package main.java.view.panel.tacheMetier;

import main.java.view.panel.tableModel.AllNameAndDateModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class InfoClientPizzaPanel extends JPanel {

    private AllNameAndDateModel allNameAndDateModel;
    MainWindow mainWindow;

    public InfoClientPizzaPanel(MainWindow mainWindow, AllNameAndDateModel allNameAndDateModel) {

        this.allNameAndDateModel = allNameAndDateModel;
        this.buildWindow();
        this.setVisible(true);

    }

    public void buildWindow() {
        this.setLayout(new GridLayout(1, 1));

        JTable tableau = new JTable(allNameAndDateModel);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableau.getTableHeader().setReorderingAllowed(false);
        this.add(tableau);
        this.add(new JScrollPane(tableau));

    }
}
