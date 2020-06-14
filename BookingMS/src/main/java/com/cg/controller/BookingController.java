package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Booking;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.service.BookingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/bookingCtrl")
public class BookingController {
	@Autowired
	BookingService bookingService;
	
	
//	Create
	//http://localhost:9091/bookingCtrl/create
	@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenBookingIdNotFoundss")
	ResponseEntity<Booking> createBooking(@RequestBody Booking booking) throws Exception{
		booking.setBookingStatus("Accepted");
		Booking savedBooking=bookingService.addNewBooking(booking);
		System.out.println(savedBooking+"ok");
		return 	new ResponseEntity<Booking>(savedBooking, HttpStatus.OK);
	}
	
	public  ResponseEntity<Booking> whenBookingIdNotFoundss(Booking booking)
	{
		Booking bookingm = new Booking(0l, null, null, null, null, null, null, null);
		return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
	}
	
	
//	-------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------------------------------------
//	Read
//	get all booking
	//http://localhost:9091/bookingCtrl/fetchAll
	@GetMapping(value ="/fetchAll",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Booking>> fetchAllBookings(){
		List<Booking> bookingList=bookingService.getAllBooking();	
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}
	
	
	
//###################	
//	get booking by Id
	//http://localhost:9091/bookingCtrl/fetch/bokId={bkId}
	@GetMapping(value ="/fetch/bokId={bkId}",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<Booking> fetchBookingByBookingId(@PathVariable("bkId")Long bId) throws BookingIdNotFoundException{
		Booking booking=bookingService.getBookingByBookingID(bId);
		return 	new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	
//	get booking by busId---%%%%%%%%%%%%%%%
	//http://localhost:9091/bookingCtrl/fetchBookings/userId={uId}/bookId={bkId}
	@GetMapping(value ="/fetchBookings/userId={uId}/bookId={bkId}",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Booking> fetchBookingsByUserIdAndBookingId(@PathVariable("uId")Long uId,@PathVariable("bkId")Long bkId){
		Booking booking=bookingService.getAllBookingByUserIdAndBookingId(uId, bkId);
//		System.out.println(booking);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	
//	get booking by busId---XXXA
	//http://localhost:9091/bookingCtrl/fetchBookings/busId={bId}
	@GetMapping(value ="/fetchBookings/busId={bId}",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Booking>> fetchBookingsByBusId(@PathVariable("bId")Long bId){
		List<Booking> bookingList=bookingService.getAllBookingByBusId(bId);
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}
	
	
	
//	get booking by user id
	//http://localhost:9091/bookingCtrl/fetchBookings/userId={uId}
	@GetMapping(value ="/fetchBookings/userId={uId}")//,produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Booking>> fetchBookingsByUserId(@PathVariable("uId")Long uId){
		List<Booking> bookingList=bookingService.getAllBookingByUserId(uId);
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);	
	}
	
	
//	-------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------------------------------------
	
	
//	Update
	//for admin use onlyXXXX
	//###################	
	
	@PutMapping(value ="/updateBooking/bkId={bId}",consumes = MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<Booking> updateBookingdetailsByBookingId(@PathVariable("bId")Long bId,@RequestBody Booking booking) throws BookingIdNotFoundException {
		Booking updatedBooking =bookingService.updateBookingByBookingId(bId, booking);
		
		return new ResponseEntity<Booking>(updatedBooking, HttpStatus.OK);		//can change only busId,BookingId,Amount
	}
	
	
	
	//for admin use onlyXXXXX
	@PutMapping(value ="/updateBookingByBusId/busId= {bId}",consumes = MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Booking>> updateBookingdetailsByBusId(@PathVariable("bId")Long bId,@RequestBody Booking booking) {
		List<Booking> bookingList=bookingService.updateBookingByBusId(bId, booking);
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);		//changes bookings related to that bus Id if bus chnages can change only busId,BookingId,Amount
	}
	
	
	
	
//	-------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------------------------------------
	
//	For Canceling Using update instead of delete 
	@PutMapping(value ="/updateBookingToCancel/bkId={btId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<Booking> cancelBookingByBookingId(@PathVariable("btId")Long btId) throws BookingIdNotFoundException {
		Booking cancelledBooking = bookingService.updatingBookingStatusToRejectedByBookingId(btId);
		return new ResponseEntity<Booking>(cancelledBooking, HttpStatus.OK);		
	}
	
	//used only for roll back
	@PutMapping(value ="/updateBookingToAccepted/bkId={btId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<Booking> acceptBookingByBookingId(@PathVariable("btId")Long btId) throws BookingIdNotFoundException {
		Booking acceptedBooking = bookingService.updatingBookingStatusToAcceptedByBookingId(btId);
		return new ResponseEntity<Booking>(acceptedBooking, HttpStatus.OK);		
	}
	
//	-------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------------------------------------------------
	
	public  ResponseEntity<Booking> whenBookingIdNotFound(Long bId)
	{
		Booking booking = new Booking(0l, null, null, null, null, null, null, null);
		return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
	}
	
//	public  ResponseEntity<Booking> whenBookingIdNotFoundForCancel(Long bId,Long)
//	{
//		Booking booking = new Booking(0l, null, null, null, null, null, null, null);
//		return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
//	}
	
}
