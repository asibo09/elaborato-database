package controller;

import model.queryexecutor.QueryExecutor;

import java.sql.SQLException;
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

    private final ManageConnection mc;
    private final QueryExecutor qe;
    private final BridgeCV bcv;
    private final ManageConnection manageConnection;

    public Controller(ManageConnection mc, QueryExecutor qe, BridgeCV bcv) throws Exception {
        this.mc = mc;
        this.qe = qe;
        this.bcv = bcv;
        this.manageConnection = new ManageConnection();
    }

    public Map<String,List<String>> executeQuery(final QueryName queryName, Map<String,String> parameters) {
        return this.qe.executeQuery(queryName,parameters);
    }

    public void closeConnection() throws SQLException {
        this.manageConnection.closeConnection();
    }

}
