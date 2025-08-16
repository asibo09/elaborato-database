package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SubscriptionVerificationAndAttendanceRegistration implements Query {

    //OP-11	Registrazione presenza in sala pesi e verifica del tipo e della validità dell’abbonamento

    private final String VERIFY_QUERY = "SELECT * " +
            "FROM abbonamento_utente " +
            "WHERE CF = ( " +
            "    SELECT CF " +
            "    FROM ISCRITTO " +
            "    WHERE Nome = ? " +
            "    AND Cognome = ? " +
            "    LIMIT 1 " +
            ") " +
            "AND Tipo = 'Sala Pesi' " +
            "AND DATEDIFF(DATE_ADD(Data_stipulazione, INTERVAL Durata DAY), CURDATE()) > 0; ";
    private final String REGISTER_ATTENDANCE_QUERY = "INSERT INTO presenza_sala_pesi (Data_stipulazione, tipo, durata, cf, Data, ora)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private final String nome;
    private final String cognome;
    private java.sql.Date dataStipulazione;
    private String tipo;
    private int durata;
    private String cf;
    private final java.sql.Date data;
    private final java.sql.Time ora;
    private final Connection connection;

    public SubscriptionVerificationAndAttendanceRegistration(
            final String nome,
            final String cognome,
            final java.sql.Date data,
            final java.sql.Time ora,
            final Connection connection
    ) {
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.ora = ora;
        this.connection = connection;
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT * " +
                    "FROM abbonamento_utente " +   
                    "WHERE CF = ( " +
                    "    SELECT CF " +
                    "    FROM iscritto " +         
                    "    WHERE Nome = ? " +
                    "    AND Cognome = ? " +
                    "    LIMIT 1 " +
                    ") " +
                    "AND tipo = 'Sala Pesi'"
                );
                ){
            preparedStatement.setString(1, this.nome);
            preparedStatement.setString(2, this.cognome);
            final ResultSet subscription = preparedStatement.executeQuery();
            if(subscription.next()){
                this.dataStipulazione = subscription.getDate("Data_stipulazione");
                this.tipo = subscription.getString("Tipo");
                this.durata = subscription.getInt("Durata");
                this.cf = subscription.getString("CF");
            } else {
                System.out.println("Abbonamento non trovato");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica se l'utente ha un abbonamento valido.
     *
     * @return {@code true} se l'utente ha un abbonamento valido,
     *         {@code false} se l'abbonamento è scaduto.
     */
    private boolean verifySubscription() {
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(this.VERIFY_QUERY);
                ){
            preparedStatement.setString(1, this.nome);
            preparedStatement.setString(2, this.cognome);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            resultSet.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Optional<ResultSet> execute() {
        if (verifySubscription()) {
            try(
                    PreparedStatement preparedStatement = this.connection.prepareStatement(this.REGISTER_ATTENDANCE_QUERY);
                    ){
                preparedStatement.setDate(1, this.dataStipulazione);
                preparedStatement.setString(2, this.tipo);
                preparedStatement.setInt(3, this.durata);
                preparedStatement.setString(4, this.cf);
                preparedStatement.setDate(5, this.data);
                preparedStatement.setTime(6, this.ora);
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }
}
