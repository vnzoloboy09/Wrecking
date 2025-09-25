package gameObject;

import extra.Vector2D;
import java.awt.*;

abstract public class GameObject implements GameEntity {
    private Vector2D position;
    private double width;
    private double height;

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position.copy();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public abstract GameObject create(Vector2D position, double width, double height);
}