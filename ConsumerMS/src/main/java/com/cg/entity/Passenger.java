package com.cg.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Passenger {
	
	
	private Long passengerId;
	private Long bookingId;
	
	@NotNull(message="bus Id is Mandatory")
	@Positive(message="bus Id should be positive")
	private Long busId;

	@NotEmpty(message="passenger Name cannot be missing or empty")
	@Size(max=25,message = "Maximum allowed length of passenger Name is 25 ")
	private String passengerName;
	

	@NotEmpty(message="gender cannot be missing or empty")
	@Size(max=1,message = "Maximum allowed length of gender is 1 ")
	private String gender;
	

	@NotNull(message="age cannot be missing or empty")
	@Min(value=1, message="age can't be less than 1")
	@Max(value=60,message="age can't be more than 100")
	@Positive(message="age cannot be negative")
	private Integer age;
	
	@NotNull(message="seat No be missing or empty")
	@Min(value=1, message="seat No can't be less than 1")
	@Max(value=60,message="seat No  can't be more than 100")
	@Positive(message="seat No  cannot be negative")
	private Integer seatNo;
	
	private String bookingStatus;
	public Passenger() {
		super();
	}
	public Passenger(Long passengerId, Long bookingId, Long busId, String passengerName, String gender, Integer age,
			Integer seatNo, String bookingStatus) {
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
	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", bookingId=" + bookingId + ", busId=" + busId
				+ ", passengerName=" + passengerName + ", gender=" + gender + ", age=" + age + ", seatNo=" + seatNo
				+ ", bookingStatus=" + bookingStatus + "]";
	}
	
	
	
	

}
