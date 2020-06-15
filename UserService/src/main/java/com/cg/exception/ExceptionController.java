package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {
	

	@ExceptionHandler
	public ResponseEntity<String> handleBusNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
