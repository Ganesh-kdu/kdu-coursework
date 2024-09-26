package com.example.assessment.services;

import com.example.assessment.dto.RegistrationSuccessDto;
import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.UserEntity;
import com.example.assessment.exceptions.custom.NoUserFoundException;
import com.example.assessment.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UsersRepository usersRepository;
    private HashMap<String,HashMap<String,Integer>> carts;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.carts = new HashMap<>();
    }

    public List<UserEntity> getAllUsers() throws NoUserFoundException {
        try {
            List<UserEntity> users = usersRepository.findAll();
            if(users.isEmpty()){
                throw new NoUserFoundException("No UsersRepository found in the database");
            }
            return users;
        } catch (NoUserFoundException e) {
            throw new NoUserFoundException(e.getMessage());
        }
    }

    public UserEntity getUserByName(String name){
            Optional<UserEntity> user = usersRepository.findById(name);

            if (user.isPresent()) {
                return user.get();
            }
            throw new NoUserFoundException("No user found with name " + name);

    }

    public RegistrationSuccessDto addUser(UserDto userDto) {
        try {
            usersRepository.save(new UserEntity(userDto));
            return new RegistrationSuccessDto(userDto.getUsername(), userDto.getEmail(), userDto.getRole());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding user");
        }
    }


}

