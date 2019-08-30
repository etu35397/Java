package main.java.view.panel.customerPanel;

import main.java.controller.PersonController;
import main.java.model.Person;
import main.java.view.panel.tableModel.AllPersonModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchCustomerPanel extends JPanel {

    private ArrayList<Person> personneList;
    private JComboBox<Person> elementCustomerJComboBox;
    private MainWindow mainWindow;

    public SearchCustomerPanel(MainWindow mainWindow, ArrayList<Person> personneList) {

        this.mainWindow = mainWindow;
        this.personneList = personneList;

        this.mainWindow.setSize(500, 400);

        JLabel elementCustomerLabel = new JLabel("Choisir une personne :", JLabel.RIGHT);
        this.elementCustomerJComboBox = new JComboBox();
        this.elementCustomerJComboBox.setEditable(true);
        JButton elementAcceptButton = new JButton("Accepter");
        elementAcceptButton.addActionListener(new ButtonAccepter());

        this.setLayout(new GridLayout(2,2,0,50 ));
        this.add(elementCustomerLabel);
        this.add(elementCustomerJComboBox);
        this.add(elementAcceptButton);

        if (this.personneList != null) {
            for (Person personne : this.personneList) {
                this.elementCustomerJComboBox.addItem(personne);
            }
        }

    }

    private class ButtonAccepter implements ActionListener {
        public void actionPerformed(ActionEvent event){
            try {

                Person person = (Person) elementCustomerJComboBox.getSelectedItem();

                PersonController customerController = new PersonController();
                AllPersonModel allPersonModel = new AllPersonModel(customerController.getOneShowPersonReduLocality(person.getId()));
                mainWindow.setContainer(new ListingCustomerPanel(allPersonModel));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}