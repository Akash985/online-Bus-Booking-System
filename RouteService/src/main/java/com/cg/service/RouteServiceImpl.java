package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dao.RouteRepository;
import com.cg.exception.RouteAlreadyExistsException;
import com.cg.exception.RouteNotFoundException;
import com.cg.model.Route;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	RouteRepository routeRepo;
	
	@Override
	public List<Route> getAllRouteDetails()
	{
		return routeRepo.findAll();
	}
    @Override
	public Route getRouteDetailsByRouteId(Integer routeId) throws RouteNotFoundException
	{
		Optional<Route>  optroute=routeRepo.findById(routeId);
		if(optroute.isPresent()) {
			return optroute.get();
		}
		else
		{
			throw new RouteNotFoundException("Route not found for given routeid");
		}
		
	}
    @Override
	public Route addRoute(Route route) throws RouteAlreadyExistsException {
    	if (routeRepo.existsById(route.getRouteId()))
    	{
    		throw new RouteAlreadyExistsException("Bus already exists for given BusId");
    	}
    	else {
		return routeRepo.save(route);
	    }


	}
    @Override
	public Route updateRouteDetails(Integer routeId,Route route) throws RouteNotFoundException
	{
		Optional<Route> optroute=routeRepo.findById(routeId);
		if(optroute.isPresent())
		{
			Route r=optroute.get();
			r.setBusId(route.getBusId());
			r.setBoardingPoint(route.getBoardingPoint());
			r.setDroppingPoint(route.getDroppingPoint());
			r.setFare(route.getFare());
			r.setBoardingPoint(route.getBoardingPoint());
			r.setTotalJourneyTime(route.getTotalJourneyTime());
			return routeRepo.save(r);
			
		}
		else
		{
			throw new RouteNotFoundException("Route not found for given routeid");
		}
	}
		@Override
		public void deleteRoute(Integer routeId) throws RouteNotFoundException
		{
			Optional<Route> optroute=routeRepo.findById(routeId);
			if(optroute.isPresent()) {
				routeRepo.deleteById(routeId);
			}
			else 
			{
				throw new RouteNotFoundException("Route not found for given routeid");
			}
			
		}
		public List<Route> getRouteDetailsByBoardingandDropping(String boardingPoint,String droppingPoint){
			return routeRepo.findAllByBoardingPointAndDroppingPoint(boardingPoint, droppingPoint);
			
		}

}
