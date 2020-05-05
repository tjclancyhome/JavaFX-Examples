package org.tjc.jfx.jfxbouncingballs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javafx.application.Application.launch;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.tjc.jfx.jfxcomponents.canvaspane.AnimatedCanvasApp;

/**
 * JavaFX JFXBouncingBallsApp
 */
public class JFXBouncingBallsApp extends AnimatedCanvasApp {
    private static final int TOTAL_BALLS = 20;

    private final List<Ball> balls;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    public JFXBouncingBallsApp() {
        this.balls = new ArrayList<>();
    }

    @Override
    public void setup() {
        setWidth(800);
        setHeight(600);
        gc = getGraphicsContext();
        Random random = new Random();
        for (int i = 0; i < TOTAL_BALLS; i++) {
            Ball ball = new Ball();
            ball.circ = random.nextInt(100) + 10;
            ball.x = random.nextInt(getWidth() - ball.circ);
            ball.y = random.nextInt(getHeight() - ball.circ);
            ball.xDir = random.nextBoolean() ? 1 : -1;
            ball.yDir = random.nextBoolean() ? 1 : -1;
            ball.color = Color.color(Math.random(), Math.random(), Math.random(), 0.5);
            balls.add(ball);
        }
        background(Color.BLANCHEDALMOND);
    }

    @Override
    public void draw() {
        balls.stream()
                .map((ball) -> {
                    ball.update();
                    return ball;
                })
                .forEachOrdered((var ball) -> {
                    ball.draw(gc);
                });
    }

    public class Ball {
        int x, y, xDir = 1, yDir = 1, circ;
        Color color;

        public void update() {
            if (x + circ > getWidth() || x < 0) {
                xDir *= -1;
            }
            if (y + circ > getHeight() || y < 0) {
                yDir *= -1;
            }
            x += 5 * xDir;
            y += 5 * yDir;
        }

        public void draw(GraphicsContext gc) {
            gc.setLineWidth(5);
            gc.setFill(color);
            gc.fillOval(x, y, circ, circ);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x, y, circ, circ);
        }
    }

}
