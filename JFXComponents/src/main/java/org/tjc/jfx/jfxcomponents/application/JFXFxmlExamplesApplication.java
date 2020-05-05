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
package org.tjc.jfx.jfxcomponents.application;

import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All JFX Examples contain an Application class that extends javafx.application.Application. All
 * JFX examples that make use of FXML files should extend this abstract class because it has
 * convienience methods that all examples should use.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public abstract class JFXFxmlExamplesApplication extends BaseJfxExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXFxmlExamplesApplication.class);

    /**
     * TODO: Add comment.
     */
    public JFXFxmlExamplesApplication() {
        super();
        log.debug("### entered JFXFxmlExamplesApplication() constructor.");
        log.debug("### exited JFXFxmlExamplesApplication() constructor.");
    }

    /**
     * TODO: Add comment.
     *
     * @param scene the scene object.
     */
    public JFXFxmlExamplesApplication(Scene scene) {
        super(scene);
        log.debug("### entered JFXFxmlExamplesApplication(Scene) constructor.");
        log.debug("### exited JFXFxmlExamplesApplication(Scene) constructor.");
    }
}
