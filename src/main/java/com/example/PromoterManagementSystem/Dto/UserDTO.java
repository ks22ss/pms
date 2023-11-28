package com.example.PromoterManagementSystem.Dto;

import com.example.PromoterManagementSystem.Type.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private UserType userType;

    public UserDTO(String username, String email, String password, UserType userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
