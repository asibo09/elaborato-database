package model.queryexecutor.impl;


import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class UnresolvedReportsOlderThanThreeDays implements Query {

    //OP-17	Visualizza le segnalazioni non risolte da più di 3 giorni
    
    private final String QUERY =
    "SELECT * " +
    "FROM storico_segnalazioni s " +
    "WHERE Stato = 'Non risolto' " +
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

            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
