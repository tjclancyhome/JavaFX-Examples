/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxhexviewer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class FileData {
    private static final Logger log = LoggerFactory.getLogger(FileData.class);

    private Path file;
    private ByteBuffer byteBuffer;

    public FileData(Path file) {
        log.debug("### entered FileData(File) constructor: file: {}", file);
        Objects.requireNonNull(file, () -> "The 'file' argument is null.");
        if (!Files.isRegularFile(file)) {
            throw new IllegalArgumentException(String.format("File '%s' is not a valid file.", file
                    .getFileName().toString()));
        }
        this.file = file;
    }

    public InputStream openStream() throws IOException {
        return new BufferedInputStream(Files.newInputStream(file, StandardOpenOption.READ), 1024 * 10);
    }

    public ByteBuffer newBuffer() throws IOException {
        byte[] bytes = Files.readAllBytes(file);
        ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        return buffer;
    }

    public ByteBuffer getByteBuffer() throws IOException {
        log.debug("### entered getByteBuffer()");
        if (byteBuffer == null) {
            log.debug("### byteBuffer is NULL, reading file data.");
            readData();
        }
        log.debug("### returning byteBuffer.");
        return byteBuffer;
    }


    private void readData() throws IOException {
        log.debug("### entered readData()");
        byte[] bytes = Files.readAllBytes(file);
        log.debug("### ByteBuffer wrapping bytes: numBytes: {}", bytes.length);
        byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.flip();
        log.debug("### exited readData()");
    }

}
