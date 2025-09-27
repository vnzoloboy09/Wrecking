package gameObject;


import extra.Vector2D;
import java.awt.*;

public class Brick extends GameObject {
    private boolean breakable = true;
    private final Color color;

    public Brick(Vector2D position, double width, double height, Color color) {
        super(position, (int)width, (int)height, true);
        this.color = color;
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
        return new Brick(position, width, height, color);
    }

    public void update(double time) {
        
    }

    @Override
    public void render(Graphics2D g2d) {
        if (!breakable) return;
        g2d.setColor(color);
        g2d.fillRect((int)getPosition().x, (int)getPosition().y, (int)getWidth(), (int)getHeight());
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int)getPosition().x, (int)getPosition().y, (int)getWidth(), (int)getHeight());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
