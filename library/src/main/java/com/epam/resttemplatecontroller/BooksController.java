//package com.epam.resttemplatecontroller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.epam.dto.BookDto;
//
//@RestController
//@RequestMapping(value = "/library")
//public class BooksController {
//
//	@Autowired
//	RestTemplate restTemplate;
//
//	private String uri = "http://localhost:9001/books/";
//
//	@GetMapping("/books")
//	public ResponseEntity<List<BookDto>> getBooks() {
//		List<BookDto> books = restTemplate.getForObject(uri, List.class);
//		return new ResponseEntity<>(books, HttpStatus.OK);
//	}
//
//	@GetMapping("/books/{id}")
//	public ResponseEntity<BookDto> getBook(@PathVariable long id) {
//		BookDto book = restTemplate.getForObject(uri.concat(String.valueOf(id)), BookDto.class);
//		return new ResponseEntity<>(book, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/books/{id}")
//	public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id) {
//		ResponseEntity<HttpStatus> status = restTemplate.exchange(uri.concat(String.valueOf(id)), HttpMethod.DELETE,
//				null, HttpStatus.class);
//		return status;
//	}
//
//	@PostMapping("/books")
//	public ResponseEntity<BookDto> insertBook(@RequestBody BookDto bookDto) {
//		HttpEntity<BookDto> entity = new HttpEntity<>(bookDto);
//		ResponseEntity<BookDto> newBook = restTemplate.exchange(uri, HttpMethod.POST, entity, BookDto.class);
//		return newBook;
//	}
//
//	@PutMapping("/books/{id}")
//	public ResponseEntity<BookDto> updateBook(@PathVariable long id, @RequestBody BookDto bookDto) {
//		HttpEntity<BookDto> entity = new HttpEntity<>(bookDto);
//		ResponseEntity<BookDto> updatedBook = restTemplate.exchange(uri.concat(String.valueOf(id)), HttpMethod.PUT,
//				entity, BookDto.class);
//		return updatedBook;
//	}
//
//}
