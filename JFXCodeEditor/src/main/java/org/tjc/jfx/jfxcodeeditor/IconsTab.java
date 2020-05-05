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
package org.tjc.jfx.jfxcodeeditor;

import java.util.EnumSet;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author tjclancy
 */
public class IconsTab extends Tab {

    IconsTab(Class<? extends Ikon> iconFontClass, EnumSet<? extends Ikon> enumSet) throws Exception {
        super(iconFontClass.getSimpleName());
        setClosable(false);
        this.setText(iconFontClass.getSimpleName());

        GridPane pane = new GridPane();
        pane.setStyle("-fx-background-color:white;");
        ScrollPane scrollPane = new ScrollPane(pane);
        setContent(scrollPane);

        int column = 0;
        int row = 0;
        int index = 0;
        for (Ikon value : enumSet) {

            FontIcon icon = FontIcon.of(value);
            icon.getStyleClass().setAll("font-icon");
            icon.setIconColor(Color.BLUEVIOLET);

            Tooltip t = new Tooltip(value.getDescription());
            t.setShowDelay(Duration.ONE);
            t.setShowDuration(Duration.seconds(5));
            t.setHideDelay(Duration.ZERO);
            Tooltip.install(icon, t);

            pane.add(icon, column++, row);

            GridPane.setMargin(icon, new Insets(15));
            if (++index % 10 == 0) {
                column = 0;
                row++;
            }
        }
    }

}
