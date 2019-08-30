package main.java.view.panel.purchaseOrderPanel;

import main.java.controller.PizzeriaController;
import main.java.controller.PurchaseOrderController;
import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.model.Holder;
import main.java.model.Locality;
import main.java.model.Person;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PizzaJoin;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;
import main.java.view.panel.HomePanel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddPurchaseOrderPanel extends JPanel {

    private JLabel nomReservationLabel;
    private JLabel dateLivraisonLabel;
    private JLabel rueLabel;
    private JLabel numRueLabel;
    private JLabel boiteLabel;
    private JLabel typeLabel;
    private JLabel titulaireLabel;
    //private JLabel pizzaListLabel;
    private JLabel dateReservationLabel;
    private JLabel localtePizzeriaLabel;
    private JLabel ruePizzeriaLabel;
    private JLabel localiteLivraisonLabel;


    private JTextField nomReservationField;
    private JSpinner dateLivraisonSpinner;
    private JTextField rueField;
    private JTextField numRueFiel;
    private JTextField boiteField;
    private JCheckBox typeCheckBox;
    private JComboBox titulaireComboBox;
    private JSpinner dateReservationSpinner;
    private JComboBox localiteLivraisonComboBox;
    private JComboBox localtePizzeriaComboBox;
    private JComboBox ruePizzeriaComboBox;
    private JList<PizzaJoin> pizzaListJListUnSelected;
    private JList<PizzaJoin> pizzaListJListSelected;
    private JSpinner nbPizzaSpinner;

    private JButton valideButton;
    
    private ArrayList<Person> titulaireList;
    private ArrayList<Locality> localityLivraisonList;
    private ArrayList<PizzeriaCityJoin> localityPizzeriaList;
    private ArrayList<PizzaJoin> pizzasList;
    private MainWindow mainWindow;

    private MembershipJoin membershipJoin;

    public AddPurchaseOrderPanel(MainWindow mainWindow, ArrayList<Person> titulaireList, ArrayList<Locality> localityLivraisonList, ArrayList<PizzeriaCityJoin>localityPizzeriaList, ArrayList<PizzaJoin> pizzasList) throws PizzeriaException, DataAccessException {

        this.mainWindow = mainWindow;
        this.titulaireList = titulaireList;
        this.localityLivraisonList = localityLivraisonList;
        this.localityPizzeriaList = localityPizzeriaList;
        this.pizzasList = pizzasList;
        membershipJoin = new MembershipJoin();

        this.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);


        this.typeLabel = new JLabel("Type : ");
        this.nomReservationLabel = new JLabel("Nom de la réservation : *");
        this.dateLivraisonLabel = new JLabel("Date de livraison : *");
        this.rueLabel = new JLabel("Rue de livraison : ");
        this.numRueLabel = new JLabel("Numéro : ");
        this.boiteLabel = new JLabel("Boite : ");
        this.titulaireLabel = new JLabel("Selectionnez la personne qui commande : *");
        this.dateReservationLabel = new JLabel("Date de réservation : ");
        this.localiteLivraisonLabel = new JLabel("Localité de la livraison : ");
        this.localtePizzeriaLabel = new JLabel("Localité de la pizzeria : ");
        this.ruePizzeriaLabel = new JLabel("Rue de la pizzeria : ");

        this.typeCheckBox = new JCheckBox("Invité");
        this.nomReservationField = new JTextField();
        this.dateLivraisonSpinner = new JSpinner(new SpinnerDateModel());
        this.rueField = new JTextField();
        this.numRueFiel = new JTextField(5);
        this.boiteField = new JTextField();
        this.titulaireComboBox = new JComboBox();
        this.dateReservationSpinner = new JSpinner(new SpinnerDateModel());
        this.localiteLivraisonComboBox = new JComboBox();
        this.localtePizzeriaComboBox = new JComboBox();
        this.ruePizzeriaComboBox = new JComboBox();
        this.pizzaListJListUnSelected = new JList<PizzaJoin>();
        this.pizzaListJListSelected = new JList<PizzaJoin>();
        JButton elementCopyButton = new JButton("Ajouter la(les) pizza(s)");
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 99,1);
        nbPizzaSpinner = new JSpinner(model);
        JButton elementResetButton = new JButton("Reinitialiser");

        this.valideButton = new JButton("Créer la commande");

        dateReservationSpinner.setEnabled(false);
        ruePizzeriaComboBox.setEnabled(false);
        pizzaListJListUnSelected.setToolTipText("Maintenir la touche 'CTRL' et cliquez sur les pizzas que vous souhaitez pour en sélectionner plusieurs");
        pizzaListJListSelected.setToolTipText("Maintenir la touche 'CTRL' et cliquez sur les pizzas que vous souhaitez pour en sélectionner plusieurs");

        SimpleDateFormat dateModel = new SimpleDateFormat("dd/MM/yyyy (HH:mm)");
        dateLivraisonSpinner.setEditor(new JSpinner.DateEditor(dateLivraisonSpinner, dateModel.toPattern()));
        dateReservationSpinner.setEditor(new JSpinner.DateEditor(dateReservationSpinner, dateModel.toPattern()));
        this.pizzaListJListUnSelected.setFixedCellHeight(15);

        this.setLayout(new GridLayout(14, 2));
        this.add(typeLabel);
        this.add(typeCheckBox);
        this.add(titulaireLabel);
        this.add(titulaireComboBox);
        this.add(nomReservationLabel);
        this.add(nomReservationField);
        this.add(dateReservationLabel);
        this.add(dateReservationSpinner);
        this.add(dateLivraisonLabel);
        this.add(dateLivraisonSpinner);
        this.add(rueLabel);
        this.add(rueField);
        this.add(numRueLabel);
        this.add(numRueFiel);
        this.add(boiteLabel);
        this.add(boiteField);
        this.add(localiteLivraisonLabel);
        this.add(localiteLivraisonComboBox);
        this.add(localtePizzeriaLabel);
        this.add(localtePizzeriaComboBox);
        this.add(ruePizzeriaLabel);
        this.add(ruePizzeriaComboBox);
        this.add(nbPizzaSpinner);
        this.add(pizzaListJListUnSelected);
        this.add(new JScrollPane(pizzaListJListUnSelected));
        this.add(elementCopyButton);
        this.add(pizzaListJListSelected);
        this.add(new JScrollPane(pizzaListJListSelected));

        this.add(elementResetButton);
        this.add(valideButton);

        nomReservationLabel.setForeground(Color.GRAY);
        nomReservationField.setEnabled(false);

        this.valideButton.addActionListener(new ActionButton());

        final DefaultListModel<PizzaJoin> modelSelect = new DefaultListModel<PizzaJoin>();

        elementCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (PizzaJoin pizza : pizzaListJListUnSelected.getSelectedValuesList()) {
                    int i = 0;
                    while(i < (Integer)nbPizzaSpinner.getValue()){
                        modelSelect.addElement(pizza);
                        i++;
                    }
                }
                pizzaListJListSelected.setModel(modelSelect);

                AddPurchaseOrderPanel.this.repaint();
            }
        });

        elementResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modelSelect.clear();
                AddPurchaseOrderPanel.this.repaint();
            }
        });

        this.typeCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(typeCheckBox.isSelected()) {

                    nomReservationLabel.setForeground(Color.BLACK);
                    nomReservationField.setEnabled(true);
                    titulaireLabel.setForeground(Color.GRAY);
                    titulaireComboBox.setEnabled(false);
                    numRueFiel.setText("");
                    rueField.setText("");
                    boiteField.setText("");

                }else if (!typeCheckBox.isSelected()) {

                    nomReservationLabel.setForeground(Color.GRAY);
                    nomReservationField.setEnabled(false);
                    titulaireLabel.setForeground(Color.BLACK);
                    titulaireComboBox.setEnabled(true);

                    syncFormPerson((Person)titulaireComboBox.getSelectedItem());
                }
            }
        });

        // Remplissage des JComboBox + JList
        
        if (this.titulaireList != null) {
            for(Person titulaire : this.titulaireList) {
                this.titulaireComboBox.addItem(titulaire);
            }
        }

        if (this.localityLivraisonList != null) {
            for(Locality locality : this.localityLivraisonList) {
                this.localiteLivraisonComboBox.addItem(locality);
            }
        }

        if (this.localityPizzeriaList != null) {
            for(PizzeriaCityJoin locPizzeria : this.localityPizzeriaList) {
                this.localtePizzeriaComboBox.addItem(locPizzeria);
            }
        }

        // Gestion des remplissage automatique

            // Remplissage du nom de réservation

        syncFormPerson((Person)titulaireComboBox.getSelectedItem());
        this.titulaireComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    // Reset du formulaire
                    nomReservationField.setText("");

                    // Synchronisation du formulaire avec le customer selectionnée à modifier
                    syncFormPerson((Person)titulaireComboBox.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Impossible de pré-remplir le champ du nom de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

            // Remplissage de la comboBox du choix de la rue

        syncFormRue((PizzeriaCityJoin)localtePizzeriaComboBox.getSelectedItem());
        ruePizzeriaComboBox.setEnabled(true);
        this.localtePizzeriaComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    // Reset du formulaire
                    ruePizzeriaComboBox.setEnabled(true);
                    ruePizzeriaComboBox.removeAllItems();

                    // Synchronisation du formulaire avec le customer selectionnée à modifier
                    syncFormRue((PizzeriaCityJoin)localtePizzeriaComboBox.getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Impossible de pré-remplir le champ du nom de la réservation.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

            // Remplissage de la JList des pizzas

        DefaultListModel<PizzaJoin> modelPizza = new DefaultListModel<PizzaJoin>();
        for (PizzaJoin pizzaJoin:this.pizzasList){
            modelPizza.addElement(pizzaJoin);
        }
        this.pizzaListJListUnSelected.setModel(modelPizza);

        // *--------------------------------------* FIN DU CONSTRUCTEUR *--------------------------------------*

    }

    private void syncFormPerson(Person person) {
        Holder holder = (Holder)person;

        this.nomReservationField.setText(holder.getNomPrenom());
        this.rueField.setText(holder.getRue());
        this.numRueFiel.setText(holder.getNumRue().toString());
        this.boiteField.setText(holder.getBoite());

        this.localiteLivraisonComboBox.setSelectedIndex(holder.getLocalite());
    }

    private void syncFormRue(PizzeriaCityJoin locality) throws PizzeriaException, DataAccessException {

        PizzeriaController localityController = new PizzeriaController();
        ArrayList<PizzeriaAdressJoin> person = localityController.getAdressPizzeria(locality.getId());
        for (PizzeriaAdressJoin adressPizza: person){
            this.ruePizzeriaComboBox.addItem(adressPizza);
        }
    }


    public boolean validateForm() {
        this.resetFormStyle();

        String messageError = "";
        if(typeCheckBox.isSelected()) {

            if (this.nomReservationField.getText().equals("") || this.nomReservationField.getText().length() > 255) {
                messageError += "- Le nom de réservation ne peux pas rester vide ou faire plus de 255 caratères.\n";
                this.nomReservationLabel.setForeground(Color.RED);
            }

        }else{

            if (this.titulaireComboBox.getSelectedItem() == null) {
                messageError += "- Un titulaire doit être sélectionné.\n";
                this.titulaireLabel.setForeground(Color.RED);
            }
        }

        GregorianCalendar dateNaiss = new GregorianCalendar();
        dateNaiss.setTime((Date) this.dateLivraisonSpinner.getValue());
        if (dateNaiss.compareTo(new GregorianCalendar()) <= 0) {
            messageError += "- La date de livraison ne doit pas être antérieure à aujourd'hui.\n";
            this.dateLivraisonLabel.setForeground(Color.RED);
        }

        if(!this.numRueFiel.getText().equals("") && this.rueField.getText().equals(""))
        {
            messageError += "- Si la rue est laissée vide, le numéro ne doit pas être indiqué (rue et numéro du titulaire automatique). Sinon remplir les deux.\n";
            this.numRueLabel.setForeground(Color.RED);

        }else if(this.numRueFiel.getText().equals("") && !this.rueField.getText().equals("")) {
            messageError += "- Si le numéro est laissée vide, la rue ne doit pas être indiqué (rue et numéro du titulaire automatique). Sinon remplir les deux.\n";
            this.rueLabel.setForeground(Color.RED);
        }
        if(!this.numRueFiel.getText().equals("") && !numRueFiel.getText().matches("[1-9]([0-9]?+)[a-zA-Z]?"))
        {
            messageError += "- Le numéro doit suivre l'expression régulière suivante : [1-9]([0-9]?+)[a-zA-Z]? (Un ou plusieurs chiffres suivit d'une lettre éventuelle)\n";
            this.numRueLabel.setForeground(Color.RED);
        }

        if(!this.boiteField.getText().equals("") && !boiteField.getText().matches("[a-zA-Z0-9]{1,4}"))
        {
            messageError += "- La boîte ne doit pas dépasser 4 caractères et ne doit contenir que des caractères alphanumériques.\n";
            this.boiteLabel.setForeground(Color.RED);
        }

        if(this.pizzaListJListSelected.getModel().getSize() == 0)
        {
            messageError += "- Vous devez choisir au moins une pizza\n";
            this.pizzaListJListUnSelected.setForeground(Color.RED);
        }

        if (!messageError.equals("")) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void resetFormStyle() {
        this.nomReservationLabel.setForeground(Color.BLACK);
        this.dateLivraisonLabel.setForeground(Color.BLACK);
        this.rueLabel.setForeground(Color.BLACK);
        this.numRueLabel.setForeground(Color.BLACK);
        this.pizzaListJListUnSelected.setForeground(Color.BLACK);

        if(typeCheckBox.isSelected()) {

            nomReservationLabel.setForeground(Color.BLACK);
            nomReservationField.setEnabled(true);
            titulaireLabel.setForeground(Color.GRAY);
            titulaireComboBox.setEnabled(false);

        }else if (!typeCheckBox.isSelected()) {

            nomReservationLabel.setForeground(Color.GRAY);
            nomReservationField.setEnabled(false);
            titulaireLabel.setForeground(Color.BLACK);
            titulaireComboBox.setEnabled(true);
        }

    }

    public void syncFormModel() throws Exception {

        membershipJoin.setNomReservation(this.nomReservationField.getText());
        membershipJoin.setDateCommande(new GregorianCalendar());

        GregorianCalendar dateLivrai = new GregorianCalendar();
        dateLivrai.setTime((Date) this.dateLivraisonSpinner.getValue());

        membershipJoin.setDateLivraison(dateLivrai);
        membershipJoin.setRue(this.rueField.getText());
        membershipJoin.setNumRue(this.numRueFiel.getText());
        if (! this.boiteField.getText().equals(""))
        {
            membershipJoin.setBoite(this.boiteField.getText());
        }




        ArrayList<PizzaJoin> pizzaJoins = new ArrayList<PizzaJoin>();
        for (int i = 0; i < pizzaListJListSelected.getModel().getSize(); i++) {
            pizzaJoins.add(pizzaListJListSelected.getModel().getElementAt(i));
        }

        membershipJoin.setPizzaList(pizzaJoins);

        Locality locality = (Locality) this.localiteLivraisonComboBox.getSelectedItem();
        membershipJoin.setLocalite(locality.getId());
        PizzeriaAdressJoin adressPizzeria = (PizzeriaAdressJoin)this.ruePizzeriaComboBox.getSelectedItem();
        membershipJoin.setPizzeria(adressPizzeria.getId());
        membershipJoin.setRuePizzeria(adressPizzeria.getRoad());
        membershipJoin.setBoitePizzeria(adressPizzeria.getBoite());
        membershipJoin.setNumRuePizzeria(adressPizzeria.getNumRoad());
        PizzeriaCityJoin cityJoin = (PizzeriaCityJoin)this.localtePizzeriaComboBox.getSelectedItem();
        membershipJoin.setCityPizzeria(cityJoin);
        membershipJoin.setEstPaye(false);

        if(this.typeCheckBox.isSelected()) {

            membershipJoin.setPersonne(null);
        }else{
            Person person = (Person)titulaireComboBox.getSelectedItem();
            membershipJoin.setPersonne(person.getId());
        }
    }

    public class ActionButton implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (validateForm()) {
                    syncFormModel();

                    PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                    purchaseOrderController.create(membershipJoin);
                    JOptionPane.showMessageDialog(null, "Création réussie!", "Création d'un bon de commande", JOptionPane.INFORMATION_MESSAGE);
                    mainWindow.setContainer(new HomePanel(mainWindow));

                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'accès à la BD " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

