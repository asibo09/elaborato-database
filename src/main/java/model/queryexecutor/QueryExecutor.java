package model.queryexecutor;

import controller.Controller;
import controller.Controller.QueryName;
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
                        parameters.get("CF"),
                        parameters.get("Nome"),
                        parameters.get("Cognome"),
                        parameters.get("Citta"),
                        parameters.get("Via"),
                        Integer.parseInt(parameters.get("NumeroCivico")),
                        parameters.get("Sesso").charAt(0),
                        java.sql.Date.valueOf(parameters.get("DataNascita")),
                        java.sql.Date.valueOf(parameters.get("DataConsegna")),
                        parameters.get("NumeroTelefono")
                ).execute();
                return new ManipulationResult(this.resultSet).getManupulatedResult();
                break;

            case ADDREPORTQUERY:
                System.out.println("Eseguendo l'operazione ADDREPORTQUERY...");
                this.factoryQuery.createAddReportQuery(parameters.get("ReportData")).execute();
                break;

            case CHANGELESSONROOM:
                System.out.println("Eseguendo l'operazione CHANGELESSONROOM...");
                this.factoryQuery.createChangeLessonRoomQuery(
                        java.sql.Date.valueOf(parameters.get("Data")),
                        java.sql.Time.valueOf(parameters.get("Ora")),
                        parameters.get("SalaVecchia"),
                        parameters.get("SalaNuova")
                ).execute();
                break;

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
                System.out.println("Eseguendo l'operazione MEMBERWEEKLYATTENDANCE...");
                // Logica specifica
                break;

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
