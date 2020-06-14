package com.cg.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Bus {
    private Long busId;
	private String busNo;
    private String busType;
    private String busName;
    private String source;
    private String destination;
    private Integer totalSeats;
    private Integer availableSeats;
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfJourney;
    private Time startPointTime;
	public Bus() {
		super();
	}
	public Bus(Long busId, String busNo, String busType, String busName, String source, String destination,
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
	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
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
	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busNo=" + busNo + ", busType=" + busType + ", busName=" + busName
				+ ", source=" + source + ", destination=" + destination + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", dateOfJourney=" + dateOfJourney + ", startPointTime="
				+ startPointTime + "]";
	}
	
    
    
}
