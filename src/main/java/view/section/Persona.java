package view.section;

import javax.swing.*;

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

        this.addGymMemberButton = new JButton(QueryName.ADDGYMMEMBER.toString());
        northWestPanel.add(this.addGymMemberButton);
        this.dailyWeightRoomAttendanceButton = new JButton(QueryName.DAILYWEIGTHROOMATTENDANCE.toString());
        northWestPanel.add(this.dailyWeightRoomAttendanceButton);
        this.lessonsOfMemberBookedInMonthButton = new JButton(QueryName.LESSONSOFMEMBERBOOKEDINMONTH.toString());
        northWestPanel.add(this.lessonsOfMemberBookedInMonthButton);
        this.memberWeeklyAttendanceButton = new JButton(QueryName.MEMBERWEEKLYATTENDANCE.toString());
        northWestPanel.add(this.memberWeeklyAttendanceButton);
        this.registerSubscriptionButton = new JButton(QueryName.REGISTERSUBSCRIPTION.toString());
        northWestPanel.add(this.registerSubscriptionButton);
        this.searchMemberAndCheckValiditySubscriptionButton = new JButton(QueryName.SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION.toString());
        northWestPanel.add(this.searchMemberAndCheckValiditySubscriptionButton);
        this.subscriptionsExpiringButton = new JButton(QueryName.SUBSCRIPTIONSEXPIRING.toString());
        northWestPanel.add(this.subscriptionsExpiringButton);
        this.subscriptionVerificationAndAttendanceRegistrationButton = new JButton(QueryName.SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION.toString());
        northWestPanel.add(this.subscriptionVerificationAndAttendanceRegistrationButton);
        this.weeklyAttendanceAVGCalculatorByMonthButton = new JButton(QueryName.WEEKLYATTENDANCEAVGCALCULATORBYMONTH.toString());
        northWestPanel.add(this.weeklyAttendanceAVGCalculatorByMonthButton);

        this.addGymMemberButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.ADDGYMMEMBER);
            this.southCenterPanel.add(parameters.get(QueryParameters.CF.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.CITTA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATACONSEGNACERTIFICATOMEDICO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATANASCITA.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NUMEROCIVICO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.NUMEROTELEFONO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.SESSO.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.VIA.toString()));
            repaintSouthWestCenter();
        });
        this.dailyWeightRoomAttendanceButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.DAILYWEIGTHROOMATTENDANCE);
        });
        this.lessonsOfMemberBookedInMonthButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.LESSONSOFMEMBERBOOKEDINMONTH);
        });
        this.memberWeeklyAttendanceButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.MEMBERWEEKLYATTENDANCE);
            this.southCenterPanel.add(parameters.get(QueryParameters.NOME.toString()));
            this.southCenterPanel.add(parameters.get(QueryParameters.COGNOME.toString()));
            repaintSouthWestCenter();
        });
        this.registerSubscriptionButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.REGISTERSUBSCRIPTION);
        });
        this.searchMemberAndCheckValiditySubscriptionButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.SEARCHMEMBERANDCHECKVALIDITYSUBSCRIPTION);
        });
        this.subscriptionsExpiringButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.SUBSCRIPTIONSEXPIRING);
        });
        this.subscriptionVerificationAndAttendanceRegistrationButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.SUBSCRIPTIONVERIFICATIONANDATTENDANCEREGISTRATION);
        });
        this.weeklyAttendanceAVGCalculatorByMonthButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.WEEKLYATTENDANCEAVGCALCULATORBYMONTH);
        });

    }

    private void repaintSouthWestCenter() {
        this.southCenterPanel.revalidate();
        this.southCenterPanel.repaint();
    }
}
