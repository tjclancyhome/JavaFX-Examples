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
package org.tjc.jfx.examples.gen.cli;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.tjc.common.unittest.BaseUnitTest;
import static org.tjc.common.unittest.UnitTestSupport.thisMethodName;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;

/**
 *
 * @author tjclancy
 */
public class ExamplesGeneratorCliTest extends BaseUnitTest {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExamplesGeneratorCliTest.class);

    public ExamplesGeneratorCliTest() {
    }

    @BeforeEach()
    public void setup() {
        forceShowOutput();
    }

    @AfterEach
    public void tearDown() {
        restoreShowOutput();
    }

    /**
     * Test of generatePom method, of class ExamplesGeneratorCli.
     * @throws java.lang.Exception
     */
    @Test
    public void testGeneratePom() throws Exception {
        writeBanner(thisMethodName());
    }

    /**
     * Test of main method, of class ExamplesGeneratorCli.
     */
    @Test
    public void testMain() {
        writeBanner(thisMethodName());
        try {
            String[] args = {"--help"};
            ExamplesGeneratorCli.main(args);
        } catch (IOException | URISyntaxException | ParseException ex) {
            log.error("Caught exception: {}", ex);
        }
    }

}
