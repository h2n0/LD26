package YTSG.main.menu;

import java.awt.Graphics;

import YTSG.main.Sprites;
import YTSG.main.Text;
import YTSG.main.entitys.Player;

public class GameMenu extends Menu {

    public void tick() {
        YTSG.main.YTSG.c.tick();
        if (Player.dead) {
            Player.restart();
            setMenu(new LoseMenu());
        }
    }

    public void render(Graphics g) {
        YTSG.main.YTSG.c.render(g);
        // Text.renderSquares(Color.red, g, Player.health, 10, 400);
        Text.render("Score : " + Player.score, g, 10, 400);
        Text.render("Currently equiped : " + (Player.activeItem != null ? Player.activeItem.getName() : "nothing"), g, 10, 425);
        Text.renderAmount(Sprites.sheet[1][2], g, Player.health, 10, 450);
        Text.renderAmount(Sprites.sheet[2][2], g, Player.sheild, 10, 450);
    }
}
