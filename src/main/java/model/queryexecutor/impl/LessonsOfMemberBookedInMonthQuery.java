package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.*;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class LessonsOfMemberBookedInMonthQuery implements Query {

    //OP-5	Elenca tutte le lezioni a cui un iscritto si è prenotato o presentato in un determinato mese di un determinato anno

    private static final String QUERY =
    "SELECT * " +
    "FROM ( " +
    "   SELECT ab.CF, pc.Data, pc.Ora, pc.Codice_Sala " +
    "   FROM ABBONAMENTO_UTENTE ab " +
    "   JOIN PARTECIPAZIONE_CORSO pc ON " +
    "       ab.Data_stipulazione = pc.Data_stipulazione AND " +
    "       ab.CF = pc.CF AND " +
    "       ab.Tipo = pc.Tipo AND " +
    "       ab.Durata = pc.Durata " +
    "   WHERE ab.CF = ? " +
    
    "   UNION " +

    "   SELECT ab.CF, pre.Data, pre.Ora, pre.Codice_Sala " +
    "   FROM ABBONAMENTO_UTENTE ab " +
    "   JOIN PRENOTAZIONE pre ON " +
    "       ab.Data_stipulazione = pre.Data_stipulazione AND " +
    "       ab.CF = pre.CF AND " +
    "       ab.Tipo = pre.Tipo AND " +
    "       ab.Durata = pre.Durata " +
    "   WHERE ab.CF = ? " +
    ") AS LezioniUtente " +
    "WHERE MONTH(Data) = ? AND YEAR(Data) = ? " +
    "ORDER BY Data, Ora " ;

    private final String cf;
    private final int mese;
    private final int anno;
    private final Connection connection;

        public LessonsOfMemberBookedInMonthQuery(final String cf, final  int mese, final int anno, final Connection conn) {
        this.cf = cf;
        this.mese = mese;
        this.anno = anno;
        this.connection = conn;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setString(1, cf); // partecipazioni
            statement.setString(2, cf); // prenotazioni
            statement.setInt(3, mese);
            statement.setInt(4, anno);

            ResultSet rs = statement.executeQuery();
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);

            if (!crs.isBeforeFirst()) { 
                return Optional.empty();
            }

            return Optional.of(crs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
