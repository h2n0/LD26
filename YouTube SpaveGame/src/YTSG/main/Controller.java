package YTSG.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import YTSG.main.Item.Antenna;
import YTSG.main.entitys.Enemy;
import YTSG.main.entitys.Friendly;
import YTSG.main.entitys.Hostile;
import YTSG.main.entitys.Player;
import YTSG.main.menu.GameMenu;
import YTSG.main.menu.ShopMenu;

public class Controller {

    public LinkedList<Friendly> fri = new LinkedList<Friendly>();
    public LinkedList<Hostile> hos = new LinkedList<Hostile>();
    private Random r = new Random();

    Friendly friend;
    Hostile host;
    YTSG game;

    int spawnCount = 1;

    public Controller(YTSG game) {
        this.game = game;
        friendly(new Player(game, game.input, (YTSG.width * YTSG.scale) / 2, (YTSG.height * YTSG.scale) / 2), true);
    }

    public void tick() {
        if (Player.killed == spawnCount) {
            game.setMenu(new ShopMenu(new GameMenu()));
            if (Player.activeItem instanceof Antenna) {
                spawnCount += 5;
            } else {
                spawnCount += 2;
            }
            Player.killed = 0;
            spawnEnemys(spawnCount);
            if (spawnCount > 25) {
                if (r.nextBoolean()) {
                    hostile(new Enemy(r.nextInt(640 - 22), -32, r.nextInt((int) 3), 2, 11, game), true);
                }
            }
        }
        for (int i = 0; i < fri.size(); i++) {
            friend = fri.get(i);
            friend.tick();
        }

        for (int i = 0; i < hos.size(); i++) {
            host = hos.get(i);
            host.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < fri.size(); i++) {
            friend = fri.get(i);
            friend.render(g);
        }
        for (int i = 0; i < hos.size(); i++) {
            host = hos.get(i);
            host.render(g);
        }
    }

    public void spawnEnemys(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            hostile(new Enemy(r.nextInt(640 - 22), -32, r.nextInt(3), 1, 3, game), true);
        }
    }

    public void friendly(Friendly frid, boolean add) {
        if (add) {
            fri.add(frid);
        } else {
            fri.remove(frid);
        }
    }

    public void hostile(Hostile host, boolean add) {
        if (add) {
            hos.add(host);
        } else {
            hos.remove(host);
        }
    }

    public void removeBoth(Friendly frin, Hostile host) {
        fri.remove(frin);
        hos.remove(host);
    }

    public LinkedList<Hostile> getHostiles() {
        return hos;
    }

    public LinkedList<Friendly> getFriendlys() {
        return fri;
    }
}