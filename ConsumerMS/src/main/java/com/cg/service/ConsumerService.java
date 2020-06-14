package com.cg.service;

import java.net.ConnectException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.cg.dto.Booking;
import com.cg.dto.BookingDetails;
import com.cg.dto.Passenger;
import com.cg.exception.BookingIdNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {
	
	@Autowired
	RestTemplate restTemplate;

	public RestTemplate getRestTemplet() {
		return restTemplate;
	}
	
	

		public List<Booking> getBusBookingById(Long userId){
//			Here als exception will come
			List<Booking> bkList = restTemplate.getForObject( "http://booking-service/bookingCtrl/fetchBookings/userId="+userId,List.class);
			return bkList;
		}
		
		public List<Passenger> getPassengerListByBookingId(Long bookingId){
//			Here als exception will come
			return restTemplate.getForObject( "http://passenger-service/passengerCtrl/fetchPassenger/bId="+bookingId,List.class);
		}
	
			
		public Booking getBusBookingByUserIdAndBookingId(Long userId,Long bookingId) throws BookingIdNotFoundException{
//			Here als exception will come
			Booking bk =restTemplate.getForObject( "http://booking-service/bookingCtrl/fetchBookings/userId="+userId+"/bookId="+bookingId,Booking.class);
			if(bk==null) {
				throw new BookingIdNotFoundException("This booking Id does not exist");
			}
			return bk;
		}

		public Booking acceptBookingByBookingId(Long bookingId){
			return restTemplate.exchange("http://booking-service/bookingCtrl/updateBookingToAccepted/bkId="+bookingId, HttpMethod.PUT,null, Booking.class).getBody();
		}
		
		public Booking cancelBookingByBookingId(Long bookingId){			
			return restTemplate.exchange("http://booking-service/bookingCtrl/updateBookingToCancel/bkId="+bookingId, HttpMethod.PUT,null, Booking.class).getBody();
		}
		
		
//		http://localhost:9091/bookingCtrl/cancelPassenger/bkId= {bId}
		public List<Passenger> updateBookingStatusToRejectedForPassengerByBookingId(Long bookingId){			
			return restTemplate.exchange("http://passenger-service/bookingCtrl/cancelPassenger/bkId="+bookingId, HttpMethod.PUT,null,List.class).getBody();
		}
		
		public BookingDetails stubBookingAndPassengerListInBookingDetails(Booking booking,List<Passenger> pssgnList) {
			BookingDetails completeBookingDetails = new BookingDetails(booking.getBookingId(), booking.getUserId(),
					booking.getBusId(),booking.getRouteId(), booking.getBookingAmount(), booking.getNoOfSeats(),
					booking.getBookingStatus(),booking.getDateOfBooking(), pssgnList);
			return completeBookingDetails;
		}
}
