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
package org.tjc.jfx.jfxgameoflife;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.tjc.jfx.jfxcomponents.utils.Utils.consumeEvent;
import org.tjc.jfx.jfxgameoflife.model.LifeGrid;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class GameOfLifePanelControl extends AnchorPane {
    private static final Logger log = LoggerFactory.getLogger(GameOfLifePanelControl.class);

    private static final String GOL_PANEL_CONTRTOL_FXML = "/fxml/GameOfLifePanel.fxml";

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    private static final int DEFAULT_GRID_WIDTH = LifeGrid.DEFAULT_WIDTH;
    private static final int DEFAULT_GRID_HEIGHT = LifeGrid.DEFAULT_HEIGHT;

    private Button start;
    private Button stop;
    private Button step;
    private Button clear;
    private Button reset;
    private Button printDebug;
    private long randomSeed = 1581155990343L;
    private Canvas canvas;
    private LifeGrid lifeGrid;

    @SuppressWarnings({"LeakingThisInConstructor", "CallToPrintStackTrace"})
    public GameOfLifePanelControl() {
        super();
        log.debug("### entered GameOfLifePanelController() constructor.");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GOL_PANEL_CONTRTOL_FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            log.error("### Caught exception loading {}.fxml: message: {}",
                getClass().getSimpleName(), ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        log.debug("### exited GameOfLifePanelController() constructor.");
    }

    public int getGridWidth() {
        return lifeGrid.getWidth();
    }

    public int getGridHeight() {
        return lifeGrid.getHeight();
    }

    public int getCellSize() {
        return lifeGrid.getCellSize();
    }

    public int getCols() {
        return lifeGrid.getCols();
    }

    public int getRows() {
        return lifeGrid.getRows();
    }

    public void setRandomSeed(long randomSeed) {
        lifeGrid.setRandomSeed(randomSeed);
    }

    public void init() {
        lifeGrid.init();
    }

    public void start() {
        log.debug("### entered start()");
        lifeGrid.start();
        log.debug("### exited start()");
    }

    public void stop() {
        lifeGrid.stop();
    }

    public void clear() {
        lifeGrid.clear();
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        log.debug("### entered initialize().");
        this.canvas = new Canvas(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
//        lifeGrid = new LifeGrid(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT, DEFAULT_GRID_WIDTH / 10, randomSeed,
//            canvas
//                .getGraphicsContext2D());
        lifeGrid = new LifeGrid(DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT, 10, randomSeed, canvas
            .getGraphicsContext2D());
        lifeGrid.init();
        log.debug("### lifeGrid: {}", lifeGrid);

        /*
         * A VBox contains the GoL grid and a button bar that has button controls to control the GoL grid.
         */
        var gridAndControlsContainer = new VBox();
        gridAndControlsContainer.setSpacing(0.0);
        gridAndControlsContainer.getChildren().addAll(canvas, initializeButtonBar());
        gridAndControlsContainer.setStyle("-fx-background-color:WHITE;");

        this.getChildren().add(gridAndControlsContainer);
        this.setPrefWidth(DEFAULT_WIDTH);
        this.setPrefHeight(DEFAULT_HEIGHT);
        log.debug("### exited initialize().");
    }

    private ButtonBar initializeButtonBar() {
        log.debug("### entered initializeButtonBar()");
        start = new Button("Start");
        start.setOnAction((var event) -> {
            log.debug("### entered 'start' onAction(): event: {}", event);
            lifeGrid.start();
            consumeEvent(event);
        });

        step = new Button("Step");
        step.setOnAction((var event) -> {
            log.debug("### entered 'step' onAction(): event: {}", event);
            consumeEvent(event);
        });

        stop = new Button("Stop");
        stop.setOnAction((ActionEvent event) -> {
            log.debug("### entered 'stop' onAction(): event: {}", event);
            consumeEvent(event);
        });

        clear = new Button("Clear");
        clear.setOnAction((var event) -> {
            log.debug("### entered 'clear' onAction(): event: {}", event);
            lifeGrid.stop();
            lifeGrid.clear();
            consumeEvent(event);
        });

        reset = new Button("Reset");
        reset.setOnAction((var event) -> {
            log.debug("### entered 'reset' onAction(): event: {}", event);
            lifeGrid.stop();
            lifeGrid.init();
            consumeEvent(event);
        });

        printDebug = new Button("Debug");
        printDebug.setOnAction((var event) -> {
            if (log.isDebugEnabled()) {
                log.debug("### entered 'printDebug' onAction(): event: {}", event);
                debugPrintLifeGridInfo();
                debugPrintThisInfo();
            }
            consumeEvent(event);

        });


        var buttonBar = new ButtonBar();
        buttonBar.setStyle("-fx-background-color:WHITE;");
        buttonBar.getButtons().addAll(start, step, stop, clear, reset, printDebug);
        buttonBar.setPadding(new Insets(10, 10, 10, 0));
        log.debug("### exited initializeButtonBar()");
        return buttonBar;
    }

    private void debugPrintLifeGridInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("===============================================");
            log.debug("Printing LifeGrid Info");
            log.debug("===============================================");
            log.debug("### cell size        : {}", lifeGrid.getCellSize());
            log.debug("### number of columns: {}", lifeGrid.getCols());
            log.debug("### number of rows   : {}", lifeGrid.getRows());
            log.debug("### width            : {}", lifeGrid.getWidth());
            log.debug("### height           : {}", lifeGrid.getHeight());
            log.debug("### random seed      : {}", lifeGrid.getRandomSeed());
            log.debug("===============================================");
            log.debug("Done Printing LifeGrid Info");
            log.debug("===============================================");
            log.debug("###");
        }
    }

    private void debugPrintThisInfo() {
        if (log.isDebugEnabled()) {
            log.debug("###");
            log.debug("===============================================");
            log.debug("Printing This Info");
            log.debug("===============================================");
            log.debug("### this.class   : {}", this.getClass().getName());
            log.debug("### this.canvas  : {}", this.canvas);
            log.debug("### this.lifeGrid: {}", this.lifeGrid);
            log.debug("### this.clear   : {}", this.clear);
            log.debug("### this.start   : {}", this.start);
            log.debug("### this.stop    : {}", this.stop);
            log.debug("### this.step    : {}", this.step);
            log.debug("### this.reset   : {}", this.reset);
            log.debug("### this.width   : {}", this.getWidth());
            log.debug("### this.height  : {}", this.getHeight());
            log.debug("### this.id      : {}", this.getId());
            log.debug("===============================================");
            log.debug("Done Printing This Info");
            log.debug("===============================================");
            log.debug("###");
        }

    }

}
