package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest req) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			errors.add(error.getDefaultMessage());
		});

		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(errors.toString());
		exRes.setStatus(HttpStatus.BAD_REQUEST.name());
		exRes.setTimestamp(new Date().toString());
		exRes.setPath(req.getDescription(false));

		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception exception, WebRequest webRequest) {
		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(exception.getMessage());
		exRes.setStatus(HttpStatus.BAD_REQUEST.name());
		exRes.setTimestamp(new Date().toString());
		exRes.setPath(webRequest.getDescription(true));
		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}

}
