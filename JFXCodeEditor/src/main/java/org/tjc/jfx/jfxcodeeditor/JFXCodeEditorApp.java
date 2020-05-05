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
package org.tjc.jfx.jfxcodeeditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXCodeEditorApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXCodeEditorApp extends JFXFxmlExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXCodeEditorApp.class);
    private static final String FXML_PATH = "/fxml/";
    private JFXCodeEditorAppSceneController controller;
    private Scene scene;

    /**
     * {@inheritDoc}
     *
     * This creates a scene by loading an FXML file setting the primary stage with it.
     */
    @Override
    public void start(Stage stage) throws Exception {
        log.debug("##### Entered start(Stage): stage: {}", stage);
        FXMLLoader fxmlLoader = createFxmlLoader(getClass().getResource(
                "/fxml/JFXCodeEditorAppScene.fxml"));

        scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();

        if (log.isDebugEnabled()) {
            scene.getStylesheets().forEach(s -> log.debug("##### before add... stylesheet: {}", s));
        }

        if (!scene.getStylesheets().add("/styles/styles.css")) {
            throw new Exception("Could not add stylesheet '/styles/styles.css' to the scene.");
        }

        if (log.isDebugEnabled()) {
            scene.getStylesheets().forEach(s -> log.debug("##### after add... stylesheet: {}", s));
        }

        stage.setScene(scene);
        controller.setScene(scene);
        stage.setTitle("JFXCodeEditorApp");
        stage.show();
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
