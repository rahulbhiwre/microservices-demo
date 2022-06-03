package com.epam.feigncontroller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.client.BookClient;
import com.epam.client.UserClient;
import com.epam.dto.BookDto;
import com.epam.dto.UserDto;
import com.epam.entity.Library;
import com.epam.service.LibraryService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class LibraryControllerTest {

	@InjectMocks
	LibraryController libraryController;

	@MockBean
	UserClient userClient;

	@MockBean
	BookClient bookClient;

	@MockBean
	LibraryService libraryService;

	private static UserDto userDto = new UserDto();
	private static BookDto bookDto = new BookDto();

	@BeforeAll
	static void addData() {
		userDto.setEmail("qwe@gmail.com");
		userDto.setName("abc");
		userDto.setUsername("qwe");

		bookDto.setName("abc");
		bookDto.setPublisher("abc");
		bookDto.setAuthor("abc");

	}

	@Test
	void testInsert() throws Exception {

		given(userClient.getUser("abc")).willReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.OK));
		given(bookClient.getBook(1L)).willReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.OK));
		libraryController.insert("abc", 1L);
		verify(libraryService, times(1)).issueBook("abc", 1L);
	}

	@Test
	void testDelete() {
		given(userClient.getUser("abc")).willReturn(new ResponseEntity<UserDto>(userDto, HttpStatus.OK));
		given(bookClient.getBook(1L)).willReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.OK));
		libraryController.delete("abc", 1L);
		verify(libraryService, times(1)).releaseBook("abc", 1L);

	}

}
