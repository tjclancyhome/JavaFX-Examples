package org.tjc.jfx.testingfonts;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX TestingFontsApp
 */
public class TestingFontsApp extends JFXExamplesApplication {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TestingFontsApp.class);

    private static final String FONT_BIRTH_OF_A_HERO = "/fonts/BIRTH OF A HERO.ttf";
    private static final String FONT_AMERICAN_TYPEWRITER = "/fonts/AmericanTypewriter.ttc";
    private static final String FONT_ORANGE_JUICE = "/fonts/orange juice 2.0.ttf";

    private static final String TEST_FONT = FONT_AMERICAN_TYPEWRITER;

    @Override
    public void start(Stage stage) {
        log.info("### entered start(Stage)");
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

//        InputStream inputStream = getClass().getResourceAsStream(TEST_FONT);
//        log.info("### fontInStream: {}", inputStream);
//        Font font = Font.loadFont(inputStream, 20);

//        Font[] fnts = Font.loadFonts(getClass().getResourceAsStream(TEST_FONT), 0);
//        log.info("### fnts: {}", asList(fnts));
//        log.info("### font: {}", font);
        var label = new Label(
                "Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

//        label.setFont(font);

        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
