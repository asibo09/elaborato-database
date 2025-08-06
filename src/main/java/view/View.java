package view;

import view.section.*;

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
    private final JButton scheda;
    private final JPanel personaPanel;
    private final JPanel corsiPanel;
    private final JPanel macchinariPanel;
    private final JPanel trainerPanel;
    private final JPanel schedaPanel;

    public View() {
        frame = new JFrame("My Application");
        panel = new JPanel();
        menu = new JToolBar();
        persona = new JButton("Persona");
        corsi = new JButton("Corsi");
        macchinari = new JButton("Macchinari");
        trainer = new JButton("Trainer");
        scheda = new JButton("Schede");

        personaPanel = new Persona();
        corsiPanel = new Corsi();
        macchinariPanel = new Macchinari();
        trainerPanel = new Trainer();
        schedaPanel = new Scheda();

        menu.setFloatable(false);
        menu.add(persona);
        menu.add(corsi);
        menu.add(macchinari);
        menu.add(trainer);
        menu.add(scheda);

        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        persona.addActionListener(e -> {
            frame.remove(panel);
            panel = personaPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        corsi.addActionListener(e -> {
            frame.remove(panel);
            panel = corsiPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        macchinari.addActionListener(e -> {
            frame.remove(panel);
            panel = macchinariPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        trainer.addActionListener(e -> {
            frame.remove(panel);
            panel = trainerPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });

        scheda.addActionListener(e -> {
            frame.remove(panel);
            panel = schedaPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }
}
