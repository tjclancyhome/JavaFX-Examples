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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;

import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class DotTest extends BaseUnitTest {

    public DotTest() {
    }

    @BeforeEach
    public void setup() {
        this.forceShowOutput();

    }

    @AfterEach
    public void tearDown() {
        writeln();
        this.restoreShowOutput();
    }

    /**
     * Test of addOutputFormat method, of class Dot.
     */
    @Test
    public void testAddOutputFormat() {
        writeBanner(methodName());

        Dot dot = new Dot();
        dot.addOutputFormat(OutputFormats.PNG);
        dot.addOutputFormat(OutputFormats.JPG);
        dot.getOutputFormatsArguments().forEach(arg -> writeln("{0}", arg));
    }

    /**
     * Test of debugDumpOutputFormatsEnum method, of class Dot.
     */
    @Test
    public void testDumpOutputFormats() {
        writeBanner(methodName());

        Dot dot = new Dot();
        dot.debugDumpOutputFormatsEnum();
    }

    /**
     * Test of debugDumpGraphAttributesEnum method, of class Dot.
     */
    @Test
    public void testDumpGraphAttributes() {
        writeBanner(methodName());

        Dot dot = new Dot();
        dot.debugDumpGraphAttributesEnum();
    }

    /**
     * Test of addGraphAttribute method, of class Dot.
     */
    @Test
    public void testAddGraphAttribute() {
    }

    /**
     * Test of getGraphAttributeArguments method, of class Dot.
     */
    @Test
    public void testGetGraphAttributeArguments() {
    }

    /**
     * Test of debugDumpOutputFormatsEnum method, of class Dot.
     */
    @Test
    public void testDebugDumpOutputFormatsEnum() {
    }

    /**
     * Test of debugDumpGraphAttributesEnum method, of class Dot.
     */
    @Test
    public void testDebugDumpGraphAttributesEnum() {
    }

    /**
     * Test of getOutputFormatsArguments method, of class Dot.
     */
    @Test
    public void testGetOutputFormatsArguments() {
    }

}
