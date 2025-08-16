package view.section;

import controller.BridgeCV;
import controller.Controller.QueryName;
import controller.Controller.QueryParameters;
import view.BaseView;

import javax.swing.*;
import java.awt.*;

public class Corsi extends BaseView {

    private final JButton lessonsByCourseEditionButton;
    private final JButton exercisesByMuscleGroupButton;
    private final JButton mostLessonByTrainerInMonthButton;

    public Corsi(final BridgeCV bridgeCV) {
        super(bridgeCV);

        this.northWestPanel.setLayout(new GridLayout(3, 0));

        // Creazione bottoni
        this.lessonsByCourseEditionButton = new JButton("Lezioni per edizione corso");
        this.exercisesByMuscleGroupButton = new JButton("Esercizi per gruppo muscolare");
        this.mostLessonByTrainerInMonthButton = new JButton("Trainer con più lezioni nel mese");

        // Colore bianco per tutti i bottoni
        this.lessonsByCourseEditionButton.setBackground(Color.WHITE);
        this.exercisesByMuscleGroupButton.setBackground(Color.WHITE);
        this.mostLessonByTrainerInMonthButton.setBackground(Color.WHITE);

        // Aggiunta bottoni al pannello
        northWestPanel.add(this.lessonsByCourseEditionButton);
        northWestPanel.add(this.exercisesByMuscleGroupButton);
        northWestPanel.add(this.mostLessonByTrainerInMonthButton);

        // ActionListener per ogni bottone

        // LESSONS BY COURSE EDITION (Nome corso, Data inizio)
        this.lessonsByCourseEditionButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.LESSONSBYCOURSEEDITION);

            this.southCenterPanel.add(new JLabel("Nome corso"));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOMECORSO.toString()));

            this.southCenterPanel.add(new JLabel("Data inizio (YYYY-MM-DD)"));
            this.southCenterPanel.add(parameters.get(QueryParameters.DATAINIZIO.toString()));

            repaintPanel();
        });

        // EXERCISES BY MUSCLE GROUP (Nome gruppo muscolare)
        this.exercisesByMuscleGroupButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.EXERCISESBYMUSCLEGROUP);

            this.southCenterPanel.add(new JLabel("Nome gruppo muscolare"));
            this.southCenterPanel.add(parameters.get(QueryParameters.NOMEGRUPPOMUSCOLARE.toString()));

            repaintPanel();
        });

        // MOST LESSON BY TRAINER IN MONTH (Nessun parametro richiesto)
        this.mostLessonByTrainerInMonthButton.addActionListener(e -> {
            this.southCenterPanel.removeAll();
            this.bridgeCV.setQueryName(QueryName.MOSTLESSONBYTRAINERINMONTH);

            repaintPanel();
        });
    }
}
