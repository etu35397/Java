package main.java.thread;

import main.java.view.window.MainWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logo extends Rectangle {

    private int x, y, width, height;
    private BufferedImage logo;
    private int directionX, directionY;
    private final int VITESSE = 2;

    MainWindow mainWindow;

    public Logo(MainWindow mainWindow, int x, int y) {
        this.mainWindow = mainWindow;
        try {
            this.logo = ImageIO.read(new File("src/main/assets/imageThread/logoPizza.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;

        if (this.logo != null) {
            this.width = this.logo.getWidth();
            this.height = this.logo.getHeight();
        }
        this.directionX = VITESSE;
        this.directionY = VITESSE;
    }

    public Logo(MainWindow mainWindow) {
        this(mainWindow, 0, 0);
    }

    public void dessine(Graphics g) {
        if (this.logo == null) return;

        if (this.x <= 0) {
            directionX = VITESSE;
        }
        if (this.y <= 0) {
            directionY = VITESSE;
        }
        if (this.x >= this.mainWindow.getWidth()-this.width) {
            this.x = this.mainWindow.getWidth()-this.width;
            directionX = -VITESSE;
        }
        // Prendre en compte les deux autres éléments qui composent la fenêtre
        // Ici, la fenêtre est composé de 3 ligne de mêmes tailles donc il suffit de diviser en 3 la hauteur.
        // TODO if time : créer une classe "getRowHeight" --> cleancode
        if (this.y >= (this.mainWindow.getHeight() / 3)-this.height) {
            this.y = (this.mainWindow.getHeight() / 3)-this.height;
            directionY = -1;
        }
        this.x += this.directionX;
        this.y +=this.directionY;

        g.drawImage(logo, x, y, null);
    }
}
