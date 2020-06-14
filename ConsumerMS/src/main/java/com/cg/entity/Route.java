package com.cg.entity;

import java.sql.Time;


public class Route {
	
	private Long routeId;
	private Long busId;
	private String boardingPoint;
	private String droppingPoint;
	private double fare;
	private Time boardingTime;
	private Time totaljourneyTime;
	public Route() {
		super();
	}
	public Route(Long routeId, Long busId, String boardingPoint, String droppingPoint, double fare, Time boardingTime,
			Time totaljourneyTime) {
		super();
		this.routeId = routeId;
		this.busId = busId;
		this.boardingPoint = boardingPoint;
		this.droppingPoint = droppingPoint;
		this.fare = fare;
		this.boardingTime = boardingTime;
		this.totaljourneyTime = totaljourneyTime;
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
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDroppingPoint() {
		return droppingPoint;
	}
	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
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
	public Time getTotaljourneyTime() {
		return totaljourneyTime;
	}
	public void setTotaljourneyTime(Time totaljourneyTime) {
		this.totaljourneyTime = totaljourneyTime;
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", busId=" + busId + ", boardingPoint=" + boardingPoint
				+ ", droppingPoint=" + droppingPoint + ", fare=" + fare + ", boardingTime=" + boardingTime
				+ ", totaljourneyTime=" + totaljourneyTime + "]";
	}
	
	
	

}
