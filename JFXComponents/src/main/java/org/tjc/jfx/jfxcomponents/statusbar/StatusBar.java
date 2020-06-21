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
package org.tjc.jfx.jfxcomponents.statusbar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Not a very good StatusBar implementation. But it works for the demo UI.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class StatusBar extends BorderPane {

    private ObjectProperty<HBox> leftContainerProperty;
    private ObjectProperty<HBox> centerContainerProperty;
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
     * @return an ObjectProperty.
     */
    public ObjectProperty<HBox> leftControlContainerProperty() {
        return leftContainerProperty;
    }

    /**
     * Return the centerControlContainer property which you can use to attach listeners.
     *
     * @return an ObjectProperty
     */
    public ObjectProperty<HBox> centerControlContainerProperty() {
        return centerContainerProperty;
    }

    /**
     * Return the rightControlContainer property which you can use to attach listeners.
     *
     * @return an ObjectProperty.
     */
    public ObjectProperty<HBox> rightControlContainerProperty() {
        return rightContainerProperty;
    }

    /**
     * Get the actual left container which is nothing more than an HBox.
     *
     * @return the status bar's left side container which is simply an HBox.
     */
    public HBox getLeftContainer() {
        return leftContainerProperty.get();
    }

    /**
     * Get the actual center container which is nothing more than an HBox.
     *
     * @return Center container (an HBox).
     */
    public HBox getCenterContainer() {
        return centerContainerProperty.get();
    }

    /**
     * Get the actual right container which is nothing more than an HBox.
     *
     * @return the status bar's right side container which is simply an HBox.
     */
    public HBox getRightContainer() {
        return rightContainerProperty.get();
    }

    /**
     * Add a node to the left container;
     *
     * @param node A JavaFX node that we want to add to a statusbar container.
     *
     * @return true if adding a node to a status bar container succeeds, otherwise return false.
     */
    public boolean addNodeToLeftContainer(Node node) {
        return leftContainerProperty.get().getChildren().add(node);
    }

    /**
     * Add a node to the center container.
     *
     * @param node A JavaFX node that we want to add to a statusbar container.
     *
     * @return true if adding a node to a status bar container succeeds, otherwise return false.
     */
    public boolean addNodeToCenterContainer(Node node) {
        return centerContainerProperty.get().getChildren().add(node);
    }

    /**
     * Add node to right container.
     *
     * @param node A JavaFX node that we want to add to a statusbar container.
     *
     * @return true if adding a node to a status bar container succeeds, otherwise return false.
     */
    public boolean addNodeToRightContainer(Node node) {
        rightContainerProperty.get().getChildren().add(0, node);
        return true;
    }

    private void init() {
        VBox.setVgrow(this, Priority.NEVER);
        this.setPadding(new Insets(5));

        HBox leftContainer = new HBox(10);
        leftContainer.setAlignment(Pos.CENTER_LEFT);
        leftContainer.setFillHeight(true);
        //leftContainer.setPadding(new Insets(2, 5, 2, 5));
        this.setLeft(leftContainer);
        leftContainerProperty = new SimpleObjectProperty<>(leftContainer);

        HBox centerContainer = new HBox(10);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setFillHeight(true);
        //centerContainer.setPadding(new Insets(2, 5, 2, 5));
        this.setCenter(centerContainer);
        centerContainerProperty = new SimpleObjectProperty<>(centerContainer);

        HBox rightContainer = new HBox(10);
        rightContainer.setSpacing(10);
        rightContainer.setAlignment(Pos.CENTER_RIGHT);
        rightContainer.setFillHeight(true);
        //rightContainer.setPadding(new Insets(2, 5, 2, 5));
        this.setRight(rightContainer);
        rightContainerProperty = new SimpleObjectProperty<>(rightContainer);

//        setMinHeight(32);
//        setPrefHeight(32);
//        setMaxHeight(32);
    }

}
