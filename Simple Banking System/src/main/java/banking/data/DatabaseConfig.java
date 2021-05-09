package banking.data;

import org.sqlite.SQLiteDataSource;

public class DatabaseConfig {

    private static final DatabaseConfig instance = new DatabaseConfig();
    private String dbFile;

    private DatabaseConfig() {
    }

    public static DatabaseConfig getInstance() {
        return instance;
    }

    public SQLiteDataSource getDataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(String.format("jdbc:sqlite:%s", dbFile));
        return dataSource;
    }

    public void setDbFile(String dbFile) {
        this.dbFile = dbFile;
    }
}
