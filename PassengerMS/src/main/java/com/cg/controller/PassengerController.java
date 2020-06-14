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
	
	
	
	
//	Create functionality
//	Add list of passenger
	//http://localhost:9090/passengerCtrl/create
	@PostMapping(value ="/create",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> createPassengers(@Valid @RequestBody List<Passenger> pssg){		
		return new ResponseEntity<List<Passenger>>(passengerService.addNewPassengerDetails(pssg),HttpStatus.OK);
	}
	
//	Read functionality
	
	//getAll passenger details
//	http://localhost:9090/passengerCtrl/getAllPassenger
	@GetMapping(value ="/getAllPassenger", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> fetchAllPassengerDetails(){
		return new ResponseEntity<List<Passenger>>( passengerService.getAllPassengerDetails(),HttpStatus.OK);// passengerService.getPassengerDetailsByBookingID(bId);
	}	
	
//	################
	//get passenger List By booking id
//	http://localhost:9090/passengerCtrl/fetchPassenger/bId=6001
	@GetMapping(value = "/fetchPassenger/bId={bkId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<List<Passenger>> fetchPassengerDetailsByBookingId(@PathVariable("bkId")Long bId) throws BookingIdNotFoundException{
		List<Passenger> pssgnList =  passengerService.getPassengerDetailsByBookingID(bId);
		return new ResponseEntity<List<Passenger>>(pssgnList,HttpStatus.OK);//instead of givin empty list in fallmethod return message
	}	
	
	
//	#####################
	//get passenger by Passenger Id.
//	http://localhost:9090/passengerCtrl/fetchPassenger/pssgnNo=1002
	@GetMapping(value = "/fetchPassenger/pssgnNo={pNo}", produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenPassengerIdNotFound")
	ResponseEntity<Passenger> fetchPassengerDetailsByPassengerId(@PathVariable("pNo")Long pNo) throws PassengerNotFoundException{
		Passenger pssgn=passengerService.getPassengerDetailByPassengerId(pNo);
		return new ResponseEntity<Passenger>(pssgn,HttpStatus.OK);// passengerService.getPassengerDetailsByBookingID(bId);
	}
	
	
//	#####################
	//get passenger list by passenger Name--->for admin only(here we will get passenger by name and here we are using list because 1 person van have 2 or more bookings in different bus on different date )
//	http://localhost:9090/passengerCtrl/fetchPassenger/pName=Akash Yadav
	@GetMapping(value ="/fetchPassenger/pName={name}", produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenPassengerNameNotFound")
	ResponseEntity<List<Passenger>> fetchPassengerDetailsByName(@PathVariable("name")String pName) throws PassengerNotFoundException{
		List<Passenger> pssgnList =  passengerService.getPassengerDetailByName(pName);
		return new ResponseEntity<List<Passenger>>(pssgnList,HttpStatus.OK);// passengerService.getPassengerDetailsByBookingID(bId);
	}
	
	
	//get passenger list by Bus id--->for admin only and internal use 
//	http://localhost:9090/passengerCtrl/fetchPassengerlist/busId=5001
	@GetMapping(value ="/fetchPassengerlist/busId={bID}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> fetchAllPassengerDetailsByBusId(@PathVariable("bID")Long busId){
		return new ResponseEntity<List<Passenger>> (passengerService.getPassengerDetailsBybusId(busId),HttpStatus.OK);//passengerService.getPassengerDetailByName(pName);// passengerService.getPassengerDetailsByBookingID(bId);
	}
	
	//get passenger by bus id and seat no. --->for admin only
//	http://localhost:9090/passengerCtrl/fetchPassengerlist/busId=5002/seatNo=6
	@GetMapping(value ="/fetchPassengerlist/busId={bID}/seatNo={sNo}", produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Passenger>> fetchAllPassengerDetailsByBusIdAndSeatNo(@PathVariable("bID")Long busId,@PathVariable("sNo")Integer seatNo){
		return new ResponseEntity<List<Passenger>>(passengerService.getPassengerDetailsBybusIdAndSeatNo(busId, seatNo),HttpStatus.OK);//passengerService.getPassengerDetailByName(pName);// passengerService.getPassengerDetailsByBookingID(bId);
	}
		
	//***********get passenger list by user id can be done via Booking service********
	
	
	
//	Update functionality
	//update passenger details by booking Id
//	@PutMapping(value ="/updatePassengerlist/bid= {bkId}",consumes = MediaType.APPLICATION_JSON_VALUE,
//			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
////	List<Passenger> createPassengerDetailsByBookingId(@RequestBody List<Passenger> pssg){
////		return passengerService.addNewPassengerDetails(pssg);
////	}
	
	
//	#####################
	//update passenger details by Ticket No.--only done by admin at busoffice
	@PutMapping(value ="/updatePassenger/pssgnNo={pNo}",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenPassengerIdNotFound")
	ResponseEntity<Passenger> updatePassengerDetailsByPassengerId(@PathVariable("pNo")Long pNo,@Valid @RequestBody Passenger pssg) throws PassengerNotFoundException{
		Passenger pssgn=passengerService.updatePassengerDetailByPassengerId(pNo, pssg);
		return new ResponseEntity<Passenger>(pssgn,HttpStatus.OK);
	}
	
	
//	#####################
//	Cancel functionality
	//update ticket status of passenger to rejected by booking Id
	@PutMapping(value ="/cancelPassenger/bkId={bId}",
			headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod="whenBookingIdNotFound")
	ResponseEntity<List<Passenger>> cancelPassengerlistBookingsByBookingId(@PathVariable("bId")Long bookId) throws BookingIdNotFoundException{
		List<Passenger> cancelledPssgnList =passengerService.updatePassengerBookingStatusToRejectedByBookingID(bookId);
		return  new ResponseEntity<List<Passenger>>(cancelledPssgnList,HttpStatus.OK);
		}
	
	
	
	//update ticket status of passenger to rejected by ticket No.-->for admin use only(if some one wants that from there booking ,
	//they want to remove one passenger--then they can approach admin at Booking office)
	@PutMapping(value ="/cancelPassenger/pssgNo={pNo}",consumes = MediaType.APPLICATION_JSON_VALUE,
	headers="Accept=application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	@HystrixCommand(fallbackMethod = "whenPassengerIdNotFound")
	ResponseEntity<Passenger> cancelPassengerBookingByPassengerId(@PathVariable("pNo")Long pNo) throws PassengerNotFoundException{
		Passenger pssgn=passengerService.updatePassengerBookingStatusToRejectedByPassengerId(pNo);
		return new ResponseEntity<Passenger>(pssgn,HttpStatus.OK);
	}
	
	
//	delete functionality(delete is not added as it can erase our record who has canceled)
	
	public  ResponseEntity<List<Passenger>> whenBookingIdNotFound(Long bId)
	{
		List<Passenger> pssgList = new ArrayList<Passenger>();///returning empty Lists
		return new ResponseEntity<List<Passenger>>(pssgList, HttpStatus.NOT_FOUND);
	}
	
	
	public  ResponseEntity<Passenger> whenPassengerIdNotFound(Long bId)
	{
		Passenger pssgn = new Passenger(0l,0l,0l,null,null,null,null,null);
		return new ResponseEntity<Passenger>(pssgn, HttpStatus.NOT_FOUND);
	}
	
	
	public  ResponseEntity<List<Passenger>> whenPassengerNameNotFound(String pName)
	{
		List<Passenger> pssgList = new ArrayList<Passenger>();///returning empty Lists
		return new ResponseEntity<List<Passenger>>(pssgList, HttpStatus.NOT_FOUND);
	}
	
}
