package com.cg.dto;

import java.sql.Time;
import java.util.Date;

public class BusInfo {
	
	private Long routeid;
    private Long busid;
	private String busno;
    private String bustype;
    private String busname;
    private String source;
    private String destination;
    private Integer totalseats;
    private Integer availableseats;
    private Date dateofjourney;
    private Time startpointtime;
    
	private String boardingpoint;
	private String droppindpoint;
	private double fare;
	private Time boardingtime;
	private Time totaljourneytime;
	public BusInfo() {
		super();
	}
	public BusInfo(Long routeid, Long busid, String busno, String bustype, String busname, String source,
			String destination, Integer totalseats, Integer availableseats, Date dateofjourney, Time startpointtime,
			String boardingpoint, String droppindpoint, double fare, Time boardingtime, Time totaljourneytime) {
		super();
		this.routeid = routeid;
		this.busid = busid;
		this.busno = busno;
		this.bustype = bustype;
		this.busname = busname;
		this.source = source;
		this.destination = destination;
		this.totalseats = totalseats;
		this.availableseats = availableseats;
		this.dateofjourney = dateofjourney;
		this.startpointtime = startpointtime;
		this.boardingpoint = boardingpoint;
		this.droppindpoint = droppindpoint;
		this.fare = fare;
		this.boardingtime = boardingtime;
		this.totaljourneytime = totaljourneytime;
	}
	public Long getRouteid() {
		return routeid;
	}
	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}
	public Long getBusid() {
		return busid;
	}
	public void setBusid(Long busid) {
		this.busid = busid;
	}
	public String getBusno() {
		return busno;
	}
	public void setBusno(String busno) {
		this.busno = busno;
	}
	public String getBustype() {
		return bustype;
	}
	public void setBustype(String bustype) {
		this.bustype = bustype;
	}
	public String getBusname() {
		return busname;
	}
	public void setBusname(String busname) {
		this.busname = busname;
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
	public Integer getTotalseats() {
		return totalseats;
	}
	public void setTotalseats(Integer totalseats) {
		this.totalseats = totalseats;
	}
	public Integer getAvailableseats() {
		return availableseats;
	}
	public void setAvailableseats(Integer availableseats) {
		this.availableseats = availableseats;
	}
	public Date getDateofjourney() {
		return dateofjourney;
	}
	public void setDateofjourney(Date dateofjourney) {
		this.dateofjourney = dateofjourney;
	}
	public Time getStartpointtime() {
		return startpointtime;
	}
	public void setStartpointtime(Time startpointtime) {
		this.startpointtime = startpointtime;
	}
	public String getBoardingpoint() {
		return boardingpoint;
	}
	public void setBoardingpoint(String boardingpoint) {
		this.boardingpoint = boardingpoint;
	}
	public String getDroppindpoint() {
		return droppindpoint;
	}
	public void setDroppindpoint(String droppindpoint) {
		this.droppindpoint = droppindpoint;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Time getBoardingtime() {
		return boardingtime;
	}
	public void setBoardingtime(Time boardingtime) {
		this.boardingtime = boardingtime;
	}
	public Time getTotaljourneytime() {
		return totaljourneytime;
	}
	public void setTotaljourneytime(Time totaljourneytime) {
		this.totaljourneytime = totaljourneytime;
	}
	
	
    
}
