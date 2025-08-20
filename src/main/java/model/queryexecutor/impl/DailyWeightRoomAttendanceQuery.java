package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DailyWeightRoomAttendanceQuery implements Query {

    // OP-14 Visualizzare presenze in sala pesi per un determinato giorno
    private final String QUERY = 
        "SELECT I.CF, I.Nome, I.Cognome, P.Data, P.Ora " +
        "FROM PRESENZA_SALA_PESI P " +
        "JOIN ISCRITTO I ON P.CF = I.CF " +
        "WHERE P.Data = ? " +
        "GROUP BY I.CF, I.Nome, I.Cognome, P.Data, P.Ora " +
        "ORDER BY P.Ora";

    private final Connection connection;
    private final java.sql.Date date;

    public DailyWeightRoomAttendanceQuery(Connection connection, java.sql.Date date) {
        this.connection = connection;
        this.date = date;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setDate(1, date);

            ResultSet resultSet = preparedStatement.executeQuery();
            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(resultSet);

            if (!cachedRowSet.isBeforeFirst()) { 
                return Optional.empty();
            }
            return Optional.of(cachedRowSet);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
