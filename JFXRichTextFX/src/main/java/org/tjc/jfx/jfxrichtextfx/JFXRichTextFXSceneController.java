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
package org.tjc.jfx.jfxrichtextfx;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRichTextFXSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXRichTextFXSceneController.class);

    private JavaCodeAreaWrapper codeAreaWrapper;
    private Stage primaryStage;
    private FileChooser fileChooser;

    @FXML
    private VBox mainWindow;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ProgressBar loadProgressBar;
    @FXML
    private Pane codeAreaPlaceHolder;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem fileOpenMenuItem;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private HBox statusBar;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### Entered initialized.");
        codeAreaWrapper = new JavaCodeAreaWrapper();

        debugDisplayChildren(mainWindow);

        replaceInChildren(
                mainWindow,
                codeAreaPlaceHolder,
                codeAreaWrapper.getVirtualizedScrollPane());

        debugDisplayChildren(mainWindow);
    }

    private void initializeFileChooser() {
    }

    /**
     * I needed access to the stage for showing the open file dialog. Also, when this method is
     * called that
     * means all is initialized so I can add other this that need access to a Stage object... not
     * sure
     * anything other than the file chooses needs access to it though.
     *
     * @param primaryStage a {@link javafx.stage.Stage} object.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        /*
         * I obviously need to call something that opens the selected file.
         */
        fileOpenMenuItem.setOnAction(e -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"),
                    new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                    new ExtensionFilter("Source Code Files", "*.java", "*.xml"),
                    new ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(this.primaryStage);
            log.debug("### selectedFile: {}", selectedFile);
        });
    }

    private void replaceInChildren(Pane pane, Node toReplace, Node replacement) {
        log.debug(
                "### Entered replaceInChildren(Pane, Node, Node): pane: {}, toReplace: {}, replacement: {}",
                pane, toReplace, replacement);

        ObservableList<Node> list = pane.getChildren();
        int index = list.indexOf(toReplace);
        if (index != -1) {
            Node removed = list.remove(index);
            log.debug("### removed: {}", removed);
            list.add(index, replacement);
        }
    }

    private static void debugDisplayChildren(Pane pane) {
        pane.getChildrenUnmodifiable().forEach(child -> {
            log.debug("### child: {}", child);
        });
    }
}
