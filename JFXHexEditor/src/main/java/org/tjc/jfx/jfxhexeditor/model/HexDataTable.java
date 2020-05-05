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
package org.tjc.jfx.jfxhexeditor.model;

import java.util.Objects;
import javafx.scene.control.TableView;

/**
 *
 * @author tjclancy
 */
public class HexDataTable extends DataTable {
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 25;
    private TableView<HexDataCell> tableView;

    public HexDataTable(TableView<HexDataCell> tableView) {
        this(tableView, DEFAULT_NUMBER_OF_COLUMNS);
    }

    public HexDataTable(TableView<HexDataCell> tableView, int numberOfColums) {
        super(numberOfColums);
        Objects.requireNonNull(tableView, () -> "The 'tableView' argument is null.");
        this.tableView = tableView;
        initialize();
    }

    private void initialize() {
        tableView.getColumns();
    }

}
