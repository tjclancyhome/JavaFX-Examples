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
package org.tjc.jfx.jfxhexeditor;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.statusbar.StatusBar;
import org.tjc.jfx.jfxcomponents.utils.Menus;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXHexEditorSceneController {
    private static final Logger log = LoggerFactory.getLogger(JFXHexEditorSceneController.class);
    private static final int NUM_COLUMNS = 20;
    private static final int NUM_ROWS = 20;
    private static final double FONT_SIZE = 14.0;
    private static final Font DEFAULT_FONT = new Font("Source Code Pro", FONT_SIZE);
    private static final double COLUMN_WIDTH = FONT_SIZE * 1.5;
    private static final double ROW_HEIGHT = FONT_SIZE * 1.5;

    @FXML
    private MenuItem addDataDebugMenuItem;
    @FXML
    private GridPane charGrid;
    @FXML
    private ScrollPane charGridScrollPane;
    @FXML
    private MenuItem clearDataDebugMenuItem;
    @FXML
    private Menu debugMenu;
    @FXML
    private Menu fontViewMenu;
    @FXML
    private AnchorPane hexEditorAnchorPane;
    @FXML
    private SplitPane hexEditorSplitPane;
    @FXML
    private AnchorPane hexGridAnchorPane;
    @FXML
    private ScrollPane hexGridScrollPane;
    @FXML
    private CheckMenuItem showToolBarMenuItem;
    @FXML
    private ScrollPane lineNumberScrollPane;
    @FXML
    private VBox mainView;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button refreshButton;
    @FXML
    private CheckMenuItem showGridLinesMenuItem;
    @FXML
    private ToolBar toolBar;

    private Scene scene;
    private GridPane hexGridPane;
    private Font font;
    private double fontSize;
    private StatusBar statusBar;
    private BooleanProperty isGridLinesVisibleProperty;

    public void initialize() {
        isGridLinesVisibleProperty = new SimpleBooleanProperty(false);
        isGridLinesVisibleProperty.addListener((obsrv, oldVal, newVal) -> {
            log.debug("&&& isGridLinesVisibleProperty: oldVal: {}, newVal: {}", oldVal, newVal);
        });
        hideToolBar();
        font = DEFAULT_FONT;
        fontSize = FONT_SIZE;
        initializeFontList();
        initializeGrids();
        initializeStatusBar();
        hideGridLines();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void debugMode() {
        addDebugData();
        showHexGridDebugData();
        showMenuDebugInfo();
        showGeneralDebugInfo();
    }

    private void initializeFontList() {
        log.debug("### entered initializeFontList()");
        ObservableList<MenuItem> list = FXCollections.observableArrayList();
        list.addAll(
            createFontMenuItem(new Font("Source Code Pro", FONT_SIZE)),
            createFontMenuItem(new Font("Monaco", FONT_SIZE)),
            createFontMenuItem(new Font("Menlo Regular", FONT_SIZE)),
            createFontMenuItem(new Font("Oxygen Mono", FONT_SIZE)),
            createFontMenuItem(new Font("Overpass Mono Light", FONT_SIZE)),
            createFontMenuItem(new Font("NovaMono", FONT_SIZE)),
            createFontMenuItem(new Font("Nimbus Mono", FONT_SIZE)),
            createFontMenuItem(new Font("Monoton", FONT_SIZE)),
            createFontMenuItem(new Font("Luxi Mono Regular", FONT_SIZE)),
            createFontMenuItem(new Font("Liberation Mono", FONT_SIZE)),
            createFontMenuItem(new Font("Cutive Mono Regular", FONT_SIZE)),
            createFontMenuItem(new Font("Courier New", FONT_SIZE)),
            createFontMenuItem(new Font("Courier", FONT_SIZE)),
            createFontMenuItem(new Font("Fira Code Retina", FONT_SIZE)),
            createFontMenuItem(new Font("Andale Mono", FONT_SIZE))
        );
        fontViewMenu.getItems().addAll(list.sorted());
    }

    private MenuItem createFontMenuItem(Font menuItemFont) {
        log.debug("### entered createFontMenuItem(): font: {}", menuItemFont);
        String fontName = menuItemFont.getName();
        CheckMenuItem menuItem = new CheckMenuItem(fontName);
        if (menuItemFont.equals(DEFAULT_FONT)) {
            menuItem.setSelected(true);
        }
        menuItem.setOnAction((var event) -> {
            log.debug("### menuItem onAction selected: event: {}", event);
            Menus.unselectAllCheckMenuItems(fontViewMenu);
            menuItem.setSelected(true);
            setFont(menuItemFont);
        });
        menuItem.setId(fontName);
        log.trace("### exited createFontMenuItem()");
        return menuItem;
    }

    private void setFont(Font font) {
        log.debug("### entered setFont(): font: {}", font.getName());
        this.font = font;
        if (hexGridPane != null && !hexGridPane.getChildren().isEmpty()) {
            hexGridPane.getChildren().forEach(node -> {
                if (node instanceof Text) {
                    ((Text) node).setFont(font);
                }
            });
        }
        log.trace("### exited setFont()");
    }

    private void initializeGrids() {
        log.debug("### entered initializeGrids()");
        clearData();
        initializeHexGrid();
    }

    private void initializeHexGrid() {
        log.debug("### entered initializeHexGrid()");
        hexGridPane = new GridPane();
        hexGridScrollPane.setFitToHeight(false);
        hexGridScrollPane.setFitToWidth(false);
        hexGridPane.setAlignment(Pos.CENTER);
        AnchorPane.setBottomAnchor(hexGridPane, 0.0);
        AnchorPane.setTopAnchor(hexGridPane, 0.0);
        AnchorPane.setLeftAnchor(hexGridPane, 0.0);
        AnchorPane.setRightAnchor(hexGridPane, 0.0);
        hexGridAnchorPane.getChildren().clear();
        hexGridAnchorPane.getChildren().add(hexGridPane);
    }

    private void initializeStatusBar() {
        log.debug("### entered initializeStatusBar()");
        statusBar = new StatusBar();
        statusBar.setVisible(true);
        mainView.getChildren().add(statusBar);
    }

    private void clearData() {
        log.debug("### entered clearData()");
        if (hexGridPane != null) {
            hexGridPane.getChildren().clear();
            hexGridPane.getColumnConstraints().clear();
            hexGridPane.getRowConstraints().clear();
        }
        if (charGrid != null) {
            charGrid.getChildren().clear();
            charGrid.getColumnConstraints().clear();
            charGrid.getRowConstraints().clear();
        }
    }

    @FXML
    private void onAddDebugData(ActionEvent event) {
        log.debug("### entered onAddDebugData()");
        Platform.runLater(() -> {
            if (hexGridPane != null && hexGridPane.getChildren().isEmpty()) {
                addDebugData();
                showHexGridDebugData();
            }
        });
    }

    @FXML
    private void onClearDebugData(ActionEvent event) {
        log.debug("### entered onClearDebugData()");
        Platform.runLater(() -> {
            if (hexGridPane != null && !hexGridPane.getChildren().isEmpty()) {
                clearDebugData();
            }
        });
    }

    private void clearDebugData() {
        log.debug("entered clearDebugData()");
        hexGridPane.getChildren().clear();
        hexGridPane.getColumnConstraints().clear();
        hexGridPane.getRowConstraints().clear();
        showHexGridDebugData();
    }

    @FXML
    private void onShowToolBar(ActionEvent event) {
        log.debug("### entered onShowToolBar()");
        if (toolBarHidden()) {
            showToolBar();
        } else {
            hideToolBar();
        }
    }

    private void showToolBar() {
        log.debug("### entered showToolBar()");
        if (toolBarHidden()) {
            mainView.getChildren().add(1, toolBar);
            toolBar.setVisible(true);
            showToolBarMenuItem.setSelected(true);
        }
    }

    private void hideToolBar() {
        log.debug("### entered hideToolBar()");
        if (!toolBarHidden()) {
            FilteredList<Node> filteredList = mainView.getChildren().filtered(node -> node instanceof ToolBar);
            if (!filteredList.isEmpty() && filteredList.size() == 1 && filteredList.get(0).equals(toolBar)) {
                toolBar.setVisible(false);
                mainView.getChildren().remove(toolBar);
                showToolBarMenuItem.setSelected(false);
            }
        }
    }

    private boolean toolBarHidden() {
        return toolBar.isVisible() == false;
    }

    @FXML
    private void onShowGridLines(ActionEvent event) {
        if (getGridLinesVisible()) {
            hideGridLines();
        } else {
            showGridLines();
        }
    }

    private void showGridLines() {
        log.debug("### entered showGridLines()");
        Platform.runLater(() -> {
            isGridLinesVisibleProperty.set(true);
            hexGridPane.setGridLinesVisible(true);
            showGridLinesMenuItem.setSelected(true);
            showHexGridDebugData();
        });
    }

    private void hideGridLines() {
        log.debug("### entered hideGridLines()");
        Platform.runLater(() -> {
            isGridLinesVisibleProperty.set(false);
            hexGridPane.setGridLinesVisible(false);
            showGridLinesMenuItem.setSelected(false);
            showHexGridDebugData();
        });
    }

    private boolean getGridLinesVisible() {
        return isGridLinesVisibleProperty.get();
    }

    private ColumnConstraints newColumnContraints() {
        ColumnConstraints columnConstraints = new ColumnConstraints(COLUMN_WIDTH);
        columnConstraints.setHgrow(Priority.ALWAYS);
        return columnConstraints;
    }

    private RowConstraints newRowConstraints() {
        RowConstraints rowConstraints = new RowConstraints(ROW_HEIGHT);
        rowConstraints.setVgrow(Priority.ALWAYS);
        return rowConstraints;
    }

    //
    //  DEBUG MODE CODE
    //
    private void addDebugData() {
        if (log.isDebugEnabled() && hexGridPane != null
            && hexGridPane.getChildren().isEmpty()
            && hexGridPane.getColumnConstraints().isEmpty()
            && hexGridPane.getRowConstraints().isEmpty()) {
            log.debug("### entered addDebugData()");
            log.debug("### debug is enabled and hexGridPane ready.");

            if (isGridLinesVisibleProperty.get() == true) {
                hexGridPane.setGridLinesVisible(false);
            }

            /*
             * Create some dummy data.
             */
            log.debug("### adding debug data...");
            for (int i = 0; i < NUM_COLUMNS; i++) {
                for (int j = 0; j < NUM_ROWS; j++) {
                    Text textNode = new Text("0F");
                    textNode.setFont(font);
                    hexGridPane.add(textNode, i, j);
                }
            }

            log.debug("### adding column width constraints...");
            for (int i = 0; i < hexGridPane.getColumnCount(); i++) {
                hexGridPane.getColumnConstraints().add(newColumnContraints());
            }

            log.debug("### adding row height constraints...");
            for (int i = 0; i < hexGridPane.getRowCount(); i++) {
                hexGridPane.getRowConstraints().add(newRowConstraints());
            }

            log.debug("### setting hexGridPane nodes with additional contstraints...");
            hexGridPane.getChildren().forEach((var node) -> {
                GridPane.setHgrow(node, Priority.NEVER);
                GridPane.setVgrow(node, Priority.ALWAYS);
                GridPane.setHalignment(node, HPos.CENTER);
                GridPane.setValignment(node, VPos.CENTER);
                GridPane.setFillHeight(node, Boolean.FALSE);
                GridPane.setFillWidth(node, Boolean.FALSE);
                log.debug("### rowIndex: {}", GridPane.getRowIndex(node));
                log.debug("### colIndex: {}", GridPane.getColumnIndex(node));

            });

            if (isGridLinesVisibleProperty.get() == true) {
                hexGridPane.setGridLinesVisible(true);
            }
        }
    }

    private void showHexGridDebugData() {
        if (log.isDebugEnabled()) {
            if (hexGridAnchorPane != null && hexGridScrollPane != null) {
                log.debug("### ===========================");
                log.debug("### showing hexGrid debug data:");
                log.debug("### ===========================");
                log.debug("###   hexGridAnchorPane.width : {}", hexGridAnchorPane.getWidth());
                log.debug("###   hexGridAnchorPane.height: {}", hexGridAnchorPane.getHeight());
                log.debug("###   hexGridScrollPane.width : {}", hexGridScrollPane.getWidth());
                log.debug("###   hexGridScrollPane.height: {}", hexGridScrollPane.getHeight());
                log.debug("");
                log.debug("###   hexGridAnchorPane.prefWidth  : {}", hexGridAnchorPane.getPrefWidth());
                log.debug("###   hexGridAnchorPane.prefHeight : {}", hexGridAnchorPane.getPrefHeight());
                log.debug("###   hexGridAnchorPane.boundsInLocal  : {}", hexGridAnchorPane.getBoundsInLocal());
                log
                    .debug("###   hexGridAnchorPane.boundsInParent : {}", hexGridAnchorPane
                        .getBoundsInParent());
                log.debug("###   hexGridScrollPane.prefWidth  : {}", hexGridScrollPane.getPrefWidth());
                log.debug("###   hexGridScrollPane.prefHeight : {}", hexGridScrollPane.getPrefHeight());
                log.debug("###   hexGridScrollPane.boundsInLocal  : {}", hexGridScrollPane.getBoundsInLocal());
                log
                    .debug("###   hexGridScrollPane.boundsInParent : {}", hexGridScrollPane
                        .getBoundsInParent());
                log.debug("###   hexGridPane                  : {}", hexGridPane);
                log.debug("###   hexGridPane.boundsInLocal    : {}", hexGridPane.getBoundsInLocal());
                log.debug("###   hexGridPane.boundsInParent   : {}", hexGridPane.getBoundsInParent());
                log.debug("###   hexGridPane.children.size    : {}", hexGridPane.getChildren().size());
                log.debug("###   hexGridPane.isGridLinesVisibe: {}", hexGridPane.isGridLinesVisible());

                log.debug("");
            }
        }
    }

    private void showMenuDebugInfo() {
        if (log.isDebugEnabled()) {
            log.debug("### ========================");
            log.debug("### showing Menu debug info:");
            log.debug("### ========================");
            fontViewMenu.getItems().forEach(menuItem -> {
                log.debug("###   menuItem: {}", menuItem);
            });
            log.debug("");
        }
    }

    private void showGeneralDebugInfo() {
        if (log.isDebugEnabled()) {
            log.debug("### ===========================");
            log.debug("### showing general debug info:");
            log.debug("### ===========================");
            log.debug("###   font        : {}", this.font);
            log.debug("###   fontSize    : {}", this.fontSize);
            log.debug("###   hexGrid     : {}", this.hexGridPane);
            log.debug("###   scene       : {}", this.scene);
            log.debug("###   scene.window: {}", this.scene.getWindow());
            log.debug("");
        }
    }

}
