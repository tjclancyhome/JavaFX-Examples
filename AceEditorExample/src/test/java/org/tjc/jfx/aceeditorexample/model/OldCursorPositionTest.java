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
package org.tjc.jfx.aceeditorexample.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
public class OldCursorPositionTest {

    public OldCursorPositionTest() {
    }

    @BeforeEach
    public void setup() {
//        pushBool(getShowOutput());
        setShowOutput(true);

    }

    @AfterEach
    public void tearDown() {
//        setShowOutput(popBool());
    }

//    @Test
    public void testRowObjectProperty() {
        writeBanner(methodName());
//        var cp = new CursorPosition();
    }

    public void testSetRowObjectValue(Number number) {
        writeBanner(methodName());
    }

    public void testGetRowObjecValue() {
        writeBanner(methodName());
    }

    public void testColumnObjectProperty() {
        writeBanner(methodName());
    }

    public void testSetColumnObjectValue(Number number) {
        writeBanner(methodName());
    }

    public void testGetColumnObjeValue() {
        writeBanner(methodName());
    }

    public void testRowProperty() {
        writeBanner(methodName());
    }

    public void testColumnProperty() {
        writeBanner(methodName());
    }

    public void testGetRow() {
        writeBanner(methodName());
    }

    public void testGetColumn() {
        writeBanner(methodName());
    }

    public void testToString() {
        writeBanner(methodName());
    }

}
