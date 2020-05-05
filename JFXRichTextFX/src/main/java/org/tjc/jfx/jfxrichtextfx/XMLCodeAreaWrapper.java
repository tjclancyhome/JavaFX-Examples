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
package org.tjc.jfx.jfxrichtextfx;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

/**
 * <p>
 * XMLCodeAreaWrapper class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class XMLCodeAreaWrapper implements CodeAreaWrapper {

    private final CodeArea codeArea;
    private static final Pattern XML_TAG = Pattern.compile(
            "(?<ELEMENT>(</?\\h*)(\\w+)([^<>]*)(\\h*/?>))"
            + "|(?<COMMENT><!--[^<>]+-->)");

    private static final Pattern ATTRIBUTES = Pattern.compile("(\\w+\\h*)(=)(\\h*\"[^\"]+\")");

    private static final int GROUP_OPEN_BRACKET = 2;
    private static final int GROUP_ELEMENT_NAME = 3;
    private static final int GROUP_ATTRIBUTES_SECTION = 4;
    private static final int GROUP_CLOSE_BRACKET = 5;
    private static final int GROUP_ATTRIBUTE_NAME = 1;
    private static final int GROUP_EQUAL_SYMBOL = 2;
    private static final int GROUP_ATTRIBUTE_VALUE = 3;

    private static final String sampleCode = String.join("\n", new String[] {
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
        "<!-- Sample XML -->",
        "< orders >",
        "	<Order number=\"1\" table=\"center\">",
        "		<items>",
        "			<Item>",
        "				<type>ESPRESSO</type>",
        "				<shots>2</shots>",
        "				<iced>false</iced>",
        "				<orderNumber>1</orderNumber>",
        "			</Item>",
        "			<Item>",
        "				<type>CAPPUCCINO</type>",
        "				<shots>1</shots>",
        "				<iced>false</iced>",
        "				<orderNumber>1</orderNumber>",
        "			</Item>",
        "			<Item>",
        "			<type>LATTE</type>",
        "				<shots>2</shots>",
        "				<iced>false</iced>",
        "				<orderNumber>1</orderNumber>",
        "			</Item>",
        "			<Item>",
        "				<type>MOCHA</type>",
        "				<shots>3</shots>",
        "				<iced>true</iced>",
        "				<orderNumber>1</orderNumber>",
        "			</Item>",
        "		</items>",
        "	</Order>",
        "</orders>"
    });

    private VirtualizedScrollPane virtualizedScrollPane;

    /**
     * <p>
     * Constructor for XMLCodeAreaWrapper.</p>
     */
    public XMLCodeAreaWrapper() {
        this.codeArea = new CodeArea();
        init();
    }

    /**
     * <p>
     * Constructor for XMLCodeAreaWrapper.</p>
     *
     * @param codeArea a {@link org.fxmisc.richtext.CodeArea} object.
     */
    public XMLCodeAreaWrapper(CodeArea codeArea) {
        this.codeArea = codeArea;
        init();
    }

    /**
     * <p>
     * Getter for the field <code>codeArea</code>.</p>
     *
     * @return a {@link org.fxmisc.richtext.CodeArea} object.
     */
    public CodeArea getCodeArea() {
        return codeArea;
    }

    /**
     * <p>
     * Getter for the field <code>virtualizedScrollPane</code>.</p>
     *
     * @return a {@link org.fxmisc.flowless.VirtualizedScrollPane} object.
     */
    public VirtualizedScrollPane getVirtualizedScrollPane() {
        return virtualizedScrollPane;
    }

    private void init() {
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText));
        });
        codeArea.replaceText(0, 0, sampleCode);
        virtualizedScrollPane = new VirtualizedScrollPane<>(codeArea);
        VBox.setVgrow(virtualizedScrollPane, Priority.ALWAYS);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {

        Matcher matcher = XML_TAG.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while (matcher.find()) {

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            if (matcher.group("COMMENT") != null) {
                spansBuilder.add(Collections.singleton("comment"), matcher.end() - matcher.start());
            } else {
                if (matcher.group("ELEMENT") != null) {
                    String attributesText = matcher.group(GROUP_ATTRIBUTES_SECTION);

                    spansBuilder.add(Collections.singleton("tagmark"),
                            matcher.end(GROUP_OPEN_BRACKET) - matcher.start(GROUP_OPEN_BRACKET));
                    spansBuilder.add(Collections.singleton("anytag"),
                            matcher.end(GROUP_ELEMENT_NAME) - matcher.end(GROUP_OPEN_BRACKET));

                    if (!attributesText.isEmpty()) {

                        lastKwEnd = 0;

                        Matcher amatcher = ATTRIBUTES.matcher(attributesText);
                        while (amatcher.find()) {
                            spansBuilder.add(Collections.emptyList(), amatcher.start() - lastKwEnd);
                            spansBuilder.add(Collections.singleton("attribute"), amatcher.end(
                                    GROUP_ATTRIBUTE_NAME) - amatcher.start(GROUP_ATTRIBUTE_NAME));
                            spansBuilder.add(Collections.singleton("tagmark"), amatcher
                                    .end(GROUP_EQUAL_SYMBOL) - amatcher.end(GROUP_ATTRIBUTE_NAME));
                            spansBuilder.add(Collections.singleton("avalue"), amatcher.end(
                                    GROUP_ATTRIBUTE_VALUE) - amatcher.end(GROUP_EQUAL_SYMBOL));
                            lastKwEnd = amatcher.end();
                        }
                        if (attributesText.length() > lastKwEnd) {
                            spansBuilder.add(Collections.emptyList(),
                                    attributesText.length() - lastKwEnd);
                        }
                    }

                    lastKwEnd = matcher.end(GROUP_ATTRIBUTES_SECTION);

                    spansBuilder.add(Collections.singleton("tagmark"),
                            matcher.end(GROUP_CLOSE_BRACKET) - lastKwEnd);
                }
            }
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSourceCodeType() {
        return "xml";
    }

}
