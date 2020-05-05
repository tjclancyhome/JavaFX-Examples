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
package org.tjc.jfx.jfxturtles.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxturtles.model.exception.TurtleShapesRuntimeException;

/**
 *
 * @author tjclancy
 */
public class TurtleShapes {

    private static final Logger log = LoggerFactory.getLogger(TurtleShapes.class);
    public static final Double[] DEFAULT_TRIANGLE_COORDS;

    static {
        DEFAULT_TRIANGLE_COORDS = new Double[] {
            0.0, 0.0,
            25.0, 10.0,
            10.0, 25.0};
    }

    public ChangeListener anglePropListener = (observable, oldVal, newVal) -> {
        log.debug("### rotationTransform.angleProperty(): oldVal: {}, newVal: {}, observable: {}",
            oldVal, newVal, observable);
    };

    private final BooleanProperty dragModeActiveProperty;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    public TurtleShapes() {
        this.dragModeActiveProperty = new SimpleBooleanProperty(this, "dragModeActive", true);
    }

    public BooleanProperty dragModeActiveProperty() {
        return dragModeActiveProperty;
    }

    public Boolean getDragModeActive() {
        return dragModeActiveProperty.get();
    }

    public void setDragModeActive(boolean isDragModeActive) {
        dragModeActiveProperty.set(isDragModeActive);
    }

    /**
     * Paints a shape given the fill paint and the shape.
     *
     * @param <T>   A Shape class.
     * @param fill  The fill color.
     * @param shape The shape to paint.
     *
     * @return The painted shape.
     */
    public static <T extends Shape> T paint(final Paint fill, final T shape) {
        shape.setFill(fill);
        return shape;
    }

    /**
     * Relocates a shape to given x, y point and returns the updated shape.
     *
     * @param <T>   The shape class.
     * @param shape The shape to relocate.
     * @param x     The new x coordinate.
     * @param y     The new y coordinate.
     *
     * @return A shape relocated to x, y point.
     */
    public static <T extends Shape> T relocate(final T shape, double x, double y) {
        log.debug("### entered relocate(): shape: {}, x: {}, y: {}", shape, x, y);
        shape.relocate(x, y);
        log.debug("### exited relocate(): shape: {}", shape);
        return shape;
    }

    /**
     * Rotate a shape by degrees.
     *
     * @param <T>     The shape class.
     * @param shape   The shape instance to rotate.
     * @param degrees The number of degrees to rotate the shape (0 to 360)
     *
     * @return Returns the rotated shape.
     */
    public static <T extends Shape> T rotate(T shape, double degrees) {
        double radians = Math.toRadians(degrees);
        log.debug("### degrees: {} to radians: {}", degrees, radians);
        shape.setRotate(radians);
        return shape;
    }

    /**
     * Sets the opacity of a shape.
     *
     * @param <T>     The shape class.
     * @param shape   the shape instance to set the opacity.
     * @param opacity The desired opacity of the shape.
     *
     * @return Returns the shape instance with the opacity set.
     */
    public static <T extends Shape> T setOpacity(T shape, double opacity) {
        shape.setOpacity(opacity);
        return shape;
    }

    public Timeline addRotationAnimation(Polygon turtle) {
        log.debug("### entered addRotationAnimation(turtle): turtle: {}", turtle);
        Rotate rotationTransformation = getRotationTransformation(turtle);
        Timeline rotationAnimation = null;
        if (rotationTransformation != null) {
            rotationAnimation = new Timeline();
            rotationAnimation.getKeyFrames()
                .add(
                    new KeyFrame(
                        Duration.seconds(2.5),
                        new KeyValue(
                            rotationTransformation.angleProperty(),
                            360)));
            log.debug("###     rotationTransformation.rotationTrasform: {}", rotationTransformation
                .angleProperty());
            rotationAnimation.setCycleCount(Animation.INDEFINITE);
            debugLogRotationAnimation(rotationAnimation);
        }
        log.debug("### exited addRotationAnimation(turtle)");
        return rotationAnimation;
    }

    public Rotate addRotationTransformation(Polygon polygon) {
        log.debug("### entered addRotationTransform(): polygon: {}", polygon);

        var bounds = polygon.getBoundsInLocal();
        log.debug("###     boundsInLocal: {}", bounds);

        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();
        log.debug("###     centerX: {}, centerY: {}", centerX, centerY);

        var rotationTransform = new Rotate(0, centerX, centerY);
        log.debug("###     rotationTransform: {}", rotationTransform);

        polygon.getTransforms().add(rotationTransform);
        log.debug("###     polygon.getTransforms.size: {}", polygon.getTransforms().size());

        log.debug("### exited addRotationTransform()");
        return rotationTransform;
    }

    public Polygon createDefaulTurtleShape(Paint fill) {
        return createTriangle(DEFAULT_TRIANGLE_COORDS, fill);
    }

    public Polygon createDefaultRotateableTurtleShape(Paint fill) {
        return createRotateableTriangle(DEFAULT_TRIANGLE_COORDS, fill, true);
    }

    /**
     * Returns a Polyline given a set of points and fills it with Paint parameter.
     *
     * @param points
     *
     * @return Returns a Polyline given a set of points.
     */
    public Polyline createPolyLine(Double... points) {
        var polyline = new Polyline();
        polyline.getPoints().addAll(points);
        return polyline;
    }

    public Line createLine(double startX, double startY, double endX, double endY) {
        return new Line(startX, startY, endX, endY);
    }

    public Polyline createPolyLine(Paint fill, Double... points) {
        return paint(fill, createPolyLine(points));
    }

    public static Polygon createPolygon(Double[] coords, Paint fill) {
        var p = new Polygon();
        p.getPoints().addAll(coords);
        return paint(fill, p);
    }

    public Polygon createRotateableTriangle(Double[] coords, Paint fill, boolean rotate) {
        log.debug("### entered createRotateableTriangle(): coords: {}, fill: {}, rotate: {}",
            Arrays.asList(coords), fill, rotate);
        var triangle = createPolygon(coords, fill);
        if (rotate == true) {
            var rotationTrasformation = addRotationTransformation(triangle);
            rotationTrasformation.angleProperty().addListener(anglePropListener);
            log.debug("###     rotationTrasform.rotationTrasform: {}", rotationTrasformation
                .angleProperty());
        }
        log.debug("### exited createRotateableTriangle()");
        return triangle;
    }

    public Node makeDraggable(final Node node) {
        if (getDragModeActive()) {
            var dragContext = new DragContext();
            node.addEventFilter(MouseEvent.ANY, (final MouseEvent mouseEvent) -> {
                mouseEvent.consume();
            });

            node.addEventFilter(MouseEvent.MOUSE_PRESSED, (final MouseEvent mouseEvent) -> {
//                scene.setCursor(Cursor.CLOSED_HAND);
                node.setCursor(Cursor.CLOSED_HAND);

                node.toFront();
                node.setOpacity(0.75);
                log.debug("### node.opacity: {}", node.getOpacity());
                dragContext.mouseAnchorX = mouseEvent.getX();
                dragContext.mouseAnchorY = mouseEvent.getY();
                dragContext.initialTranslateX
                    = node.getTranslateX();
                dragContext.initialTranslateY
                    = node.getTranslateY();
                mouseEvent.consume();
            });

            node.addEventFilter(MouseEvent.MOUSE_DRAGGED, (final MouseEvent mouseEvent) -> {
//                scene.setCursor(Cursor.HAND);
                node.setCursor(Cursor.HAND);
                node.setTranslateX(
                    dragContext.initialTranslateX
                    + mouseEvent.getX()
                    - dragContext.mouseAnchorX);
                node.setTranslateY(
                    dragContext.initialTranslateY
                    + mouseEvent.getY()
                    - dragContext.mouseAnchorY);
                mouseEvent.consume();
            });

            node.addEventFilter(MouseEvent.MOUSE_RELEASED, (final MouseEvent mouseEvent) -> {
                node.setCursor(Cursor.DEFAULT);
                node.setOpacity(1.0);
                log.debug("### node.opacity: {}", node.getOpacity());
                mouseEvent.consume();
            });
        }
        return node;
    }

    public final EventHandler<MouseEvent> nodeOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug("##### entered nodeOnMouseDraggedEventHandler.handle(event): mouse event: {}",
                event);
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if (event.getSource() instanceof Node) {
                var node = Node.class.cast(event.getSource());
                node.setTranslateX(newTranslateX);
                node.setTranslateY(newTranslateY);
            }
            log.debug("##### exited nodeOnMouseDraggedEventHandler.handle(event)");
        }
    };

    public final EventHandler<MouseEvent> nodeOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug("##### entered nodeOnMousePressedEventHandler.handle(event): mouse event: {}",
                event);
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            if (event.getSource() instanceof Node) {
                var node = Node.class.cast(event.getSource());
                node.toFront();
                node.setOpacity(0.75);
                log.debug("### node.opacity: {}", node.getOpacity());
                orgTranslateX = node.getTranslateX();
                orgTranslateY = node.getTranslateY();
            }
            log.debug("##### exited nodeOnMousePressedEventHandler.handle(event)");
        }
    };

    public final EventHandler<MouseEvent> nodeOnMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug(
                "##### entered nodeOnMouseReleasedEventHandler.handle(event): mouse event: {}",
                event);
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if (event.getSource() instanceof Node) {
                var node = Node.class
                    .cast(event.getSource());
                node.setOpacity(1.0);
                node.setTranslateX(newTranslateX);
                node.setTranslateY(newTranslateY);
            }
            log.debug("##### exited nodeOnMouseReleasedEventHandler.handle(event)");
        }
    };

    private Polygon createTriangle(Double[] coords, Paint fill) {
        var triangle = createPolygon(coords, fill);
        return triangle;
    }

    private Rotate getRotationTransformation(Polygon polygon) { //throws TurtleShapesException {
        Objects.requireNonNull(polygon, () -> "The 'polygon' argument is null.");
        log.debug("### entered getRotationTransformation(polygon): {}", polygon);
        Rotate rotationTransformation = null;
        if (polygon.getTransforms().isEmpty()) {
            throw new TurtleShapesRuntimeException("The polygon contains no transformations.");
        } else {
            List<Transform> transforms = polygon.getTransforms()
                .stream()
                .filter(p -> (p instanceof Rotate))
                .collect(Collectors.toList());
            log.debug("###     transforms.size(): {}", transforms.size());
            if (transforms.size() == 1) {
                log.debug("###     transforms: {}", transforms);
                rotationTransformation = Rotate.class
                    .cast(transforms.get(0));
                log.debug("###     rotationTransform: {}", rotationTransformation);
            }
        }
        log.debug("### exited getRotationTransformation(polygon)");
        return rotationTransformation;


    }

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }

    private static void debugLogRotationAnimation(Timeline timeline) {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("===============================================");
            log.debug("Printing Timeline Info");
            log.debug("===============================================");
            log.debug("### timeline.class          : {}", timeline.getClass().getName());
            log.debug("### timeline.currentRate    : {}", timeline.getCurrentRate());
            log.debug("### timeline.currentTime    : {}", timeline.getCurrentTime());
            log.debug("### timeline.cycleCount     : {}", timeline.getCycleCount());
            log.debug("### timeline.cycleDuration  : {}", timeline.getCycleDuration());
            log.debug("### timeline.delay          : {}", timeline.getDelay());
            log.debug("### timeline.keyFrames      : {}", timeline.getKeyFrames());
            log.debug("### timeline.rate           : {}", timeline.getRate());
            log.debug("### timeline.status         : {}", timeline.getStatus());
            log.debug("### timeline.targetFrameRate: {}", timeline.getTargetFramerate());
            log.debug("### timeline.totalDuration  : {}", timeline.getTotalDuration());
            log.debug("===============================================");
            log.debug("Done Printing Timeline Info");
            log.debug("===============================================");
            log.debug("###");
        }

    }

}
