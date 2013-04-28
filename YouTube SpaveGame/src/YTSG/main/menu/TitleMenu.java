package YTSG.main.menu;

import java.awt.Graphics;

import YTSG.main.Sprites;
import YTSG.main.Text;
import YTSG.main.entitys.Player;

public class TitleMenu extends Menu {

    private int ticks = 0;

    public TitleMenu() {
        ticks = 0;
    }

    public void tick() {
        ticks++;
        if (ticks > 60 * 10) {
            setMenu(new ExpsitionMenu(this));
        }
        if (input.enter.isPressed()) {
            Player.restart();
            Player.dead = false;
            setMenu(new GameMenu());
        }
    }

    public void render(Graphics g) {
        g.drawImage(Sprites.title, 0, -50, null);
        Text.render2("Hit [enter] / [return] to start", g, 55, (int) (320 + (Math.abs(Math.sin(ticks * 0.075) * 10))));
        Text.render("made by Elliot Lee-Cerrino", g, 10, 450);
    }
}
