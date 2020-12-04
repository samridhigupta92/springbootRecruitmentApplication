package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1168065362302980157L;
	
	public DataNotFoundException(String message)
	{
		super(message);
	}
	
	

}
