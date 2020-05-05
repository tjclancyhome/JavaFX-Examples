/*
 * The MIT License
 *
 * Copyright 2019 tjc.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.tjc.jfx.jfxsandboxbasicfxmlapp;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSandboxBasicFxmlAppSceneController {

    //private static final Logger log = LoggerFactory.getLogger(JFXSandboxBasicFxmlAppSceneController.class);
    @FXML
    private VBox mainWindows;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ToolBar toolBar;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TabPane projectsTabPane;
    @FXML
    private AnchorPane projectsTab;
    @FXML
    private Tab filesTab;
    @FXML
    private Tab servicesTab;
    @FXML
    private Tab navigatorTab;
    @FXML
    private Pane statusBar;
    @FXML
    private TabPane editorTabsPane;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        if (!editorTabsPane.getTabs().isEmpty()) {
            editorTabsPane.getTabs().clear();
            if (!editorTabsPane.getTabs().isEmpty()) {

            }
        }
    }

    /**
     * <p>
     * dispose.</p>
     */
    public void dispose() {

    }

}
