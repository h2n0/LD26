package YTSG.main.menu;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import YTSG.main.Text;

public class ExpsitionMenu extends Menu {

    List<String> lines = new ArrayList<String>();
    public int ticks = 0;

    public ExpsitionMenu(Menu parent) {
        this.parent = parent;
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(ExpsitionMenu.class.getResourceAsStream("/Exp.txt")));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        ticks++;
        if (ticks / 4 > lines.size() * 6 + 250) {
            setMenu(new TitleMenu());
        }
        if (input.space.isPressed())
            setMenu(new TitleMenu());
    }

    public void render(Graphics g) {
        int yo = ticks / 4;
        for (int y = 0; y <= 240 / 6; y++) {
            int yl = yo / 6 - 240 / 6 + y - 2;
            if (yl >= 0 && yl < lines.size()) {
                Text.render(lines.get(yl), g, (320 - 40 * 6) / 2, y * 6 - yo % 6);
            }
        }
    }
}
