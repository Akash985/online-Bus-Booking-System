package com.cg.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookingDetails {	
	
	private Long bookingId;//not used when send data from UI to MS instead it is used only when data comes from MSto Ui
	@NotNull(message="booking Id cannot be missing or empty")
//	@Size(min=2,max=5,message = "booking Id Should be minimum of 2 digit and max of 5 digit")
	@Positive(message="booking Id cannot be negative")
	private Long userId;
	private Long busId;
	private Long routeId;
	private Double bookingAmount;
	private Integer noOfSeats;
	private String bookingStatus;
	private Date dateOfBooking;
	private List<Passenger> pssgnList;
	
	public BookingDetails() {
		super();
	}
	public BookingDetails(Long bookingId, Long userId, Long busId, Long routeId, Double bookingAmount,
			Integer noOfSeats, String bookingStatus,Date dateOfBooking, List<Passenger> pssgnList) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.busId = busId;
		this.routeId = routeId;
		this.bookingAmount = bookingAmount;
		this.noOfSeats = noOfSeats;
		this.bookingStatus = bookingStatus;
		this.pssgnList = pssgnList;
		this.dateOfBooking = dateOfBooking;
	}
	
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(Date dateOfBooking) {
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
	public Double getBookingAmount() {
		return bookingAmount;
	}
	public void setBookingAmount(Double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	public List<Passenger> getPssgnList() {
		return pssgnList;
	}
	public void setPssgnList(List<Passenger> pssgnList) {
		this.pssgnList = pssgnList;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	} 
	
	
	
	
	
}
