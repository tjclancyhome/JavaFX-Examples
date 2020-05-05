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
package org.tjc.jfx.turtlesapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.turtlesapp.impl.TurtleImpl;

/**
 *
 * @author tjclancy
 */
public class TurtleCanvasPane extends BorderPane {
    private static final Logger log = LoggerFactory.getLogger(TurtleCanvasPane.class);

    public static final double DEFAULT_WIDTH = 800.0;
    public static final double DEFAULT_HEIGHT = 600.0;
    public static final Paint DEFAULT_CANVAS_FILL = Color.ALICEBLUE;
    public static final Paint DEFAULT_CANVAS_STROKE = Color.BLACK;

    private Canvas canvas;
    private GraphicsContext gc;
    private Paint canvasFill;
    private Paint canvasStroke;
    private double width;
    private double height;
    private TurtleImpl turtle;

    private ObservableList<Turtle> turtles;

    public TurtleCanvasPane() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_CANVAS_FILL, DEFAULT_CANVAS_STROKE);
    }

    public TurtleCanvasPane(double width, double height) {
        this(width, height, DEFAULT_CANVAS_FILL, DEFAULT_CANVAS_STROKE);
    }

    public TurtleCanvasPane(double width, double height, Paint canvasFill, Paint canvasStroke) {
        super();
        this.width = width;
        this.height = height;
        this.canvasFill = canvasFill;
        this.canvasStroke = canvasStroke;
        this.turtles = FXCollections.observableArrayList();
        initialize();
    }

    public void clear() {
        clearCanvas();
    }

    public TurtleImpl getTurtle() {
        return turtle;
    }

    public void setTurtle(TurtleImpl turtle) {
        this.turtle = turtle;
        this.turtle.update();
    }

    public Paint getCanvasFill() {
        return canvasFill;
    }

    public void setCanvasFill(Paint canvasFill) {
        this.canvasFill = canvasFill;
    }

    public Paint getCanvasStroke() {
        return canvasStroke;
    }

    public void setCanvasStroke(Paint canvasStroke) {
        this.canvasStroke = canvasStroke;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void initialize() {
        log.debug("### entered initialize()");

        this.setCache(true);
        this.setCacheHint(CacheHint.SPEED);
        canvas = new Canvas(width, height);
        canvas.setCache(true);
        canvas.setCacheHint(CacheHint.SPEED);
        gc = canvas.getGraphicsContext2D();
        this.setCenter(canvas);
        this.setPrefSize(width, height);
        clearCanvas();
        log.debug("###     boundsInParent: {}", this.getBoundsInParent());
        log.debug("###     boundsInLocal: {}", this.getBoundsInLocal());
        log.debug("### exited initialize()");
    }

    private void clearCanvas() {
        gc.setFill(canvasFill);
        gc.setStroke(canvasStroke);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
