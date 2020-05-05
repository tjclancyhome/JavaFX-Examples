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
package org.tjc.jfx.jfxfontexamples;

import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.FocusModel;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.converter.DoubleStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.common.utils.config.ConfigFactory;
import org.tjc.common.utils.config.ConfigImpl;
import org.tjc.common.utils.config.PropertiesConfigLoader;
import org.tjc.common.utils.config.io.ConfigIO;
import org.tjc.jfx.jfxcomponents.border.Borders;
import org.tjc.jfx.jfxcomponents.themes.Themes;
import org.tjc.jfx.jfxcomponents.toolbar.ToolBarSpacer;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXFontExamplesSceneController {
    private static final Logger log = LoggerFactory.getLogger(JFXFontExamplesSceneController.class);

    private static final Double DEFAULT_FONT_SIZE = 46.0D;
    private static final String HIDDEN_ROOT_NODE = "Fonts";

    @FXML
    private CheckMenuItem borderDebugMenuItem;
    @FXML
    private CheckMenuItem darkModeDebugMenuItem;
    @FXML
    private ComboBox<Double> fontSizeComboBox;
    @FXML
    private Slider fontSizeSlider;
    @FXML
    private TreeView<String> fontTreeView;
    @FXML
    private AnchorPane fontViewPane;
    @FXML
    private ScrollPane fontViewScrollPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private VBox rootWindow;
    @FXML
    private SplitPane splitPane;
    @FXML
    private HBox statusBar;
    @FXML
    private ToolBar toolBar;

    // For debugging.  It helps to see how layouts and controls are set in a JavaFX application.
    private Borders borders;

    private DoubleProperty fontSizeProperty;
    private double currentFontSize;
    private Scene scene;
    private BooleanProperty isDarkModeProperty;
    private SearchBarControl searchBarControl;
    private FontDisplayControl fontDisplayControl;
    private ConfigImpl config;

    public JFXFontExamplesSceneController() {
    }

    /**
     * Initializes the controller class.
     *
     * @throws java.io.IOException Throws IOException if the ConfigImpl object cannot load the default
     *                             application.properties file.
     */
    public void initialize() throws Exception {
        log.trace("### entered initialize( )");

        config = ConfigFactory.newConfig(new PropertiesConfigLoader(getClass().getResource(
            "/application.properties")));
        ConfigIO.print(config);

        currentFontSize = DEFAULT_FONT_SIZE;

        isDarkModeProperty = new SimpleBooleanProperty(false);
        isDarkModeProperty.bind(darkModeDebugMenuItem.selectedProperty());

        loadFontTree();
        initializeDebugBorders();
        initializeFontTreeViewListener();
        initializeFontTreeViewContextMenu();
        initializeToolBar();
        initializeFontSizeControls();
        initializeFontDisplayControl();

        initializeFontDetails();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void initializeDebugBorders() {
        borders = new Borders();
        borders.addBorder(menuBar);
        borders.addBorder(toolBar, Color.DARKMAGENTA);
        borders.addBorder(fontTreeView);
        borders.addBorder(fontViewPane);
        borders.addBorder(fontViewScrollPane, Color.BLUE);
        borders.addBorder(rootWindow);
        borders.addBorder(fontSizeSlider);
        borders.addBorder(fontSizeComboBox);
        borders.addBorder(splitPane);
        borders.addBorder(fontDisplayControl, Color.GREEN);
        borders.addBorder(statusBar);
    }

    private void initializeFontDetails() {
        log.debug("### entered initializeFontDetails()");
        java.awt.Font font = new java.awt.Font("Menlo", java.awt.Font.BOLD | java.awt.Font.ITALIC, 14);
        log.debug("    ### java.awt.font: {}", font);
        log.debug("    ### java.awt.font.family: {}", font.getFamily());
        log.debug("    ### java.awt.font.fontName: {}", font.getFontName());
        log.debug("    ### java.awt.font.name: {}", font.getName());
        log.debug("    ### java.awt.font.psname: {}", font.getPSName());
        log.debug("    ### java.awt.font.size: {}", font.getSize());
        log.debug("    ### java.awt.font.style: {}", font.getStyle());
        log.debug("    ### java.awt.font.attributes: {}", font.getAttributes());
        log.debug("    ### java.awt.font.toString(): {}", font.toString());
    }

    private void initializeFontDisplayControl() {
        fontDisplayControl = new FontDisplayControl(new Font("System", 13));
        fontDisplayControl.textAlignmentProperty().set(TextAlignment.CENTER);
        fontViewScrollPane.setContent(fontDisplayControl);
    }

    private void initializeToolBar() {
        searchBarControl = new SearchBarControl();
        borders.addBorder(searchBarControl);
        GridPane.setVgrow(searchBarControl, Priority.NEVER);

        searchBarControl.getSearchTextField().onActionProperty().addListener((var obsv, var oldVal, var newVal) -> {
            log.debug("### implement searchTextField.onAction()...");
        });

        searchBarControl.getSearchTextField().setOnKeyPressed((var keyEvent) -> {
            log.debug("### implement searchTextField.onKeyPressed()...");
        });

        ToolBarSpacer toolBarSpacer = ToolBarSpacer.newFlexiSpacer();
        borders.addBorder(toolBarSpacer);

        VBox.setVgrow(toolBarSpacer, Priority.NEVER);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction((var event) -> {
            log.trace("### refreshButton.onAction: {}", event);

            currentFontSize = DEFAULT_FONT_SIZE;

            FocusModel<TreeItem<String>> focusModel = fontTreeView.getFocusModel();

            int focusedIndex = focusModel.getFocusedIndex();
            log.trace("### focusedIndex: {}", focusedIndex);

            TreeItem<String> focusedItem = focusModel.getFocusedItem();
            log.trace("### focusedItem: {}", focusedItem);

            MultipleSelectionModel<TreeItem<String>> msm = fontTreeView.getSelectionModel();
            msm.setSelectionMode(SelectionMode.SINGLE);
            msm.selectAll();

            TreeItem<String> selectedItem = msm.getSelectedItem();
            log.trace("### selectedItem: {}", selectedItem);

            loadFontTree();

            msm.select(selectedItem);

            focusModel.focus(focusedIndex);
            resetFontDisplay();
        });

        toolBar.getItems().add(borders.add(refreshButton));
        toolBar.getItems().add(toolBarSpacer);
        toolBar.getItems().add(searchBarControl);
        toolBar.setPadding(new Insets(0, 5, 0, 5));
    }

    private void initializeFontSizeControls() {
        fontSizeProperty = new SimpleDoubleProperty(DEFAULT_FONT_SIZE);
        fontSizeProperty.bind(fontSizeComboBox.getSelectionModel().selectedItemProperty());
        fontSizeComboBox.getItems().addAll(
            3.0, 5.0, 7.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 18.0, 24.0, 36.0, 46.0, 48.0, 64.0, 72.0,
            96.0, 114.0, 288.0);
        fontSizeComboBox.getSelectionModel().select(DEFAULT_FONT_SIZE);
        fontSizeComboBox.setConverter(new DoubleStringConverter());
        fontSizeComboBox.getSelectionModel().selectedItemProperty().addListener((
            var obsv, var oldVal, var newVal) -> {
            fontSizeSlider.setValue(newVal);
        });
        fontSizeSlider.adjustValue(currentFontSize);
        fontSizeSlider.valueProperty().addListener((var obsv, var oldVal, var newVal) -> {
            double sliderValue = newVal.doubleValue();
            fontSizeComboBox.getSelectionModel().select(sliderValue);
        });
    }

    @FXML
    private void onSetDarkMode(ActionEvent event) {
        log.trace("### onSetDarkMode action: event: {}", event);
        Themes.setDarkMode(darkModeDebugMenuItem.isSelected(), scene);
//        
//        Platform.runLater(() -> {
//            setDarkMode();
//        });
        event.consume();
    }

//    private void setDarkMode() {
//        String darkModeTheme = Theme.formatThemeName("dark-theme.css");
//        if (darkModeDebugMenuItem.isSelected()) {
//            if (!scene.getStylesheets().contains(darkModeTheme)) {
//                scene.getStylesheets().add(darkModeTheme);
//            }
//        } else if (scene.getStylesheets().contains(darkModeTheme)) {
//            scene.getStylesheets().remove(darkModeTheme);
//        }
//    }
    @FXML
    private void onToggleBorders(ActionEvent event) {
        Platform.runLater(() -> {
            if (borderDebugMenuItem.isSelected()) {
                borders.showBorders();
            } else {
                borders.hideBorders();
            }
        });
    }

    @FXML
    private void onSelectFontSize(ActionEvent event) {
        log.trace("### entered onSelectFontSize(): event: {}", event);

        currentFontSize = fontSizeComboBox.getSelectionModel().getSelectedItem();
        fontDisplayControl.setFontSize(currentFontSize);

        if (!event.isConsumed()) {
            event.consume();
        }

        log.trace("### exiting onSelectFontSize(): event: {}", event);
    }

    private void loadFontTree() {
        log.trace("### entered loadFontTree()");

        List<String> fontFamilies = Font.getFamilies();
        TreeItem<String> root = new TreeItem<>("Fonts");
        root.setExpanded(true);
        fontTreeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fontTreeView.setRoot(root);
        fontTreeView.setShowRoot(false);


        List<TreeItem<String>> filteredFamilyNames = fontFamilies.stream()
            .filter((String fam) -> !fam.startsWith("."))
            .map(fam -> treeItem(fam))
            .collect(Collectors.toList());

        root.getChildren().setAll(filteredFamilyNames);

        root.getChildren().forEach((TreeItem<String> treeItem) -> {
            treeItem.setExpanded(false);
            treeItem.getChildren().setAll(buildFontStylesForFontFamily(treeItem.getValue()));
        });

        try {
            fontSizeComboBox.getSelectionModel().select(currentFontSize);
        } catch (Exception ex) {
            log.error("### Caught exception setting default selected font size. exception: {}",
                ex.getMessage());
            throw ex;
        }
        log.trace("### exiting loadFontTree()");
    }

    private List<TreeItem<String>> buildFontStylesForFontFamily(String fontFamilyName) {
        log.trace("### entered buildFontStylesForFontFamily()");
        List<TreeItem<String>> fontStyleTreeItems
            = Font.getFontNames(fontFamilyName).stream()
                .map((var fontName) -> {
                    String style = "";
                    try {
                        style = extractStyleStringFromFontName(fontName, fontFamilyName);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    return treeItem(style);
                })
                .filter(ti -> !ti.getValue().isBlank())
                .sorted((treeItem1, treeItem2) -> {
                    return treeItem1.getValue().compareTo(treeItem2.getValue());
                })
                .collect(Collectors.toList());
        log.trace("### exiting buildFontStylesForFontFamily()");
        return fontStyleTreeItems;
    }

    private void initializeFontTreeViewListener() {
        fontTreeView.getSelectionModel().selectedItemProperty().addListener(
            (var obsv, var oldTreeItem, var newTreeItem) -> {

                /*
                 * This should never happen... but...
                 */
                if (newTreeItem == null) {
                    return;
                }

                var parentTreeItem = newTreeItem.getParent();
                resetFontDisplay();

                /*
                 * If the value is a font family node...
                 *
                 * HiddenRoot -> Font Family -> Style
                 *
                 */
                var isFontFamilyNode = parentTreeItem.getValue().equalsIgnoreCase(HIDDEN_ROOT_NODE);
                if (isFontFamilyNode) {
                    var fontFamily = newTreeItem.getValue();

                    /*
                     * The fontFamily node, when selected, will show an example for each of its
                     * styles.
                     */
                    List<Font> fontFamilyFonts = newTreeItem.getChildren()
                        .stream()
                        .map(style -> createFont(fontFamily, style.getValue()))
                        .collect(Collectors.toList());

                    if (!fontFamilyFonts.isEmpty()) {
                        fontFamilyFonts.forEach(fontFam -> {
                            fontDisplayControl.addEntry(fontFam);
                        });
                    }
                } else {
                    var fontFamily = newTreeItem.getParent().getValue();
                    var fontStyle = newTreeItem.getValue();
                    Font font = createFont(fontFamily, fontStyle);
                    fontDisplayControl.addEntry(font);
                }
            });
    }

    private void resetFontDisplay() {
        fontDisplayControl.getChildren().clear();
    }

    private void initializeFontTreeViewContextMenu() {
        MenuItem expandAll = new MenuItem("Expand All");
        expandAll.setOnAction((var actionEvent) -> {
            fontTreeView.getRoot().getChildren().forEach((treeItem) -> {
                treeItem.setExpanded(true);
            });
        });

        MenuItem collapseAll = new MenuItem("Collapse All");
        collapseAll.setOnAction((actionEvent) -> {
            fontTreeView.getRoot().getChildren().forEach((treeItem) -> {
                treeItem.setExpanded(false);
            });
        });

        fontTreeView.setContextMenu(new ContextMenu(expandAll, collapseAll));
    }

    private Font createFont(String fontFamily, String fontStyle) {
        String fullFontName = createFullFontName(fontFamily, fontStyle);
        Font font = new Font(fullFontName, currentFontSize);



        /*
         * A hack but this will append "Regular" to the end of the full name and try to load it.
         * Seems to work.
         */
        if (!fontFamily.equals(font.getFamily()) && font.getFamily().equals("System")) {
            // See if adding "Regular" to the end will load the corrent font.
            if (!fullFontName.contains("Regular")) {
                fullFontName += " Regular";
                font = new Font(fullFontName, currentFontSize);
            }
        }
        return font;
    }

    private static String createFullFontName(String fontFamily, String fontStyle) {
        String fullFontName = String.format("%s %s",
            fontFamily,
            (fontStyle.equalsIgnoreCase("Regular") ? "" : fontStyle))
            .trim();
        return fullFontName;
    }

    /**
     * This method takes a font name (which is the font family plus style) and extracts the style
     * from the font name.
     *
     * @param fontName       The name of a font e.g. 'American Typewriter Bold'
     * @param fontFamilyName The name of the font family 'American Typewriter'
     *
     * @return
     */
    private static String extractStyleStringFromFontName(String fontName, String fontFamilyName) throws Exception {
        String style;
        if (fontName.equalsIgnoreCase(fontFamilyName)) {
            /*
             * If both the font and the fontFamily are equal, then make style == 'Regular'.
             * This doesn't always work.
             *
             */
            style = "Regular";
        } else if (!fontName.toLowerCase().contains(fontFamilyName.toLowerCase())) {
            style = "";
        } else {
            int familyNameIndx = fontName.indexOf(fontFamilyName);
            if (familyNameIndx == -1) {
                throw new Exception(
                    "familyNameIndx was -1 at this point and it shouldn't be... hmmm...");
            }
            /*
             * Lop off the style info from the font name.
             */
            int styleIndx = familyNameIndx + fontFamilyName.length() + 1;
            style = fontName.substring(styleIndx);
        }
        return style;
    }

    private static TreeItem<String> treeItem(String val) {
        return new TreeItem<>(val);
    }

    private static TreeItem<String> treeItem() {
        return new TreeItem<>();
    }

    @FXML
    private void onTreeViewContextMenu(ContextMenuEvent event) {
        log.trace("entered onTreeViewConextMenu(): event: {}", event);
        log.trace("exiting onTreeViewConextMenu(): event: {}", event);
    }

    private void debugViewFontInfo() {
        if (log.isDebugEnabled()) {
            log.debug("### entered debugViewFontInfo()");
            log.debug("### -------------------------------------------------");
            log.debug("### All Font Info");
            log.debug("### -------------------------------------------------");
            log.debug("### default font: {}", Font.getDefault());
            log.debug("");
            Font.getFamilies().forEach((var fontFamilyName) -> {
                log.debug("### font family: {}", fontFamilyName);
                List<String> fontNames = Font.getFontNames(fontFamilyName);
                fontNames.forEach((var fontName) -> {
                    log.debug("### font name       : {}", fontName);
                    Font constructedFont = new Font(fontName, 13.0);
                    log.debug("### constructed font: {}", constructedFont);
                    if ((!fontFamilyName.contains("System")) && constructedFont.getFamily()
                        .contains("System")) {
                        log
                            .debug("!!!!! family: {} != constructed family: {}",
                                fontFamilyName, constructedFont.getFamily());
                    }
                });

            });
            log.debug("");
            log.debug("### -------------------------------------------------");
            log.debug("### exiting debugViewFontInfo()");

        }
    }

    private void debugViewFontInfo2() {
        if (log.isDebugEnabled()) {
            log.debug("### entered debugViewFontInfo2()");
            log.debug("### -------------------------------------------------");
            log.debug("### All Font Info");
            log.debug("### -------------------------------------------------");
            log.debug("### default font: {}", Font.getDefault());
            log.debug("###");
            Font.getFontNames().forEach((var fontName) -> {
                log.debug("### font name: '{}'", fontName);
                Font font = new Font(fontName, 11.0);
                log.debug("### font     : {}", font);
                if (font.getFamily().equalsIgnoreCase("System")) {
                    if (!fontName.contains("System")) {
                        log.debug(
                            "### constructed font family: {}, but tried to construct font name: {}",
                            font.getFamily(), fontName);
                    }
                }
            });
            log.debug("###");
            log.debug("### -------------------------------------------------");
            log.debug("### exiting debugViewFontInfo2()");
        }
    }

    private static void debugShowAllFontNames() {
        if (log.isDebugEnabled()) {
            log.debug("### entered debugShowAllFontNames()");
            log.debug("### -------------------------------------------------");
            Font.getFontNames().forEach(fontName -> {
                Font font = Fonts.createFont(fontName, 36.0);
                log.debug("### fontName: {}, font: {}", fontName, font);
            });
            log.debug("### -------------------------------------------------");
            log.debug("### exiting debugShowAllFontNames()");
        }
    }

}
