package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class ViewWorkoutPlanQuery implements Query {

    // OP-20    Visualizza una scheda di un iscritto

    private final String QUERY =
    "SELECT U.CF, U.Codice_scheda, M.Posizione, M.Nome_esercizio, C.Peso, C.Numero_ripetizioni, C.recupero, C.quantita " +
    "FROM utilizzo_scheda U, modalita_esercizio M, configurazione C " +
    "WHERE U.Codice_scheda = M.Codice_scheda " +
    "AND M.Codice_scheda = C.Codice_scheda " +
    "AND M.Posizione = C.Posizione " +
    "AND U.CF = ? " +
    "ORDER BY M.Posizione" ;

    private final String CF;
    private final Connection connection;

    public ViewWorkoutPlanQuery(final String CF, final Connection connection) {
        this.CF = CF;
        this.connection = connection;
    }


    @Override
    public Optional<ResultSet> execute() {
        try (
            PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY)
        ) {
            preparedStatement.setString(1, this.CF);

            final ResultSet resultSet = preparedStatement.executeQuery();

            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);

        } catch (final SQLException e) {
            throw new RuntimeException("Errore durante l'esecuzione della query 20", e);
        }

    }

}