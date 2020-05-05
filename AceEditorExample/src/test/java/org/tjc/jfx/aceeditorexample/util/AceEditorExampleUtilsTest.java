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
package org.tjc.jfx.aceeditorexample.util;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class AceEditorExampleUtilsTest extends BaseUnitTest {

    private static final URL ACE_SOURCE_PATH = AceEditorExampleUtilsTest.class.getResource(
        "/js/ace/src-noconflict");

    /**
     * Test of listAllModes method, of class AceEditorExampleUtils.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testListAllModes() throws Exception {
        writeBanner(methodName());

        var path = Paths.get(ACE_SOURCE_PATH.toURI());
        writeln(Files.exists(path));

        List<String> modes = AceEditorExampleUtils.listAllModes(path);
        modes.forEach(m -> writeln("{0}", m));
    }

    /**
     * Test of listAllThemes method, of class AceEditorExampleUtils.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testListAllThemes() throws Exception {
        writeBanner(methodName());

        var path = Paths.get(ACE_SOURCE_PATH.toURI());
        writeln(Files.exists(path));

        List<String> themes = AceEditorExampleUtils.listAllThemes(path);
        themes.forEach(t -> writeln("{0}", t));
    }

    /**
     * Test of getObject method, of class AceEditorExampleUtils.
     */
//    @Test
    public void testGetObject() {
        writeBanner(methodName());
    }

    /**
     * Test of getObjectByList method, of class AceEditorExampleUtils.
     */
//    @Test
    public void testGetObjectByList() {
        writeBanner(methodName());
    }

    /**
     * Test of getAllProperties method, of class AceEditorExampleUtils.
     */
//    @Test
    public void testGetAllProperties() {
        writeBanner(methodName());
    }

    /**
     * Test of getPropertyMap method, of class AceEditorExampleUtils.
     */
//    @Test
    public void testGetPropertyMap() {
        writeBanner(methodName());
    }

    /**
     * Test of extractSubstringsFromFileNames method, of class AceEditorExampleUtils.
     *
     * @throws java.lang.Exception
     */
//    @Test
    public void testExtractSubstringsFromFileNames() throws Exception {
        writeBanner(methodName());
    }

    /**
     * Test of capitalize method, of class AceEditorExampleUtils.
     */
//    @Test
    public void testCapitalize() {
        writeBanner(methodName());
    }

}
