package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class SubscriptionsExpiringQuery implements QueryExecutor {

    //OP-23	Visualizza gli abbonamenti che scadono entro 7 giorni

    private final String QUERY =
    "SELECT * " +
    "FROM Abbonamenti_Utente " +
    "WHERE DATEDIFF(DATE_ADD(Data_stipulazione, Durata), CURDATE()) <= 7 " ;

    @Override
    public Optional<ResultSet> execute() {
        try{
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.preparedStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}