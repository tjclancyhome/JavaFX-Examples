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
package org.tjc.jfx.aceeditorexample.model;

import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class Selection {
    private static final Logger log = LoggerFactory.getLogger(Selection.class);

    private final JSObject jsoSelection;
    private final WebView webView;
    private long cursorPosition;

    public Selection(JSObject jsoSelection, WebView webView) {
        log.debug("### entered Selection(...)");
        this.jsoSelection = jsoSelection;
        log.debug("### jsoSelection: {}", this.jsoSelection);
        this.webView = webView;
        log.debug("### webView: {}", this.webView);
    }

    public JSObject getJsoSelection() {
        return jsoSelection;
    }

    public WebView getWebView() {
        return webView;
    }

    public CursorPosition getCursor() {
        CursorPosition cursorPosition = new CursorPosition((JSObject) jsoSelection.call("getCursor"));
        return cursorPosition;
    }

    public void clearSelection() {
        log.debug("### entered clearSelection()");
        Object clearSelectionResult = jsoSelection.call("clearSelection");
        log.trace("###    clearSelectionResult: {}", clearSelectionResult);
        log.debug("### exited clearSelection()");
    }

}
