package com.epam.feigncontroller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.client.BookClient;
import com.epam.dto.BookDto;
import com.epam.exception.ControllerExceptionHandler;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@InjectMocks
	BooksController booksController;

	@Mock
	BookClient bookClient;

	@Mock
	LibraryService libraryService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void testGetBooks() {
		booksController.getBooks();
		verify(bookClient, times(1)).getBooks();
	}

	@Test
	void testGetBook() {
		booksController.getBook(1L);
		verify(bookClient, times(1)).getBook(1L);
	}

	@Test
	void testDeleteBook() {
		booksController.deleteBook(1L);
		verify(bookClient, times(1)).deleteBook(1L);
	}

	@Test
	void testInsertBook() {
		BookDto bookDto = new BookDto();
		booksController.insertBook(bookDto);
		verify(bookClient, times(1)).insertBook(bookDto);
	}

	@Test
	void testUpdateBook() {
		BookDto bookDto = new BookDto();
		booksController.updateBook(bookDto.getId(), bookDto);
		verify(bookClient, times(1)).updateBook(bookDto.getId(),bookDto);
	}

	@Test
	void getBookByIdTypeMismatch() throws Exception { 
		BookDto bookDto = new BookDto();
		when(bookClient.getBook(1L)).thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.BAD_REQUEST));
		String bookInJson = mapper.writeValueAsString(bookDto);
		mockMvc.perform(get("/library/books/" + bookDto.getAuthor()).contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testInsertInvalidBook() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor("a");
		when(bookClient.insertBook(bookDto)).thenReturn(new ResponseEntity<BookDto>(bookDto, HttpStatus.BAD_REQUEST));
		String bookInJson = mapper.writeValueAsString(bookDto);
		mockMvc.perform(post("/library/books").contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

}
