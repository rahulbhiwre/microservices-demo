package com.epam.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.dto.UserDto;

@FeignClient(name = "Book Service")
public interface UserClient {

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getUsers();

	@GetMapping("/users/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable String username);

	@DeleteMapping("/users/{username}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String username);

	@PostMapping("/users")
	public ResponseEntity<UserDto> insertUser(@RequestBody UserDto userDto);

	@PutMapping("/users/{username}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto);
}
