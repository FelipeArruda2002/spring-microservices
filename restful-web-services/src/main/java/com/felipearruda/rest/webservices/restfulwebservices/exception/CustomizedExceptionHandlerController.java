package com.felipearruda.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.felipearruda.rest.webservices.restfulwebservices.user.UserNotFoundException;

@RestControllerAdvice
public class CustomizedExceptionHandlerController {

	
	@ExceptionHandler(UserNotFoundException.class)
	public ExceptionErrorDetails handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
		return new ExceptionErrorDetails(exception.getMessage(), HttpStatus.NOT_FOUND.value(), request.getDescription(false), LocalDate.now());
	}
	
	@ExceptionHandler(Exception.class)
	public ExceptionErrorDetails handleAllException(Exception exception, WebRequest request) {
		return new ExceptionErrorDetails(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false), LocalDate.now());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException .class)
	public ExceptionErrorDetails handleMethodArgumentNotValidException (MethodArgumentNotValidException exception, WebRequest request) {
		return new ExceptionErrorDetails("Total Errors:" + exception.getErrorCount() + " First Error:" + exception.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST.value(), request.getDescription(false), LocalDate.now());
	}
	
}
