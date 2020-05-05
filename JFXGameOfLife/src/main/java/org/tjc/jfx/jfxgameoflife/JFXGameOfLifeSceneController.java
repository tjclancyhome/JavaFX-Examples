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
package org.tjc.jfx.jfxgameoflife;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.border.BordersManager;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXGameOfLifeSceneController {
    private static final Logger log = LoggerFactory.getLogger(JFXGameOfLifeSceneController.class);
    @FXML
    private MenuItem fileMenuItemQuit;
    @FXML
    private VBox golLayoutContainer;
    @FXML
    private ScrollPane golAnchorScrollPane;
    @FXML
    private Menu developerMenu;
    @FXML
    private CheckMenuItem showBordersMenuItem;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu viewMenu;
    @FXML
    private AnchorPane leftSideAnchor;

    private Scene scene;
    private GameOfLifePanelControl golPanelController;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");
        menuBar.setUseSystemMenuBar(true);

        golPanelController = new GameOfLifePanelControl();
        golPanelController.setStyle("");

        golPanelController.widthProperty().addListener((var obsv, var oldVal, var newVal) -> {
            log.debug("### widthProperty.listener(): oldVal: {}, newVal: {}", oldVal, newVal);
        });

        golLayoutContainer.setAlignment(Pos.CENTER);
        golLayoutContainer.setStyle("-fx-background-color:WHITE;");
        golLayoutContainer.getChildren().add(golPanelController);

        golAnchorScrollPane.setPadding(new Insets(10));
        golAnchorScrollPane.setFitToHeight(true);
        golAnchorScrollPane.setFitToWidth(true);
        golAnchorScrollPane.setContent(golPanelController);

        viewMenu.getItems().forEach(System.out::println);

        log.debug("### exited initialize()");
    }

    public void setScene(Scene scene) {
        log.debug("### entered setScene(Scene)");
        this.scene = scene;
        log.debug("### exited setScene(Scene)");
    }

    @FXML
    private void onPrintLifeGridInfo(ActionEvent event) {

    }

    @FXML
    private void onQuitApplication(ActionEvent event) {
        log.debug("### entered onQuitApplication(ActionEvent): event.source: {}", event.getSource());
        Platform.exit();
        log.debug("### exited onQuitApplication(ActionEvent)");
    }

    @FXML
    void onShowBorders(ActionEvent event) {
        if (showBordersMenuItem.isSelected()) {
            BordersManager.getInstance().showBorders();
        } else {
            BordersManager.getInstance().hideBorders();
        }
    }
}
