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

/**
 * A context object that contains properties for sending to 'dot'.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class GraphvizCommandLineArgs {

    /**
     * The output format that is passed to 'dot' in the form of -Txxx (example -Tjpg).
     */
    public static enum OutputFormats {
        BMP("bmp"),
        GIF("gif"),
        JPG("jpg"),
        PNG("png");

        private final String outputFormatName;

        String getOuputFormatName() {
            return outputFormatName;
        }

        OutputFormats(String outputFormatName) {
            this.outputFormatName = outputFormatName;
        }

        String formatGraphvizFormat() {
            return "-T" + this.name();
        }

    }

    private OutputFormats outputFormat;

    /**
     * <p>
     * Constructor for GraphvizCommandLineArgs.</p>
     */
    public GraphvizCommandLineArgs() {
        outputFormat = OutputFormats.PNG;
    }

    /**
     * <p>
     * Constructor for GraphvizCommandLineArgs.</p>
     *
     * @param outputFormat a {@link org.tjc.jfx.jfxgraphviz.GraphvizCommandLineArgs.OutputFormats}
     *                     object.
     */
    public GraphvizCommandLineArgs(OutputFormats outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * <p>
     * Getter for the field <code>outputFormat</code>.</p>
     *
     * @return a {@link org.tjc.jfx.jfxgraphviz.GraphvizCommandLineArgs.OutputFormats} object.
     */
    public OutputFormats getOutputFormat() {
        return outputFormat;
    }

    /**
     * <p>
     * Setter for the field <code>outputFormat</code>.</p>
     *
     * @param outputFormat a {@link org.tjc.jfx.jfxgraphviz.GraphvizCommandLineArgs.OutputFormats}
     *                     object.
     */
    public void setOutputFormat(OutputFormats outputFormat) {
        this.outputFormat = outputFormat;
    }
}
