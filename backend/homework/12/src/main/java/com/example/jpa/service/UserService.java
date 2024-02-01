package com.example.jpa.service;

import com.example.jpa.entity.User;
import com.example.jpa.exception.custom.MyCustomException;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers(UUID tenantId) {
        return userRepository.findByTenantId(tenantId);
    }

    public User getUserById(UUID userId){
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public void updateUser(UUID userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setTenant(user.getTenant());
            existingUser.setUpdatedBy(user.getUpdatedBy());
            existingUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(existingUser);
        } else {
            throw new MyCustomException("User with ID " + userId + " does not exist.");
        }
    }
    public int updateUserDetails(UUID userId, User user) {
        return userRepository.updateUserDetails(user.getUsername(),user.getTimeZone(),userId);
    }
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
