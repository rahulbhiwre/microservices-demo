package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.LibraryDto;
import com.epam.entity.Library;
import com.epam.repository.LibraryRepository;

@Service
public class LibraryService {

	public final static int BookAllowedToIssue = 3;

	@Autowired
	ModelMapper mapper;

	@Autowired 
	LibraryRepository libraryRepository;

	public LibraryDto issueBook(String username, long bookid) throws Exception {
		Library library = new Library();
		if (checkCurrentIssuedBooks(username) >= BookAllowedToIssue) {
			throw new Exception("We can only issue 3 Books per user");
		}
		library.setUsername(username);
		library.setBookid(bookid);
		return mapper.map(libraryRepository.save(library), LibraryDto.class);
	}

	private int checkCurrentIssuedBooks(String username) {
		return libraryRepository.findByUsername(username).size();
	}

	public void releaseBook(String username, long bookid) {
		Library library = libraryRepository.findByUsernameAndBookid(username, bookid);
		libraryRepository.delete(library);
	}

}
