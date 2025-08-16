package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class MostUsedMachinesWeekQuery implements Query {

    //OP-24	Visualizza i macchinari più usati in settimana

    private final String QUERY = 
    "SELECT m.Numero_macchinario, m.Nome_macchinario, COUNT(*) AS Totale_Utilizzi " +
    "FROM macchinario m, attrezzo_lezione a, lezione i " +
    "WHERE m.Numero_macchinario = a.Numero_macchinario " +
    "AND m.Nome_macchinario = a.Nome_macchinario " +
    "AND i.Data = a.Data AND i.Ora = a.Ora AND i.Codice_Sala = a.Codice_Sala " +
    "AND i.Data BETWEEN ? AND ? " +
    "GROUP BY m.Numero_macchinario, m.Nome_macchinario " +
    "ORDER BY Totale_Utilizzi Desc" ;

    private final Date startDate;
    private final Date endDate;
    private final Connection connection;

    public MostUsedMachinesWeekQuery(final Connection connection) {
        this.connection = connection;

        // Calcolo inizio e fine settimana
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        this.startDate = java.sql.Date.valueOf(startOfWeek);
        this.endDate = java.sql.Date.valueOf(endOfWeek);
    }

    @Override
    public Optional<ResultSet> execute() {
        try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            final ResultSet resultSet = statement.executeQuery();
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}