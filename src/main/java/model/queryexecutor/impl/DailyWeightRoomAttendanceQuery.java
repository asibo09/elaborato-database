package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DailyWeightRoomAttendanceQuery implements Query {

    //OP-14	Visualizzare presenze in sala pesi per un determinato giorno

    private final String QUERY = "SELECT I.CF, I.Nome, I.Cognome, P.Data, P.Ora, COUNT(*) numPresenze" +
            "FROM presenze_sala_pesi P, iscritti I" +
            "WHERE P.CF = I.CF" +
            "AND P.Data = ?" +
            "GROUP BY I.CF, I.Nome, I.Cognome, P.Data, P.Ora" +
            "ORDER BY P.Ora";
    private final java.sql.Date date;

    public DailyWeightRoomAttendanceQuery(final java.sql.Date date) {
        this.date = date;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(this.QUERY);
                ) {
            preparedStatement.setDate(1, this.date);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Optional.of(resultSet);
            }
            resultSet.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
