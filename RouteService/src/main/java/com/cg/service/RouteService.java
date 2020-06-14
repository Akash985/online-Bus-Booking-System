package com.cg.service;

import java.util.List;

import com.cg.exception.RouteAlreadyExistsException;
import com.cg.exception.RouteNotFoundException;
import com.cg.model.Route;

public interface RouteService {
	
	public List<Route> getAllRouteDetails();
	public Route getRouteDetailsByRouteId(Integer routeId) throws RouteNotFoundException;
	public Route addRoute(Route route) throws RouteAlreadyExistsException;
	public Route updateRouteDetails(Integer routeId,Route route) throws RouteNotFoundException;
	public void deleteRoute(Integer routeId) throws RouteNotFoundException;
	public List<Route> getRouteDetailsByBoardingandDropping(String boardingPoint,String droppingPoint);
	

}
