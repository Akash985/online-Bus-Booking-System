package com.cg.entity;

import java.sql.Time;
import java.util.Date;

public class BusInfo {
	
	private Long routeId;
    private Long busId;
	private String busNo;
    private String busType;
    private String busName;
    private String source;
    private String destination;
    private Integer totalSeats;
    private Integer availableSeats;
    private Date dateOfJourney;
    private Time startPointTime;
    
	private String boardingPoint;
	private String droppindPoint;
	private double fare;
	private Time boardingTime;
	private Time totalJourneyTime;
	public BusInfo() {
		super();
	}
	public BusInfo(Long routeId, Long busId, String busNo, String busType, String busName, String source,
			String destination, Integer totalSeats, Integer availableSeats, Date dateOfJourney, Time startPointTime,
			String boardingPoint, String droppindPoint, double fare, Time boardingTime, Time totalJourneyTime) {
		super();
		this.routeId = routeId;
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
		this.boardingPoint = boardingPoint;
		this.droppindPoint = droppindPoint;
		this.fare = fare;
		this.boardingTime = boardingTime;
		this.totalJourneyTime = totalJourneyTime;
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
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
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDroppindPoint() {
		return droppindPoint;
	}
	public void setDroppindPoint(String droppindPoint) {
		this.droppindPoint = droppindPoint;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Time getBoardingTime() {
		return boardingTime;
	}
	public void setBoardingTime(Time boardingTime) {
		this.boardingTime = boardingTime;
	}
	public Time getTotalJourneyTime() {
		return totalJourneyTime;
	}
	public void setTotalJourneyTime(Time totalJourneyTime) {
		this.totalJourneyTime = totalJourneyTime;
	}
	@Override
	public String toString() {
		return "BusInfo [routeId=" + routeId + ", busId=" + busId + ", busNo=" + busNo + ", busType=" + busType
				+ ", busName=" + busName + ", source=" + source + ", destination=" + destination + ", totalSeats="
				+ totalSeats + ", availableSeats=" + availableSeats + ", dateOfJourney=" + dateOfJourney
				+ ", startPointTime=" + startPointTime + ", boardingPoint=" + boardingPoint + ", droppindPoint="
				+ droppindPoint + ", fare=" + fare + ", boardingTime=" + boardingTime + ", totalJourneyTime="
				+ totalJourneyTime + "]";
	}
	
	
    
}
