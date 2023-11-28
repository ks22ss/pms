package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Dto.UserDTO;
import com.example.PromoterManagementSystem.Model.User;
import com.example.PromoterManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        User existingUser = userRepository.findByUsername(username);
        if(existingUser != null){
            return existingUser;
        }else{
            throw new IllegalArgumentException("User does not exists.");
        }
    }

    public User getUserByEmail(String email) {
        User existingUser = userRepository.findByEmail(email);
        if(existingUser != null){
            return existingUser;
        }else{
            throw new IllegalArgumentException("User does not exists.");
        }
    }

    public User createUser(UserDTO user) {
        User existingUsername = userRepository.findByUsername(user.getUsername());
        if(existingUsername != null){
            throw new IllegalArgumentException("Username already exists.");
        }
        User existingEmail = userRepository.findByEmail(user.getEmail());
        if(existingEmail != null){
            throw new IllegalArgumentException("Email already exists.");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setUserType(user.getUserType());

        return userRepository.save(newUser);
    }

    public User updateUser(String userName, User updatedUser) {
        User existingUser = userRepository.findByUsername(userName);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setJoinDate(updatedUser.getJoinDate());
            existingUser.setEndDate(updatedUser.getEndDate());

            return userRepository.save(existingUser);
        }else{
            throw new IllegalArgumentException("Username does not exists.");
        }
    }

    public void deleteUser(String userName) {
        User existingUser = userRepository.findByUsername(userName);
        if(existingUser != null){
            userRepository.delete(existingUser);
        }else{
            throw new IllegalArgumentException("Username does not exists.");
        }

    }
}
