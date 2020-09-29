package org.tjc.jfx.jfxgraphviz;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;
//import org.tjc.jfx.jfxgraphviz.config.Config;

/**
 * JavaFX JFXGraphvizApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXGraphvizApp extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXGraphvizApp.class);

    private Scene scene;
    private JFXGraphvizSceneController controller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        log.debug("### entered init(Stage)");

        FXMLLoader fxmlLoader = createFxmlLoader(getClass().getResource("JFXGraphvizScene.fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setCommandLineFilePath(getPathOfFileNameParam());

        scene = new Scene(parent, 1024, 768);

        URL stylesheet = getClass().getResource("styles.css");
        log.debug("### stylesheet: {}", stylesheet);
        if (stylesheet != null) {
            boolean added = scene.getStylesheets().add(stylesheet.toExternalForm());
            log.debug("### Stylesheet: styles.css added: {}", added);
        }

        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

        log.debug("### exited init(Stage)");
    }

    private Path getPathOfFileNameParam() {
        Application.Parameters params = this.getParameters();

        Path dotFilePath = null;
        if (params.getUnnamed().isEmpty() == false) {
            String dotFileName = params.getUnnamed().get(0);
            dotFilePath = Paths.get(dotFileName);
        }
        return dotFilePath;
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
