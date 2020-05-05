/*
 * The MIT License
 *
 * Copyright 2020 tjclancy.
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
package org.tjc.jfx.jfxcomponents.utils;

import java.util.List;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inspect and manage nodes in a scenegraph.
 *
 * @author tjclancy
 */
public class Nodes {
    private static final Logger log = LoggerFactory.getLogger(Nodes.class);

    private final Pane containerPane;

    public Nodes(Pane containerPane) {
        Objects.requireNonNull(containerPane, () -> "The 'containerPane' argument is null.");
        this.containerPane = containerPane;
    }

    public Node find(String id) {
        containerPane.getChildren().forEach(node -> log.debug("### node: {}, node id: {}",
            node, node.getId()));

        List<Node> filtered = containerPane.getChildren().filtered(node -> node.getId().contains(id));
        filtered.forEach(n -> log.debug("###     filtered child: {}, filtered child id: {}",
            n, n.getId()));
        return null;
    }

    public void dump() {
        containerPane.getChildren().forEach((var c) -> System.out.println(String.format("child: %s", c)));
    }

}
