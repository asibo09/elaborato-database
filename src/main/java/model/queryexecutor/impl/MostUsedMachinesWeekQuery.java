package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

public class MostUsedMachinesWeekQuery implements Query {

    //OP-24	Visualizza i macchinari più usati in settimana

    private final String QUERY = 
    "SELECT m.Numero_Macchinario, m.Nome_Macchinario, COUNT(*) AS Totale_Utilizzi " +
    "FROM macchinario m, attrezzo_Lezione a, lezione i " +
    "WHERE m.Numero_Macchinario = a.Numero_Macchinario " +
    "AND m.Nome_Macchinario = a.Nome_Macchinario " +
    "AND i.Data = a.Data AND i.Ora = a.Ora AND i.Codice_Sala = a.Codice_Sala " +
    "AND i.Data BETWEEN ? AND ? " +
    "GROUP BY m.Numero_Macchinario, m.Nome_Macchinario " +
    "ORDER BY Totale_Utilizzi " ;

    private final Date startDate;
    private final Date endDate;
    private final Connection connection;

    public MostUsedMachinesWeekQuery(final Date startDate, final Date endDate, final Connection connection) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement statement = connection.prepareStatement(QUERY);

        ){
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
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