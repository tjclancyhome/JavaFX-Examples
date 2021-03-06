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
package org.tjc.jfx.jfxgraphviz.config;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;

import static org.mockito.Mockito.*;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class ConfigureTest extends BaseUnitTest {

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
     * Test of getInstance method, of class Configure.
     */
    @Test
    public void testGetInstance() {
        writeBanner(methodName());
    }

    /**
     * Test of getProperty method, of class Configure.
     */
    @Test
    public void testGetProperty_String() {
        writeBanner(methodName());
    }

    /**
     * Test of getProperty method, of class Configure.
     */
    @Test
    public void testGetProperty_String_String() {
        writeBanner(methodName());
    }

    /**
     * Test of getPathToOutputDir method, of class Configure.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPathToOutputDir() throws Exception {
        writeBanner(methodName());
    }

    /**
     * Test of getPathToNeato method, of class Configure.
     */
    @Test
    public void testGetPathToNeato() {
        writeBanner(methodName());
    }

    /**
     * Test of getPathToGraphvizRoot method, of class Configure.
     */
    @Test
    public void testGetPathToGraphvizRoot() {
        writeBanner(methodName());
    }

    /**
     * Test of getPathToDot method, of class Configure.
     */
    @Test
    public void testGetPathToDot() {
        writeBanner(methodName());
    }

    /**
     * Test of getPathToUserHome method, of class Configure.
     */
    @Test
    public void testGetPathToUserHome() {
        writeBanner(methodName());
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

    }

}
