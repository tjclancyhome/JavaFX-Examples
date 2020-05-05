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
package org.tjc.jfx.simplegraph;

/**
 * Sample Skeleton for 'StageInspectorScene.fxml' Controller Class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class StageInspectorController {

    @FXML // fx:id="rootContainer"
    private VBox rootContainer; // Value injected by FXMLLoader

    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader

    @FXML // fx:id="scrollPane"
    private ScrollPane scrollPane; // Value injected by FXMLLoader

    void initialize() {
        assert rootContainer != null : "fx:id=\"rootContainer\" was not injected: check your FXML file 'StageInspectorScene.fxml'.";
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'StageInspectorScene.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'StageInspectorScene.fxml'.";

    }
}
