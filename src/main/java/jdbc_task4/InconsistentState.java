package jdbc_task4;
import java.sql.*;
import java.util.UUID;

public class InconsistentState {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "admin";
        String password = "pgadmin";

        Connection conn = null;
        PreparedStatement stmt1 = null, stmt2 = null, stmt3 = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            conn.setAutoCommit(false);

            UUID studentUUID = UUID.randomUUID();
            String insertStudentSql = "INSERT INTO Student (student_id, user_id, credits) VALUES (?, ?, ?)";
            stmt1 = conn.prepareStatement(insertStudentSql);
            stmt1.setObject(1, studentUUID);
            stmt1.setObject(2, UUID.randomUUID());
            stmt1.setInt(3, 30);
            stmt1.executeUpdate();
            System.out.println("Step 1: Student inserted");

            UUID courseUUID = UUID.randomUUID();
            String enrollSql = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
            stmt2 = conn.prepareStatement(enrollSql);
            stmt2.setObject(1, studentUUID);
            stmt2.setObject(2, courseUUID);
            stmt2.executeUpdate();
            System.out.println("Step 2: Enrollment inserted");

            System.out.println("Simulating system crash...");
            Thread.sleep(5000);

            String updateCapacitySql = "UPDATE Course SET capacity = capacity - 1 WHERE course_id = ?";
            stmt3 = conn.prepareStatement(updateCapacitySql);
            stmt3.setObject(1, courseUUID);
            stmt3.executeUpdate();
            System.out.println("Step 3: Course capacity updated");

            conn.commit();

            System.out.println("Transaction committed successfully");
        } catch (SQLException | InterruptedException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (stmt1 != null) stmt1.close();
                if (stmt2 != null) stmt2.close();
                if (stmt3 != null) stmt3.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}