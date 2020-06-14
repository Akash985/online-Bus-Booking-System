package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pssg_id")
	private Long passengerId;
	
	@Column(name = "booking_id")
	@NotNull(message="booking Id cannot be missing or empty")
//	@Size(min=2,max=5,message = "booking Id Should be minimum of 2 digit and max of 5 digit")
	@Positive(message="booking Id cannot be negative")
	private Long bookingId;
	
	@Column(name = "bus_id")
	@NotNull(message="bus Id cannot be missing or empty")
//	@Size(min=2,max=5,message = "bus Id Should be minimum of 2 digit and max of 5 digit")
	@Positive(message="bus Id cannot be negative")
	private Long busId;
	
	@Column(name = "passenger_name")
	@NotEmpty(message="passenger Name cannot be missing or empty")
	@Size(max=25,message = "Maximum allowed length of passenger Name is 25 ")
	private String passengerName;
	
	@Column(name = "gender")
	@NotEmpty(message="gender cannot be missing or empty")
	@Size(max=1,message = "Maximum allowed length of gender is 1 ")
	private String gender;
	
	@Column(name = "age")
	@NotNull(message="age cannot be missing or empty")
	@Min(value=1, message="age should be more than 1")
	@Max(value=90,message="age should be below 90")
	@Positive(message="age cannot be negative")
	private Integer age;
	
	@Column(name = "seat_no")
	@NotNull(message="age cannot be missing or empty")
	@Min(value=1, message="seat no should be more than 1")
	@Max(value=50,message="seat no should be below 50")
	@Positive(message="age cannot be negative")	
	private Integer seatNo;
	
	@Column(name = "booking_status")
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
		return "Passenger [passengerId=" + passengerId + ", bookingId=" + bookingId + ", busId=" + busId + ", passengerName="
				+ passengerName + ", gender=" + gender + ", age=" + age + ", seatNo=" + seatNo + ", bookingStatus="
				+ bookingStatus + "]";
	}
	
	
	
	

}
