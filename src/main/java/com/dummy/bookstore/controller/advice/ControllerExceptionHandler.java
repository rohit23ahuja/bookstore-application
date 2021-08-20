package com.dummy.bookstore.controller.advice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dummy.bookstore.exception.ErrorItem;
import com.dummy.bookstore.exception.ErrorResponse;
import com.dummy.bookstore.exception.ResourceNotFoundException;
import com.dummy.bookstore.exception.SoldOutException;
import com.dummy.bookstore.exception.StaleException;

@RestControllerAdvice
public class ControllerExceptionHandler {
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
		ErrorResponse errors = new ErrorResponse();
		for (ConstraintViolation violation : e.getConstraintViolations()) {
			ErrorItem error = new ErrorItem();
			error.setCode(violation.getMessageTemplate());
			error.setMessage(violation.getMessage());
			errors.addError(error);
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorItem> handle(ResourceNotFoundException e) {
		ErrorItem error = new ErrorItem();
		error.setMessage(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SoldOutException.class)
	public ResponseEntity<ErrorItem> handle(SoldOutException e) {
		ErrorItem error = new ErrorItem();
		error.setMessage(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StaleException.class)
	public ResponseEntity<ErrorItem> handle(StaleException e) {
		ErrorItem error = new ErrorItem();
		error.setMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
