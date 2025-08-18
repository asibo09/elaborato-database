package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.BridgeCV;
import controller.Controller.QueryParameters;

import java.awt.*;
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
    private final JButton clearButton;

    // ogni area e associata ad una stringa che la identifica univocamente e che poi
    // passo come parametri al bridgeCV
    protected final Map<String, JTextField> parameters;
    protected Map<String, List<String>> resultMap;

    // pattern Command. The bridge is the command.
    protected final BridgeCV bridgeCV;

    private final JTable resultTable;
    private final DefaultTableModel tableModel;

    public BaseView(final BridgeCV bridgeCV) {
        this.setLayout(new BorderLayout());
        estPanel = new JPanel();
        westPanel = new JPanel();
        northWestPanel = new JPanel();
        southWestPanel = new JPanel();
        southCenterPanel = new JPanel();

        // divisione orizzontale tra i due pannelli principali di sinistra e destra
        JSplitPane splitPaneEstWest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPanel, estPanel);
        splitPaneEstWest.setResizeWeight(0.01); 
        splitPaneEstWest.setDividerLocation(0.99); 
        splitPaneEstWest.setEnabled(false); 
        splitPaneEstWest.setDividerSize(0); 

        // divisione verticale tra i due pannelli di sinistra
        westPanel.setLayout(new BorderLayout());
        westPanel.add(northWestPanel, BorderLayout.NORTH);
        westPanel.add(southWestPanel, BorderLayout.CENTER);

        this.add(splitPaneEstWest, BorderLayout.CENTER);

        this.southWestPanel.setLayout(new BorderLayout());
        this.executeButton = new JButton("Execute");
        this.executeButton.setBackground(Color.WHITE);
        this.executeButton.setPreferredSize(new Dimension(this.executeButton.getPreferredSize().width, 40));

        this.clearButton = new JButton("Clear");
        this.clearButton.setBackground(Color.WHITE);
        this.clearButton.setPreferredSize(new Dimension(this.clearButton.getPreferredSize().width, 40));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(executeButton);
        buttonPanel.add(clearButton);
        this.southWestPanel.add(buttonPanel, BorderLayout.SOUTH);


        // layout pannello a sinistra sud centro
        this.southCenterPanel.setLayout(new GridLayout(25, 0));
        this.southWestPanel.add(southCenterPanel, BorderLayout.CENTER);

        this.parameters = new HashMap<>();
        this.bridgeCV = bridgeCV;

        for (var param : QueryParameters.values()) {
            parameters.put(param.toString(), new JTextField());
        }

        // Tabella per mostrare i risultati
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        estPanel.setLayout(new BorderLayout());
        estPanel.add(tableScrollPane, BorderLayout.CENTER);

        this.clearButton.addActionListener(e -> {
            for (JTextField field : parameters.values()) {
                field.setText("");
            }

            // svuoto la tabella
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        });


        this.executeButton.addActionListener(e -> {
            final Map<String, String> newParametersMap = this.parameters.entrySet().stream()
                    .collect(HashMap<String, String>::new,
                            (r, m) -> r.put(m.getKey(), m.getValue().getText()),
                            Map::putAll);
            this.resultMap = this.bridgeCV.executeQuery(newParametersMap);

            updateResultTable(resultMap);
        });
    }

    

    private void updateResultTable(Map<String, List<String>> resultMap) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        if (resultMap == null || resultMap.isEmpty()) {
            tableModel.setColumnIdentifiers(new String[] { "Nessun risultato" });
            return;
        }

        String[] columns = resultMap.keySet().toArray(new String[0]);
        tableModel.setColumnIdentifiers(columns);

        int numRows = resultMap.values().stream().mapToInt(List::size).max().orElse(0);

        for (int i = 0; i < numRows; i++) {
            String[] row = new String[columns.length];
            for (int j = 0; j < columns.length; j++) {
                List<String> values = resultMap.get(columns[j]);
                row[j] = (values != null && i < values.size()) ? values.get(i) : "";
            }
            tableModel.addRow(row);
        }
    }

    // ridisegna il pannello genitore 
    protected void repaintPanel() {
        this.revalidate();
        this.repaint();
    }
}
