package jdbc_task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class IsolationLevels {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "admin";
        String password = "pgadmin";

        Connection conn = null;
        PreparedStatement stmt1 = null, stmt2 = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);

            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            UUID studentUUID = UUID.randomUUID();
            UUID courseUUID = UUID.randomUUID();
            String enrollSql = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
            stmt1 = conn.prepareStatement(enrollSql);
            stmt1.setObject(1, studentUUID);
            stmt1.setObject(2, courseUUID);
            stmt1.executeUpdate();
            System.out.println("Enrollment inserted with default isolation level");

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            System.out.println("Isolation level changed to SERIALIZABLE");

            stmt2 = conn.prepareStatement(enrollSql);
            stmt2.setObject(1, studentUUID);
            stmt2.setObject(2, UUID.randomUUID());
            stmt2.executeUpdate();
            System.out.println("Enrollment inserted with SERIALIZABLE isolation level");

            conn.commit();
            System.out.println("Transaction committed");

        } catch (SQLException e) {
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
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
