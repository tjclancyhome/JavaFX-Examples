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
package org.tjc.jfx.aceeditorexample.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.aceeditorexample.model.Editor;

/**
 * General utilities, but mostly for scanning the ace.src-noconflict to extract mode and theme names
 * from their respected filenames. This code contains three methods (see javadocs) that I borrowed
 * from the AceFX source code,
 * <p>
 * Copyright 2015 Sudipto Chandra.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public final class AceEditorExampleUtils {
    private static final Logger log = LoggerFactory.getLogger(AceEditorExampleUtils.class);

    private static final String SUFFIX = ".js";
    private static final String MODE_PREFIX = "mode-";
    private static final String THEME_PREFIX = "theme-";

    private AceEditorExampleUtils() {
    }

    /**
     * Returns sorted list of mode names.
     *
     * @param pathToAceSource Path to ace source directory.
     *
     * @return A sorted list of modes.
     *
     * @throws java.io.IOException if any.
     */
    public static List<String> listAllModes(Path pathToAceSource) throws IOException {
        return extractSubstringsFromFileNames(pathToAceSource, MODE_PREFIX, SUFFIX);
    }

    /**
     * Returns sorted list of theme names.
     *
     * @param pathToAceSource Path to ace source directory.
     *
     * @return A sorted list of theme names.
     *
     * @throws java.io.IOException if any.
     */
    public static List<String> listAllThemes(Path pathToAceSource) throws IOException {
        return extractSubstringsFromFileNames(pathToAceSource, THEME_PREFIX, SUFFIX);
    }

    /**
     * From AceFX Copyright 2015 Sudipto Chandra.
     * <p>
     * Creates a new JSObject from string representation of a valid JavaScript
     * object.
     *
     * @param parent Parent JSObject to use to convert the string.
     * @param object String representation of a valid JavaScript object.
     *
     * @return JSObject created from the given object string.
     */
    public static JSObject getObject(JSObject parent, Object object) {
        return (JSObject) parent.eval(String.format("(function() { return %s; })()", object));
    }

    /**
     * From AceFX Copyright 2015 Sudipto Chandra.
     * <p>
     * Creates a new JSObject from string representation of a valid JavaScript
     * object.
     *
     * @param parent Parent JSObject to use to convert the string.
     * @param object String representation of a valid JavaScript object.
     *
     * @return JSObject created from the given object string.
     */
    public static JSObject getObjectByList(JSObject parent, Collection<?> object) {
        var it = object.iterator();
        var sb = new StringBuilder();
        while (it.hasNext()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(it.next());
        }
        return (JSObject) parent.eval(String
                .format("(function() { return [%s]; })()", sb.toString()));
    }

    /**
     * From AceFX Copyright 2015 Sudipto Chandra.
     * <p>
     * Gets a list of all properties of a JavaScript object.
     *
     * @param data The object to get properties.
     *
     * @return List of all properties of the object.
     */
    public static ArrayList<String> getAllProperties(JSObject data) {
        ArrayList<String> propList = new ArrayList<>();
        var properties = (JSObject) data.eval("Object.getOwnPropertyNames(this);");
        for (var i = 0; i < (int) properties.eval("this.length"); ++i) {
            propList.add((String) properties.getSlot(i));
        }
        return propList;
    }

    /**
     * Uses getAllProperties on the supplied JSObject and uses an Editor instance to create a map
     * containing the property names and their values. This is no doubt a very inefficient way of
     * doing this but I still don't enough about JavaScript to make a single call that returns a
     * structure filled with each property and its value.
     *
     * @param jso    a JSObject object.
     * @param editor a {@link org.tjc.jfx.aceeditorexample.Editor} object.
     *
     * @return a {@link java.util.Map} object.
     */
    public static Map<String, Object> getPropertyMap(JSObject jso, Editor editor) {
        Map<String, Object> propertyMap = new HashMap<>();
        List<String> allProps = getAllProperties(jso);
        if (allProps.isEmpty() == false) {
            allProps.forEach((var prop) -> {
                var obj = editor.getOption(prop);
                if (obj != null) {
                    propertyMap.put(prop, obj);
                }
            });
        }
        return propertyMap;
    }

    /**
     * Quick hack to extract a string between prefix and suffix (e.g. given file name
     * 'theme-eclipse.js' it should return 'eclipse'.
     *
     * @param pathToAceSource Path to ace source directory.
     * @param fileNamePrefix  A string that comprises the the stuff to the left of the desired
     *                        string.
     * @param fileNameSuffix  A string the comprises the stuff to the right of the desired string.
     *
     * @return The desired substring.
     *
     * @throws java.io.IOException If there was trouble finding the source directory or listing the
     *                             source
     *                             files in
     *                             the directory.
     */
    public static List<String> extractSubstringsFromFileNames(
            Path pathToAceSource,
            String fileNamePrefix,
            String fileNameSuffix) throws IOException {

        List<String> list = new ArrayList<>();

        DirectoryStream.Filter<Path> filter = (var entry) -> {
            var fileName = entry.getFileName().toString();
            return fileName.startsWith(fileNamePrefix) && fileName.endsWith(fileNameSuffix);
        };

        try(var stream = Files.newDirectoryStream(pathToAceSource, filter)) {
            stream.forEach(filePath -> {
                var fileName = filePath.getFileName().toString().trim();
                var prefixIndex = fileName.indexOf(fileNamePrefix); // should be 0
                if (prefixIndex >= 0) {
                    var startIndx = prefixIndex + fileNamePrefix.length();
                    var endIndx = fileName.indexOf(fileNameSuffix);
                    var subString = fileName.substring(startIndx, endIndx);
                    list.add(capitalize(subString));
                }
            });
        }
        if (!list.isEmpty()) {
            Collections.sort(list);
        }
        return list;
    }

    /**
     * This method uppercases the first character of a String.
     *
     * @param str a {@link java.lang.String} object.
     *
     * @return a {@link java.lang.String} object.
     */
    public static String capitalize(String str) {
        var firstChar = str.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            return str;
        }
        firstChar = Character.toUpperCase(firstChar);
        return firstChar + str.substring(1);
    }

    public static String toFontString(Font font) {
        Objects.requireNonNull(font, () -> "The 'font' argument is null");
        String toFontString = String.format("Font {family: %s, name: %s, style: %s, size: %s}",
                font.getFamily(), font.getName(), font.getStyle(), font.getSize());
        return toFontString;
    }

    private String getSelectedFontFamily(ListView<String> fontFamilyList) {
        return fontFamilyList.getSelectionModel().getSelectedItem();
    }

    private String getSelectedFontStyle(ListView<String> fontStyleList) {
        return fontStyleList.getSelectionModel().getSelectedItem();
    }

    private double getSelectedFontSize(ListView<Double> fontSizeList) {
        return fontSizeList.getSelectionModel().getSelectedItem();
    }

}
