package com.hotel.booking.service;

import com.hotel.booking.model.User;
import com.hotel.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // user registration method
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    // user login method
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    // check user role(is it an admin?)
    public boolean isAdmin(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getRole().equals("admin");
    }

    // check user role(is it a customer?)
    public boolean isCustomer(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getRole().equals("customer");
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // save user
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
