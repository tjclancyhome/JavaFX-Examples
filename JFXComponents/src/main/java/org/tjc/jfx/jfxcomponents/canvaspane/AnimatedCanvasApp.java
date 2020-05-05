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
package org.tjc.jfx.jfxcomponents.canvaspane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public abstract class AnimatedCanvasApp extends Application {
    private static final Logger log = LoggerFactory.getLogger(AnimatedCanvasApp.class);

    private int width = 800;
    private int height = 600;
    private GraphicsContext graphicsContext;
    private Paint backgroundColor;
    private final Timeline timeline;
    private int frames = 30;
    private BorderPane root;
    private Stage stage;

    public AnimatedCanvasApp() {
        this.backgroundColor = Color.BLACK;
        this.timeline = new Timeline();
    }

    @Override
    public void start(Stage stage) throws Exception {
        log.debug("### entered start(): stage: {}", stage);
        this.stage = stage;
        var canvas = new Canvas(width, height);
        this.graphicsContext = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        root = new BorderPane();
        root.setCenter(canvas);
        log.debug("###      root.style before: {}", root.getStyle());
        root.setStyle("-fx-background-color:azure;");
        log.debug("###      root.style after : {}", root.getStyle());
        stage.setScene(new Scene(root));
        setup();
        canvas.setWidth(width);
        canvas.setHeight(height);
        startDrawing();
        stage.show();
        internalDraw();
        log.debug("### exited start()");
    }

    public abstract void setup();

    public abstract void draw();

    public void title(String title) {
        stage.setTitle(title);
    }

    public void background(Paint color) {
        backgroundColor = color;
    }

    public void frames(int frames) {
        this.frames = frames;
        startDrawing();
    }

    public void setBottom(Node node) {
        root.setBottom(node);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return stage.getTitle();
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Paint getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Paint backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public double map(double value, double start1, double stop1, double start2, double stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    private void internalDraw() {
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(0, 0, width, height);
        draw();
    }

    private void startDrawing() {
        timeline.stop();
        if (frames > 0) {
            timeline.getKeyFrames().clear();
            KeyFrame frame = new KeyFrame(Duration.millis(1000 / frames), e -> internalDraw());
            timeline.getKeyFrames().add(frame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

}
