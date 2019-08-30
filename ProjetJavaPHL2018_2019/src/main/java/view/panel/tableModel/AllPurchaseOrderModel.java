package main.java.view.panel.tableModel;

import main.java.model.join.PurchaseOrderJoin;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AllPurchaseOrderModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<PurchaseOrderJoin> contents;

    public AllPurchaseOrderModel(ArrayList<PurchaseOrderJoin> purchaseOrders) {
        this.columnNames = new ArrayList<String>();
        this.columnNames.add("Id");
        this.columnNames.add("Est payé");
        this.columnNames.add("Date de la livraison");
        this.columnNames.add("Nom de la réservation");
        this.columnNames.add("Date de la commande");
        this.columnNames.add("Adresse de la livraison");
        this.columnNames.add("Adresse de la pizzeria");
        this.contents = purchaseOrders;
    }

    public ArrayList<PurchaseOrderJoin> getContents() {
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
        PurchaseOrderJoin purchaseOrder = contents.get(row);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));

        switch (column) {
            case 0:
                return purchaseOrder.getId();
            case 1:
                return purchaseOrder.getPaid();
            case 2:
                return sdf.format(purchaseOrder.getDateLivraison().getTime());
            case 3:
                return purchaseOrder.getNomReservation();
            case 4:
                return sdf.format(purchaseOrder.getDateCommande().getTime());
            case 5:
                return purchaseOrder.getAdresseLivraison();
            case 6:
                return purchaseOrder.getAdressePizzeria();

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
                c = Boolean.class;
                break;
            case 2:
                c = String.class;
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
                c = String.class;
                break;
            default:
                c = String.class;
        }
        return c;
    }
}
