package YTSG.main.entitys;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Friendly {

    public void tick();

    public Rectangle getBounds();

    public void render(Graphics g);

}
