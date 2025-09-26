package gameObject;
import java.awt.*;
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

    }
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawRect((int)position.x, (int)position.y, this.getWidth(), this.getHeight());
    }
}
