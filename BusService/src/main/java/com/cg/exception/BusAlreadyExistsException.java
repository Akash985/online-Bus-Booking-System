package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class BusAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BusAlreadyExistsException(String msg)
	{
		super(msg);
	}

}
