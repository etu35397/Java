package main.java.view.panel.tableModel;

import main.java.model.join.ShowPersonReduLocalityJoin;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPersonModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<ShowPersonReduLocalityJoin> contents;

    public AllPersonModel(ArrayList<ShowPersonReduLocalityJoin> customers) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Nom");
        this.columnNames.add("Prenom");
        this.columnNames.add("Est un homme ?");
        this.columnNames.add("GSM");
        this.columnNames.add("Telephone");
        this.columnNames.add("Rue");
        this.columnNames.add("N° de rue");
        this.columnNames.add("Boite");
        this.columnNames.add("Code Postal");
        this.columnNames.add("Ville");
        this.columnNames.add("Points");
        this.columnNames.add("TypePersonne");
        this.contents = customers;
    }

    public ArrayList<ShowPersonReduLocalityJoin> getContents() {
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
        ShowPersonReduLocalityJoin person = contents.get(row);

        switch (column) {
            case 0:
                return person.getNom();
            case 1:
                return person.getPrenom();
            case 2:
                return person.getEstHomme();
            case 3:
                return person.getNumGSM();
            case 4:
                return person.getNumTel();
            case 5:
                return person.getRue();
            case 6 :
                return person.getNumRue();
            case 7 :
                return person.getBoite();
            case 8 :
                return person.getCodePostal();
            case 9:
                return person.getVille();
            case 10:
                return person.getPoint();
            case 11:
                return person.getTypePers();
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
                c = Boolean.class;
                break;
            case 3:
                c = String.class;
                break;
            case 4:
                c = String.class;
                break;
            case 5:
                c = String.class;
                break;
            case 6:
                c = Integer.class;
                break;
            case 7:
                c = String.class;
                break;
            case 8:
                c = Integer.class;
                break;
            case 9:
                c = String.class;
                break;
            case 10:
                c = Integer.class;
                break;
            case 11:
                c = String.class;
            default:
                c = String.class;
        }
        return c;
    }
}
