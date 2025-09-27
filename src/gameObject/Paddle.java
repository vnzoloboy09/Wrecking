package gameObject;

import extra.Vector2D;
import java.awt.*;

public class Paddle extends GameObject {

    public Paddle(Vector2D position, int width, int height, String path) {
        super(position, width, height, false);
        loadImage(path);
    }

    @Override
    public void update() {
        //TODO: Implement input
    }

    @Override
    public void render(Graphics2D g2d) {
        try {
            g2d.drawImage(getImage(),
                    (int)position.x,
                    (int)position.y,
                    width,
                    height,
                    null);
        } catch (Exception e) {
            //TODO: Add Exception for null image
            e.printStackTrace();
        }
    }
}