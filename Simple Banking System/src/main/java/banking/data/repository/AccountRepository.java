package banking.data.repository;

import banking.data.DatabaseConfig;
import banking.domain.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import java.util.Random;

public class AccountRepository {

    private final DataSource dataSource;

    public AccountRepository() {
        this.dataSource = DatabaseConfig.getInstance().getDataSource();
        createTableCard();
    }

    public Optional<Account> findByCardNumber(String cardNumber) {
        String sql = "SELECT number, pin, balance FROM card WHERE number = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, cardNumber);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getString("number"),
                            resultSet.getString("pin"),
                            resultSet.getLong("balance")
                    );
                    return Optional.of(account);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void save(Account account) {
        String sql = "INSERT INTO card(id, number, pin) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, new Random().nextInt(100));
            stmt.setString(2, account.getCardNumber());
            stmt.setString(3, account.getPin());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(Account account) {
        String sql = "UPDATE card SET balance = ? WHERE number = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setLong(1, account.getBalance());
            stmt.setString(2, account.getCardNumber());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateTransfer(Account from, Account to) {
        String sql = "UPDATE card SET balance = ? WHERE number = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmtFrom = con.prepareStatement(sql);
             PreparedStatement stmtTo = con.prepareStatement(sql)
        ) {
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            stmtFrom.setLong(1, from.getBalance());
            stmtFrom.setString(2, from.getCardNumber());
            stmtFrom.executeUpdate();

            stmtTo.setLong(1, to.getBalance());
            stmtTo.setString(2, to.getCardNumber());
            stmtTo.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(Account account) {
        String sql = "DELETE FROM card WHERE number = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, account.getCardNumber());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createTableCard() {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0" +
                        ");";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
