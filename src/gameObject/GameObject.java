package gameObject;

import extra.Vector2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

abstract public class GameObject {
    protected Vector2D position;
    protected int width;
    protected int height;
    protected BufferedImage image;
    protected Boolean isStatic;

    public GameObject(Vector2D position,int width,int height, Boolean isStatic) {
        this.position = position.copy();
        this.width = width;
        this.height = height;
        this.isStatic = isStatic;
    }
    public Vector2D getPosition() {
        return position;
    }
    public void setPosition(Vector2D position) {
        this.position = position.copy();
    }
    //----Width & Height----//
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
    //----Static flag----//
    public boolean isStatic() {
        return isStatic;
    }
    public void setStatic(boolean aStatic) {
        this.isStatic = aStatic;
    }
    //----Texture----//
    public void loadImage(String path) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url == null) {
                System.err.println("Can't find the resource: " + path);
                throw new IllegalArgumentException("Resource isn't exist: " + path);
            }
            image = ImageIO.read(url);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Can't load: " + path, e);
        }
    }
    public BufferedImage getImage() {
        return image;
    }
    //----Movement----//
    public void move(Vector2D delta) {
        if (!isStatic) {
            position.add(delta);
        }
    }
    public void moveTo(Vector2D newPosition) {
        if (!isStatic) {
            this.position = newPosition.copy();
        }
    }
    //----Inherit Methods----//
    public abstract void update();
    public abstract void render(Graphics2D g2d);
}