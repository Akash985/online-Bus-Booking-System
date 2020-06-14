package com.cg.controller;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.entity.Booking;
import com.cg.entity.BookingDetails;
import com.cg.entity.Bus;
import com.cg.entity.BusInfo;
import com.cg.entity.Passenger;
import com.cg.entity.Route;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.BookingIsAlreadyRejectedException;
import com.cg.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/consumerCtrl")
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ConsumerService consumerService;

	public RestTemplate getrestTemplate() {
		return restTemplate;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------Search Bus Part-------------------------------------------------------------------------------------------
	
//	http://localhost:9095/consumerCtrl/search/source=Mumbai/destination=Pune/dateOfJrny=2020-06-10
	@GetMapping("/search/source={src}/destination={dst}/dateOfJrny={dt}")
	@HystrixCommand(fallbackMethod = "WhenBusorRouteServiceIsDown")
	ResponseEntity searchBus(@PathVariable("src")String source,@PathVariable("dst")String destination,@PathVariable("dt")String dateOfJourney) throws ConnectException{
		Route[] routes=restTemplate.getForObject("http://route-service/routedetails/board="+source+"/drop="+destination,Route[].class);
		List<Route> routeList=Arrays.asList(routes);
		List<BusInfo> busInfoList=new ArrayList<>();
		Route tempRoute=null;
		Bus tempBus=null;
		BusInfo tempBusInfo=null;
		Long busId=0l;
		Boolean flag=false; 
		
		
		
		for(int i = 0; i<routeList.size();i++) {
			tempRoute= routeList.get(i);
			
			
			busId=routeList.get(i).getBusId();
			flag=restTemplate.getForObject("http://bus-scheduling-service/busdetails/checkAvailability/busId="+busId+"/dateOfJrny="+dateOfJourney,Boolean.class);
			if(flag) {

				tempBus=restTemplate.getForObject("http://bus-scheduling-service/busdetails/"+busId,Bus.class);

				tempBusInfo= new BusInfo(tempRoute.getRouteId(), tempRoute.getBusId(), tempBus.getBusNo(), tempBus.getBusType(), 
										tempBus.getBusName(), tempBus.getSource(), tempBus.getDestination(), tempBus.getTotalSeats(), 
										tempBus.getAvailableSeats(), tempBus.getDateOfJourney(), tempBus.getStartPointTime(), 
										tempRoute.getBoardingPoint(), tempRoute.getDroppingPoint(), tempRoute.getFare(),
										tempRoute.getBoardingTime(), tempRoute.getTotaljourneyTime());
			
				busInfoList.add(tempBusInfo);
				
			}
		}
	

		return new ResponseEntity(busInfoList,HttpStatus.OK);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------Booking Part--------------------------------------------------------------------------------------------------
	
	

	@PostMapping(value ="/createBooking",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "WhenBookingMsFails")
	public ResponseEntity createNewBooking(@Valid @RequestBody BookingDetails bookingDetails) throws ConnectException{
		
		Booking booking = new Booking(bookingDetails.getUserId(), bookingDetails.getBusId(), bookingDetails.getRouteId(), 
								bookingDetails.getNoOfSeats(), bookingDetails.getBookingAmount(),
								bookingDetails.getBookingStatus(), new Date());

		Booking bkConfirm=restTemplate.postForObject("http://booking-service/bookingCtrl/create",booking, Booking.class);
		
		
		List<Passenger> pssgnList=null;
		
			
			for ( @Valid Passenger pssgn : bookingDetails.getPssgnList()) {
				pssgn.setBookingId(bkConfirm.getBookingId());
			}
			
			//Use Passenger MS
			try {
				pssgnList = restTemplate.postForObject("http://passenger-service/passengerCtrl/create", bookingDetails.getPssgnList(), List.class);
			} catch (Exception e) {
				//rollback
				restTemplate.exchange("http://booking-service/bookingCtrl/updateBookingToCancel/bkId="+bkConfirm.getBookingId(), HttpMethod.PUT,null, Booking.class);
				bkConfirm =restTemplate.getForObject("http://booking-service/bookingCtrl/fetch/bokId="+bkConfirm.getBookingId(),Booking.class);
				BookingDetails completeBookingDetails =consumerService.stubBookingAndPassengerListInBookingDetails(bkConfirm, pssgnList);					
				return new ResponseEntity("Passenger service down-->"+completeBookingDetails.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			BookingDetails completeBookingDetails =consumerService.stubBookingAndPassengerListInBookingDetails(bkConfirm, pssgnList);			
			
			return new ResponseEntity(completeBookingDetails,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/bookingHistory/userId={uId}")
	@HystrixCommand(fallbackMethod = "WhenBookingHistoryFails")
	ResponseEntity getBookingHistoryByUserId(@PathVariable("uId")Long userId) {			
		Booking[] bookingArray = restTemplate.getForObject( "http://booking-service/bookingCtrl/fetchBookings/userId="+userId,Booking[].class);//consumerService.getBusBookingById(userId);
		List<Booking> bookingList=Arrays.asList(bookingArray);
		return new ResponseEntity(bookingList,HttpStatus.OK);		
	}
	
	
	//for user
	@GetMapping("/bookingDetails/userId={uId}/bookId={bkId}")
	@HystrixCommand(ignoreExceptions = {BookingIdNotFoundException.class},fallbackMethod = "WhenGetBookingFails")
	ResponseEntity getBookingDetailsByUserIdAndBookingId(@PathVariable("uId")Long userId,@PathVariable("bkId")Long bookingId) throws BookingIdNotFoundException {			

		
		BookingDetails bookingDetails = consumerService.stubBookingAndPassengerListInBookingDetails(
												consumerService.getBusBookingByUserIdAndBookingId(userId, bookingId), 
												consumerService.getPassengerListByBookingId(bookingId));
		return new ResponseEntity(bookingDetails,HttpStatus.OK);		
	}
	
	
	
	
	
//	For Canceling Using update instead of delete 
	@PutMapping(value ="/cancelBooking/userId={uId}/bookId={bkId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(ignoreExceptions = {BookingIdNotFoundException.class,BookingIsAlreadyRejectedException.class},fallbackMethod="BookingIdNotFounHandlerWhileCancelling")	
	ResponseEntity cancelBookingByUserIdAndBookingId(@PathVariable("uId")Long userId,@PathVariable("bkId")Long bookingId) throws BookingIdNotFoundException, BookingIsAlreadyRejectedException {
		List<Passenger> pssgnList = null;

		Booking booking = consumerService.getBusBookingByUserIdAndBookingId(userId, bookingId);
		if(booking.getBookingStatus().equals("Rejected")) {
			throw new BookingIsAlreadyRejectedException("This booking Id is already Rejected");
		}

		System.out.println("inside else");
		Booking cancelledBooking= consumerService.cancelBookingByBookingId(bookingId);
		
		try {
			pssgnList = consumerService.updateBookingStatusToRejectedForPassengerByBookingId(bookingId);
		} catch (Exception e) {
			//rollback
			Booking reacceptedBooking =consumerService.acceptBookingByBookingId(bookingId);
			BookingDetails cancelledBookingDetails =consumerService.stubBookingAndPassengerListInBookingDetails(reacceptedBooking, pssgnList);
			return new ResponseEntity("Passenger service is down therefore cancellation cannot be done"+cancelledBookingDetails.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		BookingDetails cancelledBookingDetails =consumerService.stubBookingAndPassengerListInBookingDetails(cancelledBooking, pssgnList);

		return new ResponseEntity(cancelledBookingDetails,HttpStatus.OK);

	}
	
	
	
	
	
	@GetMapping("/")
	ResponseEntity welcomeMessage() {
		return new ResponseEntity("Welcome to Bus Booking",HttpStatus.OK);
	}
	
//	--------------------------------------------------------------------------------------------------------------------
//	-----------------------------------Fall Back Methods----------------------------------------------------------------

	public ResponseEntity WhenBookingMsFails(BookingDetails bookingDetails)
	{
		return new ResponseEntity("Booking Service is Temporarily down ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity WhenBusorRouteServiceIsDown(String source,String destination,String date) {
		return new ResponseEntity("SearcH Operation Is Temporarily Down",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity WhenBookingHistoryFails(Long userId)
	{
		return new ResponseEntity("Booking History is Temporarily down ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity BookingIdNotFounHandlerWhileCancelling(Long userId,Long bookingId)
	{
		return new ResponseEntity("Booking Server is down so cancellation is denied ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	public ResponseEntity WhenGetBookingFails(Long userId,Long bookingId)
	{
		return new ResponseEntity("Booking Server is down so cancellation is denied ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
