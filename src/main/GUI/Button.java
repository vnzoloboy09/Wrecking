package main.GUI;

import main.General.AssetManager;
import main.extra.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private String fileName;
    private Runnable callback;
    private BufferedImage texture;
    private boolean isHeld;
    private boolean pressedInside;

    public Rectangle rect;

    public Button(String fileName, Point position, Runnable callback) {
        this.fileName = fileName;
        this.callback = callback;
        this.texture = AssetManager.getInstance().getTexture(fileName);
        int w = texture != null ? texture.getWidth() : 100;
        int h = texture != null ? texture.getHeight() : 50;
        this.rect = new Rectangle(position.x, position.y, w, h);
    }

    public boolean isSelectable() {
        return true;
    }

    public void checkInput(Vector2D mousePos, boolean mousePressed, boolean wasMousePressed) {
        Point mousePoint = new Point((int) mousePos.x, (int) mousePos.y);
        boolean isMouseOver = rect.contains(mousePoint);

        if (mousePressed && !wasMousePressed) { // Mouse just pressed
            pressedInside = isMouseOver;
        } else if (!mousePressed && wasMousePressed && pressedInside && isMouseOver) { // Mouse just released, was pressed inside, and released inside
            if (callback != null) {
                callback.run();
            }
        }
        isHeld = mousePressed && isMouseOver; // Update hold state
    }

    public void render(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, rect.x, rect.y, rect.width, rect.height, null);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            g.setColor(Color.BLACK);
            g.drawString("Load fail", rect.x + 10, rect.y + 30);
        }
    }
}
