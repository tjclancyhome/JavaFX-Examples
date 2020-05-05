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
import java.util.Objects;

/**
 * <p>
 * BookMark class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class BookMark {

    private final String title;
    private final URL url;

    /**
     * <p>
     * Constructor for BookMark.</p>
     *
     * @param title a {@link java.lang.String} object.
     * @param url   a {@link java.net.URL} object.
     */
    public BookMark(String title, URL url) {
        this.title = title;
        this.url = url;
    }

    /**
     * <p>
     * Getter for the field <code>title</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * Getter for the field <code>url</code>.</p>
     *
     * @return a {@link java.net.URL} object.
     */
    public URL getUrl() {
        return url;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BookMark{" + "title=" + title + ", url=" + url + '}';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + Objects.hashCode(this.url);
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
        final BookMark other = (BookMark) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.url, other.url);
    }
}
