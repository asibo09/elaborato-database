package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {

    private final JFrame frame;
    private final JPanel panel;

    public View() {
        frame = new JFrame("My Application");
        panel = new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(panel);
        frame.setVisible(true);
    }
}
