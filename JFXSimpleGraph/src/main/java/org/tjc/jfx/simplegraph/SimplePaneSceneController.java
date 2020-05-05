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

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class SimplePaneSceneController {

    private static final Logger log = LoggerFactory.getLogger(SimplePaneSceneController.class);
    @FXML
    private TitledPane applicationPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ToolBar toolBar;
    @FXML
    private BorderPane statusBar;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");

        mainVBox.setPadding(Insets.EMPTY);
        applicationPane.setPadding(Insets.EMPTY);

        log.debug("### exited initialize()");
    }

}
