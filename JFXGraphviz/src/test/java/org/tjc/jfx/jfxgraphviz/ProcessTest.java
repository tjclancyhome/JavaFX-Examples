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

import java.lang.ProcessHandle.Info;
import static java.util.Arrays.asList;
import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class ProcessTest {

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
