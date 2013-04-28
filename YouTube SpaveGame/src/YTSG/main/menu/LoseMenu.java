package YTSG.main.menu;

import java.awt.Graphics;

import YTSG.main.Text;
import YTSG.main.entitys.Player;

public class LoseMenu extends Menu {

    public void tick() {
        if (input.space.isPressed()) {
            setMenu(new TitleMenu());
        }
    }

    public void render(Graphics g) {
        Text.render2("GAME OVER", g, 10, 100);
        Text.render2("Your final score : " + Player.score, g, 10, 116);

        Text.render2("Press [Space] to go to", g, 10, 200);
        Text.render2("Back to the main menu", g, 10, 218);

        Text.render("Stats : ", g, 10, 250);
        Text.render("Total asteroids destroyed : " + Player.des, g, 10, 256);
        Text.render("Total times fired : " + input.space.getNumTimesPressed(), g, 10, 262);
    }
}
