package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LessonsByCourseEditionQuery implements Query {

    //OP-18	Visualizzare elenco delle lezioni di un’edizione corso 

    private final String QUERY = "SELECT L.* " +
            "FROM lezioni L, edizioni_corsi E " +
            "WHERE L.Data_inizio = E.Data_inizio " +
            "AND L.Nome_corso = E.Nome_corso" +
            "AND E.Nome_corso = ? " +
            "AND E.Data_inizio = ? ";
    private final String courseName;
    private final java.sql.Date courseDate;
    private final Connection connection;

    public LessonsByCourseEditionQuery(final String courseName, final java.sql.Date courseDate, final Connection connection) {
        this.courseName = courseName;
        this.courseDate = courseDate;
        this.connection = connection;
    }

    @Override
    public Optional<ResultSet> execute() {
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY);
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
