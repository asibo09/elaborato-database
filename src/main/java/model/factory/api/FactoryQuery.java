package model.factory.api;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

import model.queryexecutor.api.Query;

public interface FactoryQuery {


    public Query createAddGymMemberQuery(
            final Connection connection,
            final String cf,
            final String nome,
            final String cognome,
            final String citta,
            final String via,
            final int civico,
            final char sesso,
            final Date dataNascita,
            final Date dataConsegnaCertificatoMedico,
            final String numeroTelefono);

    public Query createAddReportQuery(
            final Connection connection,
            final String numeroSegnalazione,
            final java.sql.Date date,
            final java.sql.Time time,
            final String state,
            final String description,
            final int machineId,
            final String machineName);

    public Query ChangeLessonRoomQuery(
            final Connection connection,
            final Date data,
            final Time ora,
            final String vecchiaSala,
            final String nuovaSala);

    public Query DailyWeightRoomAttendanceQuery(final Connection connection, final java.sql.Date date);

    public Query createExercisesByMuscleGroupQuery(final Connection connection, final String muscleGroupName);

    public Query createLastYearReportsQuery(final Connection connection);

    public Query LessonsByCourseEditionQuery(final String courseName, final java.sql.Date courseDate, final Connection connection);

    public Query LessonsOfMemberBookedInMonthQuery(final String cf, final  int mese, final int anno, final Connection conn);

    public Query MemberWeeklyAttendance(final String nome, final String cognome, final Connection connection);

    public Query MostLessonByTrainerInMonthQuery(final Connection connection);

    public Query MostUsedMachinesWeekQuery(final Date startDate, final Date endDate, final Connection connection);

    public Query RegisterSubscriptionQuery(final String dataStipulazione,
                                           final String tipo,
                                           final java.sql.Time durata,
                                           final String cf,
                                           final Connection connection);

    public Query SearchMemberAndCheckValiditySubscription(final String nome, final String cognome, final Date dataNascita, final Connection connection);

    public Query SubscriptionsExpiringQuery(final Connection connection);

    public Query SubscriptionVerificationAndAttendanceRegistration(final String nome,
                                                                   final String cognome,
                                                                   final java.sql.Date data,
                                                                   final java.sql.Time ora,
                                                                   final Connection connection);

    public Query TrainerCoursesOfTheCurrentYearQuery(final String name, final String surname, final Connection connection);

    public Query UnresolvedReportsOlderThanThreeDays(final Connection connection);

    public Query ViewWorkoutPlanQuery(final String cf, final Connection connection);

    public Query WeeklyAttendanceAVGCalculatorByMonthQuery(final java.sql.Date month, final Connection connection)
}
