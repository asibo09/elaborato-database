package controller;

import model.queryexecutor.QueryExecutor;
import view.View;

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

    public enum QueryParameters {
        CF,
        NOME,
        COGNOME,
        CITTA,
        VIA,
        NUMEROCIVICO,
        SESSO,
        DATANASCITA,
        DATACONSEGNACERTIFICATOMEDICO,
        NUMEROTELEFONO,
        NUMEROSEGNALAZIONE,
        DATA,
        ORA,
        STATO,
        DESCRIZIONE,
        NUMEROMACCHINARIO,
        NOMEMACCHINARIO,
        VECCHIASALA,
        NUOVASALA,
        NOMEGRUPPOMUSCOLARE,
        NOMECORSO,
        DATAINIZIO,
        DATAFINE,
        MESE,
        ANNO,
        DATASTIPULAZIONE,
        TIPO,
        DURATA
    }

    private final ManageConnection mc;
    private final QueryExecutor qe;
    private final BridgeCV bcv;
    private final ManageConnection manageConnection;
    private final View view;

    public Controller() throws Exception {
        this.mc = new ManageConnection();
        this.qe = new QueryExecutor(mc.getConnection());
        this.bcv = new BridgeCV(this);
        this.manageConnection = new ManageConnection();
        this.view = new View(bcv);
    }

    public Map<String,List<String>> executeQuery(final QueryName queryName, Map<String,String> parameters) {
        return this.qe.executeQuery(queryName,parameters);
    }

    public void closeConnection() throws SQLException {
        this.manageConnection.closeConnection();
    }

}
