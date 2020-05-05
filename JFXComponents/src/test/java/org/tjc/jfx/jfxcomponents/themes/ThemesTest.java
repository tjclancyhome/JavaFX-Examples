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
package org.tjc.jfx.jfxcomponents.themes;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class ThemesTest {

    public ThemesTest() {
    }

    @AfterEach
    public void tearDown() {
        writeln();
    }

    /**
     * Test of setDarkMode method, of class Themes.
     */
    @Disabled("Because I have no JavaFX Scene setup, I'm not sure how to test this method ")
    @Test
    public void testSetDarkMode_boolean_Scene() {
        writeBanner(methodName());
    }

    /**
     * Test of setDarkMode method, of class Themes.
     */
    @Disabled("Because I have no JavaFX Scene setup, I'm not sure how to test this method ")
    @Test
    public void testSetDarkMode_3args() {
        writeBanner(methodName());
    }

    /**
     * Test of setTheme method, of class Themes.
     */
    @Disabled("Because I have no JavaFX Scene setup, I'm not sure how to test this method ")
    @Test
    public void testSetTheme() {
        writeBanner(methodName());
    }

    /**
     * Test of formatThemeName method, of class Themes.
     */
    @Test
    public void testFormatThemeName() {
        writeBanner(methodName());
        String expected = "/css/themes/dark-theme.css";
        String actual = Themes.formatThemeName(Themes.DEFAULT_CSS_THEMES_ROOT, "dark-theme.css");
        assertEquals(expected, actual);
        writeln("expected: \"{0}\" == actual: \"{1}\"", expected, actual);
    }

    /**
     * Test of formatThemeNameWithDefaultRoot method, of class Themes.
     */
    @Test
    public void testFormatThemeNameWithDefaultRoot() {
        writeBanner(methodName());
        String expected = "/css/themes/dark-theme.css";
        String actual = Themes.formatThemeNameWithDefaultRoot("dark-theme.css");
        assertEquals(expected, actual);
        writeln("expected: \"{0}\" == actual: \"{1}\"", expected, actual);
    }

}
