package main.java.view.panel.customerPanel;

import main.java.view.panel.tableModel.AllPersonModel;

import javax.swing.*;
import java.awt.*;

public class ListingCustomerPanel extends JPanel {

    private AllPersonModel personList;

    public ListingCustomerPanel(AllPersonModel personList) {
        this.personList = personList;
        this.buildWindow();
    }

    public void buildWindow() {
        this.setLayout(new GridLayout(1, 1));
        JTable tableau = new JTable(personList);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableau.getTableHeader().setReorderingAllowed(false);
        this.add(new JScrollPane(tableau));
    }
}
