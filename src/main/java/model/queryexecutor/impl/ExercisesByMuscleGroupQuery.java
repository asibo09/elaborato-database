package model.queryexecutor.impl;

import model.queryexecutor.api.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ExercisesByMuscleGroupQuery implements QueryExecutor {

    private static final String QUERY = "SELECT E.Nome_esercizio, A.Nome_muscolo" +
            "FROM allenamento A, esercizio E" +
            "WHERE A.Nome_esercizio = E.Nome_esercizio" +
            "AND A.Nome_muscolo = ?";
    private final String muscleGroupName;

    public ExercisesByMuscleGroupQuery(final String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

    @Override
    public Optional<ResultSet> execute() {

        return Optional.empty();
    }
}
