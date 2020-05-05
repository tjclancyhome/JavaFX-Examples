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
package org.tjc.jfx.jfxsamplesanddemos;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Not a very good StatusBar implementation. But it works for the demo UI.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class StatusBar extends BorderPane {

    private ObjectProperty<HBox> leftContainerProperty;
    private ObjectProperty<HBox> rightContainerProperty;

    /**
     * Status bar contructore which calls super() then initializes all the status bar parts.
     */
    public StatusBar() {
        super();
        init();
    }

    /**
     * Return the leftControlContainer property which you can use to attach listeners.
     *
     * @return a {@link javafx.beans.property.ObjectProperty} object.
     */
    public ObjectProperty<HBox> leftControlContainerProperty() {
        return leftContainerProperty;
    }

    /**
     * Return the rightControlContainer property which you can use to attach listeners.
     *
     * @return a {@link javafx.beans.property.ObjectProperty} object.
     */
    public ObjectProperty<HBox> rightControlContainerProperty() {
        return rightContainerProperty;
    }

    /**
     * Get the actual left container which is nothing more than an HBox.
     *
     * @return a {@link javafx.scene.layout.HBox} object.
     */
    public HBox getLeftContainer() {
        return leftContainerProperty.get();
    }

    /**
     * Get the actual right container which is nothing more than an HBox.
     *
     * @return a {@link javafx.scene.layout.HBox} object.
     */
    public HBox getRightContainer() {
        return rightContainerProperty.get();
    }

    /**
     * Add a node to the left container;
     *
     * @param node a {@link javafx.scene.Node} object.
     *
     * @return a boolean.
     */
    public boolean addNodeToLeftContainer(Node node) {
        return leftContainerProperty.get().getChildren().add(node);
    }

    /**
     * Add node to right container.
     *
     * @param node a {@link javafx.scene.Node} object.
     *
     * @return a boolean.
     */
    public boolean addNodeToRightContainer(Node node) {
        return rightContainerProperty.get().getChildren().add(node);
    }

    private void init() {
        HBox leftContainer = new HBox();
        leftContainer.setAlignment(Pos.CENTER_LEFT);
        leftContainer.setFillHeight(true);

        leftContainerProperty = new SimpleObjectProperty<>(leftContainer);

        HBox righContainer = new HBox();
        righContainer.setAlignment(Pos.CENTER_LEFT);
        righContainer.setFillHeight(true);

        rightContainerProperty = new SimpleObjectProperty<>(righContainer);

        setLeft(leftContainer);
        setRight(righContainer);

        setPadding(new Insets(5));
        setPrefHeight(25);
    }

}
