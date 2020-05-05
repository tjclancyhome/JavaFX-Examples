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
package org.tjc.jfx.jfxfontexamples;

import java.util.Objects;
import javafx.scene.text.Font;
import org.slf4j.LoggerFactory;

/**
 * Represents the data of a Font. The idea is to have a structure for the TreeItem
 *
 * @author tjclancy
 */
public class FontInfo {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FontInfo.class);

    private final String familyName;
    private final String fontName;
    private final String style;
    private final double size;

    public FontInfo(final Font font) {
        this.familyName = font.getFamily();
        this.fontName = font.getName();
        this.style = font.getStyle();
        this.size = font.getSize();
    }

    public FontInfo(String familyName, String fontName, String style, double size) {
        this.familyName = familyName;
        this.fontName = fontName;
        this.style = style;
        this.size = size;
    }

    public static FontInfo newInstance(final Font font) {
        return new FontInfo(font);
    }

    public static FontInfo newInstance(String familyName, String fontName, String style,
            double size) {
        return new FontInfo(familyName, fontName, style, size);
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFontName() {
        return fontName;
    }

    public String getStyle() {
        return style;
    }

    public double getSize() {
        return size;
    }

    public Font makeFont() {
        Font font = new Font(fontName, size);
        log.trace("### font: {}", font);
        return font;
    }

    @Override
    public String toString() {
        return "FontInfo{" + "familyName=" + familyName + ", fontName=" + fontName + ", style=" + style + ", size=" + size + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.familyName);
        hash = 19 * hash + Objects.hashCode(this.fontName);
        hash = 19 * hash + Objects.hashCode(this.style);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.size) ^ (Double.doubleToLongBits(
                this.size) >>> 32));
        return hash;
    }

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
        final FontInfo other = (FontInfo) obj;
        if (Double.doubleToLongBits(this.size) != Double.doubleToLongBits(other.size)) {
            return false;
        }
        if (!Objects.equals(this.familyName, other.familyName)) {
            return false;
        }
        if (!Objects.equals(this.fontName, other.fontName)) {
            return false;
        }
        return Objects.equals(this.style, other.style);
    }


}
