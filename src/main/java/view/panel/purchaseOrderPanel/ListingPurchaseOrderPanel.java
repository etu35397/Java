package main.java.view.panel.purchaseOrderPanel;

import main.java.controller.PurchaseOrderController;
import main.java.controller.TicketController;
import main.java.exception.ListingPizzaException;
import main.java.model.join.PizzaJoin;
import main.java.view.panel.tableModel.AllPurchaseOrderModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ListingPurchaseOrderPanel extends JPanel {

    private AllPurchaseOrderModel allPurchaseOrderModel;
    private MainWindow mainWindow;
    private JTable tableau;
    private Integer idCommande;
    private JButton button;

    private ArrayList<PizzaJoin> pizzaList;

    public ListingPurchaseOrderPanel(MainWindow mainWindow, AllPurchaseOrderModel allPurchaseOrderModel) {

        this.allPurchaseOrderModel = allPurchaseOrderModel;
        this.buildWindow();
        this.setVisible(true);

    }

    public void buildWindow() {

        this.button = new JButton("Afficher la liste des pizzas commandées");

        this.setLayout(new GridLayout(2, 1));

        tableau = new JTable(allPurchaseOrderModel);
        tableau.getTableHeader().setReorderingAllowed(false);
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.add(tableau);
        this.add(new JScrollPane(tableau));
        this.add(this.button);
        this.button.addActionListener(new ButtonSelection());

    }

    public boolean validateForm() {
        boolean isSuccess = true;
        String messageError = "";

        if (this.tableau.getSelectedRowCount() == 0) {
            isSuccess = false;
            messageError += "Au moins une ligne doit être sélectionnée pour pouvoir afficher sa liste de pizzas.\n";
        }

        if (!isSuccess) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }


    public void syncFormModel() throws ListingPizzaException {

        int selectedrow = this.tableau.getSelectedRows()[0];
        idCommande = (Integer) this.tableau.getValueAt(selectedrow , 0);
    }

    public class ButtonSelection implements ActionListener {


        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (validateForm()) {
                    syncFormModel();

                    TicketController ticketController = new TicketController();
                    pizzaList = ticketController.getPizzaFromBD(idCommande);

                    StringBuilder pizzas = new StringBuilder();
                    for (PizzaJoin pizza : pizzaList) {
                        pizzas.append(pizza + "\n");
                    }

                    JOptionPane.showMessageDialog(null, "Liste de pizzas commandées : \n" + pizzas, "Liste de pizza", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
