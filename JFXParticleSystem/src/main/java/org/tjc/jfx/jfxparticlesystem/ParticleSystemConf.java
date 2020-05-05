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
package org.tjc.jfx.jfxparticlesystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class ParticleSystemConf {
    private final IntegerProperty numberOfParticlesProperty = new SimpleIntegerProperty();
    private final IntegerProperty durationProperty = new SimpleIntegerProperty();
    private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
    private final DoubleProperty opacityProperty = new SimpleDoubleProperty();
    private final BooleanProperty oscilateProperty = new SimpleBooleanProperty();
    private final BooleanProperty fadeOutProperty = new SimpleBooleanProperty();
    private final ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>();
    private final BooleanProperty cloneConfProperty = new SimpleBooleanProperty();

    @Override
    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDeclaresCloneNotSupported"})
    public ParticleSystemConf clone() {
        ParticleSystemConf newConf = new ParticleSystemConf();
        newConf.numberOfParticlesProperty.set(numberOfParticlesProperty.get());
        newConf.durationProperty.set(durationProperty.get());
        newConf.sizeProperty.set(sizeProperty.get());
        newConf.opacityProperty.set(opacityProperty.get());
        newConf.oscilateProperty.set(oscilateProperty.get());
        newConf.fadeOutProperty.set(fadeOutProperty.get());
        newConf.colorProperty.set(colorProperty.get());
        return newConf;
    }

    public IntegerProperty numberOfParticlesProperty() {
        return numberOfParticlesProperty;
    }

    public IntegerProperty durationProperty() {
        return durationProperty;
    }

    public DoubleProperty sizeProperty() {
        return sizeProperty;
    }

    public DoubleProperty opacityProperty() {
        return opacityProperty;
    }

    public BooleanProperty oscilateProperty() {
        return oscilateProperty;
    }

    public BooleanProperty fadeOutProperty() {
        return fadeOutProperty;
    }

    public ObjectProperty<Color> colorProperty() {
        return colorProperty;
    }

    public BooleanProperty cloneConfProperty() {
        return cloneConfProperty;
    }

    @Override
    public String toString() {
        return "ParticleSystemConf{"
            + "numberOfParticlesProperty=" + numberOfParticlesProperty
            + ", durationProperty=" + durationProperty
            + ", sizeProperty=" + sizeProperty
            + ", opacityProperty=" + opacityProperty
            + ", oscilateProperty=" + oscilateProperty
            + ", fadeOutProperty=" + fadeOutProperty
            + ", colorProperty=" + colorProperty
            + ", cloneConfProperty=" + cloneConfProperty + '}';
    }

}
