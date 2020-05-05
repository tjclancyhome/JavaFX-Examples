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
package org.tjc.jfx.jfxrootexample;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXRootExampleApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRootExampleApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXRootExampleApp.class);

    private JFXRootExampleAppSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        log.debug("### Entered start(Stage)");

        var fxmlLoader = createFxmlLoader(getClass().getResource("JFXRootExampleAppScene.fxml"));
        controller = fxmlLoader.getController();

        var scene = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFXRootExampleApp");
        primaryStage.show();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch();
    }

}
