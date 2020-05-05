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
package org.tjc.jfx.simplegraph;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class CanvasAppSceneController {
    private static final Logger log = LoggerFactory.getLogger(CanvasAppSceneController.class);

    @FXML
    private TitledPane outerPane;
    @FXML
    private HBox topControls;
    @FXML
    private Circle closeWindow;
    @FXML
    private Circle minimizeWindow;
    @FXML
    private Circle expandWindow;
    @FXML
    private Label windowTitle;
    @FXML
    private ScrollPane canvasScrollPane;
    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clearCanvas(Color.WHITE);
        initializeMouseEvents();
    }

    private void clearCanvas(Paint fill) {
        gc.save();
        gc.setFill(fill);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.restore();
    }

    private void initializeMouseEvents() {
        canvas.setOnMouseEntered(event -> {
            log.debug("### entered -> canvas: mouse entered: event: {}", event);

            log.debug("### exited -> canvas: mouse entered: event: {}", event);
        });

        canvas.setOnMouseExited(event -> {
            log.debug("### entered -> canvas: mouse exited: event: {}", event);

            log.debug("### exited -> canvas: mouse exited: event: {}", event);
        });

    }

}
