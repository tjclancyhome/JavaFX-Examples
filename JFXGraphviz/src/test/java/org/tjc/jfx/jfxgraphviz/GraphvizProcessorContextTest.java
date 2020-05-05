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
package org.tjc.jfx.jfxgraphviz;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayName("A special case.")
public class GraphvizProcessorContextTest {

    public GraphvizProcessorContextTest() {
    }

    /**
     * Test of getOutputFormat method, of class GraphvizCommandLineArgs.
     */
    @Test
    @DisplayName("Tests if the default OutputFormat is OutputFormat.PNG")
    public void testGetOutputFormat() {
        writeBanner(methodName());
        GraphvizCommandLineArgs context = new GraphvizCommandLineArgs();
        /*
         * The default output format when none passed in is PNG
         */
        assertEquals(context.getOutputFormat(), GraphvizCommandLineArgs.OutputFormats.PNG);
    }

    /**
     * Test of setOutputFormat method, of class GraphvizCommandLineArgs.
     */
    @Test
    @DisplayName("Setting a new OutputFormat should return the same.")
    public void testSetOutputFormat() {
        writeBanner(methodName());
    }

    @Test
    public void test_Display_Name_Generation() {
        writeBanner(methodName());
    }

    @Test
    public void testDisplayNameGeneration2() {
        writeBanner(methodName());

    }

}
