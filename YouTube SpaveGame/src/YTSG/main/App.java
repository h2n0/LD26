package YTSG.main;

import java.awt.BorderLayout;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class App extends JApplet {

    private static YTSG ytsg = new YTSG();

    public void init() {
        setLayout(new BorderLayout());
        add(ytsg, BorderLayout.CENTER);
        setMaximumSize(YTSG.dim);
        setPreferredSize(YTSG.dim);
        setMinimumSize(YTSG.dim);
    }

    public void start() {
        init();
        ytsg.start();
    }

    public void stop() {
        System.exit(0);
        ytsg.stop();
    }

    public static void main(String[] args) {
    }
}
