package org.tjc.jfx.aceeditorexample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX AceEditorExampleApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class AceEditorExampleApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(AceEditorExampleApp.class);

    private Scene scene;
    private AceEditorExampleSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        log.debug("### entered start(Stage): primaryStage: {}", primaryStage);

        var fxmlLoader = createFxmlLoader(getClass().getResource("/fxml/AceEditorExampleScene.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

//        scene = new Scene(root, 800.0, 600.0);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AceEditorExample");
        primaryStage.show();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        log.debug("### entered main(String[])");
        launch(args);
    }
}
