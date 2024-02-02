package com.example.assessment.services;

import com.example.assessment.dto.UserDto;
import com.example.assessment.exceptions.custom.NoUserFoundException;
import com.example.assessment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() throws NoUserFoundException {
        try {
            List<UserDto> users = userRepository.getAllUsers();
            if(users.isEmpty()){
                throw new NoUserFoundException("No users found in the database");
            }
            return users;
        } catch (NoUserFoundException e) {
            throw new NoUserFoundException(e.getMessage());
        }
    }

    public UserDto getUserByName(String name){

            UserDto user = userRepository.getUserByName(name);
            if (user != null) {
                return user;
            }
            throw new NoUserFoundException("No user found with name " + name);

    }

    public UserDto addUser(UserDto userDto) {
        try {
            userRepository.addUser(userDto);
            return userDto;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding user");
        }
    }
}

