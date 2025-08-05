package model.factory.api;

import java.sql.Date;

import model.queryexecutor.api.QueryExecutor;

public interface FactoryQuery {


    public QueryExecutor createAddGymMemberQuery(final String cf,
                                                 final String nome,
                                                 final String cognome,
                                                 final String citta,
                                                 final String via,
                                                 final int civico,
                                                 final char sesso,
                                                 final Date dataNascita,
                                                 final Date dataConsegnaCertificatoMedico,
                                                 final String numeroTelefono);

    public QueryExecutor createAddReportQuery(final String query);

    public QueryExecutor createDailyWeightRoomAttendanceQuery(final String query);
}
