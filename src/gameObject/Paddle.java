package gameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import extra.Vector2D;

public class Paddle extends GameObject implements Movable {
    private Color color;

    public Paddle(Vector2D position, int width, int height, Color color) {
        super(position, width, height);
        this.color = color;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(color);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        this.setImage(img);
    }

    @Override
    public void update() {
        //TODO: Implement input
    }

    @Override
    public void render(Graphics g) {
        if (getImage() != null) {
            g.drawImage(getImage(), (int)getPosition().x, (int)getPosition().y, null);
        } else {
            g.setColor(color);
            g.fillRect((int)getPosition().x, (int)getPosition().y, getWidth(), getHeight());
        }
    }
    @Override
    public void move(Vector2D delta) {
        getPosition().add(delta);
    }

    @Override
    public void moveTo(Vector2D newPosition) {
        setPosition(newPosition);
    }
}
