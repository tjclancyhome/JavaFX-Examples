/*
 * The MIT License
 *
 * Copyright 2019 tjc.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.tjc.jfx.jfxsandbox;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXExamplesApplication;

/**
 * JavaFX JFXSandboxApp
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSandboxApp extends JFXExamplesApplication {

    private static final Logger log = LoggerFactory.getLogger(JFXSandboxApp.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        showScreenInfo();

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        var label = new Label(
                "Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch();
    }

    private void showScreenInfo() {
        ObservableList<Screen> screenList = Screen.getScreens();
        System.out.printf("Screen count        : %s\n", screenList.size());
        screenList.forEach(screen -> print(screen));
    }

    private void print(Screen s) {
        System.out.println("DPI                 : " + s.getDpi());
        System.out.print("Screen Bounds       : ");
        Rectangle2D bounds = s.getBounds();
        print(bounds);
        // Print the Visual Bounds
        System.out.print("Screen Visual Bounds: ");
        Rectangle2D visualBounds = s.getVisualBounds();
        print(visualBounds);
    }

    /**
     * <p>
     * print.</p>
     *
     * @param r a {@link javafx.geometry.Rectangle2D} object.
     */
    public void print(Rectangle2D r) {
        // Format the Output
        System.out.format("minX=%.2f, minY=%.2f, width=%.2f, height=%.2f%n", r.getMinX(), r
                .getMinY(), r
                        .getWidth(), r.getHeight());
    }

}
