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
package org.tjc.jfx.turtlesapp;

import java.time.Month;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
public class TextIoTest extends BaseUnitTest {
    private static final Logger log = LoggerFactory.getLogger(TurtleCanvasPane.class);

    private String user;
    private String password;
    private int age;
    private Month month;

    @BeforeEach
    public void setup() {
        user = "";
        password = "";
        age = 0;
        month = null;
        this.forceShowOutput();
    }

    @AfterEach
    public void tearDown() {
        this.restoreShowOutput();
    }

    @Test
    public void smokeTest() {
        writeBanner(methodName());

//        TextIO textIO = getTextIO();
//
//        TextTerminal terminal = textIO.getTextTerminal();
//
//        System.out.println(String.format("terminal: %s", terminal.getClass()));
//
//        terminal.getProperties().getAllKeys().forEach((var p) -> {
//            System.out.println(String.format("key: %s, val: %s", p, terminal.getProperties().getString(p
//                    .toString())));
//        });
//
//        terminal.printf("\nUser %s is %d years old, was born in %s and has the password %s.\n",
//                user, age, month, password);
//
//        String wait = textIO.newStringInputReader()
//                .withMaxLength(0)
//                .withMinLength(0)
//                .read("Press enter to exit");
//        terminal.abort();
//        terminal.dispose("terminal result data.");
//        textIO.dispose("textIO result data.");
    }

    @Test
    public void testJLineTextTerminal() {
        writeBanner(methodName());

//        TextIO textIO = getTextIO();
//        TextTerminal<JLineTextTerminal> terminal = (TextTerminal<JLineTextTerminal>) textIO.getTextTerminal();
//        System.out.println(String.format("terminal: %s", terminal.getClass()));
//        terminal.getProperties().getAllKeys().forEach((var p) -> {
//            System.out.println(String.format("key: %s, val: %s", p, terminal.getProperties().getString(p
//                    .toString())));
//        });
//
//        terminal.printf("\nUser %s is %d years old, was born in %s and has the password %s.\n",
//                user, age, month, password);
//
//        String wait = textIO.newStringInputReader()
//                .withMaxLength(0)
//                .withMinLength(0)
//                .read("Press enter to exit");
//        terminal.dispose();
//        textIO.dispose();
    }

//    private TextIO getTextIO() {
//        TextIO textIO = TextIoFactory.getTextIO();
//
//        user = textIO.newStringInputReader()
//                .withDefaultValue("admin")
//                .read("Username");
//
//        password = textIO.newStringInputReader()
//                .withMinLength(6)
//                .withInputMasking(true)
//                .read("Password");
//
//        age = textIO.newIntInputReader()
//                .withMinVal(13)
//                .read("Age");
//
//        month = textIO.newEnumInputReader(Month.class)
//                .read("What month were you born in?");
//
//        return textIO;
//    }
}
