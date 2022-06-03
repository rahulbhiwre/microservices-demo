package com.epam.other;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.entity.Library;

class TestDtoAndEntity {

	@Test
	void testBookDto() {
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
	void testUserDto() {
		UserDto userDto = new UserDto();
		userDto.setUsername("abcd");
		userDto.setEmail("qwer");
		userDto.setName("qwer");
		userDto.getEmail();
		userDto.getName();
		userDto.getUsername();

		userDto.toString();
	}

	@Test
	void testLibraryDto() {
		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setBookid(1L);
		libraryDto.setId(0);
		libraryDto.setUsername("asdf");
		libraryDto.getBookid();
		libraryDto.getId();
		libraryDto.getUsername();
	}

	@Test
	void testEntity() {
		Library Library = new Library();
		Library.setBookid(1L);
		Library.setId(0);
		Library.setUsername("asdf");
		Library.getBookid();
		Library.getId();
		Library.getUsername();

	}

}
