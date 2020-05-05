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

import static java.lang.String.format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
abstract public class HexDataCell {
    private static final Logger log = LoggerFactory.getLogger(HexDataCell.class);

    private String hexByteString;

    public HexDataCell() {
        log.debug("### entered HexDataCell()");
        this.hexByteString = null;
    }

    public static ImmutableHexDataCell newImmutableHexDataCell(int hexByte) {
        log.debug("### entered newImmutableHexDataCell()");
        return new ImmutableHexDataCell(hexByte);
    }

    public static MutableHexDataCell newMutableHexDataCell(int hexByte) {
        log.debug("### entered newMutableHexDataCell()");
        return new MutableHexDataCell(hexByte);
    }

    public abstract int getHexByte();

    public String getHexByteString() {
        log.debug("### entered getHexByteString()");
        return formatHexByte();
//        if (hexByteString == null) {
//            hexByteString = formatHexByte();
//        }
//        return hexByteString;
    }

    public String toHex(int i) {
        log.debug("### entered toHex()");
        return format("%x", i);
    }

    public String toUpper(String s) {
        log.debug("### entered toUpper()");
        return s.toUpperCase();
    }

    public String replace(String s, char target, char replacement) {
        log.debug("### entered replace()");
        return s.replace(target, replacement);
    }

    @Override
    public String toString() {
        return "HexDataCell{" + "hexByteString=" + hexByteString + '}';
    }

    private String formatHexByte() {
        log.debug("### entered formatHexByte()");
        return toUpper(replace(format("%2s", toHex(getHexByte())), ' ', '0'));
    }
}
