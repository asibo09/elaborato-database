package controller;

import model.queryexecutor.QueryExecutor;

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

    public Controller() throws Exception {
        this.mc = new ManageConnection();
        this.qe = new QueryExecutor(this.mc.getConnection());
        this.bcv = new BridgeCV();
    }


}
