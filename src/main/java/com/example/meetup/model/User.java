package com.example.meetup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple User class - just basic fields for beginners
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    private String password; // We'll store plain text for simplicity (NOT recommended for production!)

    // Empty constructor (required by MongoDB)
    public User() {
    }

    // Constructor with all fields
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Simple getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
