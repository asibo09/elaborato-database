package controller;

public class Controller {

    public enum QueryName {
        ADDGYMMEMBER,
        ADDREPORTQUERY,
        DAILYWEIGTHROOMATTENDANCE,
        EXERCISESBYMUSCLEGROUP,
        LESSONSBYCOURSEEDITION,
        MEMBERWORKOUTPLANVIEWER,
        REGISTERSUBSCRIPTION,
        SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION,
        TRAINERCOURSESOFTHECURRENTYEAR,
        USERMONTHLYCLASSATTENDANCE,
        WEEKLYATTENDANCEAVGCALCULATORBYMONTH
    }

    //url del database su cui si vuole eseguire il codice
    public final static String DATABASE_URL = "jdbc:mysql://localhost:3306/gym";
    
}
