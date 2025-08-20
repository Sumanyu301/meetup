package com.example.meetup.controller;

import com.example.meetup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from anywhere
public class SimpleController {

    @Autowired
    private MongoTemplate mongoTemplate;

    // ✅ Create a password encoder instance (can also be a @Bean in a config class)
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Test endpoint
    @GetMapping("/test")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", " API is working");
        return response;
    }

    // Signup
    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        try {
            // Check if email already exists (✅ email is now the unique login key)
            Query emailQuery = new Query(Criteria.where("email").is(user.getEmail()));
            if (mongoTemplate.findOne(emailQuery, User.class) != null) {
                response.put("message", "❌ Email '" + user.getEmail() + "' already exists!");
                response.put("status", "error");
                return response;
            }

            // ✅ Hash the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Save user
            mongoTemplate.save(user);

            response.put("message", "✅ Account created successfully! Welcome " + user.getUsername() + "!");
            response.put("status", "success");
            response.put("username", user.getUsername());

        } catch (Exception e) {
            response.put("message", "❌ Error creating account: " + e.getMessage());
            response.put("status", "error");
        }

        return response;
    }

    // Login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();

        try {
            String email = loginData.get("email"); // ✅ login only with email
            String password = loginData.get("password");

            // Validation
            if (email == null || email.trim().isEmpty()) {
                response.put("message", "❌ Please enter email");
                response.put("status", "error");
                return response;
            }

            if (password == null || password.trim().isEmpty()) {
                response.put("message", "❌ Please enter password");
                response.put("status", "error");
                return response;
            }

            // Find user by email
            Query query = new Query(Criteria.where("email").is(email));
            User user = mongoTemplate.findOne(query, User.class);

            if (user == null) {
                response.put("message", "❌ User with email '" + email + "' not found");
                response.put("status", "error");
                return response;
            }

            // ✅ Compare hashed password
            if (!passwordEncoder.matches(password, user.getPassword())) {
                response.put("message", "❌ Wrong password! Please try again");
                response.put("status", "error");
                return response;
            }

            // Success
            Map<String, String> userData = new HashMap<>();
            userData.put("username", user.getUsername());
            userData.put("email", user.getEmail());
            userData.put("id", user.getId());

            response.put("message", "🎉 Welcome back, " + user.getUsername() + "!");
            response.put("status", "success");
            response.put("user", userData);

        } catch (Exception e) {
            response.put("message", "❌ Login error: " + e.getMessage());
            response.put("status", "error");
        }

        return response;
    }

    // Get all users
    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<User> users = mongoTemplate.findAll(User.class);

            // Hide passwords
            users.forEach(user -> user.setPassword("***hidden***"));

            response.put("message", "📋 All users retrieved successfully");
            response.put("status", "success");
            response.put("users", users);
            response.put("count", users.size());

        } catch (Exception e) {
            response.put("message", "❌ Error getting users: " + e.getMessage());
            response.put("status", "error");
        }

        return response;
    }

    // Delete user
    @DeleteMapping("/users/{email}")
    public Map<String, String> deleteUser(@PathVariable String email) {
        Map<String, String> response = new HashMap<>();

        try {
            Query query = new Query(Criteria.where("email").is(email));
            User user = mongoTemplate.findOne(query, User.class);

            if (user == null) {
                response.put("message", "❌ User with email '" + email + "' not found");
                response.put("status", "error");
                return response;
            }

            mongoTemplate.remove(query, User.class);

            response.put("message", "🗑️ User with email '" + email + "' deleted successfully");
            response.put("status", "success");

        } catch (Exception e) {
            response.put("message", "❌ Error deleting user: " + e.getMessage());
            response.put("status", "error");
        }

        return response;
    }
}
