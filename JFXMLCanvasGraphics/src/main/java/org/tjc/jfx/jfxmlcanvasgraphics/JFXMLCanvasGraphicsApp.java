package org.tjc.jfx.jfxmlcanvasgraphics;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXMLCanvasGraphicsApp
 */
public class JFXMLCanvasGraphicsApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXMLCanvasGraphicsApp.class);

    private static final String FXML_LOCATION = "/fxml/JFXMLCanvasGraphicsScene.fxml";
    private static final String MAIN_CSS_LOCATION = "/css/styles.css";

    private JFXMLCanvasGraphicsSceneController controller;
    private Stage stage;

    public JFXMLCanvasGraphicsApp() {
        log.debug("### entered JFXMLCanvasGraphicsApp() constructor.");
        log.debug("### exited JFXMLCanvasGraphicsApp() constructor.");
    }

    @Override
    public void start(Stage stage) throws IOException {
        log.debug("### entered start(stage)");
        this.stage = stage;
        var fxmlLoader = createFxmlLoader(getClass().getResource(FXML_LOCATION));

        var parent = Parent.class.cast(fxmlLoader.load());
        log.debug("###     parent.class: {}", parent.getClass());

        controller = fxmlLoader.getController();
        var scene = setScene(new Scene(parent, 640, 480, true, SceneAntialiasing.BALANCED));
        addStyleSheet(getClass().getResource(MAIN_CSS_LOCATION));
        controller.setScene(scene);
        stage.setScene(scene);
        stage.show();
        log.debug("### exited start(stage)");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
