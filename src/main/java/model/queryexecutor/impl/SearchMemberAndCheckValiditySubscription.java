package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class SearchMemberAndCheckValiditySubscription implements Query {

    //OP-26	Ricerca di un iscritto e visualizzare se il suo abbonamento è ancora valido

    private final String QUERY = 
    "SELECT i.*, a.Data_stipulazione, a.Tipo, a.Durata " +
    "FROM ABBONAMENTO_UTENTE a, ISCRITTO i " +
    "WHERE i.CF = a.CF " +
    "AND i.Nome = ? " +
    "AND i.Cognome = ? " +
    "AND CURDATE() < DATE_ADD(a.Data_stipulazione, INTERVAL a.Durata DAY) ";

    private final String nome;
    private final String cognome;
    private final Connection connection;

    public SearchMemberAndCheckValiditySubscription(
            final String nome,
            final  String cognome,
            final Connection connection
    ) {
        this.nome = nome;
        this.cognome = cognome;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = this.connection.prepareStatement(QUERY);
        ){
            statement.setString(1, nome);
            statement.setString(2, cognome);
            final ResultSet resultSet = statement.executeQuery();
            
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);
        } catch(final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}