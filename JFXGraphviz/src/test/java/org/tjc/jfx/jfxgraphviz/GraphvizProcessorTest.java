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
package org.tjc.jfx.jfxgraphviz;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class GraphvizProcessorTest {

    public GraphvizProcessorTest() {
    }

    /**
     * Test of dot method, of class GraphvizProcessor.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDot() throws Exception {
        writeBanner(methodName());
        GraphvizProcessor gp = new GraphvizProcessor();
        boolean success = gp.dot("-Tpng", "-O", "src/test/resources/samples/test123.dot");
        assertTrue(success);
        writeln("The dot processor succeeded!");
    }

    /**
     * Test of dot method, of class GraphvizProcessor.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDot_WithBadFileName() throws Exception {
        writeBanner(methodName());
        Exception ex = assertThrows(Exception.class, () -> {
            GraphvizProcessor gp = new GraphvizProcessor();
            gp.dot("-Tpng", "-O", "src/test/resources/test124.dot");
        });
        writeln("Exception: {0}", ex);
    }

    /**
     * Test of neato method, of class GraphvizProcessor.
     */
    @Test
    public void testNeato() {
        writeBanner(methodName());
    }

    /**
     * Test of execute method, of class GraphvizProcessor.
     *
     * @throws java.lang.Exception
     */
    @Disabled
    @Test
    public void testExecute() throws Exception {
        writeBanner(methodName());
        List<String> command = List.of("/usr/local/bin/dot", "-Tpng", "-O",
            "/Users/tjclancy/Projects/eclipse-javaee-2019-06/JavaFX-12-Examples/JFXGraphviz/src/test/resources/samples/digraph1.dot");
        GraphvizProcessor gp = new GraphvizProcessor();
        List<String> results = gp.execute(command);
        results.forEach(System.out::println);

    }

    @Test
    public void testExecute2() throws Exception {
        writeBanner(methodName());
        List<String> command = List.of("/bin/bash", "-c", "echo Greetings, Stranger!");
        GraphvizProcessor gp = new GraphvizProcessor();
        List<String> results = gp.execute(command);
        String resultStr = String.join("\n", results);
        writeln("Results as a String:\n{0}", resultStr);
    }

    @Test
    public void testExecute3() throws Exception {
        writeBanner(methodName());
        List<String> command = List.of("/usr/local/bin/dot", "-?");
        GraphvizProcessor gp = new GraphvizProcessor();
        List<String> results = gp.execute(command);
        String resultStr = String.join("\n", results);
        writeln("Results as a String:\n{0}", resultStr);
    }

}
