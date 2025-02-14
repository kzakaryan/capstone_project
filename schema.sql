CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
-- Ensure idempotency (drop tables if they exist)
DROP TABLE IF EXISTS Enrollment, Course_Prerequisite, Course, Student, Instructor, Administrator, Users CASCADE;

-- Users Table (Base entity for all users)
CREATE TABLE IF NOT EXISTS Users (
                                     user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                     first_name VARCHAR(100) NOT NULL,
                                     last_name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     is_active BOOLEAN DEFAULT TRUE,
                                     role VARCHAR(50) CHECK (role IN ('student', 'instructor', 'admin')) NOT NULL
);

-- One-To-One: User ↔ Student
CREATE TABLE IF NOT EXISTS Student (
                                       student_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                       user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE,
                                       credits INT CHECK (credits >= 0)
);

-- One-To-One: User ↔ Instructor
CREATE TABLE IF NOT EXISTS Instructor (
                                          instructor_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                          user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE
);

-- One-To-One: User ↔ Administrator
CREATE TABLE IF NOT EXISTS Administrator (
                                             admin_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                             user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE
);

-- One-To-Many: Instructor ↔ Course
CREATE TABLE IF NOT EXISTS Course (
                                      course_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                      course_name VARCHAR(255) NOT NULL,
                                      course_description TEXT,
                                      course_credits INT CHECK (course_credits > 0),
                                      instructor_id UUID REFERENCES Instructor(instructor_id) ON DELETE SET NULL,
                                      capacity INT CHECK (capacity > 0)
);

-- Many-To-Many: Course Prerequisites (Self-Referencing)
CREATE TABLE IF NOT EXISTS Course_Prerequisite (
                                                   course_id UUID REFERENCES Course(course_id) ON DELETE CASCADE,
                                                   prerequisite_id UUID REFERENCES Course(course_id) ON DELETE CASCADE,
                                                   PRIMARY KEY (course_id, prerequisite_id)
);

-- Many-To-Many: Student ↔ Course (via Enrollment)
CREATE TABLE IF NOT EXISTS Enrollment (
                                          enrollment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                          student_id UUID REFERENCES Student(student_id) ON DELETE CASCADE,
                                          course_id UUID REFERENCES Course(course_id) ON DELETE CASCADE,
                                          enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          UNIQUE(student_id, course_id) -- Prevent duplicate enrollments
);
