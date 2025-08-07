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
    private final Connection connection;

    public DailyWeightRoomAttendanceQuery(final Connection connection, final java.sql.Date date) {
        this.connection = connection;
        this.date = date;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = this.connection.prepareStatement(this.QUERY);
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
