package main.java.view.panel.purchaseOrderPanel;

//import main.factory.DateFactory;
import main.java.controller.PurchaseOrderController;
import main.java.factory.DateFactory;
import main.java.view.panel.tableModel.AllPurchaseOrderModel;
import main.java.view.window.MainWindow;

import javax.net.ssl.SSLContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SearchPurchaseOrderPanel extends JPanel {

    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebutSpinner, dateFinSpinner;
    private JRadioButton payeRadio, nonPayeRadio;
    private ButtonGroup buttonGroup;
    private JButton validationButton;

    private MainWindow mainWindow;

    private GregorianCalendar dateDebut;
    private GregorianCalendar dateFin;
    private Boolean estPaye;

    public SearchPurchaseOrderPanel(MainWindow mainWindow) {

        this.mainWindow = mainWindow;

        this.mainWindow.setSize(500, 400);

        this.dateDebutLabel = new JLabel("Date de début : ");
        this.dateFinLabel = new JLabel("Date de fin : ");

        this.dateDebutSpinner = new JSpinner(new SpinnerDateModel());
        this.dateFinSpinner = new JSpinner(new SpinnerDateModel());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy (HH:mm)");
        this. dateDebutSpinner.setEditor(new JSpinner.DateEditor(dateDebutSpinner, dateFormat.toPattern()));
        this.dateFinSpinner.setEditor(new JSpinner.DateEditor(dateFinSpinner, dateFormat.toPattern()));

        // Gestion des boutons radio

        this.payeRadio = new JRadioButton("Payé", true);
        this.nonPayeRadio = new JRadioButton("Non payé", false);

        this.buttonGroup = new ButtonGroup();
        this.buttonGroup.add(payeRadio);
        this.buttonGroup.add(nonPayeRadio);

        // Bouton validé

        this.validationButton = new JButton("Validé");

        // Ajout des composants dans le container

        this.setLayout(new GridLayout(4, 3));
        this.add(dateDebutLabel);
        this.add(dateDebutSpinner);
        this.add(dateFinLabel);
        this.add(dateFinSpinner);
        this.add(payeRadio);
        this.add(nonPayeRadio);
        this.add(validationButton);

        // Ajout de l'actionListener

        validationButton.addActionListener(new ButtonRechercheListener());
    }

    public void syncFormModel() {

        this.dateDebut = DateFactory.dateToGregorianCalendar((Date) this.dateDebutSpinner.getModel().getValue());
        this.dateFin = DateFactory.dateToGregorianCalendar((Date) this.dateFinSpinner.getModel().getValue());
        this.estPaye = payeRadio.isSelected();

        this.dateDebut.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        this.dateFin.setTimeZone(TimeZone.getTimeZone("GMT+2"));
    }

    public boolean validateForm() {
        boolean isSuccess = true;
        String messageError = "";

        if (((Date) dateDebutSpinner.getValue()).compareTo(((Date) dateFinSpinner.getValue())) > 0) {
            isSuccess = false;
            messageError += "La date de début doit se trouver avant la date de fin.\n";
            dateDebutLabel.setForeground(Color.RED);
            dateFinLabel.setForeground(Color.RED);
        }

        if (!isSuccess) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }


    private class ButtonRechercheListener implements ActionListener {


        public void actionPerformed(ActionEvent event){

            try {
                if (validateForm()) {
                    syncFormModel();


                    PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                    AllPurchaseOrderModel allPurchaseOrderModel = new AllPurchaseOrderModel(purchaseOrderController.getPurchaseOrderListing(dateDebut, dateFin, estPaye));

                    //System.out.println(allPurchaseOrderModel.get);

                    mainWindow.setContainer(new ListingPurchaseOrderPanel(mainWindow, allPurchaseOrderModel));

                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }
}
