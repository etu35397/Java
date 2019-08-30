package main.java.view.panel.tableModel;

import main.java.model.ComponentPizza;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPizzaMenuComponentModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<ComponentPizza> contents;

    public AllPizzaMenuComponentModel(ArrayList<ComponentPizza> pizza) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Id");
        this.columnNames.add("Nom de l'ingrédient");
        this.columnNames.add("Id du gout");
        this.columnNames.add("Prix de l'ingrédient");
        this.contents = pizza;
    }

    public ArrayList<ComponentPizza> getContents() {
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
        ComponentPizza pizza = contents.get(row);

        switch (column) {
            case 0:
                return pizza.getId();
            case 1:
                return pizza.getName();
            case 2:
                return pizza.getTaste();
            case 3:
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
                c = Double.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
