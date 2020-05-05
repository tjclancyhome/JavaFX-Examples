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

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.tjc.common.unittest.BaseUnitTest;

import static org.tjc.common.unittest.UnitTestSupport.thisMethodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

//@Disabled
@ExtendWith(ApplicationExtension.class)
class ClickableButtonTest_JUnit5AssertJ extends BaseUnitTest {
    private static final Logger log = LoggerFactory.getLogger(TestFXTest.class);

    private Button button;

    @BeforeEach
    public void startup() {
        this.forceShowOutput();
    }

    @AfterEach
    public void cleanup() {
        this.restoreShowOutput();
    }

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) {
        writeBanner(thisMethodName());
        log.debug("### entered start()");
        button = new Button("click me!");
        button.setId("myButton");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
        log.debug("### exited start()");
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void should_contain_button_with_text(FxRobot robot) {
        log.debug("### entered when_button_is_clicked_text_changes()");
        log.debug("### entered should_contain_button_with_text()");

        System.out.println("### entered should_contain_button_with_text()");

        Assertions.assertThat(button).hasText("click me!");
        // or (lookup by css id):
        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("click me!");
        // or (lookup by css class):
        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("click me!");
        // or (query specific type):
        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("click me!");
        log.debug("### exited should_contain_button_with_text()");

    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void when_button_is_clicked_text_changes(FxRobot robot) {
        log.debug("### entered when_button_is_clicked_text_changes()");
        log.debug("### entered when_button_is_clicked_text_changes()");

        System.out.println("### entered when_button_is_clicked_text_changes()");

        // when:
        robot.clickOn(".button");

        // then:
        Assertions.assertThat(button).hasText("clicked!");
        // or (lookup by css id):
        Assertions.assertThat(robot.lookup("#myButton").queryAs(Button.class)).hasText("clicked!");
        // or (lookup by css class):
        Assertions.assertThat(robot.lookup(".button").queryAs(Button.class)).hasText("clicked!");
        // or (query specific type)
        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("clicked!");
        log.debug("### exited when_button_is_clicked_text_changes()");

    }
}
