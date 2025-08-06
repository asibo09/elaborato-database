package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SearchMemberAndCheckValiditySubscription implements QueryExecutor {

    //OP-26	Ricerca di un iscritto e visualizzare se il suo abbonamento è ancora valido

    private final String QUERY = 
    "SELECT i.*, a.Data_stipulazione, a.Tipo, a.Durata " +
    "FROM Abbonamenti_Utente a, Iscritti i " +
    "WHERE i.CF = a.CF " +
    "AND i.Nome = ? " +
    "AND i.Cognome = ? " +
    "AND i.Data_nascita = ? " +
    "AND CURDATE() < DATE_ADD(a.Data_stipulazione, INTERVAL a.Durata DAY) ";

    private final String nome;
    private final String cognome;
    private final Date dataNascita;

    public SearchMemberAndCheckValiditySubscription(String nome, String cognome, Date dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    @Override
    public Optional<ResultSet> execute() {
        try{
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setDate(3, dataNascita);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}