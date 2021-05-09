package carsharing.dao.init;

import carsharing.config.DbConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableDefinitions {
    private static final String CREATE_TBL_COMPANY = "CREATE TABLE IF NOT EXISTS company(" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR NOT NULL UNIQUE" +
            ")";

    private static final String CREATE_TBL_CAR = "CREATE TABLE IF NOT EXISTS car(" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR NOT NULL UNIQUE," +
            "company_id INT NOT NULL," +
            "FOREIGN KEY(company_id) REFERENCES company(id)" +
            ")";

    private static final String CREATE_TBL_CUSTOMER = "CREATE TABLE IF NOT EXISTS customer(" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR UNIQUE NOT NULL," +
            "rented_car_id INT," +
            "FOREIGN KEY (rented_car_id) REFERENCES car(id)" +
            ")";

    private static final String RESTART_TBL_COMPANY_ID = "ALTER TABLE company ALTER COLUMN id RESTART WITH 1;";
    private static final String RESTART_TBL_CUSTOMER_ID = "ALTER TABLE customer ALTER COLUMN id RESTART WITH 1;";

    private final DbConfig config;

    public TableDefinitions() {
        this.config = new DbConfig();
    }

    public void createTable() {
        try (Connection connection = config.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(CREATE_TBL_COMPANY);
                statement.executeUpdate(CREATE_TBL_CAR);
                statement.executeUpdate(CREATE_TBL_CUSTOMER);
                statement.executeUpdate(RESTART_TBL_COMPANY_ID);
                statement.executeUpdate(RESTART_TBL_CUSTOMER_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
