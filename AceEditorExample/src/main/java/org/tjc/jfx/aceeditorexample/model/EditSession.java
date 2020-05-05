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

import java.util.Objects;
import javafx.event.Event;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents ACE editor's EditSession object. It takes a JSObject that contains the
 * javascript
 * fuctions we can call using JSObject.call().
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class EditSession {
    private static final Logger log = LoggerFactory.getLogger(EditSession.class);

    private final JSObject jsoSession;
    private final SessionDocument sessionDocument;
    private final UndoManager undoManager;
    private final WebView webView;
    private final Selection selection;

    /**
     * <p>
     * Constructor for EditSession.</p>
     *
     * @param jsoSession a JSObject object.
     * @param webView    The javafx webview.
     */
    public EditSession(JSObject jsoSession, WebView webView) {
        Objects.requireNonNull(jsoSession, () -> "The jsoSession argument is null!");
        Objects.requireNonNull(webView, () -> "The webView argument is null!");

        log.debug("### entered EditSession(JSObject) contstructor.");
        this.jsoSession = jsoSession;
        JSObject jsoDocument = (JSObject) jsoSession.call("getDocument");
        this.sessionDocument = new SessionDocument(jsoDocument);
        JSObject jsoUndoManager = (JSObject) jsoSession.call("getUndoManager");
        undoManager = new UndoManager(jsoUndoManager);
        JSObject jsoSelection = (JSObject) jsoSession.call("getSelection");
        log.trace("###    jsoSelection: {}", jsoSelection);
        selection = new Selection(jsoSelection, webView);
        log.trace("###    selection: {}", selection);
        this.webView = webView;
        log.debug("### exited EditSession(JSObject) contstructor.");
    }

    /**
     * <p>
     * Getter for the field <code>sessionDocument</code>.</p>
     *
     * @return an instance of SessionDocument.
     */
    public SessionDocument getSessionDocument() {
        return sessionDocument;
    }

    public JSObject getJsoSession() {
        return jsoSession;
    }

    public Selection getSelection() {
        return selection;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public WebView getWebView() {
        return webView;
    }

    public void fireEvent(Event event) {
        log.debug("### entered fireEvent(): event: {}", event);
        webView.fireEvent(event);
    }

    /**
     * <p>
     * setMode.</p>
     *
     * @param mode a {@link java.lang.String} object.
     */
    public void setMode(String mode) {
        jsoSession.call("setMode", prefixMode(mode));
    }

    /**
     * <p>
     * setValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setValue(String value) {
        jsoSession.call("setValue", value);
    }

    /**
     * I pilfered this bit of code from AceFX source. Not quite sure why eval is required, and I have no idea
     * what ".$id means, but if you look at the Ace JavaScript api, a call to getMode() returns a
     * TextMode
     * object that was written by the Ace guys and I am not quite sure how to represent TextMode in
     * Java.
     *
     * @return The mode string (e.g. "ace/mode/java").
     */
    public String getMode() {
        return (String) jsoSession.eval("this.getMode().$id");
    }

    /**
     * <p>
     * getDocument.</p>
     *
     * @return a {@link java.lang.Object} object.
     */
    public Object getDocument() {
        return jsoSession.call("getDocument");
    }

    @Override
    public String toString() {
        return "EditSession{" + "jsoSession=" + jsoSession + ", sessionDocument=" + sessionDocument + ", undoManager=" + undoManager + '}';
    }

    private static String prefixMode(String mode) {
        return String.format("ace/mode/%s", mode);
    }

}
