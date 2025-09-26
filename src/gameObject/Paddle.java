package gameObject;
import java.awt.*;
import java.awt.image.BufferedImage;

import extra.Vector2D;
public class Paddle extends GameObject {
    private Color color;
    public Paddle(Vector2D position, int width, int height, Color color) {
        super(position, width, height);
        this.color = Color.WHITE;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {
        //TODO: Implement update
    }
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawRect((int)position.x, (int)position.y, this.getWidth(), this.getHeight());
    }
    @Override
    public BufferedImage getImage() {
        //TODO: Implement getImage
        return null;
    }
    @Override
    public void setImage(BufferedImage image) {
        //TODO: Implement setImage
    }
    @Override
    public void move(Vector2D move) {
        this.position.add(move);
    }
    @Override
    public void moveTo(Vector2D newPosition) {
        this.position = newPosition;
    }
}
