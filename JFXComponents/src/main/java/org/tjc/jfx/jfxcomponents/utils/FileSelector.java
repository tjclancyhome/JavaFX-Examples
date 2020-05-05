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
package org.tjc.jfx.jfxcomponents.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author tjclancy
 */
public class FileSelector {
    private final Scene scene;
    private final String title;
    private final List<ExtensionFilter> extensionFilters;

    public FileSelector(Scene scene, String title) {
        this.scene = Objects.requireNonNull(scene, () -> "The 'scene' argument is null.");
        this.title = Objects.requireNonNull(title, () -> "The 'title' argument is null.");
        extensionFilters = new ArrayList<>();
    }

    public FileSelector(Scene scene, String title, ExtensionFilter... extensionFilters) {
        this.scene = Objects.requireNonNull(scene, () -> "The 'scene' argument is null.");
        this.title = Objects.requireNonNull(title, () -> "The 'title' argument is null.");
        Objects.requireNonNull(extensionFilters, () -> "The 'extensionFilters' is null.");
        this.extensionFilters = new ArrayList<>(asList(extensionFilters));
    }

    public static FileSelector newInstance(Scene scene, String title) {
        return new FileSelector(scene, title);
    }

    public static FileSelector newInstance(Scene scene, String title, ExtensionFilter... extensionFilters) {
        return new FileSelector(scene, title, extensionFilters);
    }

    public Path selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        if (!extensionFilters.isEmpty()) {
            fileChooser.getExtensionFilters().addAll(extensionFilters);
        }
        File selectedFile = fileChooser.showOpenDialog(scene.getWindow());
        if (selectedFile != null) {
            return selectedFile.toPath();
        } else {
            return null;
        }
    }
}
