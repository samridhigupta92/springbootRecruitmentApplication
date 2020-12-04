package com.assignment.models;

import java.util.Date;

public class ErrorDetails {
	
	private String errorMessage;
	private String details;
	private Date timestamp;
	public ErrorDetails() {
		super();
		
	}
	
	public ErrorDetails(Date timestamp,String errorMessage, String details) {
		super();
		this.timestamp=timestamp;
		this.errorMessage = errorMessage;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
