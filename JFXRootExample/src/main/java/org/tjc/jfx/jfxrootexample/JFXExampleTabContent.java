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
package org.tjc.jfx.jfxrootexample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A custom component the defines the content of a Tab in a TabPane.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXExampleTabContent extends ScrollPane {

    private static final Logger log = LoggerFactory.getLogger(JFXExampleTabContent.class);

    @FXML
    private Button addNameButton;
    @FXML
    private TextField enterNameTextField;
    @FXML
    private ListView<String> nameListView;

    /**
     * <p>
     * Constructor for JFXExampleTabContent.</p>
     *
     * @throws java.io.IOException if any.
     */
    public JFXExampleTabContent() throws IOException {
        log.debug("### entered JFXExamplesTabContent()");
        init();
    }

    /**
     * <p>
     * newInstance.</p>
     *
     * @return a {@link org.tjc.jfx.jfxrootexample.JFXExampleTabContent} object.
     *
     * @throws java.io.IOException if any.
     */
    public static JFXExampleTabContent newInstance() throws IOException {
        return new JFXExampleTabContent();
    }

    /**
     * <p>
     * initialize.</p>
     */
    @FXML
    public void initialize() {
        log.debug("### entered initialize()");

        nameListView.itemsProperty().addListener((obsv, oldVal, newVal) -> {
            log.debug("### itemsProperty.listener: old: {}, new: {}", oldVal, newVal);
        });

        enterNameTextField.setOnKeyPressed((var keyEvent) -> {
            log.trace("### onKeyPressed: {}", keyEvent);
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                String text = enterNameTextField.getText();
                /*
                 * I doubt that textfield.getText() return null at any point but to be sure I check
                 * for it.
                 *
                 * TODO: Check javadoc to see if getText() might return null.
                 */
                if (text != null && !text.isBlank()) {
                    nameListView.getItems().add(text);
                    enterNameTextField.clear();
                }
            }
            keyEvent.consume();
        });

        addNameButton.setOnAction((var actionEvent) -> {
            log.trace("### addNameButton.onAction event: {}", actionEvent);
            var text = enterNameTextField.getText();
            if (text != null && !text.isBlank()) {
                nameListView.getItems().add(text);
                enterNameTextField.clear();
                actionEvent.consume();
            }
        });
    }

    private void init() throws IOException {
        var fxmlLoader = new FXMLLoader(JFXExampleTabContent.class.getResource(
                "JFXExampleTabContent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

}
