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
package org.tjc.jfx.turtlesapp;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * ****************************************************************************
 * Compilation: javac Turtle.java
 * Execution: java Turtle
 * <p>
 * Data type for turtle graphics using standard draw.
 * </p>
 *****************************************************************************
 */
public interface Turtle {

    Paint DEFAULT_TURTLE_FILL = Color.BLACK;
    Paint DEFAULT_TURTLE_STROKE = Color.RED.brighter();

    void backward(double step);

    void face(double angle);

    double facing();

    void forward(double step);

    double getAngle();

    Paint getTurtleFill();

    void setTurtleFill(Paint turtleFill);

    Paint getTurtleStroke();

    void setTurtleStroke(Paint turtleStroke);

    double getX();

    double[] getXPoints();

    double getY();

    double[] getYPoints();

    void hide();

    void home();

    void pause(int t);

    void penDown();

    void penUp();

    void reset();

    void setCanvasSize(int width, int height);

    void setPenColor(Color color);

    void setPenRadius(double radius);

    void setXscale(double min, double max);

    void setYscale(double min, double max);

    void show();

    void turnLeft(double delta);

    void turnRight(double delta);

    void update();

    void goTo(double x, double y);

}
