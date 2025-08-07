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
    public Query createAddGymMemberQuery(String cf, String nome, String cognome, String citta, String via, int civico,
            char sesso, Date dataNascita, Date dataConsegnaCertificatoMedico, String numeroTelefono) {
        return new AddGymMemberQuery(connection, cf, nome, cognome, citta, via, civico, sesso, dataNascita, dataConsegnaCertificatoMedico, numeroTelefono);
    }

    @Override
    public Query createAddReportQuery(String numeroSegnalazione, Date date, Time time, String state, String description,
            int machineId, String machineName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAddReportQuery'");
    }

    @Override
    public Query ChangeLessonRoomQuery(Date data, Time ora, String vecchiaSala, String nuovaSala) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ChangeLessonRoomQuery'");
    }

    @Override
    public Query DailyWeightRoomAttendanceQuery(Date date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DailyWeightRoomAttendanceQuery'");
    }

    @Override
    public Query createExercisesByMuscleGroupQuery(String muscleGroupName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createExercisesByMuscleGroupQuery'");
    }

    @Override
    public Query createLastYearReportsQuery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLastYearReportsQuery'");
    }

    @Override
    public Query LessonsByCourseEditionQuery(String courseName, Date courseDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LessonsByCourseEditionQuery'");
    }

    @Override
    public Query LessonsOfMemberBookedInMonthQuery(String cf, int mese, int anno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LessonsOfMemberBookedInMonthQuery'");
    }

    @Override
    public Query MemberWeeklyAttendance(String nome, String cognome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MemberWeeklyAttendance'");
    }

    @Override
    public Query MostLessonByTrainerInMonthQuery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MostLessonByTrainerInMonthQuery'");
    }

    @Override
    public Query MostUsedMachinesWeekQuery(Date startDate, Date endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'MostUsedMachinesWeekQuery'");
    }

    @Override
    public Query RegisterSubscriptionQuery(String dataStipulazione, String tipo, Time durata, String cf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RegisterSubscriptionQuery'");
    }

    @Override
    public Query SearchMemberAndCheckValiditySubscription(String nome, String cognome, Date dataNascita) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchMemberAndCheckValiditySubscription'");
    }

    @Override
    public Query SubscriptionsExpiringQuery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SubscriptionsExpiringQuery'");
    }

    @Override
    public Query SubscriptionVerificationAndAttendanceRegistration(String nome, String cognome, Date data, Time ora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SubscriptionVerificationAndAttendanceRegistration'");
    }

    @Override
    public Query TrainerCoursesOfTheCurrentYearQuery(String name, String surname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TrainerCoursesOfTheCurrentYearQuery'");
    }

    @Override
    public Query UnresolvedReportsOlderThanThreeDays() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UnresolvedReportsOlderThanThreeDays'");
    }

    @Override
    public Query ViewWorkoutPlanQuery(String cf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ViewWorkoutPlanQuery'");
    }

    @Override
    public Query WeeklyAttendanceAVGCalculatorByMonthQuery(Date month) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'WeeklyAttendanceAVGCalculatorByMonthQuery'");
    }

}