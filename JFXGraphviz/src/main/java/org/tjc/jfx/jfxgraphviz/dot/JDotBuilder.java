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
import javafx.util.Builder;

/**
 *
 * @author tjclancy
 */
public class JDotBuilder implements Builder<JDot> {
    private final Map<String, String> graphAttributes;
    private final Map<String, String> defaultNodeAttributes;
    private final Map<String, String> defaultEdgeAttributes;
    private String layoutEngine;
    private final Map<String, List<FormatSpec>> outputFormats;
    private String outputFileName;
    private String inputFileName;
    private boolean autogenOutputFileName;
    private boolean suppressWarningMessages;
    private boolean verboseMode;

    public JDotBuilder() {
        this.graphAttributes = new HashMap<>();
        this.defaultNodeAttributes = new HashMap<>();
        this.defaultEdgeAttributes = new HashMap<>();
        this.layoutEngine = "";
        this.outputFormats = new HashMap<>();
        this.outputFileName = "";
        this.inputFileName = "";
        this.autogenOutputFileName = true;
        this.suppressWarningMessages = true;
        this.verboseMode = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JDotBuilder{graphAttributes=").append(graphAttributes);
        sb.append(", defaultNodeAttributes=").append(defaultNodeAttributes);
        sb.append(", defaultEdgeAttributes=").append(defaultEdgeAttributes);
        sb.append(", layoutEngine=").append(layoutEngine);
        sb.append(", outputFormats=").append(outputFormats);
        sb.append(", outputFileName=").append(outputFileName);
        sb.append(", inputFileName=").append(inputFileName);
        sb.append(", autogenOutputFileName=").append(autogenOutputFileName);
        sb.append(", suppressWarningMessages=").append(suppressWarningMessages);
        sb.append(", verboseMode=").append(verboseMode);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public JDot build() {
        /*
         * Autogen takes precedence over defining a file name. Seems to make sense. Well it
         * makes sense to me.
         */
        if (autogenOutputFileName == true) {
            outputFileName = "";
        }

        JDot jgraphViz = new JDot(
            graphAttributes,
            defaultNodeAttributes,
            defaultEdgeAttributes,
            layoutEngine,
            outputFormats,
            outputFileName,
            inputFileName,
            autogenOutputFileName,
            suppressWarningMessages,
            verboseMode);

        return jgraphViz;
    }

    public JDotBuilder addGraphAttribute(String key, String val) {
        graphAttributes.put(key, val);
        return this;
    }

    public JDotBuilder addDefaultNodeAttribute(String key, String val) {
        defaultNodeAttributes.put(key, val);
        return this;
    }

    public JDotBuilder addDefaultEdgeAttribute(String key, String val) {
        defaultEdgeAttributes.put(key, val);
        return this;
    }

    public JDotBuilder outputFormat(String outFormat, FormatSpec formatSpec) {
        if (!outputFormats.containsKey(outFormat)) {
            outputFormats.put(outFormat, new ArrayList<>());
        }
        outputFormats.get(outFormat).add(formatSpec);
        return this;
    }

    public JDotBuilder outputFormat(String outFormat) {
        return outputFormat(outFormat, FormatSpec.newInstance());
    }

    public JDotBuilder layoutEngine(String layoutEngine) {
        this.layoutEngine = layoutEngine;
        return this;
    }

    public JDotBuilder outputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
        return this;
    }

    public JDotBuilder inputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
        return this;
    }

    public JDotBuilder autogenOutputFileName(boolean autogenOutputFileName) {
        this.autogenOutputFileName = autogenOutputFileName;
        return this;
    }

    public JDotBuilder suppressWarningMessages(boolean suppressWarningMessages) {
        this.suppressWarningMessages = suppressWarningMessages;
        return this;
    }

    public JDotBuilder verboseMode(boolean verboseMode) {
        this.verboseMode = verboseMode;
        return this;
    }

}
