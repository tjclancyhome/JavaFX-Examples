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
package org.tjc.jfx.jfxturtles;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXTurtlesApp
 */
public class JFXTurtlesApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXTurtlesApp.class);
    private JFXTurtlesSceneController controller;
    private Scene scene;
    private Group rootGroup;

    @Override
    public void start(Stage stage) throws IOException {
        log.debug("### entered init(Stage)");
        FXMLLoader fxmlLoader = createFxmlLoader(getClass()
            .getResource("/fxml/JFXTurtlesScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();

//        scene = new Scene(parent, 800, 600, true, SceneAntialiasing.BALANCED);
        scene = new Scene(parent);

        URL stylesheet = getClass().getResource("/css/styles.css");
        if (stylesheet != null) {
            boolean added = scene.getStylesheets().add(stylesheet.toExternalForm());
            log.debug("### Stylesheet: '{}' added to scene: {}", stylesheet.toString(), added);

        }
        controller.setScene(scene);
        stage.setScene(scene);
        stage.show();
        controller.showAxis();
        log.debug("### exited init(Stage)");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
