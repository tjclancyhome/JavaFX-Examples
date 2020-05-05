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
package org.tjc.jfx.jfxcodeeditor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import static java.util.EnumSet.allOf;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.kordamp.ikonli.devicons.Devicons;
import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXCodeEditorAppSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXCodeEditorAppSceneController.class);
    private static final String EDITOR_HTML = "/html/Ace.html";
    private static final String HTML_TEMPLATES = "html/templates";
    private static final String HTML_EDITOR_TEMPLATE = "html/templates/editor.html";
    private static final String MODE_FORMAT_PATTERN = "ace/mode/{0}";
    private static final String THEME_FORMAT_PATTERN = "ace/theme/{0}";

    @FXML
    private TabPane iconsTabPane;
    @FXML
    private VBox mainWindow;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TreeView<String> bookMarkTreeView;
    @FXML
    private ToolBar editorToolBar;
    @FXML
    private Button editorSaveButton;
    @FXML
    private Button editorOpenButton;
    @FXML
    private Button editorUndoButton;
    @FXML
    private Button editorRedoButton;
    @FXML
    private Button editorRefreshButton;
    @FXML
    private Button editorCutButton;
    @FXML
    private Button editorCopyButton;
    @FXML
    private Button editorPasteButton;
    @FXML
    private Button editorFindButton;
    @FXML
    private Button editorReplaceButton;
    @FXML
    private Button editorOptionsButton;
    @FXML
    private Separator editorToolbarSep1;
    @FXML
    private Separator editorToolbarSep2;
    @FXML
    private Separator editorToolbarSep3;
    @FXML
    private Separator editorToolbarSep4;
    @FXML
    private Button randomModeButton;
    @FXML
    private Button randomThemeButton;
    @FXML
    private ComboBox<String> selectMode;
    @FXML
    private ComboBox<String> selectTheme;
    @FXML
    private WebView webEditorView;
    @FXML
    private HBox statusBar;
    @FXML
    private ProgressBar loadingBar;

    private WebEngine webEngine;
    private boolean displayDebugInfo;
    private Stage stage;
    private Scene scene;

    private static List<String> listOfThemes = Arrays.asList(
            "dracula", "eclipse", "github", "textmate", "xcode", "vibrant_ink", "pastels_n_dark",
            "monokai",
            "iplastic");

    private static List<String> listOfModes = Arrays.asList(
            "java", "javascript", "clj", "cljs", "css", "xml", "dot", "go", "swift");

    /**
     * Initializes the controller class.
     *
     * @throws java.lang.Throwable if any.
     */
    public void initialize() throws Throwable {
        try {
            log.debug("##### Entered initialize().");
            FontAwesomeSolid fawSolid = FontAwesomeSolid.FOLDER_OPEN;
            log.debug("##### fawSolid.getDescription(): {}", fawSolid.getDescription());

            displayDebugInfo = true;
            webEngine = webEditorView.getEngine();
            webEngine.getHistory().getEntries().addListener((
                    ListChangeListener.Change<? extends WebHistory.Entry> change) -> {
                log.debug("##### history: onChanged: change: {}", change);
                /*
                 * DEBUG INFO
                 */
                if (displayDebugInfo) {
                    debugDisplay(webEngine);
                }

                initializeBookMarksView();
                initializeSelectThemeComboBox();
                initializeSelectModeComboBox();
                setEditorToolbarSeparatorsPadding();
                try {
                    initializeEditor();
                } catch (URISyntaxException ex) {
                    log.error("##### caught URISyntaxException: {}", ex.getMessage());
                } catch (IOException ex) {
                    log.error("##### caught IOException: {}", ex.getMessage());
                }
            });

            /*
             * DEBUG INFO
             */
            if (displayDebugInfo) {
                debugDisplay(webEngine);
            }

            initializeIconsTabs();
            initializeBookMarksView();
            initializeSelectThemeComboBox();
            initializeSelectModeComboBox();
            setEditorToolbarSeparatorsPadding();
            initializeEditor();
        } catch (Exception ex) {
            log.error("##### Exception caught: {}", ex);
            throw ex;
        }
        log.debug("##### Its all good: leaving initialize().");
    }

    /**
     * <p>
     * Setter for the field <code>scene</code>.</p>
     *
     * @param scene a {@link javafx.scene.Scene} object.
     */
    public void setScene(Scene scene) {
        log.debug("##### Entered setScene(): scene: {}", scene);
        this.scene = scene;
    }

    private void initializeBookMarksView() {
        log.debug("##### Entered initializeBookMarksView()");
        TreeItem<String> root = new TreeItem<>("Root");
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>("BookMark 1"),
                new TreeItem<>("BookMark 2"),
                new TreeItem<>("BookMark 3"));
        bookMarkTreeView.setRoot(root);
    }

    private void initializeIconsTabs() throws Exception {
        log.debug("##### Entered initializeIconsTab()");
        iconsTabPane.getTabs().clear();
        iconsTabPane.getTabs().addAll(
                new IconsTab(FontAwesomeSolid.class, allOf(FontAwesomeSolid.class)),
                new IconsTab(FontAwesomeBrands.class, allOf(FontAwesomeBrands.class)),
                new IconsTab(Devicons.class, allOf(Devicons.class))
        );

    }

    private void initializeSelectThemeComboBox() {
        log.debug("##### Entered initializeSelectThemeComboBox()");
        selectTheme.getItems().addAll(listOfThemes);
        log.debug("##### selectTheme items: {}", selectTheme.getItems());
    }

    private void initializeSelectModeComboBox() {
        log.debug("##### Entered initializeSelectModeComboBox()");
        selectMode.getItems().addAll(listOfModes);
        log.debug("##### selectMode items: {}", selectMode.getItems());
    }

    @FXML
    private void randomlyChangeEditorModeButton(ActionEvent event) {
        log.debug("##### Entered randomlyChangeEditorModeButton(): event: {}", event);
        List<String> someModes = Collections.unmodifiableList(listOfModes);
        int bound = someModes.size();
        Random rnd = new Random();
        int selectedInt = rnd.nextInt(bound);
        String randomMode = someModes.get(selectedInt);
        log.debug("##### got selected number: {}, and mode: {}", selectedInt, randomMode);
        setMode(randomMode);

    }

    @FXML
    private void randomlyChangeEditorThemeButton(ActionEvent event) {
        log.debug("##### Entered randomlyChangeEditorThemeButton(): event: {}", event);
        List<String> someThemes = Collections.unmodifiableList(listOfThemes);
        int bound = someThemes.size();
        Random rnd = new Random();
        int selectedInt = rnd.nextInt(bound);
        String randomTheme = someThemes.get(selectedInt);
        log.debug("##### got selected number: {}, and theme: {}", selectedInt, randomTheme);
        setTheme(randomTheme);
    }


    /**
     * For some reason JavaFX balks if Insets are added to an FXML file. This just adds some padding
     * on the left and right of each separator in the editor's toolbar.
     */
    private void setEditorToolbarSeparatorsPadding() {
        editorToolbarSep1.setPadding(new Insets(0, 1, 0, 1));
        editorToolbarSep2.setPadding(new Insets(0, 1, 0, 1));
        editorToolbarSep3.setPadding(new Insets(0, 1, 0, 1));
        editorToolbarSep4.setPadding(new Insets(0, 1, 0, 1));
    }

    private void initializeEditor() throws URISyntaxException, IOException {
        log.debug("##### Entered initializeEditor()");
        loadingBar.setMinWidth(400);
        //loadingBar.progressProperty().set(0.0);

        log.debug("##### loadingBar progress: {}", loadingBar.getProgress());
        loadingBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
        webEngine.getLoadWorker().stateProperty().addListener((var ov, var oldState, var newState) -> {
            log.debug("##### oldState: {}", oldState);
            log.debug("##### newState: {}", newState);
            if (newState == Worker.State.SUCCEEDED) {
                log.debug("##### Page was successfully loaded.");
                debugDisplay(webEngine.getDocument());
                try {
                    loadingBar.progressProperty().unbind();
                    loadDocument();
                } catch (IOException | URISyntaxException exc) {
                    log.error("##### Caught exception loadingDocument: {}", exc);
                }
            } else if (newState == Worker.State.FAILED) {
                log.debug("##### Page loading has failed.");
            }
        });

        webEngine.getLoadWorker().workDoneProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() == 100) {
                log.debug("##### done property indicates 100% loaded.");
            } else {
                log.debug("##### progress: {} %", newVal);
            }
        });


        /*
         * Define editor toolbar button actions.
         */
        editorSaveButton.setOnAction(e -> {
            log.debug("##### save file event: {}", e);
            //showWebHistory();
            String code = (String) webEditorView.getEngine().executeScript("editor.getValue();");
            System.out.println(code);
        });

        editorOpenButton.setOnAction(e -> {
            log.debug("##### editorOpenButton.setOnAction: {}", e);
            executeScript("editor.session.setMode(\"ace/mode/java\");");
            setMode("java");
            log.debug("##### getMode(): {}", getMode());

            setTheme("eclipse");
            log.debug("##### getTheme(): {}", getTheme());
//                String fileContents = loadFile("/AceEditorExample.sava");
//                loadEditorContent(fileContents);
//                var doc = webEngine.getDocument();
//                Element element = doc.getElementById("editor");
//                log.debug("##### current content: {}", element.getTextContent());
//                element.setTextContent(joinedLines);
//                log.debug("##### get text content: {}", element.getTextContent());
            if (displayDebugInfo) {
                debugDisplay(webEngine);
            }
//            } catch (IOException | URISyntaxException ex) {
//                log.error("##### caught exception: {}", ex.getMessage());
//                throw new RuntimeException(ex);
//            }
        });

        editorRefreshButton.setOnAction(e -> {
            log.debug("##### reload file event: {}", e);
        });

        editorUndoButton.setOnAction(e -> {
            log.debug("##### undo changes event: {}", e);
        });

        editorRedoButton.setOnAction(e -> {
            log.debug("##### redo changes event: {}", e);
        });

        editorCutButton.setOnAction(e -> {
            log.debug("##### cut event: {}", e);
        });

        editorCopyButton.setOnAction(e -> {
            log.debug("##### copy event: {}", e);
        });

        editorPasteButton.setOnAction(e -> {
            log.debug("##### paste event: {}", e);
        });

        editorFindButton.setOnAction(e -> {
            log.debug("##### find event: {}", e);
        });

        editorReplaceButton.setOnAction(e -> {
            log.debug("##### replace event: {}", e);
        });

        editorOptionsButton.setOnAction(e -> {
            log.debug("##### options event: {}", e);
        });
    }

    /**
     * This calls into the webengine to exeute a JavaScipt function.
     *
     * @param script
     *
     * @return
     */
    private Object executeScript(String script) {
        log.debug("##### Entered executeScript(): {}", script);
        try {
            Object o = webEngine.executeScript(script);
            if (o != null) {
                try {
                    if (o instanceof JSObject) {
                        JSObject jso = (JSObject) o;
                        Object evalResult = jso.eval(script);
                        log.debug("##### evalResult: {}", evalResult);
                    } else {
                        log.debug("##### o.class: {}, o.toString: {}", o.getClass(), o.toString());
                    }
                } catch (JSException ex) {
                    log.error("!!!!! Caught exception: {}", ex);
                }
            } else {
                log.debug(
                        "##### excute script returned null which was probably converted from JavaScript's return of null.");
            }
            return o;
        } catch (Exception ex) {
            log.error("##### Caught exception: {}", ex.getMessage());
            throw ex;
        }
    }

    private Element getEditorElement() {
        var doc = webEngine.getDocument();
        Element element = doc.getElementById("editor");
        return element;
    }

    private void loadDocument() throws URISyntaxException, IOException {
        log.debug("##### Entered loadDocument()");

//        URL res = ClassLoader.getSystemResource(EDITOR_HTML);
//        log.debug("##### system resource url: {}", res);
//        res = JFXCodeEditorAppSceneController.class.getResource(EDITOR_HTML);
//        log.debug("##### resource url: {}", res);
        URL resUrl = getClass().getResource(EDITOR_HTML);
        log.debug("##### resUrl: {}", resUrl);

        var urlStr = resUrl.toExternalForm();
        log.debug("##### urlStr: {}", urlStr);

        webEngine.load(urlStr);

//        String content = Files.readString(Path.of(resUrl.toURI()));
//        log.debug("##### content: {}", content);
//        webEngine.loadContent(content);
        debugDisplay(webEngine.getDocument());

    }

    private String loadFile(String fileName) throws URISyntaxException, IOException {
        log.debug("##### Entered loadFile: fileName: {}", fileName);
        var fileUrl = getClass().getResource(fileName);
        log.debug("##### fileUrl: {}", fileUrl);

        List<String> lines = Files.readAllLines(Paths.get(fileUrl.toURI()));
        String joinedLines = lines.stream().collect(Collectors.joining("\n"));

        log.debug("##### joinedLines: {}", joinedLines);
        return joinedLines;
    }

    private void setMode(String modeName) {
        log.debug("##### Entered setMode(): modeName: {}", modeName);
        String script = String.format("editor.session.setMode(\"%s\");", MessageFormat.format(
                MODE_FORMAT_PATTERN, modeName));
        Object o = executeScript(script);
        if (o != null) {
            log.debug("##### o: {}", o);
        }
    }

    private String getMode() {
        log.debug("##### Entered getMode()");
        Object o = executeScript("editor.mode;");
        if (o != null && (o instanceof String)) {
            log.debug("##### o.class: {}, o: {}", o.getClass(), (String) o);
        }
        return (String) o;
    }

    private void setTheme(String themeName) {
        String script = MessageFormat.format("editor.setTheme(\"{0}\");", MessageFormat.format(
                THEME_FORMAT_PATTERN, themeName));
        executeScript(script);
    }

    private String getTheme() {
        Object o = executeScript("editor.getTheme();");
        if (o != null && (o instanceof String)) {
            return (String) o;
        }
        return "";
    }

    private void debugDisplay(WebEngine webEngine) {
        if (displayDebugInfo) {
            log.debug("##### confirm handler         : {}", webEngine.getConfirmHandler());
            log.debug("##### create popup handler    : {}", webEngine.getCreatePopupHandler());
            log.debug("##### editor session setmode  : {}", webEngine.getCreatePopupHandler());
            log.debug("##### document                : {}", webEngine.getDocument());
            log.debug("##### history                 : {}", webEngine.getHistory());
            log.debug("##### load worker             : {}", webEngine.getLoadWorker());
            log.debug("##### title                   : {}", webEngine.getTitle());
            log.debug("##### user agent              : {}", webEngine.getUserAgent());
            log.debug("##### user data directory     : {}", webEngine.getUserDataDirectory());
            log.debug("##### user stylesheet location: {}", webEngine.getUserStyleSheetLocation());
            Document doc = webEngine.getDocument();
            if (doc != null) {
                log.debug("##### number of child nodes   : {}", doc.getChildNodes().getLength());
            }
        }
    }

    private void debugDisplay(Document doc) {
        log.debug("##### Entered debugDisplay(Document): doc: {}", doc);
        if (doc != null) {
            DOMEcho domEcho = new DOMEcho(System.out);
            domEcho.echo(doc);
            Element editorEl = doc.getElementById("editor");
            domEcho.echo(editorEl);
        }
    }

    private void setOption(String option, Object value) {
        log.debug("##### Entered setOption(): option: {}, value: {}", option, value);
        String script = "editor.setOption({0}, {1});";
        script = MessageFormat.format(script, option, value);
        Object result = executeScript(script);
        log.debug("##### result: {}", result);

    }

    private void loadEditorContent(String joinedLines) {
        webEngine.loadContent(joinedLines);
    }

    private String chooseFile() {
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));

//        File selectedFile = fileChooser.showOpenDialog(stage);
//        if (selectedFile != null) {
//            stage.display(selectedFile);
//        }
        return "";

    }

    private void showWebHistory() {
        WebHistory hist = webEngine.getHistory();
        if (hist == null) {
            log.debug("##### WebHistory is null.");
        } else {
            hist.getEntries().forEach(entry -> {
                log.debug("##### hist entry: {}", entry);
            });
        }
    }
}
