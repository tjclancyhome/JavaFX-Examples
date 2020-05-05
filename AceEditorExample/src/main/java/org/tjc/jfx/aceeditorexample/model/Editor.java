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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.event.Event;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Editor class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class Editor {
    private static final Logger log = LoggerFactory.getLogger(Editor.class);

    private final JSObject jsoEditor;
    private final EditSession editSession;
    private final WebView webView;
    private final Selection selection;

    /**
     * <p>
     * Constructor for Editor.</p>
     *
     * @param jsoEditor a JSObject object.
     * @param webView   the javafx webview.
     */
    public Editor(JSObject jsoEditor, WebView webView) {
        Objects.requireNonNull(jsoEditor, () -> "The jsoEditor argument is null.");
        Objects.requireNonNull(webView, () -> "The webView argument is null.");

        log.debug("### entered Editor(jsoEditor, webView)");

        this.jsoEditor = jsoEditor;
        log.debug("### jsoEditor: {}", jsoEditor);

        this.webView = webView;

        JSObject jsoSession = (JSObject) jsoEditor.call("getSession");
        log.debug("### jsoSession: {}", jsoSession);

        editSession = new EditSession(jsoSession, webView);
        log.debug("### editSession: {}", editSession);

        selection = editSession.getSelection();
        log.debug("### selection: {}", selection);
//        editSession.getJsoSession()
//        JSObject jsoSelection = (JSObject) jsoSession.call("getSelection");
//        selection = new Selection(jsoSelection, webView);
//        initEventListeners();

        log.debug("### exited Editor(jsoEditor, webView)");
    }

    private void initEventListeners() {
        jsoEditor.setMember("events", new Events(this));
    }

    /**
     * <p>
     * execCommand.</p>
     *
     * @param command a {@link java.lang.String} object.
     *
     * @return a boolean.
     *
     * @throws netscape.javascript.JSException if any.
     */
    public boolean execCommand(String command) throws JSException {
        return (boolean) jsoEditor.call("execCommand", command);
    }

    /**
     * Blur the current document contents.
     */
    public void blur() {
        log.debug("### entered blur()");
        jsoEditor.call("blur");
    }

    public void clearSelection() {
        log.debug("### entered clearSelection()");
        Object clearSelectionResult = jsoEditor.call("clearSelection");
        log.trace("###    clearSelectionResult: {}", clearSelectionResult);
        log.debug("### exited clearSelection()");
    }

    /**
     * <p>
     * Getter for the field <code>editSession</code>.</p>
     *
     * @return an EditSession instance.
     */
    public EditSession getEditSession() {
        log.debug("### entered getEditSession()");
        return editSession;
    }

    public WebView getWebView() {
        return webView;
    }

    public void fireEvent(Event event) {
        log.debug("### entered fireEvent(): event: {}", event);
        webView.fireEvent(event);
    }

    /**
     * Returns the document's contents as a String.
     *
     * @return the document's contents.
     */
    public String getValue() {
        log.debug("### entered getValue()");
        return (String) jsoEditor.call("getValue");
    }

    public Object getCursorPosition() {
        log.debug("### entered getCursorPosition()");
        Object obj = jsoEditor.call("getCursorPosition");
        log.trace("###    obj: {}", obj);
        log.debug("### exited getCursorPosition()");
        return obj;
    }

    public CursorPosition getCursorPositionScreen() {
        log.debug("### entered getCursorPositionScreen()");
        CursorPosition cursorPosition = new CursorPosition((JSObject) jsoEditor
            .call("getCursorPositionScreen"));
        log.trace("###    cursorPosition: {}", cursorPosition);
        return cursorPosition;
    }

    /**
     * <p>
     * setTheme.</p>
     *
     * @param theme a {@link java.lang.String} object.
     */
    public void setTheme(String theme) {
        log.debug("### entered setTheme(): theme: {}", theme);
        jsoEditor.call("setTheme", prefixTheme(theme));
    }

    /**
     * <p>
     * getTheme.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTheme() {
        log.debug("### entered getTheme()");
        return (String) jsoEditor.call("getTheme");
    }

    /**
     * <p>
     * setOption.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     *
     * @throws netscape.javascript.JSException if any.
     */
    public void setOption(String name, Object value) throws JSException {
        log.debug("### entered setOption(): name: {}, value: {}", name, value);
        jsoEditor.call("setOption", name, value);
    }

    /**
     * <p>
     * getOption.</p>
     *
     * @param optionName a {@link java.lang.String} object.
     *
     * @return a {@link java.lang.Object} object.
     */
    public Object getOption(String optionName) {
        return jsoEditor.call("getOption", optionName);
    }

    /**
     * <p>
     * getOptions.</p>
     *
     * @return a JSObject object.
     */
    public JSObject getOptions() {
        return (JSObject) jsoEditor.call("getOptions");
    }

    public String getFontFamily() {
        log.debug("### entered getFontFamily()");
        return (String) getOption("fontFamily");
    }

    public Selection getSelection() {
        return selection;
    }

    public int getFontSize() {
        log.debug("### entered getFontSize()");
        Object option = this.getOption("fontSize");
        log.trace("###    fontSize option: {}", option.getClass());
        return (int) getOption("fontSize");
    }

    public Font getEditorFont() {
        log.debug("### entered getEditorFont()");
        Font editorFont = Font.font(getFontFamily(), FontWeight.NORMAL, FontPosture.REGULAR,
            getFontSize());
        log.trace("###    editorFont: {}", editorFont);
        return editorFont;
    }

    public void setEditorFont(Font font) {
        setFontFamily(font.getFamily());
        setFontStyle(font.getStyle());
        setFontSize((int) font.getSize());
    }

    public void setFontFamily(String fontFamily) {
        log.debug("### entered setFontFamily(): fontFamily: {}", fontFamily);
        setOption("fontFamily", fontFamily);
    }

    public void setFontStyle(String fontStyle) {
        // This method is useless because it appears that Ace editor only allows 'fontFamily'
        // and 'fontSize'.
        log.debug("### entered setFontStyle(): fontStyle: {}", fontStyle);
//        setOption("fontStyle", fontStyle);
    }

    public void setStyle(String style) {
        log.debug("### entered setStyle(): style: {}", style);
        Object obj = jsoEditor.call("setStyle", style);
        log.debug("###    obj: {}, obj.class: {}", obj, obj.getClass());
    }

    public void setFontSize(int fontSize) {
        log.debug("### entered setFontSize(): fontSize: {}", fontSize);
        setOption("fontSize", fontSize);
    }

    public void setReadOnly(boolean readOnly) {
        log.debug("### entered setReadOnly(): readOnly: {}", readOnly);
        setOption("readOnly", readOnly);
    }

    /**
     * <p>
     * setScrollSpeed.</p>
     *
     * @param speedInMillis a int.
     */
    public void setScrollSpeed(int speedInMillis) {
        jsoEditor.call("setScrollSpeed", speedInMillis);
    }

    /**
     * <p>
     * navigateFileEnd.</p>
     */
    public void navigateFileEnd() {
        jsoEditor.call("navigateFileEnd");
    }

    /**
     * <p>
     * setAnimatedScroll.</p>
     */
    public void setAnimatedScroll() {
        jsoEditor.call("setAnimatedScroll");
    }

    /**
     * <p>
     * setBehavioursEnabled.</p>
     *
     * @param enabled a boolean.
     */
    public void setBehavioursEnabled(boolean enabled) {
        jsoEditor.call("setBehavioursEnabled", enabled);
    }

    /**
     * <p>
     * setValue.</p>
     *
     * @param val       a {@link java.lang.String} object.
     * @param cursorPos a int.
     */
    public void setValue(String val, int cursorPos) {
        jsoEditor.call("setValue", val, cursorPos);
    }

    /**
     * <p>
     * focus.</p>
     */
    public void focus() {
        jsoEditor.call("focus");
    }

    /**
     * <p>
     * moveCursorTo.</p>
     *
     * @param row    a int.
     * @param column a int.
     */
    public void moveCursorTo(int row, int column) {
        jsoEditor.call("moveCursorTo", row, column);
    }

    /**
     * <p>
     * toggleCommentLines.</p>
     */
    public void toggleCommentLines() {
        log.debug("### entered toggleCommentLines()");
        Object object = jsoEditor.call("toggleCommentLines");
        log.trace("###    object.type : {}", object.getClass());
        log.trace("###    object.value: {}", object);

    }

    /**
     * <p>
     * Getter for the field <code>jsoEditor</code>.</p>
     *
     * @return a JSObject object.
     */
    public JSObject getJsoEditor() {
        return jsoEditor;
    }

    /**
     * From AceFX Copyright 2015 Sudipto Chandra.
     * <p>
     * Gets a list of all properties of a JavaScript object.
     *
     * @param data The object to get properties.
     *
     * @return List of all properties of the object.
     */
    public ArrayList<String> getAllProperties(JSObject data) {
        ArrayList<String> propList = new ArrayList<>();
        var properties = (JSObject) data.eval("Object.getOwnPropertyNames(this);");
        for (var i = 0; i < (int) properties.eval("this.length"); ++i) {
            propList.add((String) properties.getSlot(i));
        }
        return propList;
    }

    /**
     * Uses getAllProperties on the supplied JSObject and uses an Editor instance to create a map
     * containing the property names and their values. This is no doubt a very inefficient way of
     * doing this but I still don't enough about JavaScript to make a single call that returns a
     * structure filled with each property and its value.
     *
     * @param jso a JSObject object.
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, Object> getPropertyMap(JSObject jso) {
        Map<String, Object> propertyMap = new HashMap<>();
        List<String> allProps = getAllProperties(jso);
        if (allProps.isEmpty() == false) {
            allProps.forEach((var prop) -> {
                var obj = getOption(prop);
                if (obj != null) {
                    propertyMap.put(prop, obj);
                }
            });
        }
        return propertyMap;
    }

    private static String prefixTheme(String theme) {
        return String.format("ace/theme/%s", theme);
    }

    @Override
    public String toString() {
        return "Editor{" + "jsoEditor=" + jsoEditor + ", editSession=" + editSession + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.jsoEditor);
        hash = 79 * hash + Objects.hashCode(this.editSession);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Editor other = (Editor) obj;
        if (!Objects.equals(this.jsoEditor, other.jsoEditor)) {
            return false;
        }
        return Objects.equals(this.editSession, other.editSession);
    }

}
