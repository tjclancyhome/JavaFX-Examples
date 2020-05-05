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
package org.tjc.jfx.jfxhexeditor.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class MutableHexDataCell extends HexDataCell {
    private static final Logger log = LoggerFactory.getLogger(MutableHexDataCell.class);
    private final IntegerProperty hexByteIntegerProperty;

    public MutableHexDataCell(int hexByte) {
        log.debug("### entered MutableHexDataCell()");
        this.hexByteIntegerProperty = new SimpleIntegerProperty(hexByte);
    }

    @Override
    public int getHexByte() {
        log.debug("### entered getHexByte()");
        return hexByteIntegerProperty.get();
    }

    public void setHexByte(int hexByte) {
        log.debug("### entered setHexByte()");
        hexByteIntegerProperty.set(hexByte);
    }

    public IntegerProperty hexByteIntegerProperty() {
        log.debug("### entered hexByteIntegerProperty()");
        return hexByteIntegerProperty;
    }

}
