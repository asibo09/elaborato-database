package controller;

import model.queryexecutor.QueryExecutor;

import java.util.List;
import java.util.Map;

public class Controller {



    public enum QueryName {
        ADDGYMMEMBER,
        ADDREPORTQUERY,
        CHANGELESSONROOM,
        DAILYWEIGTHROOMATTENDANCE,
        EXERCISESBYMUSCLEGROUP,
        LASTYEARREPORTS,
        LESSONSBYCOURSEEDITION,
        LESSONSOFMEMBERBOOKEDINMONTH,
        MEMBERWEEKLYATTENDANCE,
        MEMBERWORKOUTPLANVIEWER,
        MOSTLESSONBYTRAINERINMONTH,
        MOSTUSEDMACHINESWEEK,
        REGISTERSUBSCRIPTION,
        SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION,
        SUBSCRIPTIONSEXPIRING,
        SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION,
        TRAINERCOURSESOFTHECURRENTYEAR,
        UNRESOLVEDREPORTSOLDERTHANTHREEDAYS,
        USERMONTHLYCLASSATTENDANCE,
        VIEWWORKOUTPLANQUERY,
        WEEKLYATTENDANCEAVGCALCULATORBYMONTH
    }

    public enum QueryParameters {
        CF,
        NOME,
        COGNOME,
        CITTA,
        VIA,
        NUMEROCIVICO,
        NUMEROTELEFONO,
        DATANASCITA,
        DATACONSEGNACERTIFICATOMEDICO,
        SESSO
    }

    private final ManageConnection mc;
    private final QueryExecutor qe;
    private final BridgeCV bcv;

    public Controller() throws Exception {

    }

    public Map<String,List<String>> executeQuery(final QueryName queryName, Map<String,String> parameters) {
        return null; //this.qe.executeQuery(queryName,parameters);
    }

}
