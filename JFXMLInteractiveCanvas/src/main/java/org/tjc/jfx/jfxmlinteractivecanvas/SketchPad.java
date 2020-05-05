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
package org.tjc.jfx.jfxmlinteractivecanvas;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author tjclancy
 */
public class SketchPad {
    private static final double DEFAULT_CANVAS_WIDTH = 1024;
    private static final double DEFAULT_CANVAS_HEIGHT = 768;
    private static final Color DEFAULT_FILL_COLOR = Color.AZURE;
    private static final Color DEFAULT_PEN_COLOR = Color.BLACK;
    private static final double DEFAULT_PEN_SIZE = 5.0;

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private final DoubleProperty canvasWidthProperty;
    private final DoubleProperty canvasHeightProperty;
    private final DoubleProperty penSizeProperty;
    private final ObjectProperty<Color> canvasFillColorProperty;
    private final ObjectProperty<Color> penColorProperty;

    public SketchPad() {
        canvasFillColorProperty = new SimpleObjectProperty<>(DEFAULT_FILL_COLOR);
        penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
        penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
        canvasWidthProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_WIDTH);
        canvasHeightProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_HEIGHT);
        this.canvas = new Canvas(canvasWidthProperty.get(), canvasHeightProperty.get());
        this.canvas.setCache(true);
        this.canvas.setCacheHint(CacheHint.SPEED);
        this.gc = this.canvas.getGraphicsContext2D();
    }

    public SketchPad(Canvas canvas, double width, double height) {
        this.canvas = canvas;
        this.canvas.setCache(true);
        this.canvas.setCacheHint(CacheHint.SPEED);
        canvasFillColorProperty = new SimpleObjectProperty<>(DEFAULT_FILL_COLOR);
        penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
        penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
        canvasWidthProperty = new SimpleDoubleProperty(width);
        canvasHeightProperty = new SimpleDoubleProperty(height);
        this.canvas.setWidth(width);
        this.canvas.setHeight(height);
        this.gc = this.canvas.getGraphicsContext2D();
    }

    public SketchPad(double width, double height) {
        this(new Canvas(), width, height);
    }

    public static SketchPad newInstance() {
        return new SketchPad();
    }

    public static SketchPad newInstance(double width, double height) {
        return new SketchPad(width, height);
    }

    public static SketchPad newInstance(Canvas canvas, double width, double height) {
        return new SketchPad(canvas, width, height);
    }

    public ObjectProperty<Color> canvasFillColorProperty() {
        return canvasFillColorProperty;
    }

    public Color getCanvasFillColor() {
        return canvasFillColorProperty.get();
    }

    public void setCanvasFillColor(Color color) {
        canvasFillColorProperty.set(color);
    }

    public ObjectProperty<Color> penColorProperty() {
        return penColorProperty;
    }

    public Color getPenColor() {
        return penColorProperty.get();
    }

    public void setPenColor(Color color) {
        penColorProperty.set(color);
    }

    public DoubleProperty penSizeProperty() {
        return penSizeProperty;
    }

    public double getPenSize() {
        return penSizeProperty.get();
    }

    public void setPenSize(double penSize) {
        penSizeProperty.set(penSize);
    }

    public DoubleProperty canvasWidthProperty() {
        return canvasWidthProperty;
    }

    public double getCanvasWidth() {
        return canvasWidthProperty.get();
    }

    public void setCanvasWidth(double canvasWidth) {
        canvasWidthProperty.set(canvasWidth);
    }

    public DoubleProperty canvasHeightProperty() {
        return canvasHeightProperty;
    }

    public double getCanvasHeight() {
        return canvasHeightProperty.get();
    }

    public void setCanvasHeght(double canvasHeight) {
        canvasHeightProperty.set(canvasHeight);
    }

    public Scene getScene() {
        return scene;
    }

}
