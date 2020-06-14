package com.cg.controller;

import java.net.ConnectException;
import java.sql.Time;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cg.dto.Booking;
import com.cg.dto.BookingDetails;
import com.cg.dto.Bus;
import com.cg.dto.BusInfo;
import com.cg.dto.Passenger;
import com.cg.dto.Route;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.BookingIsAlreadyRejectedException;
import com.cg.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/consumerCtrl")
public class ConsumerController {

	@Autowired
	RestTemplate restTemplet;
	
	@Autowired
	ConsumerService consumerService;

	public RestTemplate getRestTemplet() {
		return restTemplet;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------Search Bus Part-------------------------------------------------------------------------------------------
	
//	http://localhost:9095/consumerCtrl/search/source=Mumbai/destination=Pune/dateOfJrny=2020-06-10
	@GetMapping("/search/source={src}/destination={dst}/dateOfJrny={dt}")
	@HystrixCommand(fallbackMethod = "WhenBusorRouteServiceIsDown")
	ResponseEntity searchBus(@PathVariable("src")String source,@PathVariable("dst")String destination,@PathVariable("dt")String dateOfJourney) throws ConnectException{
		Route[] routes=restTemplet.getForObject("http://route-service/routedetails/board="+source+"/drop="+destination,Route[].class);
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
			flag=restTemplet.getForObject("http://bus-scheduling-service/busdetails/checkAvailability/busId="+busId+"/dateOfJrny="+dateOfJourney,Boolean.class);
			if(flag) {

				tempBus=restTemplet.getForObject("http://bus-scheduling-service/busdetails/"+busId,Bus.class);
//				tempRoute.getBoardingTime().
				
//				inject tempRoute and tempBus in TemBusInfo
				tempBusInfo= new BusInfo(tempRoute.getRouteId(), tempRoute.getBusId(), tempBus.getBusNo(), tempBus.getBusType(), 
										tempBus.getBusName(), tempBus.getSource(), tempBus.getDestination(), tempBus.getTotalSeats(), 
										tempBus.getAvailableSeats(), tempBus.getDateOfJourney(), tempBus.getStartPointTime(), 
										tempRoute.getBoardingPoint(), tempRoute.getDroppingPoint(), tempRoute.getFare(),
										tempRoute.getBoardingTime(), tempRoute.getTotaljourneyTime());
				
//				tempBusInfo.setBoardingtime(tempBus.getStartPointTime().set);
				busInfoList.add(tempBusInfo);
				
			}
		}
	

		return new ResponseEntity(busInfoList,HttpStatus.OK);
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------Booking Part--------------------------------------------------------------------------------------------------
	
	

//  http://localhost:9095/consumerCtrl/createBooking
	@PostMapping(value ="/createBooking",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "WhenBookingMsFails")
	public ResponseEntity createNewBooking(@Valid @RequestBody BookingDetails bookingDetails) throws ConnectException{
		
		Booking booking = new Booking(bookingDetails.getUserId(), bookingDetails.getBusId(), bookingDetails.getRouteId(), 
								bookingDetails.getNoOfSeats(), bookingDetails.getBookingAmount(),
								bookingDetails.getBookingStatus(), new Date());
		
		Booking bkConfirm=restTemplet.postForObject("http://booking-service/bookingCtrl/create",booking, Booking.class);
//		System.out.println(bkConfirm);
		
		List<Passenger> pssgnList=null;
		if(bkConfirm!=null) {
			
			for (Passenger pssgn : bookingDetails.getPssgnList()) {
				pssgn.setBookingId(bkConfirm.getBookingId());
			}
			
			//Use Passenger MS
			try {
				pssgnList = restTemplet.postForObject("http://passenger-service/passengerCtrl/create", bookingDetails.getPssgnList(), List.class);
			} catch (Exception e) {
				//rollback
				restTemplet.exchange("http://booking-service/bookingCtrl/updateBookingToCancel/bkId="+bkConfirm.getBookingId(), HttpMethod.PUT,null, Booking.class);
				bkConfirm =restTemplet.getForObject("http://booking-service/bookingCtrl/fetch/bokId="+bkConfirm.getBookingId(),Booking.class);
				
			}
			
			BookingDetails completeBookingDetails = new BookingDetails(bkConfirm.getBookingId(), bkConfirm.getUserId(),
														bkConfirm.getBusId(),bkConfirm.getRouteId(), bkConfirm.getBookingAmount(), bkConfirm.getNoOfSeats(),
														bkConfirm.getBookingStatus(),bkConfirm.getDateOfBooking(), pssgnList);
			
			return new ResponseEntity(completeBookingDetails,HttpStatus.OK);
		}else {
			return null;
		}
		
	}
	
//	http://localhost:9095/consumerCtrl/bookingHistory/userId=10001
	@GetMapping("/bookingHistory/userId={uId}")
	@HystrixCommand(fallbackMethod = "WhenBookingHistoryFails")
	ResponseEntity getBookingHistoryByUserId(@PathVariable("uId")Long userId) {
		
		
		Booking[] bookingArray = restTemplet.getForObject( "http://booking-service/bookingCtrl/fetchBookings/userId="+userId,Booking[].class);//consumerService.getBusBookingById(userId);
		List<Booking> bookingList=Arrays.asList(bookingArray);
		//withot passenger
//		List<Passenger> pssgnList = null;
//		Passenger[] pssgnArray = null;
//		List<BookingDetails> bookingDetailsList = new ArrayList<BookingDetails>();
//		for (Booking booking : bookingList) {
//			pssgnArray = restTemplet.getForObject("http://passenger-service/passengerCtrl/fetchPassenger/bId="+booking.getBookingId(),Passenger[].class);
//		    pssgnList=Arrays.asList(pssgnArray);
//			System.out.println(pssgnList);
//			bookingDetailsList.add(consumerService.stubBookingAndPassengerListInBookingDetails(booking, pssgnList));			
//		}
//		Long j=0l;
//		for (int i = 0; i < bookingList.size(); i++) {
//			j=bookingList.get(i).getBookingId();
//			pssgnArray = restTemplet.getForObject("http://passenger-service/passengerCtrl/fetchPassenger/bId="+j,Passenger[].class);
//		    pssgnList=Arrays.asList(pssgnArray);
//			System.out.println(pssgnList);
//		bookingDetailsList.add(consumerService.stubBookingAndPassengerListInBookingDetails(bookingList.get(i), pssgnList));
//			
//		}
		return new ResponseEntity(bookingList,HttpStatus.OK);		
	}
	
	
	
	
//	For Canceling Using update instead of delete 
	@PutMapping(value ="/cancelBooking",consumes = MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
//	@HystrixCommand(fallbackMethod="BookingIdNotFounHandler")	
	ResponseEntity cancelBookingByUserIdAndBookingId(@RequestBody Long[] obj) throws BookingIdNotFoundException, BookingIsAlreadyRejectedException {
		List<Passenger> pssgnList = null;
		Long userId= obj[0];
		Long bookingId = obj[1];
		System.out.println(userId+"  bbb "+bookingId);
		//chech boking id is of given userid or not
		Booking booking = consumerService.getBusBookingByUserIdAndBookingId(userId, bookingId);
		System.out.println(booking);
		if(booking.getBookingStatus().equals("Rejected")) {
			throw new BookingIsAlreadyRejectedException("This booking Id is already Rejected");
		}

		System.out.println("inside else");
		Booking cancelBk= restTemplet.exchange("http://booking-service/bookingCtrl/updateBookingToCancel/bkId="+bookingId, HttpMethod.PUT,null, Booking.class).getBody();
		
		try {
			pssgnList = restTemplet.exchange("http://passenger-service/passengerCtrl/cancelPassenger/bkId="+bookingId, HttpMethod.PUT,null, List.class).getBody();
			System.out.println(pssgnList);
		
//				pssgnList = restTemplet.postForObject("http://passenger-service/passengerCtrl/create", bookingDetails.getPssgnList(), List.class);
		} catch (Exception e) {
			//rollback
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			cancelBk =restTemplet.exchange("http://booking-service/bookingCtrl/updateBookingToAccepted/bkId="+cancelBk.getBookingId(), HttpMethod.PUT,null, Booking.class).getBody();
//				cancelBk =restTemplet.getForObject("http://booking-service/bookingCtrl/fetch/bokId="+bkConfirm.getBookingId(),Booking.class);
			
		}
		
		BookingDetails cancelledBookingDetails = new BookingDetails(cancelBk.getBookingId(), cancelBk.getUserId(),
				cancelBk.getBusId(),cancelBk.getRouteId(), cancelBk.getBookingAmount(), cancelBk.getNoOfSeats(),
				cancelBk.getBookingStatus(),cancelBk.getDateOfBooking(), pssgnList);

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
		return new ResponseEntity("Booking Service is Temporarily down ", HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity WhenBusorRouteServiceIsDown(String source,String destination,String date) {
		return new ResponseEntity("SearcH Operation Is Temporarily Down",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity WhenBookingHistoryFails(Long userId)
	{
		return new ResponseEntity("Booking History is Temporarily down ", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
}
