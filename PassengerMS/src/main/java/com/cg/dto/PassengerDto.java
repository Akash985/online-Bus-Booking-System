package com.cg.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerDto {

	
	private Long passengerId;
	
	
	@NotNull(message="booking Id cannot be missing or empty")
//	@Size(min=2,max=5,message = "booking Id Should be minimum of 2 digit and max of 5 digit")
	@Positive(message="booking Id cannot be negative")
	private Long bookingId;
	
	
	@NotNull(message="bus Id cannot be missing or empty")
//	@Size(min=2,max=5,message = "bus Id Should be minimum of 2 digit and max of 5 digit")
	@Positive(message="bus Id cannot be negative")
	private Long busId;
	
	
	@NotEmpty(message="passenger Name cannot be missing or empty")
	@Size(max=25,message = "Maximum allowed length of passenger Name is 25 ")
	private String passengerName;
	
	
	@NotEmpty(message="gender cannot be missing or empty")
	@Size(max=1,message = "Maximum allowed length of gender is 1 ")
	private String gender;
	
	
	@NotNull(message="age cannot be missing or empty")
//	@Size(max=2,message = "Max allowed length of age is 2")
	@Positive(message="age cannot be negative")
	private Integer age;
	
	
	@NotNull(message="age cannot be missing or empty")
//	@Size(max=2,message = "Max allowed length of age is 2")
	@Positive(message="age cannot be negative")	
	private Integer seatNo;
	

	@NotEmpty(message="bookingStatus cannot be missing or empty")
	@Size(max=9,message = "bookingStatus allowed length of age is 2")
	private String bookingStatus;


	public PassengerDto() {
		super();
	}


	public PassengerDto(Long passengerId,
			@NotNull(message = "booking Id cannot be missing or empty") @Positive(message = "booking Id cannot be negative") Long bookingId,
			@NotNull(message = "bus Id cannot be missing or empty") @Positive(message = "bus Id cannot be negative") Long busId,
			@NotEmpty(message = "passenger Name cannot be missing or empty") @Size(max = 25, message = "Maximum allowed length of passenger Name is 25 ") String passengerName,
			@NotEmpty(message = "gender cannot be missing or empty") @Size(max = 1, message = "Maximum allowed length of gender is 1 ") String gender,
			@NotNull(message = "age cannot be missing or empty") @Positive(message = "age cannot be negative") Integer age,
			@NotNull(message = "age cannot be missing or empty") @Positive(message = "age cannot be negative") Integer seatNo,
			@NotEmpty(message = "bookingStatus cannot be missing or empty") @Size(max = 9, message = "bookingStatus allowed length of age is 2") String bookingStatus) {
		super();
		this.passengerId = passengerId;
		this.bookingId = bookingId;
		this.busId = busId;
		this.passengerName = passengerName;
		this.gender = gender;
		this.age = age;
		this.seatNo = seatNo;
		this.bookingStatus = bookingStatus;
	}


	public Long getPassengerId() {
		return passengerId;
	}


	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}


	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public Long getBusId() {
		return busId;
	}


	public void setBusId(Long busId) {
		this.busId = busId;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public Integer getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	
}
