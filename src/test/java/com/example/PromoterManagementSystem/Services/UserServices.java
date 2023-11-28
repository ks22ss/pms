package com.example.PromoterManagementSystem.Services;

import com.example.PromoterManagementSystem.Dto.UserDTO;
import com.example.PromoterManagementSystem.Model.User;
import com.example.PromoterManagementSystem.Repository.UserRepository;
import com.example.PromoterManagementSystem.Type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    private UserServices userServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userServices = new UserServices(userRepository);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "exampleUser";
        User mockUser = new User();

        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        User result = userServices.getUserByUsername(username);

        assertEquals(mockUser, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "example@example.com";
        User mockUser = new User();

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        User result = userServices.getUserByEmail(email);

        assertEquals(mockUser, result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testCreateUser() {
        // Mocking the userRepository behavior
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Create a sample UserDTO object
        UserDTO userDTO = new UserDTO("john", "john@example.com", "password", UserType.PROMOTER);

        // Call the createUser function
        userServices.createUser(userDTO);

        // Verify that the userRepository save method was called with the correct User object
        verify(userRepository).save(argThat(user ->
                user.getEmail().equals(userDTO.getEmail()) &&
                        user.getPassword().equals(userDTO.getPassword()) &&
                        user.getUsername().equals(userDTO.getUsername()) &&
                        user.getUserType().equals(userDTO.getUserType())
        ));
    }

    @Test
    public void testCreateUser_UsernameAlreadyExists() {
        // Mocking the userRepository behavior to return an existing user with the same username
        when(userRepository.findByUsername(anyString())).thenReturn(new User());

        // Create a sample UserDTO object
        UserDTO userDTO = new UserDTO("john", "john@example.com", "password", UserType.PROMOTER);

        // Call the createUser function and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> userServices.createUser(userDTO));

        // Verify that the userRepository save method was not called
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        // Mocking the userRepository behavior to return an existing user with the same email
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(new User());

        // Create a sample UserDTO object
        UserDTO userDTO = new UserDTO("john", "john@example.com", "password", UserType.PROMOTER);

        // Call the createUser function and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> userServices.createUser(userDTO));

        // Verify that the userRepository save method was not called
        verify(userRepository, never()).save(any());
    }


    @Test
    public void testUpdateUser() {
        String username = "existingUser";
        User existingUser = new User();
        existingUser.setUsername(username);

        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");

        when(userRepository.findByUsername(username)).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userServices.updateUser(username, updatedUser);

        assertEquals(existingUser, result);
        assertEquals(updatedUser.getUsername(), existingUser.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUser() {
        String username = "existingUser";
        User existingUser = new User();

        when(userRepository.findByUsername(username)).thenReturn(existingUser);

        userServices.deleteUser(username);

        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).delete(existingUser);
    }
}