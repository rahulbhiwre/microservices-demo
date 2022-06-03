package com.epam.other;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epam.dto.BookDto;
import com.epam.entity.Book;

class TestDtoAndEntity {

	@Test
	void testDto() {
		BookDto bookDto = new BookDto();
		bookDto.setId(0);
		bookDto.setAuthor("asdf");
		bookDto.setName("qer");
		bookDto.setPublisher("meee");

		bookDto.getId();
		bookDto.getName();
		bookDto.getAuthor();
		bookDto.getPublisher();
		
		bookDto.toString();
	}

	@Test
	void testEntity() {
		Book Book = new Book();
		Book.setId(0);
		Book.setAuthor("asdf");
		Book.setName("qer");
		Book.setPublisher("meee");

		Book.getId();
		Book.getName();
		Book.getAuthor();
		Book.getPublisher();
		
		Book.toString();
	}
	
}
