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
package org.tjc.jfx.jfxfractals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.canvaspane.AnimatedCanvasApp;

/**
 * JavaFX JFXFractalsApp
 */
public class JFXFractalsApp extends AnimatedCanvasApp {
    private static final Logger log = LoggerFactory.getLogger(JFXFractalsApp.class);
    private static final int MAX_ITERATIONS = 100;

    private GraphicsContext gc;
    private double zx;
    private double zy;
    private double cX;
    private double cY;
    private double tmp;
    int i;
    private BooleanProperty running;

    public static void main(String[] args) {
        launch();
    }

    public JFXFractalsApp() {
        running = new SimpleBooleanProperty();
    }

    @Override
    public void setup() {
        gc = this.getGraphicsContext();
        setWidth(1200);
        setHeight(800);
        Canvas canvas = gc.getCanvas();
        BorderPane bp = (BorderPane) canvas.getParent();
        bp.setCenter(null);
        StackPane p = new StackPane(canvas);
        p.setMinSize(20000, 20000);
        ScrollPane sp = new ScrollPane(p);
        sp.setPrefSize(1200, 800);
        sp.setVvalue(0.5);
        sp.setHvalue(0.5);
        bp.setCenter(sp);
        sp.setOnMouseClicked(e -> {
            double zoom = 0.2;
            double scaleX = canvas.getScaleX();
            double scaleY = canvas.getScaleY();
            if (e.getButton() == MouseButton.SECONDARY && (canvas.getScaleX() > 0.5)) {
                canvas.setScaleX(scaleX - zoom);
                canvas.setScaleY(scaleY - zoom);
            } else if (e.getButton() == MouseButton.PRIMARY) {
                canvas.setScaleX(scaleX + zoom);
                canvas.setScaleY(scaleY + zoom);
            } else if (e.getButton() == MouseButton.MIDDLE) {
                sp.setVvalue(0.5);
                sp.setHvalue(0.5);
                canvas.setScaleX(1);
                canvas.setScaleY(1);
            }
        });
        canvas.setOnMousePressed(canvas.getOnMouseClicked());
        setFrames(0);
        setTitle("Mandlebrot wity color and zoom");
    }

    @Override
    public void draw() {
        long start = System.currentTimeMillis();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                zx = zy = 0;
                cX = map(x, 0, getWidth(), -2.5, 1.0);
                cY = map(y, 0, getHeight(), -1, 1.0);
                i = 0;
                while (zx * zx + zy * zy < 4 && i < MAX_ITERATIONS) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    i++;
                }
                if (i < MAX_ITERATIONS) {
                    double newC = ((double) i) / ((double) MAX_ITERATIONS);
                    Color c;
                    if (newC > 0.4) {
                        c = Color.color(newC, 0.8, newC);
                    } else {
                        c = Color.color(0.2, newC, 0.2);
                    }
                    gc.getPixelWriter().setColor(x, y, c);
                } else {
                    gc.getPixelWriter().setColor(x, y, Color.BLACK);
                }
            }
        }
        System.out.println(
            "Generating mandlebrot took " + (System.currentTimeMillis() - start) + " ms");
    }
}
