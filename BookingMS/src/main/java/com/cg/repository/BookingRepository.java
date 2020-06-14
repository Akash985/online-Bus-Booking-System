package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	List<Booking> findAllByBusId(Long busId);
	List<Booking> findAllByUserId(Long userId);
	Booking findByUserIdAndBookingId(Long userId,Long bookingId);
	
}
