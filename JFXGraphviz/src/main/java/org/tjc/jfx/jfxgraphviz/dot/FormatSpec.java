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

import java.util.Objects;

/**
 *
 * @author tjclancy
 */
public class FormatSpec {
    private final String renderer;
    private final String formatter;

    FormatSpec() {
        this.renderer = "";
        this.formatter = "";
    }

    FormatSpec(String renderer) {
        this.renderer = renderer;
        this.formatter = "";
    }

    FormatSpec(String renderer, String formatter) {
        this.renderer = renderer;
        this.formatter = formatter;
    }

    public static FormatSpec newInstance() {
        return new FormatSpec();
    }

    public static FormatSpec newInstance(String renderer) {
        return new FormatSpec(renderer);
    }

    public static FormatSpec newInstance(String renderer, String formatter) {
        return new FormatSpec(renderer, formatter);
    }

    public String getRenderer() {
        return renderer;
    }

    public String getFormatter() {
        return formatter;
    }

    public String format() {
        StringBuilder sb = new StringBuilder();
        if (!renderer.isBlank()) {
            sb.append(":").append(renderer);
            if (!formatter.isBlank()) {
                sb.append(":").append(formatter);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "FormatSpec{" + "renderer=" + renderer + ", formatter=" + formatter + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.renderer);
        hash = 17 * hash + Objects.hashCode(this.formatter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FormatSpec other = (FormatSpec) obj;
        if (!Objects.equals(this.renderer, other.renderer)) {
            return false;
        }
        return Objects.equals(this.formatter, other.formatter);
    }
}
