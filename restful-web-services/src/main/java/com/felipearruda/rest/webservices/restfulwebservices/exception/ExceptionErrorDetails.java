package com.felipearruda.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

public class ExceptionErrorDetails {

	private String message;
	private Integer statusCode;
	private String details;
	private LocalDate timeStamp;

	public ExceptionErrorDetails(String message, Integer statusCode, String details, LocalDate timeStamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.details = details;
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

}
