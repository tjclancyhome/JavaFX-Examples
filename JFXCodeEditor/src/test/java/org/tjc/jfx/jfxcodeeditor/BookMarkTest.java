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
package org.tjc.jfx.jfxcodeeditor;

import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tjclancy
 */
public class BookMarkTest {

    public BookMarkTest() {
    }

    /**
     * Test of getTitle method, of class BookMark.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTitle() throws Exception {
        BookMark bookMark = new BookMark("A title", new URL("http://https://openjfx.io"));
        assertEquals("A title", bookMark.getTitle());
    }

    /**
     * Test of getUrl method, of class BookMark.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUrl() throws Exception {
        BookMark bookMark = new BookMark("A title", new URL("http://https://openjfx.io"));
        assertEquals("http://https://openjfx.io", bookMark.getUrl().toExternalForm());
    }

    /**
     * Test of toString method, of class BookMark.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of hashCode method, of class BookMark.
     */
    @Test
    public void testHashCode() {
    }

    /**
     * Test of equals method, of class BookMark.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testEquals() throws Exception {
        BookMark bookMark1 = new BookMark("A title", new URL("http://https://openjfx.io"));
        BookMark bookMark2 = new BookMark("A title", new URL("http://https://openjfx.io"));
        assertTrue(bookMark1.equals(bookMark2));
        assertTrue(bookMark2.equals(bookMark1));
        assertTrue(bookMark1.equals(bookMark1));
        assertTrue(bookMark2.equals(bookMark2));
    }

}
