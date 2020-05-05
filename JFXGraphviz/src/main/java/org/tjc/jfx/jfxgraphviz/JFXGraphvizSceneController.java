/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
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
package org.tjc.jfx.jfxgraphviz;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser.ExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.utils.FileSelector;
import org.tjc.jfx.jfxcomponents.utils.Nodes;
import org.tjc.jfx.jfxgraphviz.config.Config;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXGraphvizSceneController {

    /**
     * Constant <code>log</code>
     */
    private static final Logger log = LoggerFactory.getLogger(JFXGraphvizSceneController.class);

    @FXML
    private VBox mainWindow;
    @FXML
    private ScrollPane imagedTreeViewScrollPane;
    @FXML
    private TreeView<?> imagesTreeView;
    @FXML
    private Button dotButton;
    @FXML
    private MenuItem fileOpenMenuItem;
    @FXML
    private ScrollPane imageScrollPane;
    @FXML
    private ImageView imageView;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemActualSize;
    @FXML
    private MenuItem menuItemZoomIn;
    @FXML
    private MenuItem menuItemZoomOut;
    @FXML
    private MenuItem menuItemZoomToFit;
    @FXML
    private Button neatoButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Menu zoomMenu;

    private Scene scene;
    private final Config config;
    private final BooleanProperty imageLoadedProperty;
    private Path commandLineFilePath;

    public JFXGraphvizSceneController() {
        this.imageLoadedProperty = new SimpleBooleanProperty(false);
        this.config = Config.getInstance();
    }

    public void setCommandLineFilePath(Path commandLineFilePath) {
        this.commandLineFilePath = commandLineFilePath;
    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");

        mainWindow.getChildren().forEach(node -> {
            log.debug("###     child node id: {}", node.getId());
        });

        debugPrintEnvironment();
        debugPrintImageScrollPaneProperties();

        Nodes nodes = new Nodes(mainWindow);
        nodes.dump();

        progressBar.setVisible(true);

        menuItemActualSize.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomIn.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomOut.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomToFit.disableProperty().bind(imageLoadedProperty.not());

        /*
         * Add new StatusBar control.
         */
        mainWindow.getChildren().add(new StatusBar());

        log.debug("### exited initialize()");
    }

    /**
     * <p>
     * Setter for the field <code>scene</code>.</p>
     *
     * @param scene a {@link javafx.scene.Scene} object.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void initializeImageView() {
        log.debug("### entered initializeImageView()");
        log.debug("### exited initializeImageView()");
    }

    @FXML
    private void displayDotGraphImage(ActionEvent event) throws Exception {
        log.debug("### entered displayDotGraphImage()");
        openFile(event);
        log.debug("### exited displayDotGraphImage()");
    }

    @FXML
    private void displayNeatoGraphImage(ActionEvent event) {
        log.debug("### entered displayNeatoGraphImage()");

        File neatoFile = selectFile();
        if (neatoFile != null) {
            log.debug("!!!!! Placeholder for displaying graph images created with 'neato'");
        }
        log.debug("### exited displayNeatoGraphImage()");
    }

    private void openFile(ActionEvent event) throws Exception {
        log.debug("### entered openFile()");

        File dotFile = selectFile();
        if (dotFile == null) {
            log.debug("### exited openFile()");
            return;
        }

        log.debug("###    file chosen: {}", dotFile);
        log.debug("###    entered loadGraphImage(ActionEvent): event: {}", event);
        GraphvizProcessor gvp = new GraphvizProcessor();

        log.debug("###    calling dot(...)");
//        gvp.dot("-Tjpg", "-Tsvg", "-Tpng", "-s300.0", "-O", dotFile.getAbsolutePath());
        gvp.dot("-Tpng", "-Tsvg", "-s300.0", "-O", dotFile.getAbsolutePath());
//        gvp.neato("-Tpng", "-s300.0", "-O", dotFile.getAbsolutePath());

        log.debug("###    Returned from dot process... this is a good sign.");

        String processedFile = dotFile.getAbsolutePath() + ".png";
        log.debug("###    processedFile: {}", processedFile);

        imageView.setImage(null);
        try(FileInputStream fin = new FileInputStream(new File(processedFile))) {
            Image image = new Image(fin);

            log.debug("###    image: {} x {}", image.getWidth(), image.getHeight());
            log.debug("###    setting ImageView's image.");
            imageView.setImage(image);
            imageView.preserveRatioProperty().set(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            this.imageLoadedProperty.set(true);
        }
        log.debug("### exited openFile()");

    }

    @FXML
    private void setImageToActualSize(ActionEvent event) {
        log.debug("### entered imageZoomIn(): event: {}", event);
        if (imageView.getImage() != null) {
            log.debug("###    image.width: {}", imageView.getImage().getWidth());
            log.debug("###    image.height: {}", imageView.getImage().getHeight());
            log.debug("###    imageScrollPane.width: {}", imageScrollPane.getWidth());
            log.debug("###    imageScrollPane.height: {}", imageScrollPane.getHeight());
            makeActualSize(imageView);
//            this.fitImageMode = false;
        }
        log.debug("### exited imageZoomIn()");

    }

    @FXML
    private void imageZoomIn(ActionEvent event) {
        log.debug("### entered imageZoomIn(): event: {}", event);
//        this.fitImageMode = false;
        log.debug("### exited imageZoomIn()");
    }

    @FXML
    private void imageZoomOut(ActionEvent event) {
        log.debug("### entered imageZoomOut(): event: {}", event);
//        this.fitImageMode = false;
        log.debug("### exited imageZoomOut()");

    }

    @FXML
    private void openFileAction(ActionEvent event) throws Exception {
        log.debug("### entered openFileAction(): event: {}", event);
        openFile(event);
        log.debug("### exited openFileAction()");
    }

    @FXML
    private void zoomImageToFit(ActionEvent event) {
        log.debug("### entered zoomImageToFit(): event: {}", event);
        zoomToFit(imageView, imageScrollPane.getWidth(), imageScrollPane.getHeight());
//        this.fitImageMode = true;
        log.debug("### exited zoomImageToFit()");
    }

    /*
     * The following methods do the actual work. I don't like to put all the logic into an event
     * hanlder.
     */
    private void makeActualSize(ImageView imageView) {
        log.debug("### entered makeActualSize(): imageView: {}", imageView);
        if (imageView.getImage() != null) {
            Platform.runLater(() -> {
                imageView.setPreserveRatio(false);
                imageView.setFitHeight(imageView.getImage().getHeight());
                imageView.setFitWidth(imageView.getImage().getWidth());
            });
        }
        log.debug("### exited makeActualSize()");
    }

    private void zoomToFit(ImageView imageView, double fitWidth, double fitHeight) {
        log.debug("### entered zoomToFit(): imageView: {}. fitWidth: {}, fitHeight: {}",
            imageView, fitWidth, fitHeight);
        if (imageView.getImage() != null) {
            Platform.runLater(() -> {
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(fitHeight);
                imageView.setFitHeight(fitHeight);

            });
        }
        log.debug("### exited zoomToFit()");
    }

    private void zoomIn(ImageView imageView) {
        log.debug("### entered zoomIn(): imageView: {}", imageView);
        if (imageView.getImage() != null) {
            Platform.runLater(() -> {
                imageView.setPreserveRatio(true);
            });
        }
        log.debug("### exited zoomIn()");
    }

    private void zoomOut(ImageView imageView) {
        log.debug("### entered zoomOut(): imageView: {}", imageView);
        log.debug("### exited zoomOut()");
    }

    /**
     * <p>
     * chooseFile.</p>
     *
     * @return a {@link java.io.File} object.
     */
    private File selectFile() {
        log.debug("### entered chooseFile()");
        FileSelector fileSelector = FileSelector.newInstance(scene, "Open Graphviz File",
            new ExtensionFilter("Graphviz Dot Files", "*.dot", "*.gv"));
        Path selectedFile = fileSelector.selectFile();
        log.debug("### exited chooseFile()");
        if (selectedFile != null) {
            return selectedFile.toFile();
        } else {
            return null;
        }
    }

    private void debugPrintImageScrollPaneProperties() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("### ===================================");
            log.debug("### Listing imageScrollPane properties:");
            log.debug("### ===================================");
            imageScrollPane.getProperties().forEach((var k, var v) -> {
                log.debug("### {} -> {}", k, v);
            });
            log.debug("### ===================================");
            log.debug("###");
        }
    }

    private void debugPrintEnvironment() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("### ====================================");
            log.debug("### Printing current system snvironment:");
            log.debug("### ====================================");
            System.getenv().forEach((k, v) -> log.debug("### {} -> {}", k, v));
            log.debug("### ====================================");
            log.debug("###");
        }
    }

}
