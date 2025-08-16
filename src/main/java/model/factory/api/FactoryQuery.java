package model.factory.api;

import java.sql.Date;
import java.sql.Time;

import model.queryexecutor.api.Query;

public interface FactoryQuery {

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
            final String numeroTelefono);

    public Query createAddReportQuery(
            final String numeroSegnalazione,
            final java.sql.Date date,
            final java.sql.Time time,
            final String cf,
            final String state,
            final String description,
            final int machineId,
            final String machineName);

    public Query ChangeLessonRoomQuery(
            final Date data,
            final Time ora,
            final String vecchiaSala,
            final String nuovaSala);

    public Query DailyWeightRoomAttendanceQuery(final java.sql.Date date);

    public Query createExercisesByMuscleGroupQuery(final String muscleGroupName);

    public Query createLastYearReportsQuery();

    public Query LessonsByCourseEditionQuery(final String courseName, final Date courseDate);

    public Query LessonsOfMemberBookedInMonthQuery(final String cf, final int mese, final int anno);

    public Query MemberWeeklyAttendance(final String nome, final String cognome);

    public Query MostLessonByTrainerInMonthQuery();

    public Query MostUsedMachinesWeekQuery();

    public Query RegisterSubscriptionQuery(final Date dataStipulazione,
                                           final String tipo,
                                           final int durata,
                                           final String cf);

    public Query SearchMemberAndCheckValiditySubscription(final String nome, final String cognome);

    public Query SubscriptionsExpiringQuery();

    public Query SubscriptionVerificationAndAttendanceRegistration(final String nome,
                                                                   final String cognome,
                                                                   final java.sql.Date data,
                                                                   final java.sql.Time ora);

    public Query TrainerCoursesOfTheCurrentYearQuery(final String name, final String surname);

    public Query UnresolvedReportsOlderThanThreeDays();

    public Query ViewWorkoutPlanQuery(final String cf);

    public Query WeeklyAttendanceAVGCalculatorByMonthQuery(final int month);
}
