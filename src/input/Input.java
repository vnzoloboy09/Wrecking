package input;

import extra.Vector2D;
import main.GamePanel;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private final boolean[] keys = new boolean[256];
    public Vector2D mousePos = new Vector2D();
    public boolean mousePressed;
    GamePanel gp;

    public Input(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode >= 0 && keycode < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode >= 0 && keycode < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // not used
    }
}
