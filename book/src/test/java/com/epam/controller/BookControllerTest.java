package com.epam.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.MockMvc;
import com.epam.dto.BookDto;
import com.epam.exception.BookServiceException;
import com.epam.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ModelMapper modelMapper;

	@Mock
	private BookService bookService;

	@InjectMocks
	BookController bookController;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void testGetBook() {
		BookDto bookDto = new BookDto();
		when(bookService.get(1L)).thenReturn(bookDto);
		ResponseEntity<BookDto> responseEntity = bookController.get(1L);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testGetBooks() {
		List<BookDto> books = new ArrayList<>();
		when(bookService.findAll()).thenReturn(books);
		ResponseEntity<List<BookDto>> responseEntity = bookController.findAll();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testDeleteBook() {
		bookService.delete(1);
		ResponseEntity<?> responseEntity = bookController.delete(1L);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testInsertBook() {
		BookDto bookDto = new BookDto();
		when(bookService.insert(bookDto)).thenReturn(bookDto);
		ResponseEntity<BookDto> responseEntity = bookController.insert(bookDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
	}

	@Test
	void updateBook() {
		BookDto bookDto = new BookDto();
		when(bookService.update(bookDto, bookDto.getId())).thenReturn(bookDto);
		ResponseEntity<BookDto> responseEntity = bookController.update(bookDto, bookDto.getId());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
	}

	@Test
	void testInsertInvalidBook() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor("a");
		when(bookService.insert(bookDto)).thenReturn(bookDto);
		String bookInJson = mapper.writeValueAsString(bookDto);
		mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void getBookByIdTypeMismatch() throws Exception { 
		BookDto bookDto = new BookDto();
		when(bookService.get(1L)).thenReturn(bookDto);
		String bookInJson = mapper.writeValueAsString(bookDto);
		mockMvc.perform(get("/books/" + bookDto.getAuthor()).contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void booknotfound() throws Exception {
		doThrow(BookServiceException.class).when(bookService).get(56L);
		mockMvc.perform(get("/books/{id}", 56L)).andExpect(status().isBadRequest());
	}
	

}

//when(bookClient.getBooks()).thenThrow(new Exception());
//mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9003/library/books/30")
//		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
//doThrow(BookControllerException.class).when(bookService.get(56L));
//when(bookService.get(56)).thenThrow(new BookControllerException());
//mockMvc.perform(get("books/56")).andExpect(status().isBadRequest());