package com.epam.service;

import com.epam.dto.UserDto;
import com.epam.entity.User;
import com.epam.exception.ResourceNotFoundException;
import com.epam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	public static final String ERROR_MESSAGE = "User with username not found: ";

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserDto> getAllUsers() {
		return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {
		}.getType());
	}

	@Override
	public UserDto getUserByUsername(String username) {
		return modelMapper.map(userRepository.findById(username).orElseThrow(
				() -> new ResourceNotFoundException(ERROR_MESSAGE + username)), UserDto.class);
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
	}

	@Override
	public void deleteUser(String username) {
		if (!userRepository.existsById(username)) {
			throw new ResourceNotFoundException(ERROR_MESSAGE + username);
		}
		userRepository.deleteById(username);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String username) {
		if (!userRepository.existsById(username)) {
			throw new ResourceNotFoundException(ERROR_MESSAGE + username);
		}
		userRepository.deleteById(username);
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
	}
}