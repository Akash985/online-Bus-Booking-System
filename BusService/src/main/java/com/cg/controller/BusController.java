package com.cg.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.cg.exception.BusAlreadyExistsException;
import com.cg.exception.BusNotFoundException;
import com.cg.model.Bus;
import com.cg.service.BusServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bus")
@Api(value="Bus Booking Application")
public class BusController {
	
	@Autowired
	BusServiceImpl busService;
	
	

	@GetMapping("/busdetails")
	public List<Bus> getAllBusDetails()
	{
		return busService.getAllBusDetails();
	}
	
	
	@GetMapping("/busdetails/{busid}")
	public Bus getBusDetailsByBusId(@PathVariable("busid") Integer busId ) throws BusNotFoundException {
		
		return busService.getBusDetailsByBusId(busId);
		
	}
	
	
	@GetMapping("/busdetails/checkAvailability/busId={bid}/dateOfJrny={dt}")
	public boolean checkBusAvailabilityBybusIdAndJourneyDate(@PathVariable("bid")Integer busId,@PathVariable("dt")String dateOfJourney) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = sdf.parse(dateOfJourney);
		return busService.checkBusAvailablityOnGivenDate(busId, dt);
	}
	
	@PostMapping("/addbus")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Bus> addBus(@Valid @RequestBody Bus bus) throws BusAlreadyExistsException
	{
	    busService.addBus(bus);
	    return busService.getAllBusDetails();
	}
	

	@PutMapping("/updatebusdetails/{busid}")
	public List<Bus> updateBusDetails(@PathVariable("busid") Integer busId,@RequestBody Bus bus) throws BusNotFoundException
	{
		busService.updateBusDetails(busId, bus);
		return busService.getAllBusDetails();
	}
	
	@DeleteMapping("/deletebus/{busid}")
	public List<Bus> deletebus(@PathVariable("busid")Integer busId) throws BusNotFoundException
	{
		busService.deleteBusDetails(busId);
		return busService.getAllBusDetails();
	}
}