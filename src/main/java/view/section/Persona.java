package view.section;

import javax.swing.*;
import java.awt.*;

import controller.BridgeCV;
import controller.Controller.QueryName;
import controller.Controller.QueryParameters;
import view.BaseView;

public class Persona extends BaseView {

    private final JButton addGymMemberButton;
    private final JButton dailyWeightRoomAttendanceButton;
    private final JButton lessonsOfMemberBookedInMonthButton;
    private final JButton memberWeeklyAttendanceButton;
    private final JButton registerSubscriptionButton;
    private final JButton searchMemberAndCheckValiditySubscriptionButton;
    private final JButton subscriptionsExpiringButton;
    private final JButton subscriptionVerificationAndAttendanceRegistrationButton;
    private final JButton weeklyAttendanceAVGCalculatorByMonthButton;

    public Persona(final BridgeCV bridgeCV) {
        super(bridgeCV);
        this.northWestPanel.setLayout(new GridLayout(9, 0));

        // Creazione bottoni
        this.addGymMemberButton = new JButton("Aggiungi iscritto");
        this.dailyWeightRoomAttendanceButton = new JButton("Presenze sala pesi giornaliere");
        this.lessonsOfMemberBookedInMonthButton = new JButton("Lezioni prenotate dall'iscritto nel mese");
        this.memberWeeklyAttendanceButton = new JButton("Presenze settimanali iscritto");
        this.registerSubscriptionButton = new JButton("Registra abbonamento");
        this.searchMemberAndCheckValiditySubscriptionButton = new JButton("Cerca iscritto e verifica abbonamento");
        this.subscriptionsExpiringButton = new JButton("Abbonamenti in scadenza");
        this.subscriptionVerificationAndAttendanceRegistrationButton = new JButton("Verifica abbonamento e registra presenza");
        this.weeklyAttendanceAVGCalculatorByMonthButton = new JButton("Media presenze settimanali per mese");

        // Imposta il colore di sfondo dopo la creazione di tutti i bottoni
        this.addGymMemberButton.setBackground(Color.WHITE);
        this.dailyWeightRoomAttendanceButton.setBackground(Color.WHITE);
        this.lessonsOfMemberBookedInMonthButton.setBackground(Color.WHITE);
        this.memberWeeklyAttendanceButton.setBackground(Color.WHITE);
        this.registerSubscriptionButton.setBackground(Color.WHITE);
        this.searchMemberAndCheckValiditySubscriptionButton.setBackground(Color.WHITE);
        this.subscriptionsExpiringButton.setBackground(Color.WHITE);
        this.subscriptionVerificationAndAttendanceRegistrationButton.setBackground(Color.WHITE);
        this.weeklyAttendanceAVGCalculatorByMonthButton.setBackground(Color.WHITE);

        // Aggiunta bottoni al pannello
        northWestPanel.add(this.addGymMemberButton);
        northWestPanel.add(this.dailyWeightRoomAttendanceButton);
        northWestPanel.add(this.lessonsOfMemberBookedInMonthButton);
        northWestPanel.add(this.memberWeeklyAttendanceButton);
        northWestPanel.add(this.registerSubscriptionButton);
        northWestPanel.add(this.searchMemberAndCheckValiditySubscriptionButton);
        northWestPanel.add(this.subscriptionsExpiringButton);
        northWestPanel.add(this.subscriptionVerificationAndAttendanceRegistrationButton);
        northWestPanel.add(this.weeklyAttendanceAVGCalculatorByMonthButton);

        // ActionListener
        // ADD GYM MEMBER
        this.addGymMemberButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.ADDGYMMEMBER);

            this.southCenterPanel.add(new JLabel(QueryParameters.CF.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.CF.toString()));
            
            this.southCenterPanel.add(new JLabel(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.COGNOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.CITTA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.CITTA.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.VIA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.VIA.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.NUMEROCIVICO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NUMEROCIVICO.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.NUMEROTELEFONO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NUMEROTELEFONO.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.DATANASCITA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATANASCITA.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.DATACONSEGNACERTIFICATOMEDICO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATACONSEGNACERTIFICATOMEDICO.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.SESSO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.SESSO.toString()));

            repaintPanel();
        });

        this.dailyWeightRoomAttendanceButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.DAILYWEIGTHROOMATTENDANCE);

            this.southCenterPanel.add(new JLabel("Data"));
            this.southCenterPanel.add(new JTextField());

            repaintPanel();
        });

        this.lessonsOfMemberBookedInMonthButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.LESSONSOFMEMBERBOOKEDINMONTH);

            this.southCenterPanel.add(new JLabel(QueryParameters.CF.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.CF.toString()));

            this.southCenterPanel.add(new JLabel("Mese"));
            this.southCenterPanel.add(new JTextField());

            this.southCenterPanel.add(new JLabel("Anno"));
            this.southCenterPanel.add(new JTextField());

            repaintPanel();
        });

        this.memberWeeklyAttendanceButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.MEMBERWEEKLYATTENDANCE);

            this.southCenterPanel.add(new JLabel(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.COGNOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));

            repaintPanel();
        });

        this.registerSubscriptionButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.REGISTERSUBSCRIPTION);

            this.southCenterPanel.add(new JLabel("Data stipulazione"));
            this.southCenterPanel.add(new JTextField());

            this.southCenterPanel.add(new JLabel("Tipo"));
            this.southCenterPanel.add(new JTextField());

            this.southCenterPanel.add(new JLabel("Durata"));
            this.southCenterPanel.add(new JTextField());

            this.southCenterPanel.add(new JLabel(QueryParameters.CF.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.CF.toString()));

            repaintPanel();
        });

        this.searchMemberAndCheckValiditySubscriptionButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION);

            this.southCenterPanel.add(new JLabel(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.COGNOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.DATANASCITA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATANASCITA.toString()));

            repaintPanel();
        });

        this.subscriptionsExpiringButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.SUBSCRIPTIONSEXPIRING);
            repaintPanel();
        });

        this.subscriptionVerificationAndAttendanceRegistrationButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION);

            this.southCenterPanel.add(new JLabel(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));

            this.southCenterPanel.add(new JLabel(QueryParameters.COGNOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));

            this.southCenterPanel.add(new JLabel("Data"));
            this.southCenterPanel.add(new JTextField());

            this.southCenterPanel.add(new JLabel("Ora"));
            this.southCenterPanel.add(new JTextField());

            repaintPanel();
        });

        this.weeklyAttendanceAVGCalculatorByMonthButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.WEEKLYATTENDANCEAVGCALCULATORBYMONTH);

            this.southCenterPanel.add(new JLabel("Mese"));
            this.southCenterPanel.add(new JTextField());

            repaintPanel();
        });
    }
}
