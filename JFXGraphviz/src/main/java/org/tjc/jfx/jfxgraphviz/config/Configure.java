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
package org.tjc.jfx.jfxgraphviz.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.common.utils.SystemProps;

/**
 * The configuration class that reads config.properties.
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class Configure {
    private static final Logger log = LoggerFactory.getLogger(Configure.class);
    private static final String CONFIG_PROPERTIES_PATH = "/config.properties";

    private Properties configProps;

    /**
     * <p>
     * Constructor for Config.</p>
     *
     */
    private Configure() {
        log.debug("### entered Config constructor.");
        try {
            loadApplicationProps();
            configurePaths();
        } catch (Exception ex) {
            log.warn("### Intercepted exception in Config constructor: {}", ex.getMessage());
        }
        log.debug("### exited Config constructor.");
    }

    public static Configure getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Get the value of a property given the key.
     *
     * @param key The key associated with a value.
     *
     * @return Return the value or null if the key can't be found.
     */
    public String getProperty(String key) {
        return configProps.getProperty(key);
    }

    /**
     * Get the value of a property given the key. If the key is not found it returns the
     * defaultValue.
     *
     * @param key          The key associated with a value.
     * @param defaultValue A default value to return if key could not be found.
     *
     * @return The key's value or the default value.
     */
    public String getProperty(String key, String defaultValue) {
        return configProps.getProperty(key, defaultValue);
    }

    /**
     * <p>
     * getPathToOutputDir.</p>
     *
     * @return a {@link java.nio.file.Path} object.
     */
    public Path getPathToOutputDir() {
        return Path.of(getProperty("path.to.output.dir", SystemProps.userHome() + "/.tmp"));
    }

    /**
     * <p>
     * getPathToNeato.</p>
     *
     * @return a {@link java.nio.file.Path} object.
     */
    public Path getPathToNeato() {
        return Path.of(getProperty("path.to.neato"));
    }

    /**
     * <p>
     * getPathToGraphvizRoot.</p>
     *
     * @return a {@link java.nio.file.Path} object.
     */
    public Path getPathToGraphvizRoot() {
        return Path.of(getProperty("path.to.graphviz.root"));
    }

    /**
     * <p>
     * getPathToDot.</p>
     *
     * @return a {@link java.nio.file.Path} object.
     */
    public Path getPathToDot() {
        return Path.of(getProperty("path.to.dot"));
    }

    /**
     * <p>
     * getPathToUserHome.</p>
     *
     * @return a {@link java.nio.file.Path} object.
     */
    public Path getPathToUserHome() {
        return Path.of(SystemProps.userHome());
    }

    @SuppressWarnings("UseSpecificCatch")
    private void loadApplicationProps() throws Exception {
        log.debug("### entered loadApplicationProps()");

        URI rootPathURI = Configure.class.getResource("/config.properties").toURI();
        log.debug("###    rootPathURI: {}", rootPathURI);

        configProps = new Properties();

        try(InputStream in = rootPathURI.toURL().openStream()) {
            configProps.load(in);
        }

        debugPrintProperties(configProps);

        log.debug("### exited loadApplicationProps()");

    }

    /**
     *
     * @throws IOException
     */
    private void configurePaths() throws IOException {
        log.debug("### entered configurePaths()");
        Path outputDir = getPathToOutputDir();
        if (Files.notExists(outputDir)) {
            try {
                log.debug("###    Creating outputDir: {}", outputDir);
                Files.createDirectories(outputDir);
            } catch (IOException ex) {
                log.error("### Caught exception creating output directories: {}, message: {}",
                        outputDir.toString(),
                        ex.getMessage());
                throw ex;
            }
        }
        log.debug("### exited configurePaths()");
    }

    private static class InstanceHolder {
        private static final Configure INSTANCE = new Configure();

        static {
            log.debug("### initialized InstanceHolder singleton.");
        }
    }

    private static void debugPrintProperties(Properties props) {
        if (log.isDebugEnabled()) {
            log.debug("### ==================");
            log.debug("### listing props");
            log.debug("### ==================");
            props.forEach((k, v) -> log.debug("{} -> {}", k, v));
            log.debug("### ==================");
            log.debug("### done listing props");
            log.debug("### ==================");
        }
    }
}
