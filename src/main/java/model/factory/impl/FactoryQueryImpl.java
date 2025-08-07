package model.factory.impl;

import model.factory.api.FactoryQuery;
import model.queryexecutor.api.Query;
import model.queryexecutor.impl.AddGymMemberQuery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

public class FactoryQueryImpl implements FactoryQuery {

    private final java.sql.Connection connection;

    public FactoryQueryImpl(final java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Query createAddGymMemberQuery(Connection connection, String cf, String nome, String cognome, String citta, String via, int civico, char sesso, Date dataNascita, Date dataConsegnaCertificatoMedico, String numeroTelefono) {
        return null;
    }

    @Override
    public Query createAddReportQuery(Connection connection, String numeroSegnalazione, Date date, Time time, String state, String description, int machineId, String machineName) {
        return null;
    }

    @Override
    public Query ChangeLessonRoomQuery(Connection connection, Date data, Time ora, String vecchiaSala, String nuovaSala) {
        return null;
    }

    @Override
    public Query DailyWeightRoomAttendanceQuery(Connection connection, Date date) {
        return null;
    }

    @Override
    public Query createExercisesByMuscleGroupQuery(Connection connection, String muscleGroupName) {
        return null;
    }

    @Override
    public Query createLastYearReportsQuery(Connection connection) {
        return null;
    }

    @Override
    public Query LessonsByCourseEditionQuery(String courseName, Date courseDate, Connection connection) {
        return null;
    }

    @Override
    public Query LessonsOfMemberBookedInMonthQuery(String cf, int mese, int anno, Connection conn) {
        return null;
    }

    @Override
    public Query MemberWeeklyAttendance(String nome, String cognome, Connection connection) {
        return null;
    }

    @Override
    public Query MostLessonByTrainerInMonthQuery(Connection connection) {
        return null;
    }

    @Override
    public Query MostUsedMachinesWeekQuery(Date startDate, Date endDate, Connection connection) {
        return null;
    }

    @Override
    public Query RegisterSubscriptionQuery(String dataStipulazione, String tipo, Time durata, String cf, Connection connection) {
        return null;
    }

    @Override
    public Query SearchMemberAndCheckValiditySubscription(String nome, String cognome, Date dataNascita, Connection connection) {
        return null;
    }

    @Override
    public Query SubscriptionsExpiringQuery(Connection connection) {
        return null;
    }

    @Override
    public Query SubscriptionVerificationAndAttendanceRegistration(String nome, String cognome, Date data, Time ora, Connection connection) {
        return null;
    }

    @Override
    public Query TrainerCoursesOfTheCurrentYearQuery(String name, String surname, Connection connection) {
        return null;
    }

    @Override
    public Query UnresolvedReportsOlderThanThreeDays(Connection connection) {
        return null;
    }

    @Override
    public Query ViewWorkoutPlanQuery(String cf, Connection connection) {
        return null;
    }

    @Override
    public Query WeeklyAttendanceAVGCalculatorByMonthQuery(Date month, Connection connection) {
        return null;
    }
}
