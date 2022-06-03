package com.epam.feigncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.client.BookClient;
import com.epam.client.UserClient;
import com.epam.dto.BookDto;
import com.epam.dto.LibraryDto;
import com.epam.dto.UserDto;
import com.epam.service.LibraryService;

@RestController
public class LibraryController {

	@Autowired
	LibraryService libraryService;

	@Autowired
	UserClient userClient;

	@Autowired
	BookClient bookClient;

	@PostMapping("library/users/{username}/books/{bookid}")
	public ResponseEntity<LibraryDto> insert(@PathVariable(name = "username") String username,
			@PathVariable(name = "bookid") long bookid) throws Exception {

		// check user and bookid exists
		UserDto user = userClient.getUser(username).getBody();
		BookDto book = bookClient.getBook(bookid).getBody();

		LibraryDto library = libraryService.issueBook(username, bookid);
		return new ResponseEntity<>(library, HttpStatus.CREATED);
	}

	@DeleteMapping("library/users/{username}/books/{bookid}")
	public ResponseEntity<HttpStatus> delete(@PathVariable(name = "username") String username,
			@PathVariable(name = "bookid") long bookid) {

		// check user and bookid exists
		UserDto user = userClient.getUser(username).getBody();
		BookDto book = bookClient.getBook(bookid).getBody();

		libraryService.releaseBook(username, bookid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
