package com.cg.model;


import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bus")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bus_id")
	@NotNull(message="BusId is Mandatory")
    private Integer busId;
	
	@Column(name="bus_no")
	@NotNull(message="BusNo is Mandatory")
	@Size(min = 5, message = "BusNo should have atleast 5 characters")
	private String busNo;
	
	@Column(name="bus_type")
	@NotNull(message="Bustype is Mandatory")
	@Size(min = 5, message = "Bustype should have atleast 5 characters")
    private String busType;
	
	@Column(name="bus_name")
	@NotNull(message="Busname is Mandatory")
	@Size(min=2,message="Bus name should be more than 2 characters")
    private String busName;
	
	@Column(name="source")
	@NotNull(message="source is Mandatory")
	@Size(min=2 , message="Source should be more than 2 characters")
    private String source;
	
	@Column(name="destination")
	@NotNull(message="Destination is Mandatory")
	@Size(min=2 , message="Destination should be more than 2 characters")
    private String destination;
	
	@Column(name="total_seats")
	@Positive(message="Totalseats should be positive")
	@NotNull(message="Total seats is Mandatory")
	@Min(value=1, message="Total seats can't be less than 1")
	@Max(value=60,message="Total seats can't be more than 60")
    private Integer totalSeats;
	
	@Column(name="available_seats")
	@Positive(message="Availableseats should be positive")
	@NotNull(message="Available Seats is Mandatory")
	@Max(value=60,message="Available seats can't be more than 60")
    private Integer availableSeats;
	
	@Column(name="dateof_journey")
	@NotNull(message="Date of Journey is mandatory")
	@Future
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfJourney;
	
	@Column(name="startpoint_time")
	@NotNull(message="StartPointTime is mandatory")
    private Time startPointTime;
	
	
	public Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	public Time getStartPointTime() {
		return startPointTime;
	}
	public void setStartPointTime(Time startPointTime) {
		this.startPointTime = startPointTime;
	}
	public Bus(Integer busId, String busNo, String busType, String busName, String source, String destination,
			Integer totalSeats, Integer availableSeats, Date dateOfJourney, Time startPointTime) {
		super();
		this.busId = busId;
		this.busNo = busNo;
		this.busType = busType;
		this.busName = busName;
		this.source = source;
		this.destination = destination;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.dateOfJourney = dateOfJourney;
		this.startPointTime = startPointTime;
	}
	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busNo=" + busNo + ", busType=" + busType + ", busName=" + busName
				+ ", source=" + source + ", destination=" + destination + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", dateOfJourney=" + dateOfJourney + ", startPointTime="
				+ startPointTime + "]";
	}
	public Object size() {
		// TODO Auto-generated method stub
		return null;
	}
	
    
    
}
