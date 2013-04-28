package YTSG.main.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

import YTSG.main.Sprites;
import YTSG.main.YTSG;
import YTSG.main.Item.Potato;

public class Missile extends GameObject implements Friendly {

    private double x, y;
    private int ticks = 0;
    private YTSG game;
    private int type;

    public Missile(double x, double y, int type, YTSG game) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.game = game;
        this.type = type;
    }

    public void tick() {
        ticks++;
        if (GameObject.collision(this, game.hos)) {
            if (type == 0) {
                Enemy.health -= 3;
            } else if (type != 0) {
                Enemy.health -= 1;
            }
            if (Enemy.health <= 0) {
                YTSG.c.removeBoth(this, game.hos.remove(GameObject.getCollison(this, game.hos)));
                Player.score += Player.activeItem instanceof Potato ? 10 * 10 : 10;
                Player.killed++;
                Player.des++;
            } else {
                YTSG.c.friendly(this, false);
            }
        }
        if (type == 0) {
            y -= Calc.getVelocity(1, 2.75);
        } else {
            y -= Calc.getVelocity(1, 2.5);
        }

        if (y < -32) {
            YTSG.c.friendly(this, false);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void render(Graphics g) {
        if (type == 0) {
            g.drawImage(Sprites.sheet[ticks / 4 % 2][1], (int) x, (int) y, null);
        } else {
            g.drawImage(Sprites.sheet[0][2], (int) x, (int) y, null);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
