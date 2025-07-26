package com.hotel.booking.controller;

import com.hotel.booking.model.User;
import com.hotel.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = { "http://localhost:8080", "hotel-booking-frontend-omega.vercel.app",
        "hotel-booking-frontend-git-master-vinudas-projects.vercel.app",
        "hotel-booking-frontend-dhs6wk2gy-vinudas-projects.vercel.app" })

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        Map<String, String> response = new HashMap<>();

        if (savedUser != null) {
            response.put("message", "User registration successful");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "User already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/is-admin/{email}")
    public boolean isAdmin(@PathVariable String email) {
        return userService.isAdmin(email);
    }

    @GetMapping("/is-customer/{email}")
    public boolean isCustomer(@PathVariable String email) {
        return userService.isCustomer(email);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/block/{email}")
    public ResponseEntity<String> toggleUserStatus(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        user.setEnabled(!user.isEnabled());
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

}
