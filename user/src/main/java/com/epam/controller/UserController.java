package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

	@Operation(description = "this Fetches all the users")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

	@Operation(description = "this gets the user")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("users/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

	@Operation(description = "this stores the user")
	@ApiResponse(responseCode = "201", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/users")
    public ResponseEntity<UserDto> add(@RequestBody @Valid UserDto user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

	@Operation(description = "this delete the user")
	@ApiResponse(responseCode = "200", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
    @DeleteMapping("users/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	@Operation(description = "this updates the user")
	@ApiResponse(responseCode = "201", description = "Sucessfull")
	@ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping("users/{username}")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto user, @PathVariable String username) {
        return new ResponseEntity<>(userService.updateUser(user, username), HttpStatus.OK);
    }

}
