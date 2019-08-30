package main.java.view.panel;
import main.java.thread.CanvasLogo;
import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private MainWindow mainWindow;

    public HomePanel(MainWindow mainWindow){

        this.mainWindow = mainWindow;

        JLabel line1 = new JLabel("Bienvenue sur le programme de Libio-Pizza!", JLabel.CENTER);
        line1.setFont(new Font("Serif", Font.PLAIN, 36));
        JLabel line2 = new JLabel("Choisissez votre option dans le menu ci-dessus.", JLabel.CENTER);
        line2.setFont(new Font("Serif", Font.PLAIN, 20));
        this.setLayout(new GridLayout(3, 1));
        this.add(line1);
        this.add(line2);
        this.setLocation(100,50);
        this.setSize(1600, 800);

        this.add( new CanvasLogo(this.mainWindow) );
    }
}
