package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterSubscriptionQuery implements QueryExecutor {

    private static final String QUERY = "INSERT INTO abbonamento_utenti (Data stipulazione, Tipo, Durata, CF) VALUES (?, ?, ?, ?)";

    private final String dataStipulazione;
    private final String tipo;
    private final java.sql.Time durata;
    private final String cf;

    public RegisterSubscriptionQuery(
            final String dataStipulazione,
            final String tipo,
            final java.sql.Time durata,
            final String cf
    ) {
        this.dataStipulazione = dataStipulazione;
        this.tipo = tipo;
        this.durata = durata;
        this.cf = cf;
    }

    @Override
    public ResultSet execute() {
        try (
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)
                ){
            preparedStatement.setString(1, dataStipulazione);
            preparedStatement.setString(2, tipo);
            preparedStatement.setTime(3, durata);
            preparedStatement.setString(4, cf);

            preparedStatement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
