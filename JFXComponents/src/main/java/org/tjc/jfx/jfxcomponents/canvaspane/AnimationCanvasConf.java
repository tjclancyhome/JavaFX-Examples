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
package org.tjc.jfx.jfxcomponents.canvaspane;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author tjclancy
 */
public class AnimationCanvasConf {
    private static final double DEFAULT_CANVAS_WIDTH = 800;
    private static final double DEFAULT_CANVAS_HEIGHT = 600;
    private static final Paint DEFAULT_CANVAS_FILL = Color.WHITE;
    private static final int DEFAULT_FRAMES = 30;

    private final DoubleProperty widthProperty;
    private final DoubleProperty heightProperty;
    private final ObjectProperty<Paint> canvasFillProperty;
    private final IntegerProperty framesProperty;

    public AnimationCanvasConf() {
        widthProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_WIDTH);
        heightProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_HEIGHT);
        canvasFillProperty = new SimpleObjectProperty<>(DEFAULT_CANVAS_FILL);
        framesProperty = new SimpleIntegerProperty(DEFAULT_FRAMES);

    }

    @Override
    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDeclaresCloneNotSupported"})
    public AnimationCanvasConf clone() {
        AnimationCanvasConf newConf = new AnimationCanvasConf();
        newConf.widthProperty.set(widthProperty.get());
        newConf.heightProperty.set(heightProperty.get());
        newConf.canvasFillProperty.set(canvasFillProperty.get());
        newConf.framesProperty.set(framesProperty.get());
        return newConf;
    }

    public DoubleProperty widthProperty() {
        return widthProperty;
    }

    public DoubleProperty heightProperty() {
        return heightProperty;
    }

    public ObjectProperty<Paint> canvasFillProperty() {
        return canvasFillProperty;
    }

    public IntegerProperty framesProperty() {
        return framesProperty;
    }

    @Override
    public String toString() {
        return "AnimatedCanvasConf{\n"
            + "  widthProperty=" + widthProperty.get() + ",\n"
            + "  heightProperty=" + heightProperty.get() + ",\n"
            + "  canvasFillProperty=" + canvasFillProperty.get() + ",\n"
            + "  framesProperty=" + framesProperty.get() + "\n"
            + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.widthProperty.get());
        hash = 79 * hash + Objects.hashCode(this.heightProperty.get());
        hash = 79 * hash + Objects.hashCode(this.canvasFillProperty.get());
        hash = 79 * hash + Objects.hashCode(this.framesProperty.get());
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
        final AnimationCanvasConf other = (AnimationCanvasConf) obj;
        if (!Objects.equals(this.widthProperty.get(), other.widthProperty.get())) {
            return false;
        }
        if (!Objects.equals(this.heightProperty.get(), other.heightProperty.get())) {
            return false;
        }
        if (!Objects.equals(this.canvasFillProperty.get(), other.canvasFillProperty.get())) {
            return false;
        }
        if (!Objects.equals(this.framesProperty.get(), other.framesProperty.get())) {
            return false;
        }
        return true;
    }

}
