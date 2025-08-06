package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LastYearReportsQuery implements QueryExecutor {

    //OP-17 Visualizzare le segnalazioni nell’ultimo anno

    private final String QUERY = 
    "SELECT * " +
    "FROM Storico_Segnalazioni " +
    "WHERE Data >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) " ;

    @Override
    public Optional<ResultSet> execute() {
        try {
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}