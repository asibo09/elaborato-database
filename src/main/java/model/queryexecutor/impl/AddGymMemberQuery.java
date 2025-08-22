package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class AddGymMemberQuery implements Query {

    //OP-1	Aggiunta di un nuovo iscritto

    private final java.sql.Connection connection;
    //Parametri da inserire nella query
    private final String CF;
    private final String nome;
    private final String cognome;
    private final String citta;
    private final String via;
    private final int civico;
    private String numeroTelefono;
    private Date dataNascita;
    private Date dataConsegnaCertificato;
    private final char sesso;
    
    //La query da eseguire
    private final String QUERY = "INSERT INTO ISCRITTO (CF, Nome, Cognome, Citta, Via, Numero_civico, Numero_telefono, Data_nascita, Data_consegna_Certificato_Medico, Sesso)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private final String SELECT_QUERY = "SELECT * FROM iscritto";
    
    public AddGymMemberQuery(
            final Connection connection,
            final String cf,
            final String nome,
            final String cognome,
            final String citta,
            final String via,
            final int civico,
            final char sesso,
            final Date dataNascita,
            final Date dataConsegnaCertificato, 
            final String numeroTelefono
    ) {
        this.connection = connection;
        this.CF = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.via = via;
        this.civico = civico;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
        this.dataConsegnaCertificato = dataConsegnaCertificato;
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(this.QUERY);
                PreparedStatement selectStatement = connection.prepareStatement(this.SELECT_QUERY);
        ) {
            // Assegniamo i parametri alla query
            preparedStatement.setString(1, CF); // Codice fiscale
            preparedStatement.setString(2, nome); // Nome
            preparedStatement.setString(3, cognome); // Cognome
            preparedStatement.setString(4, citta); // Città
            preparedStatement.setString(5, via); // Via
            preparedStatement.setInt(6, civico); // Civico
            preparedStatement.setString(7, numeroTelefono); // Numero di telefono
            preparedStatement.setDate(8, dataNascita); // Data di nascita
            preparedStatement.setDate(9, dataConsegnaCertificato); // Data consegna certificato medico
            preparedStatement.setString(10, String.valueOf(sesso)); // Sesso

            // Esegue la query
            preparedStatement.executeUpdate();
            
            
            ResultSet resultSet = selectStatement.executeQuery();
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
                
            return Optional.of(crs);

        } catch (final SQLException e) {
            return Optional.empty();
        }
    }
}
