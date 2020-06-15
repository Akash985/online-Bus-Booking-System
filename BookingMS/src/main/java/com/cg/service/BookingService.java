package com.cg.service;

import java.util.List;

import com.cg.entity.Booking;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.UserHasNoBookingException;

public interface BookingService {
	
	Booking addNewBooking(Booking booking);
	List<Booking> getAllBooking();
	List<Booking> getAllBookingByBusId(Long busId);
	List<Booking> getAllBookingByUserId(Long userId) throws UserHasNoBookingException;
	Booking getAllBookingByUserIdAndBookingId(Long userId,Long bookingId) throws BookingIdNotFoundException;
	Booking getBookingByBookingID(Long bookingId) throws BookingIdNotFoundException;
	Booking updateBookingByBookingId(Long bookingId,Booking booking) throws BookingIdNotFoundException;//can change only busId,BookingId,Amount
	List<Booking> updateBookingByBusId(Long busId,Booking booking);//incase some buses changes then we have to chnage all passengerr booking details
	//for cancelling ticket
	Booking updatingBookingStatusToRejectedByBookingId(Long bookingId) throws BookingIdNotFoundException;
	Booking updatingBookingStatusToAcceptedByBookingId(Long bookingId) throws BookingIdNotFoundException;

	
	
	
}
