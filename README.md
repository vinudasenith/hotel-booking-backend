#  🏨 Hotel Booking Management Backend

## 📖 Overview
This is the backend for a full-stack hotel booking management web application. It provides RESTful APIs to support user browsing, booking, and admin management functionalities. Built with **Java** and **Spring Boot**, it integrates with a database for persistent data storage and handles user authentication, room management, and booking operations.

## 🛠️  Tech Stack
- **Java**: Version 17
- **Spring Boot**: Version 3.x
- **Database**: MongoDB
- **Dependencies**:
  - Spring Web
  - Spring Data JPA
  - Spring Security (JWT-based authentication)
  - [Other dependencies, e.g., Lombok, Spring Boot Starter Validation]

## 🚀 Features
- **User APIs**:
  - User registration and login 
  - View hotels, rooms, and gallery
  - Create and manage bookings
- **Admin APIs**:
  - View and manage users (block/unblock)
  - View, confirm, or cancel bookings
  - Add, update, or delete rooms


## 📋 Prerequisites
- Java 17 or higher
- Maven 3.8.x or higher
- MongoDB Atlas
- IDE 
- Postman or similar tool for API testing (optional)

## ⚙️ Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/vinudasenith/hotel-booking-backend.git
   cd hotel-booking-backend
   ```
2. **Install Dependencies**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The backend will be available at `http://localhost:8080`.

4. **Main Test the APIs**:
   - Use Postman or a similar tool to test endpoints.
   - Example endpoints:
     - `POST /api/users/register` - Register a new user
     - `POST /api/users/login` - Authenticate a user
     - `GET /api/rooms/all` - List available rooms
     - `POST /api/bookings` - Create a booking
     - `GET /api/users/all` - List users (admin only)
     - `PUT /api/bookings` - Confirm a booking 
     - `POST /api/rooms` - Add a new room (admin only)

## 🗂️ Project Structure
```
hotel-booking-backend/
├── src/
│   ├── main/
│   │   ├── java/com/hotel/booking
│   │   │   ├── config/             # Configuration (e.g., SecurityConfig, JWT)
│   │   │   ├── controller/         # REST controllers for user and admin APIs
│   │   │   ├── service/            # Business logic for bookings, rooms, users
│   │   │   ├── repository/         # JPA repositories for database access
│   │   │   ├── model/              # Entity classes (User, Room, Booking)
│   │   │   └── Application.java    # Main Spring Boot application
│   ├── resources/
│   │   └── application.properties  # Database and JWT configuration
├── pom.xml                         # Maven dependencies
└── uploads                         # Images
```

## 📄 License
 MIT License
