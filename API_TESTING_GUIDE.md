# Meetup API Testing Guide

This guide shows you how to test your signup and login endpoints using curl commands or tools like Postman.

## Prerequisites

1. **Install MongoDB locally**:

   ```bash
   # On Ubuntu/Debian
   sudo apt update
   sudo apt install mongodb
   sudo systemctl start mongodb
   sudo systemctl enable mongodb

   # On MacOS (using Homebrew)
   brew tap mongodb/brew
   brew install mongodb-community
   brew services start mongodb/brew/mongodb-community

   # On Windows, download from: https://www.mongodb.com/try/download/community
   ```

2. **Start your Spring Boot application**:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

### 1. Test Endpoint

**GET** `/api/auth/test`

Test if your API is working:

```bash
curl -X GET http://localhost:8080/api/auth/test
```

Expected response:

```json
{
  "success": true,
  "message": "Authentication API is working!",
  "data": null
}
```

### 2. Signup Endpoint

**POST** `/api/auth/signup`

Register a new user:

```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

Expected response:

```json
{
  "success": true,
  "message": "User registered successfully! Welcome johndoe",
  "data": null
}
```

### 3. Login Endpoint

**POST** `/api/auth/login`

Login with username or email:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "johndoe",
    "password": "password123"
  }'
```

Expected response:

```json
{
  "success": true,
  "message": "Login successful!",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "tokenType": "Bearer",
    "username": "johndoe",
    "email": "john@example.com"
  }
}
```

## Using Postman

1. **Create a new collection** called "Meetup API"

2. **Add requests**:
   - **Test**: GET `http://localhost:8080/api/auth/test`
   - **Signup**: POST `http://localhost:8080/api/auth/signup`
     - Body: raw JSON
     - Headers: Content-Type: application/json
   - **Login**: POST `http://localhost:8080/api/auth/login`
     - Body: raw JSON
     - Headers: Content-Type: application/json

## Common Error Responses

### Validation Errors

If required fields are missing or invalid:

```json
{
  "success": false,
  "message": "Username is required"
}
```

### Duplicate User

If username or email already exists:

```json
{
  "success": false,
  "message": "Error: Username is already taken!"
}
```

### Invalid Login

If username/email or password is incorrect:

```json
{
  "success": false,
  "message": "Error: User not found!"
}
```

## What the JWT Token Contains

The JWT token returned from login contains:

- **Subject**: Username
- **Issued At**: Current timestamp
- **Expiration**: 24 hours from issue (configurable in application.properties)

You can decode the token at [jwt.io](https://jwt.io) to see its contents.

## Next Steps

1. **Verify MongoDB**: Check if users are being saved in MongoDB

   ```bash
   mongo
   use meetup_db
   db.users.find()
   ```

2. **Add protected endpoints**: Create endpoints that require authentication
3. **Add user profile management**: Edit profile, change password, etc.
4. **Add role-based access**: Admin, user roles
5. **Add email verification**: Send confirmation emails
6. **Add password reset**: Forgot password functionality

## Project Structure Summary

```
src/main/java/com/example/meetup/
├── config/
│   └── SecurityConfig.java          # Security configuration
├── controller/
│   └── AuthController.java          # REST endpoints
├── dto/                            # Data Transfer Objects
│   ├── ApiResponse.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── SignupRequest.java
├── model/
│   └── User.java                   # User entity for MongoDB
├── repository/
│   └── UserRepository.java        # Database operations
├── service/
│   └── UserService.java           # Business logic
├── util/
│   └── JwtUtils.java              # JWT token utilities
└── MeetupApplication.java         # Main application class
```

This structure follows Spring Boot best practices and is beginner-friendly with clear separation of concerns.
