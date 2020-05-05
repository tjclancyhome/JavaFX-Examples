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
package org.tjc.jfx.jfxmlinteractivecanvas;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXMLInteractiveCanvasApp
 */
public class JFXMLInteractiveCanvasApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXMLInteractiveCanvasApp.class);

    private static final String FXML_LOCATION = "/fxml/JFXMLInteractiveCanvasScene.fxml";
    private static final String MAIN_CSS_LOCATION = "/css/styles.css";

    private JFXMLInteractiveCanvasSceneController controller;
    private Stage stage;

    public JFXMLInteractiveCanvasApp() {
        log.debug("### entered JFXMLInteractiveCanvasApp() constructor.");
        log.debug("### exited JFXMLInteractiveCanvasApp() constructor.");
    }

    @Override
    public void start(Stage stage) throws IOException {
        log.debug("### entered start(stage)");
        this.stage = stage;
        var fxmlLoader = createFxmlLoader(getClass().getResource(FXML_LOCATION));

        var parent = Parent.class.cast(fxmlLoader.load());
        log.debug("###     parent.class: {}", parent.getClass());

        controller = fxmlLoader.getController();
        var scene = setScene(new Scene(parent, 1034, 778, true, SceneAntialiasing.BALANCED));
        addStyleSheet(getClass().getResource(MAIN_CSS_LOCATION));
        controller.setScene(scene);
        stage.setTitle("JFXMLInteractiveCanvasApp");
        stage.setScene(scene);
        stage.show();
        log.debug("### exited start(stage)");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
