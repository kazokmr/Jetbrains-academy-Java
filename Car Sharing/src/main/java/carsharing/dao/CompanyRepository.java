package carsharing.dao;

import carsharing.config.DbConfig;
import carsharing.entity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private static final String SELECT_COMPANY = "SELECT id, name FROM company ORDER BY id ASC;";
    private static final String INSERT_COMPANY = "INSERT INTO company(name) VALUES (?);";
    private final DbConfig config;

    public CompanyRepository() {
        this.config = new DbConfig();
    }

    public List<Company> findAll() {
        return read();
    }

    public void save(Company company) {
        create(company);
    }

    private List<Company> read() {
        List<Company> companyList = new ArrayList<>();
        try (Connection connection = config.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(SELECT_COMPANY)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        companyList.add(new Company(id, name));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    private void create(Company company) {
        try (Connection connection = config.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_COMPANY)) {
                statement.setString(1, company.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
