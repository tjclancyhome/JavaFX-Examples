package org.tjc.jfx.jfxcontrollerexamples;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXControllerExamplesApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXControllerExamplesApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXControllerExamplesApp.class);

    private JFXControllerExamplesController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        log.debug("### entered start(Stage)");
        var location = getClass().getResource("JFXControllerExamples.fxml");
        log.debug("### fxml file location: {}, location.externalForm: {}",
                location,
                location.toExternalForm());
        var fxmlLoader = createFxmlLoader(location);
        var scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
