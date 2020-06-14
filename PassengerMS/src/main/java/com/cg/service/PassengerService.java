package com.cg.service;

import java.util.List;

import com.cg.entity.Passenger;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.PassengerNotFoundException;

public interface PassengerService {
	
	List<Passenger> addNewPassengerDetails(List<Passenger> pssgnList);
	List<Passenger> getAllPassengerDetails();
	List<Passenger> getPassengerDetailsByBookingID(Long bookingId) throws BookingIdNotFoundException;
	Passenger getPassengerDetailByPassengerId(Long passengerId) throws PassengerNotFoundException;
	List<Passenger> getPassengerDetailByName(String pssgnName) throws PassengerNotFoundException;
	List<Passenger> getPassengerDetailsBybusId(Long busId);
	List<Passenger> getPassengerDetailsBybusIdAndSeatNo(Long busId,Integer seatNo);
//	List<Passenger> updatePassengerDetailsByBookingId(Long bookingId,List<Passenger> pssgnList);
	Passenger updatePassengerDetailByPassengerId(Long passengerId,Passenger pssgn) throws PassengerNotFoundException;
	//for cancellation
	Passenger updatePassengerBookingStatusToRejectedByPassengerId(Long passengerId) throws PassengerNotFoundException;
	List<Passenger> updatePassengerBookingStatusToRejectedByBookingID(Long bookingId) throws BookingIdNotFoundException;

}
