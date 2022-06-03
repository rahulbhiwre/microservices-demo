//package com.epam.resttemplatecontroller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.epam.dto.BookDto;
//import com.epam.dto.LibraryDto;
//import com.epam.dto.UserDto;
//import com.epam.service.LibraryService;
//
//@RestController
//public class LibraryController {
//
//	@Autowired
//	RestTemplate restTemplate;
//
//	@Autowired
//	LibraryService libraryService;
//
//	private String uri1 = "http://localhost:9002/users/";
//	private String uri2 = "http://localhost:9001/books/";
//
//	@PostMapping("library/users/{username}/books/{bookid}")
//	public ResponseEntity<LibraryDto> insert(@PathVariable(name = "username") String username,
//			@PathVariable(name = "bookid") long bookid) throws Exception {
//
//		UserDto user = restTemplate.getForObject(uri1.concat(username), UserDto.class);
//		BookDto book = restTemplate.getForObject(uri2.concat(String.valueOf(bookid)), BookDto.class);
//
//		LibraryDto library = libraryService.issueBook(username, bookid);
//		return new ResponseEntity<>(library, HttpStatus.CREATED);
//	}
//
//	@DeleteMapping("library/users/{username}/books/{bookid}")
//	public ResponseEntity<HttpStatus> delete(@PathVariable(name = "username") String username,
//			@PathVariable(name = "bookid") long bookid) {
//
//		UserDto user = restTemplate.getForObject(uri1.concat(username), UserDto.class);
//		BookDto book = restTemplate.getForObject(uri2.concat(String.valueOf(bookid)), BookDto.class);
//
//		libraryService.releaseBook(username, bookid);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}
//
//}
