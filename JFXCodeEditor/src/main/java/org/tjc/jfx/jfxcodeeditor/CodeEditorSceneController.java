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
package org.tjc.jfx.jfxcodeeditor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class CodeEditorSceneController {

    @FXML
    private TextArea editorTexArea;
    @FXML
    private ToolBar editorRedoButton;
    @FXML
    private Button editorOpenButton;
    @FXML
    private Button editorSaveButton;
    @FXML
    private Button editorRefreshButton;
    @FXML
    private Button editorUndoButton;
    @FXML
    private Button editorCutButton;
    @FXML
    private Button editorCopyButton;
    @FXML
    private Button editorPasteButton;
    @FXML
    private Button editorFindButton;
    @FXML
    private Button editorReplaceButton;
    @FXML
    private Button editorOptionsButton;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
    }

}
