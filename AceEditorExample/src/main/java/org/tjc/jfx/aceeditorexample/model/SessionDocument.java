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
 * <p>
 * SessionDocument class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class SessionDocument {
    private static final Logger log = LoggerFactory.getLogger(SessionDocument.class);

    private final JSObject jsoDocument;

    /**
     * <p>
     * Constructor for SessionDocument.</p>
     *
     * @param jsoDocument a JSObject object.
     */
    public SessionDocument(JSObject jsoDocument) {
        this.jsoDocument = jsoDocument;
    }

    /**
     * <p>
     * getLength.</p>
     *
     * @return a int.
     */
    public int getLength() {
        log.debug("### entered getLength()");
        return (int) jsoDocument.call("getLength");
    }

    /**
     * <p>
     * getLines.</p>
     *
     * @param firstRow a int.
     * @param lastRow  a int.
     *
     * @return a JSObject object.
     */
    public JSObject getLines(int firstRow, int lastRow) {
        log.debug("### entered getLines(): firstRow: {}, lastRow: {}", firstRow, lastRow);
        Object o = jsoDocument.call("getLines", firstRow, lastRow);
        return (JSObject) o;
    }

    /**
     * <p>
     * getLine.</p>
     *
     * @param row a int.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLine(int row) {
        log.debug("### entered getLine(): row: {}", row);
        Object o = jsoDocument.call("getLine", row);
        return (String) o;
    }

    /**
     * <p>
     * getValue.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getValue() {
        log.debug("### entered getValue()");
        return (String) jsoDocument.call("getValue");
    }

    /**
     * <p>
     * Getter for the field <code>jsoDocument</code>.</p>
     *
     * @return a JSObject object.
     */
    public JSObject getJsoDocument() {
        return jsoDocument;
    }

}
