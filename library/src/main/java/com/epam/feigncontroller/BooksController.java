package com.epam.feigncontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.client.BookClient;
import com.epam.dto.BookDto;

@RestController
@RequestMapping(value = "/library")
public class BooksController {

	@Autowired
	BookClient bookClient;

	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getBooks() {
		return bookClient.getBooks();
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable long id) {
		return bookClient.getBook(id);
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id) {
		return bookClient.deleteBook(id);
	}

	@PostMapping("/books")
	public ResponseEntity<BookDto> insertBook(@RequestBody BookDto bookDto) {
		return bookClient.insertBook(bookDto);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable long id, @RequestBody BookDto bookDto) {
		return bookClient.updateBook(id, bookDto);
	}

}
