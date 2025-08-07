package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UnresolvedReportsOlderThanThreeDays implements Query {

    //OP-28	Visualizza le segnalazioni non risolte da più di 3 giorni
    
    private final String QUERY =
    "SELECT * " +
    "FROM Storico_Segnalazioni s " +
    "WHERE Stato = ‘Non risolto’ " +
    "AND s.Data <= DATE_SUB(CURDATE(), INTERVAL 3 DAY) ";
    private final Connection connection;

    public UnresolvedReportsOlderThanThreeDays(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = this.connection.prepareStatement(QUERY);
                ) {
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return Optional.of(resultSet);
            }
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
