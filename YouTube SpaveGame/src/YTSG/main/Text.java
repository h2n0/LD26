package YTSG.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Text {

    public static String[] chars = { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "!?[]()\"'£<>:;+-=0123456789", "/\\.," };

    public static void render(String msg, Graphics g, int x, int y) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            for (int ys = 0; ys < chars.length; ys++) {
                int xs = chars[ys].indexOf(ch);
                if (xs >= 0) {
                    g.drawImage(Sprites.text[xs][ys], x + i * 6, y, null);
                }
            }
        }
    }

    public static void render2(String msg, Graphics g, int x, int y) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            for (int ys = 0; ys < chars.length; ys++) {
                int xs = chars[ys].indexOf(ch);
                if (xs >= 0) {
                    g.drawImage(Sprites.text2[xs][ys], x + (i * 6 * 3), y, null);
                }
            }
        }
    }

    public static void renderSquares(Color c, Graphics g, int amt, int x, int y) {
        g.setColor(c);
        for (int i = 0; i < amt; i++) {
            g.fillRect(x + i * 16, y, 10, 10);
        }
    }

    public static void renderAmount(BufferedImage src, Graphics g, int amt, int x, int y) {
        for (int i = 0; i < amt; i++) {
            g.drawImage(src, x + i * 32, y, null);
        }
    }

}
