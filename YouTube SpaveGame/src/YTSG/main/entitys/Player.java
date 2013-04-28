package YTSG.main.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

import YTSG.main.InputHandler;
import YTSG.main.Sprites;
import YTSG.main.YTSG;
import YTSG.main.Item.BigShoota;
import YTSG.main.Item.Item;
import YTSG.main.Item.TempSheild;

public class Player extends GameObject implements Friendly {

    private double x, y;
    private InputHandler input;
    private YTSG game;
    public static Player player;

    private double speed;
    private int weaponCooldown = 0;
    private boolean canFire = true;
    public static int score = 0;
    public static int health = 3;
    public static boolean dead = false;
    public static int killed = 0;
    public static int des = 0;

    public static Item activeItem;
    public static int sheild = 0;

    public Player(YTSG game, InputHandler input, double x, double y) {
        super(x, y);
        player = this;
        this.x = x;
        this.y = y;
        this.input = input;
        this.game = game;
        speed = Calc.getVelocity(1, 2.5);
        input.enter.setNumTimesPressed(0);
    }

    public static void restart() {
        health = 3;
        sheild = 0;
    }

    public void tick() {
        if (weaponCooldown > 0) {
            weaponCooldown--;
        }
        if (input.up.isPressed()) {
            y -= speed;
        }
        if (input.down.isPressed()) {
            y += speed;
        }
        if (input.left.isPressed()) {
            x -= speed;
        }
        if (input.right.isPressed()) {
            x += speed;
        }
        if (GameObject.collision(this, game.hos)) {
            if (sheild > 0) {
                sheild--;
            } else {
                if (activeItem instanceof TempSheild) {
                    activeItem = null;
                }
                if (health >= 1) {
                    health--;
                    YTSG.c.hostile(game.hos.get(GameObject.getCollison(this, game.hos)), false);
                    killed++;
                    if (health <= 0) {
                        dead = true;
                    }
                }
            }
        }
        if (input.space.isPressed() && weaponCooldown == 0 && canFire) {
            YTSG.c.friendly(new Missile(x, y - 26, (activeItem instanceof BigShoota ? 1 : 0), YTSG.ytsg), true);
            canFire = false;
            weaponCooldown += activeItem instanceof BigShoota ? 0 : 30;
        }
        if (!input.space.isPressed())
            canFire = true;
        if (x <= 0)
            x = 0;
        if (y <= 0)
            y = 0;
        if (x >= 640 - 22)
            x = 640 - 22;
        if (y >= 480 - 34)
            y = 480 - 34;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void render(Graphics g) {
        g.drawImage(Sprites.sheet[0][0], (int) x, (int) y, null);
    }
}
