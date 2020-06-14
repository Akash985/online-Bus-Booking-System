package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.BookingIdNotFoundException;
import com.cg.exception.BookingIsAlreadyRejectedException;

@RestControllerAdvice
public class ExceptionController {
	

	@ExceptionHandler
	public ResponseEntity<String> handleBookingIdNotFoundException(BookingIdNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleBookingIsAlreadyRejectedException(BookingIsAlreadyRejectedException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
