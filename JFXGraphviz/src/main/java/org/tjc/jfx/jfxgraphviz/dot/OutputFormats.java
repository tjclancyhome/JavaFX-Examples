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
package org.tjc.jfx.jfxgraphviz.dot;

/**
 *
 * @author tjclancy
 */
public enum OutputFormats {
    BMP("Windows bitmap format"),
    CANON("CANON format"),
    CMAPX("CMAPX format"),
    DOT("DOT format"),
    FIG("FIG format"),
    GD("GD format"),
    IMAP("IMAP format"),
    PDF("PDF format"),
    PS("PS format"),
    PS2("PS2 format"),
    PNG("PNG format"),
    JPG("JPG format"),
    SVG("SVG format"),
    VRML("VRML format"),
    WBMP("WBMP format"),
    PLAIN("PLAIN format");

    String formatDescription;

    private OutputFormats(String formatDescription) {
        this.formatDescription = formatDescription;
    }

    public String formatDescription() {
        return formatDescription;
    }

    public String asCommandLineArgument() {
        return String.format("-T%s", this.name().replace("_", "").toLowerCase());
    }

}
