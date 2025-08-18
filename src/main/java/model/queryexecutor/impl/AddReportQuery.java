package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddReportQuery implements Query {

    //OP-12	Inserimento di una segnalazione

    private final String QUERY = "INSERT INTO storico_segnalazioni (Data, Ora, CF, Stato, Descrizione, Numero_macchinario, Nome_macchinario)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final java.sql.Date date;
    private final java.sql.Time time;
    private final String state;
    private final String description;
    private final int machineId;
    private final String machineName;
    private final String cf;
    private final Connection connection;

    public AddReportQuery(
            final Connection connection,
            final java.sql.Date date,
            final java.sql.Time time,
            final String cf,
            final String state,
            final String description,
            final int machineId,
            final String machineName
    ) {
        this.connection = connection;
        this.date = date;
        this.time = time;
        this.cf = cf;
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
            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, time);
            preparedStatement.setString(3, cf);
            preparedStatement.setString(4, state);
            preparedStatement.setString(5, description);
            preparedStatement.setInt(6, machineId);
            preparedStatement.setString(7, machineName);
            preparedStatement.executeUpdate();
            
            // Dopo l'insert fai la select di tutta la tabella
            String selectQuery = "SELECT * FROM storico_segnalazioni";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet rs = selectStatement.executeQuery();

            return Optional.of(rs);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
