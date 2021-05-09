package carsharing.dao;

import carsharing.config.DbConfig;
import carsharing.entity.Car;
import carsharing.entity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {
    private static final String SELECT_CAR = "SELECT id, name, company_id " +
            "FROM car WHERE company_id = ? ORDER BY id ASC;";
    private static final String INSERT_CAR = "INSERT INTO car(name,company_id) VALUES (?,?)";
    private final DbConfig config;

    public CarRepository() {
        this.config = new DbConfig();
    }

    public List<Car> findBy(Company company) {
        return read(company);
    }

    public void save(Car car) {
        create(car);
    }

    private List<Car> read(Company company) {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR)) {
                statement.setInt(1, company.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        carList.add(new Car(id, name, company));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    private void create(Car car) {
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CAR)) {
                statement.setString(1, car.getName());
                statement.setInt(2, car.getCompanyId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
