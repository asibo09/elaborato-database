package view.section;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Optional;

import javax.swing.JButton;

import model.factory.api.FactoryQuery;
import model.factory.impl.FactoryQueryImpl;
import model.queryexecutor.api.QueryExecutor;
import view.BaseView;

public class Persona extends BaseView {

    private final JButton querybutton;
    ResultSet res;
    Connection conn;
    FactoryQuery factoryQuery;
    QueryExecutor query;

    public Persona() {
        super();
        querybutton = new JButton("Query1");
        northWestPanel.setLayout(new BorderLayout());
        northWestPanel.add(querybutton);
        try {
            conn = java.sql.DriverManager.getConnection("jdbc:mariadb://localhost:3306/Palestra", "root", "prova");
        } catch (Exception e) {
            e.printStackTrace();
        }
        factoryQuery = new FactoryQueryImpl(conn);
        querybutton.addActionListener(e -> {
            query = factoryQuery.createAddGymMemberQuery("112", "mario", "rossi", "cesena", "rondini", 67, 'm',
                    new Date(2025, 8, 4), new Date(2025, 8, 4), "30");
            Optional<ResultSet> stronzo = query.execute();
            if(stronzo.isPresent()){
                res = stronzo.get();
            }
        });
    }
}
