package carsharing.config;

import carsharing.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private final String connectionUrl;

    public DbConfig() {
        String filePath = String.format("./src/carsharing/db/%s", Main.getFileName());
        this.connectionUrl = String.format("jdbc:h2:%s", filePath);
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
