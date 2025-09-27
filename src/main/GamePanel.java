package main;

import javax.swing.*;
import java.awt.*;

import enums.*;
import input.Input;
import gameObject.Paddle;
import extra.Vector2D;

public class GamePanel extends JPanel implements Runnable {
    final int WIDTH = 600;
    final int HEIGHT = 800;
    final int FPS = 60;

    Thread gameThread;

    StateType gameState = StateType.PLAY;
    Input input = new Input(this);

    // Paddle object
    Paddle paddle;

    //------------------------------------------------------//
    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.setFocusable(true);

        int paddleWidth = 100;
        int paddleHeight = 20;
        Vector2D paddlePosition = new Vector2D(WIDTH/2 - paddleWidth/2, HEIGHT - paddleHeight - 50);

        paddle = new Paddle(paddlePosition, paddleWidth, paddleHeight, "C:\\Users\\ad\\IdeaProjects\\Wrecking\\src\\gameObject\\erdplus.png", input, WIDTH);
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
                // Update paddle
                paddle.update();
                break;
            case NONE:
                System.err.println("State not found!");
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        switch (gameState) {
            case PLAY:
                paddle.render(g2d);
                break;
        }
    }
}