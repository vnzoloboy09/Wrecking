package gameObject;

import extra.Vector2D;
import java.awt.*;

public class Ball extends GameObject {

    private Vector2D velocity;
    private int panelWidth;
    private int panelHeight;

    public Ball(Vector2D position, int width, int height, String path, int panelWidth, int panelHeight) {
        super(position, width, height, false);
        loadImage(path);
        this.velocity = new Vector2D(0, -10);
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    @Override
    public void update() {
        position = position.plus(velocity);

        if (position.y <= 0) {
            position.y = 0;
            reverseY();
        }

        if (position.y + height >= panelHeight) {
            position = new Vector2D(panelWidth / 2.0 - width / 2.0, panelHeight / 2.0);
            velocity = new Vector2D(0, -3);
            System.out.println("Game Over");
        }

        if (position.x <= 0) {
            position.x = 0;
            reverseX();
        }

        if (position.x + width >= panelWidth) {
            position.x = panelWidth - width;
            reverseX();
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

    public void setAlive(boolean b) {

    }

    public double getRadius() {
        return width / 2.0;
    }
}
