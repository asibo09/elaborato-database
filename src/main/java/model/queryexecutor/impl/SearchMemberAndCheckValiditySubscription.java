package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SearchMemberAndCheckValiditySubscription implements Query {

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
    private final Connection connection;

    public SearchMemberAndCheckValiditySubscription(
            final String nome,
            final  String cognome,
            final Date dataNascita,
            final Connection connection
    ) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = this.connection.prepareStatement(QUERY);
        ){
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setDate(3, dataNascita);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return Optional.of(resultSet);
            }
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}