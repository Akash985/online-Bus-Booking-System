package com.cg.service;

import java.util.Date;
import java.util.List;

import com.cg.exception.BusAlreadyExistsException;
import com.cg.exception.BusNotFoundException;
import com.cg.model.Bus;

public interface BusService {
	
  public List<Bus> getAllBusDetails();
  public Bus getBusDetailsByBusId(Integer busId) throws BusNotFoundException;
  public Bus addBus(Bus bus) throws BusAlreadyExistsException;
  public Bus updateBusDetails(Integer busId,Bus bus) throws BusNotFoundException;
  public void deleteBusDetails(Integer busId) throws BusNotFoundException;
  public boolean checkBusAvailablityOnGivenDate(Integer busId,Date dateOfJourney);
}
