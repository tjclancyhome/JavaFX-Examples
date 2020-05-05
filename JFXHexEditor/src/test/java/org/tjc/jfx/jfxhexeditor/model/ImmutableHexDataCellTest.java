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

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxhexeditor.BaseHexEditorTest;

/**
 *
 * @author tjclancy
 */
public class ImmutableHexDataCellTest extends BaseHexEditorTest {
    private static final Logger log = LoggerFactory.getLogger(ImmutableHexDataCellTest.class);

    public ImmutableHexDataCellTest() {
        System.out.println("Logging Level: " + this.getLoggingLevel());
    }

    @BeforeEach
    public void beforeEach() {
        setLoggingLevel(Level.INFO);
    }

    @AfterEach
    public void afterEach() {
        log.debug("### --------------------------------------------------------");
    }

    /**
     * Test of getHexByte method, of class ImmutableHexDataCell.
     */
    @Test
    public void testGetHexByte() {
        log.debug("### entered testGetHexByte()");
        int expected = 255;
        ImmutableHexDataCell hexDataCell = HexDataCell.newImmutableHexDataCell(expected);
        int actual = hexDataCell.getHexByte();
        assertEquals(expected, actual);
    }

    /**
     * Test of hexByteIntegerProperty method, of class ImmutableHexDataCell.
     */
    @Test
    public void testHexByteIntegerProperty() {
        log.debug("### entered testHexByteIntegerProperty()");
        int expected = 255;
        ImmutableHexDataCell hexDataCell = HexDataCell.newImmutableHexDataCell(expected);
        assertNotNull(hexDataCell.hexByteIntegerProperty());
        int actual = hexDataCell.hexByteIntegerProperty().get();
        assertEquals(expected, actual);
    }

}
