# Meetup Application

A Spring Boot application for managing meetups with MongoDB integration.

## Features

- RESTful API for meetup management
- MongoDB database integration
- User management system
- Spring Security for password encryption

## Technology Stack

- **Backend**: Spring Boot 3.5.4
- **Database**: MongoDB
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

- Java 17 or higher
- MongoDB running on localhost:27017
- Maven (or use the included wrapper)

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/Sumanyu301/meetup.git
cd meetup
```

### 2. Configure MongoDB
Make sure MongoDB is running on your local machine:
- Host: localhost
- Port: 27017
- Database: meetup_db

### 3. Run the application
```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

# Or if you have Maven installed
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Refer to [API_TESTING_GUIDE.md](API_TESTING_GUIDE.md) for detailed API documentation and testing instructions.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/meetup/
│   │       ├── MeetupApplication.java
│   │       ├── controller/
│   │       └── model/
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/meetup/
```

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).
