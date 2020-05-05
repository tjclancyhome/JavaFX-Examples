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
package org.tjc.jfx.jfxregexapp;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * <p>
 * JFXRegExApp class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRegExApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXRegExApp.class);

    private JFXRegExAppSceneController controller;

    /**
     * {@inheritDoc}
     *
     * Called by JavaFX Application. This loads the root parent from the /fxml/JFXRegExAppScene.fxml
     * file.
     */
    @Override
    public void start(Stage stage) throws Exception {
        log.debug("### entered start(Stage)");

        FXMLLoader fxmlLoader = createFxmlLoader(getClass().getResource(
                "/fxml/JFXRegExAppScene.fxml"));

        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/styles.css");
        controller.setScene(scene);
        stage.setTitle("JFXRegExApp");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main() serves only as
     * fallback in case the application can not be launched through deployment artifacts, e.g., in
     * IDEs with limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
