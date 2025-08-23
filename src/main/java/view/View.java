package view;

import view.section.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.BridgeCV;

import java.awt.BorderLayout;
import java.awt.Toolkit;

public class View {

    private final JFrame frame;
    private JPanel panel;
    private final JToolBar menu;
    private final JButton persona;
    private final JButton corsi;
    private final JButton macchinari;
    private final JButton trainer;
    private final JButton scheda;
    private final JButton segnalazioni;
    private final JPanel personaPanel;
    private final JPanel corsiPanel;
    private final JPanel macchinariPanel;
    private final JPanel trainerPanel;
    private final JPanel schedaPanel;
    private final JPanel segnalazioniPanel;

    public View(final BridgeCV bridgeCV) {
        frame = new JFrame("Gestionale Palestra");
        panel = new JPanel();
        menu = new JToolBar();
        persona = new JButton("Persona");
        corsi = new JButton("Corsi");
        macchinari = new JButton("Macchinari");
        trainer = new JButton("Trainer");
        scheda = new JButton("Schede");
        segnalazioni = new JButton("Segnalazioni");

        personaPanel = new Persona(bridgeCV);
        corsiPanel = new Corsi(bridgeCV);
        macchinariPanel = new Macchinari(bridgeCV);
        trainerPanel = new Trainer(bridgeCV);
        schedaPanel = new Scheda(bridgeCV);
        segnalazioniPanel = new Segnalazioni(bridgeCV);

        menu.setFloatable(false);
        menu.add(persona);
        menu.add(corsi);
        menu.add(macchinari);
        menu.add(trainer);
        menu.add(scheda);
        menu.add(segnalazioni);

        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.PAGE_START);
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sizer();
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

        segnalazioni.addActionListener(e -> {
            frame.remove(panel);
            panel = segnalazioniPanel;
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void sizer(){

        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
        this.frame.setSize(width, height);
    }
}
