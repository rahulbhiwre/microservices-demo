package com.epam.service;

import java.util.List;

import com.epam.dto.UserDto;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserByUsername(String username);

    UserDto addUser(UserDto userDto);

    void deleteUser(String username);

    UserDto updateUser(UserDto userDto, String username);


}
