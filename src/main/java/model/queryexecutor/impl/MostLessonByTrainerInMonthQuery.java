package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class MostLessonByTrainerInMonthQuery implements Query {

    //OP-25	Visualizza i trainer con più lezioni in un determinato mese

    private final String QUERY = 
    "SELECT  t.CF, t.Nome, t.Cognome, COUNT(*) AS NumeroLezioni " +
    "FROM Trainer t, Edizioni_Corsi ed, Lezioni l " +
    "WHERE t.CF = ed.CF " +
    "AND ed.Data_Inizio = l.Data_Inizio " +
    "AND ed.Nome = l.nome " +
    "GROUP BY t.CF, t.Nome, t.Cognome " +
    "ORDER BY COUNT(t.CF) DESC " ;

    @Override
    public Optional<ResultSet> execute() {
        try{
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}