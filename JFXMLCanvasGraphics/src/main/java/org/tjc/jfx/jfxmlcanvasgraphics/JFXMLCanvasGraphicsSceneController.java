/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxmlcanvasgraphics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.utils.LoggerWrapper;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXMLCanvasGraphicsSceneController {
    private static final LoggerWrapper log = new LoggerWrapper(LoggerFactory.getLogger(
        JFXMLCanvasGraphicsSceneController.class));

    @FXML
    private VBox mainView;
    @FXML
    private StackPane canvasStackPane;
    @FXML
    private ScrollPane stackPaneScollPane;
    @FXML
    private Canvas stackPaneCanvas;

    private Color canvasFillColor;
    private Scene scene;
    private GraphicsContext gc;

    public JFXMLCanvasGraphicsSceneController() {
    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        canvasFillColor = Color.CORNSILK;
        initializeController();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Canvas getCanvas() {
        return stackPaneCanvas;
    }

    private void initializeController() {
        gc = stackPaneCanvas.getGraphicsContext2D();
        log.entered("initializeController()");
        clearCanvas();
        log.exited("initializeController()");
    }

    @FXML
    private void onDrawRandomPixels(ActionEvent event) {
        drawRandomPixels();
        event.consume();
    }

    @FXML
    private void onClearCanvas(ActionEvent event) {
        log.entered("onClearCanvas()");
        clearCanvas();
        event.consume();
        log.exited("onClearCanvas()");
    }

    @FXML
    private void onMoveCanvas(ActionEvent event) {
        log.entered("onMoveCanvas(event)");
        moveCanvas(20, 20);
        debugPrintCanvasInfo();
        event.consume();
        log.exited("onMoveCanvas(event)");
    }

    @FXML
    private void onDebugPrintCanvasInfo(ActionEvent event) {
        debugPrintCanvasInfo();
        event.consume();
    }

    private void clearCanvas() {
        gc.save();
        Bounds bounds = stackPaneCanvas.getBoundsInLocal();
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        double width = bounds.getWidth();
        double height = bounds.getHeight();
        gc.setFill(canvasFillColor);
        gc.fillRect(x, y, width, height);
        gc.restore();
    }

    private void drawRandomPixels() {
        for (int i = 0; i < stackPaneCanvas.getWidth(); i++) {
            for (int j = 0; j < stackPaneCanvas.getHeight(); j++) {
                gc.getPixelWriter().setColor(i, j, Color.color(Math.random(),
                    Math.random(), Math.random()));
            }
        }
    }

    private void moveCanvas(double x, double y) {
        log.debug("### entered moveCanvas(): x: {}, y: {}", x, y);
        stackPaneCanvas.setTranslateX(x);
        stackPaneCanvas.setTranslateY(y);
        log.debug("### exited moveCanvas()");
    }

    private void debugPrintCanvasInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("### ===============================================");
            log.debug("### Printing Canvas Info");
            log.debug("### ===============================================");
            log.debug("### canvas.width        : {}", stackPaneCanvas.getWidth());
            log.debug("### canvas.height       : {}", stackPaneCanvas.getHeight());
            log.debug("### canvasInLocal       : minX: {}, minY: {}, maxX: {}, maxY: {}",
                stackPaneCanvas.getBoundsInLocal().getMinX(),
                stackPaneCanvas.getBoundsInLocal().getMinY(),
                stackPaneCanvas.getBoundsInLocal().getMaxX(),
                stackPaneCanvas.getBoundsInLocal().getMinY());
            log.debug("### canvasInParent      : minX: {}, minY: {}, maxX: {}, maxY: {}",
                stackPaneCanvas.getBoundsInParent().getMinX(),
                stackPaneCanvas.getBoundsInParent().getMinY(),
                stackPaneCanvas.getBoundsInParent().getMaxX(),
                stackPaneCanvas.getBoundsInParent().getMinY());
            log.debug("### ===============================================");
            log.debug("### Done Printing Canvas Info");
            log.debug("### ===============================================");
            log.debug("###");
        }

    }

}
