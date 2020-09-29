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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tjc.common.unittest.BaseUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

/**
 *
 * @author tjclancy
 */
public class RecentFilesTest extends BaseUnitTest {

    @BeforeEach
    public void setup() {
        this.forceShowOutput();

    }

    @AfterEach
    public void tearDown() {
        writeln();
        this.restoreShowOutput();
    }

    /**
     * Test of addRecentPath method, of class RecentFiles.
     */
    @Test
    public void testAddRecentFile() {
        writeBanner(methodName());
        RecentFiles recentFiles = new RecentFiles();
        recentFiles.addRecentPath(Path.of("d1.gv"));
        assertThat(recentFiles.size()).isEqualTo(1);
        Path recentFile = recentFiles.getPath("d1.gv");
        assertThat(recentFile.getFileName().toString()).isEqualTo("d1.gv");
    }

    /**
     * Test of getRecentFiles method, of class RecentFiles.
     */
    @Test
    public void testGetRecentFiles() {
        writeBanner(methodName());
        RecentFiles recentFiles = new RecentFiles();
        assertThat(recentFiles.getRecentPaths()).isEmpty();
    }

    /**
     * Test of toMenuItems method, of class RecentFiles.
     */
    @Test
    public void testGetRecentItemsAsMenuItems() {
        writeBanner(methodName());
        RecentFiles recentFiles = new RecentFiles();
        recentFiles.addRecentPath(Path.of("d1.gv"));
        recentFiles.addRecentPath(Path.of("digraph1.gv"));
        recentFiles.addRecentPath(Path.of("d2.gv"));
        recentFiles.addRecentPath(Path.of("digraph2.gv"));
        assertThat(recentFiles.size()).isEqualTo(4);

        var menuItems = recentFiles.toMenuItems();
        assertThat(menuItems).isNotEmpty();
        assertThat(menuItems.size()).isEqualTo(4);

        menuItems.forEach((var menuItem) -> {
            writeln("menuItem: {0}", menuItem.getText());
        });
    }

    /**
     * Test of size method, of class RecentFiles.
     */
    @Test
    public void testSize() {
        writeBanner(methodName());
        RecentFiles recentFiles = new RecentFiles();
        recentFiles.addRecentPath(Path.of("d1.gv"));
        recentFiles.addRecentPath(Path.of("d2.gv"));
        recentFiles.addRecentPath(Path.of("digraph1.gv"));
        recentFiles.addRecentPath(Path.of("digraph2.gv"));
        assertThat(recentFiles.size()).isEqualTo(4);
    }

    /**
     * Test of getRecentFilePaths method, of class RecentFiles.
     */
    @Test
    public void testGetRecentFilePaths() {
        writeBanner(methodName());
    }

    /**
     * Test of getPath method, of class RecentFiles.
     */
    @Test
    public void testGetPath() {
        writeBanner(methodName());
    }
}
