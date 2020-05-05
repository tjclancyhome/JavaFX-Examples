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
public enum NeighborCells {

    NORTH_WEST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x - 1, y - 1);
        }
    },
    NORTH {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x, y - 1);
        }
    },
    NORTH_EAST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x + 1, y - 1);
        }
    },
    EAST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x + 1, y);
        }
    },
    SOUTH_EAST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x + 1, y + 1);
        }
    },
    SOUTH {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x + 1, y);
        }
    },
    SOUTH_WEST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x - 1, y + 1);
        }
    },
    WEST {
        @Override
        public Cell apply(int x, int y) {
            return Cell.newInstance(x, y - 1);
        }
    };

    public abstract Cell apply(int x, int y);
}
