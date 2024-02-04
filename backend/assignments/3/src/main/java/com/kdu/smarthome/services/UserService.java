package com.kdu.smarthome.services;


import com.kdu.smarthome.dto.UserDto;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import com.kdu.smarthome.mapper.DtoToEntities;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() throws NotFoundException {
        try {
            List<User> users = userRepository.findAll();
            if(users.isEmpty()){
                throw new NotFoundException("No UsersRepository found in the database");
            }
            return users;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public User getUserByName(String name){
        Optional<User> user = userRepository.findById(name);

        if (user.isPresent()) {
            return user.get();
        }
        throw new NotFoundException("No user found with name " + name);

    }

    public void addUser(UserDto userDto) {
        try {
            userRepository.save(DtoToEntities.dtoToUser(userDto));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding user");
        }
    }


}

