package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import enums.*;
import input.Input;

public class GamePanel extends JPanel implements Runnable {
    final int WIDTH = 600;
    final int HEIGHT = 800;
    final int FPS = 60;

    Thread gameThread;

    StateType gameState = StateType.PLAY;
    Input input = new Input(this);

    //------------------------------------------------------//
    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.setFocusable(true);
    }

    public void launch() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
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
        switch (gameState) {
            case PLAY:
                System.out.println("updating the play state");
                break;
            case NONE:
                System.err.println("State not find!");
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}
