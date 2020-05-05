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
package org.tjc.jfx.jfxpropertiesandlisteners;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tjclancy
 */
public class JFXPropertiesAndListernersAppTest {

    private static IntegerProperty intProperty;

    public JFXPropertiesAndListernersAppTest() {
    }

    @Test
    public void smokeTest() {
        createProperty();
        addAndRemoveInvalidationListener();
        addAndRemoveChangeListener();
        bindAndUnbindOnePropertyToAnother();
    }

    @Test
    public void smokeTest2() {
        System.out.println();
        System.out.println("smokeTest2()");
        System.out.println("------------");
    }

    private static void createProperty() {
        System.out.println();
        System.out.println("createProperty()");
        System.out.println("---------------");

        intProperty = new SimpleIntegerProperty(1024);
        System.out.println("intProperty = " + intProperty);
        System.out.println("intProperty.get() = " + intProperty.get());
        System.out.println("intProperty.getValue() = " + intProperty.getValue());
    }

    private static void addAndRemoveInvalidationListener() {
        System.out.println();
        System.out.println("addAndRemoveInvalidationListener()");
        System.out.println("----------------------------------");
        final InvalidationListener invalidationListener = observable
                -> System.out.println("The observable has been invalidated: " + observable + ".");

        intProperty.addListener(invalidationListener);
        System.out.println("Added invalidation listener.");

        System.out.println("Calling intProperty.set(2048).");
        intProperty.set(2048);

        System.out.println("Calling intProperty.setValue(3072).");
        intProperty.setValue(3072);

        intProperty.removeListener(invalidationListener);
        System.out.println("Removed invalidation listener.");

        System.out.println("Calling intProperty.set(4096).");
        intProperty.set(4096);
    }

    private static void addAndRemoveChangeListener() {
        System.out.println();
        System.out.println("addAndRemoveChangeListener()");
        System.out.println("----------------------------");
        final ChangeListener changeListener = (ObservableValue observableValue, Object oldValue,
                Object newValue)
                -> System.out.println(
                        "The observableValue has changed: oldValue = " + oldValue + ", newValue = " + newValue);

        intProperty.addListener(changeListener);
        System.out.println("Added change listener.");

        System.out.println("Calling intProperty.set(5120).");
        intProperty.set(5120);

        intProperty.removeListener(changeListener);
        System.out.println("Removed change listener.");

        System.out.println("Calling intProperty.set(6144).");
        intProperty.set(6144);
    }

    private static void bindAndUnbindOnePropertyToAnother() {
        System.out.println();
        System.out.println("bindAndUnbindOnePropertyToAnother()");
        System.out.println("-----------------------------------");

        IntegerProperty otherProperty = new SimpleIntegerProperty(0);
        System.out.println("otherProperty.get() = " + otherProperty.get());

        System.out.println("Binding otherProperty to intProperty.");
        otherProperty.bind(intProperty);
        System.out.println("otherProperty.get() = " + otherProperty.get());

        System.out.println("Calling intProperty.set(7168).");
        intProperty.set(7168);
        System.out.println("otherProperty.get() = " + otherProperty.get());

        System.out.println("Unbinding otherProperty from intProperty.");
        otherProperty.unbind();
        System.out.println("otherProperty.get() = " + otherProperty.get());

        System.out.println("Calling intProperty.set(8192).");
        intProperty.set(8192);
        System.out.println("otherProperty.get() = " + otherProperty.get());
    }

}
