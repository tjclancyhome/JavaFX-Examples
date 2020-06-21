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
package org.tjc.jfx.jfxcomponents.border;

import java.util.Objects;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mainly for debugging purposes as well as demonstration, this class manages a list of Regions
 * allowing one to toggle the visibility of a border for each region being managed. The default
 * border is RED with a solid stroke and with a default width of 1 pixel.
 *
 * @author tjclancy
 */
public class Borders {
    private static final Logger log = LoggerFactory.getLogger(Borders.class);
    private static final Paint DEFAULT_PAINT = Color.RED;

    private final ObservableSet<BorderToggler> borderTogglers;

    /**
     * Default constructor.
     */
    public Borders() {
        borderTogglers = FXCollections.observableSet();
    }

    /**
     * Adds a BorderToggler for the given region and the default color of Color.RED.
     *
     * @param region The region for adding a border.
     */
    public void addBorder(Region region) {
        log.debug("### entered addBorder(region): region: {}", region);

        Objects.requireNonNull(region, () -> "The 'region' argument is null.");
        BorderToggler bt = new BorderToggler(createBorder(DEFAULT_PAINT), region);
        borderTogglers.add(bt);
    }

    /**
     * Adds a BorderToggler for the given region and the speccolor of Color.RED.
     *
     * @param region The region for adding a border.
     * @param paint  The Paint/Color to make the border.
     */
    public void addBorder(Region region, Paint paint) {
        log.debug("### entered addBorder(region, paint): region: {}, paint: {}", region, paint);
        if (region == null) {
            log.warn("The region is null. Just return.");
            return;
        }
        if (paint == null) {
            log.warn("The paint argument is null");
            return;
        }
//        Objects.requireNonNull(region, () -> "The 'region' argument is null.");
//        Objects.requireNonNull(paint, () -> "The 'paint' argument is null.");
        BorderToggler bt = new BorderToggler(createBorder(paint), region);
        borderTogglers.add(bt);
    }

    /**
     * Remove the border from the region.
     *
     * @param region The region to remove a border from.
     *
     * @return Returns true if the region was deleted, false otherwise.
     */
    public boolean removeBorder(Region region) {
        log.debug("### entered removeBorder(region): I do nothing yet. region: {}", region);
        if (region == null) {
            return false;
        }

        Optional<BorderToggler> toggler = borderTogglers
            .stream()
            .filter(bt -> bt.getRegion().equals(region))
            .findFirst();
        log.debug("### optional toggler: {}", toggler);
        if (toggler.isPresent()) {
            BorderToggler bt = toggler.get();
            borderTogglers.remove(bt);
            return true;
        }
        return false;
    }

    /**
     * Adds the region to the toggler and then returns the region. This was king of a way to do
     * something like this:
     * <pre>
     *      var TreeView = border.add(new TreeView());
     *
     *      // or
     *
     *      var TreeVew = border.add(new TreeView(), Color.GREEN)
     * </pre>
     *
     * @param <T>    The regtion type.
     * @param region The region object
     *
     * @return The region to which a border was added.
     */
    public <T extends Region> T add(T region) {
        if (region == null) {
            return null;
        }
        BorderToggler bt = new BorderToggler(createBorder(DEFAULT_PAINT), region);
        borderTogglers.add(bt);
        return region;
    }

    /**
     * Adds the region to the toggler and then returns the region.
     *
     * <pre>
     * <code>
     *      var TreeView = border.add(new TreeView());
     *
     *      var TreeVew = border.add(new TreeView(), Color.GREEN)
     * </code>
     * </pre>
     *
     * @param <T>    The regtion type.
     * @param region The region object
     * @param paint  The color used to paint a border around the region.
     *
     * @return The region to which a border was added.
     */
    public <T extends Region> T add(T region, Paint paint) {
        if (region == null) {
            return null;
        }
        BorderToggler bt = new BorderToggler(createBorder(paint), region);
        borderTogglers.add(bt);
        return region;
    }

    /**
     * Toggles all borders, showing all bordered regions.
     */
    public void showBorders() {
        borderTogglers.forEach(BorderToggler::toggleOn);
    }

    /**
     * Toggles all borders, hiding all bordered regions.
     */
    public void hideBorders() {
        borderTogglers.forEach(BorderToggler::toggleOff);
    }

    /**
     * The number of border togglers.
     *
     * @return The number of border togglers.
     */
    public int size() {
        return borderTogglers.size();
    }

    /**
     * Returns an unmodifiable, observable set of border togglers.
     *
     * @return An unmodifiable, observable set of border togglers.
     */
    public ObservableSet<BorderToggler> getBorderTogglersUnmodifiable() {
        return FXCollections.unmodifiableObservableSet(borderTogglers);
    }

    private static Border createBorder(Paint paint) {
        return new Border(new BorderStroke(
            paint,
            BorderStrokeStyle.DASHED,
            new CornerRadii(0.5),
            BorderWidths.FULL));
    }

}
