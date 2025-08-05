package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;

public class BaseView extends JPanel {

    protected final JPanel estPanel;
    protected final JPanel westPanel;
    protected final JPanel northWestPanel;
    protected final JPanel southWestPanel;

    public BaseView() {
        this.setLayout(new BorderLayout());
        estPanel = new JPanel();
        westPanel = new JPanel();
        northWestPanel = new JPanel();
        southWestPanel = new JPanel();

        estPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        northWestPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        southWestPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        westPanel.setLayout(new BorderLayout());
        westPanel.add(northWestPanel, BorderLayout.NORTH);
        westPanel.add(southWestPanel, BorderLayout.SOUTH);

        northWestPanel.add(new JTextArea("stirati"));

        //divisione orizzontale tra i due pannelli principali di sinistra e destra 
        JSplitPane splitPaneEstWest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPanel, estPanel);
        splitPaneEstWest.setResizeWeight(0.5); // Divide lo spazio equamente
        splitPaneEstWest.setDividerLocation(0.5); // Divider iniziale a metà
        splitPaneEstWest.setEnabled(false); // Disabilita la possibilità di spostare il divider
        splitPaneEstWest.setDividerSize(0); // Nasconde il divisore visivamente

        //divisione verticale del pannello di sinistra tra pannello superiore e inferiore 
        JSplitPane splitPaneWestSouthNorth = new JSplitPane(JSplitPane.VERTICAL_SPLIT, northWestPanel, southWestPanel);
        splitPaneWestSouthNorth.setResizeWeight(0.5); 
        splitPaneWestSouthNorth.setDividerLocation(0.5); 
        splitPaneWestSouthNorth.setEnabled(false); 
        splitPaneWestSouthNorth.setDividerSize(0); 

        this.add(splitPaneEstWest, BorderLayout.CENTER);
        westPanel.add(splitPaneWestSouthNorth);
    }
}
