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
package org.tjc.jfx.jfxspreadsheet;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p>
 * Person class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public final class Person {

    private StringProperty firstName;

    /**
     * <p>
     * Constructor for Person.</p>
     *
     * @param firstName a {@link java.lang.String} object.
     * @param lastName  a {@link java.lang.String} object.
     */
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * <p>
     * Setter for the field <code>firstName</code>.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setFirstName(String value) {
        firstNameProperty().set(value);
    }

    /**
     * <p>
     * Getter for the field <code>firstName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFirstName() {
        return firstNameProperty().get();
    }

    /**
     * <p>
     * firstNameProperty.</p>
     *
     * @return a {@link javafx.beans.property.StringProperty} object.
     */
    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "firstName");
        }
        return firstName;
    }

    private StringProperty lastName;

    /**
     * <p>
     * Setter for the field <code>lastName</code>.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setLastName(String value) {
        lastNameProperty().set(value);
    }

    /**
     * <p>
     * Getter for the field <code>lastName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLastName() {
        return lastNameProperty().get();
    }

    /**
     * <p>
     * lastNameProperty.</p>
     *
     * @return a {@link javafx.beans.property.StringProperty} object.
     */
    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "lastName");
        }
        return lastName;
    }

    /**
     * <p>
     * generateExampleList.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public static List<Person> generateExampleList() {
        List<Person> members
                = List.of(new Person("William", "Reed"),
                        new Person("James", "Michaelson"),
                        new Person("Julius", "Dean"),
                        new Person("SomeReallyLargeFirstName", "SomeReallyLargeLastName"),
                        new Person("Thomas", "Clancy"),
                        new Person("Sherri", "Clancy"),
                        new Person("Another Really Long First Name", "A Really Long Last Name"));
        return members;
    }

}
