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
package org.tjc.jfx.jfxspreadsheet;

/**
 * <p>
 * Coord class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class Coord implements Formula {

    private int row;
    private int column;

    /**
     * <p>
     * Constructor for Coord.</p>
     *
     * @param row    a int.
     * @param column a int.
     */
    public Coord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * <p>
     * Getter for the field <code>row</code>.</p>
     *
     * @return a int.
     */
    public int getRow() {
        return row;
    }

    /**
     * <p>
     * Setter for the field <code>row</code>.</p>
     *
     * @param row a int.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * <p>
     * Getter for the field <code>column</code>.</p>
     *
     * @return a int.
     */
    public int getColumn() {
        return column;
    }

    /**
     * <p>
     * Setter for the field <code>column</code>.</p>
     *
     * @param column a int.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Character.toString('A' + column) + row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.row;
        hash = 17 * hash + this.column;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
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
        final Coord other = (Coord) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

}
