package com.epam.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

	@Size(min = 2, max = 30, message = "username should be between 2 and 30 characters length")
    private String username;

    @Email
    private String email;

	@Size(min = 2, max = 30, message = "name should be between 2 and 30 characters length")
    private String name;
}
