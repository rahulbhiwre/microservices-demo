package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
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

import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.exception.ResourceNotFoundException;
import com.epam.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@MockBean
	UserRepository userRepository;

	@Mock
	private ModelMapper mapper;

	@Test
	void testGetAllUsers() {
		userService.getAllUsers();
		verify(userRepository, atLeast(1)).findAll();
	}

	@Test
	void testGetUserByUsername() {
		Optional<User> user = Optional.ofNullable(new User());
		given(userRepository.findById("abc")).willReturn(user);
		userService.getUserByUsername("abc");
		verify(userRepository, atLeast(1)).findById("abc");
	}

	@Test
	void testAddUser() {
		UserDto userDto = new UserDto();
		User user = mapper.map(userDto, User.class);
		userService.addUser(userDto);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testDeleteUser() {
		Optional<User> user = Optional.ofNullable(new User());
		given(userRepository.existsById("abc")).willReturn(true);
		userService.deleteUser("abc");
		verify(userRepository, times(1)).deleteById("abc");
	}

	@Test
	void testUpdateUser() {
		UserDto userDto = new UserDto();
		User user = mapper.map(userDto, User.class);
		given(userRepository.existsById("abc")).willReturn(true);
		userService.updateUser(userDto, "abc");
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testGetUserException() {
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.getUserByUsername("abc"),
				"User with username not found: " + "abc");
		assertEquals("User with username not found: " + "abc", thrown.getMessage());
	}
	
	@Test
	void testDeleteException() throws Exception {

		Optional<User> user = Optional.ofNullable(new User());
		given(userRepository.existsById("abc")).willReturn(false);

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser("abc"),
				"User with username not found: " + "abc");
		assertEquals("User with username not found: " + "abc", thrown.getMessage());

	}

	@Test
	void testUpdateException() throws Exception {

		UserDto userDto = new UserDto();
		User user = mapper.map(userDto, User.class);
		given(userRepository.existsById("abc")).willReturn(false);

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto, "abc"),
				"User with username not found: " + "abc");
		assertEquals("User with username not found: " + "abc", thrown.getMessage());

	}

}
