package config;

import java.sql.Connection;
import java.sql.SQLException;

public class TransaccionManager {
    private Connection connection;

    public void begin() throws SQLException {
        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws SQLException {
        if (connection != null) {
            connection.commit();
            connection.close();
        }
    }

    public void rollback() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }
}
