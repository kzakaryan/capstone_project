package jdbc_task2;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class DBUtilsTest {

    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        // Connect to the embedded H2 database
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "pgadmin");

        // Set up the schema (create tables, etc.)
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";");
            statement.execute("DROP TABLE IF EXISTS Enrollment, Course_Prerequisite, Course, Student, Instructor, Administrator, Users CASCADE;");
            statement.execute("CREATE TABLE IF NOT EXISTS Users (" +
                    "user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "first_name VARCHAR(100) NOT NULL, " +
                    "last_name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "is_active BOOLEAN DEFAULT TRUE, " +
                    "role VARCHAR(50) CHECK (role IN ('student', 'instructor', 'admin')) NOT NULL);");
            statement.execute("CREATE TABLE IF NOT EXISTS Student (" +
                    "student_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE, " +
                    "credits INT CHECK (credits >= 0));");
            statement.execute("CREATE TABLE IF NOT EXISTS Instructor (" +
                    "instructor_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE);");
            statement.execute("CREATE TABLE IF NOT EXISTS Administrator (" +
                    "admin_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "user_id UUID UNIQUE REFERENCES Users(user_id) ON DELETE CASCADE);");
            statement.execute("CREATE TABLE IF NOT EXISTS Course (" +
                    "course_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "course_name VARCHAR(255) NOT NULL, " +
                    "course_description TEXT, " +
                    "course_credits INT CHECK (course_credits > 0), " +
                    "instructor_id UUID REFERENCES Instructor(instructor_id) ON DELETE SET NULL, " +
                    "capacity INT CHECK (capacity > 0));");
            statement.execute("CREATE TABLE IF NOT EXISTS Course_Prerequisite (" +
                    "course_id UUID REFERENCES Course(course_id) ON DELETE CASCADE, " +
                    "prerequisite_id UUID REFERENCES Course(course_id) ON DELETE CASCADE, " +
                    "PRIMARY KEY (course_id, prerequisite_id));");
            statement.execute("CREATE TABLE IF NOT EXISTS Enrollment (" +
                    "enrollment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(), " +
                    "student_id UUID REFERENCES Student(student_id) ON DELETE CASCADE, " +
                    "course_id UUID REFERENCES Course(course_id) ON DELETE CASCADE, " +
                    "enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "UNIQUE(student_id, course_id));");
        }
    }

    @Test
    public void testInsertUser() throws SQLException {
        String insertUserQuery = "INSERT INTO Users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
            statement.setString(1, "John");
            statement.setString(2, "Doe");
            statement.setString(3, "john.doe@example.com");
            statement.setString(4, "password123");
            statement.setString(5, "student");
            statement.executeUpdate();
        }

        String selectQuery = "SELECT first_name, last_name FROM Users WHERE email = 'john.doe@example.com'";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet rs = statement.executeQuery()) {
            assertTrue(rs.next());
            assertEquals("John", rs.getString("first_name"));
            assertEquals("Doe", rs.getString("last_name"));
        }
    }

    @Test
    public void testInsertStudent() throws SQLException {
        String insertUserQuery = "INSERT INTO Users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
            statement.setString(1, "Jane");
            statement.setString(2, "Doe");
            statement.setString(3, "jane.doe@example.com");
            statement.setString(4, "password123");
            statement.setString(5, "student");
            statement.executeUpdate();
        }

        String getUserIdQuery = "SELECT user_id FROM Users WHERE email = 'jane.doe@example.com'";
        UUID userId = null;
        try (PreparedStatement statement = connection.prepareStatement(getUserIdQuery);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                userId = rs.getObject("user_id", UUID.class);
            }
        }

        String insertStudentQuery = "INSERT INTO Student (user_id, credits) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertStudentQuery)) {
            statement.setObject(1, userId);
            statement.setInt(2, 30);
            statement.executeUpdate();
        }

        String selectStudentQuery = "SELECT credits FROM Student WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectStudentQuery)) {
            statement.setObject(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(30, rs.getInt("credits"));
            }
        }
    }

    @Test
    public void testEnrollStudentInCourse() throws SQLException {
        String insertUserQuery = "INSERT INTO Users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
            statement.setString(1, "Alice");
            statement.setString(2, "Smith");
            statement.setString(3, "alice.smith@example.com");
            statement.setString(4, "password123");
            statement.setString(5, "student");
            statement.executeUpdate();
        }

        String insertCourseQuery = "INSERT INTO Course (course_name, course_description, course_credits, capacity) VALUES (?, ?, ?, ?)";
        UUID courseId = null;
        try (PreparedStatement statement = connection.prepareStatement(insertCourseQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, "Math 101");
            statement.setString(2, "Introductory Mathematics");
            statement.setInt(3, 3);
            statement.setInt(4, 30);
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    courseId = rs.getObject(1, UUID.class);
                }
            }
        }

        String getUserIdQuery = "SELECT user_id FROM Users WHERE email = 'alice.smith@example.com'";
        UUID studentId = null;
        try (PreparedStatement statement = connection.prepareStatement(getUserIdQuery);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                studentId = rs.getObject("user_id", UUID.class);
            }
        }

        String insertEnrollmentQuery = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertEnrollmentQuery)) {
            statement.setObject(1, studentId);
            statement.setObject(2, courseId);
            statement.executeUpdate();
        }

        String selectEnrollmentQuery = "SELECT student_id FROM Enrollment WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectEnrollmentQuery)) {
            statement.setObject(1, studentId);
            statement.setObject(2, courseId);
            try (ResultSet rs = statement.executeQuery()) {
                assertTrue(rs.next());
                assertEquals(studentId, rs.getObject("student_id"));
            }
        }
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}