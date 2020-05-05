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

import java.nio.file.Path;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * RecentFiles class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class RecentFiles {
    private static final Logger log = LoggerFactory.getLogger(RecentFiles.class);

    private static final int MAX_RECENT_FILES = 10;
    private final Menu menu;

    /**
     * <p>
     * Constructor for RecentFiles.</p>
     *
     * @param menu a {@link javafx.scene.control.Menu} object.
     */
    public RecentFiles(Menu menu) {
        log.debug("### entered RecentFiles(Menu): menu: {}", menu);
        this.menu = menu;
    }

    public void addFile(Path file) {
        log.debug("### entered addFile(): menu: {}, file: {}", menu, file);
        menu.getItems().forEach(item -> log.debug("### item: {}", item));
        if (menu.getItems().size() < MAX_RECENT_FILES) {
            var menuItem = new MenuItem(file.getFileName().toString());
            menuItem.setUserData(file);
            menu.getItems().add(menuItem);
            log.debug("###     menu.items.size: {}", menu.getItems().size());
        }
    }

    public Menu getMenu() {
        return menu;
    }
}
