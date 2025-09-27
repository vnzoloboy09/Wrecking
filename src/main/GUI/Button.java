package main.GUI;

import main.General.AssetManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;

public class Button {
    private Rectangle rect;
    private String fileName;
    private Runnable callback;
    private BufferedImage texture;

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

    public void handleEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED && e.getButton() == MouseEvent.BUTTON1) {
            if (rect.contains(e.getPoint())) {
                if (callback != null) {
                    callback.run();
                }
            }
        }
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
