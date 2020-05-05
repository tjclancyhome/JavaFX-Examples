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
package org.tjc.jfx.jfxcanvasexample;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXCanvasExampleApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXCanvasExampleApp extends JFXFxmlExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXCanvasExampleApp.class);

    private Scene scene;
    //private FXMLLoader loader;
    private Stage stage;
    private JFXCanvasExampleSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws IOException {
        log.debug("##### entered Appliction.start(...)");
        this.stage = stage;

        FXMLLoader loader = createFxmlLoader(getClass().getResource("/fxml/JFXCanvasExampleScene.fxml"));
        log.debug("##### created loader: {}", loader);

        Parent root = loader.load();
        log.debug("##### loaded root: {}", root);

        controller = loader.getController();
        log.debug("##### got controller: {}", controller);
        controller.setStage(stage);

        root.getStylesheets().add("/css/styles.css");

        scene = new Scene(root);
        stage.setScene(scene);
        log.debug("##### Hold on to your hats, were going through...");
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        log.debug("##### called Application.stop()");
        handleExit();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void handleExit() {
        log.debug("##### entered handleExit()");

        log.debug("##### closing stage...");
        stage.close();

        log.debug("##### calling Platform.exit()...");
        Platform.exit();

        log.debug("##### Calling System.exit(0)...");
        System.exit(0);
    }

}
