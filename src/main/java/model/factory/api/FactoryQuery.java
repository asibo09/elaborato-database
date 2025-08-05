package model.factory.api;

import model.queryexecutor.api.QueryExecutor;

public interface FactoryQuery {


    public QueryExecutor createAddGymMemberQuery(final String cf,
                                                 final String nome,
                                                 final String cognome,
                                                 final String citta,
                                                 final String via,
                                                 final String civico,
                                                 final String sesso);

    public QueryExecutor createAddReportQuery(final String query);

    public QueryExecutor createDailyWeightRoomAttendanceQuery(final String query);
}
