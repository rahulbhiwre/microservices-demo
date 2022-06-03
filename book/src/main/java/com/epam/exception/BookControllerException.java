package com.epam.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.PersistentObjectException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class BookControllerException {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionResponse> handleQuestionNotFoundException(Exception ex,
			WebRequest req) {
		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(ex.getMessage());
		exRes.setStatus(HttpStatus.NOT_FOUND.name());
		exRes.setTimestamp(new Date().toString());
		exRes.setPath(req.getDescription(true));
		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}


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

	@ExceptionHandler(value = BookServiceException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(BookServiceException ex,
			WebRequest req) {

		ExceptionResponse exRes = new ExceptionResponse();
		exRes.setError(ex.getMessage());
		exRes.setStatus(HttpStatus.BAD_REQUEST.name());
		exRes.setTimestamp(new Date().toString());
		exRes.setPath(req.getDescription(false));

		return new ResponseEntity<>(exRes, HttpStatus.BAD_REQUEST);
	}

}
