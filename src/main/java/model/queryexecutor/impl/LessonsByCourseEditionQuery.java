package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LessonsByCourseEditionQuery implements QueryExecutor {

    private final String QUERY = "SELECT L.*" +
            "FROM lezioni L, edizioni_corsi E" +
            "WHERE L.Data_inizio = E.Data_inizio" +
            "AND L.Nome_corso = E.Nome_corso" +
            "AND E.Nome_corso = ?" +
            "AND E.Data_inizio = ?";
    private final String courseName;
    private final java.sql.Date courseDate;

    public LessonsByCourseEditionQuery(final String courseName, final java.sql.Date courseDate) {
        this.courseName = courseName;
        this.courseDate = courseDate;
    }

    @Override
    public Optional<ResultSet> execute() {
        try(
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                ) {
            preparedStatement.setString(1,this.courseName);
            preparedStatement.setDate(2,this.courseDate);
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
