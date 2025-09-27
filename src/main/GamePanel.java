package main;

import enums.StateType;
import main.General.StateManager;
import main.State.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    final int FPS = 60;

    private Thread gameThread;
    private StateManager stateManager;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        stateManager = new StateManager();
        stateManager.switchState(StateType.MENU);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (stateManager.currentState != null) {
                    stateManager.currentState.handleEvent(e);
                }
            }
        });
    }

    public void launch() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (stateManager.currentState != null) {
            stateManager.currentState.update();
        } else {
            System.err.println("No current state set!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (stateManager.currentState != null) {
            stateManager.currentState.render(g2d);
        }

        g2d.dispose();
    }
}
