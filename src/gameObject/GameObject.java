package gameObject;

import extra.Vector2D;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class GameObject {
    public Vector2D position;
    private int width;
    private int height;

    public GameObject(Vector2D position,int width,int height) {
        this.position = position.copy();
        this.width = width;
        this.height = height;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position.copy();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract BufferedImage getImage();
    public abstract void setImage(BufferedImage image);
    public abstract void move(Vector2D move);
    public abstract void moveTo(Vector2D newPosition);
}