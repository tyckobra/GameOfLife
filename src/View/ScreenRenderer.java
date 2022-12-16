
package View;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class ScreenRenderer extends Canvas {
    private int WIDTH;
    private int HEIGTH;
    private int scale;

    private BufferedImage image;
    private Screen screen;

    public ScreenRenderer(int width, int height, int scale) {
        // Screen data
        this.WIDTH = width;
        this.HEIGTH = height;
        this.scale = scale;
        image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
        screen = new Screen(((DataBufferInt) image.getRaster().getDataBuffer()).getData(),image.getWidth(),
                image.getHeight());
        setPreferredSize(new Dimension(WIDTH*scale, HEIGTH*scale));
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH*scale, HEIGTH*scale, null);
        g.dispose();
        bs.show();
    }

    public Screen getScreen() {
        return screen;
    }

    public void draw(Shape[] shapes) {
        screen.clear();
        for (Shape s : shapes) {
            screen.draw((Point) s, 0xFFFFFF);
        }
    }

    public void drawSprites(ArrayList<Sprite> sprites) {
        screen.clear();
        for (Sprite s : sprites) {
            screen.draw(s);
        }
    }
}