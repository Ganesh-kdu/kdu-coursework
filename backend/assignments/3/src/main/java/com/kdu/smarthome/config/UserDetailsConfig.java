package com.kdu.smarthome.config;



import com.kdu.smarthome.services.UserService;
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

    /**
     * Loads user from database and returns a userDetails object
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.kdu.smarthome.entities.User user = userService.getUserByName(name);
        String userName = null;
        String userPassword = null;
        List<GrantedAuthority> authorities = null;
        if(user == null){
            throw new UsernameNotFoundException("UserDto details not found for username : " + name);
        }else{
            userName = user.getUsername();
            userPassword = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(userName, userPassword, authorities);
    }
}