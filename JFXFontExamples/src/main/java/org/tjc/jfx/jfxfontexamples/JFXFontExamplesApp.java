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
package org.tjc.jfx.jfxfontexamples;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX App
 */
public class JFXFontExamplesApp extends JFXFxmlExamplesApplication {

    private Scene scene;
    private JFXFontExamplesSceneController controller;

    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = createFxmlLoader(getClass().getResource("/fxml/JFXFontExamplesScene.fxml"));

        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        scene = new Scene(root);
        controller.setScene(scene);
        scene.getStylesheets().add("/css/styles.css");
        stage.setScene(scene);
        stage.setTitle("JFXFontExamplesApp");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
