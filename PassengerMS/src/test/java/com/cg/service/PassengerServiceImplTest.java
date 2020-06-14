package com.cg.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.entity.Passenger;
import com.cg.repository.PassengerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PassengerServiceImplTest {

	@Autowired
	private PassengerService passengerService;
	
	@MockBean
	private PassengerRepository passengerRepository;
	
	@Test
	public void  addNewPassengerDetailsTest() {
//		saveAll
		List<Passenger> passengers = new ArrayList<>();
		passengers.add(new Passenger(70001l, 1001l, 3001l, "AAA", "M", 23, 7, "Accepted"));
		passengers.add(new Passenger(70001l, 1001l, 3001l, "AAA", "M", 23, 7, "Accepted"));
		when(passengerRepository.saveAll(passengers)).thenReturn(Stream
				.of(new Passenger(70001l, 1001l, 3001l, "AAA", "M", 23, 7, "Accepted"),
						new Passenger(70002l, 1001l, 3003l, "BBB", "M", 27, 8, "Accepted")).collect(Collectors.toList()));
		assertEquals(passengers.size(), passengerService.addNewPassengerDetails(passengers).size());
		
	}
	
	@Test
	public void  getAllPassengerDetailsTest() {
		
	}
	@Test
	public void  getPassengerDetailsByBookingIDTest() {
		
	}
	@Test
	public void  getPassengerDetailByPassengerId() {
		
	}
	@Test
	public void  updatePassengerBookingStatusToRejectedByBookingIDTest() {
		
	}
	
	@Test
	public void updatePassengerDetailByPassengerIdTest() {
	}

	
	
//	List<Passenger> addNewPassengerDetails(List<Passenger> pssgnList);
//	List<Passenger> getAllPassengerDetails();
//	List<Passenger> getPassengerDetailsByBookingID(Long bookingId) throws BookingIdNotFoundException;
//	Passenger getPassengerDetailByPassengerId(Long passengerId) throws PassengerNotFoundException;
//	List<Passenger> getPassengerDetailByName(String pssgnName) throws PassengerNotFoundException;--XXXA
//	List<Passenger> getPassengerDetailsBybusId(Long busId);--XXXA
//	List<Passenger> getPassengerDetailsBybusIdAndSeatNo(Long busId,Integer seatNo);--XXXA
////	List<Passenger> updatePassengerDetailsByBookingId(Long bookingId,List<Passenger> pssgnList);--XXXA
//	Passenger updatePassengerDetailByPassengerId(Long passengerId,Passenger pssgn) throws PassengerNotFoundException;--XXXA
//	//for cancellation
//	Passenger updatePassengerBookingStatusToRejectedByPassengerId(Long passengerId) throws PassengerNotFoundException;--XXX
//	List<Passenger> updatePassengerBookingStatusToRejectedByBookingID(Long bookingId) throws BookingIdNotFoundException;
}
