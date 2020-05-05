/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxhexviewer;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.skin.SplitPaneSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.statusbar.StatusBar;
import org.tjc.jfx.jfxcomponents.themes.Themes;
import org.tjc.jfx.jfxcomponents.utils.FileSelector;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXHexViewerSceneController {
    private static final Logger log = LoggerFactory.getLogger(JFXHexViewerSceneController.class);

    private static final String DARK_MODE_THEME = "/css/dark-theme.css";
    private static final int DEFAULT_NUM_COLUMNS = 25;
    private static final int DEFAULT_HEXGRID_MARGIN = 3;
    private static final Font DEFAULT_FONT = new Font("Source Code Pro", 13);

    private static final String NON_PRINTABLE_CHAR_SUBSTITUTE = " ";
    private static final String OTHER_CHAR_SUBSTITUTE = ".";

    @FXML
    private AnchorPane charGridAnchorPane;
    @FXML
    private ScrollPane charGridScrollPane;
    @FXML
    private CheckMenuItem darkModeViewMenuItem;
    @FXML
    private AnchorPane hexGridAnchorPane;
    @FXML
    private ScrollPane hexGridScrollPane;
    @FXML
    private AnchorPane lineNumbersAnchorPane;
    @FXML
    private Button reloadToolBarButton;
    @FXML
    private SplitPane viewerSplitPane;
    @FXML
    private ScrollPane lineNumbersScrollPane;
    @FXML
    private VBox mainView;
    @FXML
    private MenuBar menuBar;

    private GridPane hexGrid;
    private GridPane charGrid;
    private GridPane lineNumberGrid;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");
        viewerSplitPane.setPadding(Insets.EMPTY);
        initializeLineNumbersView();
        initializeHexGridView();
        initializeCharGridView();
        initializeStatusBar();
        hidePanes();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void debugMode() {
        /*
         * Show various debug information.
         */
        showViewerSplitPaneDebugInfo();
        showSplitPaneSkinDebugInfo();
    }

    private void initializeLineNumbersView() {
        log.debug("### entered initializeLineNumbersView()");
        lineNumbersScrollPane.setFitToHeight(false);
        lineNumbersScrollPane.setFitToWidth(true);
        lineNumbersScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        lineNumbersScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        createLineNumberGrid();
        lineNumbersScrollPane.vvalueProperty().bindBidirectional(hexGridScrollPane.vvalueProperty());
        lineNumbersScrollPane.hvalueProperty().bindBidirectional(hexGridScrollPane.hvalueProperty());
    }

    private void initializeHexGridView() {
        log.debug("### entered initializeHexGridView()");
        hexGridScrollPane.setFitToHeight(false);
        hexGridScrollPane.setFitToWidth(false);
        hexGridScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hexGridScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        createHexGrid();
    }

    private void initializeCharGridView() {
        log.debug("### entered initializeCharGridView()");
        charGridScrollPane.setFitToHeight(false);
        charGridScrollPane.setFitToWidth(false);
        charGridScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        charGridScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        createCharGrid();
        charGridScrollPane.vvalueProperty().bindBidirectional(hexGridScrollPane.vvalueProperty());
        charGridScrollPane.hvalueProperty().bindBidirectional(hexGridScrollPane.hvalueProperty());
    }

    private void initializeStatusBar() {
        log.debug("### entered initializeStatusBar()");
        StatusBar statusBar = new StatusBar();
        mainView.getChildren().add(statusBar);
        log.debug("### exited initializeStatusBar()");
    }

    private void clearData() {
        log.debug("### entered clearData()");
        lineNumberGrid.getChildren().clear();
        hexGrid.getChildren().clear();
        hexGrid.getColumnConstraints().clear();
        hexGrid.getRowConstraints().clear();
        charGrid.getChildren().clear();
        charGrid.getColumnConstraints().clear();
        charGrid.getRowConstraints().clear();
        createLineNumberGrid();
        createHexGrid();
        createCharGrid();
        log.debug("### exited clearData()");
    }

    private void hidePanes() {
        log.debug("### entered hidePanes()");
        lineNumbersAnchorPane.setVisible(false);
        hexGridAnchorPane.setVisible(false);
        charGridAnchorPane.setVisible(false);
//        Platform.requestNextPulse();
        log.debug("### exited hidePanes()");
    }

    private void showPanes() {
        log.debug("### entered showPanes()");
        lineNumbersAnchorPane.setVisible(true);
        hexGridAnchorPane.setVisible(true);
        charGridAnchorPane.setVisible(true);
        log.debug("### exited showPanes()");
    }

    @FXML
    private void onQuitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void onReload(ActionEvent event) {
        event.consume();
    }

    private void createLineNumberGrid() {
        log.debug("### entered createLineNumberGrid()");
        lineNumbersAnchorPane.getChildren().clear();
        lineNumberGrid = new GridPane();
        lineNumberGrid.getStyleClass().add("line-numbers-grid");
        lineNumberGrid.setAlignment(Pos.CENTER_RIGHT);
        lineNumberGrid.setGridLinesVisible(false);
        AnchorPane.setBottomAnchor(lineNumberGrid, 0.0);
        AnchorPane.setTopAnchor(lineNumberGrid, 0.0);
        AnchorPane.setLeftAnchor(lineNumberGrid, 0.0);
        AnchorPane.setRightAnchor(lineNumberGrid, 0.0);
        lineNumbersAnchorPane.getChildren().add(lineNumberGrid);
    }

    private void createHexGrid() {
        log.debug("### entered createHexGrid()");
        hexGridAnchorPane.getChildren().clear();
        hexGrid = new GridPane();
        hexGrid.setAlignment(Pos.CENTER);
        hexGrid.setGridLinesVisible(true);
        AnchorPane.setBottomAnchor(hexGrid, 0.0);
        AnchorPane.setTopAnchor(hexGrid, 0.0);
        AnchorPane.setLeftAnchor(hexGrid, 0.0);
        AnchorPane.setRightAnchor(hexGrid, 0.0);
        hexGridAnchorPane.getChildren().add(hexGrid);
    }

    private void createCharGrid() {
        log.debug("### entered createCharGrid()");
        charGridAnchorPane.getChildren().clear();
        charGrid = new GridPane();
        charGrid.setAlignment(Pos.CENTER);
        charGrid.setGridLinesVisible(true);
        AnchorPane.setBottomAnchor(charGrid, 0.0);
        AnchorPane.setTopAnchor(charGrid, 0.0);
        AnchorPane.setLeftAnchor(charGrid, 0.0);
        AnchorPane.setRightAnchor(charGrid, 0.0);
        charGridAnchorPane.getChildren().add(charGrid);
    }

    @FXML
    private void onOpenFileAction(ActionEvent event) {
        log.debug("### entered onOpenFile(ActionEvent): event: {}", event);
        Path file = chooseFile();
        if (file != null && Files.exists(file) && Files.isRegularFile(file)) {
            try {
                readAndDisplay(file);
            } catch (Exception ex) {
                log.error("Caught Exception reading and displaying file '{}': message: {}", file, ex
                    .getMessage());
                throw new RuntimeException(ex);
            }
        } else {
            throw new RuntimeException(String.format(
                "The selected file '%s' is not valid for opening and viewing.", file));
        }
        event.consume();
    }

    @FXML
    private void onCloseFileAction(ActionEvent event) {
        clearData();
        hidePanes();
        event.consume();
    }

    private Path chooseFile() {
        FileSelector fileSelector = new FileSelector(scene, "Select File");
        return fileSelector.selectFile();
    }

    @FXML
    private void onSetDarkMode(ActionEvent event) {
        Themes.setDarkMode(darkModeViewMenuItem.isSelected(), scene);
        event.consume();
    }

//    private void setDarkMode(Boolean on) {
//        if (on) {
//            if (!scene.getStylesheets().contains(DARK_MODE_THEME)) {
//                scene.getStylesheets().add(DARK_MODE_THEME);
//            }
//        } else if (scene.getStylesheets().contains(DARK_MODE_THEME)) {
//            scene.getStylesheets().remove(DARK_MODE_THEME);
//        }
//        showViewerSplitPaneDebugInfo();
//    }
    private void readAndDisplay(Path file) {
        log.debug("### entered readAndDisplay(Path)");
//        Platform.runLater(() -> {
        try {
            hidePanes();
            clearData();
            setTitle(file.getFileName().toString());
            FileData fileData = new FileData(file);
            try(InputStream in = fileData.openStream()) {
                displayBytes(in);
            }
//            ByteBuffer byteBuffer = fileData.getByteBuffer();
//            ByteBuffer byteBuffer = fileData.newBuffer();
//            displayBytes(byteBuffer);

            showPanes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//        });
    }

    private void setTitle(String fileName) {
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("JFXHexViewerApp - " + fileName);
    }

    private void setTitle() {
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("JFXHexViewerApp");
    }

//    private void displayBytes(ByteBuffer byteBuffer) {
    private void displayBytes(InputStream in) throws IOException {
        log.debug("### entered displayBytes(ByteBuffer)");
        populateDataGrids(in);
        log.debug("### exited displayBytes()");

//        byte[] bytes = byteBuffer.array();
//        log.debug("### entered displayBytes(): numBytes: {}", bytes.length);
//        populateHexGrid(bytes);
//        populateCharGrid(bytes);
//        createLineNumbers(charGrid.getRowCount(), charGrid.getColumnCount());
//        log.debug("### exited displayBytes()");
    }

//    private void populateDataGrids(ByteBuffer buffer) {
    private void populateDataGrids(InputStream in) throws IOException {
        log.debug("### entered populateData()");

        /*
         * Combine hex and char grid populating using a single loop.
         */
//        log.debug("### byteBuffer             : {}", buffer);
//        log.debug("### byteBuffer.limit       : {}", MemoryMetricsConverters.convertToString(buffer.limit()));
//        log.debug("### byteBuffer.capacity    : {}", MemoryMetricsConverters
//                .convertToString(buffer.capacity()));
//        log.debug("### byteBuffer.hasArray    : {}", buffer.hasArray());
//        log.debug("### byteBuffer.hasRemaining: {}", buffer.hasRemaining());
//        log.debug("### byteBuffer.position    : {}", buffer.position());
//        log.debug("### byteBuffer.remaining   : {}", MemoryMetricsConverters.convertToString(buffer
//                .remaining()));

        int row = 0;
        int col = 0;
        int i = 0;
        int avail = in.available();
        log.debug("### in.available: {}", avail);
        int len;
        byte[] buffer = new byte[10240];
//        while (buffer.hasRemaining()) {
        while ((len = in.read(buffer)) != -1) {
            for (int n = 0; n < len; n++) {
                //byte b = buffer.get();
                byte b = buffer[n];
                if (i != 0 && i % DEFAULT_NUM_COLUMNS == 0) {
                    row++;
                    col = 0;
                }
                /*
                 * Populate hexGrid with byte.
                 */
                var hexStr = toUpper(replace(format("%2s", toHex(b)), ' ', '0'));
                var text = textObject(hexStr);
                text.setTextAlignment(TextAlignment.CENTER);
                GridPane.setMargin(text, new Insets(DEFAULT_HEXGRID_MARGIN));
                hexGrid.add(text, col, row);

                /*
                 * Populate charGrid with byte.
                 */
                String textStr;
                int ub = b; //Byte.toUnsignedInt(b);
                if (isPrintableAscii(ub)) {
                    textStr = Character.toString((char) ub);
                } else if (isNotPrintableAscii(ub)) {
                    textStr = NON_PRINTABLE_CHAR_SUBSTITUTE;
                } else {
                    /*
                     * All other bytes in char grid display as a '.'.
                     */
                    textStr = OTHER_CHAR_SUBSTITUTE;
                }
                Text charText = textObject(textStr);
                charText.setTextAlignment(TextAlignment.CENTER);
                GridPane.setMargin(charText, new Insets(DEFAULT_HEXGRID_MARGIN));
                charGrid.add(charText, col, row);

                i++;
                col++;
            }
        }
        createLineNumbers(charGrid.getRowCount(), charGrid.getColumnCount());

        log.debug("### exited populateData()");
    }

    private void populateHexGrid(final byte[] bytes) {
        log.debug("### entered populateHexGrid()");
        /*
         * Create hex grid view.
         */
        log.debug("### creating hex grid view...");
        int row = 0;
        int col = 0;
        int i = 0;
        for (byte b : bytes) {
            if (i != 0 && i % DEFAULT_NUM_COLUMNS == 0) {
                row++;
                col = 0;
            }
            var hexStr = toUpper(replace(format("%2s", toHex(b)), ' ', '0'));
            var text = textObject(hexStr);
            text.setTextAlignment(TextAlignment.CENTER);
            GridPane.setMargin(text, new Insets(DEFAULT_HEXGRID_MARGIN));
            hexGrid.add(text, col, row);
            i++;
            col++;
        }
        log.debug("### hexGrid populated.");
//        showHexGridAndHexGridAnchorPaneDebugInfo();
        log.debug("### exited populateHexGrid()");
    }

    private void populateCharGrid(final byte[] bytes) {
        log.debug("### entered populateCharGrid()");
        /*
         * Create character grid view.
         */
        log.debug("### creating char grid view...");
        int row = 0;
        int col = 0;
        int i = 0;
        for (byte b : bytes) {
            if (i != 0 && i % DEFAULT_NUM_COLUMNS == 0) {
                row++;
                col = 0;
            }
            String textStr;
            int ub = Byte.toUnsignedInt(b);
            if (isPrintableAscii(ub)) {
                textStr = Character.toString((char) ub);
            } else if (isNotPrintableAscii(ub)) {
                textStr = NON_PRINTABLE_CHAR_SUBSTITUTE;
            } else {
                /*
                 * All other bytes in char grid display as a '.'.
                 */
                textStr = OTHER_CHAR_SUBSTITUTE;
            }
            Text charText = textObject(textStr);
            charText.setTextAlignment(TextAlignment.CENTER);
            GridPane.setMargin(charText, new Insets(DEFAULT_HEXGRID_MARGIN));
            charGrid.add(charText, col, row);
            i++;
            col++;
        }
        log.debug("### charGrid populated.");
        log.debug("### exited populateCharGrid()");
    }

    private void createLineNumbers(int rowCount, int colCount) {
        log.debug("### entered createLineNumbers()");
        /*
         * Create line number view.
         */
        for (int n = 0; n < rowCount; n++) {
            Text text = textObject(format("%5s", String.valueOf(n * colCount)));
            text.setTextAlignment(TextAlignment.RIGHT);
            GridPane.setMargin(text, new Insets(3));
            lineNumberGrid.add(text, 0, n);
        }
        log.debug("### exited createLineNumbers()");
    }

    private String toHex(byte b) {
        return format("%x", Byte.toUnsignedInt(b));
    }

    private String toHex(int i) {
        return format("%x", i);
    }

    private String toHex(long l) {
        return format("%x", l);
    }

    private String toUpper(String s) {
        return s.toUpperCase();
    }

    private String replace(String s, char target, char replacement) {
        return s.replace(target, replacement);
    }

    private void addToolTip(Text text, byte b) {
        char c = (char) b;
        String textStr;
        if (isNonAsciiPrintable(c)) {
            textStr = NON_PRINTABLE_CHAR_SUBSTITUTE;
        } else {
            textStr = Character.toString(c);
        }
        Tooltip t = new Tooltip("character (" + textStr + ")");
        t.setFont(new Font("Menlo", 13));
        t.setShowDelay(Duration.ONE);
        t.setShowDuration(Duration.seconds(5));
        t.setHideDelay(Duration.ZERO);
        Tooltip.install(text, t);
    }

    private Text textObject(String str) {
        Text text = new Text(str);
        text.getStyleClass().add("display-text");
        text.setFont(DEFAULT_FONT);
        return text;
    }

    private boolean isNonAsciiPrintable(final char c) {
        return !CharUtils.isAsciiPrintable(c);
    }

    private boolean isAsciiPrintable(final char c) {
        return CharUtils.isAsciiPrintable(c);
    }

    private boolean isPrintableAscii(int ch) {
        return ch >= 32 && ch < 128;
    }

    private boolean isNotPrintableAscii(int ch) {
        return ch < 32;
    }

    private boolean isPrintable(final char ch) {
        return ch >= 32 && ch < 256;
//        return (ch >= 32 && ch < 127) || (ch >= 127 && ch < 256);

    }

    private boolean isPrintable(final byte b) {
        return b >= 32 && b < 256;
//        return (b >= 32 && b < 127) || (b >= 127 && b < 256);
    }

    private boolean isPrintable(final int i) {
        return i >= 32 && i < 256;
//        return (i >= 32 && i < 127) || (i >= 127 && i < 256);
    }

    /**
     * Debug method... will be removed after app is completed.
     */
    private void showViewerSplitPaneDebugInfo() {
        if (log.isDebugEnabled() && scene != null) {
            log.debug("### entered showDebugInfo()");
            if (scene.getWindow() != null) {
                viewerSplitPane.lookupAll(".split-pane-divider")
                    .forEach((var node) -> log.debug("### node: {}", node));
            } else {
                log.debug("### the scene.window is null.");
            }
        } else if (scene == null) {
            log.debug("### the scene object is null.");
        }
    }

    /**
     * Debug method... will be removed after app is completed.
     */
    private void showSplitPaneSkinDebugInfo() {
        if (log.isDebugEnabled() && scene != null) {
            log.debug("### ======================================================");
            log.debug("### entered showSplitPaneSkinDebugInfo()");
            if (scene.getWindow() != null) {
                SplitPaneSkin splitPaneSkin = (SplitPaneSkin) viewerSplitPane.getSkin();
                log.debug("### splitPaneSkin: {}", splitPaneSkin);
                splitPaneSkin.getSkinnable().getDividers().forEach((var d)
                    -> log.debug("### divider: {}", d.positionProperty().toString()));
            } else {
                log.debug("### the scene.window is null.");
            }
        } else if (scene == null) {
            log.debug("### the scene object is null.");
        }
        log.debug("### ======================================================");
        log.debug("");
    }

    private void showHexGridAndHexGridAnchorPaneDebugInfo() {
        if (log.isDebugEnabled()) {
            log.debug("### ======================================================");
            log.debug("### hexGridAnchorPane.prefWidth: {}", hexGridAnchorPane.getPrefWidth());
            log.debug("### hexGrid.width: {}", hexGrid.getWidth());
            log.debug("### hexGrid.prefWidth: {}", hexGrid.getPrefWidth());
            log.debug("### hexGrid.boundsInLocal: {}", hexGrid.getBoundsInLocal());
            log.debug("### hexGridAnchorPane.prefWidth: {}", hexGridAnchorPane.getPrefWidth());
            log.debug("### hexGridAnchorPane.width: {}", hexGridAnchorPane.getWidth());
            log.debug("### hexGridAnchorPane.boundsInLocal: {}", hexGridAnchorPane.getBoundsInLocal());
            log.debug("### hexGridAnchorPane.boundsInParent: {}", hexGridAnchorPane.getBoundsInParent());
            log.debug("### hexGrid.prefWidth: {}", hexGrid.getPrefWidth());
            log.debug("");
            log.debug("### viewerSplitPane.isVisible: {}", viewerSplitPane.isVisible());
            log.debug("");
            log.debug("### hexGridScrollPane.getViewportBounds: {}", hexGridScrollPane.getViewportBounds());
            log
                .debug("### hexGridScrollPane.getMinViewportWidth: {}", hexGridScrollPane
                    .getMinViewportWidth());
            log.debug("### hexGridScrollPane.getPrefViewportWidth: {}", hexGridScrollPane
                .getPrefViewportWidth());
            log.debug("### ======================================================");
            log.debug("");
        }
    }

    private void showGridDebugInfo() {
        if (log.isDebugEnabled()) {
            log.debug("### ======================================================");
            log.debug("### lineNumberGrid.boundsInParent: {}", lineNumberGrid.getBoundsInParent());
            log.debug("### hexGrid.boundsInParent: {}", hexGrid.getBoundsInParent());
            log.debug("### charGrid.boundsInParent: {}", charGrid.getBoundsInParent());
            log.debug("");
            log.debug("### lineNumberGrid.getBoundsInLocal: {}", lineNumberGrid.getBoundsInLocal());
            log.debug("### hexGrid.getBoundsInLocal: {}", hexGrid.getBoundsInLocal());
            log.debug("### charGrid.getBoundsInLocal: {}", charGrid.getBoundsInLocal());
            log.debug("");
            log.debug("### lineNumberGrid.isVisible: {}", lineNumberGrid.isVisible());
            log.debug("### charGrid.isVisible: {}", charGrid.isVisible());
            log.debug("### hexGrid.isVisible: {}", hexGrid.isVisible());
            log.debug("");
            log.debug("### viewerSplitPane.isVisible: {}", viewerSplitPane.isVisible());
            log.debug("### ======================================================");
            log.debug("");
        }
    }

}
