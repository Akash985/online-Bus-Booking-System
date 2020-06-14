package com.cg.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Passenger {
	
	
	private Long passengerId;
	private Long bookingId;
	private Long busId;
	private String passengerName;
	private String gender;
	private Integer age;
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
