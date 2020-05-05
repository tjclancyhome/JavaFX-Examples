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
package org.tjc.jfx.aceeditorexample.model;

import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class UndoManager {
    private static final Logger log = LoggerFactory.getLogger(UndoManager.class);

    private final JSObject jsoUndoManager;

    public UndoManager(JSObject jsoUndoManager) {
        this.jsoUndoManager = jsoUndoManager;
    }

    public void execute(Object options) {
        jsoUndoManager.call("execute", options);
    }

    public boolean hasRedo() {
        return (boolean) jsoUndoManager.call("hasRedo");
    }

    public boolean hasUndo() {
        return (boolean) jsoUndoManager.call("hasUndo");
    }

    public void redo(Boolean dontSelect) {
        jsoUndoManager.call("redo", dontSelect);
    }

    public void undo(Boolean dontSelect) {
        jsoUndoManager.call("undo", dontSelect);
    }

    public void reset() {
        jsoUndoManager.call("reset");
    }

    @Override
    public String toString() {
        return "UndoManager{" + "jsoUndoManager=" + jsoUndoManager + '}';
    }

}
