package com.example.assessment.services;

import com.example.assessment.dto.UserDto;
import com.example.assessment.entity.UserEntity;
import com.example.assessment.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartupDataAddition implements CommandLineRunner {
    UsersRepository userRepository;
    PasswordEncoder passwordEncoder;

    public StartupDataAddition(UsersRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new UserEntity(new UserDto("Ganesh1", passwordEncoder.encode("a1"), "ganesh1@kickdrum.com", "ROLE_ADMIN")));
        userRepository.save(new UserEntity(new UserDto("Ganesh2", passwordEncoder.encode("a1"), "ganesh2@kickdrum.com", "ROLE_USER")));
    }
}