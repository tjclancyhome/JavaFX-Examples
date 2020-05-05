/*
 * The MIT License
 *
 * Copyright 2019 tjc.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.tjc.jfx.jfxsandboxfxml;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXSandboxFxmlApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSandboxFxmlApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXSandboxFxmlApp.class);

    private static Scene scene;
    private FXMLLoader loader;
    private JFXSandboxFxmlSceneController controller;

    /**
     * {@inheritDoc}
     * <p>
     * Starts initialization of the JavaFX
     */
    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = createFxmlLoader(getClass().getResource("JFXSandboxFxmlScene.fxml"));
        scene = new Scene(fxmlLoader.load());

        controller = fxmlLoader.getController();

        stage.setScene(scene);
        stage.setTitle("JavaFX 12 Example");

        stage.setOnCloseRequest((var event) -> {
            log.debug("### onCloseRequest(): event: {}", event);
            handleExit();
        });

        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        log.debug("### called Application.stop()");
    }

    private void handleExit() {
        scene.getWindow().hide();
        controller.dispose();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Main entry point.
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
