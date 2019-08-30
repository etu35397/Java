package main.java.view.panel.tacheMetier;

import main.java.controller.PizzeriaController;
import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.factory.DateFactory;
import main.java.model.ComponentPizza;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class PrixMoyenPanel extends JPanel {

    private MainWindow mainWindow;
    private JButton button;
    private JComboBox<PizzeriaCityJoin> pizzeriaComboBox;
    private JComboBox<PizzeriaAdressJoin> ruePizzeriaComboBox;
    private ArrayList<PizzeriaCityJoin> listPizzeria;
    private ArrayList<PizzeriaAdressJoin> listRuePizzeria;
    private JLabel dateDebutLabel, dateFinLabel, choixLocPizzeriaLabel, choixRuePizzeriaLabel;
    private JSpinner dateDebutSpinner, dateFinSpinner;

    private GregorianCalendar dateDebut;
    private GregorianCalendar dateFin;
    private PizzeriaAdressJoin pizzeriaChoosen;


    public PrixMoyenPanel(MainWindow mainWindow, ArrayList<PizzeriaCityJoin> listPizzeria) throws PizzeriaException, DataAccessException {

        this.mainWindow = mainWindow;
        this.listPizzeria = listPizzeria;
        this.mainWindow.setSize(600, 500);
        buildWindow();
        this.setVisible(true);
    }

    public void buildWindow() throws PizzeriaException, DataAccessException {
        this.button = new JButton("Afficher le prix moyen");

        this.dateDebutLabel = new JLabel("Date de début : ");
        this.dateFinLabel = new JLabel("Date de fin : ");
        this.choixLocPizzeriaLabel = new JLabel("Choisissez la ville de la pizzeria");
        this.choixRuePizzeriaLabel = new JLabel("Choisissez la rue de la pizzeria");

        this.pizzeriaComboBox = new JComboBox<>();
        this.ruePizzeriaComboBox = new JComboBox();
        this.dateDebutSpinner = new JSpinner(new SpinnerDateModel());
        this.dateFinSpinner = new JSpinner(new SpinnerDateModel());

        this.setLayout(new GridLayout(5, 2));
        this.add(choixLocPizzeriaLabel);
        this.add(new JScrollPane(this.pizzeriaComboBox));
        this.add(choixRuePizzeriaLabel);
        this.add(new JScrollPane(this.ruePizzeriaComboBox));
        this.add(dateDebutLabel);
        this.add(dateDebutSpinner);
        this.add(dateFinLabel);
        this.add(dateFinSpinner);
        this.add(this.button);
        this.button.addActionListener(new PrixMoyenPanel.ButtonRechercheListener());

        for (PizzeriaCityJoin pizzeria : this.listPizzeria) {
            this.pizzeriaComboBox.addItem(pizzeria);
        }

        syncFormRue((PizzeriaCityJoin)pizzeriaComboBox.getSelectedItem());
        ruePizzeriaComboBox.setEnabled(true);
        this.pizzeriaComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    // Reset du formulaire
                    ruePizzeriaComboBox.setEnabled(true);
                    ruePizzeriaComboBox.removeAllItems();

                    // Synchronisation du formulaire avec le customer selectionnée à modifier
                    syncFormRue((PizzeriaCityJoin)pizzeriaComboBox.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Impossible de pré-remplir le champ du nom de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void syncFormRue(PizzeriaCityJoin locality) throws PizzeriaException, DataAccessException {

        PizzeriaController localityController = new PizzeriaController();
        ArrayList<PizzeriaAdressJoin> person = localityController.getAdressPizzeria(locality.getId());
        for (PizzeriaAdressJoin adressPizza: person){
            this.ruePizzeriaComboBox.addItem(adressPizza);
        }
    }

    public void syncFormModel() {

        this.dateDebut = DateFactory.dateToGregorianCalendar((Date) this.dateDebutSpinner.getModel().getValue());
        this.dateFin = DateFactory.dateToGregorianCalendar((Date) this.dateFinSpinner.getModel().getValue());

        this.dateDebut.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        this.dateFin.setTimeZone(TimeZone.getTimeZone("GMT+2"));

        this.pizzeriaChoosen = (PizzeriaAdressJoin) this.ruePizzeriaComboBox.getSelectedItem();
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

                    GregorianCalendar dateDebut = DateFactory.dateToGregorianCalendar((Date)dateDebutSpinner.getValue());
                    GregorianCalendar dateFin = DateFactory.dateToGregorianCalendar((Date)dateFinSpinner.getValue());

                    PizzeriaController pizzeriaController = new PizzeriaController();
                    Double prixMoyen = pizzeriaController.getAveragePrice(pizzeriaChoosen.getId(), dateDebut, dateFin);

                    System.out.println(pizzeriaChoosen.getId());

                    JOptionPane.showMessageDialog(null, "Prix moyen : " + String.format("%.2f", prixMoyen) + "€", "Prix moyen d'un pizzeria", JOptionPane.INFORMATION_MESSAGE);

                }
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }
}
