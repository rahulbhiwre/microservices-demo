package com.epam.exception;

public class BookServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookServiceException(String errorMessgae,long id) {
		super(errorMessgae + id);
	}

}
