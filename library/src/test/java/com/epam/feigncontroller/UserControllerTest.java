package com.epam.feigncontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.client.UserClient;
import com.epam.dto.UserDto;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	UsersController usersController;

	@MockBean
	UserClient userClient;

	@Test
	void testGetUsers() {
		usersController.getUsers();
		verify(userClient, times(1)).getUsers();
	}

	@Test
	void testGetUser() {
		usersController.getUser("abc");
		verify(userClient, times(1)).getUser("abc");
	}

	@Test
	void testDeleteUser() {
		usersController.deleteUser("abc");
		verify(userClient, times(1)).deleteUser("abc");
	}

	@Test
	void testInsertUser() {
		UserDto userDto = new UserDto();
		usersController.insertUser(userDto);
		verify(userClient, times(1)).insertUser(userDto);
	}

	@Test
	void testUpdateUser() {
		UserDto userDto = new UserDto();
		usersController.updateUser(userDto.getUsername(), userDto);
		verify(userClient, times(1)).updateUser(userDto.getUsername(), userDto);
	}

}
