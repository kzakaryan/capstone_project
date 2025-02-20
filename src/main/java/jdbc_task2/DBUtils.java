package jdbc_task2;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class DBUtils {

    public static void execute(String query, Object... args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "pgadmin");
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, args);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String query, Consumer<PreparedStatement> consumer) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "pgadmin");
             PreparedStatement statement = connection.prepareStatement(query)) {

            consumer.accept(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> T findOne(String query, Function<ResultSet, T> mapper, Object... args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "pgadmin");
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, args);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapper.apply(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> List<T> findMany(String query, Function<ResultSet, T> mapper, Object... args) {
        List<T> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "admin", "pgadmin");
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, args);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                results.add(mapper.apply(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    private static void setParameters(PreparedStatement stmt, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
    }
}

