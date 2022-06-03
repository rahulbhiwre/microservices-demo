package com.epam.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import feign.FeignException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = FeignException.class)
	public ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest req) {
		ExceptionResponse exRes = new ExceptionResponse();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String body = ex.getMessage();  // change here
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");
		return new ResponseEntity<>(body, httpHeaders, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception exception, WebRequest webRequest) {

		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(exception.getMessage());
		exRes.setStatus(HttpStatus.NOT_FOUND.name());
		exRes.setTimestamp(new Date().toString());
		exRes.setPath(webRequest.getDescription(false));
		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}

}
