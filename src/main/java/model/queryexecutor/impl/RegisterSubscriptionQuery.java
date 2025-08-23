package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class RegisterSubscriptionQuery implements Query {

    //OP-2	Registrare un abbonamento per un iscritto

    private final String QUERY = "INSERT INTO abbonamento_utente (Data_stipulazione, Tipo, Durata, CF) VALUES (?, ?, ?, ?)";

    private static final String selectQuery = "SELECT * FROM abbonamento_utente ORDER BY Data_stipulazione, Durata, Tipo, CF";

    private final Date dataStipulazione;
    private final String tipo;
    private final int durata;
    private final String cf;
    private final Connection connection;

    public RegisterSubscriptionQuery(
            final Date dataStipulazione,
            final String tipo,
            final int durata,
            final String cf,
            final Connection connection
    ) {
        this.dataStipulazione = dataStipulazione;
        this.tipo = tipo;
        this.durata = durata;
        this.cf = cf;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(this.QUERY);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                ){
            preparedStatement.setDate(1, dataStipulazione);
            preparedStatement.setString(2, tipo);
            preparedStatement.setInt(3, durata);
            preparedStatement.setString(4, cf);
            preparedStatement.executeUpdate();

            // Dopo l'insert fai la select di tutta la tabella            
            ResultSet resultSet = selectStatement.executeQuery();
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
                
            return Optional.of(crs);
        } catch (final SQLException e) {
            e.printStackTrace();;
        }
        return Optional.empty();
    }
}
