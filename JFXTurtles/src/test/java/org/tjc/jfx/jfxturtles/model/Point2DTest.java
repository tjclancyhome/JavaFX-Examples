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

import javafx.geometry.Point2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.getShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class Point2DTest extends BaseUnitTest {

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

    @Test
    public void smokeTest() {
        writeBanner(methodName());
        Point2D point = new Point2D(1.0, 2.0);
        printPoint("point: ", point);
    }

    @Test
    public void testAddTwoPoints() {
        writeBanner(methodName());
        Point2D point1 = new Point2D(1.0, 2.0);
        printPoint("point1: ", point1);
        Point2D point2 = new Point2D(1.0, 2.0);
        printPoint("point2: ", point2);
        Point2D pointsAdded = point1.add(point2);
        printPoint("point1 + point2 == ", pointsAdded);
    }

    @Test
    public void testAngle() {
        writeBanner(methodName());
        Point2D point1 = new Point2D(25, 20);
        printPoint("point1: ", point1);
        Point2D point2 = new Point2D(95, 75);
        printPoint("point2: ", point2);

        double angle = point1.angle(point2);
        double radians = Math.toRadians(angle);
        writeln("Math.toRadians(): {0}", Math.toRadians(angle));
        writeln("Math.toDegrees(): {0}", Math.toDegrees(radians));

        writeln("point1.angle(point2): {0}", point1.angle(point2));
        writeln("point2.angle(point1): {0}", point2.angle(point1));
    }

    @Test
    public void testRadsToDegrees_Math() {
        writeBanner(methodName());

        double radians = Math.PI;
        writeln("Math.PI: radian       s: {0}", String.valueOf(radians));
        writeln("Math.toDegrees(radians): {0}", String.valueOf(Math.toDegrees(radians)));
        writeln();

        radians = Math.PI / 2;
        writeln("Math.PI / 2: radians   : {0}", String.valueOf(radians));
        writeln("Math.toDegrees(radians): {0}", String.valueOf(Math.toDegrees(radians)));
        writeln();

        radians = Math.PI / 4;
        writeln("Math.PI / 4: radians   : {0}", String.valueOf(radians));
        writeln("Math.toDegrees(radians): {0}", String.valueOf(Math.toDegrees(radians)));
    }

    @Test
    public void testRadsToDegrees_StrictMath() {
        writeBanner(methodName());

        double radians = StrictMath.PI;
        writeln("StrictMath.PI: radians       : {0}", String.valueOf(radians));
        writeln("StrictMath.toDegrees(radians): {0}", String.valueOf(StrictMath.toDegrees(radians)));
        writeln();

        radians = StrictMath.PI / 2;
        writeln("StrictMath.PI / 2: radians   : {0}", String.valueOf(radians));
        writeln("StrictMath.toDegrees(radians): {0}", String.valueOf(StrictMath.toDegrees(radians)));
        writeln();

        radians = StrictMath.PI / 4;
        writeln("StrictMath.PI / 4: radians   : {0}", String.valueOf(radians));
        writeln("StrictMath.toDegrees(radians): {0}", String.valueOf(StrictMath.toDegrees(radians)));
    }

    @Test
    public void testDistance() {
        writeBanner(methodName());
        Point2D point1 = new Point2D(50, 50);
        Point2D point2 = new Point2D(100, 100);

        printPoint("point1: ", point1);
        printPoint("point2: ", point2);

        writeln("point1.distance(point2): {0}", point1.distance(point2));
        writeln("point2.distance(point1): {0}", point2.distance(point1));
    }

    @Test
    public void testDotProduct() {
        writeBanner(methodName());
        Point2D point1 = new Point2D(50, 50);
        Point2D point2 = new Point2D(100, 100);

        printPoint("point1: ", point1);
        printPoint("point2: ", point2);

        writeln("point1.dotProduct(point2): {0}", String.valueOf(point1.dotProduct(point2)));
        writeln("point2.dotProduct(point1): {0}", String.valueOf(point2.dotProduct(point1)));
    }

    @Test
    public void testMagnitude() {
        writeBanner(methodName());
        Point2D point = new Point2D(25, 100);
        printPoint("point: ", point);
        double mag = point.magnitude();
        writeln("point.magnitude(): {0}", String.valueOf(point.magnitude()));
    }

    @Test
    public void testMidpoint() {
        writeBanner(methodName());
        Point2D point1 = new Point2D(10, 45);
        Point2D point2 = new Point2D(100, 200);

        printPoint("point1: ", point1);
        printPoint("point2: ", point2);

        writeln("point1.midpoint(point2): {0}", String.valueOf(point1.midpoint(point2)));
        writeln("point2.midpoint(point1): {0}", String.valueOf(point2.midpoint(point1)));
    }

    private void printPoint(String prefix, Point2D point) {
        writeln("{0}{1}", prefix, formatPoint(point));
    }

    private String formatPoint(Point2D point) {
        return String.format("(%1.1f, %1.1f)", point.getX(), point.getY());
    }

}
