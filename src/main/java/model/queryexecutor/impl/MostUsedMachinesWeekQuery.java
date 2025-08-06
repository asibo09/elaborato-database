package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

public class MostUsedMachinesWeekQuery implements Query {

    //OP-24	Visualizza i macchinari più usati in settimana

    private final String QUERY = 
    "SELECT m.Numero_Macchinario, m.Nome_Macchinario, COUNT(*) AS Totale_Utilizzi " +
    "FROM Macchinari m, Attrezzi_Lezione a, Lezione i " +
    "WHERE m.Numero_Macchinario = a.Numero_Macchinario " +
    "AND m.Nome_Macchinario = a.Nome_Macchinario " +
    "AND i.Data = a.Data AND i.Ora = a.Ora AND i.Codice_Sala = a.Codice_Sala " +
    "AND i.Data BETWEEN ? AND ? " +
    "GROUP BY m.Numero_Macchinario, m.Nome_Macchinario " +
    "ORDER BY Totale_Utilizzi " ;

    private final Date startDate;
    private final Date endDate;

    public MostUsedMachinesWeekQuery(final Date startDate, final Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Optional<ResultSet> execute() {
        try {
            Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultSet);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}