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
public enum GraphAttributes {
    ASPECT(""),
    BGCOLOR(""),
    CENTER(""),
    CLUSTER_RANK(""),
    COLOR(""),
    COLOR_SCHEME(""),
    COMMENT(""),
    COMPOUND(""),
    CONCENTRATE(""),
    DPI(""),
    FILL_COLOR(""),
    FONT_COLOR(""),
    FONT_NAME(""),
    FONT_NAMES(""),
    FONT_PATH(""),
    FONT_SIZE(""),
    ID(""),
    LABEL(""),
    LABEL_JUST(""),
    LABEL_LOC(""),
    LANDSCAPE(""),
    LAYERS(""),
    LAYER_SEP(""),
    LAYOUT(""),
    MARGIN(""),
    MINDIST(""),
    NODE_SEP(""),
    NO_JUSTIFY(""),
    ORDERING(""),
    ORIENTATION(""),
    OUTPUT_ORDER(""),
    PAGE(""),
    PAGE_DIR(""),
    PENC_OLOR(""),
    PEN_WIDTH(""),
    PERIPHERIES(""),
    RANK(""),
    RANK_DIR(""),
    RANK_SEP(""),
    RATIO(
        "'f' sets the aspect ratio to f which may be a floating point number, or one of the keywords fill, compress, or auto."),
    ROTATE(""),
    SAMPLE_POINTS(""),
    SEARCH_SIZE(""),
    SIZE("'x,y' the max bounding box of drawing in inches"),
    SPLINES(""),
    STYLE(""),
    STYLESHEET(""),
    TARGET(""),
    TOOLTIP(""),
    TRUECOLOR(""),
    VIEWPORT(""),
    URL("");

    private final String attributeDescription;

    GraphAttributes(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    public String attributeDescription() {
        return this.attributeDescription;
    }

    public String asCommandLineArgument() {
        return String.format("-G%s=", this.name().replace("_", "").toLowerCase());
    }

}
