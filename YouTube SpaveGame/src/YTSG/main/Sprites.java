package YTSG.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprites {

    public static BufferedImage bg = load("/Background.png");
    public static BufferedImage title = load("/Title.png");
    public static BufferedImage[][] sheet = split(load("/Sheet.png"), 32, 32);
    public static BufferedImage[][] sheet2 = split(scale(load("/Sheet.png"), 3), 32 * 3, 32 * 3);
    public static BufferedImage[][] text = split(load("/Text.png"), 6, 6);
    public static BufferedImage[][] text2 = split(scale(load("/Text.png"), 3), 6 * 3, 6 * 3);

    public static BufferedImage load(String path) {
        try {
            BufferedImage org = ImageIO.read(Sprites.class.getResource(path));
            BufferedImage res = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = res.getGraphics();
            g.drawImage(org, 0, 0, null, null);
            g.dispose();
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage[][] split(BufferedImage org, int xs, int ys) {

        int xSlice = org.getWidth() / xs;
        int ySlice = org.getHeight() / ys;
        BufferedImage[][] res = new BufferedImage[xSlice][ySlice];
        for (int x = 0; x < xSlice; x++) {
            for (int y = 0; y < ySlice; y++) {
                res[x][y] = new BufferedImage(xs, ys, BufferedImage.TYPE_INT_ARGB);
                Graphics g = res[x][y].getGraphics();
                g.drawImage(org, -x * xs, -y * ys, null);
                g.dispose();
            }
        }
        return res;
    }

    public static BufferedImage scale(BufferedImage src, int scale) {
        int w = src.getWidth() * scale;
        int h = src.getHeight() * scale;
        BufferedImage res = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = res.getGraphics();
        g.drawImage(src.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
        g.dispose();
        return res;
    }
}
