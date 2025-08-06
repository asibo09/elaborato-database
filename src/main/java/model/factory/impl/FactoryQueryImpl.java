package model.factory.impl;

import model.factory.api.FactoryQuery;
import model.queryexecutor.api.Query;
import model.queryexecutor.impl.AddGymMemberQuery;

import java.sql.Date;

public class FactoryQueryImpl implements FactoryQuery {

    private final java.sql.Connection connection;

    public FactoryQueryImpl(final java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Query createAddGymMemberQuery(final String cf,
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
    public Query createAddReportQuery(String query) {
        return null;
    }

    @Override
    public Query createDailyWeightRoomAttendanceQuery(String query) {
        return null;
    }
}
