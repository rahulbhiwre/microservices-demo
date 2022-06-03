package com.epam.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.BookDto;

@FeignClient(name = "User Service")
public interface BookClient {

	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> getBooks();

	@GetMapping("/books/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable long id);

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id);

	@PostMapping("/books")
	public ResponseEntity<BookDto> insertBook(@RequestBody BookDto bookDto);

	@PutMapping("/books/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable long id, @RequestBody BookDto bookDto);

}