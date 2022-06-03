package com.epam.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibraryDto {

	private long id;
	@Size(min = 2, max = 30, message = "username should be between 2 and 30 characters length")
	private String username;
	@NotNull
	@UniqueElements(message = "bookid must be unique")
	private long bookid;

}
