package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import java.sql.ResultSet;

public class ChangeLessonRoomQuery implements Query {
    
    //OP-21	Spostare una lezione in un'altra sala

    private final String COPY_LEZIONE = 
    "INSERT INTO LEZIONE (Data, Ora, Codice_sala, Data_inizio, Nome) " +
    "SELECT Data, Ora, ?, Data_inizio, Nome " +
    "FROM LEZIONE " +
    "WHERE Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ;

    private final String UPDATE_PRENOTAZIONI =
    "UPDATE PRENOTAZIONE SET Codice_Sala = ? " +
    "WHERE  Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ; 

    private final String UPDATE_ATTREZZI =
    "UPDATE ATTREZZO_LEZIONE SET Codice_Sala = ? " +
    "WHERE  Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? " ;

    private final String DELETE_LEZIONE =
    "DELETE FROM LEZIONE " +
    "WHERE Data = ? " +
    "AND Ora = ? " +
    "AND Codice_Sala = ? "; 

    private static final String SELECT_LEZIONE =
    "SELECT * FROM LEZIONE WHERE Data = ? AND Ora = ?";

    private final Date data;
    private final Time ora;
    private final int vecchiaSala;
    private final int nuovaSala;
    private final Connection conn;

    public ChangeLessonRoomQuery(
            final Connection connection,
            final Date data,
            final Time ora,
            final int vecchiaSala,
            final int nuovaSala
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
                PreparedStatement deleteLezione = conn.prepareStatement(DELETE_LEZIONE);
                PreparedStatement selectLezione = conn.prepareStatement(SELECT_LEZIONE);
            ) {
                //copia nuova lezione
                copyLezione.setInt(1, nuovaSala);
                copyLezione.setDate(2, data);
                copyLezione.setTime(3, ora);
                copyLezione.setInt(4, vecchiaSala);
                copyLezione.executeUpdate();

                //aggiorna prenotazioni
                updatePrenotazioni.setInt(1, nuovaSala);
                updatePrenotazioni.setDate(2, data);
                updatePrenotazioni.setTime(3, ora);
                updatePrenotazioni.setInt(4, vecchiaSala);
                updatePrenotazioni.executeUpdate();

                //aggiorna attrezzi
                updateAttrezzi.setInt(1, nuovaSala);
                updateAttrezzi.setDate(2, data);
                updateAttrezzi.setTime(3, ora);
                updateAttrezzi.setInt(4, vecchiaSala);
                updateAttrezzi.executeUpdate();

                //elimina vecchia lezione
                deleteLezione.setDate(1, data);
                deleteLezione.setTime(2, ora);
                deleteLezione.setInt(3, vecchiaSala);
                deleteLezione.executeUpdate();

                conn.commit();

                // Prepara e ritorna il ResultSet di Lezione
                selectLezione.setDate(1, data);
                selectLezione.setTime(2, ora);
                ResultSet resultSet = selectLezione.executeQuery();
                CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
                crs.populate(resultSet);

                if (!crs.isBeforeFirst()) {
                    return Optional.empty();
                }
                
                return Optional.of(crs);

            } catch (final SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

}