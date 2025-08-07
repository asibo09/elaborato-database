package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LastYearReportsQuery implements Query {

    //OP-17 Visualizzare le segnalazioni nell’ultimo anno

    private final String QUERY = 
    "SELECT * " +
    "FROM Storico_Segnalazioni " +
    "WHERE Data >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) ";
    private final Connection connection;

    public LastYearReportsQuery(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY);
                ){
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
               return Optional.of(resultSet);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}