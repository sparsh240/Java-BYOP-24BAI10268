# Terminal Task Manager (Java CLI)

## Overview
This is a command-line based task manager for developers, designed to manage tasks directly from the terminal without requiring a graphical interface.

The application supports:
- Secure user authentication (hashed + salted passwords)
- Multi-user environment
- Task sharing between users
- Persistent storage using local files

---

## Project Structure

-- To be Added --


---

## File Descriptions

### Main.java
- Entry point of the application  
- Handles all CLI interactions  
- Displays menus (login/register, task operations)  
- Maintains current user session  
- Delegates logic to service classes  

---

## model/ (Data Layer)

### User.java
- Represents a user in the system  
- Stores:
  - `username`
  - `salt`
  - `passwordHash`  
- Used for authentication and identification  

### Task.java
- Represents a task  
- Stores:
  - Task ID  
  - Title  
  - Owner (creator)  
  - Shared users  
  - Completion status  
- Supports task sharing and tracking  

---

## service/ (Business Logic Layer)

### AuthService.java
- Handles user authentication  
- Features:
  - Register new users  
  - Login existing users  
  - Password validation using hashing + salt  
- Interacts with:
  - StorageService  
  - PasswordUtil  
  - SaltUtil  

### TaskService.java
- Manages all task-related operations:
  - Add tasks  
  - View tasks  
  - Mark tasks as complete  
  - Share tasks with other users  
- Enforces access control:
  - Only owners or shared users can view tasks  
  - Only owners can modify tasks  

### StorageService.java
- Handles persistence (file storage)  
- Reads and writes:
  - `users.dat`  
  - `tasks.dat`  
- Ensures data is saved between sessions  
- Contains no business logic  

---

## util/ (Utility Classes)

### PasswordUtil.java
- Handles password hashing  
- Uses secure algorithms (e.g., SHA-256)  
- Combines password + salt before hashing  

### SaltUtil.java
- Generates random salts for each user  
- Prevents common password attacks  

---

## data/ (Persistent Storage)

### users.dat
- Stores serialized user data  
- Contains:
  - Username  
  - Salt  
  - Hashed password  

### tasks.dat
- Stores serialized task data  
- Contains:
  - Task details  
  - Ownership and sharing info  

---

## Application Flow

1. Start application  
2. Choose:
   - Register new user  
   - Login existing user  
3. After login:
   - Add tasks  
   - View tasks  
   - Mark tasks complete  
   - Share tasks  
4. Data is automatically saved after changes  

---

## Security Features

- Passwords are never stored in plain text  
- Each user has a unique salt  
- Passwords are hashed before storage  
- Prevents:
  - Password leaks  
  - Rainbow table attacks  

---

## Design Principles

- Separation of concerns  
- Modular architecture  
- Scalable structure  
- Secure data handling  
- Clean CLI interaction  

---

## Future Improvements

- Switch from `.dat` to JSON or database  
- Add deadlines & priorities  
- Implement notifications  
- Build GUI (JavaFX)  
- Convert to REST API backend  

---

## Summary

This project is designed to be:
- Simple to use via terminal  
- Secure for multiple users  
- Scalable for future enhancements  