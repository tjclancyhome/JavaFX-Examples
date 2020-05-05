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
package org.tjc.jfx.simplegraph;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.HTMLEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class ComplexPaneSceneController {
    private static final Logger log = LoggerFactory.getLogger(ComplexPaneSceneController.class);

    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private TitledPane applicationPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private Rectangle rectangleShape;
    @FXML
    private MenuBar menuBar;
    @FXML
    private HBox statusBar;
    @FXML
    private Circle circleShape;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");
        mainVBox.setPadding(Insets.EMPTY);
        applicationPane.setPadding(Insets.EMPTY);
        applicationPane.setContentDisplay(ContentDisplay.CENTER);
        applicationPane.setOnMouseEntered(event -> {
            log.debug("### entered titledpane onMouseEntered(): event: {}", event);
            event.consume();
            log.debug("### exited titledpane onMouseEntered():");

        });
        log.debug("### exited initialize()");

    }

    public static void hideHtmlEditorToolbars(final HTMLEditor editor) {
        log.debug("### entered hideHtmlEditorToolbars()");
        editor.setVisible(false);
        Platform.runLater(() -> {
            var nodes = editor.lookupAll(".tool-bar");
            nodes.forEach((Node node) -> {
                if (node != null) {
                    if (node instanceof ToolBar) {
                        ToolBar toolBar = (ToolBar) node;
                        toolBar.getChildrenUnmodifiable().forEach((var child) -> {
                            log.debug("###     toolBar child: {}", child);
                            if (child instanceof Parent) {
                                ((Parent) child).getChildrenUnmodifiable().forEach((var pc) -> {
                                    log.debug("###     child: {}", pc);
                                    if (pc instanceof Button) {
                                        Button b = (Button) pc;
                                        log.debug("###     button style class: {}",
                                                b.getStyleClass());
                                        b.setVisible(false);
                                        b.setManaged(false);
                                        log.debug("###     set visible and managed to false: node: {}", node);
                                    }
                                });
                            }
                        });
                    }
                }
            });
            editor.setVisible(true);
        });
        log.debug("### exiting hideHtmlEditorToolbars()");
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        log.debug("### entered onMouseClicked(): event: {}", event);
        event.consume();
        log.debug("### exited onMouseClicked(): event: {}", event);

    }
}
