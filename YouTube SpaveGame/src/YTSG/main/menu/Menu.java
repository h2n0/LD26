package YTSG.main.menu;

import java.awt.Graphics;

import YTSG.main.InputHandler;
import YTSG.main.YTSG;

public class Menu {

    protected InputHandler input;
    protected Menu parent;
    private YTSG ytsg;
    
    public final void init(YTSG ytsg , InputHandler input) {
        this.ytsg = ytsg;
        this.input = input;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    protected void setMenu(Menu menu) {
        ytsg.setMenu(menu);
    }
}
