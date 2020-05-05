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

import java.awt.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.getShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.writeln;
import org.tjc.jfx.jfxturtles.model.trig.StdDraw;

/**
 *
 * @author tjclancy
 */
public class TurtleTest extends BaseUnitTest {

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
    public void testTurtle() {
        StdDraw.enableDoubleBuffering();
        double x0 = 0.5;
        double y0 = 0.0;
        double a0 = 60.0;
        double step = Math.sqrt(3) / 2;
        Turtle turtle = new Turtle(x0, y0, a0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        turtle.show();


    }

    public class Turtle {
        private double x, y;     // turtle is at (x, y)
        private double angle;    // facing this many degrees counterclockwise from the x-axis

        // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis
        public Turtle(double x0, double y0, double a0) {
            x = x0;
            y = y0;
            angle = a0;
        }

        // rotate orientation delta degrees counterclockwise
        public void turnLeft(double delta) {
            angle += delta;
        }

        // move forward the given amount, with the pen down
        public void goForward(double step) {
            double oldx = x;
            double oldy = y;
            x += step * Math.cos(Math.toRadians(angle));
            y += step * Math.sin(Math.toRadians(angle));
            StdDraw.line(oldx, oldy, x, y);
        }

        // copy to onscreen
        public void show() {
            StdDraw.show();
        }

        // pause t milliseconds
        public void pause(int t) {
            StdDraw.pause(t);
        }

        public void setPenColor(Color color) {
            StdDraw.setPenColor(color);
        }

        public void setPenRadius(double radius) {
            StdDraw.setPenRadius(radius);
        }

        public void setCanvasSize(int width, int height) {
            StdDraw.setCanvasSize(width, height);
        }

        public void setXscale(double min, double max) {
            StdDraw.setXscale(min, max);
        }

        public void setYscale(double min, double max) {
            StdDraw.setYscale(min, max);
        }
    }
}
