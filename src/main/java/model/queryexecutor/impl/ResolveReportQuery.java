package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class ResolveReportQuery implements Query {

    // OP-20 Aggiornamento lo stato di una segnalazione a "Risolto"

    private static final String QUERY =
        "UPDATE storico_segnalazioni SET Stato = 'Risolto' " +
        "WHERE Numero_segnalazione = ? AND Nome_macchinario = ? AND Numero_macchinario = ?";

    private static final String selectQuery = "SELECT * FROM storico_segnalazioni";
    
    private final Connection connection;
    private final int numero_segnalazione;
    private final String nome_macchinario;
    private final int numero_macchinario;

    public ResolveReportQuery(Connection connection, int numero_segnalazione, String nome_macchinario, int numero_macchinario) {
        this.connection = connection;
        this.numero_segnalazione = numero_segnalazione;
        this.nome_macchinario = nome_macchinario;
        this.numero_macchinario = numero_macchinario;

    }

    @Override
    public Optional<ResultSet> execute() {
        try (PreparedStatement ps = connection.prepareStatement(QUERY);
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);) {
            ps.setInt(1, numero_segnalazione);
            ps.setString(2, nome_macchinario);
            ps.setInt(3, numero_macchinario);

            int updatedRows = ps.executeUpdate();

            if (updatedRows > 0) {
                // Dopo l'insert fai la select di tutta la tabella
                ResultSet resultSet = selectStatement.executeQuery();
                CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
                crs.populate(resultSet);

                if (!crs.isBeforeFirst()) {
                    return Optional.empty();
                }
                return Optional.of(crs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
