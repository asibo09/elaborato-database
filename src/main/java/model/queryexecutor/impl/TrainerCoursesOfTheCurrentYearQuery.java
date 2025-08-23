package model.queryexecutor.impl;

import model.queryexecutor.api.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class TrainerCoursesOfTheCurrentYearQuery implements Query {

    //OP-7	Elenco dei corsi tenuti da un trainer in un determinato anno

    private final String TRAINER_CF_QUERY = "Select CF " +
            "From trainer " +
            "Where nome = ? " +
            "And Cognome = ? " +
            "Limit 1 ";
    private final String QUERY = "Select * " +
            "From EDIZIONE_CORSO " +
            "Where CF = ? " +
            "And DATEDIFF(CURDATE(), Data_inizio) < 365 ";
    private final String name;
    private final String surname;
    private final Connection connection;

    public TrainerCoursesOfTheCurrentYearQuery(final String name, final String surname, final Connection connection) {
        this.name = name;
        this.surname = surname;
        this.connection = connection;
    }

    private String getTrainerCf() {
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(TRAINER_CF_QUERY);
                ) {
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.surname);
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
                    PreparedStatement preparedStatement = this.connection.prepareStatement(QUERY);
                    ) {
                preparedStatement.setString(1, trainerCf);
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
        return Optional.empty();
    }
}
