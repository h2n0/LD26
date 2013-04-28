package YTSG.main.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

import YTSG.main.Sprites;
import YTSG.main.YTSG;

public class Enemy extends GameObject implements Hostile {

    public double x, y, speed;
    public int ticks = 0;
    public int image;
    public static int health;
    private int type;

    public Enemy(double x, double y, double speed, int type, int health, YTSG game) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        if (type == 1) {
            this.getBounds().setBounds(setBounds(32 * 3, 32 * 3));
        }
        Enemy.health = health;
    }

    public void tick() {
        ticks++;
        if (ticks / 3 % 2 == 1) {
            image = 2;
        } else {
            image = 3;
        }
        speed += 0.005;
        y += Calc.getVelocity(1, speed);
        if (type == 1) {
            if (speed > 2) {
                speed = 2;
            }
        } else {
            if (speed > 1) {
                speed = 1;
            }
        }
        if (y > 600) {
            y = -32 - r.nextInt(100);
            x = r.nextInt(640);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void render(Graphics g) {
        if (type == 1) {
            g.drawImage(Sprites.sheet[image][1], (int) x, (int) y, null);
        } else {
            g.drawImage(Sprites.sheet2[3][2], (int) x, (int) y, null);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
