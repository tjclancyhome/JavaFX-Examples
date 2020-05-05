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

import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public abstract class AbstractAnimationCanvas implements AnimationCanvas {
    private static final Logger log = LoggerFactory.getLogger(AbstractAnimationCanvas.class);

    private double width;
    private double height;
    private Paint canvasFill;
    private int frames = 30;
    private Stage stage;
    private String title;
    private Timeline timeline;
    private final BorderPane root;
    private final GraphicsContext graphicsContext;
    private final Canvas canvas;

    public AbstractAnimationCanvas(AnimationCanvasConf conf) {
        Objects.requireNonNull(conf, () -> "The 'conf' argument is null.");
        validateConf(conf);
        log.debug("### entered AbstractAnimationCanvas(): conf: {}", conf);
        width = conf.widthProperty().get();
        height = conf.heightProperty().get();
        canvasFill = conf.canvasFillProperty().get();
        frames = conf.framesProperty().get();
        title = "Untitled";
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        root = new BorderPane(canvas);
        timeline = new Timeline();
        log.debug("### exited AbstractAnimationCanvas()");
    }

    @Override
    public abstract void setup();

    @Override
    public abstract void draw();

    public void clearCanvas() {
        Bounds bounds = canvas.getBoundsInLocal();
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        graphicsContext.setFill(getCanvasFill());
        graphicsContext.fillRect(x, y, width, height);
    }

    @Override
    public int getFrames() {
        return frames;
    }

    @Override
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double map(double value, double start1, double stop1, double start2, double stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    @Override
    public Paint getCanvasFill() {
        return canvasFill;
    }

    @Override
    public void setCanvasFill(Paint canvasFill) {
        this.canvasFill = canvasFill;
    }

    @Override
    public void setBottom(Node node) {
        root.setBottom(node);
    }

    @Override
    public void setFrames(int frames) {
        this.frames = frames;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane getRoot() {
        return root;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void internalDraw() {
        graphicsContext.setFill(canvasFill);
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

    private void validateConf(AnimationCanvasConf conf) {
        Objects.requireNonNull(conf.canvasFillProperty().get(),
            () -> "The 'conf.backgroundColor' property is null.");
    }

}
