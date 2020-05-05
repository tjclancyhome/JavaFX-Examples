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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.tjc.jfx.aceeditorexample.util.AceEditorExampleUtils.toFontString;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class FontSelectorControl extends AnchorPane {
    private static final Logger log = LoggerFactory.getLogger(FontSelectorControl.class);

    private static final Font DEFAULT_FONT;
    private static final List<String> STYLES;
    private static final String FONT_SELECTOR_CONTRO_FXML = "/fxml/FontSelectorControl.fxml";

    static {
        DEFAULT_FONT = Font.font("Menlo", FontPosture.REGULAR, 12.0);
        STYLES = List.of("Plain", "Bold", "Italic", "Bold Italic");
    }

    @FXML
    private Button applyButton;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Button cancelButton;
    @FXML
    private ListView<String> fontFamilyList;
    @FXML
    private ListView<Double> fontSizeList;
    @FXML
    private ListView<String> fontStyleList;
    @FXML
    private GridPane gridPaneContainer;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button okButton;
    @FXML
    private TextField sampleTextField;
    @FXML
    private AnchorPane sampleTextPane;
    @FXML
    private Label selectedFontFamily;
    @FXML
    private Label selectedFontSize;
    @FXML
    private Label selectedFontStyle;

    private Font workingFont;
    private Font originalFont;
    private ObjectProperty<Font> selectedFontProperty;
    private ObjectProperty<Font> fontProperty;
    private BooleanProperty showMonospacedFontsProperty;

    @SuppressWarnings({"LeakingThisInConstructor", "CallToPrintStackTrace"})
    public FontSelectorControl(Font currentFont) {
        log.debug("### entered FontSelectorComponent()");
        Objects.requireNonNull(currentFont, () -> "The currentFont argument is null.");

        log.debug("### currentFont: {}", currentFont);

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource(FONT_SELECTOR_CONTRO_FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
//            workingFont = currentFont;
            workingFont = new Font(currentFont.getName(), currentFont.getSize());
            originalFont = new Font(currentFont.getName(), currentFont.getSize());
            log.debug("### workingFont: {}", workingFont);

            fontProperty = new SimpleObjectProperty<>(workingFont);
            log.debug("### fontProperty: {}", fontProperty);

            selectedFontProperty = new SimpleObjectProperty<>(workingFont);
            log.debug("### selectedFontProperty: {}", selectedFontProperty);

            showMonospacedFontsProperty = new SimpleBooleanProperty(false);
            log.debug("### showMonospacedFontsProperty: {}", showMonospacedFontsProperty);

            fxmlLoader.load();
        } catch (IOException ex) {
            log.error("### Caught exception loading {}.fxml: message: {}",
                    getClass().getSimpleName(), ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public ObjectProperty<Font> selectedFontProperty() {
        return selectedFontProperty;
    }

    public ObjectProperty<Font> fontProperty() {
        return fontProperty;
    }

    public BooleanProperty showMonospacedFontsProperty() {
        return showMonospacedFontsProperty;
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        log.debug("### entered initialize()");

        addDebugListeners();

//        debugPrintFontInfo();
//        debugShowFixedWidthFonts();

        gridPaneContainer.gridLinesVisibleProperty().set(false);

        //
        // Initialize fontFamilyList
        //

        fontFamilyList.setItems(observableArrayList(getMonoSpacedFontFamilyNames()));
        fontStyleList.setItems(observableArrayList(STYLES));
        fontSizeList.setItems(observableArrayList(rangeClosed(3, 48).asDoubleStream().boxed()
                .collect(toList())));

        fontFamilyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        updateFontFamilyListView(true);

        List<String> workingFontStyles = getFontStyles(workingFont.getFamily());
        fontStyleList.setItems(observableArrayList(workingFontStyles));

        fontFamilyList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable,
                        String oldFontFamily, String newFontFamily) -> {
            log.debug(
                    "### fontFamilyList.selectedItemProperty.listener: observable: {}, oldFontFamily: {}, newFontFamily: {}",
                    observable, oldFontFamily, newFontFamily);

            if (newFontFamily == null && oldFontFamily == null) {
                log.error("###    oldFontFamily and newFontFamily are both null!");
                throw new RuntimeException("oldFontFamily and newFontFamily are both null!");
            }

            if (newFontFamily == null && oldFontFamily != null) {
                log.warn(
                        "###    newFontFamily == null and oldFontFamily != null. Set newFontFamily to oldFontFamily.");
                newFontFamily = oldFontFamily;
            }

            selectedFontFamily.setText(newFontFamily);

            List<String> fontStyles = getFontStyles(newFontFamily);
            log.debug("###    fontStyles: {}", fontStyles);

            fontStyleList.setItems(observableArrayList(STYLES));
            updateWorkingFont();
            updateFontStyleListView(true);
            updateFontSizeListView(true);
            log.debug("###    workingFont: {}", workingFont);
        });

        //
        // Initialize fontStyleList
        //
        fontStyleList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        updateFontStyleListView(true);

        fontStyleList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldFontStyle, String newFontStyle) ->
        {
            log.debug(
                    "### fontStyleList.selectedItemProperty.listener: observable: {}, oldFontStyle: {}, newFontStyle: {}",
                    observable, oldFontStyle, newFontStyle);
            if (newFontStyle == null && oldFontStyle == null) {
                log.error("### oldFontStyle and newFontStyle are both null!");
                return;
            }
            if (newFontStyle == null && oldFontStyle != null) {
                log.warn(
                        "### newFontStyle == null but oldFontStyle != null. Set newFontStyle to oldFontStyle.");
                newFontStyle = oldFontStyle;
            }
            if (newFontStyle != null) {
                selectedFontStyle.setText(newFontStyle);
                updateWorkingFont();
                log.debug("###    workingFont: {}", toFontString(workingFont));
            } else {
                log.error("### newFontStyle is null!");
            }
//            updateFontFamilyListView();
//            updateFontSizeListView();

        });

        //
        // Initialize fontSizeList
        //

        fontSizeList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        fontSizeList.getSelectionModel().selectedItemProperty().addListener(
                (var observable, var oldFontSize, var newFontSize) -> {
            log.debug(
                    "### fontSizeList.selectedItemProperty.listener: observable: {}, oldFontSize: {}, newFontSize: {}",
                    observable, oldFontSize, newFontSize);
            selectedFontSize.setText(newFontSize.toString());
            updateWorkingFont();
            log.debug("###    workingFont: {}", toFontString(workingFont));
        });
        updateFontSizeListView(true);

        //
        // Set some sample text so that we can see how the selected font appears.
        //
        sampleTextField.setText("This is some sample text.");
        sampleTextField.fontProperty().bind(fontProperty);

        fontProperty.set(workingFont);
    }

    @FXML
    private void onCancel(ActionEvent event) {
        log.debug("### entered onCancel(): event: {}", event);
        fontProperty.set(originalFont);
        closeStage(event);
        event.consume();
    }

    @FXML
    private void onApply(ActionEvent event) {
        log.debug("### entered onApply(): event: {}", event);
        event.consume();
        fontProperty.set(workingFont);
    }

    @FXML
    private void onOkay(ActionEvent event) {
        log.debug("### entered onOkay(): event: {}", event);
        event.consume();
        fontProperty.set(workingFont);
        closeStage(event);
    }

//    @FXML
//    private void onSelecMonospacedFonts(ActionEvent event) {
//        if (monospacedFontsCheckBox.isSelected()) {
//            loadMonospacedFonts();
//            showMonospacedFontsProperty.set(true);
//        } else {
//            loadAllFonts();
//            showMonospacedFontsProperty.set(false);
//        }
//    }
    /**
     * Load only the monospaced fonts.
     */
    private void loadMonospacedFonts() {
        log.debug("### entered loadMonospacedFonts()");
        fontFamilyList.getItems().clear();
        fontFamilyList.setItems(observableArrayList(getMonoSpacedFontFamilyNames()));
        fontFamilyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        log.debug("###    workingFont before check: {}", workingFont);
        if (workingFont == null || !fontFamilyList.getItems().contains(workingFont.getFamily())) {
            workingFont = DEFAULT_FONT;
        }
        log.debug("###    workingFont after check: {}", workingFont);
        updateFontFamilyListView(true);

        List<String> workingFontStyles = getFontStyles(workingFont.getFamily());
        fontStyleList.setItems(observableArrayList(workingFontStyles));
        fontStyleList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        updateFontStyleListView(true);
    }

    private void updateFontFamilyListView(boolean scrollTo) {
        log.debug("### entered updateFontFamilyListView(): workingFont: {}", toFontString(workingFont));
        fontFamilyList.getSelectionModel().select(workingFont.getFamily());
        if (scrollTo) {
            fontFamilyList.scrollTo(workingFont.getFamily());
        }
    }

    private void updateFontStyleListView(boolean scrollTo) {
        log.debug("### entered updateFontStyleListView(): workingFont: {}",
                toFontString(workingFont));
        fontStyleList.getSelectionModel().select(workingFont.getStyle());
        if (scrollTo) {
            fontStyleList.scrollTo(workingFont.getStyle());
        }
    }

    private void updateFontSizeListView(boolean scrollTo) {
        log.debug("### entered updateFontSizeListView(): workingFont: {}", toFontString(workingFont));
        fontSizeList.getSelectionModel().select(workingFont.getSize());
        if (scrollTo) {
            fontSizeList.scrollTo(workingFont.getSize());
        }
    }

//    private void loadAllFonts() {
//        log.debug("### entered loadAllFonts()");
//        fontFamilyList.getItems().clear();
//        fontFamilyList.setItems(observableArrayList(Font.getFamilies()));
//        fontFamilyList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        log.debug("###    workingFont before check: {}", workingFont);
//        if (workingFont == null) {
//            workingFont = DEFAULT_FONT;
//        }
//        log.debug("###    workingFont after check: {}", workingFont);
//        updateFontFamilyListView();
//
////        fontFamilyList.getSelectionModel().select(workingFont.getFamily());
////        fontFamilyList.scrollTo(workingFont.getFamily());
//
//        List<String> workingFontStyles = getFontStyles(workingFont.getFamily());
//        fontStyleList.setItems(observableArrayList(workingFontStyles));
//        fontStyleList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        updateFontStyleListView();
//
////        fontStyleList.getSelectionModel().select(workingFont.getStyle());
////        fontStyleList.scrollTo(workingFont.getStyle());
//    }
    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void updateWorkingFont() {
        log.debug("### entered updateWorkingFont()");
        log.debug("###    workingFont before updating: {}", toFontString(workingFont));

        String fontName = workingFont.getName();
        log.debug("###    workingFont.fontName: {}", fontName);

        String fontFamily = fontFamilyList.getSelectionModel().getSelectedItem();
        log.debug("###    selected fontFamily: {}", fontFamily);
        if (fontFamily == null) {
            fontFamily = DEFAULT_FONT.getFamily();//"Monaco";
        }

        String fontStyle = fontStyleList.getSelectionModel().getSelectedItem();
        log.debug("###    selected fontStyle: {}", fontStyle);
        if (fontStyle == null) {
            fontStyle = DEFAULT_FONT.getStyle();
        }

        Double fontSize = fontSizeList.getSelectionModel().getSelectedItem();
        log.debug("###    selected fontSize: {}", fontSize);
        if (fontSize == null) {
            fontSize = DEFAULT_FONT.getSize();
        }

        log.debug("###    familyName: {}, fontName: {}, fontStyle: {}, fontSize: {}",
                fontFamily,
                fontName,
                fontStyle,
                fontSize);

        workingFont = makeFont(fontFamily, fontStyle, fontSize);

//        updateFontFamilyListView(true);
//        updateFontStyleListView(true);
//        updateFontSizeListView(true);

        log.debug("###    workingFont after updating: {}", toFontString(workingFont));

        fontProperty.set(workingFont);
    }

    private Font makeFont(String familyName, String fontStyle, double fontSize) {
        log.debug("### entered makeFont(): familyName: {}, fontStyle: {}, fontSize: {}",
                familyName, fontStyle, fontSize);

        Font font;
        FontWeight fontWeight = FontWeight.findByName(fontStyle);
        FontPosture fontPosture = FontPosture.findByName(fontStyle);

        log.debug("###    fontWeight: {}, fontPosture: {}", fontWeight, fontPosture);

        if (fontWeight != null && fontWeight.equals(FontWeight.NORMAL)) {
            // A Normal font weight seems to cause JavaFX to replace the font name with 'System'.
            // Hoping this helps...
            fontWeight = null;
        }

        log.debug("###    fontWeight: {}, fontPosture: {}", fontWeight, fontPosture);

        if (fontWeight != null && fontPosture != null) {
            font = Font.font(familyName, fontWeight, fontPosture, fontSize);
            log.debug("###    fontWeight != null and fontPosture != null: font: {}", font);
        } else if (fontWeight == null && fontPosture != null) {
            font = Font.font(familyName, fontPosture, fontSize);
            log.debug("###    fontWeight == null and fontPosture != null: font: {}", font);
        } else if (fontWeight != null && fontPosture == null) {
            font = Font.font(familyName, fontWeight, fontSize);
            log.debug("###    fontWeight != null and fontPosture == null: font: {}", font);
        } else {
            font = DEFAULT_FONT;
            log.debug("###    fontWeight == null and fontPosture == null: font: {}", font);
        }
        log.debug("###    font: {}", font);
        return font;
    }

    private static List<String> getFontNames(String fontFamilyName) {
        Objects.requireNonNull(fontFamilyName, () -> "The 'fontFamilyName' argument is null.");

        log.debug("### entered getFontNames(String): fontFamilyName: {}", fontFamilyName);

        List<String> fontNames = new ArrayList<>(Font.getFontNames(fontFamilyName));
        log.debug("###    fontNames: {}", fontNames);

        return fontNames;
    }

    private static List<String> getFontStyles(String fontFamilyName) {
        Objects.requireNonNull(fontFamilyName, () -> "The 'fontFamilyName' argument is null.");

        log.debug("### entered getFontStyles(): fontFamilyName: {}", fontFamilyName);

        List<String> fontStyles = new ArrayList<>();
        Set<String> fontStyleSet = new TreeSet<>();
        List<String> fontNames = getFontNames(fontFamilyName);
        log.debug("###    fontNames: {}", fontNames);
        fontNames.forEach(name -> {
            int index = name.indexOf(fontFamilyName);
            if (index != -1) {
                String fontStyleString = name.substring(index + fontFamilyName.length()).trim();
                log.debug("###    fontStyleString: {}", fontStyleString);
                if (fontStyleString.isEmpty() || fontStyleString.equalsIgnoreCase("Normal")) {
                    fontStyleString = "Regular";
                }

                fontStyleSet.add(fontStyleString);
            }
        });
        log.debug("###    fontStyleSet: {}", fontStyleSet);
        return new ArrayList<>(fontStyleSet);
    }

    public static List<String> getMonoSpacedFontFamilyNames() {
        // taken from https://yo-dave.com/2015/07/27/finding-mono-spaced-fonts-in-javafx/
        //

        final Text thinTxt = new Text("1 l"); // note the space
        final Text thikTxt = new Text("MWX");

        List<String> fontFamilyList = Font.getFamilies();
        List<String> monoFamilyList = new ArrayList<>();

        Font font;

        for (String fontFamilyName : fontFamilyList) {
            font = Font.font(fontFamilyName, FontWeight.NORMAL, FontPosture.REGULAR, 14.0d);
            thinTxt.setFont(font);
            thikTxt.setFont(font);
            if (thinTxt.getLayoutBounds().getWidth() == thikTxt.getLayoutBounds().getWidth()) {
                monoFamilyList.add(fontFamilyName);
            }
        }
        return monoFamilyList;
    }

    private static void debugPrintFontInfo() {
        if (log.isTraceEnabled()) {
            log.debug("### -------------------------------------------------");
            log.debug("### All Font Info");
            log.debug("### -------------------------------------------------");
            log.debug("###    default font: {}", Font.getDefault());
            log.debug("");
            Font.getFamilies().forEach(fam -> log.debug("###    family: {}", fam));
            log.debug("");
            Font.getFontNames().forEach(f -> log.debug("###    font: {}", f));
            log.debug("");
            log.debug("### Monospaced Fonts");
            log.debug("### -------------------------------------------------");

            debugShowFixedWidthFonts();

            log.debug("### -------------------------------------------------");
        }
    }

    private static void debugShowFixedWidthFonts() {
        if (log.isDebugEnabled()) {
            log.debug("### -------------------------------------------------");
            log.debug("### Fixed Width Font Info");
            log.debug("### -------------------------------------------------");
            getMonoSpacedFontFamilyNames()
                    .forEach(mono -> log.debug("###    mono font: {}", mono));
            log.debug("### -------------------------------------------------");
        }
    }

    private void addDebugListeners() {
        if (log.isDebugEnabled()) {
            log.debug("### entered addDebugListeners()");
            fontProperty.addListener(
                    (ObservableValue<? extends Font> obsv, Font oldVal, Font newVal) -> {
                log
                        .debug(
                                "\n\n### debugListener.fontProperty.listener: oldVal: {}, newVal(): {}\n",
                                oldVal,
                                newVal);
            });

            showMonospacedFontsProperty.addListener((ObservableValue<? extends Boolean> obsv,
                    Boolean oldVal,
                    Boolean newVal) -> {
                log.debug(
                        "\n\n### debugListener.showMonospacedFontsProperty.listener: oldVal: {}, newVal(): {}\n",
                        oldVal,
                        newVal);
            });

        }
    }

}
