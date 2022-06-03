package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.BDDMockito.given;

import com.epam.dto.BookDto;
import com.epam.entity.Book;
import com.epam.exception.BookServiceException;
import com.epam.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	BookService bookService;

	@MockBean
	BookRepository bookRepository;

	@Mock
	private ModelMapper mapper;

	@Test
	void testInsert() {
		BookDto bookDto = new BookDto();
		Book book = mapper.map(bookDto, Book.class);
		bookService.insert(bookDto);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testFindAll() {
		bookService.findAll();
		verify(bookRepository, atLeast(1)).findAll();
	}

	@Test
	void testGet() {
		
		Optional<Book> book = Optional.ofNullable(new Book());
		given(bookRepository.findById(1L)).willReturn(book);
		bookService.get(1L);
		verify(bookRepository, atLeast(1)).findById(1L);
		
	}

	@Test
	void testDelete() {
		Optional<Book> book = Optional.ofNullable(new Book());
		given(bookRepository.existsById(1L)).willReturn(true);
		bookService.delete(1L);
		verify(bookRepository, times(1)).deleteById(1L);
	}

	@Test
	void testUpdate() {
		BookDto bookDto = new BookDto();
		Book book = mapper.map(bookDto, Book.class);
		given(bookRepository.existsById(1L)).willReturn(true);
		bookService.update(bookDto,1L);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	void testDeleteException() throws Exception {

		Optional<Book> book = Optional.ofNullable(new Book());
		given(bookRepository.existsById(1L)).willReturn(false);

		BookServiceException thrown = assertThrows(BookServiceException.class, () -> bookService.delete(1L),
				"Book Not found with id: " + 1L);
		assertEquals("Book Not found with id: " + 1L, thrown.getMessage());

	}

	@Test
	void testUpdateException() throws Exception {

		Optional<Book> book = Optional.ofNullable(new Book());
		BookDto bookDto = mapper.map(book, BookDto.class);
		given(bookRepository.existsById(1L)).willReturn(false);

		BookServiceException thrown = assertThrows(BookServiceException.class, () -> bookService.update(bookDto,1L),
				"Book Not found with id: " + 1L);
		assertEquals("Book Not found with id: " + 1L, thrown.getMessage());

	}
	
	@Test
	void testGetQuestionException() {
		BookServiceException thrown = assertThrows(BookServiceException.class, () -> bookService.get(1L),
				"Book Not found with id: " + 1L);
		assertEquals("Book Not found with id: " + 1L, thrown.getMessage());
	}
	
}
