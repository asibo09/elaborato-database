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

public class LessonsByCourseEditionQuery implements Query {

    //OP-18	Visualizzare elenco delle lezioni di un’edizione corso 

    private final String QUERY = "SELECT L.* " +
            "FROM lezione L, edizione_corso E " +
            "WHERE L.Data_inizio = E.Data_inizio " +
            "AND L.Nome = E.Nome " +
            "AND E.Nome = ? " +
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
