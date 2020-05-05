/*
 * The MIT License
 *
 * Copyright 2020 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tjc.jfx.turtlesapp.impl;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.turtlesapp.Turtle;

/**
 *
 * @author tjclancy
 */
public class TurtleImpl implements Turtle {
    private static final Logger log = LoggerFactory.getLogger(TurtleImpl.class);
    public static final Paint DEFAULT_CANVAS_FILL = Color.ALICEBLUE;
    public static final Paint DEFAULT_CANVAS_STROKE = Color.BLACK;

    private double x;
    private double y;
    private double angle;
    private final GraphicsContext gc;
    private final TurtleShapes shapes;
//    private final Polygon turtleShape;
    private final Circle turtleShape;
    private Paint turtleFill;
    private Paint turtleStroke;
    private Bounds canvasBounds;
    private Canvas canvas;
    private boolean turtleHidden = false;

    public TurtleImpl(final GraphicsContext gc) {
        this(gc, 0.0, 0.0, 0.0);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TurtleImpl(final GraphicsContext gc, final double x, final double y, final double angle) {
        log.debug("### entered TurtleImpl(gc, x, y, angle): gc: {}, x: {}, y:{}, angle: {}",
                gc, x, y, angle);
        this.turtleFill = DEFAULT_TURTLE_FILL;
        this.turtleStroke = DEFAULT_TURTLE_STROKE;
        this.gc = gc;
        this.canvas = gc.getCanvas();
        this.canvasBounds = gc.getCanvas().getBoundsInParent();
        this.x = x;
        this.y = y;
        this.angle = angle;
        shapes = new TurtleShapes();
        turtleShape = new Circle(20);
//        turtleShape = shapes.createDefaultRotateableTurtleShape(turtleFill);
//        turtleShape.getPoints().forEach(point -> log.debug("###     point: {}", point));
        turtleShape.setCenterX(canvasBounds.getCenterX());
        turtleShape.setCenterY(canvasBounds.getCenterY());
        this.x = canvasBounds.getCenterX();
        this.y = canvasBounds.getCenterY();
        turtleShape.relocate(x, y);
//        home();
        log.debug("###");
//        turtleShape.getPoints().forEach(point -> log.debug("###     point: {}", point));
        log.debug("###     turtleShape: {}", turtleShape);
        log.debug("### exited TurtleImpl(turtleCanvas, x, y, angle)");
    }

    public static TurtleImpl newInstance(final GraphicsContext gc) {
        return new TurtleImpl(gc);
    }

    public static TurtleImpl newInstance(final GraphicsContext gc, final double x, final double y,
            final double angle) {
        return new TurtleImpl(gc, x, y, angle);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void turnLeft(double delta) {
        log.debug("### entered turnLeft(double): delta: {}", delta);
        angle += delta;
        draw();
        this.debugPrintInfo();
        log.debug("### exited turnLeft(double)");
    }

    @Override
    public void turnRight(double delta) {
        log.debug("### entered turnRight(double): delta: {}", delta);
        angle -= delta;
        draw();
        this.debugPrintInfo();
        log.debug("### exited turnRight(double)");
    }

    @Override
    public void reset() {
        log.debug("### entered reset()");
        x = 0.0;
        y = 0.0;
        angle = 0.0;
        draw();
        debugPrintInfo();
        this.debugPrintInfo();
        log.debug("### exited reset()");
    }

    @Override
    public void home() {
        log.debug("### entered home()");
        x = gc.getCanvas().getWidth() / 2;
        log.debug("###     x: {}", x);
        y = gc.getCanvas().getHeight() / 2;
        log.debug("###     y: {}", y);
        angle = 90.0;
        log.debug("###     angle: {}", angle);
        draw();
        debugPrintInfo();
        log.debug("### exited home()");

    }

    @Override
    public void forward(double step) {
        log.debug("### entered forward(double): step: {}", step);
        x += step * Math.cos(Math.toRadians(angle));
        y += step * Math.sin(Math.toRadians(angle));
        log.debug("###     x: {}", x);
        log.debug("###     y: {}", y);
        draw();
        debugPrintInfo();
        log.debug("### exited forward()");
    }

    @Override
    public void face(double angle) {
        log.debug("### entered face(double): step: {}", angle);
        this.angle = angle;
        turtleShape.setRotate(angle);
        draw();
        debugPrintInfo();
        log.debug("### exited face()");
    }

    @Override
    public double facing() {
        log.debug("### entered facing()");
        debugPrintInfo();
        log.debug("### exited facing()");
        return angle;
    }

    @Override
    public void backward(double step) {
    }

    @Override
    public void show() {
        log.debug("### entered show()");
        turtleHidden = false;
        draw();
        debugPrintInfo();
        log.debug("### exit show()");
    }

    @Override
    public void hide() {
        log.debug("### entered hide()");
        turtleHidden = true;
        debugPrintInfo();
        draw();
        log.debug("### exited hide()");
    }

    @Override
    public void penUp() {
    }

    @Override
    public void penDown() {
    }

    @Override
    public void pause(int t) {
    }

    @Override
    public void setPenColor(Color color) {
    }

    @Override
    public void setPenRadius(double radius) {
    }

    @Override
    public void setCanvasSize(int width, int height) {
    }

    @Override
    public void setXscale(double min, double max) {
    }

    @Override
    public void setYscale(double min, double max) {
    }

    @Override
    public Paint getTurtleFill() {
        return turtleFill;
    }

    @Override
    public void setTurtleFill(Paint turtleFill) {
        this.turtleFill = turtleFill;
    }

    @Override
    public Paint getTurtleStroke() {
        return turtleStroke;
    }

    @Override
    public void setTurtleStroke(Paint turtleStroke) {
        this.turtleStroke = turtleStroke;
    }

    @Override
    public void update() {
        log.debug("### entered update()");
        draw();
        log.debug("### exited update()");
    }

    @Override
    public void goTo(double x, double y) {
        this.x = x;
        this.y = y;
        draw();
    }

    @Override
    public double[] getXPoints() {
        List<Point2D> turtlePoints2D = getTurtlePointsAs2DPoints();
        double[] xPoints = new double[turtlePoints2D.size()];
        for (int i = 0; i < turtlePoints2D.size(); i++) {
            xPoints[i] = turtlePoints2D.get(i).getX();
        }
        return xPoints;
    }

    @Override
    public double[] getYPoints() {
        List<Point2D> turtlePoints2D = getTurtlePointsAs2DPoints();
        double[] yPoints = new double[turtlePoints2D.size()];
        for (int i = 0; i < turtlePoints2D.size(); i++) {
            yPoints[i] = turtlePoints2D.get(i).getY();
        }
        return yPoints;
    }

    private List<Point2D> getTurtlePointsAs2DPoints() {
//        List<Double> allPoints = turtleShape.getPoints();
        List<Point2D> points2D = new ArrayList<>();
//        for (int i = 0; i < allPoints.size(); i += 2) {
//            points2D.add(new Point2D(allPoints.get(i), allPoints.get(i + 1)));
//        }
        return points2D;
    }

    public void debugPrintInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("===============================================");
            log.debug("Printing Turtle Info");
            log.debug("===============================================");
            log.debug("### x     : {}", x);
            log.debug("### y     : {}", y);
            log.debug("### angle : {}", angle);
            log.debug("### radius: {}", turtleShape.getRadius());
            log.debug("### canvasBounds: {}", canvasBounds);
            log.debug("### canvasBounds.centerX: {}", canvasBounds.getCenterX());
            log.debug("### canvasBounds.centerY: {}", canvasBounds.getCenterY());
//            log.debug("### points: {}", Arrays.toString(toDoubleArray(turtleShape.getPoints())));
            log.debug("===============================================");
            log.debug("Done Printing Turtle Info");
            log.debug("===============================================");
            log.debug("###");
        }
    }

    private static double[] toDoubleArray(List<Double> listOfPoints) {
        return listOfPoints.stream().mapToDouble(Number::doubleValue).toArray();
    }

//    public void translateTurtle2() {
//        log.debug("### entered translateTurtle2()");
//        double[] points = toDoubleArray(turtleShape.getPoints());
//        log.debug("###     points: {}", Arrays.toString(points));
//        double centerX = turtleShape.getLayoutBounds().getCenterX();
//        double centerY = turtleShape.getLayoutBounds().getCenterY();
//        log.debug("###     centerX: {}, centerY: {}", centerX, centerY);
//        Rotate rot = new Rotate(angle, centerX, centerY);
//        Translate t = new Translate(100, 300);
//        Scale sc = new Scale(1, 1);
//
//        log.debug("###     points: {}", Arrays.toString(points));
//
//        Arrays.asList(rot, t, sc)
//                .forEach((transform) -> {
//                    transform.transform2DPoints(points, 0, points, 0, 3);
//                });
//
//        turtleShape.getPoints().clear();
//        log.debug("###     turtleShape.points: {}", turtleShape.getPoints());
//        for (int i = 0; i < points.length; i++) {
//            turtleShape.getPoints().add(points[i]);
//        }
//        log.debug("###     turtleShape.points: {}", turtleShape.getPoints());
//        log.debug("###     points: {}", Arrays.toString(points));
//        log.debug("### exited translateTurtle2()");
//    }
//    public void translateTurtle() {
//        log.debug("### entered translateTurtle()");
//        double[] points = turtleShape.getPoints().stream().mapToDouble(Number::doubleValue).toArray();
//        log.debug("###     points: {}", Arrays.toString(points));
//        turtleShape.getLocalToParentTransform().transform2DPoints(points, 0, points, 0, 3);
//        log.debug("###     points: {}", Arrays.toString(points));
//        turtleShape.getPoints().clear();
//        for (int i = 0; i < points.length; i++) {
//            turtleShape.getPoints().add(points[i]);
//        }
//        log.debug("###     turtleShape.points: {}", turtleShape.getPoints());
//        log.debug("### exited translateTurtle()");
//    }
    private void draw() {
        //clearCanvas();
        if (turtleHidden == false) {
            log.debug("### entered draw()");
//        translateTurtle2();
//        double[] xPoints = getXPoints();
//        log.debug("###     xPoints: {}", Arrays.toString(xPoints));
//        double[] yPoints = getYPoints();
//        log.debug("###     yPoints: {}", Arrays.toString(yPoints));
            gc.setFill(turtleFill);
//        gc.fillPolygon(xPoints, yPoints, 3);
            gc.fillOval(x, y, 20, 20);
            gc.setStroke(turtleStroke);
            gc.strokeOval(x, y, 20, 20);
//        gc.strokePolygon(xPoints, yPoints, 3);
            log.debug("### exited draw()");
        }
    }

    private void clearCanvas() {
        gc.setFill(DEFAULT_CANVAS_FILL);
        gc.setStroke(DEFAULT_CANVAS_STROKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }
}
