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
    "FROM Presenze_Sala_Pesi " +
    "WHERE CF = ( " +
    "   SELECT CF " +
    "   FROM Iscritti " +
    "   WHERE Nome = ? " +
    "   AND Cognome = ? " +
    "   LIMIT 1 " + 
    ")" +
    "GROUP BY CF ";

    private final String nome;
    private final String cognome;

    public MemberWeeklyAttendance(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    @Override
    public Optional<ResultSet> execute() {
        try {
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            ResultSet resultSet = statement.executeQuery();
            return Option.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}