package com.cg.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.entity.Booking;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.repository.BookingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceImpTest {
	
	@Autowired
	private BookingService bookingService;
	
	@MockBean
	private BookingRepository bookingRepository;
	
	@Test
	public void addNewBookingTest() {
		Booking booking = new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Accepted", new Date());
		when(bookingRepository.save(booking)).thenReturn(booking);
		assertEquals(booking, bookingService.addNewBooking(booking));
		
	}
	
	@Test
	public void getAllBookingTest() {
		when(bookingRepository.findAll()).thenReturn(Stream
				.of(new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Accepted", new Date()),
				new Booking(1002l, 2002l, 3003l, 4004l, 5, 4555.50, "Accepted", new Date())).collect(Collectors.toList()));
		assertEquals(2, bookingService.getAllBooking().size());
	}
	
	@Test
	public void getAllBookingByBusIdTest() {
		Long busId= 3001l;
		when(bookingRepository.findAllByBusId(busId)).thenReturn(Stream
				.of(new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Accepted", new Date()),
				new Booking(1003l, 2004l, 3001l, 4004l, 5, 4555.50, "Accepted", new Date())).collect(Collectors.toList()));
		assertEquals(2, bookingService.getAllBookingByBusId(busId).size());
	}
	
	@Test
	public void getBookingByBookingIdTest() throws BookingIdNotFoundException {
		Long bookingId= 1001l;
		Booking booking = (new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Accepted", new Date()));
		when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
		
		System.out.println(bookingRepository.findById(bookingId).get());

		assertEquals(booking, bookingService.getBookingByBookingID(bookingId));
	}
	
	@Test(expected = BookingIdNotFoundException.class)
	public void getBookingByBookingIdTestForBookingIdNotFoundException() throws BookingIdNotFoundException {
		Long bookingId= 1001l;
		when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
		bookingService.getBookingByBookingID(bookingId);
	}
	
	
	@Test
	public void updatingBookingStatusToRejectedByBookingIdTest() throws BookingIdNotFoundException {
		Long bookingId= 1001l;
		Booking booking1 = (new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Accepted", new Date()));
		Booking booking2 = (new Booking(1001l, 2001l, 3001l, 4001l, 8, 5555.50, "Rejected", new Date()));
		when(bookingRepository.findById(bookingId)).thenReturn(Optional.ofNullable(booking1));
		when(bookingRepository.save(booking1)).thenReturn(booking2);
		assertEquals(booking2, bookingService.updatingBookingStatusToRejectedByBookingId(bookingId));
		
	}
	
	@Test(expected = BookingIdNotFoundException.class)
	public void updatingBookingStatusToRejectedByBookingIdTestForBookingIdNotFoundException() throws BookingIdNotFoundException {
		Long bookingId= 1001l;
		when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
		bookingService.updatingBookingStatusToRejectedByBookingId(bookingId);
		
	}
	
	
//	List<Booking> getAllBookingByBusId(Long busId);--
//	List<Booking> getAllBookingByUserId(Long userId);--
//	Booking getBookingByBookingID(Long bookingId) throws BookingIdNotFoundException;---
//	Booking updateBookingByBookingId(Long bookingId,Booking booking) throws BookingIdNotFoundException;//can change only busId,BookingId,Amount**************
//	List<Booking> updateBookingByBusId(Long busId,Booking booking);//incase some buses changes then we have to chnage all passengerr booking details*************
//	//for cancelling ticket
//	Booking updatingBookingStatusToRejectedByBookingId(Long bookingId) throws BookingIdNotFoundException;---
	

}
