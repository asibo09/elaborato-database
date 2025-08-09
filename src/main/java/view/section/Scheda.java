package view.section;

import controller.BridgeCV;
import controller.Controller.QueryName;
import view.BaseView;

import javax.swing.*;
import java.awt.*;

public class Scheda extends BaseView {

    private final JButton viewWorkoutPlanButton;

    public Scheda(final BridgeCV bridgeCV) {
        super(bridgeCV);

        this.northWestPanel.setLayout(new GridLayout(1, 0));

        this.viewWorkoutPlanButton = new JButton("Visualizza scheda allenamento");
        this.viewWorkoutPlanButton.setBackground(Color.WHITE);

        northWestPanel.add(this.viewWorkoutPlanButton);

        // Parametro richiesto: CF
        this.viewWorkoutPlanButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.VIEWWORKOUTPLANQUERY);

            this.southCenterPanel.add(new JLabel("CF"));
            this.southCenterPanel.add(parameters.get("CF"));

            repaintPanel();
        });
    }
}
