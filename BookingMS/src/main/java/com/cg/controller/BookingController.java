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
import com.cg.exception.UserHasNoBookingException;
import com.cg.service.BookingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/bookingCtrl")
public class BookingController {
	@Autowired
	BookingService bookingService;

	@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenBookingFailsDueTolostDBConnection")
	ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
		booking.setBookingStatus("Accepted");
		Booking savedBooking=bookingService.addNewBooking(booking);
		return 	new ResponseEntity<>(savedBooking, HttpStatus.OK);
	}
	
	
	
//	-------------------------------------------------------------------------------------------------------------
//	----------------------------------------Fetching---------------------------------------------------------------------

	@GetMapping(value ="/fetchAll",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Booking>> fetchAllBookings(){
		List<Booking> bookingList=bookingService.getAllBooking();	
		return new ResponseEntity<>(bookingList, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value ="/fetch/bokId={bkId}",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Booking> fetchBookingByBookingId(@PathVariable("bkId")Long bId) throws BookingIdNotFoundException{//,NumberFormatException{
		Booking booking=bookingService.getBookingByBookingID(bId);
		return 	new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value ="/fetchBookings/userId={uId}/bookId={bkId}",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Booking> fetchBookingsByUserIdAndBookingId(@PathVariable("uId")Long uId,@PathVariable("bkId")Long bkId) throws BookingIdNotFoundException{
		Booking booking=bookingService.getAllBookingByUserIdAndBookingId(uId, bkId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	


	
	
	@GetMapping(value ="/fetchBookings/userId={uId}")
	ResponseEntity<List<Booking>> fetchBookingsByUserId(@PathVariable("uId")Long uId) throws UserHasNoBookingException{
		List<Booking> bookingList=bookingService.getAllBookingByUserId(uId);
		return new ResponseEntity<>(bookingList, HttpStatus.OK);	
	}
	
	
	
	

	
	
//	-------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------For cancellation------------------------------------------------------------
	
//	For Canceling Using update instead of delete 
	@PutMapping(value ="/updateBookingToCancel/bkId={btId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Booking> cancelBookingByBookingId(@PathVariable("btId")Long btId) throws BookingIdNotFoundException {
		Booking cancelledBooking = bookingService.updatingBookingStatusToRejectedByBookingId(btId);
		return new ResponseEntity<Booking>(cancelledBooking, HttpStatus.OK);		
	}
	
	//used only for roll back
	@PutMapping(value ="/updateBookingToAccepted/bkId={btId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Booking> acceptBookingByBookingId(@PathVariable("btId")Long btId) throws BookingIdNotFoundException {
		Booking acceptedBooking = bookingService.updatingBookingStatusToAcceptedByBookingId(btId);
		return new ResponseEntity<Booking>(acceptedBooking, HttpStatus.OK);		
	}
	
//	-------------------------------------------------------------------------------------------------------------
//	---------------------------------------Fall back Method----------------------------------------------------------------------
	

	

	
	public  ResponseEntity<Booking> whenBookingFailsDueTolostDBConnection(Booking booking)
	{
		Booking booking1 = new Booking(0l, null, null, null, null, null, null, null);
		return new ResponseEntity<Booking>(booking1, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
