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
package org.tjc.jfx.jfxturtles;

import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.utils.LoggerWrapper;
import org.tjc.jfx.jfxturtles.model.Turtle;
import org.tjc.jfx.jfxturtles.model.TurtleShapes;
import static org.tjc.jfx.jfxturtles.model.TurtleShapes.relocate;
import static org.tjc.jfx.jfxturtles.model.TurtleShapes.setOpacity;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXTurtlesSceneController {
    private static final LoggerWrapper log = new LoggerWrapper(LoggerFactory.getLogger(
        JFXTurtlesSceneController.class));

    @FXML
    private MenuItem forwardTenMenuItem;
    @FXML
    private VBox mainView;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem placeTurtleCenterMenuItem;
    @FXML
    private MenuItem removeTurtleMenuItem;
    @FXML
    private MenuItem startTurtleRotatingMenuItem;
    @FXML
    private AnchorPane titledViewAnchorPane;
    @FXML
    private TitledPane treeStuffTitledPane;
    @FXML
    private Pane turtleCanvas;
    @FXML
    private SplitPane turtleCanvasSplitPane;
    @FXML
    private SplitPane turtleToolsSplitPane;
    @FXML
    private TreeView<?> turtleTreeView;

    private Scene scene;
    private TurtleShapes turtleShapes;
    private Turtle turtle;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        turtleShapes = new TurtleShapes();
        initializeTurtleCanvas();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void showAxis() {
        log.entered("showAxis()");
        double width = turtleCanvas.getWidth();
        log.debug("###     turtleCanvas.width: {}", width);
        double height = turtleCanvas.getHeight();
        log.debug("###     turtleCanvas.height: {}", height);

        double centerX = turtleCanvas.getBoundsInParent().getCenterX();
        log.debug("###     turtleCanvas.boundsInParent.centerX: {}", centerX);
        double centerY = turtleCanvas.getBoundsInParent().getCenterY();
        log.debug("###     turtleCanvas.boundsInParent.centerY: {}", centerY);

        Line line = turtleShapes.createLine(0, height / 2, width, height / 2);
        line.setStrokeWidth(0.5);
        line.setFill(Color.BLACK);
        log.debug("###     line: {}", line);
        turtleCanvas.getChildren().add(line);
        log.exited("showAxis()");

    }

    private void initializeTurtleCanvas() {
        log.entered("initializeTurtleCanvas()");
        turtleCanvas.getStyleClass().add("turtle-canvas");
        log.exited("initializeTurtleCanvas()");
    }

    private double getTurtleCenterX() {
        double turtleCenterX = 0.0;
        if (turtle != null && !turtle.isDisposed()) {
            turtleCenterX = turtle.getTurtleShape().getBoundsInParent().getCenterX();
            log.debug("### turtleCenterX: {}", turtleCenterX);
        }
        return turtleCenterX;
    }

    private double getTurtleCenterY() {
        double turtleCenterY = 0.0;
        if (turtle != null && !turtle.isDisposed()) {
            turtleCenterY = turtle.getTurtleShape().getBoundsInParent().getCenterY();
            log.debug("### turtleCenterY: {}", turtleCenterY);
        }
        return turtleCenterY;
    }

    @FXML
    private void onFace180Degrees(ActionEvent event) {
        log.entered("onFace180Degrees(event)");
        if (turtle != null && !turtle.isDisposed()) {
            turtle.face(180.0);
        }
        event.consume();
        log.exited("onFace180Degrees(event)");
    }

    @FXML
    private void onFace360Degrees(ActionEvent event) {
        log.entered("onFace360Degrees(event)");
        if (turtle != null && !turtle.isDisposed()) {
            turtle.face(360.0);
        }
        event.consume();
        log.exited("onFace360Degrees(event)");
    }

    @FXML
    private void onFaceNinetyDegrees(ActionEvent event) {
        log.entered("onFaceNinetyDegrees(event)");
        if (turtle != null && !turtle.isDisposed()) {
            turtle.face(90.0);
        }
        event.consume();
        log.exited("onFaceNinetyDegrees(event)");
    }

    @FXML
    private void onFaceZeroDegrees(ActionEvent event) {
        log.entered("onFaceZeroDegrees(event)");
        if (turtle != null && !turtle.isDisposed()) {
            turtle.face(0.0);
        }
        event.consume();
        log.exited("onFaceZeroDegrees(event)");
    }

    @FXML
    private void onForwardTen(ActionEvent event) {
        log.entered("onForwardTen(event)");
        if (turtle != null && !turtle.isDisposed()) {
            Bounds parentBounds = turtle.getTurtleShape().getBoundsInParent();

            log.debug("###     before relocate: centerX: {}, centerY: {}",
                parentBounds.getCenterX(),
                parentBounds.getCenterY());
            double centerX = parentBounds.getCenterX();
            double centerY = parentBounds.getCenterY();
            turtle.getTurtleShape().relocate(0, 0);
//            turtle.getTurtleShape().setTranslateX(parentBounds.getCenterX());
//            turtle.getTurtleShape().setTranslateY(parentBounds.getCenterY() + 25);
            parentBounds = turtle.getTurtleShape().getBoundsInParent();

            log.debug("###     after relocate: centerX: {}, centerY: {}",
                parentBounds.getCenterX(),
                parentBounds.getCenterY());
        }
        event.consume();
        log.exited("onForwardTen(event)");
    }

    @FXML
    private void onDebugLogCanvasInfo(ActionEvent event) {
        if (log.isDebugEnabled()) {
            log.entered("onLogCanvasInfo(event)");
            debugPrintCanvasInfo();
            log.exited("onLogCanvasInfo(event)");
        }
        event.consume();
    }

    @FXML
    private void onDebugPrintTurtleInfo(ActionEvent event) {
        if (log.isDebugEnabled()) {
            log.entered("onPrintTurtleInfo(event)");
            if (turtle != null && !turtle.isDisposed()) {
                turtle.debugPrintTurtleInfo();
            }
            log.exited("onPrintTurtleInfo(event)");
        }
        event.consume();
    }

    @FXML
    private void onDebugPrintTurtleShapeInfo(ActionEvent event) {
        if (log.isDebugEnabled()) {
            log.entered("onPrintTurtleShapeInfo(event)");
            if (turtle != null && !turtle.isDisposed()) {
                turtle.debugPrintTurtleShapeInfo();
            }
            log.exited("onPrintTurtleShapeInfo(event)");
        }
        event.consume();
    }

    @FXML
    private void onRemoveTurtle(ActionEvent event) {
        log.entered("onRemoveTurtle(event)");
        if (turtle != null && !turtle.isDisposed()) {
            if (turtleCanvas.getChildren().contains(turtle.getTurtleShape())) {
                turtleCanvas.getChildren().remove(turtle.getTurtleShape());
            }
            turtle.disposeTurtle();
            turtle = null;
        }
        event.consume();
        log.exited("onRemoveTurtle(event)");
    }

    @FXML
    private void onStartTurtleRotating(ActionEvent event) {
        log.entered("onStartTurtleRotating(event)");
        if (turtle != null && !turtle.isDisposed()) {
            if (turtle.getRotationAnimation() == null) {
                turtle.addRotationAnimation();
            }
            Status status = turtle.getRotationAnimationStatus().orElseThrow();
            if (status.equals(Status.STOPPED)) {
                turtle.getRotationAnimation().play();
            }
        }
        event.consume();
        log.exited("onStartTurtleRotating(event)");
    }

    @FXML
    private void onStopTurtleRotating(ActionEvent event) {
        log.entered("onStopTurtleRotating(event)");
        if (turtle != null && !turtle.isDisposed()) {
            Status status = turtle.getRotationAnimationStatus().orElseThrow();
            if (status.equals(Status.RUNNING) || status.equals(Status.PAUSED)) {
                turtle.getRotationAnimation().stop();
            }
        }
        event.consume();
        log.exited("onStopTurtleRotating(event)");
    }

    @FXML
    private void onPlaceTurtleCenter(ActionEvent event) {
        log.entered("onPlaceTurtleCenter(event)");
        if (turtle == null) {
            placeTurtleAtCenter();
        }
        event.consume();
        log.exited("onPlaceTurtleCenter(event)");
    }

    private void placeTurtleAtCenter() {
        log.entered("placeTurtleAtCenter()");
        turtleCanvas.getBoundsInLocal().getCenterX();
        var centerX = getCenterX();
        var centerY = getCenterY();
        log.debug("###     centerX: {}, centerY: {}", centerX, centerY);
        var polygon
            = makeDraggable(
                setOpacity(
                    relocate(
                        turtleShapes.createDefaultRotateableTurtleShape(Color.BLACK),
                        centerX, centerY),
                    1.0));
        log.debug("###     polygon: {}", polygon);
        turtle = Turtle.newInstance(polygon);
        turtle.setRotate(45.0);
        log.debug("###     turtle: {}", turtle);
        turtleCanvas.getChildren().add(turtle.getTurtleShape());
        log.exited("placeTurtleAtCenter()");
    }

    private Polygon makeDraggable(Polygon turtle) {
        turtle.setOnMousePressed(turtleShapes.nodeOnMousePressedEventHandler);
        turtle.setOnMouseDragged(turtleShapes.nodeOnMouseDraggedEventHandler);
        turtle.setOnMouseReleased(turtleShapes.nodeOnMouseReleasedEventHandler);
        return turtle;
    }

    private double getCenterX() {
        return turtleCanvas.getBoundsInLocal().getCenterX();
    }

    private double getCenterY() {
        return turtleCanvas.getBoundsInLocal().getCenterY();
    }

    private void debugPrintCanvasInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("### ===============================================");
            log.debug("### Printing Canvas Info");
            log.debug("### ===============================================");
            log.debug("### bounds in parent       : {}", turtleCanvas.getBoundsInParent());
            log.debug("### bounds in local        : {}", turtleCanvas.getBoundsInLocal());
            log.debug("### centerX inParent bounds: {}", turtleCanvas.getBoundsInParent()
                .getCenterX());
            log.debug("### centerY inParent bounds: {}", turtleCanvas.getBoundsInParent()
                .getCenterY());
            log.debug("### centerX inLocal bounds : {}", turtleCanvas.getBoundsInLocal()
                .getCenterX());
            log.debug("### centerY inLocal bounds : {}", turtleCanvas.getBoundsInLocal()
                .getCenterY());
            log.debug("### ===============================================");
            log.debug("### Done Printing Canvas Info");
            log.debug("### ===============================================");
            log.debug("###");
        }

    }

}
