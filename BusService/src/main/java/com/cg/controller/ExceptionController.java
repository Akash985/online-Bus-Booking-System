package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.BusAlreadyExistsException;
import com.cg.exception.BusNotFoundException;

@RestControllerAdvice
public class ExceptionController {
	

	@ExceptionHandler
	public ResponseEntity<String> handleBusNotFoundException(BusNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleBusAlreayExistsException(BusAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
