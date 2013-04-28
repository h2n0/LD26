package YTSG.main.menu;

import java.awt.Graphics;

import YTSG.main.Sprites;
import YTSG.main.Text;
import YTSG.main.Item.Antenna;
import YTSG.main.Item.BigShoota;
import YTSG.main.Item.Item;
import YTSG.main.Item.Plating;
import YTSG.main.Item.Potato;
import YTSG.main.Item.TempSheild;
import YTSG.main.entitys.Player;

public class ShopMenu extends Menu {

    public String[] options;
    public int item = 0;
    public int itemCount = 4;
    private int selectedY = 0;
    private int waitTime = 0;
    private Item i;

    public ShopMenu(Menu parent) {
        this.parent = parent;
        options = new String[] { "BUY", "EXIT" };
    }

    public void tick() {
        if (waitTime > 0) {
            waitTime--;
        }
        if (input.up.isPressed() && waitTime == 0) {
            if (selectedY == 0) {
                selectedY = options.length - 1;
                waitTime += 10;
            } else {
                selectedY--;
                waitTime += 10;
            }
        }
        if (input.down.isPressed() && waitTime == 0) {
            if (selectedY == options.length - 1) {
                selectedY = 0;
                waitTime += 10;
            } else {
                selectedY++;
                waitTime += 10;
            }
        }
        if (input.left.isPressed() && waitTime == 0) {
            if (item == 0) {
                item = itemCount;
                waitTime += 10;
            } else {
                item--;
                waitTime += 10;
            }
        }
        if (input.right.isPressed() && waitTime == 0) {
            if (item == itemCount) {
                item = 0;
                waitTime += 10;
            } else {
                item++;
                waitTime += 10;
            }
        }

        if (input.enter.isPressed()) {
            switch (selectedY) {
            case 0:
                if (Player.score >= i.getPrice()) {
                    if (i instanceof TempSheild)
                        Player.sheild = 5;
                    if (i instanceof Plating)
                        Player.health = 10;
                    Player.activeItem = i;
                    Player.score -= i.getPrice();
                }
                break;
            case 1:
                Player.dead = false;
                setMenu(parent);
                break;
            }
        }
    }

    public void render(Graphics g) {
        int x = 400;
        int y = 100;
        Text.render2("Money : £" + Player.score, g, 100, 160);
        for (int i = 0; i < options.length; i++) {
            String msg = options[i];
            if (i == selectedY) {
                msg = ">" + msg + " <";
                Text.render2(msg, g, 100, 100 + i * 30);

            }
            Text.render2(msg, g, 100, 100 + i * 30);
        }
        switch (item) {
        case 0:
            i = new Antenna();
            g.drawImage(Sprites.sheet2[4][0], x, y, null);
            Text.render2("" + i.getName(), g, 300, 200);
            Text.render2("Price : £" + i.getPrice(), g, 300, 218);
            Text.render("Flys ya right in to the sucker", g, 300, 245);
            break;
        case 1:
            i = new BigShoota();
            g.drawImage(Sprites.sheet2[5][0], x, y, null);
            Text.render2("" + i.getName(), g, 300, 200);
            Text.render2("Price : £" + i.getPrice(), g, 300, 218);
            Text.render("This one is for all you spammers out there", g, 300, 245);
            break;
        case 2:
            i = new Plating();
            g.drawImage(Sprites.sheet2[6][0], x, y, null);
            Text.render2("" + i.getName(), g, 300, 200);
            Text.render2("Price : £" + i.getPrice(), g, 300, 218);
            Text.render("For when the going gets tough", g, 300, 245);
            break;
        case 3:
            i = new Potato();
            g.drawImage(Sprites.sheet2[7][0], x, y, null);
            Text.render2("" + i.getName(), g, 300, 200);
            Text.render2("Price : £" + i.getPrice(), g, 300, 218);
            Text.render("Apparently theres a big thing about these on earth", g, 300, 245);
            Text.render("right now", g, 300, 251);
            break;
        case 4:
            i = new TempSheild();
            g.drawImage(Sprites.sheet2[4][1], x, y, null);
            Text.render2("" + i.getName(), g, 300, 200);
            Text.render2("Price : £" + i.getPrice(), g, 300, 218);
            Text.render("For when your broke and you need a safty net", g, 300, 245);
        }
    }
}
