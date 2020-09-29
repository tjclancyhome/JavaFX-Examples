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
public class JDotTest extends BaseUnitTest {

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
     * Test of getInputFileName method, of class JDot.
     */
    @Test
    public void testGetInputFileName() {
        writeBanner(methodName());

        JDot jdot = new JDot();
        writeln("jdot: {0}", jdot);
    }

    /**
     * Test of setInputFileName method, of class JDot.
     */
    @Test
    public void testSetInputFileName() {
        writeBanner(methodName());
    }

    /**
     * Test of getGraphAttributes method, of class JDot.
     */
    @Test
    public void testGetGraphAttributes() {
        writeBanner(methodName());
    }

    /**
     * Test of getDefaultNodeAttributes method, of class JDot.
     */
    @Test
    public void testGetDefaultNodeAttributes() {
        writeBanner(methodName());
    }

    /**
     * Test of getDefaultEdgeAttributes method, of class JDot.
     */
    @Test
    public void testGetDefaultEdgeAttributes() {
        writeBanner(methodName());
    }

    /**
     * Test of getLayoutEngine method, of class JDot.
     */
    @Test
    public void testGetLayoutEngine() {
        writeBanner(methodName());
    }

    /**
     * Test of getOutputFormats method, of class JDot.
     */
    @Test
    public void testGetOutputFormats() {
        writeBanner(methodName());
    }

    /**
     * Test of getOutputFileName method, of class JDot.
     */
    @Test
    public void testGetOutputFileName() {
        writeBanner(methodName());
    }

    /**
     * Test of isAutogenOutputFileName method, of class JDot.
     */
    @Test
    public void testIsAutogenOutputFileName() {
        writeBanner(methodName());
    }

    /**
     * Test of isSuppressWarningMessages method, of class JDot.
     */
    @Test
    public void testIsSuppressWarningMessages() {
        writeBanner(methodName());
    }

    /**
     * Test of isVerboseMode method, of class JDot.
     */
    @Test
    public void testIsVerboseMode() {
        writeBanner(methodName());
    }

    /**
     * Test of toString method, of class JDot.
     */
    @Test
    public void testToString() {
        writeBanner(methodName());
    }

    /**
     * Test of builder method, of class JDot.
     */
    @Test
    public void testBuilder() {
        writeBanner(methodName());
    }

    /**
     * Test of buildCommandLineArgs method, of class JDot.
     */
    @Test
    public void testBuildCommandLineArgs() {
        writeBanner(methodName());
    }

}
