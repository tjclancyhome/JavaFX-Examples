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
package org.tjc.jfx.jfxpropertiesandlisteners;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * <p>
 * JFXPropertiesAndListernersApp class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXPropertiesAndListernersApp extends JFXFxmlExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXPropertiesAndListernersApp.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws Exception {
        log.debug("### Entered start(Stage): {}", stage);
        Slider s1 = new Slider(0, 100, 40);
        Slider s2 = new Slider(0, 100, 40);
        s2.setOrientation(Orientation.VERTICAL);
        s1.valueProperty().bindBidirectional(s2.valueProperty());

        VBox root = new VBox(5, s1, s2);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 200, 150));
        stage.show();

        // keep in mind that isBound() doesn't work for bidirectional binding
        System.out.println(s1.valueProperty().isBound()); // false
        System.out.println(s2.valueProperty().isBound()); // false
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
