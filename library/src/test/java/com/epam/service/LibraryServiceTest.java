package com.epam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.dto.LibraryDto;
import com.epam.entity.Library;
import com.epam.repository.LibraryRepository;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

	@InjectMocks
	LibraryService libraryService;

	@MockBean
	LibraryRepository libraryRepository;

	@Mock
	private ModelMapper mapper;

	private Library library = new Library();

	@BeforeEach
	void addData() {
		library.setBookid(1L);
		library.setUsername("abc");
	}

	@Test
	void testIssueBook() throws Exception {
		Library library1 = new Library();
		LibraryDto libraryDto = mapper.map(library1, LibraryDto.class);
		List<Library> labs = new ArrayList<>();
		labs.add(library);
		given(libraryRepository.findByUsername(library1.getUsername())).willReturn(labs);
		when(libraryRepository.save(library1)).thenReturn(library1);
		assertEquals(libraryDto, libraryService.issueBook(library1.getUsername(), library1.getBookid()));

	}

	@Test
	void testIssueBookException() {
		List<Library> labs = new ArrayList<>();
		labs.add(library);
		labs.add(library);
		labs.add(library);
		labs.add(library);
		given(libraryRepository.findByUsername("abc")).willReturn(labs);
		Exception thrown = assertThrows(Exception.class, () -> libraryService.issueBook("abc", 1),
				"We can only issue 3 Books per user");
		assertEquals("We can only issue 3 Books per user", thrown.getMessage());
	}

	@Test
	void testReleaseBook() {
		given(libraryRepository.findByUsernameAndBookid("abc", 1L)).willReturn(library);
		libraryService.releaseBook("abc", 1L);
		verify(libraryRepository, atLeast(1)).delete(library);
	}

}
