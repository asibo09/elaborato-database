package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class SubscriptionsExpiringQuery implements Query {

    //OP-23	Visualizza gli abbonamenti che scadono entro 7 giorni

    private final String QUERY =
    "SELECT *, DATEDIFF(DATE_ADD(Data_stipulazione, INTERVAL Durata DAY), CURDATE()) AS GiorniResidui " +
    "FROM abbonamento_utente " +
    "WHERE DATEDIFF(DATE_ADD(Data_stipulazione, INTERVAL Durata DAY), CURDATE()) BETWEEN 0 AND 7 ";
    private final Connection connection;

    public SubscriptionsExpiringQuery(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try(
                PreparedStatement statement = this.connection.prepareStatement(QUERY);
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