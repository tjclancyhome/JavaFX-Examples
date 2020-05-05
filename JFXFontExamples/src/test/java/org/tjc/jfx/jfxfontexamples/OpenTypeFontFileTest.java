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
package org.tjc.jfx.jfxfontexamples;

import static java.lang.String.format;
import static org.tjc.common.unittest.UnitTestSupport.methodName;
import static org.tjc.common.unittest.UnitTestSupport.setShowOutput;
import static org.tjc.common.unittest.UnitTestSupport.write;
import static org.tjc.common.unittest.UnitTestSupport.writeBanner;
import static org.tjc.common.unittest.UnitTestSupport.writeln;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxfontexamples.fontfile.OpenTypeFontFile;

/**
 *
 * @author tjclancy
 */
public class OpenTypeFontFileTest {
    private static final Logger log = LoggerFactory.getLogger(OpenTypeFontFileTest.class);

    public OpenTypeFontFileTest() {
    }

    @BeforeEach
    public void setup() {
        setShowOutput(true);
    }

    @AfterEach
    public void tearDown() {
        setShowOutput(false);
    }

    /**
     * Test of debugShowAwtFontInfo method, of class OpenTypeFontFile.
     *
     * @throws java.lang.Exception
     */
    // @Test
    public void testDebugShowAwtFontInfo() throws Exception {
        writeBanner(methodName());
        OpenTypeFontFile ff = new OpenTypeFontFile();
        ff.debugShowAwtFontInfo();
        writeln("");
    }

    /**
     *
     * @throws FileNotFoundException
     */
    @Test
    public void smokeTest() throws FileNotFoundException, IOException {
        writeBanner(methodName());

        OpenTypeFontFile ff = new OpenTypeFontFile(new File("src/test/resources/fonts/BIRTH OF A HERO.ttf"));
        try (RandomAccessFile raf = ff.openFontFile()) {

            showFilePointer(raf);

            raf.seek(0);

            writeln("file length: {0} bytes", String.valueOf(raf.length()));

            showFilePointer(raf);

            byte[] bytes = new byte[10];
            int numBytes = raf.read(bytes);
            writeln("number of bytes read: {0} bytes", String.valueOf(numBytes));
            showBytes(bytes);

            showFilePointer(raf);

        }
        writeln("");
    }

    @Test
    public void testToHex_Integer() {
        writeBanner(methodName());

        for (int i = 0; i < 256; i++) {
            writeln(format("%2s", toHex(i).toUpperCase()));
        }
    }

    @Test
    public void testToHex_Long() {
        writeBanner(methodName());

        for (long l = 0; l < 256; l++) {
            writeln(format("%2s", toHex(l).toUpperCase()));
        }
    }

    @Test
    public void testHexDump() throws IOException {
        writeBanner(methodName());

        // hexDump(new File("src/test/resources/fonts/BIRTH OF A HERO.ttf"), null);
        hexDump(new File("src/test/resources/fonts/fonts.mf"), null);
    }

    private void showFilePointer(RandomAccessFile raf) throws IOException {
        long filePointer = raf.getFilePointer();
        writeln("filePointer: {0}", String.valueOf(filePointer));
    }

    private void showBytes(byte[] bytes) {
        writeln("show bytes read:");
        writeln("================");
        for (byte b : bytes) {
            writeln("    byte: {0}", toHex(b));
        }
    }

    private String toHex(byte b) {
        return format("%x", Byte.toUnsignedInt(b));
    }

    private String toHex(Integer i) {
        return format("%x", i);
    }

    private String toHex(Long l) {
        return format("%x", l);
    }

    private String toUpper(String s) {
        return s.toUpperCase();
    }

    private String replace(String s, char target, char replacement) {
        return s.replace(target, replacement);
    }

    public void hexDump(File file, HexDumpConfig config) throws FileNotFoundException, IOException {
        Objects.requireNonNull(file, () -> "The 'file' argument is null.");
        if (!file.exists()) {
            throw new IllegalArgumentException(format("the file '%s' does not exist.", file.toString()));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(format("the 'file' argument is not a file.", file
                .toString()));
        }
        if (config == null) {
            config = new HexDumpConfig(5);
        }
        Objects.requireNonNull(config, () -> "The 'config' argument is null.");

        log.debug("### config: {}", config);
        byte[] bytes;
        try (FileInputStream in = new FileInputStream(file)) {
            bytes = in.readAllBytes();
        }

        writeln("======================================================");
        writeln("hex dump: columns: {0}, rows: {1}, startOffset: {2}", config.getColumns(),
            (config.getRows() == -1 ? "(ALL)" : config.getRows()), config.getStartOffset());
        writeln("======================================================");
        write("  | ");
        for (int i = 0; i < bytes.length; i++) {
            if (i != 0 && i % config.getColumns() == 0) {
                writeln("");
                write("  | ");
            }
            write(toUpper(format("%s ", replace(format("%2s", toHex(bytes[i])), ' ', '0'))));
            // log.debug("### i: {}, {} % {}: {}", i, i, config.getColumns(), i % config.getColumns());
            // if (i >= config.getColumns() && (i % config.getColumns()) == 0) {
            // writeln("");
            // }
        }
        writeln("");

        // int numBytes = bytes.length;
        // log.debug("### numBytes read: {}", numBytes);
        // int numRows = numBytes / config.getColumns();
        // log.debug("### numRows: {}", numRows);

        // for (int r = 0; r < numRows; r++) {
        // StringBuilder sb = new StringBuilder();
        // for (int i = r * config.getColumns(); i < r * config.getColumns(); i++) {
        // byte b = bytes[i];
        // String hexStr = String.format("%2s", toUpper(toHex(b))).replace(' ', '0');
        // sb.append(hexStr).append(" ");
        // }
        // r = r + config.getColumns();
        // writeln("{0}", sb.toString());
        // }
    }

}
