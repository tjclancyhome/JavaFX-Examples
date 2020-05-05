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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * <p>
 * ExamplesTab class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class ExamplesTab extends Tab {

    private ScrollPane scrollPane;
    private AnchorPane anchorPane;

    private ObjectProperty<ScrollPane> scrollPaneProperty;
    private ObjectProperty<AnchorPane> anchorPaneProperty;

    /**
     * <p>
     * Constructor for ExamplesTab.</p>
     */
    public ExamplesTab() {
        this("ExamplesTab");
    }

    /**
     * <p>
     * Constructor for ExamplesTab.</p>
     *
     * @param text a {@link java.lang.String} object.
     */
    public ExamplesTab(String text) {
        super(text);
        initialize();
    }

    /**
     * <p>
     * Getter for the field <code>scrollPane</code>.</p>
     *
     * @return a {@link javafx.scene.control.ScrollPane} object.
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * <p>
     * Getter for the field <code>anchorPane</code>.</p>
     *
     * @return a {@link javafx.scene.layout.AnchorPane} object.
     */
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    /**
     * <p>
     * scrollPaneProperty.</p>
     *
     * @return a {@link javafx.beans.property.ObjectProperty} object.
     */
    public ObjectProperty<ScrollPane> scrollPaneProperty() {
        return scrollPaneProperty;
    }

    /**
     * <p>
     * anchorPaneProperty.</p>
     *
     * @return a {@link javafx.beans.property.ObjectProperty} object.
     */
    public ObjectProperty<AnchorPane> anchorPaneProperty() {
        return anchorPaneProperty;
    }

    private void initialize() {
        anchorPane = new AnchorPane();
        scrollPane = new ScrollPane(anchorPane);
        scrollPaneProperty = new SimpleObjectProperty<>(scrollPane);
        anchorPaneProperty = new SimpleObjectProperty<>(anchorPane);
        this.setContent(scrollPane);
    }

}
