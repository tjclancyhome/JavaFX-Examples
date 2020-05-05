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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
public class GraphvizTest {

    public GraphvizTest() {
    }

    /**
     * Test of call method, of class GraphvizProcessor.
     */
    @Test
    public void testDot() {
        writeBanner(methodName());
    }

    @Test
    public void testProcessBuilder() throws IOException, InterruptedException {
        writeBanner(methodName());
        System.out.format("System env PATH: %s%n", System.getenv("PATH"));
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "echo", "$PATH");
        Map<String, String> env = pb.environment();
        env.put("PATH", "/usr/local/bin");
        //printProcessBuilderEnv(pb);
        System.out.format("command: %s%n", pb.command());

        File log = new File("src/test/resources/log");
        pb.redirectErrorStream(true);
        pb.redirectOutput(Redirect.appendTo(log));
        Process p = pb.start();
        System.out.format("info: %s%n", p.info());
        System.out.format("pid: %s%n", p.pid());
        int exitCode = p.waitFor();
        System.out.format("exit code: %s%n", exitCode);
    }

    @Test
    public void testProcessBuilderEnv() throws InterruptedException, IOException {
        writeBanner(methodName());
        Path path = Path.of("src/test/resources/test123.dot");

        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "dot -Tpng -O " + path.toAbsolutePath());
//        ProcessBuilder pb = new ProcessBuilder("bash", "-c", "pwd");
        pb.environment().put("u", "util/");
        pb.environment().put("PATH", "/usr/local/bin" + File.pathSeparator + System.getenv("PATH"));

        File log = new File("src/test/resources/log");
        pb.redirectErrorStream(true);
        pb.redirectOutput(Redirect.appendTo(log));
        Process p = pb.start();
        int exitCode = p.waitFor();
        System.out.format("exit code: %s%n", exitCode);
    }

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testRuntimeExec() throws IOException, InterruptedException {
        writeBanner(methodName());
        System.out.println("executing: '/Users/tjclancy/bin/echo-path.sh'");
        System.out.println("=============================================");
        execute("/Users/tjclancy/bin/echo-path.sh");
        System.out.println();
        System.out.println("executing: '/usr/local/bin/dot -?'");
        System.out.println("==================================");
        execute("/usr/local/bin/dot -?");
    }

    private void execute(String command) {
        //writeBanner(methodName());
        try {
            Process process = Runtime.getRuntime().exec(command);
            processInputStream(process);
            processErrorStream(process);
            int exitCode = process.waitFor();
            System.out.format("exit code: %s%n", exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String command, String[] envp) {
        //writeBanner(methodName());
        try {
            Process process = Runtime.getRuntime().exec(command, envp);
            processInputStream(process);
            processErrorStream(process);
            int exitCode = process.waitFor();
            System.out.format("exit code: %s%n", exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String[] command, String[] envp) {
        //writeBanner(methodName());
        try {
            Process process = Runtime.getRuntime().exec(command, envp);
            processInputStream(process);
            processErrorStream(process);
            int exitCode = process.waitFor();
            System.out.format("exit code: %s%n", exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processInputStream(Process process) throws IOException {
        //writeBanner(methodName());
        System.out.println();
        System.out.println("Processing InputStream");
        System.out.println("======================");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private void processErrorStream(Process process) throws IOException {
        //writeBanner(methodName());
        System.out.println();
        System.out.println("Processing ErrorStream");
        System.out.println("======================");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private static void printSystemEnv() {
        System.out.println("===");
        Map<String, String> env = System.getenv();
        env.forEach((k, v) -> System.out.format("%s -> %s%n", k, v));
        System.out.println("===");
    }

    private static void printProcessBuilderEnv(ProcessBuilder pb) {
        System.out.println("===");
        Map<String, String> pbEnv = pb.environment();
        pbEnv.forEach((k, v) -> System.out.format("%s => %s%n", k, v));
        System.out.println("===");
    }

}
