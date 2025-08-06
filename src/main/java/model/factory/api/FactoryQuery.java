package model.factory.api;

import java.sql.Date;

import model.queryexecutor.api.Query;

public interface FactoryQuery {


    public Query createAddGymMemberQuery(final String cf,
                                         final String nome,
                                         final String cognome,
                                         final String citta,
                                         final String via,
                                         final int civico,
                                         final char sesso,
                                         final Date dataNascita,
                                         final Date dataConsegnaCertificatoMedico,
                                         final String numeroTelefono);

    public Query createAddReportQuery(final String query);

    public Query createDailyWeightRoomAttendanceQuery(final String query);
}
