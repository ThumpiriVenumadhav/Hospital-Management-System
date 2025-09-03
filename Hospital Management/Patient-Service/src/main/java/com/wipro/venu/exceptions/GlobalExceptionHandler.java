package com.wipro.venu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(PatientNotFoundException ex)
	{
		ErrorMessage errrorMessage=new ErrorMessage(ex.getMessage(),"Patient_NOT_FOUND_EXCEPTON");
		return new ResponseEntity<>(errrorMessage,HttpStatus.NOT_FOUND);
	}
	


}
