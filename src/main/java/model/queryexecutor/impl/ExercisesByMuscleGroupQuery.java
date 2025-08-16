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

public class ExercisesByMuscleGroupQuery implements Query {

    //OP-16	Mostrare gli esercizi che allenano un determinato gruppo muscolare

    private final String QUERY = "SELECT e.Nome_esercizio " +
            "FROM esercizio e, allenamento a, gruppo_muscolare g " +
            "WHERE e.Nome_esercizio = a.Nome_esercizio " +
            "AND a.Nome_muscolo = g.Nome_muscolo " +
            "AND g.Nome_muscolo = ? ";
    private final String muscleGroupName;
    private Connection connection;

    public ExercisesByMuscleGroupQuery(final Connection connection, final String muscleGroupName) {
        this.connection = connection;
        this.muscleGroupName = muscleGroupName;
    }

    @Override
    public Optional<ResultSet> execute() {
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY)
                ) {
            preparedStatement.setString(1, this.muscleGroupName);
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
