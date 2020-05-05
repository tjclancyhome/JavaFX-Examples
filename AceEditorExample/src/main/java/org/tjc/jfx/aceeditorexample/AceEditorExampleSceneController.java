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
package org.tjc.jfx.aceeditorexample;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.aceeditorexample.model.CursorPosition;
import org.tjc.jfx.aceeditorexample.model.Editor;
import org.tjc.jfx.aceeditorexample.util.AceEditorExampleUtils;
import org.tjc.jfx.jfxcomponents.themes.Themes;
import org.w3c.dom.Document;

/**
 * The AceEditorExample scene FXML Controller class.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class AceEditorExampleSceneController {
    private static final Logger log = LoggerFactory.getLogger(AceEditorExampleSceneController.class);

    private static final String APP_TITLE = "AceEditorExample";

    @FXML
    private AnchorPane aceEditorAnchorPane;
    @FXML
    private VBox aceEditorMainContainer;
    @FXML
    private WebView aceWebView;
    /*
     * I might not need to actually include these fxid-named controls.
     */
    @FXML
    private MenuBar aceEditorMenuBar;
    @FXML
    private ToolBar aceEditorToolBar;
    @FXML
    private MenuItem blurDevMenuItem;
    @FXML
    private Menu developMenu;
    @FXML
    private MenuItem goToEndMenuItem;
    @FXML
    private CheckMenuItem showDarkModeMenuItem;
    @FXML
    private CheckMenuItem showToolBarMenuItem;
    @FXML
    private MenuItem toggleCommentMenuItem;
    @FXML
    private Menu openRecentMenu;
    @FXML
    private ComboBox<String> selectFontList;
    @FXML
    private MenuItem selectFontMenuItem;
    @FXML
    private ComboBox<String> selectFontSizeList;
    @FXML
    private ComboBox<String> selectModeComboBox;
    @FXML
    private ComboBox<String> selectThemeComboBox;
    @FXML
    private MenuItem showHistoryMenuItem;
    @FXML
    private MenuItem showOptionsMenuItem;
    @FXML
    private MenuItem simulateDeleteLineKeyMenuItem;
    @FXML
    private MenuItem simulateEnterKeyMenuItem;
    @FXML
    private MenuItem zoomInMenuItem;
    @FXML
    private MenuItem zoomOutMenuItem;

    /*
     * StatusBar line count controls, etc.
     */
    private JSObject jsoAce;
    private Editor editor;
    private AceEditorStatusBar statusBar;
    private WebEngine webEngine;
    private Stage stage;
    private Path pathToAceSource;
    private RecentFiles recentFiles;
    private final BooleanProperty bufferEmptyProperty = new SimpleBooleanProperty(true);
    private final BooleanProperty documentLoadedProperty = new SimpleBooleanProperty(false);
    private AceEventHandlers aceEventHandlers;
    @FXML
    private Menu zoomViewMenu;

    /**
     * <p>
     * Constructor for AceEditorExampleSceneController.</p>
     */
    public AceEditorExampleSceneController() {
    }

    /**
     * <p>
     * Setter for the field <code>stage</code>.</p>
     *
     * @param stage a {@link javafx.stage.Stage} object.
     */
    public void setStage(Stage stage) {
        Objects.requireNonNull(stage, () -> "The Stage argument is null.");
        this.stage = stage;
        this.stage.widthProperty().addListener((var obs, var oldValue, var newValue) -> {
            log.debug("### primaryStage width changed: oldValue: {}, newValue: {}", oldValue,
                newValue);
        });
    }

    /**
     * <p>
     * Getter for the field <code>stage</code>.</p>
     *
     * @return a {@link javafx.stage.Stage} object.
     */
    public Stage getStage() {
        return stage;
    }

    public BooleanProperty bufferEmptyProperty() {
        return bufferEmptyProperty;
    }

    public boolean isBufferEmpty() {
        return bufferEmptyProperty.get();
    }

    public void setBufferEmpty(boolean b) {
        bufferEmptyProperty.set(b);
    }

    /**
     * Initializes the controller class.
     *
     * @throws java.net.URISyntaxException if any.
     * @throws java.io.IOException         if any.
     */
    public void initialize() throws URISyntaxException, IOException {
        log.debug("### entered initialize()");
        URL resource = getClass().getResource("/js/ace/src-noconflict");
        pathToAceSource = Paths.get(resource.toURI());
        log.debug("###     pathToAceSource: {}", pathToAceSource);
        aceWebView.setContextMenuEnabled(true);
        aceWebView.setFontSmoothingType(FontSmoothingType.LCD);
        aceWebView.setVisible(false);
        webEngine = aceWebView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.setUserDataDirectory(getUserDataDirectory().toFile());

        log.debug("###     calling initializers.");
        initializeThemes();
        initializeModes();
        initializeSelectFontList();
        initializeSelectFontSizeList();
        initializeStatusBar();
        initializeListenersAndBindings();

        log.debug("###     hiding toolbar.");
        hideToolBar();

        log.debug("###     loading resource: /html/Ace.html");
        webEngine.load(getClass().getResource("/html/Ace.html").toExternalForm());

        log.debug("###     adding state property listener.");
        webEngine.getLoadWorker().stateProperty().addListener(
            (ObservableValue<? extends State> obsv, State previousState, State newState) -> {
                log.trace("### entered loadWorker.stateProperty.changeListener");
                log.trace("###    load worker state transition: {} -> {}", previousState, newState);
                if (newState == State.SUCCEEDED) {
                    aceEditorMainContainer.addEventHandler(AceEventHandlers.onBlurEvent, (Event t) -> {
                        log.trace("### entered aceEditorMainContainer.eventHandler: event: {}", t
                            .getEventType());
                    });

                    jsoAce = (JSObject) executeScript("ace");
                    log.trace("###    executed script('ace'): jsoAce: {}", jsoAce);

                    JSObject jsoEditor = (JSObject) jsoAce.call("edit", "editor");
                    editor = new Editor(jsoEditor, aceWebView);
                    log.trace("###    loaded editor object: editor: {}", editor);

                    initializeCallbacks();

                    editor.setStyle("Bold");

                    aceWebView.setVisible(false);
                    log.trace("###    set aceEditorWebView.visible to true...");

                    recentFiles = new RecentFiles(openRecentMenu);
                    log.trace("###    CreatedRecentFiles list: {}", recentFiles);

                    debugPrintWebViewProperties();
                    debugPrintWebEngineProperties();

                    aceEditorMainContainer.addEventHandler(AceEventHandlers.onChangeCursorEvent,
                        (Event event) -> {
                            log.trace("### entered aceEditorMainContainer.eventHandler: event: {}", event
                                .getEventType());

                            CursorPosition cursorPosition = editor.getSelection().getCursor();
                            log.trace("###    cursorPosition: {}", cursorPosition);
                            statusBar.updateCursorPosition(cursorPosition);
                            event.consume();
                        });
                }
            });

        webEngine.getLoadWorker().stateProperty().addListener((var obsv) -> {
            log.debug("### entered loadWorker.stateProperty.invalidationListener");
            log.debug("###     invalidated observable: {}", obsv);
            log.debug("### exited loadWorker.stateProperty.invalidationListener");

        });

        documentLoadedProperty.addListener((var obsv, var oldVal, var newVal) -> {
            log.debug("### entered documentLoadedProperty change listener.");
            log.debug("###    obsv: {}, oldVal: {}, newVal: {}", obsv, oldVal, newVal);
            log.debug("### exited documentLoadedProperty change listener.");
        });
    }

    private void initializeCallbacks() {
        log.debug("### entered initializeCallbacks()");
        var jsoEditor = editor.getJsoEditor();

        aceEventHandlers = new AceEventHandlers(aceEditorMainContainer);
        jsoEditor.setMember("myAceEventHandlers", aceEventHandlers);

        var evalResponse = (JSObject) jsoEditor.eval(
            "this.on('blur', function() { editor.myAceEventHandlers.onBlur(); });");
        log.trace("###    evalResponse: {}", evalResponse);

        evalResponse = (JSObject) jsoEditor.eval(
            "this.getSelection().on('changeCursor', function() { editor.myAceEventHandlers.onChangeCursor(); });");
        log.trace("###    evalResponse: {}", evalResponse);

        log.trace("### exited initializeCallbacks()");
    }

    private Object executeScript(String script) {
        log.debug("### entered executeScript(): script: {}", script);
        Object result = null;
        try {
            result = webEngine.executeScript(script);
            if (result != null) {
                log.trace("###     executeScript result type : {}", result.getClass());
                log.trace("###     executeScript result value: {}", result);
            }
        } catch (Exception ex) {
            log.error("###     Exception caught while executing javascript: {}, exception: {}",
                script, ex.getMessage());
        }
        return result;
    }

    private void initializeStatusBar() {
        log.debug("### entered initializeStatusBar()");
        statusBar = new AceEditorStatusBar();
        aceEditorMainContainer.getChildren().add(statusBar);
    }

    private void initializeListenersAndBindings() {
        log.debug("### entered initializeListenersAndBindings()");

        selectModeComboBox.getSelectionModel().selectedItemProperty().addListener(
            (var obsv, var previousMode, var selectedMode) -> {
                log.trace("### mode selected: {}", selectedMode);
                editor.getEditSession().setMode(selectedMode.toLowerCase());
            });

        selectThemeComboBox.getSelectionModel().selectedItemProperty().addListener(
            (var obsv, var previousTheme, var selectedTheme) -> {
                log.trace("### theme selected: {}", selectedTheme);
                editor.setTheme(selectedTheme.toLowerCase());
            });

        selectFontSizeList.getSelectionModel().selectedItemProperty().addListener(
            (var obs, var oldValue, var newValue) -> {
                log.trace("### selectedItemProperty changeListener: old: {}, new: {}", oldValue,
                    newValue);
                log.trace("### Attempting the set the fontSize to: {}", newValue);
                editor.setOption("fontSize", Integer.valueOf(newValue));
            });

        selectFontList.getSelectionModel().selectedItemProperty().addListener(
            (var obs, var oldValue, var newValue) -> {
                log.trace("### selectedItemProperty changeListener: old: {}, new: {}", oldValue,
                    newValue);
                editor.setOption("fontFamily", newValue);
            });

        selectModeComboBox.disableProperty().bind(documentLoadedProperty.not());
        selectThemeComboBox.disableProperty().bind(documentLoadedProperty.not());
        selectFontSizeList.disableProperty().bind(documentLoadedProperty.not());
        selectFontList.disableProperty().bind(documentLoadedProperty.not());
    }

    private void initializeThemes() throws IOException {
        // this is obviously not the way to go because it reads the ace source code filenames each time the
        // the application starts.
        //
        selectThemeComboBox.getItems().addAll(AceEditorExampleUtils.listAllThemes(pathToAceSource));
    }

    private void initializeModes() throws IOException {
        // this is obviously not the way to go because it reads the ace source code filenames each time the
        // the application starts.
        //
        selectModeComboBox.getItems().addAll(AceEditorExampleUtils.listAllModes(pathToAceSource));
    }

    private void initializeSelectFontList() {
        log.debug("### entered initializeSelectFontList()");
        ObservableList<String> fonts = FXCollections.observableArrayList(Font.getFamilies());
        selectFontList.setItems(fonts);
    }

    private void initializeSelectFontSizeList() {
        log.debug("### entered initializeSelectFontSizeList()");
        ObservableList<String> fontSizes = FXCollections.observableArrayList(
            "3", "5", "8", "9", "10", "12", "13", "14", "18", "24", "36", "48");
        selectFontSizeList.setItems(fontSizes);
    }

    private void onAbout(ActionEvent event) {
        log.debug("### entered onAbout(): event: {}", event);

    }

    @FXML
    private void onBlur(ActionEvent event) {
        log.debug("### entered onBlur(): event: {}", event);
        this.editor.blur();
        event.consume();
    }

    @FXML
    private void onCloseAction(ActionEvent event) {
        log.debug("### entered onCloseAction(): event: {}", event);
        closeDocument();
        event.consume();
    }

    private void closeDocument() {
        editor.setValue("", 0);
        stage.setTitle(APP_TITLE);
        aceWebView.setVisible(false);
        documentLoadedProperty.set(false);
    }

    @FXML
    private void onDevClearSelection(ActionEvent event) {
        log.debug("### entered onDevClearSelection()");
        editor.clearSelection();
        log.debug("### exited onDevClearSelection()");
    }

    @FXML
    private void onGoToEndAction(ActionEvent event) {
        log.debug("### entered onGoToEndAction(): event: {}", event);
        editor.focus();
        editor.navigateFileEnd();
        log.debug("### exited onGoToEndAction()");
    }

    @FXML
    private void onNewAction(ActionEvent event) {
        log.debug("### entered onNewAction(): event: {}", event);
        createNewDocumentWindow();
        createNewDocument();
        event.consume();
        log.debug("### exited onNewAction()");
    }

    private void createNewDocumentWindow() {

    }

    public void createNewDocument() {
        editor.getEditSession().setValue("");
        aceWebView.setVisible(true);
        stage.setTitle("Untitled - " + APP_TITLE);
        editor.moveCursorTo(0, 0);
        editor.focus();
        documentLoadedProperty.set(true);
    }

    @FXML
    private void onOpenAction(ActionEvent event) throws Exception {
        log.debug("### entered onOpenAction(): event: {}", event);
        log.trace("### event.isConsumed? {}", event.isConsumed());
        chooseDocument();
        if (!event.isConsumed()) {
            event.consume();
        }
        log.debug("### exited onOpenAction()");
    }

    private boolean isDocumentLoaded() {
        return documentLoadedProperty.get();
    }

    private void chooseDocument() throws IOException, Exception {
        Path file = this.chooseFile();
        if (file != null) {
            openDocument(file);
        }
    }

    private void openDocument(Path file) throws Exception {
        Document doc = webEngine.getDocument();
        if (doc != null) {
            log.trace("### webEngine: type: {}, value: {}", doc.getClass(), doc);
        }

        String value = Files.readString(file);
        editor.getEditSession().setValue(value);
        stage.setTitle(file.getFileName() + " - " + APP_TITLE);
        aceWebView.setVisible(true);
        int numLines = editor.getEditSession().getSessionDocument().getLength();
        log.trace("### number of lines in document: {}", numLines);
        editor.moveCursorTo(0, 0);
        editor.focus();
        documentLoadedProperty.set(true);
    }

    private void addToRecentlyOpenedFiles(Path file) {
        log.debug("### entered addToRecentlyOpenedFiles(): file: {}", file);
        recentFiles.addFile(file);
    }

    @FXML
    private void onOpenRecent(ActionEvent event) throws Exception {
        log.debug("### entered onOpenRecent(): event: {}", event);
        log.trace("###    source: {}, target: {}", event.getSource(), event.getTarget());
        MenuItem menuItem = (MenuItem) event.getTarget();
        Object userData = menuItem.getUserData();
        if (userData != null) {
            log.trace("###    userData: {}, class: {}", userData, userData.getClass());
            openRecentDocument((Path) userData);
        }
        event.consume();
    }

    private void openRecentDocument(Path file) throws Exception {
        log.debug("### entered openRecentDocument(): file: {}", file);
        if (isDocumentLoaded()) {
            closeDocument();
        }
        openDocument(file);
    }

    @FXML
    private void onPreferencesAction(ActionEvent event) {
        log.debug("### entered onPreferencesAction(): event: {}", event);
    }

    @FXML
    private void onQuitAction(ActionEvent event) {
        log.debug("### entered onQuitAction(): event: {}", event);
    }

    @FXML
    private void onRevertAction(ActionEvent event) {
        log.debug("### entered onRevertAction(): event: {}", event);
    }

    @FXML
    private void onSaveAction(ActionEvent event) {
        log.debug("### entered onSaveAction(): event: {}", event);
    }

    @FXML
    private void onSaveAsAction(ActionEvent event) {
        log.debug("### entered onSaveAsAction(): event: {}", event);
    }

    @FXML
    private void onSelectFont(ActionEvent event) throws IOException {
        log.debug("### entered onSelectFont(): event: {}, event.source: {}",
            event, event.getSource());

//        String fontFamily = editor.getFontFamily();
//        int fontSize = editor.getFontSize();

//        log.debug("### editor.option.fontFamily: {}", fontFamily);
//        log.debug("### editor.option.fontSize: {}", fontSize);
        log.trace("###    editor.font: {}", editor.getEditorFont());
        var fontSelector = new FontSelectorControl(editor.getEditorFont()); //Font.font(fontFamily, fontSize));

        fontSelector.fontProperty().addListener(
            (ObservableValue<? extends Font> obsv, Font oldValue,
                Font newValue) -> {
                if (!newValue.equals(oldValue)) {
                    editor.setEditorFont(newValue);
                }
            });

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initOwner(this.stage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(fontSelector));
        stage.setTitle("Select Font");
        log.trace("###    before: showAndWait()");
        editor.setReadOnly(true);
        stage.showAndWait();
        editor.setReadOnly(false);
        log.trace("###    after showAndWait()");
        Font selectedFont = fontSelector.fontProperty().get();
        log.trace("###    font selected in font selector dialog: {}", selectedFont);
        if (!editorFontEquals(selectedFont)) {
            log.trace("###    editorFont: {} != selectedFont: {}... setting editor font.",
                getEditorFont(), selectedFont);
            setEditorFont(selectedFont);
        }
    }

    @FXML
    private void onShowDarkMode(ActionEvent event) {
        log.debug("### entered onShowDarkMode()");
        String pathToThemeCss = Themes.formatThemeName("/css", "dark-theme.css");
        log.trace("###    pathToThemeCss: {}", pathToThemeCss);
        if (showDarkModeMenuItem.isSelected()) {
            Themes.setTheme(pathToThemeCss, true, stage.getScene());
        } else {
            Themes.setTheme(pathToThemeCss, false, stage.getScene());
        }
    }

    @FXML
    private void onShowHistoryAction(ActionEvent event) {
        log.debug("### entered onShowHistoryAction(): event: {}", event);
        WebHistory history = webEngine.getHistory();
        history.getEntries().forEach(entry -> {
            log.trace("###    history.entry; {}", entry);
        });

    }

    @FXML
    void onZoomIn(ActionEvent event) {
        log.debug("### entered onZoomIn(): event: {}", event);
    }

    @FXML
    void onZoomOut(ActionEvent event) {
        log.debug("### entered onZoomOut(): event: {}", event);
    }

    @FXML
    private void onShowOptions(ActionEvent event) {
        editor.execCommand("showSettingsMenu");
    }

    @FXML
    private void onShowToollBar(ActionEvent event) {
        if (showToolBarMenuItem.isSelected() == true) {
            addToolBar();
        } else {
            removeToolBar();
        }
    }

    private void removeToolBar() {
        log.debug("### entered removeToolBar()");
        if (aceEditorMainContainer.getChildren().contains(aceEditorToolBar)) {
            aceEditorMainContainer.getChildren().remove(aceEditorToolBar);
        }
    }

    private void addToolBar() {
        log.debug("### entered addToolBar()");
        if (!aceEditorMainContainer.getChildren().contains(aceEditorToolBar)) {
            log.debug("### adding toolbar to main scene.");
            aceEditorMainContainer.getChildren().add(1, aceEditorToolBar);
        }
    }

    private void hideToolBar() {
        if (!isToolBarHidden()) {
            removeToolBar();
            showToolBarMenuItem.setSelected(false);
        }
    }

    private void showToolBar() {
        if (isToolBarHidden()) {
            addToolBar();
            showToolBarMenuItem.setSelected(true);
        }
    }

    private boolean isToolBarHidden() {
        return !showToolBarMenuItem.isSelected();
    }

    @FXML
    private void onToggleComment(ActionEvent event) {
        editor.toggleCommentLines();
    }

    /**
     * A temporary method until I get the editor far enough do that I can figure out how to get line
     * count (if
     * one can).
     *
     * @param event The action event driven by menuBar/editMenu/simuleEnterKey.
     */
    @FXML
    private void onSimulateEnterKey(ActionEvent event) {
        log.debug("### onSimulateEnterKey(): event: {}", event);
        statusBar.intLineCountProperty().set(statusBar.intLineCountProperty().get() + 1);
    }

    /**
     * A temporary method until I get the editor far enough do that I can figure out how to get line
     * count (if
     * one can).
     *
     * @param event The action event driven by menuBar/editMenu/simuleDeleteLine.
     */
    @FXML
    private void onSimulateDeleteLineKey(ActionEvent event) {
        log.debug("### onSimulateDeleteLineKey(): event: {}", event);
        if (statusBar.intLineCountProperty().get() <= 0) {
            statusBar.intLineCountProperty().set(0);
        } else {
            statusBar.intLineCountProperty().set(statusBar.intLineCountProperty().get() - 1);
        }
    }

    /**
     * <p>
     * createFxmlLoader.</p>
     *
     * @param location a {@link java.net.URL} object.
     *
     * @return a {@link javafx.fxml.FXMLLoader} object.
     */
    public FXMLLoader createFxmlLoader(URL location) {
        log.debug("### entered createFxmlLoader(URL): location: {}", location);
        return new FXMLLoader(location);
    }

    private Path chooseFile() {
        log.debug("### entered chooseFile()");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            return selectedFile.toPath();
        } else {
            return null;
        }
    }

    private boolean editorFontEquals(Font font) {
        return editor.getFontFamily().equals(font.getFamily())
            && (editor.getFontSize() == (int) font.getSize());
    }

    private void setEditorFont(Font font) {
        editor.setFontFamily(font.getFamily());
        editor.setFontSize((int) font.getSize());
    }

    private Font getEditorFont() {
        log.debug("### entered getEditorFont()");
        return editor.getEditorFont();
    }

    private Path getUserDataDirectory() throws IOException {
        Path userDataDirectory = Paths.get(System.getProperty("user.home"), ".aceEditorExample",
            "userData");
        if (Files.exists(userDataDirectory) == false) {
            userDataDirectory = Files.createDirectories(userDataDirectory);
        }
        return userDataDirectory;
    }

    private void debugPrintPropertyMap() {
        if (log.isDebugEnabled()) {
            log.debug("### ------------");
            log.debug("### Property Map");
            log.debug("### ------------");
            Map<String, Object> propertyMap = editor.getPropertyMap(editor.getOptions());
            propertyMap.forEach((k, v) -> log.debug("{}: {}", k, v));
            log.debug("### ------------");
        }
    }

    private void debugPrintWebEngineProperties() {
        if (log.isDebugEnabled()) {
            log.debug("### ---------------------");
            log.debug("### WenbEngine Properties");
            log.debug("### ---------------------");
            log.debug("### document                 : {}", webEngine.getDocument());
            log.debug("### history                  : {}", webEngine.getHistory());
            log.debug("### location                 : {}", webEngine.getLocation());
            log.debug("### title                    : {}", webEngine.getTitle());
            log.debug("### user agent               : {}", webEngine.getUserAgent());
            log.debug("### user data directory      : {}", webEngine.getUserDataDirectory());
            log.debug("### user style sheet location: {}", webEngine.getUserStyleSheetLocation());
            log.debug("### is JavaScript enabled    : {}", webEngine.isJavaScriptEnabled());
            log.debug("### ---------------------");
        }
    }

    private void debugPrintWebViewProperties() {
        if (log.isDebugEnabled()) {
            WebView webView = aceWebView;
            log.debug("### ------------------");
            log.debug("### WenView Properties");
            log.debug("### ------------------");
            log.debug("### css metadata       : {}", webView.getCssMetaData());
            log.debug("### font scale         : {}", webView.getFontScale());
            log.debug("### font smoothing type: {}", webView.getFontSmoothingType());
            log.debug("### height             : {}", webView.getHeight());
            log.debug("### width              : {}", webView.getWidth());
            log.debug("### max height         : {}", webView.getMaxHeight());
            log.debug("### max width          : {}", webView.getMaxWidth());
            log.debug("### min height         : {}", webView.getMinHeight());
            log.debug("### min width          : {}", webView.getMinWidth());
            log.debug("### preferred height   : {}", webView.getPrefHeight());
            log.debug("### preferred width    : {}", webView.getPrefWidth());
            log.debug("### zoom               : {}", webView.getZoom());
            log.debug("### ------------------");
        }
    }

}
