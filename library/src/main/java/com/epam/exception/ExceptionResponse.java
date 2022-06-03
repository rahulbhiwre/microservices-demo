package com.epam.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {

	String timestamp;
	String error;
	String status;
	String path;
}
