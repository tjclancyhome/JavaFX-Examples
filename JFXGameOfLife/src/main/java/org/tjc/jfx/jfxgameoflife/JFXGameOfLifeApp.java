package org.tjc.jfx.jfxgameoflife;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * JavaFX JFXGameOfLifeApp
 */
public class JFXGameOfLifeApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXGameOfLifeApp.class);

    private static final String FXML_LOCATION = "/fxml/JFXGameOfLifeScene.fxml";
    private static final String MAIN_CSS_LOCATION = "/css/styles.css";

    private JFXGameOfLifeSceneController controller;
    private Stage stage;

    public JFXGameOfLifeApp() {
        super();
        log.debug("### entered JFXGameOfLifeApp() constructor.");
        log.debug("### exited JFXGameOfLifeApp() constructor.");
    }

    /**
     * The javafx start() method.
     *
     * @param primaryStage The stage to configure and start.
     *
     * @throws IOException Throw IOException if
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        log.debug("### entered start(primaryStage)");
        this.stage = primaryStage;
        var fxmlLoader = createFxmlLoader(getClass().getResource(FXML_LOCATION));
        var parent = Parent.class.cast(fxmlLoader.load());
        log.debug("###     parent.class: {}", parent.getClass());
        controller = fxmlLoader.getController();
        var scene = setScene(new Scene(parent, 800, 600, true, SceneAntialiasing.BALANCED));
        addStyleSheet(getClass().getResource(MAIN_CSS_LOCATION));
        controller.setScene(scene);
        stage.setScene(scene);
        stage.show();
        log.debug("### exited start(Stage)");
    }

    public JFXGameOfLifeSceneController getController() {
        return controller;
    }

    @Override
    public void init() {
        log.debug("### entered init()");
        log.debug("### exited init()");
    }

    @Override
    public void stop() {
        log.debug("### entered stop()");
        stage.close();
        log.debug("### exited stop()");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
