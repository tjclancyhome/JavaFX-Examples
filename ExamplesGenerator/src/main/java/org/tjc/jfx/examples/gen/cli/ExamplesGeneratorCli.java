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
package org.tjc.jfx.examples.gen.cli;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class ExamplesGeneratorCli {
    private static final Logger log = LoggerFactory.getLogger(ExamplesGeneratorCli.class);
    private StringSubstitutor stringSub;
    private final Map<String, String> valueMap;
    private final Options options;
    private final CommandLine cmd;

    public ExamplesGeneratorCli(String[] args) throws IOException, URISyntaxException, ParseException {
        log.debug("### entered ExamplesGeneratorCli()");
        log.debug("###     args: {}", Arrays.asList(args));
        valueMap = new HashMap<>();
        options = new Options();
        CommandLineParser parser = new DefaultParser();
        configureOptions();
        cmd = parser.parse(options, args);
        log.debug("### exited ExamplesGeneratorCli()");
    }

    public void generateExample() {
        log.debug("### entered generateExample()");
        cmd.getArgList().forEach(arg -> log.debug("###     arg: {}", arg));
        if (cmd.hasOption("h") || cmd.hasOption("help")) {
            printHelp();
        }
        log.debug("### exited generateExample()");
    }

    private String generatePom() throws IOException {
        log.debug("### entered generatePom()");
        printHeader();
        valueMap.clear();
        valueMap.put("project.artifactId", "JFXFooApp");
        valueMap.put("package", "org.tjc.jfx");
        log.debug("###     valueMap  : {}", valueMap);
        stringSub = new StringSubstitutor(valueMap, "#{", "}");
        log.debug("###     stringSub : {}", stringSub);
        URL templateUrl = getClass().getResource("/templates/pom.xml.tmpl");
        log.debug("###     templateUrl: {}", templateUrl);
        Path p = Paths.get(templateUrl.getPath());
        String templateStr = Files.readString(p);
        String replacedStr = stringSub.replace(templateStr);
        log.debug("###     replacedStr: {}", replacedStr);
        log.debug("### exited generatePom()");
        return replacedStr;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        try {
            log.debug("### entered main(args[])");
            var app = new ExamplesGeneratorCli(args);
            app.generateExample();
            log.debug("### exited main(args[])");
        } catch (IOException | URISyntaxException | ParseException ex) {
            log.error("Caught exception: {}", ex.getMessage());
            throw ex;
        }
    }

    private void printHeader() {
        System.out.println("ExamplesGeneratorCli -- version 0.0.1-SNAPSHOT");
        System.out.println();
        printHelp();
    }

    private void configureOptions() {
        //options.addOption(new Option("h", "help", false, "Show this help."));
        Option help = Option.builder("h")
                .required(false)
                .longOpt("help")
                .build();

        options.addOption(help);

    }

    private void printHelp() {
        log.debug("### entered printHelp()");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ExamplesGeneratorCli", options);
        log.debug("### exited printHelp()");
    }

}
