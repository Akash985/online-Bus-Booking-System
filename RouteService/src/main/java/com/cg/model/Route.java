package com.cg.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="route")
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="route_id")
	@Positive
	private Integer routeId;
	@Column(name="bus_id")
	@Positive
	private Integer busId;
	@Column(name="boarding_point")
	@NotNull
	@Size(min=2,message="Boarding point should be more than 2 characters")
	private String boardingPoint;
	@Column(name="dropping_point")
	@NotNull
	@Size(min=2,message="Dropping point should be more than 2 characters")
	private String droppingPoint;
	@Column(name="fare")
	@Positive(message="fare should be positive")
	private double fare;
	@Column(name="boarding_time")
	private Time boardingTime;
	@Column(name="totaljourney_time")
	private Time totalJourneyTime;
	public @Positive Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public @Positive Integer getBusId() {
		return busId;
	}
	public void setBusId(Integer busId) {
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
	public Time getTotalJourneyTime() {
		return totalJourneyTime;
	}
	public void setTotalJourneyTime(Time totalJourneyTime) {
		this.totalJourneyTime = totalJourneyTime;
	}
	public Route(Integer routeId,Integer busId, String boardingPoint, String droppingPoint, double fare, Time boardingTime,
			Time totalJourneyTime) {
		super();
		this.routeId = routeId;
		this.busId = busId;
		this.boardingPoint = boardingPoint;
		this.droppingPoint = droppingPoint;
		this.fare = fare;
		this.boardingTime = boardingTime;
		this.totalJourneyTime = totalJourneyTime;
	}
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", busId=" + busId + ", boardingPoint=" + boardingPoint
				+ ", droppingPoint=" + droppingPoint + ", fare=" + fare + ", boardingTime=" + boardingTime
				+ ", totalJourneyTime=" + totalJourneyTime + "]";
	}
	
}
