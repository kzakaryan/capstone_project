package jdbc_task3;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MultiThreadedSimulation implements Runnable {

    private final DataSource dataSource;

    public MultiThreadedSimulation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT pg_sleep(2);"; // Simulate a long operation
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeQuery();
                System.out.println("Thread " + Thread.currentThread().getName() + " completed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "admin";
        String password = "pgadmin";

        try {
            DataSource dataSource = new CustomDataSource(jdbcUrl, username, password);

            long startTime = System.currentTimeMillis();

            int numberOfThreads = 10;
            Thread[] threads = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i] = new Thread(new MultiThreadedSimulation(dataSource));
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("All threads finished in " + (endTime - startTime) + " ms");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}