package main.java.view.panel.tacheMetier;

import main.java.controller.MembershipController;
import main.java.controller.PizzeriaController;
import main.java.factory.DateFactory;
import main.java.model.join.NameAndDateJoin;
import main.java.model.join.PizzaJoin;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.view.panel.pizzaMenuPanel.ListingPizzaMenuPanel;
import main.java.view.panel.tableModel.AllNameAndDateModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class RecherchePizzaPanel extends JPanel {

    private MainWindow mainWindow;
    private JButton button;
    private JComboBox<PizzaJoin> pizzaComboBox;
    private ArrayList<PizzaJoin> listPizza;
    private JLabel dateDebutLabel, dateFinLabel, choixPizzaLabel;
    private JSpinner dateDebutSpinner, dateFinSpinner;

    private GregorianCalendar dateDebut;
    private GregorianCalendar dateFin;
    private PizzaJoin pizzaChoosen;


    public RecherchePizzaPanel(MainWindow mainWindow, ArrayList<PizzaJoin> listPizza) {

        this.mainWindow = mainWindow;
        this.mainWindow.setSize(600, 500);
        this.listPizza = listPizza;
        buildWindow();
        this.setVisible(true);
    }

    public void buildWindow() {
        this.button = new JButton("Afficher les clients");

        this.dateDebutLabel = new JLabel("Date de début : ");
        this.dateFinLabel = new JLabel("Date de fin : ");
        this.choixPizzaLabel = new JLabel("Choisissez votre pizza");

        this.pizzaComboBox = new JComboBox<>();
        this.dateDebutSpinner = new JSpinner(new SpinnerDateModel());
        this.dateFinSpinner = new JSpinner(new SpinnerDateModel());

        this.setLayout(new GridLayout(4, 2));
        this.add(choixPizzaLabel);
        this.add(new JScrollPane(this.pizzaComboBox));
        this.add(dateDebutLabel);
        this.add(dateDebutSpinner);
        this.add(dateFinLabel);
        this.add(dateFinSpinner);
        this.add(this.button);
        this.button.addActionListener(new RecherchePizzaPanel.ButtonRechercheListener());

        for (PizzaJoin pizza : this.listPizza) {
            this.pizzaComboBox.addItem(pizza);
        }
    }

    public void syncFormModel() {

        this.dateDebut = DateFactory.dateToGregorianCalendar((Date) this.dateDebutSpinner.getModel().getValue());
        this.dateFin = DateFactory.dateToGregorianCalendar((Date) this.dateFinSpinner.getModel().getValue());

        this.dateDebut.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        this.dateFin.setTimeZone(TimeZone.getTimeZone("GMT+2"));

        this.pizzaChoosen = (PizzaJoin) this.pizzaComboBox.getSelectedItem();
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

                    MembershipController membershipController = new MembershipController();
                    ArrayList<NameAndDateJoin> customers = membershipController.getPersonsFromPizzaId(pizzaChoosen.getId());
                    AllNameAndDateModel allNameAndDateModel = new AllNameAndDateModel(customers);
                    mainWindow.setContainer(new InfoClientPizzaPanel(mainWindow, allNameAndDateModel));


                }
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
    }
}
