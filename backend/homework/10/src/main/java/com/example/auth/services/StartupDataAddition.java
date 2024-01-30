package com.example.auth.services;

import com.example.auth.dto.UserDto;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartupDataAddition implements CommandLineRunner {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public StartupDataAddition(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.addUser(new UserDto("Ganesh1", passwordEncoder.encode("1111"), "ganesh1@kickdrum.com", "ROLE_ADMIN"));
        userRepository.addUser(new UserDto("Ganesh2", passwordEncoder.encode("a1"), "ganesh2@kickdrum.com", "ROLE_USER"));
    }
}