/*
 * The MIT License
 *
 * Copyright 2020 tjclancy.
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
package org.tjc.jfx.turtlesapp;

//import com.kodedu.terminalfx.TerminalBuilder;
//import com.kodedu.terminalfx.TerminalTab;
//import com.kodedu.terminalfx.config.TerminalConfig;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.turtlesapp.impl.TurtleImpl;

/**
 * JavaFX TurtlesApp
 */
public class TurtlesApp extends Application {
    private static final Logger log = LoggerFactory.getLogger(TurtlesApp.class);

    public static final double DEFAULT_SCENE_WIDTH = 800;
    public static final double DEFAULT_SCENE_HEIGHT = 600;

    private Scene scene;
    private VBox mainWindowVBox;
    private MenuBarConf menuBarConf;
    private TurtleCanvasPane turtleCanvasPane;
    private TurtleImpl turtle;
    private SplitPane splitPane;
//    private TabPane scriptTabPane;
//    private TerminalTab scriptTab;

    @Override
    public void start(Stage stage) {
        log.debug("### entered start(stage)");

        stage.setOnCloseRequest((var event) -> {
            log.debug("##### onCloseRequest(): event: {}", event);
            handleExit();
        });

        menuBarConf = new MenuBarConf();

        mainWindowVBox = initializeApp();

        scene = new Scene(mainWindowVBox, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);

        stage.setScene(scene);
        stage.show();

        debugPrintApplicationInfo();
        log.debug("### exited start(stage)");
    }

    public static void main(String[] args) {
        launch();
    }

    private VBox initializeApp() {
        log.debug("entered initializeApp()");
//        ScriptTerminal scriptTerminal = new ScriptTerminal();
        mainWindowVBox = new VBox();
        mainWindowVBox.getChildren().add(menuBarConf.getMenuBar());

        turtleCanvasPane = new TurtleCanvasPane(800, 600);
        GraphicsContext gc = turtleCanvasPane.getGraphicsContext();

        log.debug("###     turtleCanvasPane: {}", turtleCanvasPane);

        turtle = new TurtleImpl(gc, 300, 300, 0.0); //, centerX, centerY, 0.0);
        turtleCanvasPane.setTurtle(turtle);

//        scriptTab = newScriptTerminal("zsh -i -l");
//        scriptTab.setText("zsh");
//        scriptTabPane = new TabPane(scriptTab);
        ScrollPane scrollPane = new ScrollPane(turtleCanvasPane);
        SplitPane.setResizableWithParent(scrollPane, Boolean.TRUE);
//        SplitPane.setResizableWithParent(scriptTabPane, Boolean.FALSE);

//        SwingNode swingNode = new SwingNode();
//        SwingUtilities.invokeLater(() -> {
//            swingNode.setContent(scriptTerminal.newScriptTerminal().getScrollPane());
//        });

        splitPane = new SplitPane(scrollPane);
//        splitPane = new SplitPane(scrollPane, swingNode);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPositions(0.55);

        mainWindowVBox.getChildren().add(splitPane);

        menuBarConf.getDebugTurtleHome().setOnAction(event -> {
            log.debug("### calling turtle.home()");
            turtle.home();
            debugPrintApplicationInfo();
            event.consume();
        });

        menuBarConf.getDebugTurtleForward().setOnAction(event -> {
            log.debug("### calling turtle.forward()");
            turtle.forward(50);
            debugPrintApplicationInfo();
            event.consume();
        });

        menuBarConf.getDebugTurtleTest().setOnAction(event -> {
            log.debug("### calling turtle.test()");
            event.consume();
            Platform.runLater(() -> {
                double step = 50;
                turtle.show();
                turtle.forward(step);
                turtle.turnLeft(120.0);
                turtle.forward(step);
                turtle.turnLeft(120.0);
                turtle.forward(step);
                turtle.turnLeft(120.0);
                turtle.face(270.0);
                debugPrintApplicationInfo();
            });
        });

        mainWindowVBox.widthProperty().addListener((var obsv, var oldVal, var newVal) -> {
            log.debug("Main window width changed: oldVal: {}, newVal: {}", oldVal, newVal);
        });

        mainWindowVBox.heightProperty().addListener((var obsv, var oldVal, var newVal) -> {
            log.debug("Main window height changed: oldVal: {}, newVal: {}", oldVal, newVal);
        });

        log.debug("exited initializeApp()");

        return mainWindowVBox;
    }

//    private TerminalTab newScriptTerminal(String command) {
//        TerminalConfig darkConfig = TerminalConfigs.darkConfig();
//        darkConfig.setFontSize(12);
//        darkConfig.setUnixTerminalStarter(command);
//        TerminalBuilder terminalBuilder = new TerminalBuilder(darkConfig);
//        TerminalTab terminal = terminalBuilder.newTerminal();
//        terminal.onTerminalFxReady(() -> {
//            log.debug("### terminal ready... clearing screen.");
//            terminal.getTerminal().command("cls\n");
//        });
//        return terminal;
//    }
    void debugPrintApplicationInfo() {
        if (log.isDebugEnabled()) {
            Screen primary = Screen.getPrimary();
            log.debug("### ====================================");
            log.debug("### Printing App Info");
            log.debug("### ====================================");
            log.debug("### ------------------------------------");
            log.debug("### Screen Info");
            log.debug("### ------------------------------------");
            log.debug("###     visualBounds  : {}", primary.getVisualBounds());
            log.debug("###     bounds        : {}", primary.getBounds());
            log.debug("###     dpi           : {}", primary.getDpi());
            log.debug("###     output scale x: {}", primary.getOutputScaleX());
            log.debug("###     output scale y: {}", primary.getOutputScaleY());
            log.debug("###     toString      : {}", primary.toString());
            log.debug("### ------------------------------------");
            log.debug("### scene           : {}", this.scene);
            log.debug("### mainWindowVBox  : {}", this.mainWindowVBox);
            log.debug("### menuBarConf     : {}", this.menuBarConf);
            log.debug("### turtleCanvasPane: {}", this.turtleCanvasPane);
            log.debug("### turtle          : {}", this.turtle);
            log.debug("### ------------------------------------");
            log.debug("### mainWindow Local Bounds        : {}", formatBounds(mainWindowVBox
                .getBoundsInLocal()));
            log.debug("### mainWindow Parent Bounds       : {}", formatBounds(mainWindowVBox
                .getBoundsInParent()));
            log.debug("### turtleCanvasPane Local Bounds  : {}", formatBounds(turtleCanvasPane
                .getBoundsInLocal()));
            log.debug("### turtleCanvasPane Parent Bounds : {}", formatBounds(turtleCanvasPane
                .getBoundsInParent()));
            log.debug("### splitPane.item(0) Local Bounds : {}", formatBounds(splitPane.getItems()
                .get(0)
                .getBoundsInLocal()));
            log.debug("### splitPane.item(0) Parent Bounds: {}", formatBounds(splitPane.getItems()
                .get(0)
                .getBoundsInParent()));

            log.debug("### ====================================");
            log.debug("### Done Printing App Info");
            log.debug("### ====================================");
        }

    }

    private void handleExit() {
        log.debug("### entered handleExit()");
        scene.getWindow().hide();
//        scriptTab.closeTerminal();
        log.debug("###     closed terminal.");
        Platform.exit();
        log.debug("### exited handleExit()");
        System.exit(0);
    }

    private String formatBounds(Bounds bounds) {
        StringBuilder sb = new StringBuilder();
        sb.append("(width: ").append(bounds.getWidth()).append(", ")
            .append("height: ").append(bounds.getHeight()).append(")");
        return sb.toString();
    }

}
