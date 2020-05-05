package org.tjc.jfx.jfxcanvasbaseapp;

import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXCanvasBaseApp
 */
public class JFXCanvasBaseApp extends JFXExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXCanvasBaseApp.class);

    private static final double DEFAULT_CANVAS_WIDTH = 800;
    private static final double DEFAULT_CANVAS_HEIGHT = 600;
    private static final double DEFAULT_APPLICATION_WIDTH = 900;
    private static final double DEFAULT_APPLICATION_HEIGHT = 700;
    private static final double DEFAULT_VBOX_SPACING = 5;
    private static final Color DEFAULT_CANVAS_COLOR = Color.LIGHTBLUE;

    private Stage stage;
    private Canvas canvas;
    private VBox mainContainer;
    private BorderPane canvasParentContainer;
    private GraphicsContext graphicsContext;
    private double canvasWidth;
    private double canvasHeight;
    private double applicationWidth;
    private double applicationHeight;
    private Color canvasColor;

    public JFXCanvasBaseApp() {
        log.debug("### entered JFXCanvasBaseApp() constructor.");
        canvasWidth = DEFAULT_CANVAS_WIDTH;
        canvasHeight = DEFAULT_CANVAS_HEIGHT;
        canvasColor = DEFAULT_CANVAS_COLOR;
        applicationWidth = DEFAULT_APPLICATION_WIDTH;
        applicationHeight = DEFAULT_APPLICATION_HEIGHT;
        log.debug("### exited JFXCanvasBaseApp() constructor.");
    }

    @Override
    public void start(Stage primaryStage) {
        log.debug("### entered start(primaryStage)");
        stage = primaryStage;
        canvas = new Canvas(getCanvasWidth(), getCanvasHeight());
        graphicsContext = canvas.getGraphicsContext2D();
        mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_LEFT);
        mainContainer.setStyle("-fx-background-color:WHITE;");
        canvasParentContainer = new BorderPane(canvas);
        canvasParentContainer.setStyle("-fx-background-color:LIGHTGRAY;");
        VBox.setVgrow(canvasParentContainer, Priority.ALWAYS);
        mainContainer.getChildren().add(canvasParentContainer);
        var scene = new Scene(mainContainer, getApplicationWidth(), getApplicationHeight());
        clearCanvas();

        addMenuBar(new MenuBar(
            new Menu("File"),
            new Menu("Edit"),
            new Menu("View"),
            new Menu("Window"),
            new Menu("Help")
        ));

        stage.setTitle("JFXCanvasBaseApp");
        stage.setScene(scene);
        stage.show();
        debugPrintCanvasInfo();
        log.debug("### exited start(stage)");
    }

    public void clearCanvas() {
        var gc = graphicsContext;
        gc.save();
        log.debug("### entered clearCanvas()");
        var bounds = canvas.getBoundsInLocal();
        var x = bounds.getMinX();
        var y = bounds.getMinY();
        var width = bounds.getWidth();
        var height = bounds.getHeight();
        gc.setFill(canvasColor);
        gc.fillRect(x, y, width, height);
        gc.restore();
        log.debug("### exited clearCanvas()");
    }

    public void addMenuBar(MenuBar menuBar) {
        VBox.setVgrow(menuBar, Priority.NEVER);
        mainContainer.getChildren().add(0, menuBar);
        mainContainer.getChildren().forEach(child -> log.debug("### child: {}", child));
    }


    public static void main(String[] args) {
        launch(args);
    }

    protected double getCanvasWidth() {
        return canvasWidth;
    }

    protected double getCanvasHeight() {
        return canvasHeight;
    }

    protected void setCanvasWidth(double canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    protected void setCanvasHeight(double canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    protected GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    protected Color getCanvasColor() {
        return canvasColor;
    }

    protected void setCanvasColor(Color canvasColor) {
        this.canvasColor = canvasColor;
    }

    public double getApplicationWidth() {
        return applicationWidth;
    }

    public void setApplicationWidth(double applicationWidth) {
        this.applicationWidth = applicationWidth;
    }

    public double getApplicationHeight() {
        return applicationHeight;
    }

    public void setApplicationHeight(double applicationHeight) {
        this.applicationHeight = applicationHeight;
    }

    private void debugPrintCanvasInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("### ====================================");
            log.debug("### Printing App Info");
            log.debug("### ====================================");
            log.debug("### canvas local  x   : {}", canvas.getBoundsInLocal().getMinX());
            log.debug("### canvas local  y   : {}", canvas.getBoundsInLocal().getMinY());
            log.debug("### canvas parent x   : {}", canvas.getBoundsInParent().getMinX());
            log.debug("### canvas parent y   : {}", canvas.getBoundsInParent().getMinY());
            log.debug("### canvas width      : {}", canvas.getWidth());
            log.debug("### canvas height     : {}", canvas.getHeight());
            log.debug("### application width : {}", applicationWidth);
            log.debug("### application height: {}", applicationHeight);
            log.debug("### canvas fill color : {}", graphicsContext.getFill());
            log.debug("### ====================================");
            log.debug("### Done Printing App Info");
            log.debug("### ====================================");
            log.debug("###");
        }
    }
}
