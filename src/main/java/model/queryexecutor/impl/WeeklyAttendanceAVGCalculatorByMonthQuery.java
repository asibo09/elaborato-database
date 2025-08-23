package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class WeeklyAttendanceAVGCalculatorByMonthQuery implements Query {

    //OP-19	Media delle presenze settimanali in sala pesi nell'arco di un mese
    
    private final String QUERY = "SELECT COUNT(*) / 4 AS MediaSettimanale " +
            "FROM presenza_sala_pesi " +
            "WHERE MONTH(Data) = ? ";
    private final int month;
    private final Connection connection;

    public WeeklyAttendanceAVGCalculatorByMonthQuery(final int month, final Connection connection) {
        this.month = month;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY);
                ) {
            preparedStatement.setInt(1, this.month);
            final ResultSet resultSet = preparedStatement.executeQuery();
            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            if (!crs.isBeforeFirst()) {
                return Optional.empty();
            }
            return Optional.of(crs);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}
