package com.epam.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.dto.UserDto;
import com.epam.exception.ResourceNotFoundException;
import com.epam.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ModelMapper modelMapper;

	@Mock
	private UserService userService;

	@InjectMocks
	UserController userController;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void testGetAll() {
		UserDto userDto = new UserDto();
		List<UserDto> users = new ArrayList<>();
		when(userService.getAllUsers()).thenReturn(users);
		ResponseEntity<List<UserDto>> responseEntity = userController.getAll();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}

	@Test
	void testGetByUsername() {
		UserDto userDto = new UserDto();
		when(userService.getUserByUsername("abc")).thenReturn(userDto);
		ResponseEntity<UserDto> responseEntity = userController.getByUsername("abc");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testAdd() {
		UserDto userDto = new UserDto();
		when(userService.addUser(userDto)).thenReturn(userDto);
		ResponseEntity<UserDto> responseEntity = userController.add(userDto);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	void testDelete() {
		userService.deleteUser("abc");
		ResponseEntity<?> responseEntity = userController.delete("abc");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
	}

	@Test
	void testUpdate() {
		UserDto userDto = new UserDto();
		when(userService.updateUser(userDto, "abc")).thenReturn(userDto);
		ResponseEntity<UserDto> responseEntity = userController.update(userDto, userDto.getUsername());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testInsertInvalidUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setEmail("a");
		when(userService.addUser(userDto)).thenReturn(userDto);
		String bookInJson = mapper.writeValueAsString(userDto);
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void getUserByIdTypeMismatch() throws Exception {
		UserDto userDto = new UserDto();
		when(userService.getUserByUsername("abc")).thenReturn(userDto);
		String bookInJson = mapper.writeValueAsString(userDto);
		mockMvc.perform(get("/users/" + 1L).contentType(MediaType.APPLICATION_JSON).content(bookInJson))
				.andExpect(status().isBadRequest());
	}

	@Test
	void usernotfound() throws Exception {
		doThrow(ResourceNotFoundException.class).when(userService).getUserByUsername("abc");
		mockMvc.perform(get("/users/{username}", 56L)).andExpect(status().isBadRequest());
	}
}
