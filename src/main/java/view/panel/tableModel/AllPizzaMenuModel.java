package main.java.view.panel.tableModel;

import main.java.model.join.PizzaJoin;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPizzaMenuModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<PizzaJoin> contents;

    public AllPizzaMenuModel(ArrayList<PizzaJoin> pizza) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Id");
        this.columnNames.add("Nom de la pizza");
        this.columnNames.add("Pate");
        this.contents = pizza;
    }

    public ArrayList<PizzaJoin> getContents() {
        return contents;
    }

    public int getColumnCount() {
        return this.columnNames.size();
    }

    public int getRowCount() {
        return this.contents.size();
    }

    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        PizzaJoin pizza = contents.get(row);

        switch (column) {
            case 0:
                return pizza.getId();
            case 1:
                return pizza.getName();
            case 2:
                return pizza.getDoughName();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0:
                c = Integer.class;
                break;
            case 1:
                c = String.class;
                break;
            case 2:
                c = String.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
