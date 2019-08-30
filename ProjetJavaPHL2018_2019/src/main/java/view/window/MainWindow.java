package main.java.view.window;
import main.java.view.MenuWindow;
import main.java.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private Container container;

    public MainWindow(){
        super("LIBIO_PIZZA");
        container = getContentPane();
        MenuWindow menu = new MenuWindow(this);
        this.setContainer(new HomePanel(this));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setLocation(100,50);
        this.setSize(1600, 800);
        setVisible(true);
    }

    public void setContainer(JPanel panel) {
        if (this.container != null)
            this.container.removeAll();
        this.container.add(panel);
        this.validate();
    }
}
