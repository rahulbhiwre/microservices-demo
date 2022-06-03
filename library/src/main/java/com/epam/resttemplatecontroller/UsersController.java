//package com.epam.resttemplatecontroller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.epam.dto.UserDto;
//
//@RestController
//@RequestMapping(value = "/library")
//public class UsersController {
//
//	@Autowired
//	RestTemplate restTemplate;
//
//	private String uri = "http://localhost:9002/users/";
//
//	@GetMapping("/users")
//	public ResponseEntity<List<UserDto>> getUsers() {
//		List<UserDto> users = restTemplate.getForObject(uri, List.class);
//		return new ResponseEntity<>(users, HttpStatus.OK);
//	}
//
//	@GetMapping("/users/{username}")
//	public ResponseEntity<UserDto> getUser(@PathVariable String username) {
//		UserDto user = restTemplate.getForObject(uri.concat(username), UserDto.class);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/users/{username}")
//	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String username) {
//		ResponseEntity<HttpStatus> status = restTemplate.exchange(uri.concat(username), HttpMethod.DELETE, null,
//				HttpStatus.class);
//		return status;
//	}
//
//	@PostMapping("/users")
//	public ResponseEntity<UserDto> insertUser(@RequestBody UserDto userDto) {
//		HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
//		ResponseEntity<UserDto> newUser = restTemplate.exchange(uri, HttpMethod.POST, entity, UserDto.class);
//		return newUser;
//	}
//
//	@PutMapping("/users/{username}")
//	public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
//		HttpEntity<UserDto> entity = new HttpEntity<>(userDto);
//		ResponseEntity<UserDto> updatedUser = restTemplate.exchange(uri.concat(username), HttpMethod.PUT, entity,
//				UserDto.class);
//		return updatedUser;
//	}
//
//}
