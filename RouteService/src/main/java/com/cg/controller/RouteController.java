package com.cg.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.RouteAlreadyExistsException;
import com.cg.exception.RouteNotFoundException;

import com.cg.model.Route;
import com.cg.service.RouteServiceImpl;



import io.swagger.annotations.Api;

@RestController
@RequestMapping("/route")
@Api(value="Bus Booking Application")
public class RouteController {
	
	@Autowired
	RouteServiceImpl routeService;
	
	@GetMapping("/routedetails")
	public List<Route> getAllRouteDetails()
	{
		return routeService.getAllRouteDetails();
	}
	
	
	@GetMapping("/routedetails/{routeid}")
	public Route getRouteDetailsByRouteId(@PathVariable("routeid") Integer routeid ) throws RouteNotFoundException {
		
		return routeService.getRouteDetailsByRouteId(routeid);
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addroute")
	public List<Route> addRoute(@Valid @RequestBody Route route) throws RouteAlreadyExistsException
	{
		routeService.addRoute(route);
		return routeService.getAllRouteDetails();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/updateroutedetails/{routeid}")
	public Route updateBusDetails(@PathVariable("busid") Integer routeid,@Valid @RequestBody Route route) throws RouteNotFoundException
	{
		return routeService.updateRouteDetails(routeid,route);
	}
	
	
	@DeleteMapping("/deletebus/{routeid}")
	public List<Route> deleteRoute(@PathVariable("routeid")Integer routeid) throws RouteNotFoundException
	{
		routeService.deleteRoute(routeid);
		return routeService.getAllRouteDetails();
	}
	
	
	@GetMapping("/routedetails/board={src}/drop={dst}")
	public List<Route> getRouteDetailsBySourceAandDestination(@PathVariable("src")String boardingPoint,@PathVariable("dst")String droppingPoint) {
		return routeService.getRouteDetailsByBoardingandDropping(boardingPoint, droppingPoint);
	}
	    
	
}
