package gameObject;

import extra.Vector2D;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends GameObject {
    private boolean breakable = true;
    private String path;
    private BufferedImage brickImage;

    public Brick(Vector2D position, double width, double height, String path) {
        super(position, (int) width, (int) height, true);
        this.path = path;
        loadImage(path); 
        brickImage = getImage();
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public void hit() {
        breakable = false;
        System.out.println("Brick hit!");
    }

    public GameObject create(Vector2D position, double width, double height) {
        return new Brick(position, width, height, path);
    }

    public void update(double time) {
    }

    @Override
    public void render(Graphics2D g2d) {
        if (!breakable) return;
        try {
            g2d.drawImage(brickImage,
                    (int) position.x,
                    (int) position.y,
                    width,
                    height,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean handleCollision(Ball ball) {
        if (!breakable) return false;

        Vector2D ballPos = ball.getPosition();
        double radius = ball.getRadius();

        if (checkCollision(ballPos, radius)) {
            hit();
            ball.reverseY();

            return true;
        }
        return false;
    }

    public boolean checkCollision(Vector2D ballPos, double ballRadius) {
        if (!breakable) return false;

        double bx = ballPos.x;
        double by = ballPos.y;

        double rx = getPosition().x;
        double ry = getPosition().y;
        double rw = getWidth();
        double rh = getHeight();

        double closestX = clamp(bx, rx, rx + rw);
        double closestY = clamp(by, ry, ry + rh);

        double distanceX = bx - closestX;
        double distanceY = by - closestY;

        double distanceSquared = distanceX * distanceX + distanceY * distanceY;
        return distanceSquared < ballRadius * ballRadius;
    }

    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public void update() {
        // not used
    }
}
