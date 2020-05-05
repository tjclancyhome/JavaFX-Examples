/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
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
package org.tjc.jfx.jfxregexapp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.border.Borders;
import org.tjc.jfx.jfxcomponents.statusbar.StatusBar;

/**
 * The JavaFX GUI controller for the JFXRegExAppScene.fxml.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRegExAppSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXRegExAppSceneController.class);

    private static final TextBoundsType DEFAULT_TEXT_BOUNDS_TYPE = TextBoundsType.VISUAL;

//    private static final String DEFAULT_BACKGROUND_HIGHLIGHT_COLOR = "DARKGREEN";
    private static final String DEFAULT_BACKGROUND_HIGHLIGHT_COLOR = "BLUEVIOLET";
    private static final String DEFAULT_HIGHLIGHT_FILL_COLOR = "WHITE";
//    private static final String DEFAULT_HIGHLIGHT_FILL_COLOR = "BLUEVIOLET";
    private static final int DEFAULT_TEXT_SIZE = 13;
//    private static final Font DEFAULT_FONT = Font.font("Monospaced", FontWeight.NORMAL, FontPosture.REGULAR,
//        DEFAULT_TEXT_SIZE);

//    private static final Font DEFAULT_FONT = Font.font("Menlo", FontWeight.NORMAL, FontPosture.REGULAR,
//      DEFAULT_TEXT_SIZE);
    @FXML
    private Button clearButton;
    @FXML
    private Button findButton;
    @FXML
    private VBox mainContainer;
    @FXML
    private Button matchButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextArea regExTextArea;
    @FXML
    private Button replaceButton;
    @FXML
    private ComboBox<String> selectFontComboBox;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane textFlowScrollPane;
    @FXML
    private ToolBar toolBar;

    private String searchTextStr;
    private Text searchText;
    private Scene scene;
    private StatusBar statusBar;
    private final Borders borders;
//    private final Font font;
    private Font font;

    /**
     * This is the controller class for the JFXRegExAppScene.fxml file.
     */
    public JFXRegExAppSceneController() {
        log.debug("### entered JFXRegExAppSceneController()");
        borders = new Borders();

        // Monospaced
        // Menlo
        // Monaco

        //font = Font.font("Menlo", FontWeight.NORMAL, FontPosture.REGULAR, DEFAULT_TEXT_SIZE);
        //font = Font.font("Menlo", FontWeight.BOLD, DEFAULT_TEXT_SIZE);
        font = new Font("Monospaced Regular", DEFAULT_TEXT_SIZE);
        log.debug("### font.family : {}", font.getFamily());
        log.debug("### font.name   : {}", font.getName());
        log.debug("### font.posture: {}", font.getStyle());
        log.debug("### font.size   : {}", font.getSize());

        //debugLogFontInfo(font);
    }

    /**
     * <p>
     * initialize.</p>
     */
    public void initialize() {
        log.debug("### entered initialize()");
        statusBar = new StatusBar();
        VBox.setVgrow(statusBar, Priority.NEVER);
        mainContainer.getChildren().add(statusBar);
        System.out.println("is debug enabled? " + log.isDebugEnabled());

        if (log.isDebugEnabled()) {

            borders.addBorder(mainContainer);
            borders.addBorder(statusBar);
            borders.addBorder(toolBar);

            toolBar.getItems().stream()
                    .filter(item -> (item instanceof Control))
                    .collect(Collectors.toList())
                    .forEach(ctrl -> borders.addBorder((Control) ctrl));

            borders.addBorder(regExTextArea);
            borders.addBorder(menuBar);
//            borders.addBorder(findButton);
//            borders.addBorder(matchButton);
//            borders.addBorder(replaceButton);
            borders.addBorder(textFlowScrollPane);
            borders.addBorder(textFlow);
        }

        disableControlls();

        regExTextArea.setWrapText(true);
        regExTextArea.setFont(font);


        if (log.isTraceEnabled()) {
            textFlowScrollPane.vvalueProperty().addListener((var obv, var oldVal, var newVal) -> {
                log.debug("### obv: {}, oldVal: {}, newVal: {}", obv, oldVal, newVal);
            });
        }

        searchText = new Text("");
        searchText.setBoundsType(DEFAULT_TEXT_BOUNDS_TYPE);
        searchText.setFont(font);

        textFlow.getChildren().clear();
        textFlow.setPadding(new Insets(10));
        textFlow.setLineSpacing(1);
        textFlow.setTextAlignment(TextAlignment.LEFT);
        textFlow.setVisible(false);

        textFlow.getChildren().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                if (c.next()) {
                    log
                            .debug("### textFlow.child list change listener: was added: {}", c
                                    .wasAdded());
                    if (c.wasAdded()) {
                        enableControlls();
                    }
                }
            }
        });

        selectFontComboBox.getItems().addAll(Font.getFamilies());

//        textFlow.addEventHandler(EventType.ROOT, e -> {
//            log.debug("### textFlow event hander: eventType: {}", e.getEventType());
//        });

        log.debug("### textFlowScrollPane.getVmin()  : {}", textFlowScrollPane.getVmin());
        log.debug("### textFlowScrollPane.getVmax()  : {}", textFlowScrollPane.getVmax());
        log.debug("### textFlowScrollPane.getVvalue) : {}", textFlowScrollPane.getVvalue());
        log.debug("### textFlowScrollPane.HbarPolicy): {}", textFlowScrollPane.getHbarPolicy());

    }

    /**
     * <p>
     * Getter for the field <code>scene</code>.</p>
     *
     * @return a {@link javafx.scene.Scene} object.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * <p>
     * Setter for the field <code>scene</code>.</p>
     *
     * @param scene a {@link javafx.scene.Scene} object.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @FXML
    private void onClear(ActionEvent event) {
        log.debug("### onClear(): event: {}", event);
        this.resetSearchText();
        event.consume();
    }

    private String regularExpression() {
        return regExTextArea.getText();
    }

    @FXML
    private void onFind(ActionEvent event) {
        log.debug("### onFind: {}", event);
        resetSearchText();
        if (!regularExpression().isBlank() && !textFlow.getChildren().isEmpty()) {
            List<MatchResult> matchResults = new ArrayList<>();

            Matcher matcher = buildMatcher(regularExpression(), searchTextStr);

            matchResults.addAll(matcher.results().collect(toList()));

            if (!matchResults.isEmpty()) {
                List<Node> textList = diceAndSplice(matchResults);
                populateTextFlow(textFlow, textList);
            } else {
                populateTextFlow(textFlow, searchText);
            }
        }
        event.consume();
    }

    private static Matcher buildMatcher(String regEx, String searchText) {
        return Pattern.compile(regEx).matcher(searchText);
    }

    private static void populateTextFlow(TextFlow textFlow, Text text) {
        textFlow.getChildren().clear();
        textFlow.getChildren().add(text);
    }

    private static void populateTextFlow(TextFlow textFlow, List<Node> textList) {
        textFlow.getChildren().clear();
        if (!textList.isEmpty()) {
            textFlow.getChildren().addAll(textList);
            textFlow.setPadding(new Insets(10));
            textFlow.setLineSpacing(1);
            textFlow.setTextAlignment(TextAlignment.LEFT);
        }
    }

    private List<Node> diceAndSplice(List<MatchResult> matchResults) {
        final List<Node> textList = new ArrayList<>();
        final String textStr = searchText.getText();
        int i = 0;
        for (MatchResult mr : matchResults) {
            String subStr = textStr.substring(i, mr.start());
            Text subStrText = new Text(subStr);
            textList.add(formatTextNode(subStrText));

            String diced = textStr.substring(mr.start(), mr.end());
            textList.add(highlight(new Text(diced)));
            i = mr.end();
        }
        if (i < textStr.length()) {
            String remaining = textStr.substring(i);
            Text text = new Text(remaining);
            textList.add(formatTextNode(text));
        }

        //debugPrintTextList(textList);

        return textList;
    }

    private Node highlight(Text textNode) {
        textNode.setBoundsType(DEFAULT_TEXT_BOUNDS_TYPE);
        textNode.setFont(font);
        textNode.setFill(Paint.valueOf(DEFAULT_HIGHLIGHT_FILL_COLOR));
        StackPane stackPane = new StackPane(textNode);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setStyle(String.format("-fx-background-color: %s;",
                DEFAULT_BACKGROUND_HIGHLIGHT_COLOR));
        stackPane.setPadding(new Insets(0, 0, 0, 0));
        return stackPane;
    }

    private Node formatTextNode(Text textNode) {
        textNode.setBoundsType(DEFAULT_TEXT_BOUNDS_TYPE);
        textNode.setFont(font);
        return textNode;
    }

    @FXML
    private void onHideBorders(ActionEvent event) {
        Platform.runLater(() -> {
            borders.hideBorders();
        });
    }

    @FXML
    private void onMatch(ActionEvent event) {
        log.debug("### onMatch: {}", event);
        event.consume();
    }

    @FXML
    private void onOpenFile(ActionEvent event) {
        log.debug("### onOpenFile: {}", event);
        Platform.runLater(() -> {
            Path path = chooseFile();
            if (path != null) {
                try {
                    textFlow.getChildren().clear();
                    String fileContent = Files.readString(path, StandardCharsets.UTF_8);
                    this.searchTextStr = fileContent;
                    this.searchText = new Text(searchTextStr);
                    this.searchText.setFont(font);
                    textFlow.getChildren().add(searchText);
                    textFlow.setVisible(true);
                } catch (IOException ex) {
                    log.error("### Caught exception loading text from file: {}", path);
                }
            }
        });
        event.consume();
    }

    private void resetSearchText() {
        textFlow.getChildren().clear();
        searchText = new Text(searchTextStr);
        searchText.setFont(font);
        textFlow.getChildren().add(searchText);
    }

    @FXML
    private void onReplace(ActionEvent event) {
        log.debug("### entered onReplace(): {}", event);
        event.consume();
    }

    @FXML
    private void onSelectFont(ActionEvent event) {
        /**
         * Figure out how to set the selected font.
         */
        log.debug("### entered onSelectFont(): event: {}", event);
        Platform.runLater(() -> {
            String selectedItem = selectFontComboBox.getSelectionModel().getSelectedItem();
            log.debug("### selectedItem: {}", selectedItem);
            this.font = Font.font(selectedItem, DEFAULT_TEXT_SIZE);
            textFlow.getChildren().forEach(c -> {
                if (c instanceof Text) {
                    ((Text) c).setFont(font);
                }
            });
        });
        event.consume();
    }

    @FXML
    private void onShowBorders(ActionEvent event) {
        Platform.runLater(() -> {
            borders.showBorders();
        });
        event.consume();
    }

    @FXML
    private void onTextAlignmentCenter(ActionEvent event) {
        log.debug("### entered onTextAlignmentCenter(): event: {}", event);
        Platform.runLater(() -> {
            textFlow.setTextAlignment(TextAlignment.CENTER);
        });
        event.consume();
    }

    @FXML
    private void onTextAlignmentJustify(ActionEvent event) {
        log.debug("### entered onTextAlignmentJustify(): event: {}", event);
        Platform.runLater(() -> {
            textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        });
        event.consume();
    }

    @FXML
    private void onTextAlignmentLeft(ActionEvent event) {
        log.debug("### entered onTextAlignmentLeft(): event: {}", event);
        Platform.runLater(() -> {
            textFlow.setTextAlignment(TextAlignment.LEFT);
        });
        event.consume();
    }

    @FXML
    private void onTextAlignmentRight(ActionEvent event) {
        log.debug("### entered onTextAlignmentRight(): event: {}", event);
        Platform.runLater(() -> {
            textFlow.setTextAlignment(TextAlignment.RIGHT);
        });
        event.consume();
    }

    private static void debugLogAllChildNodes(TextFlow textFlow) {
        if (log.isDebugEnabled() || log.isTraceEnabled()) {
            log.debug("### TextFlow Child Node List:");
            log.debug("### -------------------------");
            textFlow.getChildren().forEach(node -> {
                log.debug("###    type: {}, value: {}", node.getClass(), node.toString());
            });
            log.debug("### -------------------------");
        }
    }

    private static void debugLogFontInfo(Font font) {
        if (log.isDebugEnabled() || log.isTraceEnabled()) {
            log.debug("### All Font Info");
            log.debug("### -------------");
            log.debug("###    fixedWidthFont style: {}", font.getStyle());
            log.debug("###    default font: {}", Font.getDefault());
            log.debug("");
            Font.getFamilies().forEach(f -> {
                log.debug("###    font family: {}", f);
                Font.getFontNames(f).forEach(n -> log.debug("###        font name: {}", n));
            });
            log.debug("### -------------");
        }
    }

    private String createMatchResultString(MatchResult mr) {
        StringBuilder sb = new StringBuilder();
        sb.append("start: ").append(mr.start()).append("\n")
                .append("end: ").append(mr.end()).append("\n")
                .append("groupCount: ").append(mr.groupCount()).append("\n");
        return sb.toString();
    }

    private Path chooseFile() {
        log.debug("### entered chooseFile()");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Graphviz File");
        File selectedFile = fileChooser.showOpenDialog(mainContainer.getScene().getWindow());
        if (selectedFile != null) {
            return selectedFile.toPath();
        } else {
            return null;
        }
    }

    private void disableControlls() {
        toolBar.setDisable(true);
//        findButton.setDisable(true);
//        matchButton.setDisable(true);
//        replaceButton.setDisable(true);
//        clearButton.setDisable(true);
        regExTextArea.setDisable(true);
        textFlowScrollPane.setDisable(true);
//        selectFontComboBox.setDisable(true);
    }

    private void enableControlls() {
        toolBar.setDisable(false);
//        findButton.setDisable(false);
//        matchButton.setDisable(false);
//        replaceButton.setDisable(false);
//        clearButton.setDisable(false);
        regExTextArea.setDisable(false);
        textFlowScrollPane.setDisable(false);
//        selectFontComboBox.setDisable(false);
    }

    private static void debugPrintTextList(List<Node> textList) {
        if (!log.isDebugEnabled()) {
            return;
        }
        log.debug("### -------------------------------------");
        log.debug("### Printing diced and splice text lines:");
        log.debug("### -------------------------------------");
        textList.forEach((var node) -> {
            if (node != null) {
                if (node instanceof StackPane) {
                    var sp = (StackPane) node;
                    log.debug("###     stackPane.alignment  : {}", sp.getAlignment());
                    log.debug("###     stackPane.contentBias: {}", sp.getContentBias());
                    log.debug("###     stackPane.cssMetaData: {}", sp.getCssMetaData());
                    log.debug("###     stackPane.style      : {}", sp.getStyle());
                    log.debug("###     -------------------------");
                    log.debug("###     Printing StackPane child:");
                    log.debug("###     -------------------------");
                    sp.getChildren().forEach((var child) -> {
                        log.debug("###         child type : {}", child.getClass());
                        if (child instanceof Text) {
                            Text text = (Text) child;
                            log.debug("###             text.text         : {}", text.getText());
                            log.debug("###             text.style        : {}", text.getStyle());
                            log
                                    .debug("###             text.boundsType   : {}", text
                                            .getBoundsType());
                            log.debug("###             text.font         : {}", text.getFont());
                            log.debug("###             text.cssMetaData  : {}", text
                                    .getCssMetaData());
                            log.debug("###             text.textAlignment: {}", text
                                    .getTextAlignment());
                            log
                                    .debug("###             text.textOrigin   : {}", text
                                            .getTextOrigin());
                            log.debug("###             text.fillColor    : {}", text.getFill());
                        }
                    });
                    log.debug("###     -------------------------");
                }
//                else {
//                    log.debug("###         node type : {}\n", node.getClass().getSimpleName());
//                    log.debug("###         node style: {}\n", node.getStyle());
//                }
            }
        });
        log.debug("### -------------------------------------");


    }
}
