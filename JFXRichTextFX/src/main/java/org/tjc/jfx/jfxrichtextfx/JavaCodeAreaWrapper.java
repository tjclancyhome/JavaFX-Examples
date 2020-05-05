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

import java.time.Duration;
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
import org.reactfx.Subscription;

/**
 * <p>
 * JavaCodeAreaWrapper class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JavaCodeAreaWrapper implements CodeAreaWrapper {

    private static final String[] KEYWORDS = new String[] {
        "abstract", "assert", "boolean", "break", "byte",
        "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else",
        "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import",
        "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public",
        "return", "short", "static", "strictfp", "super",
        "switch", "synchronized", "this", "throw", "throws",
        "transient", "try", "void", "volatile", "while"
    };

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<PAREN>" + PAREN_PATTERN + ")"
            + "|(?<BRACE>" + BRACE_PATTERN + ")"
            + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
            + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    private static final String sampleCode = String.join("\n", new String[] {
        "package com.example;",
        "",
        "import java.util.*;",
        "",
        "public class Foo extends Bar implements Baz {",
        "",
        "    /*",
        "     * multi-line comment",
        "     */",
        "    public static void main(String[] args) {",
        "        // single-line comment",
        "        for(String arg: args) {",
        "            if(arg.length() != 0)",
        "                System.out.println(arg);",
        "            else",
        "                System.err.println(\"Warning: empty string as argument\");",
        "        }",
        "    }",
        "",
        "}"
    });

    private CodeArea codeArea;
    private VirtualizedScrollPane virtualizedScrollPane;

    /**
     * <p>
     * Constructor for JavaCodeAreaWrapper.</p>
     */
    public JavaCodeAreaWrapper() {
        codeArea = new CodeArea();
        init();
    }

    /**
     * <p>
     * Constructor for JavaCodeAreaWrapper.</p>
     *
     * @param codeArea a {@link org.fxmisc.richtext.CodeArea} object.
     */
    public JavaCodeAreaWrapper(CodeArea codeArea) {
        this.codeArea = codeArea;
        init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VirtualizedScrollPane getVirtualizedScrollPane() {
        return virtualizedScrollPane;
    }

    private void init() {
//        codeArea = new CodeArea();

        // add line numbers to the left of area
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        // recompute the syntax highlighting 500 ms after user stops editing area
        Subscription cleanupWhenNoLongerNeedIt = codeArea
                // plain changes = ignore style changes that are emitted when syntax highlighting is reapplied
                // multi plain changes = save computation by not rerunning the code multiple times
                //   when making multiple changes (e.g. renaming a method at multiple parts in file)
                .multiPlainChanges()
                // do not emit an event until 500 ms have passed since the last emission of previous stream
                .successionEnds(Duration.ofMillis(500))
                // run the following code block when previous stream emits an event
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea
                .getText())));

        // when no longer need syntax highlighting and wish to clean up memory leaks
        // run: `cleanupWhenNoLongerNeedIt.unsubscribe();`
        codeArea.replaceText(0, 0, sampleCode);
        virtualizedScrollPane = new VirtualizedScrollPane<>(codeArea);
        VBox.setVgrow(virtualizedScrollPane, Priority.ALWAYS);

    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass
                    = matcher.group("KEYWORD") != null ? "keyword"
                    : matcher.group("PAREN") != null ? "paren"
                    : matcher.group("BRACE") != null ? "brace"
                    : matcher.group("BRACKET") != null ? "bracket"
                    : matcher.group("SEMICOLON") != null ? "semicolon"
                    : matcher.group("STRING") != null ? "string"
                    : matcher.group("COMMENT") != null ? "comment"
                    : null;
            /*
             * never happens
             */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
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
        return "java";
    }

}
