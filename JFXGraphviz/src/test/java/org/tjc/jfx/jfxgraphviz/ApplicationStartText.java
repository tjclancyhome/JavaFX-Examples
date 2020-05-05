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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.beans.InvalidationListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import static org.testfx.util.WaitForAsyncUtils.waitForAsyncFx;

@Disabled
class ApplicationStartTest extends ApplicationTest {

    private Button button;
    private CountDownLatch buttonClickedLatch;

    @Override
    public void init() throws Exception {
        FxToolkit.registerStage(Stage::new);
    }

    @Override
    public void start(Stage stage) {
        CountDownLatch setSceneLatch = new CountDownLatch(1);
        InvalidationListener invalidationListener = observable -> setSceneLatch.countDown();
        stage.sceneProperty().addListener(observable -> {
            setSceneLatch.countDown();
            stage.sceneProperty().removeListener(invalidationListener);
        });
        buttonClickedLatch = new CountDownLatch(1);
        button = new Button("click me!");
        button.setOnAction(actionEvent -> {
            button.setText("clicked!");
            buttonClickedLatch.countDown();
        });
        stage.setScene(new Scene(button, 100, 100));
        try {
            if (!setSceneLatch.await(10, TimeUnit.SECONDS)) {
                fail("Timeout while waiting for scene to be set on stage.");
            }
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    void should_contain_button() {
        // expect:
        verifyThat(".button", hasText("click me!"), informedErrorMessage(this));
    }

    @Test
    void should_click_on_button() throws InterruptedException {
        // when:
        moveTo(".button");
        press(MouseButton.PRIMARY);
        release(MouseButton.PRIMARY);

        buttonClickedLatch.await(5, TimeUnit.SECONDS);
        verifyThat(waitForAsyncFx(3000, () -> button.getText()), equalTo("clicked!"), informedErrorMessage(
            this));
    }
}
