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
package org.tjc.jfx.jfxcomponents.application;

import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public abstract class BaseJfxApplication extends Application {
    private static final Logger log = LoggerFactory.getLogger(BaseJfxExamplesApplication.class);

    private Scene scene;

    /**
     * TODO: Add comment.
     */
    public BaseJfxApplication() {
        super();
        log.debug("### entered BaseJfxExamplesApplication() constructor.");
        log.debug("### exited BaseJfxExamplesApplication() constructor.");
    }

    /**
     * TODO: Add comment.
     *
     * @param scene the scene object.
     */
    public BaseJfxApplication(Scene scene) {
        super();
        log.debug("### entered BaseJfxExamplesApplication(Scene) constructor.");
        this.scene = scene;
        log.debug("### scene: {}", this.scene);
        log.debug("### exited BaseJfxExamplesApplication(Scene)");
    }

    /**
     * TODO: Add comment.
     *
     * @return Returns the scene object.
     */
    protected Scene getScene() {
        return scene;
    }

    /**
     * TODO: Add comment.
     *
     * @param scene The scene object to set.
     *
     * @return Returns the same scene object... useful for a more fluent application.
     */
    protected Scene setScene(Scene scene) {
        this.scene = scene;
        return scene;
    }

    /**
     * Adds a style sheet to the stage object.
     *
     * @param stylesheet The URL of the stylesheet to add.
     *
     * @return Return true if the stylesheet could be sdded, false otherwise.
     */
    protected boolean addStyleSheet(URL stylesheet) {
        Objects.requireNonNull(stylesheet, () -> "The 'stylesheet' argument is null.");
        Objects.requireNonNull(scene, () -> "The 'scene' instance is null.");
        log.debug("### entered addStyleSheet(scene, stylesheet)");
        boolean added = false;
        if (stylesheet != null) {
            added = scene.getStylesheets().add(stylesheet.toExternalForm());
            log.debug("###     stylesheet: styles.css added: {}", added);
        }
        log.debug("### exited addStyleSheet(scene, stylesheet)");
        return added;
    }

    /**
     * This method creates a new FXMLLoader instance given a URL pointing to some fxml file. It sets
     * the location here so that it can call FXMLLoade#load().* @return
     *
     * @param fxmlURL The URL of the fxml file resource .
     *
     * @return A new FXMLLoader instance.
     */
    public FXMLLoader createFxmlLoader(URL fxmlURL) {
        log.debug("### entered createFxmlLoader(URL): fxmlURL: {}", fxmlURL);
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        loader.setLoadListener(new LoadListener() {
            @Override
            public void beginCopyElement() {
                log.debug("### entered beginCopyElement()");
            }

            @Override
            public void beginDefineElement() {
                log.debug("### entered beginDefineElement()");
            }

            @Override
            public void beginIncludeElement() {
                log.debug("### entered beginIncludeElement()");
            }

            @Override
            public void beginInstanceDeclarationElement(
                Class<?> type) {
                log.debug("### entered beginInstanceDeclarationElement(): type: {}", type);
            }

            @Override
            public void beginPropertyElement(String string,
                Class<?> type) {
                log.debug("### entered beginPropertyElement(): string: {}, type: {}", string, type);
            }

            @Override
            public void beginReferenceElement() {
                log.debug("### entered beginReferenceElement()");
            }

            @Override
            public void beginRootElement() {
                log.debug("### entered beginRootElement()");
            }

            @Override
            public void beginScriptElement() {
                log.debug("### entered beginScriptElement()");
            }

            @Override
            public void beginUnknownStaticPropertyElement(String string) {
                log.debug("### entered beginUnknownStaticPropertyElement(): string: {}", string);
            }

            @Override
            public void beginUnknownTypeElement(String string) {
                log.debug("### entered beginUnknownTypeElement(): string: {}", string);
            }

            @Override
            public void endElement(Object o) {
                log.debug("### entered endElement(): o: {}", o);
            }

            @Override
            public void readComment(String string) {
                log.debug("### entered readComment(): string: {}", string);
            }

            @Override
            public void readEventHandlerAttribute(String string, String string1) {
                log.debug("### entered readEventHandlerAttribute(): string: {}, string1: {}", string, string1);
            }

            @Override
            public void readImportProcessingInstruction(String string) {
                log.debug("### entered readImportProcessingInstruction(): string: {}", string);
            }

            @Override
            public void readInternalAttribute(String string, String string1) {
                log.debug("### entered readInternalAttribute(): string: {}, string1: {}", string, string1);
            }

            @Override
            public void readLanguageProcessingInstruction(String string) {
                log.debug("### entered readLanguageProcessingInstruction(): string: {}", string);
            }

            @Override
            public void readPropertyAttribute(String string,
                Class<?> type, String string1) {
                log.debug("### entered readPropertyAttribute(): string: {}, type: {}, string1: {}",
                    string, type, string1);
            }

            @Override
            public void readUnknownStaticPropertyAttribute(String string, String string1) {
                log.debug("### entered readUnknownStaticPropertyAttribute(): string: {}, string1: {}", string,
                    string1);
            }
        });

        debugPrintFxmlLoader(loader);

        log.debug("### exited createFxmlLoader(URL)");
        return loader;
    }

    @Override
    public String toString() {
        return "BaseJfxExamplesApplication{" + "scene=" + scene + '}';
    }

    /**
     * TODO: Add comment.
     *
     * @param loader An FXMLLoader from which various properties are printed.
     */
    private static void debugPrintFxmlLoader(FXMLLoader loader) {
        if (log.isDebugEnabled()) {
            try {
                log.debug("###");
                log.debug("### ===============================");
                log.debug("### Printing FXMLLoader properties:");
                log.debug("### ===============================");
                log.debug("charset          : {}", loader.getCharset());
                log.debug("location         : {}", loader.getLocation());
                if (!loader.getNamespace().isEmpty()) {
                    log.debug("namespace.size   : {}", loader.getNamespace().size());
                    log.debug("namespace        :");
                    loader.getNamespace().forEach((k, v) -> {
                        log.debug("###     {} -> {}", k, v);
                    });
                }
                log.debug("### root         : {}",
                    (loader.getRoot() != null ? loader.getRoot().getClass() : null));
                log.debug("### controller   : {}",
                    (loader.getController() != null ? loader.getController().getClass() : null));
                log.debug("### load listener: {}", loader.getLoadListener());
                log.debug("### ===============================");
                log.debug("###");
            } catch (Exception ex) {
                log.error("!!! exception caught: {}", ex.getMessage());
            }
        }

    }

}
