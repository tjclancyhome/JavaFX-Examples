package org.tjc.jfx.jfxhexviewer;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXHexViewerApp
 */
public class JFXHexViewerApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXHexViewerApp.class);

    private Scene scene;
    private JFXHexViewerSceneController controller;

    @Override
    public void start(Stage stage) throws IOException {
        log.debug("### entered start(Stage) method.");

        var fxmlLoader = createFxmlLoader(getClass().getResource("/fxml/JFXHexViewerScene.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        scene = new Scene(root);
        controller.setScene(scene);
        scene.getStylesheets().add("/css/styles.css");
        stage.setScene(scene);
        stage.setTitle("JFXHexViewerApp");
        stage.show();
        controller.debugMode();
    }

    public static void main(String[] args) {
        launch();
    }

}
