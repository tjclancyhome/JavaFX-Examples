/**
 * Sample Skeleton for 'JFXCanvasExampleScene.fxml' Controller Class
 */
package org.tjc.jfx.jfxcanvasexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * JFXCanvasExampleSceneController class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXCanvasExampleSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXCanvasExampleSceneController.class);

    @FXML // fx:id="mainWindow"
    private VBox mainWindow; // Value injected by FXMLLoader

    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader

    @FXML // fx:id="fileMenu"
    private Menu fileMenu; // Value injected by FXMLLoader

    @FXML // fx:id="editMenu"
    private Menu editMenu; // Value injected by FXMLLoader

    @FXML // fx:id="viewMenu"
    private Menu viewMenu; // Value injected by FXMLLoader

    @FXML // fx:id="windowMenu"
    private Menu windowMenu; // Value injected by FXMLLoader

    @FXML // fx:id="newCanvasButton"
    private Button newCanvasButton; // Value injected by FXMLLoader

    @FXML // fx:id="saveCanvasButton"
    private Button saveCanvasButton; // Value injected by FXMLLoader

    @FXML // fx:id="clearCanvasButton"
    private Button clearCanvasButton; // Value injected by FXMLLoader

    @FXML // fx:id="splitPane"
    private SplitPane splitPane; // Value injected by FXMLLoader

    @FXML // fx:id="treeViewScroller"
    private ScrollPane treeViewScroller; // Value injected by FXMLLoader

    @FXML // fx:id="treeView"
    private TreeView<?> treeView; // Value injected by FXMLLoader

    @FXML // fx:id="mainCanvas"
    private Canvas mainCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="statusBar"
    private HBox statusBar; // Value injected by FXMLLoader

    @FXML // fx:id="leftLabel"
    private Label leftLabel; // Value injected by FXMLLoader

    @FXML // fx:id="stretcherPane"
    private Pane stretcherPane; // Value injected by FXMLLoader

    @FXML // fx:id="rightLabel"
    private Label rightLabel; // Value injected by FXMLLoader

    private Stage stage;

    void initialize() {
        log.debug("##### initializing JFXCanvasExampleSceneController()...");
//        assertControls();
        initializeCanvas();
    }

    /**
     * <p>
     * Setter for the field <code>stage</code>.</p>
     *
     * @param stage a {@link javafx.stage.Stage} object.
     */
    public void setStage(Stage stage) {
        log.debug("### entered setStage()");
        this.stage = stage;
        log.debug("### exited setStage()");
    }

    public Stage getStage() {
        return stage;
    }

    private void initializeCanvas() {
        log.info("##### Initializing canvas...");
        mainCanvas.setWidth(1024);
        mainCanvas.setHeight(768);
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        log.debug("#####     canvas.width : {}", mainCanvas.getWidth());
        log.debug("#####     canvas.height: {}", mainCanvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.strokeText("Hello, Canvas", 150, 150);
        gc.rect(0, 0, 200, 200);
        gc.clearRect(0, 0, 20, 20);

        log.debug("##### Bounds in local: {}", gc.getCanvas().getBoundsInLocal());
        log.debug("##### Bounds in parent: {}", gc.getCanvas().getBoundsInParent());

    }

    @FXML
    private void onNewCanvas(ActionEvent event) {
    }
}
