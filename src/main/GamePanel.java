package main;

import enums.*;
import extra.Vector2D;
import gameObject.Ball;
import gameObject.Brick;
import gameObject.Paddle;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    final int WIDTH = 600;
    final int HEIGHT = 800;
    final int FPS = 60;

    Thread gameThread;

    StateType gameState = StateType.PLAY;

    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;

    //------------------------------------------------------//
    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        initializeGameObjects();
    }

    private void initializeGameObjects() {
        // paddle
        int paddleWidth = 100;
        int paddleHeight = 20;
        int paddleX = (WIDTH - paddleWidth) / 2;
        int paddleY = HEIGHT - paddleHeight - 50;
        paddle = new Paddle(new Vector2D(paddleX, paddleY), paddleWidth, paddleHeight, "/images/paddle.png");

        // ball
        int ballSize = 20;
        int ballX = paddleX + (paddleWidth - ballSize) / 2;
        int ballY = paddleY - ballSize - 5;
        ball = new Ball(new Vector2D(ballX, ballY), ballSize, ballSize, "/images/ball.png");

        // bricks
        bricks = new ArrayList<>();
        int rows = 5, cols = 10;
        int brickWidth = 50, brickHeight = 20, padding = 5;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = c * (brickWidth + padding) + 25;
                int y = r * (brickHeight + padding) + 50;
                bricks.add(new Brick(new Vector2D(x, y), brickWidth, brickHeight, Color.RED));
            }
        }
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
                if (ball != null) ball.update();
                if (paddle != null) paddle.update();
                // TODO: check collisions ball vs paddle, ball vs bricks
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

        if (paddle != null) {
            paddle.render(g2d);
        }

        if (ball != null) {
            ball.render(g2d);
        }

        if (bricks != null) {
            for (Brick b : bricks) {
                b.render(g2d);
            }
        }
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }
}
