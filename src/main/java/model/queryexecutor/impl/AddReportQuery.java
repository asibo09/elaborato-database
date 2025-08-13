package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddReportQuery implements Query {

    //OP-12	Inserimento di una segnalazione

    private final String QUERY = "INSERT INTO storico_segnalazioni (Numero segnalazione, Data, Ora, Stato, Descrizione, Numero Macchinario, Nome Macchinario)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String numeroSegnalazione;
    private final java.sql.Date date;
    private final java.sql.Time time;
    private final String state;
    private final String description;
    private final int machineId;
    private final String machineName;
    private final Connection connection;

    public AddReportQuery(
            final Connection connection,
            final String numeroSegnalazione,
            final java.sql.Date date,
            final java.sql.Time time,
            final String state,
            final String description,
            final int machineId,
            final String machineName
    ) {
        this.connection = connection;
        this.numeroSegnalazione = numeroSegnalazione;
        this.date = date;
        this.time = time;
        this.state = state;
        this.description = description;
        this.machineId = machineId;
        this.machineName = machineName;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(this.QUERY);
                ){
            preparedStatement.setString(1, numeroSegnalazione);
            preparedStatement.setDate(2, date);
            preparedStatement.setTime(3, time);
            preparedStatement.setString(4, state);
            preparedStatement.setString(5, description);
            preparedStatement.setInt(6, machineId);
            preparedStatement.setString(7, machineName);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
