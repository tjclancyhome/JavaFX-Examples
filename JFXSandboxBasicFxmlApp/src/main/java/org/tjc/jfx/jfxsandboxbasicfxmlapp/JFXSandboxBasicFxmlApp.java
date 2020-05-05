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
package org.tjc.jfx.jfxsandboxbasicfxmlapp;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXSandboxBasicFxmlApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSandboxBasicFxmlApp extends JFXExamplesApplication {

    private static final Logger log = org.slf4j.LoggerFactory
            .getLogger(JFXSandboxBasicFxmlApp.class);

    private static Scene scene;
    private FXMLLoader loader;
    private JFXSandboxBasicFxmlAppSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = createFxmlLoader(getClass().getResource("JFXSandboxBasicFxmlAppScene.fxml"));
        scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        stage.setScene(scene);
        stage.show();
    }

    void setRoot(String fxml) throws IOException {
        var fxmlLoader = createFxmlLoader(getClass().getResource("JFXSandboxBasicFxmlAppScene.fxml"));
        scene.setRoot(fxmlLoader.load());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        log.debug("##### called Application.stop()");
    }

    private void handleExit() {
        scene.getWindow().hide();
        controller.dispose();
        Platform.exit();
    }

    /**
     * Main entry point.
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Shutdown hook")));
        launch();
    }

}
