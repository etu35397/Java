package main.java.view.panel.tableModel;

import main.java.model.join.PizzaJoin;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPizzaSauceModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<PizzaJoin> contents;

    public AllPizzaSauceModel(ArrayList<PizzaJoin> pizza) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Id");
        this.columnNames.add("Nom de la pizza");
        this.columnNames.add("Pate");
        this.columnNames.add("Sauce");
        this.columnNames.add("Prix total");
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
            case 3:
                return pizza.getSauceName();
            case 4:
                return pizza.getPrice();
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
            case 3:
                c = String.class;
                break;
            case 4:
                c = Double.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
