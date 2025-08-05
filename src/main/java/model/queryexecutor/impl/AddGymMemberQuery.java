package model.queryexecutor.impl;

import model.queryexecutor.api.QueryExecutor;
import controller.Controller;

import java.sql.*;
import java.util.Optional;

public class AddGymMemberQuery implements QueryExecutor {

    private final java.sql.Connection connection;
    //Parametri da inserire nella query
    private final String CF;
    private final String nome;
    private final String cognome;
    private final String citta;
    private final String via;
    private final String civico;
    private int numeroTelefono;
    private java.sql.Date dataNascita;
    private java.sql.Date dataConsegnaCertificato;
    private final String sesso;
    //La query da eseguire
    private final String QUERY = "INSERT INTO iscritti (CF, Nome, Cognome, Città, Via, Civico, Numero Telefono, Data nascita, Data consegna certificato medico, Sesso)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public AddGymMemberQuery(
            final Connection connection,
            final String cf,
            final String nome,
            final String cognome,
            final String citta,
            final String via,
            final String civico,
            final String sesso
    ) {
        this.connection = connection;
        this.CF = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.via = via;
        this.civico = civico;
        this.sesso = sesso;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(this.QUERY)
        ) {
            // Assegniamo i parametri alla query
            preparedStatement.setString(1, CF); // Codice fiscale
            preparedStatement.setString(2, nome); // Nome
            preparedStatement.setString(3, cognome); // Cognome
            preparedStatement.setString(4, citta); // Città
            preparedStatement.setString(5, via); // Via
            preparedStatement.setString(6, civico); // Civico
            preparedStatement.setInt(7, numeroTelefono); // Numero di telefono
            preparedStatement.setDate(8, dataNascita); // Data di nascita
            preparedStatement.setDate(9, dataConsegnaCertificato); // Data consegna certificato medico
            preparedStatement.setString(10, sesso); // Sesso

            // Esegue la query
            preparedStatement.executeUpdate();

            // Non ci aspettiamo un ResultSet per un'operazione INSERT, quindi restituiamo null
            return Optional.empty();

        } catch (final SQLException e) {
            return Optional.empty();
        }
    }
}
