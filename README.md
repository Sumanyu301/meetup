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

- **Java 17 or higher** - [Download here](https://adoptium.net/)
- **MongoDB** - [Download here](https://www.mongodb.com/try/download/community)
- **Git** - [Download here](https://git-scm.com/downloads)
- Maven (optional - project includes Maven wrapper)

## Quick Setup (Automated)

For an automated setup experience, use the provided setup scripts:

### Linux/macOS:
```bash
chmod +x setup.sh
./setup.sh
```

### Windows:
```cmd
setup.bat
```

These scripts will:
- Check if Java 17+ is installed
- Verify MongoDB installation and connection
- Test the build process
- Provide guidance if issues are found

## Manual Setup

### Step 1: Clone the Repository
```bash
git clone https://github.com/Sumanyu301/meetup.git
cd meetup
```

### Step 2: Install and Start MongoDB

#### On Windows:
1. Download MongoDB Community Server
2. Install with default settings
3. MongoDB will start automatically as a service

#### On macOS:
```bash
# Using Homebrew
brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb/brew/mongodb-community
```

#### On Linux (Ubuntu/Debian):
```bash
# Install MongoDB
sudo apt-get update
sudo apt-get install -y mongodb

# Start MongoDB service
sudo systemctl start mongodb
sudo systemctl enable mongodb
```

#### Verify MongoDB is Running:
```bash
# Check if MongoDB is listening on port 27017
netstat -an | grep 27017

# Or try connecting with mongo shell
mongosh
# Type 'exit' to quit
```

### Step 3: Verify Java Installation
```bash
java -version
# Should show Java 17 or higher
```

### Step 4: Run the Application

#### Option A: Using Maven Wrapper (Recommended)
```bash
# On Linux/macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

#### Option B: Using System Maven
```bash
mvn spring-boot:run
```

#### Option C: Build and Run JAR
```bash
# Build the application
./mvnw clean package

# Run the generated JAR
java -jar target/meetup-0.0.1-SNAPSHOT.jar
```

### Step 5: Verify Application is Running
- Open your browser and go to `http://localhost:8080`
- The application should be running and connected to MongoDB
- Check the console logs for any startup issues

## Troubleshooting

### Common Issues:

#### 1. "Port 8080 already in use"
```bash
# Find what's using port 8080
lsof -i :8080

# Kill the process (replace PID with actual process ID)
kill -9 <PID>
```

#### 2. "Cannot connect to MongoDB"
- Ensure MongoDB is running: `sudo systemctl status mongodb` (Linux) or check Services (Windows)
- Check if port 27017 is available: `netstat -an | grep 27017`
- Verify MongoDB connection: `mongosh`

#### 3. "Java version not supported"
```bash
# Check Java version
java -version

# Update JAVA_HOME if needed (Linux/macOS)
export JAVA_HOME=/path/to/java17
```

#### 4. Permission denied on Maven wrapper
```bash
# Make Maven wrapper executable (Linux/macOS)
chmod +x mvnw
```

## Configuration

The application uses these default settings (in `src/main/resources/application.properties`):
- **Server Port**: 8080
- **MongoDB Host**: localhost
- **MongoDB Port**: 27017
- **Database Name**: meetup_db

To change these settings, modify the `application.properties` file or use environment variables.

## Development Workflow

### Making Changes
1. Make your code changes
2. The application supports hot reload - changes to Java files will trigger automatic restart
3. For immediate testing, you can also run specific classes/methods in your IDE

### Running Tests
```bash
# Run all tests
./mvnw test

# Run tests with coverage report
./mvnw test jacoco:report
```

### Building for Production
```bash
# Create production JAR
./mvnw clean package -DskipTests

# The JAR will be created in target/meetup-0.0.1-SNAPSHOT.jar
```

### Database Management
- MongoDB database `meetup_db` will be created automatically when the application starts
- You can use MongoDB Compass (GUI) or mongosh (CLI) to view/manage data
- Connect to: `mongodb://localhost:27017/meetup_db`

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
