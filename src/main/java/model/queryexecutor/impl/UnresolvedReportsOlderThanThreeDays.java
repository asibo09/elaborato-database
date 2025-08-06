package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UnresolvedReportsOlderThanThreeDays implements QueryExecutor {

    //OP-28	Visualizza le segnalazioni non risolte da più di 3 giorni
    
    private final String QUERY =
    "SELECT * " +
    "FROM Storico_Segnalazioni s " +
    "WHERE Stato = ‘Non risolto’ " +
    "AND s.Data <= DATE_SUB(CURDATE(), INTERVAL 3 DAY) ";

    @Override
    public Optional<ResultSet> execute() {
        try {
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
