/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxhexviewer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * Borrowed code from the following <a href="https://www.baeldung.com/java-char-encoding">baeldung article</a>
 *
 * @author tjclancy
 */
public class ByteEncodingTest {

    @Test
    public void testConvertToBinary() throws UnsupportedEncodingException {
        System.out.printf("'T' to Binary Encoded as US_ASCII  : %s\n", convertToBinary("T",
                StandardCharsets.US_ASCII));

        System.out.printf("'T' to Binary Encoded as UTF-8     : %s\n", convertToBinary("T",
                StandardCharsets.UTF_8));

        System.out.printf("'T' to Binary Encoded as UTF-16    : %s\n", convertToBinary("T",
                StandardCharsets.UTF_16));

        System.out.printf("'T' to Binary Encoded as ISO_8859_1: %s\n", convertToBinary("T",
                StandardCharsets.ISO_8859_1));

        System.out.printf("'語' to Binary Encoded as Big5     : %s\n", convertToBinary("語",
                Charset.forName("Big5")));

        System.out.println();
        System.out.println("default charset: " + Charset.defaultCharset().displayName());
        System.out.println("\n");
    }

    @Test
    public void testDecodeText() throws IOException {
        System.out.println(decodeText("The façade pattern is a software design pattern.",
                StandardCharsets.US_ASCII));

        System.out.println(decodeText("The façade pattern is a software design pattern.",
                StandardCharsets.UTF_8));

        System.out.println(decodeText("The façade pattern is a software design pattern.",
                StandardCharsets.US_ASCII,
                CodingErrorAction.IGNORE));

        System.out.println(decodeText("The façade pattern is a software design pattern.",
                StandardCharsets.US_ASCII,
                CodingErrorAction.REPLACE));

        try {
            System.out.println(decodeText("The façade pattern is a software design pattern.",
                    StandardCharsets.US_ASCII,
                    CodingErrorAction.REPORT));
        } catch (IOException ex) {
            System.err.printf("Caught expected exception using CodingErrorAction.REPORT: %s\n", ex
                    .getMessage());
        }
        System.out.println("\n");
    }

    public String convertToBinary(String input, Charset charset) throws UnsupportedEncodingException {
        byte[] encoded_input = charset.encode(input).array();
        return IntStream.range(0, encoded_input.length)
                .map(i -> encoded_input[i])
                .mapToObj(e -> Integer.toBinaryString(e ^ 255))
                .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
                .collect(Collectors.joining(" "));
    }

    public String decodeText(String input, Charset charset) throws IOException {
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(input.getBytes()), charset)).readLine();
    }

    String decodeText(String input, Charset charset, CodingErrorAction codingErrorAction) throws IOException {
        CharsetDecoder charsetDecoder = charset.newDecoder();
        charsetDecoder.onMalformedInput(codingErrorAction);
        return new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(input.getBytes()), charsetDecoder)).readLine();
    }
}
