package view.section;

import controller.BridgeCV;
import controller.Controller.QueryName;
import view.BaseView;

import javax.swing.*;
import java.awt.*;

public class Macchinari extends BaseView {

    private final JButton mostUsedMachinesWeekButton;

    public Macchinari(final BridgeCV bridgeCV) {
        super(bridgeCV);

        this.northWestPanel.setLayout(new GridLayout(1, 0));

        this.mostUsedMachinesWeekButton = new JButton("Macchinari più usati della settimana");
        this.mostUsedMachinesWeekButton.setBackground(Color.WHITE);

        northWestPanel.add(this.mostUsedMachinesWeekButton);

        // Nessun parametro richiesto per questa query
        this.mostUsedMachinesWeekButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.MOSTUSEDMACHINESWEEK);
            repaintPanel();
        });
    }
}
