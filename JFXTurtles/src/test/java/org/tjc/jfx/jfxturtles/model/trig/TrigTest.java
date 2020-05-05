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
package org.tjc.jfx.jfxturtles.model.trig;

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
public class TrigTest extends BaseUnitTest {
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
    public void testCalculateHeadingWithTrig() {
        writeBanner(methodName());
        var speed = 55.0;
        var facingAngle = 180.0;

        /*
         * show steps in: speed* Math.cos(Math.toRadians(facingAngle));
         */
        var facingAngleRads = Math.toRadians(facingAngle);
        var cosFacingAngleAsRads = Math.cos(facingAngleRads);
        writeln("Shows steps of horizontalVelocity = (speed * Math.cos(Math.toRadians(facingAngle)))");
        writeln("===================================================================================");
        writeln("toRad(facingAngle: {0})     : {1}", facingAngle, facingAngleRads);
        writeln("cos(facingAngleRads)        : {0}", cosFacingAngleAsRads);
        writeln("speed * cosFacingAngleAsRads: {0}", cosFacingAngleAsRads * speed);
        var horizontalVelocity = speed * Math.cos(Math.toRadians(facingAngle));
        writeln("horizontalVelocity          : {0}", horizontalVelocity);
        writeln();

        facingAngleRads = Math.toRadians(facingAngle);
        var sinFacingAngleAsRads = Math.sin(facingAngleRads);
        writeln("Shows steps of verticalVelocity = (speed * Math.sin(Math.toRadians(facingAngle)))");
        writeln("=================================================================================");
        writeln("toRad(facingAngle: {0})     : {1}", facingAngle, facingAngleRads);
        writeln("sin(facingAngleRads)        : {0}", sinFacingAngleAsRads);
        writeln("speed * cosFacingAngleAsRads: {0}", sinFacingAngleAsRads * speed);
        var verticalVelocity = speed * Math.sin(Math.toRadians(facingAngle));
        writeln("verticalVelocity            : {0}", verticalVelocity);
        writeln();
    }

    @Test
    public void testGoForward() {
        writeBanner(methodName());
        double oldx = 1;
        double oldy = 1;
        double x = 10;
        double y = 25;
        double step = 1;
        double angle = 25;
        x += step * Math.cos(Math.toRadians(angle));
        y += step * Math.sin(Math.toRadians(angle));


        writeln("goForward: oldX: {0}, oldY: {1}", oldx, oldy);
        writeln("goForward: x: {0}, y: {1}", Math.round(x), Math.round(y));
    }

//    @Test
    public void testDegreeToRadians() {
        writeBanner(methodName());
        for (int i = 0; i < 360; i++) {
            degreesToRadians((double) i);

        }
    }

    private double degreesToRadians(double angle) {
        var degToRads = Math.toRadians(angle);
        writeln("degToRads: angle {0}, rads: {1}", angle, degToRads);
        return degToRads;
    }

}
