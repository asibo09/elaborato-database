package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MostLessonByTrainerInMonthQuery implements Query {

    //OP-25	Visualizza i trainer con più lezioni in un determinato mese

    private final String QUERY = 
    "SELECT  t.CF, t.Nome, t.Cognome, COUNT(*) AS NumeroLezioni " +
    "FROM trainer t, edizione_Corso ed, lezione l " +
    "WHERE t.CF = ed.CF " +
    "AND ed.Data_Inizio = l.Data_Inizio " +
    "AND ed.Nome = l.nome " +
    "GROUP BY t.CF, t.Nome, t.Cognome " +
    "ORDER BY COUNT(t.CF) DESC ";
    private final Connection connection;

    public MostLessonByTrainerInMonthQuery(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try(
                PreparedStatement statement = connection.prepareStatement(QUERY)
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