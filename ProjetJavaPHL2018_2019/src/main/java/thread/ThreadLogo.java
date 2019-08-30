package main.java.thread;

public class ThreadLogo extends Thread {
    private CanvasLogo canvas;

    public void run() {
        while (true) {
            try {
                Thread.sleep(12);
                canvas.repaint();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCanvas(CanvasLogo canvas) {
        this.canvas = canvas;
    }
}
