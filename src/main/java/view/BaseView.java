package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.BridgeCV;
import controller.Controller.QueryParameters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseView extends JPanel {

    protected final JPanel estPanel;
    protected final JPanel westPanel;
    protected final JPanel northWestPanel;
    protected final JPanel southWestPanel;
    protected final JPanel southCenterPanel;
    protected final JButton executeButton;

    // ogni area e associata ad una stringa che la identifica univocamente e che poi
    // passo come parametri al bridgeCV
    protected final Map<String, JTextArea> parameters;
    protected Map<String, List<String>> resultMap;

    // pattern Command. The bridge is the command.
    protected final BridgeCV bridgeCV;

    private final JTextArea resultArea;

    public BaseView(final BridgeCV bridgeCV) {
        this.setLayout(new BorderLayout());
        estPanel = new JPanel();
        westPanel = new JPanel();
        northWestPanel = new JPanel();
        southWestPanel = new JPanel();
        southCenterPanel = new JPanel();

        estPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        northWestPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        southWestPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));


        // divisione orizzontale tra i due pannelli principali di sinistra e destra
        JSplitPane splitPaneEstWest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPanel, estPanel);
        splitPaneEstWest.setResizeWeight(0.5); // Divide lo spazio equamente
        splitPaneEstWest.setDividerLocation(0.5); // Divider iniziale a metà
        splitPaneEstWest.setEnabled(false); // Disabilita la possibilità di spostare il divider
        splitPaneEstWest.setDividerSize(0); // Nasconde il divisore visivamente

        // divisione verticale del pannello di sinistra tra pannello superiore e
        // inferiore
        JSplitPane splitPaneWestSouthNorth = new JSplitPane(JSplitPane.VERTICAL_SPLIT, northWestPanel, southWestPanel);
        splitPaneWestSouthNorth.setResizeWeight(0.5);
        splitPaneWestSouthNorth.setDividerLocation(0.5);
        splitPaneWestSouthNorth.setEnabled(false);
        splitPaneWestSouthNorth.setDividerSize(0);

        this.add(splitPaneEstWest, BorderLayout.CENTER);
        westPanel.add(splitPaneWestSouthNorth);

        //layout pannello a sinistra nord
        this.northWestPanel.setLayout(new GridLayout(7,0));

        this.southWestPanel.setLayout(new BorderLayout());
        this.executeButton = new JButton("Execute");
        this.southWestPanel.add(executeButton, BorderLayout.SOUTH);

        //layout pannello a sinistra sud centro
        this.southCenterPanel.setLayout(new GridLayout(5,2));
        this.southWestPanel.add(southCenterPanel, BorderLayout.CENTER);

        this.parameters = new HashMap<>();
        this.bridgeCV = bridgeCV;

        for (var param : QueryParameters.values()) {
            parameters.put(param.toString(), new JTextArea(param.toString()));
        }

        // Pannello per mostrare la resultMap
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        estPanel.setLayout(new BorderLayout());
        estPanel.add(scrollPane, BorderLayout.CENTER);

        this.executeButton.addActionListener(e -> {
            final Map<String, String> newParametersMap = this.parameters.entrySet().stream()
                    .collect(HashMap<String, String>::new,
                            (r, m) -> {
                                final String content = m.getValue().getText();
                                r.put(m.getKey(), content);
                            },
                            (om, nm) -> {
                                nm.putAll(om);
                            });
            this.resultMap = this.bridgeCV.executeQuery(newParametersMap);

            // Stampa la resultMap nel pannello estPanel
            if (resultMap != null) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
                    sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                resultArea.setText(sb.toString());
            } else {
                resultArea.setText("Nessun risultato.");
            }
        });
    }
}
