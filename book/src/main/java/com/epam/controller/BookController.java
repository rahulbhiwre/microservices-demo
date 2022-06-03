package com.epam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.BookDto;
import com.epam.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	Environment env;
	
	@Operation(description = "this stores the book")
	@ApiResponse(responseCode = "201", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@PostMapping("/books")
	public ResponseEntity<BookDto> insert(@RequestBody @Valid BookDto bookDto) {
		return new ResponseEntity<>(bookService.insert(bookDto), HttpStatus.ACCEPTED);
	}

	@Operation(description = "this Fetches all the books")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@GetMapping("/books")
	public ResponseEntity<List<BookDto>> findAll() {
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
	}

	@Operation(description = "this gets the book")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@GetMapping("/books/{id}")
	public ResponseEntity<BookDto> get(@PathVariable long id) {
		String port = env.getProperty("local.server.port");
		System.out.println("book service port-----------------" + port);
		return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
	}

	@Operation(description = "this delete the book")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(description = "this updates the book")
	@ApiResponse(responseCode = "201", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@PutMapping("/books/{id}")
	public ResponseEntity<BookDto> update(@RequestBody @Valid BookDto bookDto,@PathVariable long id) {
		return new ResponseEntity<>(bookService.update(bookDto,id), HttpStatus.ACCEPTED);
	}

}
