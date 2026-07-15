package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	  public ResponseEntity<ErrorDetails> handleEmployeeNotFound(
			  EmployeeNotFoundException ex)
	  {
		    ErrorDetails ed=new ErrorDetails(
		    		LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),
		    		ex.getMessage());
		    return new ResponseEntity<>(ed,HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {

	        errors.put(error.getField(),
	                   error.getDefaultMessage());

	    });

	    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception ex) {

	    ErrorDetails ed = new ErrorDetails(
	            LocalDateTime.now(),
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            ex.getMessage());
	    return new ResponseEntity<>(ed,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
	    return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> handleEmailAlreadyExists(
	        EmailAlreadyExistsException ex) {

	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
	}
}
