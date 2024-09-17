package com.example.auth.repository;


import com.example.auth.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<UserDto> users = new ArrayList<>();

    public List<UserDto> getAllUsers() {
        return users;
    }

    public UserDto getUserByName(String name) {
        return  users.stream()
                .filter(u -> u.getUsername().equals(name))
                .findFirst()
                .orElse(null);

    }

    public void addUser(UserDto userDto) {
        users.add(userDto);
    }
}