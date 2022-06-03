package com.epam.other;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.epam.dto.UserDto;
import com.epam.entity.User;

class TestDtoAndEntity {

	@Test
	void testUserDto() {
		UserDto userDto = new UserDto();
		userDto.setUsername("abcd");
		userDto.setEmail("qwer");
		userDto.setName("qwer");
		userDto.getEmail();
		userDto.getName();
		userDto.getUsername();

		userDto.toString();
	}

	@Test
	void testEntity() {
		User user = new User();
		user.setUsername("abcd");
		user.setEmail("qwer");
		user.setName("qwer");
		user.getEmail();
		user.getName();
		user.getUsername();

		user.toString();
	}

}
