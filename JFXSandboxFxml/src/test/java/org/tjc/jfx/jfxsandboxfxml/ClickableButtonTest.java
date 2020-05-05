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
package org.tjc.jfx.jfxsandboxfxml;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;

/**
 *
 * @author tjclancy
 */
public class ClickableButtonTest { //extends ApplicationTest {

    private Button button;

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage
     */
//    @Override
    public void start(Stage stage) {
        button = new Button("click me!");
        button.setOnAction((var actionEvent) -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }

    //@Test
    public void should_contain_button_with_text() {
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("click me!"));
    }

    //@Test
    public void when_button_is_clicked_text_changes() {
        // when:
//        clickOn(".button");

        // then:
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("clicked!"));
    }
}
