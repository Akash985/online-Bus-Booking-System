package com.cg.service;

import java.util.List;

import com.cg.entity.Booking;
import com.cg.exception.BookingIdNotFoundException;

public interface BookingService {
	
	Booking addNewBooking(Booking booking);
	List<Booking> getAllBooking();
	List<Booking> getAllBookingByBusId(Long busId);
	List<Booking> getAllBookingByUserId(Long userId);
	Booking getAllBookingByUserIdAndBookingId(Long userId,Long bookingId);
	Booking getBookingByBookingID(Long bookingId) throws BookingIdNotFoundException;
	Booking updateBookingByBookingId(Long bookingId,Booking booking) throws BookingIdNotFoundException;//can change only busId,BookingId,Amount
	List<Booking> updateBookingByBusId(Long busId,Booking booking);//incase some buses changes then we have to chnage all passengerr booking details
	//for cancelling ticket
	Booking updatingBookingStatusToRejectedByBookingId(Long bookingId) throws BookingIdNotFoundException;
	Booking updatingBookingStatusToAcceptedByBookingId(Long bookingId) throws BookingIdNotFoundException;
//	get booking by Id
//	get booking by busId
//	get booking by user id
	
//	update booking by booking Id --->only fr admin use
//	update booking by bus Id --->only fr admin use
	
//for cancelling ticket
//	updatingBookingStatusToRejectedByBookingId
	
	
	
}
