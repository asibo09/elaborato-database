package controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageConnection {

    private final String url = "jdbc:mysql://localhost:3306/gym";
    private final Connection connection;

    public ManageConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, "root", "prova");
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public Connection getConnection() {
        return this.connection;
    }
}
