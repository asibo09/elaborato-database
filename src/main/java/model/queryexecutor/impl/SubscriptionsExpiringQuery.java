package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

public class SubscriptionsExpiringQuery implements Query {

    //OP-23	Visualizza gli abbonamenti che scadono entro 7 giorni

    private final String QUERY =
    "SELECT * " +
    "FROM Abbonamenti_Utente " +
    "WHERE DATEDIFF(DATE_ADD(Data_stipulazione, Durata), CURDATE()) <= 7 ";
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
            if(resultSet.next()) {
                return Optional.of(resultSet);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}