package com.example.PlayMate.Service;

import com.example.PlayMate.Entity.User;
import com.example.PlayMate.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "User already exists";
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set roles based on input or default to "USER"
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles("USER"); // Default role
        }
        user.setEnabled(true); // Ensure user is enabled

        // Save the user
        userRepository.save(user);

        // Logging for debugging
        System.out.println("User registered: " + user.getUsername() + " with roles: " + user.getRoles());

        return "User registered successfully";
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
