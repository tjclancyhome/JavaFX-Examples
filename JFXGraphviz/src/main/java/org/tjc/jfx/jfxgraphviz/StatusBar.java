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
package org.tjc.jfx.jfxgraphviz;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * <p>
 * StatusBar class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class StatusBar extends BorderPane {

    /**
     * <p>
     * Constructor for StatusBar.</p>
     */
    public StatusBar() {
        super();
        initialize(new Label("Left"), new Label("Right"));
    }

    /**
     * <p>
     * Constructor for StatusBar.</p>
     *
     * @param left  a {@link javafx.scene.Node} object.
     * @param right a {@link javafx.scene.Node} object.
     */
    public StatusBar(Node left, Node right) {
        initialize(left, right);
    }

    /**
     * <p>
     * getLeftNode.</p>
     *
     * @return a {@link javafx.scene.Node} object.
     */
    public Node getLeftNode() {
        return getLeft();
    }

    /**
     * <p>
     * getRightNode.</p>
     *
     * @return a {@link javafx.scene.Node} object.
     */
    public Node getRightNode() {
        return getRight();
    }

    private void initialize(Node left, Node right) {
        setLeft(left);
        setRight(right);

    }
}
