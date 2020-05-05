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

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tjclancy
 */
public class NeighborCellsTest {

    public NeighborCellsTest() {
    }

    /**
     * Test of values method, of class NeighborCells.
     */
    @Test
    public void testValues() {
    }

    /**
     * Test of valueOf method, of class NeighborCells.
     */
    @Test
    public void testValueOf() {
    }

    /**
     * Test of apply method, of class NeighborCells.
     */
    @Test
    public void testApply() {
    }

    @Test
    public void smokeTest() {
        Cell origin = Cell.newInstance(5, 5);
        System.out.println(String.format("ORIGIN: %s", origin));
        System.out.println("==================================");
        System.out.println(
            String.format("  NORTH_WEST: %s", NeighborCells.NORTH_WEST.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  NORTH     : %s", NeighborCells.NORTH.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  NORTH_EAST: %s", NeighborCells.NORTH_EAST.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  EAST      : %s", NeighborCells.EAST.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  SOUTH_EAST: %s", NeighborCells.SOUTH_EAST.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  SOUTH     : %s", NeighborCells.SOUTH.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  SOUTH_WEST: %s", NeighborCells.SOUTH_WEST.apply(origin.getX(), origin.getY())));
        System.out.println(
            String.format("  WEST      : %s", NeighborCells.WEST.apply(origin.getX(), origin.getY())));
    }

    @Test
    public void smokeTest2() {
        Cell origin = Cell.newInstance(5, 5);
        System.out.println(String.format("ORIGIN: %s", origin));
        System.out.println("==================================");
        NeighborCells[] neighbors = NeighborCells.values();
        assertEquals(8, neighbors.length);
        for (int i = 0; i < 8; i++) {

        }
    }

}
