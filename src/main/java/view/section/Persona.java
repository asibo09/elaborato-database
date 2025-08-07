package view.section;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import controller.BridgeCV;
import model.factory.api.FactoryQuery;
import model.queryexecutor.api.Query;
import controller.Controller.QueryName;
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
    private final JButton executeButton;

    //ogni area e associata ad una stringa che la identifica univocamente e che poi passo come parametri al bridgeCV
    private final Map<String,JTextArea> parameters;
    private Map<String,String> resultMap;

    //pattern Command. The bridge is the command.
    private final BridgeCV bridgeCV;

    public Persona(final BridgeCV bridgeCV) {
        super();

        this.parameters = new HashMap<>();
        this.bridgeCV = bridgeCV;
        this.executeButton = new JButton("Execute");
        this.executeButton.addActionListener(e -> {
            final Map<String,String> newParametersMap = this.parameters.entrySet().stream()
                    .collect(HashMap<String,String>::new,
                            (r,m) ->{
                                final String content = m.getValue().getText();
                                r.put(m.getKey(),content);
                    },
                            (om,nm) -> {
                                nm.putAll(om);
                    });
            this.bridgeCV.executeQuery(newParametersMap);
        });

        northWestPanel.setLayout(new BorderLayout());

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
            this.bridgeCV.setQueryName(QueryName.ADDGYMMEMBER);
        });
        this.dailyWeightRoomAttendanceButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.DAILYWEIGTHROOMATTENDANCE);
        });
        this.lessonsOfMemberBookedInMonthButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.LESSONSOFMEMBERBOOKEDINMONTH);
        });
        this.memberWeeklyAttendanceButton.addActionListener(e -> {
            this.bridgeCV.setQueryName(QueryName.MEMBERWEEKLYATTENDANCE);
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
}
