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
import org.junit.jupiter.api.Test;

/**
 *
 * @author tjclancy
 */
public class FontInfoTest {

    public FontInfoTest() {
    }

    /**
     * Test of newInstance method, of class FontInfo.
     */
    @Test
    public void testNewInstance_Font() {
        FontInfo fontInfo = FontInfo.newInstance(Font.font("American Typewriter", 36.0));
        System.out.println(fontInfo);
    }

    /**
     * Test of newInstance method, of class FontInfo.
     */
    @Test
    public void testNewInstance_4args() {
        FontInfo fontInfo = FontInfo.newInstance("American Typewriter",
                "American Typewriter Condensed Light", "Condensed Light", 36.0);
    }

    /**
     * Test of getFamilyName method, of class FontInfo.
     */
    @Test
    public void testGetFamilyName() {
    }

    /**
     * Test of getFontName method, of class FontInfo.
     */
    @Test
    public void testGetFontName() {
    }

    /**
     * Test of getStyle method, of class FontInfo.
     */
    @Test
    public void testGetStyle() {
    }

    /**
     * Test of getSize method, of class FontInfo.
     */
    @Test
    public void testGetSize() {
    }

    /**
     * Test of toString method, of class FontInfo.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of hashCode method, of class FontInfo.
     */
    @Test
    public void testHashCode() {
    }

    /**
     * Test of equals method, of class FontInfo.
     */
    @Test
    public void testEquals() {
    }

    /**
     * Test of makeFont method, of class FontInfo.
     */
    @Test
    public void testMakeFont() {


    }

}
