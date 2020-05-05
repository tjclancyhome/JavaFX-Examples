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
package org.tjc.jfx.core.fxml;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tjclancy
 *
 */
public class FxmlUtil {

    private static final Logger log = LoggerFactory.getLogger(FxmlUtil.class);

    public static FXMLLoader createLoader(Class<?> resourceClass, String fxmlFileName) throws IOException {
        log.debug("##### entered createLoader(resourceClass: {}, fxmlFileName: {})", resourceClass
                .getName(),
                fxmlFileName);
        URL url = resourceClass.getResource(fxmlFileName);
        log.debug("##### resource url: {}", url);
        return createLoader(url);
    }

    public static FXMLLoader createLoader(URL fxmlResource) throws IOException {
        log.debug("##### entered createLoader(fxmlResource: {})", fxmlResource);
        FXMLLoader loader;
        try {
            loader = FXMLLoader.load(fxmlResource);
        } catch (Throwable ex) {
            System.out.println("==============");
            ex.printStackTrace();
            System.out.println("==============");
            System.out.println(ex);
            log.debug("##### Caught exception: {}", ex.getMessage());
            throw ex;
        }
        return loader;
    }

    public static Parent loadFxml(FXMLLoader loader) throws IOException {
        return loader.load();
    }
}
