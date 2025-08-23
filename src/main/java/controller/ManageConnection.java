package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageConnection {

    String url = "jdbc:mysql://127.0.0.1:3306/palestra";
    //String url = "jdbc:mariadb://localhost:3306/Palestra";
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
