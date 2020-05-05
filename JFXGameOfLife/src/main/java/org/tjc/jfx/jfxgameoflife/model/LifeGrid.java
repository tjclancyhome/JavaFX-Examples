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

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxgameoflife.model.Cell;
import org.tjc.jfx.jfxgameoflife.model.LifeGrid;
import org.tjc.jfx.jfxgameoflife.model.NeighborCells;

/**
 *
 * @author tjclancy
 */
public class LifeGrid {
    private static final Logger log = LoggerFactory.getLogger(LifeGrid.class);

    private static final int ALIVE_CELL = 1;
    private static final int DEAD_CELL = 0;
    private static final int EMPTY_CELL = 0;
//    private static final int EMPTY_CELL = 0;
    private static final int DEFAULT_NUMBER_HORIZONTAL_CELLS = 20;
    private static final int DEFAULT_NUMBER_VERTICAL_CELLS = 20;

    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 500;
    public static final int DEFAULT_CELLSIZE = 10;

    private final int width;
    private final int height;
    private final int cellSize;
    private int[][] grid;
    private int rows;
    private int cols;
    private Random random;
    private long randomSeed;
    private int numberOfHorizontalCells;
    private int numberOfVerticalCells;
    private double calculatedWidth;
    private double calculatedHeight;

    private final GraphicsContext graphics;

    public LifeGrid(GraphicsContext graphics) {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_CELLSIZE, graphics);
    }

    public LifeGrid(int width, int height, GraphicsContext graphics) {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_CELLSIZE, graphics);
    }

    public LifeGrid(int width, int height, int cellSize, GraphicsContext graphics) {
        this(width, height, cellSize, System.currentTimeMillis(), graphics);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public LifeGrid(int width, int height, int cellSize, long randomSeed, GraphicsContext graphics) {
        log.debug("### entered LifeGrid() constructor: width: {}, height: {}, cellSize: {}, randomSeed: {}",
            width, height, cellSize, randomSeed);
        Objects.requireNonNull(graphics, () -> "The 'graphics' argument is null.");
        this.numberOfHorizontalCells = DEFAULT_NUMBER_HORIZONTAL_CELLS;
        this.numberOfVerticalCells = DEFAULT_NUMBER_VERTICAL_CELLS;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.calculatedWidth = this.cellSize * this.numberOfHorizontalCells;
        this.calculatedHeight = this.cellSize * this.numberOfVerticalCells;
        this.graphics = graphics;
        this.randomSeed = randomSeed;
        this.random = new Random(this.randomSeed);
        this.rows = (int) Math.floor(this.height / this.cellSize);
        this.cols = (int) Math.floor(this.width / this.cellSize);
        this.grid = new int[cols][rows];
        this.debugPrintGrid();
        this.clear();
        log.debug("### exited LifeGrid() constructor.");
    }

    public static LifeGrid newInstance(GraphicsContext gc) {
        return new LifeGrid(gc);
    }

    public void clear() {
        clearCells();
        draw();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }

    public void init() {
        StopWatch sw = new StopWatch();

        if (log.isDebugEnabled()) {
            sw.start();
        }
        loopOverGrid(randomOnOffCell);

        if (log.isDebugEnabled()) {
            sw.stop();
            log.debug("### sw.toString: {}", sw.toString());
            log.debug("### sw time in seconds: {}", sw.getTime(TimeUnit.SECONDS));
            log.debug("### sw time in milliseconds: {}", sw.getTime(TimeUnit.MILLISECONDS));
            debugPrintGrid();
        }
        draw();
    }

    public void start() {
        log.debug("### entered start()");
        liveNeighborsOf(5, 5);
        log.debug("### exited start()");
    }

    public void stop() {
        log.debug("### entered stop()");
        log.debug("### exited stop()");
    }

    public void loopOverGrid(BiConsumer<Integer, Integer> consumer) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                consumer.accept(c, r);
            }
        }
    }

    public int liveNeighborsOf(int row, int col) {
        log.debug("### entered liveNeighborsOf(): row: {}, col: {}", row, col);
        int liveNeighbors = 0;
        int x = col;
        int y = row;
        List<Cell> neighbors = getNeighbors(x, y);
        log.debug("### exited liveNeighborsOf()");
        return liveNeighbors;
    }

    private List<Cell> getNeighbors(int x, int y) {
        log.debug("### entered getNeighbors(): x: {}, y: {}", x, y);
        List<Cell> neighbors = new ArrayList<>();

        long size = Stream.of(NeighborCells.values()).count();
        log.debug("###    number of neighbor cells: {}", size);
        List<Cell> nbc
            = Stream.of(NeighborCells.values())
                .map(nc -> nc.apply(x, y))
                .filter(nc -> isAlive(nc.getX(), nc.getY()))
                .collect(Collectors.toList());

        nbc.forEach(nc -> log.debug("### neighbor cell: {}", nc));

//        for (int i = 0; i < NEIGHBORS; i++) {
//
//        }
        log.debug("### exited getNeighbors()");
        return neighbors;
    }

    private boolean isNegative(int a) {
        return a < 0;
    }

    private boolean isPositive(int a) {
        return a >= 0;
    }

    private boolean isAlive(int x, int y) {
        int cellLoc = grid[y][x];
        return cellLoc == 1;
    }

    private void draw() {
        log.debug("### entered draw()");
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == ALIVE_CELL) {
                    /*
                     * first rect will end up becoming the border
                     */
                    graphics.setFill(Color.gray(0.5, 1.0));
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.WHITE);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                } else {
                    /*
                     * DEAD_CELL
                     */
                    graphics.setFill(Color.gray(0.5, 1.0));
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.setFill(Color.BLACK);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
        log.debug("### exited draw()");
    }

    private void clearCells() {
        loopOverGrid(clearCell);
    }

    private final BiConsumer<Integer, Integer> clearCell = (row, col) -> {
        grid[row][col] = EMPTY_CELL;
    };

    private final BiConsumer<Integer, Integer> randomOnOffCell = (row, col) -> {
        grid[row][col] = random.nextInt(2);
    };

    private final BiConsumer<Integer, Integer> resurectCell = (row, col) -> {
        grid[row][col] = ALIVE_CELL;
    };

    private final BiConsumer<Integer, Integer> killCell = (row, col) -> {
        grid[row][col] = DEAD_CELL;
    };

    @Override
    public String toString() {
        return "LifeField{"
            + "width=" + width
            + ", height=" + height
            + ", cellSize=" + cellSize
            + ", grid=" + Arrays.toString(grid)
            + ", rows=" + rows
            + ", cols=" + cols
            + ", graphixContext=" + graphics + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.width;
        hash = 19 * hash + this.height;
        hash = 19 * hash + this.cellSize;
        hash = 19 * hash + this.rows;
        hash = 19 * hash + this.cols;
        return hash;
    }

    @Override
    //@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
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
        final LifeGrid other = (LifeGrid) obj;
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.cellSize != other.cellSize) {
            return false;
        }
        if (this.rows != other.rows) {
            return false;
        }
        return this.cols == other.cols;
    }

    private void debugPrintGrid() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("===============================================");
            log.debug("Printing Grid");
            log.debug("===============================================");
            log.debug("### width                  : {}", width);
            log.debug("### height                 : {}", height);
            log.debug("### cellSize               : {}", cellSize);
            log.debug("### grid                   : {}", asList(grid));
            log.debug("### rows                   : {}", rows);
            log.debug("### cols                   : {}", cols);
            log.debug("### randomSeed             : {}", randomSeed);
            log.debug("### calculatedHeight       : {}", this.calculatedHeight);
            log.debug("### calculatedWidth        : {}", this.calculatedWidth);
            log.debug("### numberOfHorizontalCells: {}", this.numberOfHorizontalCells);
            log.debug("### numberOfVericalCells   : {}", this.numberOfVerticalCells);
            log.debug("===============================================");
            log.debug("Done Printing Grid");
            log.debug("===============================================");
            log.debug("###");
        }
    }
}
