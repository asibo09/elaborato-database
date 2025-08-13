package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExercisesByMuscleGroupQuery implements Query {

    //OP-16	Mostrare gli esercizi che allenano un determinato gruppo muscolare

    private final String QUERY = "SELECT e.Nome_Esercizio " +
            "FROM Esercizi e, Allenamenti a, Gruppi_Muscolari g " +
            "WHERE e.Nome_Esercizio = a.Nome_Esercizio " +
            "AND a.Nome_Muscolo = g.Nome_Muscolo " +
            "AND g.Nome_Muscolo = ? ";
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
