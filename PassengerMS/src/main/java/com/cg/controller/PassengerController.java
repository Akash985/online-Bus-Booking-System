package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.PassengerDto;
import com.cg.entity.Passenger;
import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.PassengerNotFoundException;
import com.cg.service.PassengerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value ="/passengerCtrl")
public class PassengerController {
	
	@Autowired
	private PassengerService passengerService;
	
	

	@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> createPassengers(@Valid @RequestBody List<Passenger> pssg){		
		return new ResponseEntity<List<Passenger>>(passengerService.addNewPassengerDetails(pssg),HttpStatus.OK);
	}
	

	@GetMapping(value ="/getAllPassenger", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> fetchAllPassengerDetails(){
		return new ResponseEntity<List<Passenger>>( passengerService.getAllPassengerDetails(),HttpStatus.OK);
	}	
	

	@GetMapping(value = "/fetchPassenger/bId={bkId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<List<Passenger>> fetchPassengerDetailsByBookingId(@PathVariable("bkId")Long bId) throws BookingIdNotFoundException{
		List<Passenger> pssgnList =  passengerService.getPassengerDetailsByBookingID(bId);
		return new ResponseEntity<List<Passenger>>(pssgnList,HttpStatus.OK);//instead of givin empty list in fallmethod return message
	}	
	

	@GetMapping(value = "/fetchPassenger/pssgnNo={pNo}", produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenPassengerIdNotFound")
	ResponseEntity<Passenger> fetchPassengerDetailsByPassengerId(@PathVariable("pNo")Long pNo) throws PassengerNotFoundException{
		Passenger pssgn=passengerService.getPassengerDetailByPassengerId(pNo);
		return new ResponseEntity<Passenger>(pssgn,HttpStatus.OK);
	}
	
	

	@PutMapping(value ="/cancelPassenger/bkId={bId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<List<Passenger>> cancelPassengerlistBookingsByBookingId(@PathVariable("bId")Long bookId) throws BookingIdNotFoundException{
		List<Passenger> cancelledPssgnList =passengerService.updatePassengerBookingStatusToRejectedByBookingID(bookId);
		return  new ResponseEntity<List<Passenger>>(cancelledPssgnList,HttpStatus.OK);
		}
	
	
	

	
	
//	-------------------------------------------------------------------------------------------------------------------------------------------------------
//	-----------------------------------------------------Fall Back methods---------------------------------------------------------------------------------
	
	public  ResponseEntity<List<Passenger>> whenBookingIdNotFound(Long bId)
	{
		List<Passenger> pssgList = new ArrayList<Passenger>();
		return new ResponseEntity<List<Passenger>>(pssgList, HttpStatus.NOT_FOUND);
	}
	
	
	public  ResponseEntity<Passenger> whenPassengerIdNotFound(Long bId)
	{
		Passenger pssgn = new Passenger(0l,0l,0l,null,null,null,null,null);
		return new ResponseEntity<Passenger>(pssgn, HttpStatus.NOT_FOUND);
	}
	
	
	public  ResponseEntity<List<Passenger>> whenPassengerNameNotFound(String pName)
	{
		List<Passenger> pssgList = new ArrayList<Passenger>();
		return new ResponseEntity<List<Passenger>>(pssgList, HttpStatus.NOT_FOUND);
	}
	
}
