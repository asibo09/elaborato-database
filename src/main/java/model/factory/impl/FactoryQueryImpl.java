package model.factory.impl;

import model.factory.api.FactoryQuery;
import model.queryexecutor.api.QueryExecutor;
import model.queryexecutor.impl.AddGymMemberQuery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

public class FactoryQueryImpl implements FactoryQuery {

    private final java.sql.Connection connection;

    public FactoryQueryImpl(final java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public QueryExecutor createAddGymMemberQuery(final String cf,
                                                 final String nome,
                                                 final String cognome,
                                                 final String citta,
                                                 final String via,
                                                 final int civico,
                                                 final char sesso,
                                                 final Date dataNascita,
                                                 final Date dataConsegnaCertificatoMedico,
                                                 final String numeroTelefono
    ) {
        return new AddGymMemberQuery(this.connection, cf, nome, cognome, citta, via, civico, sesso, dataNascita, dataConsegnaCertificatoMedico, numeroTelefono);
    }

    @Override
    public QueryExecutor createAddReportQuery(String query) {
        return null;
    }

    @Override
    public QueryExecutor createDailyWeightRoomAttendanceQuery(String query) {
        return null;
    }
}
