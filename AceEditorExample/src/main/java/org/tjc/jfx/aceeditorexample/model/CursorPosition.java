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
package org.tjc.jfx.aceeditorexample.model;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class CursorPosition {
    private static final Logger log = LoggerFactory.getLogger(CursorPosition.class);

    private final IntegerProperty rowProperty;
    private final IntegerProperty columnProperty;
    private final ObjectProperty<Number> rowObjectProperty;
    private final ObjectProperty<Number> columnObjectProperty;

    public CursorPosition() {
        rowProperty = new SimpleIntegerProperty(0);
        columnProperty = new SimpleIntegerProperty(0);
        rowObjectProperty = new SimpleObjectProperty<>();
        columnObjectProperty = new SimpleObjectProperty<>();
    }

    public CursorPosition(int row, int column) {
        this();
        rowProperty.set(row);
        columnProperty.set(column);
        rowObjectProperty.set(row);
        columnObjectProperty.set(column);
    }

    public CursorPosition(Number row, Number column) {
        this();
        this.rowObjectProperty.set(row);
        this.columnObjectProperty.set(column);
    }

    public CursorPosition(JSObject jsObject) {
        this();
        Objects.requireNonNull(jsObject, () -> "The 'jsObject' argument is null.");
        log.debug("### entered CursorPosition(): jsObject: {}", jsObject);

        var row = jsObject.getMember("row");
        var column = jsObject.getMember("column");

        if (row != null && column != null) {
            log.debug("###     row   : {}, type: {}", row, row.getClass());
            log.debug("###     column: {}, type: {}", column, column.getClass());

            if (row instanceof Integer) {
                rowObjectProperty.set(Integer.class.cast(row));
                rowProperty.set(Integer.class.cast(row));
            } else if (row instanceof Double) {
                rowObjectProperty.set(Double.class.cast(row));
            }
            log.debug("###     rowProperty         : {}", rowProperty);
            log.debug("###     rowObjectProperty   : {}", rowObjectProperty);

            if (column instanceof Integer) {
                columnObjectProperty.set(Integer.class.cast(column));
                columnProperty.set(Integer.class.cast(row));
            } else if (column instanceof Double) {
                columnObjectProperty.set(Double.class.cast(row));
            }
            log.debug("###     columnProperty      : {}", columnProperty);
            log.debug("###     columnObjectProperty: {}", columnObjectProperty);
        } else {
            Objects.requireNonNull(row, () -> "The 'row' object is null.");
            Objects.requireNonNull(column, () -> "The 'column' object is null.");
        }
        log.debug("### exiting CursorPosition()");
    }

    public ObjectProperty<Number> rowObjectProperty() {
        return rowObjectProperty;
    }

    public void setRowObjectValue(Number number) {
        rowObjectProperty.set(number);
    }

    public Number getRowObjecValue() {
        return rowObjectProperty.get();
    }

    public ObjectProperty<Number> columnObjectProperty() {
        return columnObjectProperty;
    }

    public void setColumnObjectValue(Number number) {
        columnObjectProperty.set(number);
    }

    public Number getColumnObjectValue() {
        return columnObjectProperty.get();
    }

    public IntegerProperty rowProperty() {
        return rowProperty;
    }

    public IntegerProperty columnProperty() {
        return columnProperty;
    }

    /**
     * Returns the row as an int representing the row of the cursor in the editor.
     *
     * @return The row as integer.
     */
    public int getRow() {
        return rowProperty.get();
    }

    /**
     * Returns the column as an int representing the column of the cursor in the editor.
     *
     * @return The column as integer.
     */
    public int getColumn() {
        return columnProperty.get();
    }

    @Override
    public String toString() {
        return "CursorPosition{" + "row=" + rowProperty + ", col=" + columnProperty + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.rowProperty.get();
        hash = 73 * hash + this.columnProperty.get();
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
        final CursorPosition other = (CursorPosition) obj;
        if (this.rowProperty != other.rowProperty) {
            return false;
        }
        return this.columnProperty == other.columnProperty;
    }

}
