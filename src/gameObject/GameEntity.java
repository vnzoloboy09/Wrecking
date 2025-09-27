package gameObject;
import java.awt.*;

public interface GameEntity {
    void update(double time);
    void render(Graphics2D g2d);
}
