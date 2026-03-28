# Terminal Task Manager (Java CLI)

## Overview
This is a command-line based task manager for developers, designed to manage tasks directly from the terminal without requiring a graphical interface.

The application supports:
- Secure user authentication (hashed + salted passwords)
- Multi-user environment
- Task sharing between users
- Persistent storage using local files

---

## Terminal Task Manager (Java CLI) – Setup and Usage Guide

This guide explains how to set up and run the Terminal Task Manager project from scratch, assuming no prior experience with Java or command-line tools.

---

## 1. Install Java

- You need to have **Java Development Kit (JDK) 21 or higher** installed.  
- This allows your system to compile and run Java programs.  
- If Java is not installed, download and install it from the OpenJDK website or Oracle’s JDK page.  
- After installation, verify it works by checking the version in a terminal.

---

## 2. Prepare the Project Folder and Clone the Repo

- Create a folder (for example - task-manager/) where you want the project to reside. 
- run -  `git clone https://github.com/sparsh240/Java-BYOP-24BAI10268.git task-manager/` to clone the repository. (using git bash in windows or terminal in macOS or linux)




---

## 4. Compile the Project

- Use your terminal to navigate into the main project folder.  
- Compile all Java files so the program can run. This step creates the necessary compiled files.  

- `javac Main.java model/*.java service/*.java util/*.java`

---

## 5. Run the Project
- run - `java Main` 
- After compilation, start the program.  
- You will see a menu allowing you to register a new user, login, and exit.  
- Once logged in, you can:
  - Add tasks  
  - View your tasks  
  - Mark tasks as done (completed tasks are removed from the list)  
  - Share tasks with other users  
  - Logout to return to the main menu  

- Data is automatically saved between sessions in the `data` folder.

---

## 6. Data Storage

- `users.dat` stores usernames and hashed passwords securely.  
- `tasks.dat` stores all tasks, their owners, and shared users.  
- The program handles reading and writing these files automatically.  
- Do not delete the `data` folder if you want to preserve your tasks and users.

---

## 7. Optional Suggestion

- You can add the project folder to your system’s PATH environment variable.  
- This allows you to run the program from any directory without having to navigate to the project folder each time.

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

## service/ (Logic Layer)

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


## Summary

This project is designed to be:
- Simple to use via terminal  
- Secure for multiple users  
- Scalable for future enhancements  