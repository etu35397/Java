package main.java.view.panel.pizzaMenuPanel;

import main.java.controller.PizzaController;
import main.java.exception.ListingPizzaException;
import main.java.model.join.PizzaJoin;
import main.java.view.panel.tableModel.AllPizzaMenuComponentModel;
import main.java.view.panel.tableModel.AllPizzaSauceModel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingPizzaSpecificationPanel extends JPanel {

    MainWindow mainWindow;

    private JTable tableau;
    private JButton button;
    private Integer idPizza;

    private AllPizzaSauceModel allPizzaSauceModel;


    public ListingPizzaSpecificationPanel(MainWindow mainWindow, AllPizzaSauceModel allPizzaSauceModel) {
        this.mainWindow = mainWindow;
        this.allPizzaSauceModel = allPizzaSauceModel;
        this.buildWindow();
        this.setVisible(true);
    }

    public void buildWindow() {
        this.button = new JButton("Afficher les ingrédients et leurs prix");

        this.setLayout(new GridLayout(2, 1));
        this.tableau = new JTable(allPizzaSauceModel);
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
            messageError += "Au moins une ligne doit être sélectionnée pour obtenir des informations sur une pizza.\n";
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
                    syncFormModel();
                    PizzaController pizzaController = new PizzaController();
                    AllPizzaMenuComponentModel pizzaJoin = new AllPizzaMenuComponentModel(pizzaController.searchComponentOfOnePizza(idPizza));

                    mainWindow.setContainer(new ListingPizzaMenuComponentPanel(pizzaJoin));
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
