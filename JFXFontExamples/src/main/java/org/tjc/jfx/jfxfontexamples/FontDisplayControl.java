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

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class FontDisplayControl extends TextFlow {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FontDisplayControl.class);

    private static final double LABEL_FONT_SIZE = 20.0;

    private static enum TextType {
        LABEL,
        EXAMPLE;
    }

    private Font labelFont;

    /**
     * Single constructor takes a font that is used to render the sample text.
     *
     * @param font The font.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public FontDisplayControl(Font font) {
        log.trace("### entered FontDisplayControl(Font) constructor: font: {}", font);

        URL fxmlUrl = FontDisplayControl.class.getResource("/fxml/FontDisplayControl.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(fxmlUrl);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            log.trace("### fxmlLoader successful!");
        } catch (IOException ex) {
            log.error("### Caught exception loading fxml file: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
        log.trace("### exiting FontDisplayControl(Font) constructor.");

    }

    public Font getLabelFont() {
        return labelFont;
    }

    public void addEntry(Font font) {
        String labelText;
        if (font.getName().endsWith(font.getStyle())) {
            labelText = font.getName();
        } else {
            labelText = String.format("%s %s", font.getName(), font.getStyle());
        }
        labelText += "\n\n";
        getChildren().addAll(createTextObject(labelText, labelFont, TextType.LABEL),
                createTextObject("ABCDEFGHIJKLM\n", font, TextType.EXAMPLE),
                createTextObject("NOPQRSTUVWXYZ\n", font, TextType.EXAMPLE),
                createTextObject("nopqrstuvwxyz\n", font, TextType.EXAMPLE),
                createTextObject("0123456789\n\n", font, TextType.EXAMPLE));

    }

    /**
     * Sets the font size of each Text component in the TextFlow container. In this case there is
     * usually just one.
     *
     * @param newFontSize Size of the font.
     */
    public void setFontSize(double newFontSize) {
        log.trace("### entered setFontSized()");

        /*
         * Because TextFlow maintains a collection of Nodes, anything that extends Node (controls,
         * etc.) can be added to the TextFlow collection. But because I am only interested in Text
         * nodes, I filter them and then set each Text object's font to the newFontSize.
         *
         * -tjclancy and that's the story I'm sticking to.
         */
        getChildrenUnmodifiable()
                .filtered(node -> node instanceof Text)
                .forEach(node -> {
                    Text t = (Text) node;
                    if (!isLabel(t) && t.getFont().getSize() != newFontSize) {
                        Font f = t.getFont();
                        t.setFont(new Font(f.getName(), newFontSize));
                    }
                });

        log.trace("### exited setFontSized()");
    }

    private boolean isLabel(Text text) {
        boolean isLabel = text.getUserData() != null && text.getUserData().equals(TextType.LABEL);
        log.trace("### isLabel? {}", isLabel);
        return isLabel;
    }

    @FXML
    private void initialize() {
        textAlignmentProperty().set(TextAlignment.CENTER);
        labelFont = new Font("System Regular", LABEL_FONT_SIZE);
    }

    private Text createTextObject(final String str, final Font font, final TextType textType) {
        log.trace("### entered createTextObject(): str: {}, font: {}", str, font);
        Text text = new Text(str);
        text.setWrappingWidth(1);
        text.setFont(font);
        text.getStyleClass().add("font-display-text");
        text.setUserData(textType);

        log.debug("### Text::CssMetaData");
        text.getCssMetaData().forEach(cssmd -> {
            if (cssmd.getProperty().equals("-fx-font")) {
                log.debug("    ### cssmd: {}", cssmd);
                if (cssmd.getSubProperties() != null && !cssmd.getSubProperties().isEmpty()) {
                    cssmd.getSubProperties()
                            .forEach(subp -> log.debug("        ### subp cssmd: {}", subp));
                }
            }
        });

        return text;
    }
}
