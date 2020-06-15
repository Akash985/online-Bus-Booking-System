package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Passenger;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.PassengerNotFoundException;
import com.cg.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService{
	
	@Autowired
	private PassengerRepository passengerRepo;

	@Override
	public List<Passenger> addNewPassengerDetails(List<Passenger> pssgnList) {
		for (Passenger passenger : pssgnList) {
			passenger.setBookingStatus("Accepted");
		}
		List<Passenger> savedPssgnList =passengerRepo.saveAll(pssgnList);		
		return savedPssgnList;
	}
//	
	
	//read
	
	@Override
	public List<Passenger> getAllPassengerDetails() {	
		//list is empty exception
		return passengerRepo.findAll();								//MP
	}
	
	@Override
	public List<Passenger> getPassengerDetailsByBookingID(Long bookingId) throws BookingIdNotFoundException {
		List<Passenger> pssgnList = passengerRepo.findAllByBookingId(bookingId);
		
		//booking id not found exception
		if(pssgnList.isEmpty()) {
			throw new BookingIdNotFoundException("This booking id is not present in passenger service");
		}else {
			return pssgnList;
		}
	}

	@Override
	public Passenger getPassengerDetailByPassengerId(Long passengerId) throws PassengerNotFoundException {	
		//passengerIdNotFoundException
		Passenger passenger = passengerRepo.findById(passengerId).get();
		if(passenger==null) {
			throw new PassengerNotFoundException("Passenger Id does not exist");
		}else {
		return passenger;
		}
	}
	

	@Override
	public List<Passenger> getPassengerDetailByName(String pssgnName) throws PassengerNotFoundException {
		//PaassengerNAme Not FoundException
		List<Passenger> pssgnList = passengerRepo.findAllByPassengerName(pssgnName);
		if(pssgnList.isEmpty()) {
			throw new PassengerNotFoundException(pssgnName +" not present in passenger list");
		}else {
		return pssgnList;
		}
	}

	@Override
	public List<Passenger> getPassengerDetailsBybusId(Long busId) {
		//no passenger in bus exception								//MP
		return passengerRepo.findAllByBusId(busId);
	}

	@Override
	public List<Passenger> getPassengerDetailsBybusIdAndSeatNo(Long busId, Integer seatNo) {
		return passengerRepo.findAllByBusIdAndSeatNo(busId, seatNo);		//MP
	}


	@Override
	public Passenger updatePassengerDetailByPassengerId(Long passengerId, Passenger pssgn) throws PassengerNotFoundException {
		Passenger tempPassenger = passengerRepo.findById(passengerId).get();
		//PassengerId Not found exception
		if(tempPassenger==null) {
			throw new PassengerNotFoundException("Passenger Id does not exist");
		}else {
			tempPassenger.setBusId(pssgn.getBusId());
			tempPassenger.setPassengerName(pssgn.getPassengerName());
			tempPassenger.setAge(pssgn.getAge());
			tempPassenger.setGender(pssgn.getGender());
			return passengerRepo.save(pssgn);
		}
		
	}

	@Override
	public Passenger updatePassengerBookingStatusToRejectedByPassengerId(Long passengerId) throws PassengerNotFoundException {
//		passenger Id not found Exception
		Passenger pssgn= passengerRepo.findById(passengerId).get();
		if(pssgn==null) {
			throw new PassengerNotFoundException("Passenger Id does not exist");
		}else {
			pssgn.setBookingStatus("Rejected");
			return passengerRepo.save(pssgn);
		}
		
	}

	@Override
	public List<Passenger> updatePassengerBookingStatusToRejectedByBookingID(Long bookingId) throws BookingIdNotFoundException {
		 //Booking Id Not Found Exception
		List<Passenger> pssgnListTemp = passengerRepo.findAllByBookingId(bookingId);
		if(pssgnListTemp==null) {
			throw new BookingIdNotFoundException("This booking id is not present in passenger service");
		}else {
		for(int i=0;i<pssgnListTemp.size();i++) {
			pssgnListTemp.get(i).setBookingStatus("Rejected");
			passengerRepo.save(pssgnListTemp.get(i));
		}
		return passengerRepo.findAllByBookingId(bookingId);
		}
	}

	
	
	
	
	
	
	

}
