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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

/**
 * A class that lets you setup up (in a fluent manner) the command line options to be sent to the
 * eternal 'dot' program.
 *
 * @author tjclancy
 */
public class JDot {
    private static final Logger log = LoggerFactory.getLogger(JDot.class);

    private final Map<String, String> graphAttributes;
    private final Map<String, String> defaultNodeAttributes;
    private final Map<String, String> defaultEdgeAttributes;
    private final String layoutEngine;
    private final Map<String, List<FormatSpec>> outputFormats;
    private final String outputFileName;
    private String inputFileName;
    private final boolean autogenOutputFileName;
    private final boolean suppressWarningMessages;
    private final boolean verboseMode;

    public JDot() {
        this.graphAttributes = new HashMap<>();

        this.defaultNodeAttributes = new HashMap<>();
        this.defaultEdgeAttributes = new HashMap<>();
        this.layoutEngine = "";
        this.outputFormats = new HashMap<>();
        this.outputFileName = "";
        this.inputFileName = "";
        this.autogenOutputFileName = false;
        this.suppressWarningMessages = true;
        this.verboseMode = false;
    }

    JDot(Map<String, String> graphAttributes,
        Map<String, String> defaultNodeAttribute, Map<String, String> defaultEdgeAttribute,
        String layoutEngine, Map<String, List<FormatSpec>> outputFormats, String outputFileName,
        String inputFileName, boolean autogenOutputFileName, boolean suppressWarningMessages,
        boolean verboseMode) {

        this.graphAttributes = requireNonNull(graphAttributes,
            () -> "The 'graphAttributes' argument is null.");
        this.defaultNodeAttributes = requireNonNull(defaultNodeAttribute,
            () -> "The 'defaultNodeAttribute' argument is null.");
        this.defaultEdgeAttributes = requireNonNull(defaultEdgeAttribute,
            () -> "The 'defaultEdgeAttribute' argument is null.");
        this.layoutEngine = requireNonNull(layoutEngine,
            () -> "The 'layoutEngine' argument is null.");
        this.outputFormats = requireNonNull(outputFormats,
            () -> "The 'outputFormats' argument is null.");

        this.outputFileName = outputFileName;
        this.inputFileName = inputFileName;
        this.autogenOutputFileName = autogenOutputFileName;
        this.suppressWarningMessages = suppressWarningMessages;
        this.verboseMode = verboseMode;

        validateArguments();
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        if (inputFileName == null) {
            this.inputFileName = "";

        } else {
            this.inputFileName = inputFileName;
        }
    }

    public Map<String, String> getGraphAttributes() {
        return graphAttributes;
    }

    public Map<String, String> getDefaultNodeAttributes() {
        return defaultNodeAttributes;
    }

    public Map<String, String> getDefaultEdgeAttributes() {
        return defaultEdgeAttributes;
    }

    public String getLayoutEngine() {
        return layoutEngine;
    }

    public Map<String, List<FormatSpec>> getOutputFormats() {
        return outputFormats;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean isAutogenOutputFileName() {
        return autogenOutputFileName;
    }

    public boolean isSuppressWarningMessages() {
        return suppressWarningMessages;
    }

    public boolean isVerboseMode() {
        return verboseMode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JGraphviz{graphAttributes=").append(graphAttributes).append(",\n");
        sb.append("  defaultNodeAttributes=").append(defaultNodeAttributes).append(",\n");
        sb.append("  defaultEdgeAttributes=").append(defaultEdgeAttributes).append(",\n");
        sb.append("  layoutEngine=").append(layoutEngine).append(",\n");
        sb.append("  outputFormats=").append(outputFormats).append(",\n");
        sb.append("  outputFileName=").append(outputFileName).append(",\n");
        sb.append("  inputFileName=").append(inputFileName).append(",\n");
        sb.append("  autogenOutputFileName=").append(autogenOutputFileName).append(",\n");
        sb.append("  suppressWarningMessages=").append(suppressWarningMessages).append(",\n");
        sb.append("  verboseMode=").append(verboseMode).append("\n");
        sb.append('}');
        return sb.toString();
    }

    public static JDotBuilder builder() {
        return new JDotBuilder();
    }

    public List<String> buildCommandLineArgs() {
        log.debug("### entered buildCommandLineArgs()");
        final var commandLineArgs = new ArrayList<String>();
        outputFormats.forEach((var outFmt, var fmtSpecList) -> {
            fmtSpecList.forEach((var fmtSpec) -> {
                log.debug("###     fmtSpec: {}", fmtSpec);
                commandLineArgs.add(String.format("-T%s%s", outFmt, fmtSpec.format()));
            });
        });

        graphAttributes.forEach((var key, var val) -> {
            commandLineArgs.add(String.format("-G%s=%s", key, val));
        });

        defaultNodeAttributes.forEach((var key, var val) -> {
            commandLineArgs.add(String.format("-N%s=%s", key, val));
        });

        defaultEdgeAttributes.forEach((var key, var val) -> {
            commandLineArgs.add(String.format("-E%s=%s", key, val));
        });

        if (suppressWarningMessages) {
            commandLineArgs.add("-q");
        }

        if (verboseMode) {
            commandLineArgs.add("-v");
        }

        if (autogenOutputFileName == true) {
            commandLineArgs.add("-O");
        } else {
            commandLineArgs.add(String.format("-o %s", outputFileName));
        }

        if (!inputFileName.isBlank()) {
            commandLineArgs.add(inputFileName);
        }

        log.debug("### exiting buildCommandLineArgs()");
        return commandLineArgs;
    }

    private void validateArguments() {
        if (outputFormats.isEmpty()) {
            throw new IllegalArgumentException("There are no outputFormats defined.");
        }

        if (autogenOutputFileName == false) {
            if (outputFileName == null || outputFileName.isBlank() == true) {
                throw new IllegalArgumentException("There is no outputFileName defined.");
            }
        }
    }

}
