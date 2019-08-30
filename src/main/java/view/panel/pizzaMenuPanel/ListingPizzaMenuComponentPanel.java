package main.java.view.panel.pizzaMenuPanel;

import main.java.view.panel.tableModel.AllPizzaMenuComponentModel;

import javax.swing.*;
import java.awt.*;

public class ListingPizzaMenuComponentPanel extends JPanel {

    private AllPizzaMenuComponentModel componentPizza;

    public ListingPizzaMenuComponentPanel(AllPizzaMenuComponentModel componentPizza) {
        this.componentPizza = componentPizza;
        this.buildWindow();
    }

    public void buildWindow() {
        this.setLayout(new GridLayout(1, 1));
        JTable tableau = new JTable(componentPizza);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableau.getTableHeader().setReorderingAllowed(false);
        this.add(new JScrollPane(tableau));
    }
}
