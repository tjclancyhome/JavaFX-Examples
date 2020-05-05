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
package org.tjc.jfx.jfxcomponents.border;

import javafx.collections.ObservableSet;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class BordersManager {
    private static final Logger log = LoggerFactory.getLogger(BordersManager.class);

    private final Borders borders;

    private BordersManager() {
        borders = new Borders();
    }

    public static BordersManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addBorder(Region region) {
        borders.addBorder(region);
    }

    public void addBorder(Region region, Paint paint) {
        borders.addBorder(region, paint);
    }

    public boolean removeBorder(Region region) {
        return borders.removeBorder(region);
    }

    public <T extends Region> T add(T region) {
        return borders.add(region);
    }

    public <T extends Region> T add(T region, Paint paint) {
        return borders.add(region, paint);
    }

    public void showBorders() {
        borders.showBorders();
    }

    public void hideBorders() {
        borders.hideBorders();
    }

    public int size() {
        return borders.size();
    }

    public ObservableSet<BorderToggler> getBorderTogglersUnmodifiable() {
        return borders.getBorderTogglersUnmodifiable();
    }

    private static class InstanceHolder {
        private static final BordersManager INSTANCE = new BordersManager();

        static {
            log.debug("### initialized InstanceHolder singleton.");
        }
    }
}
