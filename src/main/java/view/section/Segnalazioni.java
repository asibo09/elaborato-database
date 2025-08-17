package view.section;

import controller.BridgeCV;
import controller.Controller.QueryName;
import controller.Controller.QueryParameters;
import view.BaseView;

import javax.swing.*;
import java.awt.*;

public class Segnalazioni extends BaseView {

    private final JButton addReportButton;
    private final JButton lastYearReportsButton;
    private final JButton unresolvedReportsOlderThanThreeDaysButton;

    public Segnalazioni(final BridgeCV bridgeCV) {
        super(bridgeCV);

        this.northWestPanel.setLayout(new GridLayout(3, 0));


        // Creazione bottoni
        this.addReportButton = new JButton("Inserimento segnalazione");
        this.lastYearReportsButton = new JButton("Segnalazioni nell'ultimo anno");
        this.unresolvedReportsOlderThanThreeDaysButton = new JButton("Segnalazioni non risolte da più di tre giorni");
        

        // Colore bianco per tutti i bottoni
        this.addReportButton.setBackground(Color.WHITE);
        this.lastYearReportsButton.setBackground(Color.WHITE);
        this.unresolvedReportsOlderThanThreeDaysButton.setBackground(Color.WHITE);

        // Aggiunta bottoni al pannello
        northWestPanel.add(this.addReportButton);
        northWestPanel.add(this.lastYearReportsButton);
        northWestPanel.add(this.unresolvedReportsOlderThanThreeDaysButton);

        // ActionListener per ogni bottone

        // ADD REPORT (Data, Ora, Stato, Descrizione, Numero Macchinario, Nome Macchinario, CF)
        this.addReportButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.ADDREPORTQUERY);

            this.southCenterPanel.add(new JLabel("Data"));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATA.toString()));

            this.southCenterPanel.add(new JLabel("Ora"));
            this.southCenterPanel.add(parameters.get(QueryParameters.ORA.toString()));

            this.southCenterPanel.add(new JLabel("CF"));
            this.southCenterPanel.add(parameters.get(QueryParameters.CF.toString()));

            this.southCenterPanel.add(new JLabel("Stato"));
            this.southCenterPanel.add(parameters.get(QueryParameters.STATO.toString()));

            this.southCenterPanel.add(new JLabel("Descrizione"));
            this.southCenterPanel.add(parameters.get(QueryParameters.DESCRIZIONE.toString()));

            this.southCenterPanel.add(new JLabel("Numero Macchinario"));
            this.southCenterPanel.add(parameters.get(QueryParameters.NUMEROMACCHINARIO.toString()));
            
            this.southCenterPanel.add(new JLabel("Nome Macchinario"));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOMEMACCHINARIO.toString()));

            repaintPanel();
        });


        // LAST YEAR REPORTS (Nessun parametro richiesto)
        this.lastYearReportsButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.LASTYEARREPORTS);


            repaintPanel();
        });


        // UNRESOLVED REPORTS OLDER THAN THREE DAYS (Nessun parametro richiesto)
        this.unresolvedReportsOlderThanThreeDaysButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.UNRESOLVEDREPORTSOLDERTHANTHREEDAYS);

            repaintPanel();
        });

        
    }
}
