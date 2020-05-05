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

import java.util.Objects;
import java.util.Optional;
import javafx.animation.Animation.Status;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.utils.LoggerWrapper;
import org.tjc.jfx.jfxturtles.model.exception.TurtleRuntimeException;

/**
 *
 * @author tjclancy
 */
public class Turtle {
    private static final LoggerWrapper log = new LoggerWrapper(LoggerFactory.getLogger(Turtle.class));
    private static final Rotate DEFAULT_ROTATION_TRANSFORMATION = new Rotate(0.0);
    private static final Color DEFAULT_TURTLE_PAINT = Color.BLACK;

    private Polygon turtleShape;
    private Paint fill;
    private Timeline rotationAnimation;
    private Rotate rotationTransformation;
    private final TurtleShapes turtleShapes;
    private boolean isDisposed;
    private double facing;
    private final Object lock = new Object();

    private final ChangeListener anglePropListener;

    Turtle() {
        log.entered("Turtle()");
        this.anglePropListener = (observable, oldVal, newVal) -> {
            log.trace(
                "### rotationTransform.angleProperty(): oldVal: {}, newVal: {}, observable: {}",
                oldVal, newVal, observable);
        };
        this.turtleShapes = new TurtleShapes();
        this.rotationTransformation = DEFAULT_ROTATION_TRANSFORMATION;
        this.fill = DEFAULT_TURTLE_PAINT;
        this.turtleShape = this.turtleShapes.createDefaultRotateableTurtleShape(this.fill);
        this.isDisposed = false;
        this.facing = 0.0;
        log.exited("Turtle()");
    }

    public Turtle(Polygon turtleShape) {
        this();
        log.debug("### entered Turtle(turtleShape): turtleShape: {}", turtleShape);
        this.turtleShape = turtleShape;
        log.exited("Turtle(turtleShape)");
    }

    public Turtle(Double[] polygonCoordinates) {
        this(TurtleShapes.createPolygon(polygonCoordinates, DEFAULT_TURTLE_PAINT));
        log.entered("Turtle(Double[])");
        log.exited("Turtle(Double[])");
    }

    public static Turtle newInstance() {
        return new Turtle();
    }

    public static Turtle newInstance(Polygon turtle) {
        log.entered("newInstance(Polygon)");
        log.exited("newInstance(Polygon)");
        return new Turtle(turtle);
    }

    public static Turtle newInstance(Double[] polygonCoordinates) {
        log.entered("newInstance(Double[])");
        log.exited("newInstance(Double[]");
        return new Turtle(polygonCoordinates);
    }

    public void fill(Paint paint) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        Objects.requireNonNull(paint, () -> "The 'paint' argument is null.");
        synchronized (lock) {
            this.fill = paint;
            turtleShape.setFill(this.fill);
        }
    }

    public double getFacing() {
        return facing;
    }

    public Paint getFill() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        return fill;
    }

    public void setFill(Paint paint) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        Objects.requireNonNull(paint, () -> "The 'paint' argument is null.");
        synchronized (lock) {
            this.fill = paint;
        }
    }

    public Timeline getRotationAnimation() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        return rotationAnimation;
    }

    public void setRotate(double degree) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            turtleShape.setRotate(degree);
        }
    }

    public void face(double angle) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            facing += angle;
            log.entered("face()");
            turtleShape.rotateProperty().set(facing);
            log.exited("face()");
        }
    }

    public void turn(double angle) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            facing += angle;
            turtleShape.rotateProperty().set(facing);
        }
    }

    public void goTo(double x, double y) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            turtleShape.relocate(x, y);
        }
    }

    public void goTo(Point2D point) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        Objects.requireNonNull(point, () -> "The 'point' argument is null.");
        turtleShape.relocate(point.getX(), point.getY());
    }

    public double getRotate() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            return turtleShape.getRotate();
        }
    }

    public Rotate getRotationTransformation() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        return rotationTransformation;
    }

    public void addRotationTransformation(Rotate rotationTransformation) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        Objects.requireNonNull(rotationTransformation,
            () -> "The 'rotationTransformation' argument is null.");
        synchronized (lock) {
            this.rotationTransformation = turtleShapes.addRotationTransformation(turtleShape);
            this.rotationTransformation.angleProperty().addListener(anglePropListener);
        }
    }

    public void addRotationAnimation() { //throws TurtleShapesException {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        log.debug("### turtleShape: {}", turtleShape);

        synchronized (lock) {
            if (turtleShape != null && rotationAnimation == null) {
                rotationAnimation = turtleShapes.addRotationAnimation(turtleShape);
            }
        }
    }

    public void playRotationAnimation() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        if (rotationAnimation != null) {

        }
    }

    public Optional<Status> getRotationAnimationStatus() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        if (rotationAnimation != null) {
            return Optional.ofNullable(rotationAnimation.getStatus());
        }
        return Optional.empty();
    }

    public boolean isAnimationRunning() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        if (rotationAnimation != null) {
            Status status = getRotationAnimationStatus().orElseThrow();
            return status.equals(Status.RUNNING);
        }
        return false;
    }

    public boolean isAnimationStopped() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        if (rotationAnimation != null) {
            Status status = getRotationAnimationStatus().orElseThrow();
            return status.equals(Status.STOPPED);
        }
        return false;
    }

    public boolean isAnimationPaused() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        if (rotationAnimation != null) {
            Status status = getRotationAnimationStatus().orElseThrow();
            return status.equals(Status.PAUSED);
        }
        return false;
    }

    public Polygon getTurtleShape() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        return turtleShape;
    }

    public void rotate(double degrees) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            TurtleShapes.rotate(turtleShape, degrees);
        }
    }

    public void setDirection(double degree) {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            turtleShape.setRotate(degree);
        }
    }

    public void removeRotationTransformation() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            if (turtleShape != null && rotationTransformation != null) {
                rotationTransformation.angleProperty().removeListener(anglePropListener);
                turtleShape.getTransforms().remove(rotationTransformation);
                rotationTransformation = null;
            }
        }
    }

    public void removeAnglePropertyListener() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            if (rotationTransformation != null) {
                rotationTransformation.angleProperty().removeListener(anglePropListener);
            }
        }
    }

    public void addAnglePropertyListener() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        synchronized (lock) {
            if (rotationTransformation != null) {
                rotationTransformation.angleProperty().addListener(anglePropListener);
            }
        }
    }

    public boolean isDisposed() {
        return isDisposed;
    }

    public void disposeTurtle() {
        if (!isDisposed) {
            synchronized (lock) {
                if (rotationTransformation != null) {
                    removeAnglePropertyListener();
                    if (rotationAnimation != null) {
                        rotationAnimation.stop();
                        rotationAnimation = null;
                    }
                }
                removeRotationTransformation();
                turtleShape = null;
                isDisposed = true;
            }
        }
    }

    public final void debugPrintTurtleShapeInfo() {
        log.entered("debugPrintTurtleShapeInfo()");
        if (log.isDebugEnabled() && turtleShape != null) {
            log.debug("###");
            log.debug("### ===============================================");
            log.debug("### Printing TurtleShape Info");
            log.debug("### ===============================================");
            log.debug("###     turtleShape layoutBounds.width : {}", turtleShape.getLayoutBounds()
                .getWidth());
            log.debug("###     turtleShape layoutBounds.height: {}", turtleShape.getLayoutBounds()
                .getHeight());
            log.debug("###     turtleShape layoutBounds.minX  : {}", turtleShape.getLayoutBounds()
                .getMinX());
            log.debug("###     turtleShape layoutBounds.minY  : {}", turtleShape.getLayoutBounds()
                .getMinY());
            log.debug("###     turtleShape layoutBounds.maxX  : {}", turtleShape.getLayoutBounds()
                .getMaxX());
            log.debug("###     turtleShape layoutBounds.maxY  : {}", turtleShape
                .getLayoutBounds()
                .getMaxY());
            log.debug("###");
            log.debug("###     turtleShape localBounds.width  : {}", turtleShape
                .getBoundsInLocal()
                .getWidth());
            log.debug("###     turtleShape localBounds.height : {}", turtleShape
                .getBoundsInLocal()
                .getHeight());
            log.debug("###     turtleShape localBounds.minX   : {}", turtleShape
                .getBoundsInLocal()
                .getMinX());
            log.debug("###     turtleShape localBounds.minY   : {}", turtleShape
                .getBoundsInLocal()
                .getMinY());
            log.debug("###     turtleShape localBounds.maxX   : {}", turtleShape
                .getBoundsInLocal()
                .getMaxX());
            log.debug("###     turtleShape localBounds.maxY   : {}", turtleShape
                .getBoundsInLocal()
                .getMaxY());
            log.debug("###");
            log.debug("###     turtleShape parentBounds.width : {}", turtleShape
                .getBoundsInParent()
                .getWidth());
            log.debug("###     turtleShape parentBounds.height: {}", turtleShape
                .getBoundsInParent()
                .getHeight());
            log.debug("###     turtleShape parentBounds.minX  : {}", turtleShape
                .getBoundsInParent()
                .getMinX());
            log.debug("###     turtleShape parentBounds.minY  : {}", turtleShape
                .getBoundsInParent()
                .getMinY());
            log.debug("###     turtleShape parentBounds.maxX  : {}", turtleShape
                .getBoundsInParent()
                .getMaxX());
            log.debug("###     turtleShape parentBounds.maxY  : {}", turtleShape
                .getBoundsInParent()
                .getMaxY());
            log.debug("###");
            log.debug("###     turtleShape.localToParentTransform:\n{}", turtleShape
                .getLocalToParentTransform());
            log.debug("###     turtleShape.localToSceneTransform :\n{}", turtleShape
                .getLocalToSceneTransform());
            log.debug("###     turtleShape.getTransforms         : {}", turtleShape
                .getTransforms());
            log.debug("### ===============================================");
            log.debug("### Done Printing TurtleShape Info");
            log.debug("### ===============================================");
            log.debug("###");
        }
        log.exited("debugPrintTurtleShapeInfo()");
    }

    public final void debugPrintTurtleInfo() {
        log.entered("debugPrintTurtleInfo()");
        if (log.isDebugEnabled() && turtleShape != null) {
            log.debug("###");
            log.debug("### ===============================================");
            log.debug("### Printing Turtle Info");
            log.debug("### ===============================================");
            log.debug("### fill                  : {}", this.fill);
            log.debug("### facing                : {}", this.facing);
            log.debug("### rotationAnimation     : {}", this.rotationAnimation);
            log.debug("### rotationTransformation: {}", this.rotationTransformation);
            log.debug("### turtleShape           : {}", this.turtleShape);
            log.debug("### animation paused      : {}", this.isAnimationPaused());
            log.debug("### animation running     : {}", this.isAnimationRunning());
            log.debug("### animation stopped     : {}", this.isAnimationStopped());
            log.debug("### ===============================================");
            log.debug("### Done Printing Turtle Info");
            log.debug("### ===============================================");
            log.debug("###");
        }
        log.exited("debugPrintTurtleInfo()");
    }

    private Point2D createPoint2D() {
        if (isDisposed) {
            throw new TurtleRuntimeException("This turtle has been disposed.");
        }
        Point2D point = null;
        if (turtleShape != null) {
            Bounds localBounds = turtleShape.getBoundsInLocal();
            point = new Point2D(localBounds.getCenterX(), localBounds.getCenterY());
        }
        return point;
    }

}
