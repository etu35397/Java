package main.java.thread;

import main.java.view.window.MainWindow;

import javax.swing.*;
import java.awt.*;

public class CanvasLogo extends JPanel {
    private Logo logo;

    public CanvasLogo(MainWindow mainWindowView) {
        this.logo = new Logo(mainWindowView);
        ThreadLogo threadLogo = new ThreadLogo();
        threadLogo.setCanvas(this);
        threadLogo.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        logo.dessine(g);
    }
}
