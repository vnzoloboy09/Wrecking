package gameObject;
import extra.Vector2D;
public interface Movable {
    void move(Vector2D delta);
    void moveTo(Vector2D newPosition);
}