package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.sql.ResultSet;

public class ChangeLessonRoomQuery implements Query {
    
    //OP-21	Spostare una lezione in un'altra sala

    private final String COPY_LEZIONE = 
    "INSERT INTO LEZIONI (Data, Ora, Codice_sala, Data_inizio, Nome_corso) " +
    "SELECT Data, Ora, Codice_sala " +
    "FROM LEZIONI " +
    "WHERE Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ;

    private final String UPDATE_PRENOTAZIONI =
    "UPDATE PRENOTAZIONI SET Codice_Sala = ? " +
    "WHERE  Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ; 

    private final String UPDATE_ATTREZZI =
    "UPDATE ATTREZZI_LEZIONI SET Codice_Sala = ? " +
    "WHERE  Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ;

    private final String DELETE_LEZIONE =
    "DELETE FROM LEZIONI " +
    "WHERE Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? "; 

    private final Date data;
    private final Time ora;
    private final String vecchiaSala;
    private final String nuovaSala;
    private final Connection conn;

    public ChangeLessonRoomQuery(
            final Connection connection,
            final Date data,
            final Time ora,
            final String vecchiaSala,
            final String nuovaSala
    ) {
        this.conn = connection;
        this.data = data;
        this.ora = ora;
        this.vecchiaSala = vecchiaSala;
        this.nuovaSala = nuovaSala;
    }

    @Override
    public Optional<ResultSet> execute() {
        try {
            conn.setAutoCommit(false);

            try (
                PreparedStatement copyLezione = conn.prepareStatement(COPY_LEZIONE);
                PreparedStatement updatePrenotazioni = conn.prepareStatement(UPDATE_PRENOTAZIONI);
                PreparedStatement updateAttrezzi = conn.prepareStatement(UPDATE_ATTREZZI);
                PreparedStatement deleteLezione = conn.prepareStatement(DELETE_LEZIONE)
            ) {
                //copia nuova lezione
                copyLezione.setString(1, nuovaSala);
                copyLezione.setDate(2, data);
                copyLezione.setTime(3, ora);
                copyLezione.setString(4, vecchiaSala);
                copyLezione.executeUpdate();

                //aggiorna prenotazioni
                updatePrenotazioni.setString(1, nuovaSala);
                updatePrenotazioni.setDate(2, data);
                updatePrenotazioni.setTime(3, ora);
                updatePrenotazioni.setString(4, vecchiaSala);
                updatePrenotazioni.executeUpdate();

                //aggiorna attrezzi
                updateAttrezzi.setString(1, nuovaSala);
                updateAttrezzi.setDate(2, data);
                updateAttrezzi.setTime(3, ora);
                updateAttrezzi.setString(4, vecchiaSala);
                updateAttrezzi.executeUpdate();

                //elimina vecchia lezione
                deleteLezione.setDate(1, data);
                deleteLezione.setTime(2, ora);
                deleteLezione.setString(3, vecchiaSala);
                deleteLezione.executeUpdate();

                conn.commit();
            } catch (final SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

}