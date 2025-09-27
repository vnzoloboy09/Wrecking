package gameObject;

import extra.Vector2D;
import java.awt.*;

public class Ball extends GameObject {

    private Vector2D velocity;

    public Ball(Vector2D position, int width, int height, String path) {
        super(position, width, height, false);
        loadImage(path);
        this.velocity = new Vector2D(0, -3);
    }

    @Override
    public void update() {
        position = position.plus(velocity);

        if (position.y <= 0) {
            velocity = new Vector2D(velocity.x, Math.abs(velocity.y));
        }
            if (position.y + height >= 600) {
            position = new Vector2D(400, 0);
            velocity = new Vector2D(0, 3);
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void render(Graphics2D g2d) {
        try {
            g2d.drawImage(getImage(),
                    (int) position.x,
                    (int) position.y,
                    width,
                    height,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reverseY() {
        velocity = new Vector2D(velocity.x, -velocity.y);
    }

    public void reverseX() {
        velocity = new Vector2D(-velocity.x, velocity.y);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
