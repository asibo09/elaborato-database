package model.queryexecutor.impl;

import model.queryexecutor.api.QueryExecutor;

import java.sql.ResultSet;
import java.util.Optional;

public class UserMonthlyClassAttendance implements QueryExecutor {

    private final String QUERY = "SELECT * FROM Presenze_Sala_Pesi WHERE Data = ? AND CF = ?";

    private final String cf;
    private final java.sql.Date data;

    public UserMonthlyClassAttendance(final String cf, final java.sql.Date data) {
        this.cf = cf;
        this.data = data;
    }

    @Override
    public Optional<ResultSet> execute() {
        return Optional.empty();
    }
}
