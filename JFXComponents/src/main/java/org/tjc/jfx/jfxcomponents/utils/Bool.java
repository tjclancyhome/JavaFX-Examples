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
package org.tjc.jfx.jfxcomponents.utils;

/**
 *
 * @author tjclancy
 */
public class Bool {

    /**
     * Returns not b
     *
     * @param b Input boolean to negate.
     *
     * @return Returns true or false.
     */
    public static boolean not(boolean b) {
        return !b;
    }

    /**
     * Returns a and b.
     *
     * @param a The first boolean of this binary funtion.
     * @param b The second boolean of this binary funtion.
     *
     * @return Returns true or false.
     */
    public static boolean and(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Returns a or b.
     *
     * @param a The first boolean of this binary funtion.
     * @param b The second boolean of this binary funtion.
     *
     * @return Returns true or false.
     */
    public static boolean or(boolean a, boolean b) {
        return a || b;
    }
}
