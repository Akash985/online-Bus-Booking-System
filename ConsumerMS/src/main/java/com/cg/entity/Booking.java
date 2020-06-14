package com.cg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Booking {

	
	private Long bookingId;
	private Long userId;
	private Long busId;
	private Long routeId;
	private Integer noOfSeats;
	private Double bookingAmount;
	private String  bookingStatus;
	private Date dateOfBooking;
	public Booking() {
		super();
	}
	
	public Booking(Long userId, Long busId, Long routeId, Integer noOfSeats, Double bookingAmount, String bookingStatus,
			Date dateOfBooking) {
		super();
		this.userId = userId;
		this.busId = busId;
		this.routeId = routeId;
		this.noOfSeats = noOfSeats;
		this.bookingAmount = bookingAmount;
		this.bookingStatus = bookingStatus;
		this.dateOfBooking = dateOfBooking;
	}
	
	public Booking(Long userId, Long busId, Long routeId, Integer noOfSeats, Double bookingAmount, Date datOfBooking) {
		super();
		this.userId = userId;
		this.busId = busId;
		this.routeId = routeId;
		this.noOfSeats = noOfSeats;
		this.bookingAmount = bookingAmount;
		this.dateOfBooking = dateOfBooking;
	}

	public Booking(Long bookingId, Long userId, Long busId, Long routeId, Integer noOfSeats, Double bookingAmount,
			String bookingStatus, Date dateOfBooking) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.busId = busId;
		this.routeId = routeId;
		this.noOfSeats = noOfSeats;
		this.bookingAmount = bookingAmount;
		this.bookingStatus = bookingStatus;
		this.dateOfBooking = dateOfBooking;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public Double getBookingAmount() {
		return bookingAmount;
	}
	public void setBookingAmount(Double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDatOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", busId=" + busId + ", routeId=" + routeId
				+ ", noOfSeats=" + noOfSeats + ", bookingAmount=" + bookingAmount + ", bookingStatus=" + bookingStatus
				+ ", datOfBooking=" + dateOfBooking + "]";
	}

	
//	booking_id,user_id,bus_id,route_id,no_of_seats,booking_amt,booking_status,date_of_booking
//	insert into booking(booking_id,user_id,bus_id,route_id,no_of_seats,booking_amt,booking_status,date_of_booking) values('6001',10001,5001,70001,5,1200.0,'Accepted','2020-06-25')
	
	
	
}
