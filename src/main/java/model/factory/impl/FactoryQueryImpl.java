package model.factory.impl;

import model.factory.api.FactoryQuery;
import model.queryexecutor.api.Query;
import model.queryexecutor.impl.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

public class FactoryQueryImpl implements FactoryQuery {

    private final Connection connection;

    public FactoryQueryImpl(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Query createAddGymMemberQuery(
            final String cf,
            final String nome,
            final String cognome,
            final String citta,
            final String via,
            final int civico,
            final char sesso,
            final Date dataNascita,
            final Date dataConsegnaCertificatoMedico,
            final String numeroTelefono) {
        return new AddGymMemberQuery(connection, cf, nome, cognome, citta, via, civico, sesso, dataNascita, dataConsegnaCertificatoMedico, numeroTelefono);
    }

    @Override
    public Query createAddReportQuery(
            final String numeroSegnalazione,
            final Date date,
            final Time time,
            final String state,
            final String description,
            final int machineId,
            final String machineName) {
        return new AddReportQuery(connection, numeroSegnalazione, date, time, state, description, machineId, machineName);
    }

    @Override
    public Query ChangeLessonRoomQuery(
            final Date data,
            final Time ora,
            final String vecchiaSala,
            final String nuovaSala) {
        return new ChangeLessonRoomQuery(connection, data, ora, vecchiaSala, nuovaSala);
    }

    @Override
    public Query DailyWeightRoomAttendanceQuery(final Date date) {
        return new DailyWeightRoomAttendanceQuery(connection, date);
    }

    @Override
    public Query createExercisesByMuscleGroupQuery(final String muscleGroupName) {
        return new ExercisesByMuscleGroupQuery(connection, muscleGroupName);
    }

    @Override
    public Query createLastYearReportsQuery() {
        return new LastYearReportsQuery(connection);
    }

    @Override
    public Query LessonsByCourseEditionQuery(
            final String courseName,
            final Date courseDate) {
        return new LessonsByCourseEditionQuery(courseName, courseDate, connection);
    }

    @Override
    public Query LessonsOfMemberBookedInMonthQuery(
            final String cf,
            final int mese,
            final int anno) {
        return new LessonsOfMemberBookedInMonthQuery(cf, mese, anno, connection);
    }

    @Override
    public Query MemberWeeklyAttendance(
            final String nome,
            final String cognome) {
        return new MemberWeeklyAttendance(nome, cognome, connection);
    }

    @Override
    public Query MostLessonByTrainerInMonthQuery() {
        return new MostLessonByTrainerInMonthQuery(connection);
    }

    @Override
    public Query MostUsedMachinesWeekQuery(
            final Date startDate,
            final Date endDate) {
        return new MostUsedMachinesWeekQuery(startDate, endDate, connection);
    }

    @Override
    public Query RegisterSubscriptionQuery(
            final String dataStipulazione,
            final String tipo,
            final Time durata,
            final String cf) {
        return new RegisterSubscriptionQuery(dataStipulazione, tipo, durata, cf, connection);
    }

    @Override
    public Query SearchMemberAndCheckValiditySubscription(
            final String nome,
            final String cognome,
            final Date dataNascita) {
        return new SearchMemberAndCheckValiditySubscription(nome, cognome, dataNascita, connection);
    }

    @Override
    public Query SubscriptionsExpiringQuery() {
        return new SubscriptionsExpiringQuery(connection);
    }

    @Override
    public Query SubscriptionVerificationAndAttendanceRegistration(
            final String nome,
            final String cognome,
            final Date data,
            final Time ora) {
        return new SubscriptionVerificationAndAttendanceRegistration(nome, cognome, data, ora, connection);
    }

    @Override
    public Query TrainerCoursesOfTheCurrentYearQuery(
            final String name,
            final String surname) {
        return new TrainerCoursesOfTheCurrentYearQuery(name, surname, connection);
    }

    @Override
    public Query UnresolvedReportsOlderThanThreeDays() {
        return new UnresolvedReportsOlderThanThreeDays(connection);
    }

    @Override
    public Query ViewWorkoutPlanQuery(final String cf) {
        return new ViewWorkoutPlanQuery(cf, connection);
    }

    @Override
    public Query WeeklyAttendanceAVGCalculatorByMonthQuery(final Date month) {
        return new WeeklyAttendanceAVGCalculatorByMonthQuery(month, connection);
    }
}