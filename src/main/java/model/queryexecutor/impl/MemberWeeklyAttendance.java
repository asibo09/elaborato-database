package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MemberWeeklyAttendance implements Query {
    
    //OP-29	Visualizzare la media settimanale delle presenze di un iscritto

    private final String QUERY = 
    "SELECT CF, COUNT(*) / 52 AS Media_Settimanale " +
    "FROM presenza_sala_pesi " +
    "WHERE CF = ( " +
    "   SELECT CF " +
    "   FROM iscritto " +
    "   WHERE Nome = ? " +
    "   AND Cognome = ? " +
    "   LIMIT 1 " + 
    ") " +
    "GROUP BY CF ";

    private final String nome;
    private final String cognome;
    private final Connection connection;

    public MemberWeeklyAttendance(final String nome, final String cognome, final Connection connection) {
        this.nome = nome;
        this.cognome = cognome;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY);
                ){
            statement.setString(1, nome);
            statement.setString(2, cognome);
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