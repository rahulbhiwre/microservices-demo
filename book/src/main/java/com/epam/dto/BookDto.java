package com.epam.dto;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

	private long id;
	@Size(min = 2, max = 30, message = "book name should be between 2 and 30 characters length")
	private String name;
	@Size(min = 2, max = 30, message = "book publisher name should be between 2 and 30 characters length")
	private String publisher;
	@Size(min = 2, max = 30, message = "book author name should be between 2 and 30 characters length")
	private String author;

}
