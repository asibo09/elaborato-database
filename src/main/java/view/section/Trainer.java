package view.section;

import controller.BridgeCV;
import controller.Controller.QueryName;
import view.BaseView;

import javax.swing.*;
import java.awt.*;

public class Trainer extends BaseView {

    private final JButton trainerCoursesOfTheCurrentYearButton;

    public Trainer(final BridgeCV bridgeCV) {
        super(bridgeCV);

        this.northWestPanel.setLayout(new GridLayout(1, 0));

        this.trainerCoursesOfTheCurrentYearButton = new JButton("Corsi tenuti dal trainer quest'anno");
        this.trainerCoursesOfTheCurrentYearButton.setBackground(Color.WHITE);

        northWestPanel.add(this.trainerCoursesOfTheCurrentYearButton);

        // Parametri richiesti: Nome, Cognome
        this.trainerCoursesOfTheCurrentYearButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.TRAINERCOURSESOFTHECURRENTYEAR);

            this.southCenterPanel.add(new JLabel("Nome"));
            this.southCenterPanel.add(parameters.get("NOME"));

            this.southCenterPanel.add(new JLabel("Cognome"));
            this.southCenterPanel.add(parameters.get("COGNOME"));

            repaintPanel();
        });
    }
}
