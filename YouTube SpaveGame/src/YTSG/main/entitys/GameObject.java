package YTSG.main.entitys;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import YTSG.main.YTSG;

public class GameObject {

    public double x, y;
    protected YTSG game;
    protected Random r = new Random();

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle setBounds(int width, int height) {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public static boolean collision(Friendly obj, LinkedList<Hostile> host) {
        for (int i = 0; i < host.size(); i++) {
            if (obj.getBounds().intersects(host.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public static int getCollison(Friendly obj, LinkedList<Hostile> host) {
        int index = 0;
        for (int i = 0; i < host.size(); i++) {
            if (!obj.getBounds().intersects(host.get(i).getBounds())) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }
}
