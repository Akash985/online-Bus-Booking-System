package com.cg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Booking;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.UserHasNoBookingException;
import com.cg.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Booking addNewBooking(Booking booking)  {
		return bookingRepo.save(booking);// no exceptin required
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingRepo.findAll();// not decide to add excepion
	}

	@Override
	public List<Booking> getAllBookingByBusId(Long busId) {
		return bookingRepo.findAllByBusId(busId); // MP
	}

	@Override
	public List<Booking> getAllBookingByUserId(Long userId) throws UserHasNoBookingException {
		List<Booking> bookinList = bookingRepo.findAllByUserId(userId);
		if(bookinList.isEmpty()) {
			throw new UserHasNoBookingException("No booking found For given UserId");
		}
		return bookinList; // MP
	}

	@Override
	public Booking getBookingByBookingID(Long bookingId) throws BookingIdNotFoundException {
		Booking booking = bookingRepo.findById(bookingId).orElse(null);//orElse will return null if the value is not present
		if (booking == null) {
			throw new BookingIdNotFoundException("Booking Id Not Found");
		} else {
			return booking;
		} // HP--Implemented
	}

	@Override
	public Booking getAllBookingByUserIdAndBookingId(Long userId, Long bookingId) throws BookingIdNotFoundException {
		Booking booking = bookingRepo.findByUserIdAndBookingId(userId, bookingId);
		if(booking==null) {
			throw new BookingIdNotFoundException("No booking found for given UserId");
		}
		return booking;
	}
	
	
	@Override
	public Booking updateBookingByBookingId(Long bookingId, Booking booking) throws BookingIdNotFoundException {
		Booking tempBooking = bookingRepo.findById(bookingId).orElse( null);
		if (tempBooking == null) {
			throw new BookingIdNotFoundException("Booking Id Not Found");
		} else {
			tempBooking.setBusId(booking.getBusId());
			tempBooking.setRouteId(booking.getRouteId());
			tempBooking.setBookingAmount(booking.getBookingAmount());
			return bookingRepo.save(tempBooking);
		}
	}

	@Override
	public List<Booking> updateBookingByBusId(Long busId, Booking booking) {
		Booking tempBooking = null; 											// LP
		List<Booking> tempBookingList = bookingRepo.findAllByBusId(busId);
		for (int i = 0; i < tempBookingList.size(); i++) {
			tempBooking = tempBookingList.get(i);
			tempBooking.setBusId(booking.getBusId());
			tempBooking.setRouteId(booking.getRouteId());
			tempBooking.setBookingAmount(booking.getBookingAmount());
			bookingRepo.save(tempBooking);
		}
		return bookingRepo.findAllByBusId(busId);
	}

	@Override
	public Booking updatingBookingStatusToRejectedByBookingId(Long bookingId) throws BookingIdNotFoundException {
		Booking tempBooking = bookingRepo.findById(bookingId).orElse(null);
		if (tempBooking == null) {
			throw new BookingIdNotFoundException("Booking Id Not Found");
		} else {
			tempBooking.setBookingStatus("Rejected");
			return bookingRepo.save(tempBooking);
		}
	}

	@Override
	public Booking updatingBookingStatusToAcceptedByBookingId(Long bookingId) throws BookingIdNotFoundException {
		Booking tempBooking = bookingRepo.findById(bookingId).orElse(null);
		if (tempBooking == null) {
			throw new BookingIdNotFoundException("Booking Id Not Found");
		} else {
			tempBooking.setBookingStatus("Accepted");
			return bookingRepo.save(tempBooking);
		}
	}

	

}
