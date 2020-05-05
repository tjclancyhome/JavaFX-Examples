/*
 * The MIT License
 *
 * Copyright 2020 tjclancy.
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

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handlers for handling JavaScript callbacks.
 *
 * @author tjclancy
 */
public class AceEventHandlers {
    private static final Logger log = LoggerFactory.getLogger(AceEventHandlers.class);

    private final VBox aceEditorMainContainer;

    public static final EventType<? extends Event> onBlurEvent = new EventType<>("onBlur");
    public static final EventType<? extends Event> onChangeCursorEvent = new EventType<>("onChangeCursor");

    public AceEventHandlers(VBox aceEditorMainContainer) {
        log.trace("### entered AceEventHandlers()");
        this.aceEditorMainContainer = aceEditorMainContainer;
        log.trace("### exited AceEventHandlers()");
    }

    public void onChangeCursor() {
        log.trace("### entered onChangeCursor()");
        log.trace("###    fire onChangeCursorEvent...");
        aceEditorMainContainer.fireEvent(new Event(AceEventHandlers.onChangeCursorEvent));
        log.trace("### exited onChangeCursor()");
    }

    public void onBlur() {
        log.trace("### entered onBlur()");
        log.trace("###    fire some event or update some bindable observable object.");
        aceEditorMainContainer.fireEvent(new Event(AceEventHandlers.onBlurEvent));
        log.trace("### exited onBlur()");
    }

}
