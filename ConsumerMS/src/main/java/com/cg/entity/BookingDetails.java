package com.cg.dto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BookingDetails {	
	
	private Long bookingId;//not used when send data from UI to MS instead it is used only when data comes from MSto Ui
	
	@NotNull(message="user Id cannot be missing or empty")
	@Positive(message="user Id cannot be negative")
	private Long userId;
	
	@NotNull(message="bus Id is Mandatory")
	@Positive(message="bus Id should be positive")
	private Long busId;
	
	@NotNull(message="route Id is Mandatory")
	@Positive(message="route Id should be positive")
	private Long routeId;
	
	@NotNull(message="Booking amount is Mandatory")
	@Positive(message="Booking Amount should be positive")
	private Double bookingAmount;
	
	@NotNull(message="bus Id is Mandatory")
	@Positive(message="bus Id should be positive")
	@Min(value=1, message="bus Id can't be less than 1")
	@Max(value=15,message="Total seats can't be more than 15")
	private Integer noOfSeats;
	
//
//	@NotEmpty(message="bookingStatus cannot be missing or empty")
//	@Size(max=9,message = "bookingStatus allowed length of age is 2")
	private String bookingStatus;
	
	
	private Date dateOfBooking;
	@NotNull(message="Minimum 1 passenger is required")
	@NotEmpty(message="Minimum 1 passenger is required")
	private List<@Valid Passenger> pssgnList;
	
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
	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", userId=" + userId + ", busId=" + busId + ", routeId="
				+ routeId + ", bookingAmount=" + bookingAmount + ", noOfSeats=" + noOfSeats + ", bookingStatus="
				+ bookingStatus + ", dateOfBooking=" + dateOfBooking + ", pssgnList=" + pssgnList + "]";
	} 
	
	
	
	
	
}
