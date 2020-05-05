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
package org.tjc.jfx.jfxrootexample;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXRootExampleAppSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXRootExampleApp.class);

    @FXML
    private TabPane exampleTabPane;

    private Tab exampleTab3;

    /**
     * Initializes the controller class.
     *
     * @throws java.io.IOException if any.
     */
    public void initialize() throws IOException {
        log.debug("### entered initialize()");

        /*
         * Creating a Tab that will contain our JFXExampleTagContent custom component.
         */
        exampleTab3 = createExampleTab("Example 3");
        exampleTabPane.getTabs().add(exampleTab3);
    }

    /**
     * <p>
     * Create a tab containing our JFXExampleTabContent custom control.</p>
     *
     * @param tabName a {@link java.lang.String} object containing the name of our tab.
     *
     * @return a {@link javafx.scene.control.Tab} tab that contains our custom component.
     *
     * @throws java.io.IOException if any.
     */
    public Tab createExampleTab(String tabName) throws IOException {
        log.debug("### entered createExampleTab(String): tabName: {}", tabName);
        var tab = new Tab(tabName, JFXExampleTabContent.newInstance());
        return tab;
    }

}
