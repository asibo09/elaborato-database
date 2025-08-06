package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WeeklyAttendanceAVGCalculatorByMonthQuery implements QueryExecutor {

    private final String QUERY = "SELECT COUNT(*) / 4" +
            "FROM Presenze_Sala_Pesi" +
            "WHERE MONTH(Data) = ?";
    private final java.sql.Date month;

    public WeeklyAttendanceAVGCalculatorByMonthQuery(final java.sql.Date month) {
        this.month = month;
    }

    @Override
    public Optional<ResultSet> execute() {
        try (
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
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
