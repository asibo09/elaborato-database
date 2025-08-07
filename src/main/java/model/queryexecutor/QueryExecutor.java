package model.queryexecutor;

import controller.Controller;
import controller.Controller.QueryName;
import controller.Controller.QueryParameters;
import model.factory.impl.FactoryQueryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        parameters.get(QueryParameters.NUMEROTELEFONO.toString())
                ).execute();
                new ManipulationResult(this.resultSet).getManupulatedResult();
            case ADDREPORTQUERY:
            case CHANGELESSONROOM:
            case DAILYWEIGTHROOMATTENDANCE:
                System.out.println("Eseguendo l'operazione DAILYWEIGTHROOMATTENDANCE...");
                // Logica specifica
                break;

            case EXERCISESBYMUSCLEGROUP:
                System.out.println("Eseguendo l'operazione EXERCISESBYMUSCLEGROUP...");
                // Logica specifica
                break;

            case LASTYEARREPORTS:
                System.out.println("Eseguendo l'operazione LASTYEARREPORTS...");
                // Logica specifica
                break;

            case LESSONSBYCOURSEEDITION:
                System.out.println("Eseguendo l'operazione LESSONSBYCOURSEEDITION...");
                // Logica specifica
                break;

            case LESSONSOFMEMBERBOOKEDINMONTH:
                System.out.println("Eseguendo l'operazione LESSONSOFMEMBERBOOKEDINMONTH...");
                // Logica specifica
                break;

            case MEMBERWEEKLYATTENDANCE:
                this.resultSet = this.factoryQuery.MemberWeeklyAttendance(parameters.get(QueryParameters.NOME.toString()),parameters.get(QueryParameters.COGNOME.toString())).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();
            case MEMBERWORKOUTPLANVIEWER:
                System.out.println("Eseguendo l'operazione MEMBERWORKOUTPLANVIEWER...");
                // Logica specifica
                break;

            case MOSTLESSONBYTRAINERINMONTH:
                System.out.println("Eseguendo l'operazione MOSTLESSONBYTRAINERINMONTH...");
                // Logica specifica
                break;

            case MOSTUSEDMACHINESWEEK:
                System.out.println("Eseguendo l'operazione MOSTUSEDMACHINESWEEK...");
                // Logica specifica
                break;

            case REGISTERSUBSCRIPTION:
                System.out.println("Eseguendo l'operazione REGISTERSUBSCRIPTION...");
                // Logica specifica
                break;

            case SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION:
                System.out.println("Eseguendo l'operazione SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION...");
                // Logica specifica
                break;

            case SUBSCRIPTIONSEXPIRING:
                System.out.println("Eseguendo l'operazione SUBSCRIPTIONSEXPIRING...");
                // Logica specifica
                break;

            case SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION:
                System.out.println("Eseguendo l'operazione SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION...");
                // Logica specifica
                break;

            case TRAINERCOURSESOFTHECURRENTYEAR:
                System.out.println("Eseguendo l'operazione TRAINERCOURSESOFTHECURRENTYEAR...");
                // Logica specifica
                break;

            case UNRESOLVEDREPORTSOLDERTHANTHREEDAYS:
                System.out.println("Eseguendo l'operazione UNRESOLVEDREPORTSOLDERTHANTHREEDAYS...");
                // Logica specifica
                break;

            case USERMONTHLYCLASSATTENDANCE:
                System.out.println("Eseguendo l'operazione USERMONTHLYCLASSATTENDANCE...");
                // Logica specifica
                break;

            case VIEWWORKOUTPLANQUERY:
                System.out.println("Eseguendo l'operazione VIEWWORKOUTPLANQUERY...");
                // Logica specifica
                break;

            case WEEKLYATTENDANCEAVGCALCULATORBYMONTH:
                System.out.println("Eseguendo l'operazione WEEKLYATTENDANCEAVGCALCULATORBYMONTH...");
                // Logica specifica
                break;

            default:
                throw new IllegalArgumentException("Operazione non riconosciuta: " + queryName);
        }
        return java.util.Collections.emptyMap();
    }
}
