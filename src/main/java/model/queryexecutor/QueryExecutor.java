package model.queryexecutor;

import controller.Controller.QueryName;
import controller.Controller.QueryParameters;
import model.factory.impl.FactoryQueryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QueryExecutor {

    private Optional<ResultSet> resultSet;
    private final FactoryQueryImpl factoryQuery;

    public QueryExecutor(final Connection connection) {
        this.factoryQuery = new FactoryQueryImpl(connection);
    }

    public Map<String, List<String>> executeQuery(final QueryName queryName, Map<String, String> parameters) {
        switch (queryName) {
            case ADDGYMMEMBER:
                this.resultSet = this.factoryQuery.createAddGymMemberQuery(
                        parameters.get(QueryParameters.CF.toString()),
                        parameters.get(QueryParameters.NOME.toString()),
                        parameters.get(QueryParameters.COGNOME.toString()),
                        parameters.get(QueryParameters.CITTA.toString()),
                        parameters.get(QueryParameters.VIA.toString()),
                        Integer.parseInt(parameters.get(QueryParameters.NUMEROCIVICO.toString())),
                        parameters.get(QueryParameters.SESSO.toString()).charAt(0),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATANASCITA.toString())),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATACONSEGNACERTIFICATOMEDICO.toString())),
                        parameters.get(QueryParameters.NUMEROTELEFONO.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case ADDREPORTQUERY:
                this.resultSet = this.factoryQuery.createAddReportQuery(
                        parameters.get(QueryParameters.NUMEROSEGNALAZIONE.toString()),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATA.toString())),
                        java.sql.Time.valueOf(parameters.get(QueryParameters.ORA.toString())),
                        parameters.get(QueryParameters.STATO.toString()),
                        parameters.get(QueryParameters.DESCRIZIONE.toString()),
                        Integer.parseInt(parameters.get(QueryParameters.NUMEROMACCHINARIO.toString())),
                        parameters.get(QueryParameters.NOMEMACCHINARIO.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case CHANGELESSONROOM:
                this.resultSet = this.factoryQuery.ChangeLessonRoomQuery(
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATA.toString())),
                        java.sql.Time.valueOf(parameters.get(QueryParameters.ORA.toString())),
                        parameters.get(QueryParameters.VECCHIASALA.toString()),
                        parameters.get(QueryParameters.NUOVASALA.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case DAILYWEIGTHROOMATTENDANCE:
                this.resultSet = this.factoryQuery.DailyWeightRoomAttendanceQuery(
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATA.toString()))).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case EXERCISESBYMUSCLEGROUP:
                this.resultSet = this.factoryQuery.createExercisesByMuscleGroupQuery(
                        parameters.get(QueryParameters.NOMEGRUPPOMUSCOLARE.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case LASTYEARREPORTS:
                this.resultSet = this.factoryQuery.createLastYearReportsQuery().execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case LESSONSBYCOURSEEDITION:
                this.resultSet = this.factoryQuery.LessonsByCourseEditionQuery(
                        parameters.get(QueryParameters.NOMECORSO.toString()),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATAINIZIO.toString()))).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case LESSONSOFMEMBERBOOKEDINMONTH:
                this.resultSet = this.factoryQuery.LessonsOfMemberBookedInMonthQuery(
                        parameters.get(QueryParameters.CF.toString()),
                        Integer.parseInt(parameters.get(QueryParameters.MESE.toString())),
                        Integer.parseInt(parameters.get(QueryParameters.ANNO.toString()))).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case MEMBERWEEKLYATTENDANCE:
                this.resultSet = this.factoryQuery.MemberWeeklyAttendance(
                        parameters.get(QueryParameters.NOME.toString()),
                        parameters.get(QueryParameters.COGNOME.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case MEMBERWORKOUTPLANVIEWER:
                // Implementazione placeholder, da completare se serve
                return java.util.Collections.emptyMap();

            case MOSTLESSONBYTRAINERINMONTH:
                this.resultSet = this.factoryQuery.MostLessonByTrainerInMonthQuery().execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case MOSTUSEDMACHINESWEEK:
                this.resultSet = this.factoryQuery.MostUsedMachinesWeekQuery(
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATAINIZIO.toString())),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATAFINE.toString()))).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case REGISTERSUBSCRIPTION:
                this.resultSet = this.factoryQuery.RegisterSubscriptionQuery(
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATASTIPULAZIONE.toString())), 
                        parameters.get(QueryParameters.TIPO.toString()),                                   
                        Integer.parseInt(parameters.get(QueryParameters.DURATA.toString())),               
                        parameters.get(QueryParameters.CF.toString())                                      
                        ).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION:
                this.resultSet = this.factoryQuery.SearchMemberAndCheckValiditySubscription(
                        parameters.get(QueryParameters.NOME.toString()),
                        parameters.get(QueryParameters.COGNOME.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case SUBSCRIPTIONSEXPIRING:
                this.resultSet = this.factoryQuery.SubscriptionsExpiringQuery().execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION:
                this.resultSet = this.factoryQuery.SubscriptionVerificationAndAttendanceRegistration(
                        parameters.get(QueryParameters.NOME.toString()),
                        parameters.get(QueryParameters.COGNOME.toString()),
                        java.sql.Date.valueOf(parameters.get(QueryParameters.DATA.toString())),
                        java.sql.Time.valueOf(parameters.get(QueryParameters.ORA.toString()))).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case TRAINERCOURSESOFTHECURRENTYEAR:
                this.resultSet = this.factoryQuery.TrainerCoursesOfTheCurrentYearQuery(
                        parameters.get(QueryParameters.NOME.toString()),
                        parameters.get(QueryParameters.COGNOME.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case UNRESOLVEDREPORTSOLDERTHANTHREEDAYS:
                this.resultSet = this.factoryQuery.UnresolvedReportsOlderThanThreeDays().execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case USERMONTHLYCLASSATTENDANCE:
                // Implementazione placeholder, da completare se serve
                return java.util.Collections.emptyMap();

            case VIEWWORKOUTPLANQUERY:
                this.resultSet = this.factoryQuery.ViewWorkoutPlanQuery(
                        parameters.get(QueryParameters.CF.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            case WEEKLYATTENDANCEAVGCALCULATORBYMONTH:
                int mese = Integer.parseInt(parameters.get(QueryParameters.MESE.toString()));
                this.resultSet = this.factoryQuery.WeeklyAttendanceAVGCalculatorByMonthQuery(mese).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();

            default:
                throw new IllegalArgumentException("Operazione non riconosciuta: " + queryName);
        }
    }
}
