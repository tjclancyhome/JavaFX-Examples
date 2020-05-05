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
package org.tjc.jfx.jfxcomponents.border;

import java.util.Objects;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;

/**
 *
 * @author tjclancy
 */
public class BorderToggler {
    private final Border border;
    private final Region region;

    public BorderToggler(Border border, Region region) {
        this.border = border;
        this.region = region;
        this.region.setBorder(this.border);
//        this.region.setBorder(Border.EMPTY);
//        this.region.setBorder(
//            new Border(
//                new BorderStroke(Color.RED, BorderStrokeStyle.DASHED, new CornerRadii(0.5), BorderWidths.FULL)));
    }

    public Region toggleOn() {
        region.setBorder(border);
        return region;
    }

    public Region toggleOff() {
        region.setBorder(Border.EMPTY);
        return region;
    }

    public Border getBorder() {
        return border;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "BorderToggler{" + "border=" + border + ", region=" + region + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.border);
        hash = 53 * hash + Objects.hashCode(this.region);
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
        final BorderToggler other = (BorderToggler) obj;
        if (!Objects.equals(this.border, other.border)) {
            return false;
        }
        return Objects.equals(this.region, other.region);
    }

}
