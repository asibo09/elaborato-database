package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SubscriptionVerificationAndAttendanceRegistration implements QueryExecutor {

    private static final String VERIFY_QUERY = "SELECT *" +
            "FROM abbonamenti_utente" +
            "WHERE CF = (" +
            "    SELECT CF" +
            "    FROM ISCRITTI" +
            "    WHERE Nome = ?" +
            "    AND Cognome = ?" +
            "    LIMIT 1" +
            ")" +
            "AND Tipo_abbonamento = \"Sala Pesi\"" +
            "AND DATEDIFF(DATE_ADD(Data_stipulazione, INTERVAL Durata DAY), CURDATE()) > 0;";
    private static final String REGISTER_ATTENDANCE_QUERY = "INSERT INTO Presenze_Sala_Pesi (Data_stipulazione, tipo, durata, cf, Data, ora)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private final String nome;
    private final String cognome;
    private final java.sql.Date dataStipulazione;
    private final String tipo;
    private final java.sql.Time durata;
    private final String cf;
    private final java.sql.Date data;
    private final java.sql.Time ora;

    public SubscriptionVerificationAndAttendanceRegistration(
            final String nome,
            final String cognome,
            final java.sql.Date data,
            final java.sql.Time ora
    ) {
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.ora = ora;
        try(
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM abbonamenti_utente" +
                                "WHERE CF = (" +
                                "    SELECT CF" +
                                "    FROM ISCRITTI" +
                                "    WHERE Nome = ?" +
                                "    AND Cognome = ?" +
                                "    LIMIT 1" +
                                ")" +
                                "AND Tipo_abbonamento = \"Sala Pesi\"")
                ){
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            final ResultSet subscription = preparedStatement.executeQuery();
            if(subscription.next()){
                this.dataStipulazione = subscription.getDate("Data_stipulazione");
                this.tipo = subscription.getString("Tipo_abbonamento");
                this.durata = subscription.getTime("Durata");
                this.cf = subscription.getString("CF");
            } else {
                throw new RuntimeException("Abbonamento non trovato");
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
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(this.VERIFY_QUERY);
                ){
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
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
                    Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                    PreparedStatement preparedStatement = connection.prepareStatement(this.REGISTER_ATTENDANCE_QUERY);
                    ){
                preparedStatement.setDate(1, dataStipulazione);
                preparedStatement.setString(2, tipo);
                preparedStatement.setTime(3, durata);
                preparedStatement.setString(4, cf);
                preparedStatement.setDate(5, data);
                preparedStatement.setTime(6, ora);
                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }
}
