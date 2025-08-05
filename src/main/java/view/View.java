package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

public class View {

    private final JFrame frame;
    private JPanel panel;
    private final JToolBar menu;
    private final JButton persona;
    private final JButton corsi;
    private final JButton macchinari;
    private final JButton trainer;
    private final JButton schede;

    public View() {
        frame = new JFrame("My Application");
        panel = new JPanel();
        menu = new JToolBar();
        persona = new JButton("Persona");
        corsi = new JButton("Corsi");
        macchinari = new JButton("Macchinari");
        trainer = new JButton("Trainer");
        schede = new JButton("Schede");

        menu.setFloatable(false);
        menu.add(persona);
        menu.add(corsi);
        menu.add(macchinari);
        menu.add(trainer);
        menu.add(schede);

        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        persona.addActionListener(e -> {
            frame.remove(panel);
            panel = new BaseView();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        corsi.addActionListener(e -> {
            frame.remove(panel);
            panel = new BaseView();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        macchinari.addActionListener(e -> {
            frame.remove(panel);
            panel = new BaseView();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        trainer.addActionListener(e -> {
            frame.remove(panel);
            panel = new BaseView();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        schede.addActionListener(e -> {
            frame.remove(panel);
            panel = new BaseView();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }
}
