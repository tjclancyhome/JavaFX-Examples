/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxhexviewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.common.utils.MemoryMetricsConverters;

/**
 *
 * @author tjclancy
 */
public class FileDataTest {
    private static final Logger log = LoggerFactory.getLogger(FileDataTest.class);
    private static final List<URL> urls;

    static {
        urls = new ArrayList<>();
//        urls.add(FileDataTest.class.getResource("/test.txt"));
//        urls.add(FileDataTest.class.getResource("/code-saver.zip"));
//        urls.add(FileDataTest.class.getResource("/cff.pdf"));
//        urls.add(FileDataTest.class.getResource("/pdfbox-2.0.17-src.zip"));
        urls.add(FileDataTest.class.getResource("/openjdk-13.0.1_osx-x64_bin.tar.gz"));
    }

    public FileDataTest() {
    }

    /**
     * Test of getByteBuffer method, of class FileData.
     *
     * @throws java.net.URISyntaxException
     * @throws java.io.IOException
     */
    @Test
    public void testGetByteBuffer() throws URISyntaxException, IOException {
        log.debug("### entered testGetByteBuffer()");

        urls.forEach((var testFile) -> {
            try {
                log.debug("### =========================================================");
                log.debug("### localDateTime.now     : {}", LocalDateTime.now());
                log.debug("### =========================================================");
                log.debug("### testFile.externalForm : {}", testFile.toExternalForm());
                log.debug("### testFile.file         : {}", testFile.getFile());
                var file = Paths.get(testFile.getFile());
                log.debug("### file.size             : {}", MemoryMetricsConverters.convertToString(
                        Files.size(file)));

                /*
                 * Track and display the time it takes to load a file.
                 */
                var fileData = new FileData(file);
                var sw = StopWatch.createStarted();
                var byteBuffer = fileData.getByteBuffer();
                sw.stop();
                log.debug("### byteBuffer.limit      : {}", MemoryMetricsConverters.convertToString(
                        byteBuffer.limit()));
                log.debug("### stopWatch.toString()  : {}", sw.toString());
                log.debug("### stopWatch.milliseconds: {}", sw.getTime(TimeUnit.MILLISECONDS));
                log.debug("### stopWatch.microseconds: {}", sw.getTime(TimeUnit.MICROSECONDS));
                log.debug("### stopWatch.nanoseconds : {}", sw.getTime(TimeUnit.NANOSECONDS));
                log.debug("### =========================================================");
                log.debug("");
            } catch (IOException ex) {
                log.error("### Caught exception: {}", ex.getMessage());
            }
        });
    }

    /**
     * Test of newBuffer method, of class FileData.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNewBuffer() throws Exception {
        log.debug("### entered testNewBuffer()");
        urls.forEach((var testFile) -> {
            try {
                log.debug("### =========================================================");
                log.debug("### localDateTime.now     : {}", LocalDateTime.now());
                log.debug("### =========================================================");
                log.debug("### testFile.externalForm : {}", testFile.toExternalForm());
                log.debug("### testFile.file         : {}", testFile.getFile());

                var file = Paths.get(testFile.getFile());
                log.debug("### file.size             : {}", MemoryMetricsConverters.convertToString(
                        Files.size(file)));

                var fileData = new FileData(file);

                var sw = StopWatch.createStarted();
                ByteBuffer byteBuffer = fileData.newBuffer();
                sw.stop();

                log.debug("### byteBuffer.limit       : {}", MemoryMetricsConverters.convertToString(
                        byteBuffer.limit()));
                log.debug("### byteBuffer.capacity    : {}", MemoryMetricsConverters.convertToString(
                        byteBuffer.capacity()));
                log.debug("### byteBuffer.hasArray    : {}", byteBuffer.hasArray());
                log.debug("### byteBuffer.hasRemaining: {}", byteBuffer.hasRemaining());
                log.debug("### byteBuffer.position    : {}", byteBuffer.position());
                log.debug("### byteBuffer.remaining   : {}", MemoryMetricsConverters.convertToString(
                        byteBuffer.remaining()));
                log.debug("### =========================================================");
                log.debug("### stopWatch.toString()  : {}", sw.toString());
                log.debug("### stopWatch.milliseconds: {}", sw.getTime(TimeUnit.MILLISECONDS));
                log.debug("### stopWatch.microseconds: {}", sw.getTime(TimeUnit.MICROSECONDS));
                log.debug("### stopWatch.nanoseconds : {}", sw.getTime(TimeUnit.NANOSECONDS));
                log.debug("### =========================================================");
                log.debug("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Test of openStream method, of class FileData.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testOpenStream() throws Exception {
        log.debug("### entered testOpenStream()");
        urls.forEach((var testFile) -> {
            try {
                log.debug("### =========================================================");
                log.debug("### localDateTime.now     : {}", LocalDateTime.now());
                log.debug("### =========================================================");
                log.debug("### testFile.externalForm : {}", testFile.toExternalForm());
                log.debug("### testFile.file         : {}", testFile.getFile());

                var file = Paths.get(testFile.getFile());
                log.debug("### file.size             : {}", MemoryMetricsConverters.convertToString(
                        Files.size(file)));

                var fileData = new FileData(file);

                var sw = StopWatch.createStarted();
                try( InputStream in = fileData.openStream()) {
                    int len;
                    byte[] buffer = new byte[10240];
                    log.debug("### Reading bytes from fileData stream.");
                    int count = 0;
                    while ((len = in.read(buffer)) != -1) {
                        count += len;
                    }
                    log.debug("### read {} from fileData stream.", MemoryMetricsConverters.convertToString(
                            count));
                }
                sw.stop();

//                log.debug("### byteBuffer.limit       : {}", MemoryMetricsConverters.convertToString(
//                        byteBuffer.limit()));
//                log.debug("### byteBuffer.capacity    : {}", MemoryMetricsConverters.convertToString(
//                        byteBuffer.capacity()));
//                log.debug("### byteBuffer.hasArray    : {}", byteBuffer.hasArray());
//                log.debug("### byteBuffer.hasRemaining: {}", byteBuffer.hasRemaining());
//                log.debug("### byteBuffer.position    : {}", byteBuffer.position());
//                log.debug("### byteBuffer.remaining   : {}", MemoryMetricsConverters.convertToString(
//                        byteBuffer.remaining()));
                log.debug("### =========================================================");
                log.debug("### stopWatch.toString()  : {}", sw.toString());
                log.debug("### stopWatch.milliseconds: {}", sw.getTime(TimeUnit.MILLISECONDS));
                log.debug("### stopWatch.microseconds: {}", sw.getTime(TimeUnit.MICROSECONDS));
                log.debug("### stopWatch.nanoseconds : {}", sw.getTime(TimeUnit.NANOSECONDS));
                log.debug("### =========================================================");
                log.debug("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
