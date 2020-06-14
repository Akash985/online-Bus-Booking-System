package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{
	
	List<Passenger> findAllByBookingId(Long bookingId);
	List<Passenger> findAllByPassengerName(String passengerName);
	List<Passenger> findAllByBusId(Long busId);
	List<Passenger> findAllByBusIdAndSeatNo(Long busId,Integer seatNo);
	

}
