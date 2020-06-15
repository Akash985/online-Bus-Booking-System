package com.cg.controller;

import javax.persistence.RollbackException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.NoPassengerInBusException;
import com.cg.exception.PassengerNotFoundException;

@RestControllerAdvice
public class ExceptionController {
	

	@ExceptionHandler
	public ResponseEntity<String> handleBookingIdNotFoundException(BookingIdNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
		return new ResponseEntity<>("You can enter only numeric value --> "+ex.getMessage()+"not a numeric value",HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<String> handleNoPassengerInBusException(NoPassengerInBusException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handlePassengerNotFoundException(PassengerNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleRollbackException(RollbackException ex) {
		return new ResponseEntity<>(ex.getMessage()+"Please check the fileds you have entered",HttpStatus.NOT_ACCEPTABLE);
	}
}
