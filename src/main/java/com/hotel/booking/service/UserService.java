package com.hotel.booking.service;

import com.hotel.booking.model.User;
import com.hotel.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean isAdmin(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getRole().equals("admin");
    }

    public boolean isCustomer(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getRole().equals("customer");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
