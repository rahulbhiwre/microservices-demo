package com.epam.dto;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {

	private long id;
	private String name;
	private String publisher;
	private String author;

}
