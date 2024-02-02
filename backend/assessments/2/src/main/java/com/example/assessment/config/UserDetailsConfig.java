package com.example.assessment.config;



import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.UserEntity;
import com.example.assessment.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsConfig implements UserDetailsService {
    private final UserService userService;
    public UserDetailsConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userService.getUserByName(name);
        String userName = null;
        String userPassword = null;
        List<GrantedAuthority> authorities = null;

        if(user == null){
            throw new UsernameNotFoundException("UserDto details not found for username : " + name);
        }else{
            userName = user.getUsername();
            userPassword = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        }
        return new User(userName, userPassword, authorities);
    }
}