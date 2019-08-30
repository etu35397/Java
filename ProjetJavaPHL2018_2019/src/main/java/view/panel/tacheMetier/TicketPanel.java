package main.java.view.panel.tacheMetier;

import main.java.controller.MembershipController;
import main.java.controller.PurchaseOrderController;
import main.java.controller.TicketController;
import main.java.exception.DataAccessException;
import main.java.exception.ListingPizzaException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.PurchaseOrderJoin;
import main.java.model.join.TicketJoin;
import main.java.view.panel.HomePanel;
import main.java.view.panel.tableModel.AllPurchaseOrderModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketPanel extends JPanel {

    MainWindow mainWindow;

    private JTable tableau;
    private JButton button;
    private Integer idPurchaseOrder;
    private Boolean estPaye;

    private AllPurchaseOrderModel allPurchaseOrderModel;
    private TicketJoin ticketJoin;


    public TicketPanel(MainWindow mainWindow, AllPurchaseOrderModel allPurchaseOrderModel) {
        this.mainWindow = mainWindow;
        this.allPurchaseOrderModel = allPurchaseOrderModel;
        this.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.buildWindow();
        this.setVisible(true);
    }

    public void buildWindow() {
        this.button = new JButton("Afficher le ticket de caisse");

        this.setLayout(new GridLayout(2, 1));
        this.tableau = new JTable(allPurchaseOrderModel);
        this.tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tableau.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.tableau.getTableHeader().setReorderingAllowed(false);
        this.add(new JScrollPane(this.tableau));
        this.add(this.button);
        this.button.addActionListener(new ButtonSelection());
    }

    public boolean validateForm() {
        boolean isSuccess = true;
        String messageError = "";

        if (this.tableau.getSelectedRowCount() == 0) {
            isSuccess = false;
            messageError += "Une ligne doit être sélectionnée pour pouvoir afficher le ticket.\n";
        }

        if (!isSuccess) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }

    public void syncFormModel() throws ListingPizzaException {

        int selectedrow = tableau.getSelectedRow();
        idPurchaseOrder = (Integer) this.tableau.getValueAt(selectedrow , 0);
        estPaye = (Boolean) tableau.getValueAt(selectedrow, 1);
    }

    public class ButtonSelection implements ActionListener {


            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (validateForm()) {
                        syncFormModel();

                        TicketController ticketController = new TicketController();


                        TicketJoin ticketJoin = ticketController.getTicket(idPurchaseOrder);


                        Object[] options = {"Confirmer le payement du ticket", "Retour"};
                        int dialogResult = JOptionPane.showOptionDialog(null, ticketJoin, "Attention", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

                        if (dialogResult == JOptionPane.YES_OPTION) {

                            int dialogResultPayement = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir confirmer le payement de ce ticket ?", "Attention", JOptionPane.YES_NO_OPTION);

                            if (dialogResultPayement == JOptionPane.YES_OPTION) {
                                if (!estPaye) {
                                    MembershipController membershipController = new MembershipController();
                                    membershipController.updateState(idPurchaseOrder);
                                    JOptionPane.showMessageDialog(null, "Modification de l'état réussie!", "Ticket", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Ce bon de commande a déjà été payé", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                            AllPurchaseOrderModel newAllPurchaseOrderModel =  new AllPurchaseOrderModel(purchaseOrderController.getAllPuschaseOrder());
                            mainWindow.setContainer(new TicketPanel(mainWindow, newAllPurchaseOrderModel));
                        }
                    }
                } catch (ListingPizzaException e1) {
                    e1.printStackTrace();
                } catch (DataAccessException e1) {
                    e1.printStackTrace();
                } catch (PurchaseOrderException e) {
                    e.printStackTrace();
                }
            }
    }
}
