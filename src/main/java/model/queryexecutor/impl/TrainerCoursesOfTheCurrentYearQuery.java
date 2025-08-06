package model.queryexecutor.impl;

import controller.Controller;
import model.queryexecutor.api.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TrainerCoursesOfTheCurrentYearQuery implements QueryExecutor {

    private final String TRAINER_CF_QUERY = "Select CF" +
            "From Trainer" +
            "Where nome = ?" +
            "And Cognome = ?" +
            "Limit 1";
    private final String QUERY = "Select *" +
            "From EDIZIONE_CORSI" +
            "Where CF = ?" +
            "And DATEDIFF(CURDATE(), Data_inizio) < 365";
    private final String name;
    private final String surname;

    public TrainerCoursesOfTheCurrentYearQuery(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }

    private String getTrainerCf() {
        try(
                Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(TRAINER_CF_QUERY);
                ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            final ResultSet trainerCfRes = preparedStatement.executeQuery();
            final String trainerCf;
            if(trainerCfRes.next()) {
                trainerCf = trainerCfRes.getString(1);
                trainerCfRes.close();
                return trainerCf;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    @Override
    public Optional<ResultSet> execute() {
        final String trainerCf = getTrainerCf();
        if(!trainerCf.isBlank()) {
            try(
                    Connection connection = java.sql.DriverManager.getConnection(Controller.DATABASE_URL);
                    PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
                    ) {
                preparedStatement.setString(1, trainerCf);
                final ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    return Optional.of(resultSet);
                }
                resultSet.close();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }
}
