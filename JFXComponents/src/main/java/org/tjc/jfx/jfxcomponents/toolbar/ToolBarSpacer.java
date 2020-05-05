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
package org.tjc.jfx.jfxcomponents.toolbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 *
 * @author tjclancy
 */
public class ToolBarSpacer extends Pane {
    @SuppressWarnings("LeakingThisInConstructor")
    ToolBarSpacer() {
        super();
        HBox.setHgrow(this, Priority.SOMETIMES);
        setPrefHeight(1.0);
        setMinHeight(1.0);
        setMaxHeight(1.0);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    ToolBarSpacer(int size) {
        super();
        setPrefSize(size, 20);
        setMinSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
        setPrefHeight(1.0);
        setMinHeight(1.0);
        setMaxHeight(1.0);
    }

    public static ToolBarSpacer newFlexiSpacer() {
        return new ToolBarSpacer();
    }

    public static ToolBarSpacer newFizedSizeSpacer(int size) {
        return new ToolBarSpacer(size);
    }
}
