package model.queryexecutor.impl;

import model.queryexecutor.api.QueryExecutor;
import controller.Controller;

import java.sql.*;

public class AddGymMemberQuery implements QueryExecutor {

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
            final DriverManager driverManager,
            final String cf,
            final String nome,
            final String cognome,
            final String citta,
            final String via,
            final String civico,
            final String sesso
    ) {
        CF = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.via = via;
        this.civico = civico;
        this.sesso = sesso;
    }

    @Override
    public ResultSet execute() {
        try (
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY)
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
            return null;

        } catch (final SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
