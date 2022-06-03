package com.epam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.BookDto;
import com.epam.entity.Book;
import com.epam.exception.BookServiceException;
import com.epam.repository.BookRepository;

@Service
public class BookService {

	public static final String ERROR_MESSAGE = "Book Not found with id: ";

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ModelMapper mapper;

	public BookDto insert(BookDto bookDto) {
		Book book = mapper.map(bookDto, Book.class);
		return mapper.map(bookRepository.save(book), BookDto.class);
	}

	public List<BookDto> findAll() {
		return mapper.map(bookRepository.findAll(), new TypeToken<List<BookDto>>() {
		}.getType());
	}

	public BookDto get(long id) {
		return mapper.map(bookRepository.findById(id).orElseThrow(() -> new BookServiceException(ERROR_MESSAGE, id)),
				BookDto.class);
	}

	public void delete(long id) {
		if (!bookRepository.existsById(id)) {
			throw new BookServiceException(ERROR_MESSAGE, id);
		}
		bookRepository.deleteById(id);
	}

	public BookDto update(BookDto bookDto, long id) {
		if (!bookRepository.existsById(id)) {
			throw new BookServiceException(ERROR_MESSAGE, id);
		}
		Book book = mapper.map(bookDto, Book.class);
		return mapper.map(bookRepository.save(book), BookDto.class);
	}
	
	

}
