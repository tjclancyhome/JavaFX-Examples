/*
 * The MIT License
 *
 * Copyright 2019 tjc.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.tjc.jfx.jfxsandboxfxml;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.kodedu.terminalfx.config.TerminalConfig;
import java.util.Objects;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxsandboxfxml.utils.Strings;

/**
 * FXML Controller class using the Complex App template in SceneBuilder.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSandboxFxmlSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXSandboxFxmlSceneController.class);

    private UpdateHeapStatus heapStatus;
    private Timeline heapStatusTimeline;

    @FXML
    private MenuBar menuBar;
    @FXML
    private ScrollPane viewPane;
    @FXML
    private HBox statusBar;
    @FXML
    private VBox mainWindow;
    @FXML
    private Button addBashTabButton;
    @FXML
    private Button addJShellTabButton;
    @FXML
    private Button closeAllTabsButton;
    @FXML
    private Label heapStatusLabel;
    @FXML
    private TabPane viewTabPane;
    @FXML
    private ScrollPane propertiesPane;
    @FXML
    private Button newFileIconButton;
    @FXML
    private Button openFolderIconButton;
    @FXML
    private MenuItem garbageCollectMenuItem;
    @FXML
    private ScrollPane rightSideScrollPane;
    @FXML
    private Button addTextFlowTabButton;
    @FXML
    private Button addHtmlEditorTabButton;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        disposeTabs();
        configureHeapStatusLabel();
        initializeMenus();
        initializeToolbar();
        initializeViewTabPane();
        addBashTerminalTab();
        addJShellTab();
        addResizableGridTab();
        startHeapStatusUpdate();
    }

    private void initializeViewTabPane() {
        viewTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    private void addTerminalTab(String start, String tabTitle) {
        if (Strings.isNullOrEmpty(tabTitle)) {
            tabTitle = "Terminal";
        }
        TerminalConfig darkConfig = TerminalConfigs.darkConfig();
        darkConfig.setFontSize(12);
        darkConfig.setUnixTerminalStarter(start);
        TerminalBuilder terminalBuilder = new TerminalBuilder(darkConfig);
        TerminalTab terminal = terminalBuilder.newTerminal();

        terminal.setOnSelectionChanged(event -> {
            log.debug("### onSelectionChanged event: {}", event);
            if (!event.isConsumed()) {
                log.debug("### consuming event: {}", event);
                event.consume();
            }
        });

        terminal.setOnClosed(event -> {
            log.debug("### on closed event: terminal: {}, event: {}", terminal, event);
            terminal.destroy();
            log.debug("### destroyed terminal: {}", terminal);
            viewTabPane.getTabs().remove(terminal);
            log.debug("### removed from tab pane: {}", terminal);
            if (!event.isConsumed()) {
                log.debug("### consuming event: {}", event);
                event.consume();
            }
        });

        terminal.setOnCloseRequest(event -> {
            log.debug("### on close request(): terminal: {}, event: {}", terminal, event);
            terminal.closeTerminal();
            log.debug("### closed terminal.");
            if (!event.isConsumed()) {
                log.debug("### consuming event: {}", event);
                event.consume();
            }
        });

        log.debug("### adding termainal to tab pane: {}", terminal);
        viewTabPane.getTabs().add(terminal);
        terminal.setText(tabTitle + " #" + viewTabPane.getTabs().size());
        log.debug("### number of tabs: {}", viewTabPane.getTabs().size());
    }

    private void addTab(Node content, String tabTitle) {
        Objects.requireNonNull(content);
        if (Strings.isNullOrEmpty(tabTitle)) {
            tabTitle = "Tab";
        }
        Tab tab = new Tab();
        tab.setContent(content);

        tab.setOnSelectionChanged(event -> {
            log.debug("### onSelectionChanged(): tab: {}, event: {}", tab, event);
            if (!event.isConsumed()) {
                log.debug("### onSelectionChanged(): consuming event: {}", event);
                event.consume();
            }
        });

        tab.setOnCloseRequest(event -> {
            log.debug("### onCloseRequest(): tab: {}, event: {}", tab, event);
            if (!event.isConsumed()) {
                log.debug("### onCloseRequest(): consuming event: {}", event);
                event.consume();
            }
        });

        tab.setOnClosed(event -> {
            log.debug("### onClosed(): tab: tab: {}, event: {}", tab, event);
            viewTabPane.getTabs().remove(tab);
            log.debug("### onClosed(): removed from tab pane: tab: {}", tab);
            if (!event.isConsumed()) {
                log.debug("### onClosed(): consuming event: {}", event);
                event.consume();
            }
        });

        viewTabPane.getTabs().add(tab);
        log.debug("### number of tabs: {}", viewTabPane.getTabs().size());

        tab.setText(tabTitle + " #" + viewTabPane.getTabs().size());
    }

    private void addBashTerminalTab() {
        addTerminalTab("bash -i -l", "Bash");
    }

    private void addJShellTab() {
        addTerminalTab("jshell -v", "jshell");
    }

    private void addTextFlowTab() {
        Text text = new Text("Big italic white text");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Helvetica", FontPosture.ITALIC, 14));
        TextFlow textFlow = new TextFlow(text);
        textFlow.setPrefSize(1024, 1024);
        textFlow.setPadding(new Insets(5));
        textFlow.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        var scrollPane = new ScrollPane(textFlow);

        addTab(scrollPane, "TextFlow");

    }

    private void addHtmlEditorTab() {
        HTMLEditor htmlEditor = new HTMLEditor();
        addTab(htmlEditor, "HTMLEditor");
    }

    private void addResizableGridTab() {
        var grid = new ResizableGrid();
        grid.setPrefSize(1024, 1024);
        grid.addEventFilter(MouseEvent.MOUSE_CLICKED, (var event) -> {
            log.debug("!!!!! grid clicked(filter): event: {}", event);
        });

        var gridScrollPane = new ScrollPane(grid);
        gridScrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED, (var event) -> {
            log.debug("!!!!! gridScrollPane clicked(filter): event: {}", event);
        });

        grid.setOnMouseClicked((var event) -> {
            log.debug("### grid clicked(handler). event: {}", event);
            // if we consume the event then the gridScrollPane's onMouseClicked handler never gets the event.
            //event.consume();
        });

        gridScrollPane.setOnMouseClicked((var event) -> {
            log.debug("### gridScrollPane clicked(handler): event: {}", event);
        });

        addTab(gridScrollPane, "Graph");
    }

    private void initializeToolbar() {
        ImageView imageView = new ImageView("icons/icons8-new-file-100.png");
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        newFileIconButton.setText("");
        newFileIconButton.setGraphic(imageView);
        Tooltip newFileToolTip = new Tooltip("New file...");
        newFileToolTip.setShowDelay(Duration.millis(500));
        newFileIconButton.setTooltip(new Tooltip("New file..."));

        imageView = new ImageView("icons/icons8-opened-folder-100.png");
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        openFolderIconButton.setText("");
        openFolderIconButton.setGraphic(imageView);
        Tooltip openFolderToolTip = new Tooltip("Open folder...");
        openFolderToolTip.setShowDelay(Duration.millis(500));
        openFolderIconButton.setTooltip(new Tooltip("New file..."));

        addBashTabButton.setOnAction((var event) -> {
            Platform.runLater(() -> {
                addBashTerminalTab();
            });
        });

        addJShellTabButton.setOnAction((var event) -> {
            Platform.runLater(() -> {
                addJShellTab();
            });
        });

        addTextFlowTabButton.setOnAction((var event) -> {
            addTextFlowTab();
        });

        addHtmlEditorTabButton.setOnAction((var event) -> {
            addHtmlEditorTab();
        });

        closeAllTabsButton.setOnAction((var event) -> {
            if (!viewTabPane.getTabs().isEmpty()) {
                Platform.runLater(() -> disposeTabs());
            }
        });
    }

    /**
     * <p>
     * dispose.</p>
     */
    public void dispose() {
        log.debug("### dispose()!");
        heapStatusTimeline.stop();
        disposeTabs();
    }

    private void disposeTabs() {
        log.debug("### entered disposeTabs()... clearing {} tabs.", viewTabPane.getTabs().size());
        viewTabPane.getTabs().forEach(tab -> {
            log.debug("### nulling tab: {}", tab);
            tab = null;
            log.debug("### tab nulled: {}", tab);
        });
        clearTabs();
        log.debug("### number of tabs left: {}", viewTabPane.getTabs().size());
    }

    private void clearTabs() {
        viewTabPane.getTabs().clear();
    }

    private void startHeapStatusUpdate() {
        heapStatus = new UpdateHeapStatus(heapStatusLabel);

        // Initial update to show heap status before timeline starts.
        heapStatus.update();

        Platform.runLater(() -> {
            heapStatusTimeline = new Timeline(new KeyFrame(Duration.seconds(2), ae -> heapStatus
                    .update()));
            heapStatusTimeline.setCycleCount(Animation.INDEFINITE);
            heapStatusTimeline.play();
        });
    }

    private void configureHeapStatusLabel() {
        heapStatusLabel.setPadding(new Insets(5, 5, 5, 10));
    }

    private void initializeMenus() {
        garbageCollectMenuItem.setOnAction(event -> {
            Platform.runLater(() -> {
                Runtime.getRuntime().gc();
            });
        });
    }

}
