package physics;

import extra.Vector2D;
import gameObject.Ball;
import gameObject.Brick;
import gameObject.GameObject;
import gameObject.Paddle;

import java.util.List;

public class CollisionManager {

    public static boolean checkBallPaddleCollision(Ball ball, Paddle paddle) {
        double bx = ball.getPosition().x;
        double by = ball.getPosition().y;
        double bw = ball.getWidth();
        double bh = ball.getHeight();

        double px = paddle.getPosition().x;
        double py = paddle.getPosition().y;
        double pw = paddle.getWidth();
        double ph = paddle.getHeight();

        return bx < px + pw &&
               bx + bw > px &&
               by < py + ph &&
               by + bh > py;
    }

    public static Brick checkBallBricksCollision(Ball ball, List<Brick> bricks) {
        for (Brick brick : bricks) {
            if (brick.checkCollision(((GameObject) ball).getCenter(), ball.getWidth() / 2.0)) {
                return brick;
            }
        }
        return null;
    }

    public static void checkBallWallCollision(Ball ball, int panelWidth, int panelHeight) {
        Vector2D pos = ball.getPosition();
        Vector2D vel = ball.getVelocity();
        int bw = ball.getWidth();
        int bh = ball.getHeight();

        if (pos.x <= 0 || pos.x + bw >= panelWidth) {
            ball.reverseX();
        }
        if (pos.y <= 0) {
            ball.reverseY();
        }
        if (pos.y + bh >= panelHeight) {
            ball.setAlive(false);
        }
    }
}
