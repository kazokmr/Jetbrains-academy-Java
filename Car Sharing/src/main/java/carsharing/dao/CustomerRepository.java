package carsharing.dao;

import carsharing.config.DbConfig;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final String SELECT_CUSTOMERS = "" +
            "SELECT " +
            "customer.id," +
            "customer.name," +
            "customer.rented_car_id," +
            "car.id," +
            "car.name," +
            "company.id," +
            "company.name " +
            "FROM " +
            "customer " +
            "LEFT OUTER JOIN " +
            "car " +
            "ON " +
            "car.id = customer.rented_car_id " +
            "LEFT OUTER JOIN " +
            "company " +
            "ON " +
            "company.id = car.company_id " +
            "ORDER BY " +
            "customer.id ASC;";
    private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER(name) values (?);";
    private static final String UPDATE_RENTED_CAR_ID = "UPDATE CUSTOMER SET rented_car_id = ? WHERE id = ?;";
    private final DbConfig config;

    public CustomerRepository() {
        this.config = new DbConfig();
    }

    public List<Customer> findAll() {
        return read();
    }

    public void saveRentedCar(Customer customer) {
        update(customer);
    }

    public void save(Customer customer) {
        create(customer);
    }

    private List<Customer> read() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMERS)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        customerList.add(getCustomer(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    private Customer getCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2));
        resultSet.getInt(3);
        if (!resultSet.wasNull()) {
            Company company = new Company(resultSet.getInt(6), resultSet.getString(7));
            Car rentedCar = new Car(resultSet.getInt(4), resultSet.getString(5), company);
            customer.setRentedCar(rentedCar);
        }
        return customer;
    }

    private void update(Customer customer) {
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_RENTED_CAR_ID)) {
                if (customer.hasRentedCar()) {
                    statement.setInt(1, customer.getRentedCar().getId());
                } else {
                    statement.setNull(1, Types.NULL);
                }
                statement.setInt(2, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void create(Customer customer) {
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER)) {
                statement.setString(1, customer.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

