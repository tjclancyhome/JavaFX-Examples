/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.javafonts;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author tjclancy
 */
public class JavaFonts {

    private static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public static void main(String[] args) {

        Font[] fontArray = ge.getAllFonts();

//        int i = 0;
//        for (Font font : fontArray) {
//            System.out.printf("(family: %s, name: %s) at index %s\n",
//                font.getFamily(), font.getFontName(), i++);
//        }

//        for (Font font : fontArray) {
//            printAvailableAttributes(font);
//            System.out.println();
//        }
//        Rectangle b = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
//        System.out.println("### bounds: " + b);
//        for (Font font : fontArray) {
//            printFontInfo(font);
//            System.out.println();
//        }
//        System.out.println();
        var g2d = getGraphics2D();
        var f = new Font(fontArray[2145].getFontName(), 1, 14);
        g2d.setFont(f);
//        printLineMetrics("A", g2d);
//        printLineMetrics("AA", g2d);
        printLineMetrics("abcdefghijklmnopqrstuvwxyz", g2d);
        printLineMetrics("ABCDEFGHIJKLMNOPQRSTUVWXYZ", g2d);
        printLineMetrics("B", g2d);
        System.exit(0);

    }

    private static void printLineMetrics(String str, Graphics2D g2d) {
        System.out.println(createBorderedMessage(
            String.format("printing line metrics of string: '%s'", str)));

        var fontMetrics = g2d.getFontMetrics();
        var lineMetrics = fontMetrics.getLineMetrics(str, g2d);

        System.out.println(createBorderedMessage("Graphics2D Info:"));
        System.out.printf("###   font: %s\n", g2d.getFont());
        System.out.println();
        System.out.println(createBorderedMessage(String.format("fontMetrics of String: %s", str)));
        System.out.printf("###   fontMetrics.stringWidth       : %s\n", fontMetrics.stringWidth(str));
        System.out.printf("###   fontMetrics.stringBounds      : %s\n", fontMetrics.getStringBounds(str, g2d));
        System.out.printf("###   fontMetrics.uniformLineMetrics: %s\n", fontMetrics.hasUniformLineMetrics());
        System.out.println();
        System.out.println(createBorderedMessage(String.format("lineMetrics of String: %s", str)));
        System.out.printf("###   lineMetrics.ascent            : %s\n", lineMetrics.getAscent());
        System.out.printf("###   lineMetrics.descent           : %s\n", lineMetrics.getDescent());
        System.out.printf("###   lineMetrics.height            : %s\n", lineMetrics.getHeight());
        System.out.printf("###   lineMetrics.leading           : %s\n", lineMetrics.getLeading());
        System.out.printf("###   lineMetrics.numChars          : %s\n", lineMetrics.getNumChars());
        System.out.println();
    }

    private static Graphics2D getGraphics2D() {
        var gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDefaultConfiguration();
        var frame = new JFrame(gc);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        var g = frame.getGraphics();
        return (Graphics2D) g;
    }

    public static void printAttributes(Font font) {
        var border = genBorder(font);
        System.out.println(border);
        System.out.println("### Font: " + font.toString());
        System.out.println(border);
        font.getAttributes().forEach((var attribute, var a) -> {
            System.out.printf("###   attribute: %s\n", attribute.toString());
        });
        System.out.println();
    }

    public static void printAvailableAttributes(Font font) {
        var border = genBorder(font);
        System.out.println(border);
        System.out.println("### Font: " + font.toString());
        System.out.println(border);
        Attribute[] attrArray = font.getAvailableAttributes();
        Arrays.asList(attrArray).forEach(a -> {
            System.out.printf("###   available attribute: %s\n", a);
        });
        System.out.println();
    }

    public static void printFontInfo(Font font) {
        var border = genBorder(font);
        System.out.println(border);
        System.out.println("### Font: " + font.toString());
        System.out.println(border);
        System.out.println(String.format("###   font.family         : %s", font.getFamily()));
        System.out.println(String.format("###   font.fontName       : %s", font.getFontName()));
        System.out.println(String.format("###   font.logicalName    : %s", font.getName()));
        System.out.println(String.format("###   font.PSName         : %s", font.getPSName()));
        System.out.println(String.format("###   font.italicAngle    : %s", font.getItalicAngle()));
        System.out.println(String.format("###   font.numGlyphs      : %s", font.getNumGlyphs()));
        System.out.println(String.format("###   font.size           : %s", font.getSize()));
        System.out.println(String.format("###   font.size2D         : %s", font.getSize2D()));
        System.out.println(String.format("###   font.style          : %s", font.getStyle()));
        System.out.println(String.format("###   font.affineTransform: %s", font.getTransform()));
        System.out.println(String.format("###   font.isBold         : %s", font.isBold()));
        System.out.println(String.format("###   font.isItalic       : %s", font.isItalic()));
        System.out.println(String.format("###   font.isPlain        : %s", font.isPlain()));
        System.out.println(String.format("###   font.isTransformed  : %s", font.isTransformed()));

        System.out.println();
    }

    private static String genBorder(Font font) {
        var toStr = font.toString();
        int len = toStr.length() + "Font: ".length();
        var sb = new StringBuilder();
        sb.append("### ");
        for (int i = 0; i < len; i++) {
            sb.append('=');
        }
        return sb.toString();
    }

    private static String createBorderedMessage(String message) {
        var sb = new StringBuilder();
        int len = message.length();
        var borderLine = fillString(len, "=");
        sb.append(borderLine).append("\n")
            .append(message).append("\n")
            .append(borderLine);
        return sb.toString();
    }

    private static String fillString(int len, String fillStr) {
        var sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(fillStr);
        }
        return sb.toString();
    }
}
