package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WeeklyAttendanceAVGCalculatorByMonthQuery implements Query {

    //OP-19	Media delle presenze settimanali in sala pesi nell'arco di un mese
    
    private final String QUERY = "SELECT COUNT(*) / 4 " +
            "FROM presenza_sala_pesi " +
            "WHERE MONTH(Data) = ? ";
    private final java.sql.Date month;
    private final Connection connection;

    public WeeklyAttendanceAVGCalculatorByMonthQuery(final java.sql.Date month, final Connection connection) {
        this.month = month;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY);
                ) {
            preparedStatement.setDate(1, this.month);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Optional.of(resultSet);
            }
            resultSet.close();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
