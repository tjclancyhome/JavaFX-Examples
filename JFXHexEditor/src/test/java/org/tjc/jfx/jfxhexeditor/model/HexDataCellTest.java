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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxhexeditor.BaseHexEditorTest;

/**
 *
 * @author tjclancy
 */
public class HexDataCellTest extends BaseHexEditorTest {
    private static final Logger log = LoggerFactory.getLogger(HexDataCellTest.class);

    public HexDataCellTest() {
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
     * Test of newImmutableHexDataCell method, of class HexDataCell.
     */
    @Test
    public void testNewImmutableHexDataCell() {
        log.debug("### entered testNewImmutableHexDataCell()");
    }

    /**
     * Test of newMutableHexDataCell method, of class HexDataCell.
     */
    @Test
    public void testNewMutableHexDataCell() {
        log.debug("### entered testNewMutableHexDataCell()");
    }

    /**
     * Test of getHexByte method, of class HexDataCell.
     */
    @Test
    public void testGetHexByte() {
        log.debug("### entered testGetHexByte()");
    }

    /**
     * Test of getHexByteString method, of class HexDataCell.
     */
    @Test
    public void testGetHexByteString() {
        log.debug("### entered testGetHexByteString()");
    }

    /**
     * Test of toHex method, of class HexDataCell.
     */
    @Test
    public void testToHex() {
        log.debug("### entered testToHex()");
    }

    /**
     * Test of toUpper method, of class HexDataCell.
     */
    @Test
    public void testToUpper() {
        log.debug("### entered testToUpper()");
    }

    /**
     * Test of replace method, of class HexDataCell.
     */
    @Test
    public void testReplace() {
        log.debug("### entered testReplace()");
    }

    public class HexDataCellImpl extends HexDataCell {
        public int getHexByte() {
            return 0;
        }
    }

}
