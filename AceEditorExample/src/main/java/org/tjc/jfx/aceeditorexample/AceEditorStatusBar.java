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
package org.tjc.jfx.aceeditorexample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.aceeditorexample.model.CursorPosition;
import org.tjc.jfx.jfxcomponents.statusbar.StatusBar;

/**
 * <p>
 * AceEditorStatusBar class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class AceEditorStatusBar extends StatusBar {
    private static final Logger log = LoggerFactory.getLogger(AceEditorStatusBar.class);

    private HBox lineCountControl;
    private HBox cursorLocationControl;
    private Label lineCountDisplay;
    private Label lineCountLabel;
    private IntegerProperty intLineCountProperty;
    private StringProperty lineCountProperty;
    private Label cursorLocationLabel;
    private Label cursorLocationDisplay;
    private final IntegerProperty cursorLineNumberProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty cursorRowNumberProperty = new SimpleIntegerProperty(0);

    /**
     * <p>
     * Constructor for AceEditorStatusBar.</p>
     */
    public AceEditorStatusBar() {
        super();
        init();
    }

    /**
     * Return the lineCountProperty which is used to keep track of the number of lines in the editor
     * session.
     *
     * @return The line count.
     */
    public StringProperty lineCountProperty() {
        log.debug("### entered lineCountProperty()");
        return lineCountProperty;
    }

    public IntegerProperty cursorLineNumberProperty() {
        return cursorLineNumberProperty;
    }

    public IntegerProperty cursorRowNumberProperty() {
        return cursorRowNumberProperty;
    }

    public void updateCursorPosition(CursorPosition cursorPosition) {
        updateStatusBarCursorLocation(cursorPosition);
    }

    /**
     * <p>
     * intLineCountProperty.</p>
     *
     * @return a {@link javafx.beans.property.IntegerProperty} object.
     */
    public IntegerProperty intLineCountProperty() {
        log.debug("### entered intLineCountProperty()");
        return intLineCountProperty;
    }

    private void init() {
        log.debug("### entered init()");

        addLineCountControl();
        addCursorLocationControl();
    }

    private void updateStatusBarCursorLocation(CursorPosition cursorPosition) {
        String lineColumnStr = String
            .format("%d:%d", cursorPosition.getRow() + 1, cursorPosition.getColumn() + 1);
        cursorLocationDisplay.setText(lineColumnStr);
    }

    private void addCursorLocationControl() {
        cursorLocationControl = new HBox();
        cursorLocationControl.setSpacing(2);
        cursorLocationControl.setPadding(new Insets(5));

        cursorLocationLabel = new Label("");

        HBox.setHgrow(cursorLocationLabel, Priority.ALWAYS); // maybe should be NEVER?

        cursorLocationDisplay = new Label();
        cursorLocationDisplay.setPrefWidth(35);
        cursorLocationDisplay.setText("");
        cursorLocationDisplay.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(cursorLocationDisplay, Priority.ALWAYS); // maybe should be NEVER?
        cursorLocationControl.getChildren().addAll(cursorLocationLabel, cursorLocationDisplay);

        this.addNodeToRightContainer(cursorLocationControl);
    }

    private void addLineCountControl() {
        this.intLineCountProperty = new SimpleIntegerProperty(0);
        this.lineCountProperty = new SimpleStringProperty("0");

        /*
         * This is a control that displays the current line count.
         *
         * TODO: emulate Netbeans's line:col display as well.
         */
        lineCountControl = new HBox();
        lineCountControl.setSpacing(2.0);
        lineCountControl.setPadding(new Insets(5));

        lineCountLabel = new Label("Line Count:");
        HBox.setHgrow(lineCountLabel, Priority.SOMETIMES); // maybe should be NEVER?

        lineCountDisplay = new Label();
        lineCountDisplay.setPrefWidth(35);
        lineCountDisplay.setText("0");
        lineCountDisplay.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(lineCountDisplay, Priority.SOMETIMES); // maybe should be NEVER?

        lineCountControl.getChildren().addAll(lineCountLabel, lineCountDisplay);

        addNodeToRightContainer(lineCountControl);

        /*
         * TODO: figure how to allow a label's text property to bind with an IntegerProperty that
         * keeps track
         * of the total number of lines in the edit session.
         */
        lineCountDisplay.textProperty().bind(lineCountProperty);

        /*
         * Triggered whenever a change in the number of lines changes.
         */
        intLineCountProperty.addListener((var obsv, var previousLineCount, var newLineCount) -> {
            log.debug(
                "### lineCountProperty.ChangeListener: obsv: {}, oldLineCount: {}, newLineCount: {}",
                obsv, previousLineCount, newLineCount);
            lineCountProperty.setValue(newLineCount.toString());
        });
    }

}
