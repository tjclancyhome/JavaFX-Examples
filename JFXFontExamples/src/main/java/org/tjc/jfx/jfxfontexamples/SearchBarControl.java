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
package org.tjc.jfx.jfxfontexamples;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class SearchBarControl extends GridPane {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SearchBarControl.class);

    @FXML
    private Label searchBarLabel;
    @FXML
    private TextField searchTextField;

    @SuppressWarnings("LeakingThisInConstructor")
    public SearchBarControl() {
        log.trace("### entered SearchBarControl() constructor");

        URL fxmlUrl = FontDisplayControl.class.getResource("/fxml/SearchBarControl.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            log.trace("### fxmlLoader successful!");
        } catch (IOException ex) {
            log.error("### Caught exception loading fxml file: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
        log.trace("### exiting SearchBarControl() constructor.");
    }


    /**
     * Initializes the controller class.
     */
    private void initialize() {
        log.trace("### entered initialize()");
        searchBarLabel.setPadding(new Insets(0, 0, 0, 5));
        searchTextField.setPadding(new Insets(0, 5, 0, 5));

        log.trace("### exited initialize()");
    }

    public Label getSearchBarLabel() {
        return searchBarLabel;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

}
