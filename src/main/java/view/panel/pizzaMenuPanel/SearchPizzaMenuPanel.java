package main.java.view.panel.pizzaMenuPanel;

import main.java.controller.PizzaController;
import main.java.model.ComponentPizza;
import main.java.model.Size;
import main.java.view.panel.tableModel.AllPizzaMenuModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPizzaMenuPanel extends JPanel {

    private MainWindow mainWindow;

    private JLabel elementPriceLabel;
    private JLabel elementComponentLabel;
    private JLabel elementSizeLabel;
    private JLabel elementPriceBetweenLabel;

    private JSpinner elementMinPriceSpinner;
    private JSpinner elementMaxPriceSpinner;
    private JList<ComponentPizza> elementComponentListUnselected;
    private JList<ComponentPizza> elementComponentListSelected;
    private JComboBox<String> elementSizeComboBox;

    private ArrayList<ComponentPizza> componentPizzas;
    private ArrayList<Size> sizes;

    private Integer minPrice;
    private Integer maxPrice;
    private ArrayList<ComponentPizza> componentPizzaList;
    private Size size;


    public SearchPizzaMenuPanel(MainWindow mainWindow, ArrayList<ComponentPizza> componentPizzas, ArrayList<Size> sizes){

        this.mainWindow = mainWindow;
        this.componentPizzaList = new ArrayList<ComponentPizza>();
        this.mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.componentPizzas = componentPizzas;
        this.sizes = sizes;

        this.setLayout(new GridLayout(3,3,20,50 ));
        this.elementMinPriceSpinner = new JSpinner();
        this.elementMaxPriceSpinner = new JSpinner();
        this.elementMaxPriceSpinner.setValue(25);
        this.elementSizeComboBox = new JComboBox<String>();


        Size allSize = new Size(999, "Toutes", null);
        sizes.add(allSize);


        this.elementSizeComboBox.addItem(allSize.getName());
        if (this.sizes != null) {
            for (Size size : this.sizes) {
                this.elementSizeComboBox.addItem(size.getName());
            }
        }

        this.elementSizeComboBox.setEditable(true);


        this.elementPriceLabel = new JLabel("Prix:",JLabel.LEFT);
        this.elementComponentLabel = new JLabel("Composants:",JLabel.LEFT);
        this.elementSizeLabel = new JLabel("Taille:",JLabel.LEFT);
        this.elementPriceBetweenLabel = new JLabel(" <  Prix  <", JLabel.CENTER);
        JButton elementResetButton = new JButton("Reinitialiser");
        JButton elementAcceptButton = new JButton("Accepter");
        this.elementComponentLabel.setFont(new Font("Serif", Font.BOLD, 20));
        this.elementPriceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        this.elementSizeLabel.setFont(new Font("Serif", Font.BOLD, 20));


        DefaultListModel<ComponentPizza> modelUnSelect = new DefaultListModel<ComponentPizza>();
        for (ComponentPizza s:componentPizzas){
            modelUnSelect.addElement(s);
        }
        this.elementComponentListUnselected = new JList<ComponentPizza>(modelUnSelect);

        this.elementComponentListUnselected.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton elementCopyButton = new JButton("Définir le(s) critère(s) de recherche >>>");
        this.elementComponentListUnselected.setToolTipText("Maintenir la touche 'CTRL' et cliquez sur les composants que vous souhaitez pour en sélectionner plusieurs");

        this.elementComponentListSelected = new JList<ComponentPizza>();
        this.elementComponentListSelected.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ButtonAjouteListener listenerAjouter = new ButtonAjouteListener();
        ButtonReinitListener listenerReinit = new ButtonReinitListener();
        ButtonSendListener listenerSend = new ButtonSendListener();

        elementCopyButton.addActionListener(listenerAjouter);
        elementResetButton.addActionListener(listenerReinit);
        elementAcceptButton.addActionListener(listenerSend);


        this.add(elementPriceLabel);
        this.add(elementMinPriceSpinner);
        this.add(elementPriceBetweenLabel);
        this.add(elementMaxPriceSpinner);
        this.add(elementComponentLabel);
        this.add(new JScrollPane(elementComponentListUnselected));
        this.add(elementCopyButton);
        this.add(new JScrollPane(elementComponentListSelected));
        this.add(elementSizeLabel);
        this.add(elementSizeComboBox);
        this.add(elementResetButton);
        this.add(elementAcceptButton);

    }

    public void syncFormModel() {

        this.minPrice = (Integer) elementMinPriceSpinner.getValue();
        this.maxPrice = (Integer) elementMaxPriceSpinner.getValue();

        for (int i = 0; i < elementComponentListSelected.getModel().getSize(); i++) {
            componentPizzaList.add(elementComponentListSelected.getModel().getElementAt(i));
        }


        for (Size sizeOfSizes :sizes) {
            if (sizeOfSizes.getName() == elementSizeComboBox.getSelectedItem()) {
                this.size = sizeOfSizes;
            }
        }
    }

    public boolean validateForm() {
        this.resetFormStyle();

        String messageError = "";

        if ((Integer)elementMinPriceSpinner.getValue() > (Integer) elementMaxPriceSpinner.getValue()) {

            messageError += "Le prix minimal ne peut être supérieur au prix maximal\n";
            this.elementPriceLabel.setForeground(Color.RED);
        }else if ((Integer)elementMinPriceSpinner.getValue() < 0 || (Integer) elementMaxPriceSpinner.getValue() < 0){

            messageError += "Aucun prix ne peut être inférieur à zéro\n";
            this.elementPriceLabel.setForeground(Color.RED);
        }


        if (!messageError.equals("")) {
            JOptionPane.showMessageDialog(null, messageError, "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void resetFormStyle() {
        this.elementPriceLabel.setForeground(Color.BLACK);

    }

    private class ButtonAjouteListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            DefaultListModel<ComponentPizza> modelSelect = new DefaultListModel<ComponentPizza>();
            for (ComponentPizza component : elementComponentListUnselected.getSelectedValuesList()) {
                modelSelect.addElement(component);
            }
            elementComponentListSelected.setModel(modelSelect);

            SearchPizzaMenuPanel.this.repaint();
        }
    }

    private class ButtonSendListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try{
                if (validateForm()) {
                    syncFormModel();

                    PizzaController pizzaController = new PizzaController();
                    AllPizzaMenuModel allPizzaMenuModel = new AllPizzaMenuModel(pizzaController.getPizzaMenuListing(minPrice, maxPrice, componentPizzaList, size));

                    mainWindow.setContainer(new ListingPizzaMenuPanel(mainWindow, allPizzaMenuModel));

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class ButtonReinitListener implements ActionListener {
        public void actionPerformed(ActionEvent event){

            elementMinPriceSpinner.setValue(0);
            elementMaxPriceSpinner.setValue(25);
            elementComponentListUnselected.clearSelection();
            elementComponentListSelected.clearSelection();

            elementComponentListSelected.setListData(new ComponentPizza[0]);

            SearchPizzaMenuPanel.this.repaint();
            elementSizeComboBox.setSelectedIndex(0);
        }
    }
}
