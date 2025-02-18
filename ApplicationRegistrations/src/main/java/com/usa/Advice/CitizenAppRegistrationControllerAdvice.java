package com.usa.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.usa.exception.InvalidSSNException;

@RestControllerAdvice
public class CitizenAppRegistrationControllerAdvice {

	@ExceptionHandler(InvalidSSNException.class)
	public ResponseEntity<String> handleInvalidSSN(InvalidSSNException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleInvalidSSN(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
