package main.java.view.panel.tableModel;

import main.java.model.join.NameAndDateJoin;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AllNameAndDateModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<NameAndDateJoin> contents;

    public AllNameAndDateModel(ArrayList<NameAndDateJoin> customers) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Nom");
        this.columnNames.add("Prenom");
        this.columnNames.add("Date de commande");
        this.contents = customers;
    }

    public ArrayList<NameAndDateJoin> getContents() {
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
        NameAndDateJoin person = contents.get(row);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));

        switch (column) {
            case 0:
                return person.getNom();
            case 1:
                return person.getPrénom();
            case 2:
                return sdf.format(person.getDateCommande().getTime());
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0:
                c = String.class;
                break;
            case 1:
                c = String.class;
                break;
            case 2:
                c = GregorianCalendar.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
