package com.example.jdbc.services;

import com.example.jdbc.dao.UserDao;
import com.example.jdbc.dto.UserDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }
    public String addUser(UserDto userDto){
        User user = DtoToModel.mapUserDtoToUser(userDto);
        userDao.saveUser(user);
        return user.getId().toString();
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