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
package org.tjc.jfx.jfxsamplesanddemos;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * A simple demo framework that lets you show your own controls, etc., in a simple demo UI.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class SamplesAndDemosApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(SamplesAndDemosApp.class);
    private static final Border DEFAULT_BORDER = new Border(new BorderStroke(Color.RED,
            BorderStrokeStyle.SOLID,
            new CornerRadii(0),
            BorderWidths.DEFAULT));
    private ObservableSet<Region> borderedRegions;
    private boolean bordersShowing = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        borderedRegions = FXCollections.synchronizedObservableSet(FXCollections.observableSet());
        Parent demoScene = buildDemoScene();
        Scene scene = new Scene(demoScene);
        scene.getStylesheets().add("controlStyle.css");
        primaryStage.setTitle("Samples and Demos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Parent buildDemoScene() {
        VBox box = new VBox();
        box.setFillWidth(true);
        box.setPrefSize(640, 480);
        box.getChildren().addAll(
                newMenuBar(),
                newToolBar(),
                newSplitPane(),
                newStatusBar());
        VBox.setVgrow(box, Priority.ALWAYS);
        return box;
    }

    private MenuBar newMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(
                initMenu(new Menu("File")),
                initMenu(new Menu("Edit")),
                initMenu(new Menu("View")),
                initMenu(new Menu("Tools")),
                initMenu(new Menu("Window")),
                initMenu(new Menu("Help")));
        addBorder(menuBar);
        return menuBar;
    }

    private ToolBar newToolBar() {
        Button button = new Button("Show/Hide Borders");
        button.setOnAction(ev -> {
            if (bordersShowing) {
                hideBorders();
            } else {
                showBorders();
            }
        });
        ToolBar toolBar = new ToolBar(button);
        VBox.setVgrow(toolBar, Priority.NEVER);
        addBorder(toolBar);
        return toolBar;
    }

    private SplitPane newSplitPane() {
        SplitPane splitPane = new SplitPane();
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.setDividerPositions(0.35);
        splitPane.getItems().addAll(createTreeView(), createDemoView());
        addBorder(splitPane);
        return splitPane;
    }

    private StatusBar newStatusBar() {
        StatusBar statusBar = new StatusBar();
        statusBar.addNodeToLeftContainer(new Button("Left Side"));
        statusBar.addNodeToRightContainer(new Button("Right Side"));
        addBorder(statusBar);
        return statusBar;
    }

    private Menu initMenu(Menu menu) {
        switch(menu.getText()) {
            case "File": {
                menu.getItems().addAll(
                        initMenuItem(new MenuItem("New")),
                        newSeparator(),
                        initMenuItem(new MenuItem("Open")),
                        initMenuItem(new MenuItem("Close")),
                        newSeparator(),
                        initMenuItem(new MenuItem("Quit")));
            }
            break;
            default:
        }
        return menu;
    }

    private SeparatorMenuItem newSeparator() {
        return new SeparatorMenuItem();
    }

    private MenuItem initMenuItem(MenuItem menuItem) {
        return menuItem;
    }

    private ScrollPane createTreeView() {
        var treeView = new TreeView<String>();
        var root = new TreeItem<String>("Fruit");
        root.setExpanded(true);
        treeView.setShowRoot(true);
        treeView.setRoot(root);
        root.getChildren().addAll(createTreeItem("Apple"), createTreeItem("Orange"), createTreeItem(
                "Pear"));
        var scrollPane = new ScrollPane(treeView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setMinWidth(scrollPane.getPrefWidth());
        scrollPane.setMinHeight(scrollPane.getPrefHeight());

        SplitPane.setResizableWithParent(scrollPane, Boolean.FALSE);
        addBorder(scrollPane);
        return scrollPane;
    }

    private TabPane createDemoView() {
        var tabPane = new TabPane();

        var tab1 = new Tab();
        tab1.setText("Demo 1");
        tab1.setContent(createDemoPane("First Demo"));

        var tab2 = new Tab("Demo 2");
        tab2.setContent(createDemoPane("Second Demo"));

        var tab3 = new Tab("Demo 3");
        tab3.setContent(createDemoPane("Third Demo"));

        tabPane.getTabs().addAll(tab1, tab2, tab3);
        addBorder(tabPane);
        return tabPane;
    }

    private TreeItem<String> createTreeItem(String value) {
        return new TreeItem<>(value);
    }

    private Region addBorder(Region region) {
        log.trace("### Entered addBorder(Region): region: {}", region);

        if (region == null) {
            return null;
        }
        log.trace("### setting border on: {}", region);
        region.setBorder(DEFAULT_BORDER);
        bordersShowing = true;
        borderedRegions.add(region);
        return region;
    }

    private void showBorders() {
        Platform.runLater(() -> {
            borderedRegions.forEach((var r) -> {
                if (r.getBorder() == Border.EMPTY) {
                    r.setBorder(DEFAULT_BORDER);
                }
            });
            bordersShowing = true;
        });
    }

    private void hideBorders() {
        Platform.runLater(() -> {
            borderedRegions.forEach((var r) -> {
                if (r.getBorder() != Border.EMPTY) {
                    r.setBorder(Border.EMPTY);
                }
            });
            bordersShowing = false;
        });
    }

    private Node createDemo(String demoName) {
        return new Button(demoName);
    }

    private Control createDemoPane(String demoName) {
        return new ScrollPane(new Button(demoName));
    }

}
