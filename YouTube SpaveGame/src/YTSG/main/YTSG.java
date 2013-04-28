package YTSG.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

import YTSG.main.entitys.Friendly;
import YTSG.main.entitys.Hostile;
import YTSG.main.menu.Menu;
import YTSG.main.menu.TitleMenu;

@SuppressWarnings("serial")
public class YTSG extends Canvas implements Runnable {

    public static final int width = 320;
    public static final int height = width / 12 * 9;
    public static final int scale = 2;
    public static final Dimension dim = new Dimension(width * scale, height * scale);
    public final String Title = "Asteroid Blaster";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    public InputHandler input;
    public static Controller c;
    public static YTSG ytsg;
    public Menu menu;

    public LinkedList<Friendly> fri;
    public LinkedList<Hostile> hos;

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this, "YTSG");
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @SuppressWarnings("unused")
    public void run() {
        init();
        long lastTime = System.nanoTime();
        double ns = 1000000000D / 60D;
        double delta = 0;
        int ticks = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        setMenu(new TitleMenu());
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                ticks++;
                delta -= 1;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Frames : " + frames);
                frames = 0;
                ticks = 0;
            }

        }
        stop();
    }

    private void tick() {
        if (hasFocus()) {
            if (menu != null) {
                menu.tick();
            } else {
                c.tick();
            }
        } else {
            input.releaseAll();
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        // /////////////////////////////////
        if (hasFocus()) {
            g.drawImage(Sprites.bg, 0, 0, getWidth(), getHeight(), this);
            if (menu != null) {
                menu.render(g);
            } else {
                c.render(g);
            }
        } else {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            Text.render2("Click to focus", g, 200, 225);
        }
        // ////////////////////////////////
        g.dispose();
        bs.show();

    }

    public void init() {
        input = new InputHandler(this);
        c = new Controller(this);
        ytsg = this;
        c.spawnEnemys(1);
        fri = c.getFriendlys();
        hos = c.getHostiles();
    }

    public static void main(String[] args) {
        YTSG game = new YTSG();
        game.setPreferredSize(YTSG.dim);
        game.setMaximumSize(YTSG.dim);
        game.setMinimumSize(YTSG.dim);

        JFrame frame = new JFrame(game.Title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(Sprites.sheet[2][1]);

        game.start();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        if (menu != null)
            menu.init(this, input);
    }

}
