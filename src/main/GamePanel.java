package main;

import enums.*;
import extra.Vector2D;
import gameObject.Ball;
import gameObject.Brick;
import gameObject.Paddle;
import input.Input;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    final int WIDTH = 600;
    final int HEIGHT = 800;
    final int FPS = 60;

    Thread gameThread;

    StateType gameState = StateType.PLAY;
    Input input;

    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;

    private int score = 0;

    //------------------------------------------------------//
    GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        input = new Input(this);
        this.addKeyListener(input);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        this.setFocusable(true);
        this.requestFocus();

        initializeGameObjects();
    }

    private void initializeGameObjects() {
        // paddle
        int paddleWidth = 120;
        int paddleHeight = 20;
        int paddleX = (WIDTH - paddleWidth) / 2;
        int paddleY = HEIGHT - paddleHeight - 50;
        paddle = new Paddle(new Vector2D(paddleX, paddleY), paddleWidth, paddleHeight,
                "/images/paddle.jpg", input, WIDTH);

        // ball
        int ballSize = 20;
        int ballX = paddleX + (paddleWidth - ballSize) / 2;
        int ballY = paddleY - ballSize - 5;
        ball = new Ball(new Vector2D(ballX, ballY), ballSize, ballSize, "/images/ball.png", WIDTH, HEIGHT);
        ball.setVelocity(new Vector2D(0, 3));
        // bricks
        bricks = new ArrayList<>();
        int rows = 20, cols = 10;
        int brickWidth = 50, brickHeight = 20, padding = 5;

        Random rand = new Random();
        String[] brickImages = {
                "/images/brick1.jpg",
                "/images/brick2.jpg",
                "/images/brick3.jpg",
                "/images/brick4.jpg"
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = c * (brickWidth + padding) + 25;
                int y = r * (brickHeight + padding) + 50;

                String path = brickImages[rand.nextInt(brickImages.length)];

                bricks.add(new Brick(new Vector2D(x, y), brickWidth, brickHeight, path));
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
        switch (gameState) {
            case PLAY:
                if (ball != null) ball.update();
                if (paddle != null) paddle.update();
                checkCollisions();
                break;
            case NONE:
                System.err.println("State not find!");
                break;
        }
    }

    private void checkCollisions() {
        // Ball vs Paddle
        if (ball != null && paddle != null) {
            Rectangle ballRect = new Rectangle((int) ball.getPosition().x, (int) ball.getPosition().y, ball.getWidth(), ball.getHeight());
            Rectangle paddleRect = new Rectangle((int) paddle.getPosition().x, (int) paddle.getPosition().y, paddle.getWidth(), paddle.getHeight());

            if (ballRect.intersects(paddleRect)) {
                double paddleCenter = paddle.getPosition().x + paddle.getWidth() / 2.0;
                double hitPoint = (ball.getPosition().x + ball.getWidth() / 2.0) - paddleCenter;
                double normalized = hitPoint / (paddle.getWidth() / 2.0);

                double speed = ball.getVelocity().length();
                double angle = normalized * Math.toRadians(60);

                double newVx = speed * Math.sin(angle);
                double newVy = -Math.abs(speed * Math.cos(angle));

                ball.setVelocity(new Vector2D(newVx, newVy));
                ball.getPosition().y = paddle.getPosition().y - ball.getHeight() - 1;
            }
        }

        // Ball vs Bricks
        Iterator<Brick> it = bricks.iterator();
        while (it.hasNext()) {
            Brick brick = it.next();
            if (!brick.isBreakable()) continue;

            if (brick.checkCollision(new Vector2D(ball.getPosition().x + ball.getWidth()/2.0,
                    ball.getPosition().y + ball.getHeight()/2.0), ball.getRadius())) {
                brick.hit();
                it.remove();
                score += 10;

                ball.reverseY();
                break;
            }
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

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 20, 30);
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }
}
