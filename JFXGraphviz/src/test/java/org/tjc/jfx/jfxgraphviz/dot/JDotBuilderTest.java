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
package org.tjc.jfx.jfxgraphviz.dot;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class JDotBuilderTest extends BaseUnitTest {

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
     * Test of builder method, of class JDot.
     */
    @Test
    public void testBuilder() {
        writeBanner(methodName());
        JDot jdot = JDot.builder()
            .addDefaultEdgeAttribute("defaultEdgeAttr", "some edge value")
            .addDefaultNodeAttribute("defaultNodeAttr", "some node value")
            .addGraphAttribute("graphAttr", "some graph value")
            .outputFormat("png")
            .inputFileName("d2.gv")
            .autogenOutputFileName(true)
            .build();

        writeln("jgraphviz:\n{0}", jdot);
    }

    /**
     * Test of buildCommandLineArgs method, of class JDot.
     */
    @Test
    public void testBuildCommandLineArgs() {
        writeBanner(methodName());
        JDot jdot = this.buildJGraphviz();
        List<String> commandLineArgs = jdot.buildCommandLineArgs();
        commandLineArgs.forEach(arg -> writeln("{0}", arg));
    }

    /**
     * Test of toString method, of class JDot.
     */
    @Test
    public void testToString() {
        writeBanner(methodName());
        JDot jdot = buildJGraphviz();
        writeln("jgv.toString():\n{0}", jdot.toString());
    }

    /**
     * Test of getInputFileName method, of class JDot.
     */
    @Test
    public void testGetInputFileName() {
        writeBanner(methodName());
        JDot jdot = this.buildJGraphviz();
        String inputFileName = jdot.getInputFileName();
        assertThat(inputFileName).isNotNull();
        assertThat(inputFileName).isNotBlank();
        writeln("inputFileName: {0}", inputFileName);
    }

    @Test
    public void testGetInputFileName_isBlank() {
        writeBanner(methodName());
        JDot jdot = this.buildJGraphvizWithNoInputFileName();
        String inputFileName = jdot.getInputFileName();
        assertThat(inputFileName).isBlank();
        writeln("inputFileName: <isBlank>");
    }

    /**
     * Test of setInputFileName method, of class JDot.
     */
    @Test
    public void testSetInputFileName() {
        writeBanner(methodName());
        JDot jdot = this.buildJGraphviz();
        jdot.setInputFileName("foo.bar.gv");
        String inputFileName = jdot.getInputFileName();
        assertThat(inputFileName).isNotNull();
        assertThat(inputFileName).isNotBlank();
        assertThat(inputFileName).isEqualTo("foo.bar.gv");
        writeln("inputFileName: {0}", inputFileName);
    }

    private JDot buildJGraphviz() {
        JDot jdot = JDot.builder()
            .addDefaultEdgeAttribute("defaultEdgeAttr", "some edge value")
            .addDefaultNodeAttribute("defaultNodeAttr", "some node value")
            .addGraphAttribute("graphAttr", "some graph value")
            .outputFormat("png")
            .outputFormat("png", FormatSpec.newInstance("cairo"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "cairo"))
            .outputFormat("png", FormatSpec.newInstance("quartz", "quartz"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "quartz"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "gd"))
            .outputFormat("png", FormatSpec.newInstance("gd", "gd"))
            .inputFileName("d2.gv")
            .autogenOutputFileName(false)
            .outputFileName("d2.gv.png")
            .build();
        return jdot;
    }

    private JDot buildJGraphvizWithNoInputFileName() {
        JDot jdot = JDot.builder()
            .addDefaultEdgeAttribute("defaultEdgeAttr", "some edge value")
            .addDefaultNodeAttribute("defaultNodeAttr", "some node value")
            .addGraphAttribute("graphAttr", "some graph value")
            .outputFormat("png")
            .outputFormat("png", FormatSpec.newInstance("cairo"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "cairo"))
            .outputFormat("png", FormatSpec.newInstance("quartz", "quartz"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "quartz"))
            .outputFormat("png", FormatSpec.newInstance("cairo", "gd"))
            .outputFormat("png", FormatSpec.newInstance("gd", "gd"))
            .autogenOutputFileName(false)
            .outputFileName("d2.gv.png")
            .build();
        return jdot;
    }
}
