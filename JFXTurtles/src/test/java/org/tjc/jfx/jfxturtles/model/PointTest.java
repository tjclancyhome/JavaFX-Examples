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
package org.tjc.jfx.jfxturtles.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.getShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 * The Point using test.
 *
 * @author tjclancy
 */
public class PointTest extends BaseUnitTest {

    public PointTest() {
    }

    @BeforeEach
    public void setup() {
        pushBool(getShowOutput());
        setShowOutput(true);
    }

    @AfterEach
    public void tearDown() {
        writeln();
        setShowOutput(popBool());
    }

    /**
     * Test of getX method, of class Point.
     */
    @Test
    public void testGetX() {
        writeBanner(methodName());
        Point p = new Point(1, 2);
        Assertions.assertEquals(1, p.getX());
        writeln("point.x(): {0}", p.getX());
    }

    /**
     * Test of getY method, of class Point.
     */
    @Test
    public void testGetY() {
        writeBanner(methodName());
        Point p = new Point(1, 2);
        Assertions.assertEquals(2, p.getY());
        writeln("point.y(): {0}", p.getY());
    }

    /**
     * Test of toString method, of class Point.
     */
    @Test
    public void testToString() {
        writeBanner(methodName());
        Point p = new Point(1, 2);
        writeln("point.toString(): {0}", p);
    }

    /**
     * Test of hashCode method, of class Point.
     */
    @Test
    public void testHashCode() {
        writeBanner(methodName());
        Point p = new Point(1, 2);
        writeln("point.hashCode(): {0}", p.hashCode());
    }

    /**
     * Test of equals method, of class Point.
     */
    @Test
    public void testEquals() {
        writeBanner(methodName());
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Assertions.assertEquals(p1, p2);
        writeln("p1: {0} == p2: {1}", p1, p2);
    }

}
