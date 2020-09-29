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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessHandle.Info;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;

import static java.util.Arrays.asList;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class ProcessTest extends BaseUnitTest {

    @BeforeEach
    public void setup() {
        this.forceShowOutput();

    }

    @AfterEach
    public void tearDown() {
        writeln();
        this.restoreShowOutput();
    }

    @Test
    public void testProcessBuilder() throws IOException, InterruptedException {
        writeBanner(methodName());
//        ProcessBuilder pb = new ProcessBuilder("java", "-version");
//        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "ls -lathr");
        ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/dot", "-v", "-Tpng", "-O",
            "/Users/tjclancy/Projects/graphviz_projects/backup/digraph2.dot");
        pb.redirectErrorStream(true);
        Process process = pb.start();
        List<String> results = readOutput(process.getInputStream());
        int exitCode = process.waitFor();

        writeln("exit code: {0}", exitCode);
        if (!results.isEmpty()) {
            results.forEach(line -> writeln("{0}", line));
        }
    }

    private List<String> readOutput(InputStream inputStream) throws IOException {
        try(BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                .collect(Collectors.toList());
        }
    }

    @Test
    public void testProcessHandle_Pid() {
        writeBanner(methodName());
        writeln("Current pid: {0}", Long.toString(currentProcessHandle().pid()));
    }

    @Test
    public void testProcessHandle_Children() {
        writeBanner(methodName());
        ProcessHandle handle = currentProcessHandle();
        handle.children().forEach((var c) -> writeln("child: {0}", c));
    }

    @Test
    public void testProcessHandle_Descendants() {
        writeBanner(methodName());
        ProcessHandle handle = currentProcessHandle();
        handle.descendants().forEach((var c) -> writeln("descendant: {0}", c));
    }

    @Test
    public void testProcessHandle_Info() {
        writeBanner(methodName());
        ProcessHandle handle = currentProcessHandle();
        printInfo(handle.info());
    }

    @Test
    public void testProcessHandle_Parent() {
        writeBanner(methodName());
        ProcessHandle handle = currentProcessHandle();
        ProcessHandle parent = handle.parent().get();
        writeln("parent: {0}", parent);
        printInfo(parent.info());
    }

    ProcessHandle currentProcessHandle() {
        return ProcessHandle.current();
    }

    void printInfo(Info info) {
        writeln("Info:");
        writeln("=====");
        writeln("    command      : {0}", info.command());
        writeln("    commandLine  : {0}", info.commandLine());
        List<String> args = asList(info.arguments().orElse(Arrays.array("[no args]")));
        writeln("    arguments    :");
        writeln("    ==============");
        args.forEach(a -> writeln("        {0}", a));
        writeln("    user         : {0}", info.user());
        writeln("    start instant: {0}", info.startInstant());
        writeln("    cpu duration : {0}", info.totalCpuDuration());

    }
}
