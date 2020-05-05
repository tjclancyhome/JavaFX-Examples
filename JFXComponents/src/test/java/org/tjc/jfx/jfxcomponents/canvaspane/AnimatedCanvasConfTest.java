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
package org.tjc.jfx.jfxcomponents.canvaspane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class AnimatedCanvasConfTest {

    public AnimatedCanvasConfTest() {
    }

    /**
     * Test of clone method, of class AnimationCanvasConf.
     */
    @Test
    public void testClone() {
        writeBanner(methodName());
        AnimationCanvasConf conf = new AnimationCanvasConf();
        AnimationCanvasConf cloned = conf.clone();
        printAnimatedCanvasConf(conf);
        printAnimatedCanvasConf(cloned);
        assertEquals(conf, cloned);
    }

    /**
     * Test of widthProperty method, of class AnimationCanvasConf.
     */
    @Test
    public void testWidthProperty() {
        writeBanner(methodName());

    }

    /**
     * Test of heightProperty method, of class AnimationCanvasConf.
     */
    @Test
    public void testHeightProperty() {
        writeBanner(methodName());
    }

    /**
     * Test of backgroundColorProperty method, of class AnimationCanvasConf.
     */
    @Test
    public void testCanvasFillProperty() {
        writeBanner(methodName());
    }

    /**
     * Test of framesProperty method, of class AnimationCanvasConf.
     */
    @Test
    public void testFramesProperty() {
        writeBanner(methodName());
    }

    /**
     * Test of toString method, of class AnimationCanvasConf.
     */
    @Test
    public void testToString() {
        writeBanner(methodName());

        String expected = "AnimatedCanvasConf{\n"
            + "  widthProperty=800.0,\n"
            + "  heightProperty=600.0,\n"
            + "  canvasFillProperty=0xffffffff,\n"
            + "  framesProperty=30\n"
            + "}";

        AnimationCanvasConf conf = new AnimationCanvasConf();
        String actual = conf.toString();
        assertEquals(expected, actual);

    }

    /**
     * Test of equals method, of class AnimationCanvasConf.
     */
    @Test
    public void testEquals() {
        writeBanner(methodName());
        AnimationCanvasConf conf = new AnimationCanvasConf();
        AnimationCanvasConf cloned = conf.clone();
        assertEquals(conf, cloned);
    }

    private void printAnimatedCanvasConf(AnimationCanvasConf conf) {
        writeln("=================================");
        writeln("Printing AnimatedCanvasConf");
        writeln("=================================");
        writeln("width          : {0}", conf.widthProperty().get());
        writeln("height         : {0}", conf.heightProperty().get());
        writeln("backgroundColor: {0}", conf.canvasFillProperty().get());
        writeln("frames         : {0}", conf.framesProperty().get());
        writeln("=================================");
        writeln("Done Printing AnimatedCanvasConf.");
        writeln("=================================");
    }

}
