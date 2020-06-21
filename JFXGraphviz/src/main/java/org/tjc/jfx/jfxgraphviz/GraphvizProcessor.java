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
import java.nio.file.Path;
import java.util.ArrayList;

import static java.util.Arrays.asList;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxgraphviz.config.Configure;

/**
 * <p>
 * GraphvizProcessor class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class GraphvizProcessor {

    private static final Logger log = LoggerFactory.getLogger(GraphvizProcessor.class);
    private static final String PATH_TO_DOT_KEY = "path.to.dot";
    private static final String PATH_TO_NEATO_KEY = "path.to.neato";

    private final Configure config;

    /**
     * <p>
     * Constructor for GraphvizProcessor.</p>
     *
     * @throws org.tjc.jfx.jfxgraphviz.GraphvizProcessorException if any.
     */
    public GraphvizProcessor() throws GraphvizProcessorException {
        log.debug("### entered GraphvizProcessor() constructor.");
        this.config = Configure.getInstance();
    }

//    /**
//     * <p>
//     * Constructor for GraphvizProcessor.</p>
//     *
//     * @param config a {@link org.tjc.jfx.jfxgraphviz.Configure} object.
//     */
//    public GraphvizProcessor(Configure config) {
//        log.debug("### entered GraphvizProcessor(Configure) constructor.");
//        this.config = config;
//    }
    /**
     * <p>
     * neato.</p>
     *
     * @param args a {@link java.lang.String} object.
     *
     * @return a boolean.
     *
     * @throws org.tjc.jfx.jfxgraphviz.GraphvizProcessorException
     */
    public boolean neato(String... args) throws GraphvizProcessorException {
        log.debug("### entered dot(String[]): {}", asList(args));

        List<String> commandAndArgs = new ArrayList<>();
        commandAndArgs.add(config.getProperty(PATH_TO_NEATO_KEY));
        commandAndArgs.addAll(asList(args));

        execute(commandAndArgs);
        return true;
    }

    /**
     * <p>
     * dot.</p>
     *
     * @param args a {@link java.lang.String} object.
     *
     * @return a boolean.
     *
     * @throws java.io.IOException            if any.
     * @throws java.lang.InterruptedException if any.
     * @throws java.lang.Exception            if any.
     */
    public boolean dot(String... args) throws IOException, InterruptedException, Exception {
        log.debug("### entered dot(String[]): {}", asList(args));

        List<String> commandAndArgs = new ArrayList<>();
        commandAndArgs.add(config.getProperty(PATH_TO_DOT_KEY));
        commandAndArgs.addAll(asList(args));

        execute(commandAndArgs);
        return true;
    }

    /**
     * <p>
     * execute.</p>
     *
     * @param commandAndArgs a {@link java.util.List} object.
     *
     * @return a {@link java.util.List} object.
     *
     * @throws org.tjc.jfx.jfxgraphviz.GraphvizProcessorException if any.
     */
    public List<String> execute(List<String> commandAndArgs) throws GraphvizProcessorException {
        log.debug("### execute: {}", commandAndArgs);

        //var outputDir = config.getProperty("path.to.output.dir");
        Path outputDirPath = config.getPathToOutputDir();

        log.debug("### outputDir: {}", outputDirPath.toString());
        log.debug("### outputDir: {}", outputDirPath.toAbsolutePath().toString());

        var processBuilder = new ProcessBuilder(commandAndArgs);

        debugView(processBuilder);

        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException ex) {
            throw new GraphvizProcessorException(ex);
        }

        int exitCode;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException ex) {
            throw new GraphvizProcessorException(ex);
        }

        if (exitCode != 0) {
            var errorResultStr = readOutputAsString(process.getErrorStream());
            throw new GraphvizProcessorException(String.format(
                    "Exit code: %s, Exception message: %s", exitCode, errorResultStr));
        }

        List<String> results = readOutput(process.getInputStream());

        return results;
    }

    /**
     * <p>
     * readOutput.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     *
     * @return a {@link java.util.List} object.
     *
     * @throws org.tjc.jfx.jfxgraphviz.GraphvizProcessorException if any.
     */
    public List<String> readOutput(InputStream inputStream) throws GraphvizProcessorException {
        List<String> results = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }
            return results;
        } catch (IOException ex) {
            throw new GraphvizProcessorException(ex);
        }
    }

    /**
     * <p>
     * readOutputAsString.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     *
     * @return a {@link java.lang.String} object.
     *
     * @throws org.tjc.jfx.jfxgraphviz.GraphvizProcessorException if any.
     */
    public String readOutputAsString(InputStream inputStream) throws GraphvizProcessorException {
        List<String> results = readOutput(inputStream);
        if (!results.isEmpty()) {
            return String.join("\n", results);
        }
        return "";
    }

    private void debugView(ProcessBuilder pb) {
        if (log.isDebugEnabled()) {
            log.debug("-------------------------------------");
            log.debug("### pb command()         : {}", pb.command());
            log.debug("### pb directory()       : {}", pb.directory());
            log.debug("### pb environment() size: {}", pb.environment().size());
            if (log.isTraceEnabled()) {
                log.debug("### pb environment()     :");
                pb.environment().forEach((k, v) -> log.debug("{}: {}", k, v));
            }
            log.debug("-------------------------------------");
        }

    }

}
