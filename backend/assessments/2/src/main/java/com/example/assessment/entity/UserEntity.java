package com.example.assessment.entity;

import com.example.assessment.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    public UserEntity(UserDto userDto){
        this.email = userDto.getEmail();
        this.role = userDto.getRole();
        this.password = userDto.getPassword();
        this.username = userDto.getUsername();
    }
}
