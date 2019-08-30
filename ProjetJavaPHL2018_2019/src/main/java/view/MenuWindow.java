package main.java.view;

import main.java.controller.*;
import main.java.model.ComponentPizza;
import main.java.model.Locality;
import main.java.model.Person;
import main.java.model.Size;
import main.java.model.join.PizzaJoin;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;
import main.java.model.join.PurchaseOrderJoin;
import main.java.view.panel.HomePanel;
import main.java.view.panel.customerPanel.SearchCustomerPanel;
import main.java.view.panel.pizzaMenuPanel.SearchPizzaMenuPanel;
import main.java.view.panel.purchaseOrderPanel.*;
import main.java.view.panel.tableModel.AllPurchaseOrderModel;
import main.java.view.panel.tacheMetier.PrixMoyenPanel;
import main.java.view.panel.tacheMetier.RecherchePizzaPanel;
import main.java.view.panel.tacheMetier.TicketPanel;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuWindow {
    private JMenuBar menuBar;
    private JMenu fileMenu,menuMenu, commandeMenu, clientMenu, tacheMetierMenu;
    private JMenuItem exit, accueil, ajout, suppresion, modification, searchCommande, searchClient, searchMenu, ticket, listing, prixMoyen, recherchePizza;
    private MainWindow main;

    public MenuWindow(MainWindow main){

        this.main = main;

        // ---- Bar de menu ----

        menuBar = new JMenuBar();
        //Vive le médiator pattern !
        main.setJMenuBar(menuBar);

        // ---- menu ----

        fileMenu = new JMenu("Fichier");
        fileMenu.setMnemonic('f');
        menuBar.add(fileMenu);

        commandeMenu = new JMenu("Bon de commande");
        commandeMenu.setMnemonic('b');
        menuBar.add(commandeMenu);

        clientMenu = new JMenu("Client");
        clientMenu.setMnemonic('c');
        menuBar.add(clientMenu);

        menuMenu = new JMenu("Menu");
        menuMenu.setMnemonic('m');
        menuBar.add(menuMenu);

        tacheMetierMenu = new JMenu("Tâche métier");
        tacheMetierMenu.setMnemonic('t');
        menuBar.add(tacheMetierMenu);

        // ---- Sous-menu ----

        accueil = new JMenuItem("Accueil");
        accueil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        accueil.addActionListener(new AccueilListener());
        fileMenu.add(accueil);

        fileMenu.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(new ExitListener());
        fileMenu.add(exit);

        ajout = new JMenuItem("Ajout");
        ajout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        ajout.addActionListener(new AjoutListener());
        commandeMenu.add(ajout);

        suppresion = new JMenuItem("Suppression");
        suppresion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        suppresion.addActionListener(new DeleteListener());
        commandeMenu.add(suppresion);

        modification = new JMenuItem("Modification");
        modification.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        modification.addActionListener(new ModifListener());
        commandeMenu.add(modification);

        searchCommande = new JMenuItem("Recherche");
        searchCommande.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        searchCommande.addActionListener(new SearchCommandListener());
        commandeMenu.add(searchCommande);

        listing = new JMenuItem("Listing");
        listing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        listing.addActionListener(new ListingListener());
        commandeMenu.add(listing);

        searchClient = new JMenuItem("Recherche");
        searchClient.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        searchClient.addActionListener(new SearchClientListener());
        clientMenu.add(searchClient);

        searchMenu = new JMenuItem("Recherche");
        searchMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        searchMenu.addActionListener(new SearchMenuListener());
        menuMenu.add(searchMenu);

        ticket = new JMenuItem("Gestion des tickets de caisse");
        ticket.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        ticket.addActionListener(new ticketMenuListener());
        tacheMetierMenu.add(ticket);

        prixMoyen = new JMenuItem("Prix vente moyen d'une pizzeria");
        prixMoyen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        prixMoyen.addActionListener(new prixMoyenMenuListener());
        tacheMetierMenu.add(prixMoyen);

        recherchePizza = new JMenuItem("Recherche infos client en fonction de la pizza");
        recherchePizza.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        recherchePizza.addActionListener(new recherchePizzaListener());
        tacheMetierMenu.add(recherchePizza);
    }

    // ActionListener de chaque sous-menus

    private class AccueilListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            finally {
                main.setContainer(new HomePanel(main));
            }
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            finally {
                System.exit(0);
            }
        }
    }

    private class AjoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {

                PersonController personController = new PersonController();
                LocalityController localiteController = new LocalityController();
                PizzeriaController pizzeriaController = new PizzeriaController();
                PizzaController pizzaController = new PizzaController();

                ArrayList<Person> person = personController.getAllPerson(false);
                ArrayList<Locality> localities = localiteController.getAllLocality();
                ArrayList<PizzeriaCityJoin> pizzeriaCityJoins = pizzeriaController.getAllTownPizzeria();
                ArrayList<PizzaJoin> pizzas = pizzaController.listingPizzaForOrder();

               main.setContainer(new AddPurchaseOrderPanel(main, person, localities, pizzeriaCityJoins, pizzas));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                AllPurchaseOrderModel allPurchaseOrderModel =  new AllPurchaseOrderModel(purchaseOrderController.getAllPuschaseOrder());

                main.setContainer(new DeletePurchaseOrderPanel(main, allPurchaseOrderModel));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ListingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                AllPurchaseOrderModel allPurchaseOrderModel =  new AllPurchaseOrderModel(purchaseOrderController.getAllPuschaseOrder());

                main.setContainer(new ListingPurchaseOrderPanel(main, allPurchaseOrderModel));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ModifListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PersonController personController = new PersonController();
                LocalityController localiteController = new LocalityController();
                PizzeriaController pizzeriaController = new PizzeriaController();
                PizzaController pizzaController = new PizzaController();
                PurchaseOrderController purchaseOrderController = new PurchaseOrderController();

                ArrayList<Person> person = personController.getAllPerson(false);
                ArrayList<Locality> localities = localiteController.getAllLocality();
                ArrayList<PizzeriaCityJoin> pizzeriaCityJoins = pizzeriaController.getAllTownPizzeria();
                ArrayList<PizzaJoin> pizzas = pizzaController.listingPizzaForOrder();
                ArrayList<PurchaseOrderJoin> purchaseOrderJoins = purchaseOrderController.getAllPuschaseOrder();

                main.setContainer(new ModifyPurchaseOrderPanel(main, person, localities, pizzeriaCityJoins, pizzas, purchaseOrderJoins));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchCommandListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                main.setContainer(new SearchPurchaseOrderPanel(main));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchClientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PersonController personController = new PersonController();
                ArrayList<Person> personList = personController.getAllPerson(true);
                main.setContainer(new SearchCustomerPanel(main, personList));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                ComponentController componentController = new ComponentController();
                ArrayList<ComponentPizza> componentsList = componentController.getComponentNameAndPrice();

                SizeContoller sizeContoller = new SizeContoller();
                ArrayList<Size> sizeList = sizeContoller.getSizeNameAndPrice();


                main.setContainer(new SearchPizzaMenuPanel(main, componentsList, sizeList));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ticketMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PurchaseOrderController purchaseOrderController = new PurchaseOrderController();
                AllPurchaseOrderModel allPurchaseOrderModel =  new AllPurchaseOrderModel(purchaseOrderController.getAllPuschaseOrder());

                main.setContainer(new TicketPanel(main, allPurchaseOrderModel));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class prixMoyenMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PizzeriaController pizzeriaController = new PizzeriaController();
                ArrayList<PizzeriaCityJoin> listPizzeria = pizzeriaController.getAllTownPizzeria();


                main.setContainer(new PrixMoyenPanel(main, listPizzeria));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class recherchePizzaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                PizzaController pizzaController = new PizzaController();
                ArrayList<PizzaJoin> listPizza = pizzaController.listingPizzaForOrder();


                main.setContainer(new RecherchePizzaPanel(main, listPizza));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}


