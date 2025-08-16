package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

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

            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}