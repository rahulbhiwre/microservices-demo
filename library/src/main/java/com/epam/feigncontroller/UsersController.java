package com.epam.feigncontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.client.UserClient;
import com.epam.dto.UserDto;

@RestController
@RequestMapping("/library")
public class UsersController {

	@Autowired
	UserClient userClient;

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getUsers() {
		return userClient.getUsers();
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable String username) {
		return userClient.getUser(username);
	}

	@DeleteMapping("/users/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String username) {
		return userClient.deleteUser(username);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDto> insertUser(@RequestBody UserDto userDto) {
		return userClient.insertUser(userDto);
	}

	@PutMapping("/users/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
		return userClient.updateUser(username, userDto);
	}

}
