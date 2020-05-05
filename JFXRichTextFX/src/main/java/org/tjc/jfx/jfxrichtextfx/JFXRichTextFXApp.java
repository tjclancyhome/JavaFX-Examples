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
package org.tjc.jfx.jfxrichtextfx;

import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX App
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRichTextFXApp extends JFXFxmlExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXRichTextFXApp.class);

    private static Scene scene;
    private JFXRichTextFXSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("JFXRichTextFXScene.fxml"));

        addStylesheetsToScene("css/xml-highlighting.css", "css/java-keywords.css");
        controller.setPrimaryStage(stage);
        stage.setScene(scene);
        stage.setTitle("JFXRichTextFX");
        stage.show();
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(JFXRichTextFXApp.class.getResource(fxml));
//        return fxmlLoader.load();
//    }
    private void addStylesheetToScene(String stylesheetPath) {
        scene.getStylesheets().add(JFXRichTextFXApp.class.getResource(stylesheetPath)
                .toExternalForm());
    }

    private void addStylesheetsToScene(String... stylesheetPaths) {
        if (stylesheetPaths.length > 0) {
            Arrays.asList(stylesheetPaths).forEach((var stylesheetPath) -> {
                addStylesheetToScene(stylesheetPath);
            });
        }
    }

    private Parent loadFXML(String fxml) throws IOException {
        log.debug("##### Entered loadFXML(String): fxml: {}", fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(JFXRichTextFXApp.class.getResource(fxml));
        log.debug("##### Created fxmlLoader: {}", fxmlLoader);
        Parent p = fxmlLoader.load();
        log.debug("##### Loaded parent: {}", p);
        controller = fxmlLoader.getController();
        log.debug("##### Got controller from loader: {}", controller);
        return p;
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
