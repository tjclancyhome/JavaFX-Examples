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
package org.tjc.jfx.jfxrichtextfx;

import org.fxmisc.flowless.VirtualizedScrollPane;

/**
 * Defines an extension (it's really just a wrapper) mechanism to specialize a CodeArea for a
 * specific
 * language syntax.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public interface CodeAreaWrapper {

    /**
     * For example, if it is a Java code area, return java, etc.
     *
     * @return The name of the source code file's type (xml, java, etc.)
     */
    String getSourceCodeType();

    /**
     * Return the VirtualizedScrollPane to insert the code area into some container (e.g. VBox).
     *
     * @return a {@link org.fxmisc.flowless.VirtualizedScrollPane} object.
     */
    public VirtualizedScrollPane getVirtualizedScrollPane();

}
