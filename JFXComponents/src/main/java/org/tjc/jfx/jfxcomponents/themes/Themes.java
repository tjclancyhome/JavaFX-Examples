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
package org.tjc.jfx.jfxcomponents.themes;

import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.tjc.jfx.jfxcomponents.utils.Bool.not;

/**
 *
 * @author tjclancy
 */
public class Themes {
    private static final Logger log = LoggerFactory.getLogger(Themes.class);

    public static final String DEFAULT_CSS_THEMES_ROOT = "/css/themes";
    public static final String DARK_MODE_THEME = DEFAULT_CSS_THEMES_ROOT + "/dark-theme.css";

    /**
     * Adds the darkmode theme css name to the Scene's list of stylesheets.
     *
     * @param on    Switches the darkmode theme on or of given the value of the 'on' parameter.
     * @param scene The Scene to add or remove the darkmode theme.
     *
     * @deprecated Use setTheme instead.
     */
    @Deprecated()
    public static void setDarkMode(boolean on, Scene scene) {
        setDarkMode(DARK_MODE_THEME, on, scene);
    }

    /**
     * Adds the darkmode theme css name to the Scene's list of stylesheets.
     *
     * @param pathToThemeCss The name of the css file path (e.g. '/css/themes/dark-theme.css')
     * @param on             Switches the darkmode theme on or of given the value of the 'on'
     *                       parameter.
     * @param scene          The Scene to add or remove the darkmode theme.
     *
     * @deprecated Use setTheme instead.
     */
    @Deprecated()
    public static void setDarkMode(String pathToThemeCss, boolean on, Scene scene) {
        setTheme(pathToThemeCss, on, scene);
    }

    /**
     * Adds a theme css name to the Scene's list of stylesheets.
     *
     * @param pathToThemeCss The name of the css file path (e.g. '/css/themes/dark-theme.css')
     * @param on             Switches the theme on or off given the value of the 'on' parameter.
     * @param scene          The Scene to add or remove the theme.
     */
    public static void setTheme(String pathToThemeCss, boolean on, Scene scene) {
        log.debug("### entered setTheme(): pathToThemeCss: {}, on: {}, scene: {}", pathToThemeCss,
            on, scene);

        if (log.isDebugEnabled()) {
            scene.getStylesheets()
                .forEach(styleSheet -> log.debug("### styleSheet: {}", styleSheet));
        }

        if (on) {
            if (not(scene.getStylesheets().contains(pathToThemeCss))) {
                log.debug("### dark mode on, setting dark mode theme style.");
                scene.getStylesheets().add(pathToThemeCss);
            }
        } else if (scene.getStylesheets().contains(pathToThemeCss)) {
            log.debug("### dark mode not on, removing dark mode theme style.");
            scene.getStylesheets().remove(pathToThemeCss);
        }
    }

    public static String formatThemeName(String themeCssRoot, String themeCss) {
        return String.format("%s/%s", themeCssRoot, themeCss);
    }

    public static String formatThemeNameWithDefaultRoot(String themeCss) {
        return String.format("%s/%s", DEFAULT_CSS_THEMES_ROOT, themeCss);
    }

}
