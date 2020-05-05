package org.tjc.jfx.jfxspreadsheet;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXSpreadSheetApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class JFXSpreadSheetApp extends JFXFxmlExamplesApplication {

    /**
     * Constant <code>log</code>
     */
    public static Logger log = LoggerFactory.getLogger(JFXSpreadSheetApp.class);
    private static Scene scene;

    private static JFXSheetsSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws IOException {
        log.debug("##### Entered start(Stage)");
        Parent parent = loadFXML("JFXSheetsScene.fxml");
        scene = new Scene(parent);

        boolean styleSheetAdded = addStylesheetToScene("styles.css");
        log.debug("##### styleSheetAdded: {}", styleSheetAdded);

        stage.setScene(scene);
        stage.setTitle("JFXSpreadSheet");
        stage.show();
    }

    private boolean addStylesheetToScene(String stylesheetPath) {
        return scene.getStylesheets()
                .add(JFXSpreadSheetApp.class.getResource(stylesheetPath).toExternalForm());
    }

    private static Parent loadFXML(String fxml) throws IOException {
        log.debug("##### Entered loadFXML(String): fxml: {}", fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(JFXSpreadSheetApp.class.getResource(fxml));
        controller = fxmlLoader.getController();
        return fxmlLoader.load();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        log.debug("##### Entered main()");
        launch(args);
    }

}
