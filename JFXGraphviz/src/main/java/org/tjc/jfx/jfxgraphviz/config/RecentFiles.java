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
package org.tjc.jfx.jfxgraphviz.config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the File -> Open Recent menu items.
 *
 * @author tjclancy
 */
public class RecentFiles {
    private static final Logger log = LoggerFactory.getLogger(RecentFiles.class);

    private static final int UPPER_LIMIT = 25;
    private static final int DEFAULT_MAX_RECENT_FILES = 15;
    private SimpleLru recentPaths;
    private final int maxRecentFiles;

    public RecentFiles() {
        this(DEFAULT_MAX_RECENT_FILES);
    }

    public RecentFiles(int maxRecentFiles) {
        if (maxRecentFiles > UPPER_LIMIT) {
            this.maxRecentFiles = UPPER_LIMIT;
        } else {
            this.maxRecentFiles = maxRecentFiles;
        }
        this.recentPaths = new SimpleLru(this.maxRecentFiles);
    }

    public void addRecentPath(Path recentFilePath) {
        recentPaths.put(recentFilePath.getFileName().toString(), recentFilePath);
    }

    public Map<String, Path> getRecentPaths() {
        return Collections.unmodifiableMap(recentPaths);
    }

    public List<MenuItem> toMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        recentPaths.forEach((k, v) -> {
            menuItems.add(new MenuItem(k));
        });
        return menuItems;
    }

    public Path getPath(String fileName) {
        if (recentPaths.containsKey(fileName)) {

        }
        return recentPaths.get(fileName);
    }

    public int size() {
        return recentPaths.size();
    }

    private class SimpleLru extends LinkedHashMap<String, Path> {
        public SimpleLru(int initialCapacity) {
            super(initialCapacity);
        }

        public SimpleLru() {
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Path> eldest) {
            return this.size() == maxRecentFiles;
        }
    }
}
