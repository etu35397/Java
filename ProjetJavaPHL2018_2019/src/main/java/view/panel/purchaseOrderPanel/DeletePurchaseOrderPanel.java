package main.java.view.panel.purchaseOrderPanel;

import main.java.controller.PurchaseOrderController;
import main.java.exception.ListingPizzaException;
import main.java.view.panel.tableModel.AllPurchaseOrderModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePurchaseOrderPanel extends JPanel {

    MainWindow mainWindow;

    private JTable tableau;
    private JButton button;

    private AllPurchaseOrderModel allPurchaseOrderModel;
    private Integer idPizza;

    public DeletePurchaseOrderPanel(MainWindow mainWindow, AllPurchaseOrderModel allPurchaseOrderModel) {
        this.mainWindow = mainWindow;
        this.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.allPurchaseOrderModel = allPurchaseOrderModel;
        this.buildWindow();
        this.setVisible(true);
    }

    public void buildWindow() {
        this.button = new JButton("Supprimer le bon de commande");

        this.setLayout(new GridLayout(2, 1));
        this.tableau = new JTable(allPurchaseOrderModel);
        this.tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.add(new JScrollPane(this.tableau));
        this.add(this.button);
        this.button.addActionListener(new ButtonSelection());
    }

    public boolean validateForm() {
        boolean isSuccess = true;
        String messageError = "";

        if (this.tableau.getSelectedRowCount() == 0) {
            isSuccess = false;
            messageError += "Au moins une ligne doit être sélectionnée pour pouvoir supprimer un bon de commande.\n";
        }

        if (!isSuccess) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }


    public void syncFormModel() throws ListingPizzaException {

        int selectedrow = this.tableau.getSelectedRows()[0];
        idPizza = (Integer) this.tableau.getValueAt(selectedrow , 0);
    }

    public class ButtonSelection implements ActionListener {


        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (validateForm()) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir supprimer ce bon de commande ?", "Attention", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        syncFormModel();
                        PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                        purchaseOrderController.delete(idPizza);
                        JOptionPane.showMessageDialog(null, "Suppression réussie!", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);

                        AllPurchaseOrderModel newAllPurchaseOrderModel =  new AllPurchaseOrderModel(purchaseOrderController.getAllPuschaseOrder());
                        mainWindow.setContainer(new DeletePurchaseOrderPanel(mainWindow, newAllPurchaseOrderModel));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
