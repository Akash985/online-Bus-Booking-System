package com.cg.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.BusRepository;
import com.cg.exception.BusAlreadyExistsException;
import com.cg.exception.BusNotFoundException;
import com.cg.model.Bus;

@Service
public class BusServiceImpl implements BusService {
	
	@Autowired
	BusRepository busRepo;
	
	@Override
	public List<Bus> getAllBusDetails()
	{
		
		return busRepo.findAll();
	}
	
    @Override
	public Bus getBusDetailsByBusId(Integer busId) throws BusNotFoundException
	{
		Optional<Bus>  optbus=busRepo.findById(busId);
		if(optbus.isPresent()) {
			return optbus.get();
		}
		else
		{
			throw new BusNotFoundException("Bus not found for fiven busId");
		}
		
	}
    
    @Override
	public Bus addBus(Bus bus) throws BusAlreadyExistsException {
    	if (busRepo.existsById(bus.getBusId()))
    	{
    		throw new BusAlreadyExistsException("Bus already exists for given BusId");
    	}
    	else {
		return busRepo.save(bus);
	    }
    }
    
	@Override
	public Bus updateBusDetails(Integer busId,Bus bus) throws BusNotFoundException
	{
		Optional<Bus> optbus=busRepo.findById(busId);
		if(optbus.isPresent())
		{
			Bus b=optbus.get();
			b.setBusNo(bus.getBusNo());
			b.setBusType(bus.getBusType());
			b.setBusName(bus.getBusName());
			b.setSource(bus.getSource());
			b.setDestination(bus.getDestination());
			b.setTotalSeats(bus.getTotalSeats());
			b.setAvailableSeats(bus.getAvailableSeats());
			b.setDateOfJourney(bus.getDateOfJourney());
			b.setStartPointTime(bus.getStartPointTime());
			return busRepo.save(b);
			
		}
		else
		{
			throw new BusNotFoundException("Bus not found for fiven busId");
		}
	}   
	
		@Override
		public void deleteBusDetails(Integer busId) throws BusNotFoundException
		{
			Optional<Bus> optbus=busRepo.findById(busId);
			if(optbus.isPresent()) {
				busRepo.deleteById(busId);
			}
			else 
			{
				throw new BusNotFoundException("Bus not found for fiven busId");
			}
			
		}
		
		public boolean checkBusAvailablityOnGivenDate(Integer busId,Date dateOfJourney) {
			if(busRepo.findByBusIdAndDateOfJourney(busId, dateOfJourney)==null) {
				return false;
			}
			else {
				return true;
			}
			
	}
}
