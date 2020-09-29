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
import java.util.List;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser.ExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.statusbar.StatusBar;
import org.tjc.jfx.jfxcomponents.utils.FileSelector;
import org.tjc.jfx.jfxcomponents.utils.Nodes;
import org.tjc.jfx.jfxgraphviz.config.Configure;
import org.tjc.jfx.jfxgraphviz.config.RecentFiles;

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

    private static final double SCALE_TEN_PERCENT = 1.10;
    private static final double SCALE_25_PERCENT = 1.25;
    private static final double SCALE_50_PERCENT = 1.50;
    private static final double SCALE_75_PERCENT = 1.75;
    private static final double SCALE_100_PERCENT = 2.0;

    @FXML
    private VBox mainWindow;
    @FXML
    private ScrollPane imagedTreeViewScrollPane;
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
    private SplitPane splitPane;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Menu zoomMenu;
    @FXML
    private ToggleButton toggleGenerateSvg;
    @FXML
    private TreeView<?> graphStoreTreeView;
    @FXML
    private Menu fileOpenRecent;

    private Scene scene;
    private final Configure config;
    private final BooleanProperty imageLoadedProperty;
    private Path commandLineFilePath;
    private final BooleanProperty generateSvgProperty;
    private StatusBar statusBar;
    private ProgressBar progressBar;
    private boolean preserveAspectRatio;
    private final RecentFiles recentFiles;

    public JFXGraphvizSceneController() {
        this.imageLoadedProperty = new SimpleBooleanProperty(false);
        this.generateSvgProperty = new SimpleBooleanProperty(false);
        this.config = Configure.getInstance();
        preserveAspectRatio = false;
        recentFiles = new RecentFiles();
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
        debugPrintImageViewInfo();
        debugPrintImageInfo();

        Nodes nodes = new Nodes(mainWindow);
        nodes.dump();

        menuItemActualSize.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomIn.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomOut.disableProperty().bind(imageLoadedProperty.not());
        menuItemZoomToFit.disableProperty().bind(imageLoadedProperty.not());

        progressBar = new ProgressBar(0.0);
        progressBar.setPrefWidth(250);
        progressBar.setMinWidth(250);
        progressBar.setVisible(true);
        statusBar = new StatusBar();
        statusBar.addNodeToLeftContainer(progressBar);

        /*
         * Add new StatusBar control.
         */
        mainWindow.getChildren().add(statusBar);

        /*
         * JUST FOR TESTING
         */
        recentFiles.addRecentPath(Path.of("d1.gv"));
        recentFiles.addRecentPath(Path.of("d2.gv"));
        recentFiles.addRecentPath(Path.of("digraph1.gv"));
        recentFiles.addRecentPath(Path.of("digraph2.gv"));

        List<MenuItem> menuItems = recentFiles.toMenuItems();
        menuItems.forEach((var menuItem) -> {
            menuItem.setOnAction((ActionEvent event) -> {
                log.debug("### event: {}", event);
                log.debug("### menuItem: {}", menuItem.getText());
            });
        });
        fileOpenRecent.getItems().addAll(menuItems);

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
        event.consume();
        log.debug("### exited displayDotGraphImage()");
    }

    @FXML
    private void displayNeatoGraphImage(ActionEvent event) {
        log.debug("### entered displayNeatoGraphImage()");

        Path neatoFile = selectFile();
        if (neatoFile != null) {
            log.debug("!!!!! Placeholder for displaying graph images created with 'neato'");
        }
        log.debug("### exited displayNeatoGraphImage()");
        event.consume();

    }

    private void openFile(ActionEvent event) throws Exception {
        log.debug("### entered openFile()");

        Path dotFile = selectFile();
        if (dotFile == null) {
            log.debug("### exited openFile()");
            return;
        }

//        imageView.setFitWidth(imageScrollPane.getWidth());
//        imageView.setFitHeight(imageScrollPane.getHeight());

        log.debug("###    file chosen: {}", dotFile);
        log.debug("###    entered loadGraphImage(ActionEvent): event: {}", event);
        GraphvizProcessor gvp = new GraphvizProcessor();

        log.debug("###    calling dot(...)");
//        gvp.dot("-Tjpg", "-Tsvg", "-Tpng", "-s300.0", "-O", dotFile.getAbsolutePath());
//        gvp.dot("-Tpng", "-Tsvg", "-s300.0", "-O", dotFile.getAbsolutePath());
//        gvp.dot("-Tpng", "-Tsvg", "-Gsize=2,2", "-s1.5", "-O", dotFile.getAbsolutePath());
//        gvp.neato("-Tpng", "-s300.0", "-O", dotFile.getAbsolutePath());
        gvp.dot("-v", "-Tpng", "-Gdpi=300.0", "-Gsize=25,25", "-Gratio=1", "-Gcenter=10",
            "-Nshape=box", "-O",
            dotFile.toFile().getAbsolutePath());

        log.debug("###    Returned from dot process... this is a good sign.");

        String processedFile = dotFile.toFile().getAbsolutePath() + ".png";
        log.debug("###    processedFile: {}", processedFile);

        imageView.setImage(null);
        try(FileInputStream fin = new FileInputStream(new File(processedFile))) {
            Image image = new Image(fin);

            log.debug("###    image: {} x {}", image.getWidth(), image.getHeight());
            log.debug("###    setting ImageView's image.");
            imageView.setImage(image);
            imageView.setPreserveRatio(preserveAspectRatio);
            imageView.setSmooth(true);
            imageView.setCache(true);
            zoomToFit();
            this.imageLoadedProperty.set(true);
            debugPrintImageViewInfo();
            debugPrintImageInfo();
        }
        event.consume();
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
            makeActualSize();
        }
        event.consume();
        log.debug("### exited imageZoomIn()");

    }

    @FXML
    private void imageZoomIn(ActionEvent event) {
        log.debug("### entered imageZoomIn(): event: {}", event);
        zoomIn();
        event.consume();
        log.debug("### exited imageZoomIn()");
    }

    @FXML
    private void imageZoomOut(ActionEvent event) {
        log.debug("### entered imageZoomOut(): event: {}", event);
        zoomOut();
        event.consume();
        log.debug("### exited imageZoomOut()");
    }

    @FXML
    private void openFileAction(ActionEvent event) throws Exception {
        log.debug("### entered openFileAction(): event: {}", event);
        openFile(event);
        event.consume();
        log.debug("### exited openFileAction()");
    }

    @FXML
    private void zoomImageToFit(ActionEvent event) {
        log.debug("### entered zoomImageToFit(): event: {}", event);
        log.debug("###     viewportBounds: {}", imageScrollPane.getViewportBounds());
        zoomToFit();
        event.consume();
        log.debug("### exited zoomImageToFit()");
    }

    /*
     * The following methods do the actual work. I don't like to put all the logic into an event
     * hanlder.
     */
    private void makeActualSize() {
        log.debug("### entered makeActualSize(): imageView: {}", imageView);
        if (imageView.getImage() != null) {
            Platform.runLater(() -> {
                imageView.setScaleX(1.0);
                imageView.setScaleY(1.0);
                if (isBoundStickyFit()) {
                    unbindStickyFit();
                }
                imageView.setFitHeight(imageView.getImage().getHeight());
                imageView.setFitWidth(imageView.getImage().getWidth());
                debugPrintImageViewInfo();
                debugPrintImageInfo();
            });
        }
        log.debug("### exited makeActualSize()");
    }

    private void zoomToFit() {
        log.debug("### entered zoomToFit()");
        if (imageView.getImage() != null) {
            double fitWidth = imageScrollPane.getWidth();
            double fitHeight = imageScrollPane.getHeight();
            log.debug("###     imageView: {}. fitWidth: {}, fitHeight: {}", imageView,
                fitWidth, fitHeight);
            if (isBoundStickyFit()) {
                unbindStickyFit();
            }
            imageView.setFitWidth(fitWidth);
            imageView.setFitHeight(fitHeight);
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
            bindStickyFit();
            debugPrintImageViewInfo();
            debugPrintImageInfo();
        }
        log.debug("### exited zoomToFit()");
    }

    private boolean isBoundStickyFit() {
        return imageView.fitHeightProperty().isBound() && imageView.fitWidthProperty().isBound();
    }

    private void toggleStickyFit() {
        if (isBoundStickyFit()) {
            unbindStickyFit();
        } else {
            bindStickyFit();
        }
    }

    private void bindStickyFit() {
        if (!imageView.fitWidthProperty().isBound()) {
            imageView.fitWidthProperty().bind(imageScrollPane.widthProperty());
        }
        if (!imageView.fitHeightProperty().isBound()) {
            imageView.fitHeightProperty().bind(imageScrollPane.heightProperty());
        }
    }

    private void unbindStickyFit() {
        if (imageView.fitWidthProperty().isBound()) {
            imageView.fitWidthProperty().unbind();
        }
        if (imageView.fitHeightProperty().isBound()) {
            imageView.fitHeightProperty().unbind();
        }
    }

    private void zoomIn() {
        log.debug("### entered zoomIn(): imageView: {}", imageView);
        if (imageView.getImage() != null) {
            debugPrintImageInfo();
            if (isBoundStickyFit()) {
                unbindStickyFit();
            }
            imageView.setScaleX(imageView.getScaleX() * SCALE_TEN_PERCENT);
            imageView.setScaleY(imageView.getScaleY() * SCALE_TEN_PERCENT);
            log.debug("###     scaleX: {}", imageView.getScaleX());
            log.debug("###     scaleY: {}", imageView.getScaleY());
            debugPrintImageViewInfo();
        }
        log.debug("### exited zoomIn()");
    }

    private void zoomOut() {
        log.trace("### entered zoomOut()");
        if (imageView.getImage() != null) {
            if (isBoundStickyFit()) {
                unbindStickyFit();
            }
            imageView.setScaleX(imageView.getScaleX() - 0.10);
            imageView.setScaleY(imageView.getScaleY() - 0.10);
            log.debug("###     scaleX: {}", imageView.getScaleX());
            log.debug("###     scaleY: {}", imageView.getScaleY());
            debugPrintImageViewInfo();
        }
        log.trace("### exiting zoomOut()");
    }

    /**
     * <p>
     * chooseFile.</p>
     *
     * @return a {@link java.io.File} object.
     */
    private Path selectFile() {
        log.debug("### entered chooseFile()");
        FileSelector fileSelector = FileSelector.newInstance(scene, "Open Graphviz File",
            new ExtensionFilter("Graphviz .dot/.gv Files", "*.dot", "*.gv"));
        Path selectedFile = fileSelector.selectFile();
        log.debug("### exited chooseFile()");
        if (selectedFile != null) {
            return selectedFile;
        } else {
            return null;
        }
    }

    @FXML
    private void onToggleGenerateSvg(ActionEvent event) {
        generateSvgProperty.setValue(toggleGenerateSvg.isSelected());
        event.consume();
    }

    @FXML
    private void onQuit(ActionEvent event) {
        debugPrintImageScrollPaneProperties();
        debugPrintEnvironment();
        debugPrintImageViewInfo();
        debugPrintImageInfo();
        event.consume();
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
            log.debug("### Printing current system environment:");
            log.debug("### ====================================");
            System.getenv().forEach((k, v) -> log.debug("### {} -> {}", k, v));
            log.debug("### ====================================");
            log.debug("###");
        }
    }

    private void debugPrintImageViewInfo() {
        if (log.isDebugEnabled() && imageLoadedProperty.getValue() == true) {
            log.debug("###");
            log.debug("### ====================================");
            log.debug("### Printing imageView info:");
            log.debug("### ====================================");
            log.debug("### viewport             : {}", imageView.getViewport());
            log.debug("### fitHeight            : {}", imageView.getFitHeight());
            log.debug("### fitWidth             : {}", imageView.getFitWidth());
            log.debug("### preserve aspect ratio: {}", imageView.isPreserveRatio());
            log.debug("### smooth               : {}", imageView.isSmooth());
            log.debug("### pressed              : {}", imageView.isPressed());
            log.debug("### resizable            : {}", imageView.isResizable());
            log.debug("### cache                : {}", imageView.isCache());
            log.debug("### image                : {}", imageView.getImage());
            log.debug("### x                    : {}", imageView.getX());
            log.debug("### y                    : {}", imageView.getY());
            log.debug("### scaleX               : {}", imageView.getScaleX());
            log.debug("### scaleY               : {}", imageView.getScaleY());
            log.debug("### ====================================");
            log.debug("###");
        }
    }

    private void debugPrintImageInfo() {
        if (log.isDebugEnabled() && imageLoadedProperty.getValue() == true) {
            Image image = imageView.getImage();
            if (image != null) {
                log.debug("###");
                log.debug("### ====================================");
                log.debug("### Printing image info:");
                log.debug("### ====================================");
                log.debug("### image                : {}", image);
                log.debug("### image.height         : {}", image.getHeight());
                log.debug("### image.width          : {}", image.getWidth());
                log.debug("### image.requestedHeight: {}", image.getRequestedHeight());
                log.debug("### image.requestedWidth : {}", image.getRequestedWidth());
                log.debug("### image.url            : {}", image.getUrl());
                log.debug("### ====================================");
                log.debug("###");
            }
        }
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        log.trace("### entered onMouseDragged(): event: {}", event);
        event.consume();
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        log.trace("### entered onMouseClicked(): event: {}", event);
        event.consume();
    }

    @FXML
    private void onDragDetected(MouseEvent event) {
        log.trace("### onDragDetected(): event: {}", event);
        event.consume();
    }
}
