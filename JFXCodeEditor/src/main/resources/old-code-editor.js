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

var editor = ace.edit("editor");

editor.setOptions({
    enableBasicAutocompletion: true,
    autoScrollEditorIntoView: true,
    copyWithEmptySelection: false,
    behavioursEnabled: false,
    cursorStyle: "ace",
    navigateWithinSoftTabs: true,
    showInvisibles: false,
    showPrintMargin: true,
    hScrollBarAlwaysVisible: true,
    vScrollBarAlwaysVisible: true,
    highlightGutterLine: true,
    highlightSelectedWord: true,
    highlightActiveLine: true,
    tabSize: 4,
    useSoftTabs: true,
    fadeFoldWidgets: false,
    showGutter: true,
    fontSize: 12,
    printMarginColumn: 110
});

editor.setTheme("ace/theme/dracula");
editor.setMode("ace/mode/java");
editor.session.newLineMode = "unix";
