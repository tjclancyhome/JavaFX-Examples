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
package org.tjc.jfx.jfxgraphviz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

@Disabled
public class ApplicationLaunchTest extends FxRobot {

    static Button button;

    public static class DemoApplication extends Application {
        @Override
        public void start(Stage stage) {
            button = new Button("click me!");
            button.setOnAction(actionEvent -> button.setText("clicked!"));
            stage.setScene(new Scene(new StackPane(button), 100, 100));
            stage.show();
            stage.setAlwaysOnTop(true);
        }
    }

    @BeforeEach
    public void setup() throws Exception {
        ApplicationTest.launch(DemoApplication.class);
    }

    @AfterEach
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    public void should_contain_button() {
        // expect:
        assertThat(lookup(".button").queryButton()).hasText("click me!");
        assertThat(button).hasText("click me!");
        verifyThat(".button", hasText("click me!"), informedErrorMessage(this));
    }

    @Test
    public void should_click_on_button() {
        // when:
        clickOn(".button");

        // then:
        assertThat(lookup(".button").queryButton()).hasText("clicked!");
        assertThat(button).hasText("clicked!");
        verifyThat(".button", hasText("clicked!"), informedErrorMessage(this));
    }

}
