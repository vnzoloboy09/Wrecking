package gameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import extra.Vector2D;
import input.Input;

public class Paddle extends GameObject {
    private Input input;
    private float speed = 5.0f;
    private int panelWidth;

    public Paddle(Vector2D position, int width, int height, String path, Input input, int panelWidth) {
        super(position, width, height, false);
        this.input = input;
        this.panelWidth = panelWidth;
        loadImage(path);
    }

    @Override
    public void update() {
        if (input.isKeyPressed(KeyEvent.VK_A) || input.isKeyPressed(KeyEvent.VK_LEFT)) {
            position.x -= speed;
        }

        if (input.isKeyPressed(KeyEvent.VK_D) || input.isKeyPressed(KeyEvent.VK_RIGHT)) {
            position.x += speed;
        }

        if (position.x < 0) {
            position.x = 0;
        }
        if (position.x > panelWidth - width) {
            position.x = panelWidth - width;
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        try {
            g2d.drawImage(getImage(),
                    (int)position.x,
                    (int)position.y,
                    width,
                    height,
                    null);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}