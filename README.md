# Course Registration and Management System (CLI-Based)

## 📌 Overview
This is a **CLI-based Java application** for course registration and management, built with **SOLID principles** and modularized for **educational use**. It allows **students, instructors, and administrators** to manage courses, enrollments, and grades efficiently.

The project follows a **microservices-inspired architecture**, dividing responsibilities into three subsystems:
1. **Course Management** - CRUD for courses
2. **User Management** - Authentication and user accounts
3. **Library Management** - Enrollment, grades, notifications

---

## 🎯 Features

### ✅ **For Students**
- View available courses
- Enroll in courses (with prerequisite and capacity checks)
- Withdraw from courses (before deadlines)
- View class schedule
- View grades

### ✅ **For Instructors**
- View assigned courses
- View student rosters
- Submit student grades
- Communicate with students

### ✅ **For Administrators**
- Add, edit, and remove courses
- Manage students and instructors
- Assign instructors to courses
- Set registration and withdrawal deadlines
- Generate reports (enrollment, grades)

---

## 🚀 Getting Started

### **Prerequisites**
- Java **17+**
- Maven **(for dependency management)**
- Docker **(for containerized deployment)**
- JUnit 5 & Mockito **(for testing)**

### **Installation & Setup**
1. **Clone the repository:**
```bash
git@github.com:kzakaryan/capstone_project.git