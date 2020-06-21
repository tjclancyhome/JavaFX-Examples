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
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;

/**
 * This class is used for setting command line arguments used when calling the external graphviz
 * 'dot' to generate an image from dot file.
 *
 * @author tjclancy
 */
public class Dot {
    private static final Logger log = LoggerFactory.getLogger(Dot.class);

    private final SortedSet<OutputFormats> outputFormatsSet;
    private final SortedSet<GraphAttributes> graphAttributesSet;
    private final SortedMap<GraphAttributes, String> graphAttrValMap;

    public Dot() {
        outputFormatsSet = new TreeSet<>();
        graphAttributesSet = new TreeSet<>();
        graphAttrValMap = new TreeMap<>();
    }

    public boolean addOutputFormat(OutputFormats outputFormat) {
        Objects.requireNonNull(outputFormat, () -> "The 'outputFormat' argument is null.");
        outputFormatsSet.add(outputFormat);
        return true;
    }

    public boolean addOutputFormats(OutputFormats... formats) {
        if (formats.length == 0) {
            return false;
        }
        asList(formats).forEach(fmt -> outputFormatsSet.add(fmt));
        return true;
    }

    public void putGraphAttribute(GraphAttributes attribute, String value) {
        graphAttrValMap.put(attribute, value);
    }

    public void putGraphAttributes(Entry<GraphAttributes, String>... entries) {
        log.debug("### entered putGraphAttributes(Entry<>... entries)");
        asList(entries).forEach(entry -> {
            log.debug("###     entry: {}", entry);
            //graphAttrValMap.putIfAbsent(GraphAttributes.DPI, value);
        });
        log.debug("### exiting putGraphAttributes(Entry<>... entries)");
    }

    public List<String> getOutputFormatsArguments() {
        var outputFormatsArguments = new ArrayList<String>();
        outputFormatsSet.forEach(outFmt -> outputFormatsArguments
            .add(outFmt.asCommandLineArgument()));
        return Collections.unmodifiableList(outputFormatsArguments);
    }

    public List<String> getGraphAttributeArguments() {
        var attributeArguments = new ArrayList<String>();
        graphAttrValMap.forEach((var attr, var val) -> {
            attributeArguments.add(String.format("%s=%s", attr.asCommandLineArgument(), val));
        });
        return Collections.unmodifiableList(attributeArguments);
    }

    public void debugDumpOutputFormatsEnum() {
        if (log.isDebugEnabled()) {
            for (OutputFormats outputFormat : OutputFormats.values()) {
                log.debug(String.format("Format name: %-6s, as argument: %-7s, description: %s",
                    outputFormat,
                    outputFormat.asCommandLineArgument(),
                    outputFormat.formatDescription()));
            }
        }
    }

    public void debugDumpGraphAttributesEnum() {
        if (log.isDebugEnabled()) {
            for (GraphAttributes attribute : GraphAttributes.values()) {
                log.debug(String
                    .format("Attribute name: %-14s, as argument: %-16s, description: %s",
                        attribute,
                        attribute.asCommandLineArgument(),
                        attribute.attributeDescription()));
            }
        }
    }

}
