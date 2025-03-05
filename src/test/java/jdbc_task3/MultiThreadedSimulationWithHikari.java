package jdbc_task3;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MultiThreadedSimulationWithHikari implements Runnable {

    private final DataSource dataSource;

    public MultiThreadedSimulationWithHikari(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT pg_sleep(2);";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeQuery();
                System.out.println("Thread " + Thread.currentThread().getName() + " completed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("admin");
        config.setPassword("pgadmin");
        config.setMaximumPoolSize(10);

        HikariDataSource dataSource = new HikariDataSource(config);

        long startTime = System.currentTimeMillis();

        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new MultiThreadedSimulationWithHikari(dataSource));
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
    }
}