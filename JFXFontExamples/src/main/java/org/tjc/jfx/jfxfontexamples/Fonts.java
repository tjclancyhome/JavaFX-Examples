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

import javafx.scene.text.Font;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class Fonts {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Fonts.class);


    public static Font createFont(String fontName, double fontSize) {
        log.trace("### entered createFont(): fontName: {}, fontSize: {}", fontName, fontSize);
        Font font = new Font(fontName, fontSize);

        //FontCssMetaData<Text> cssMetaData = new FontCssMetaData<Text>("-fx-font", Font.getDefault());

        if (!fontName.equals(font.getName()) && font.getName().equals("System Regular")) {
            log.trace("!!! font requested: {} is not what was created: {}",
                    fontName, font.getName());
            if (fontName.endsWith("Regular")) {
                fontName = fontName.replace("Regular", "");
            } else if (!fontName.contains("Regular")) {
                fontName = fontName + " Regular";
            }
            font = new Font(fontName, fontSize);
        }
        log.trace("### created font: {}", font);
        log.trace("### exiting createFont()");
        return font;
    }

    public static Font resizeFont(Font font, double size) {
        return new Font(font.getName(), size);

    }

    private Fonts() {
    }
}
