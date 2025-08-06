package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ViewWorkoutPlanQuery implements QueryExecutor {

    // OP-20    Visualizza una scheda di un iscritto

    private final String QUERY =
    "SELECT U.CF, U.Codice_scheda, M.Posizione, M.Nome_esercizio, C.Peso, C.Numero_ripetizioni, C.recupero, C.quantità " +
    "FROM utilizzi_schede U, modalità_esercizi M, configurazioni C " +
    "WHERE U.Codice_scheda = M.Codice_scheda " +
    "AND M.Codice_scheda = C.Codice_scheda " +
    "AND M.Nome_esercizio = C.Nome_esercizio " +
    "AND U.CF = ? " +
    "ORDER BY M.Posizione" ;

    private final String CF;

    public ViewWorkoutPlanQuery(final String CF) {
        this.CF = CF
    }

    @Override
    public Optional<ResultSet> execute() {
        
    }

}