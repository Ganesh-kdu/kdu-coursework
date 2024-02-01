package com.example.jdbc.services;

import com.example.jdbc.dao.UserDao;
import com.example.jdbc.dto.UserDto;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }
    public void addUser(UserDto userDto){
        User user = mapUserDtoToUser(userDto);
        userDao.saveUser(user);
    }

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName(userDto.getUserName());
        user.setLoggedIn(userDto.isLoggedIn());
        user.setTimeZone(userDto.getTimeZone());
        user.setTenantId(UUID.fromString(userDto.getTenantId()));
        return user;
    }

    public User getUserById(UUID id){
        return userDao.getUserById(id);
    }

    public List<User> getAllUser(){
        return userDao.getUsers();
    }

    public List<User> getUserByName(String name){
        return userDao.getUserByName(name);
    }

    public int updateUser(UUID id, String name){
        return userDao.updateNameForId(id,name);
    }


}