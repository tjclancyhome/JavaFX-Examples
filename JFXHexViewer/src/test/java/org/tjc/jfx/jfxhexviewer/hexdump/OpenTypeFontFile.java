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
package org.tjc.jfx.jfxhexviewer.hexdump;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class OpenTypeFontFile {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OpenTypeFontFile.class);

    private static final String READ_ONLY = "r";

    private final File fontFile;

    public OpenTypeFontFile() {
        this.fontFile = null;
    }

    public OpenTypeFontFile(File file) {
        Objects.requireNonNull(file, () -> "The 'file' argument is null.");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("the file '%s' does not exist.", file.toString()));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("the 'file' argument is not a file.", file
                .toString()));
        }
        this.fontFile = file;
    }

    public RandomAccessFile openFontFile() throws FileNotFoundException {
        return new RandomAccessFile(fontFile, READ_ONLY);
    }

    public void debugShowAwtFontInfo() throws FontFormatException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("### entered debugShowAwtFontInfo()");
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(
                "/fonts/BIRTH OF A HERO.ttf"));
            // Font font = new Font("Menlo", java.awt.Font.BOLD | java.awt.Font.ITALIC, 14);
            log.debug("    ### java.awt.font: {}", font);
            log.debug("    ### java.awt.font.family: {}", font.getFamily());
            log.debug("    ### java.awt.font.fontName: {}", font.getFontName());
            log.debug("    ### java.awt.font.name: {}", font.getName());
            log.debug("    ### java.awt.font.psname: {}", font.getPSName());
            log.debug("    ### java.awt.font.size: {}", font.getSize());
            log.debug("    ### java.awt.font.style: {}", font.getStyle());
            log.debug("    ### java.awt.font.attributes: {}", font.getAttributes());
            log.debug("    ### java.awt.font.toString(): {}", font.toString());
        }
    }

}
