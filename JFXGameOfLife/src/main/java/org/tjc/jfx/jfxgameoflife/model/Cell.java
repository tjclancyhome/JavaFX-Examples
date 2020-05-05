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
package org.tjc.jfx.jfxgameoflife.model;

/**
 *
 * @author tjclancy
 */
public class Cell {
    private final int x;
    private final int y;
    private boolean isAlive;

    public Cell() {
        x = 0;
        y = 0;
        isAlive = false;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        isAlive = false;
    }

    public Cell(int x, int y, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    public static Cell newInstance() {
        return new Cell();
    }

    public static Cell newInstance(int x, int y) {
        return new Cell(x, y);
    }

    public static Cell newInstance(int x, int y, boolean isAlive) {
        return new Cell(x, y, isAlive);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRow() {
        return y;
    }

    public int getCol() {
        return x;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return "Cell(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
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
        final Cell other = (Cell) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
