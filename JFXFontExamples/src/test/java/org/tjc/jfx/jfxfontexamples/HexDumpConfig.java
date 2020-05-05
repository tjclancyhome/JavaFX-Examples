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
package org.tjc.jfx.jfxfontexamples;

/**
 *
 * @author tjclancy
 */
public class HexDumpConfig {

    public static final HexDumpConfig DEFAULT_CONFIG = new HexDumpConfig(16);

    private final int columns;
    private final int rows;
    private final int startOffset;

    public HexDumpConfig(int columns) {
        this.columns = columns;
        this.rows = -1;
        this.startOffset = 0;
    }

    public HexDumpConfig(int columns, int rows, int startOffset) {
        this.columns = columns;
        this.rows = rows;
        this.startOffset = startOffset;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getStartOffset() {
        return startOffset;
    }

    @Override
    public String toString() {
        return "HexDumpConfig{" + "columns=" + columns + ", rows=" + rows + ", startOffset=" + startOffset + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.columns;
        hash = 79 * hash + this.rows;
        hash = 79 * hash + this.startOffset;
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
        final HexDumpConfig other = (HexDumpConfig) obj;
        if (this.columns != other.columns) {
            return false;
        }
        if (this.rows != other.rows) {
            return false;
        }
        return this.startOffset == other.startOffset;
    }

}
