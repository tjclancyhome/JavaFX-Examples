import java.applet.*;
import java.awt.*;
import java.awt.color.*;
import java.awt.datatransfer.*;
import java.awt.desktop.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.im.*;
import java.awt.im.spi.*;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.awt.print.*;
import java.beans.*;
import java.beans.beancontext.*;
import java.io.*;
import java.lang.*;
import java.lang.annotation.*;
import java.lang.constant.*;
import java.lang.instrument.*;
import java.lang.invoke.*;
import java.lang.management.*;
import java.lang.module.*;
import java.lang.ref.*;
import java.lang.reflect.*;
import java.lang.runtime.*;
import java.math.*;
import java.net.*;
import java.net.http.*;
import java.net.spi.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;
import java.nio.charset.*;
import java.nio.charset.spi.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.*;
import java.rmi.*;
import java.rmi.activation.*;
import java.rmi.dgc.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.security.*;
import java.security.cert.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.sql.*;
import java.text.*;
import java.text.spi.*;
import java.time.*;
import java.time.chrono.*;
import java.time.format.*;
import java.time.temporal.*;
import java.time.zone.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.function.*;
import java.util.jar.*;
import java.util.logging.*;
import java.util.prefs.*;
import java.util.regex.*;
import java.util.spi.*;
import java.util.stream.*;
import java.util.zip.*;
import javax.accessibility.*;
import javax.annotation.processing.*;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;
import javax.imageio.*;
import javax.imageio.event.*;
import javax.imageio.metadata.*;
import javax.imageio.plugins.bmp.*;
import javax.imageio.plugins.jpeg.*;
import javax.imageio.plugins.tiff.*;
import javax.imageio.spi.*;
import javax.imageio.stream.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.*;
import javax.management.*;
import javax.management.loading.*;
import javax.management.modelmbean.*;
import javax.management.monitor.*;
import javax.management.openmbean.*;
import javax.management.relation.*;
import javax.management.remote.*;
import javax.management.remote.rmi.*;
import javax.management.timer.*;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.event.*;
import javax.naming.ldap.*;
import javax.naming.ldap.spi.*;
import javax.naming.spi.*;
import javax.net.*;
import javax.net.ssl.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;
import javax.rmi.ssl.*;
import javax.script.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.kerberos.*;
import javax.security.auth.login.*;
import javax.security.auth.spi.*;
import javax.security.auth.x500.*;
import javax.security.cert.*;
import javax.security.sasl.*;
import javax.sound.midi.*;
import javax.sound.midi.spi.*;
import javax.sound.sampled.*;
import javax.sound.sampled.spi.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.sql.rowset.serial.*;
import javax.sql.rowset.spi.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.multi.*;
import javax.swing.plaf.nimbus.*;
import javax.swing.plaf.synth.*;
import javax.swing.table.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.rtf.*;
import javax.swing.tree.*;
import javax.swing.undo.*;
import javax.tools.*;
import javax.transaction.xa.*;
import javax.xml.*;
import javax.xml.catalog.*;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.datatype.*;
import javax.xml.namespace.*;
import javax.xml.parsers.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import javax.xml.stream.util.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stax.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import javax.xml.xpath.*;
import org.ietf.jgss.*;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.*;
import org.w3c.dom.events.*;
import org.w3c.dom.ls.*;
import org.w3c.dom.ranges.*;
import org.w3c.dom.traversal.*;
import org.w3c.dom.views.*;
import org.xml.sax.*;
import org.xml.sax.ext.*;
import org.xml.sax.helpers.*;
void print(boolean b) { System.out.print(b); }
void print(char c) { System.out.print(c); }
void print(int i) { System.out.print(i); }
void print(long l) { System.out.print(l); }
void print(float f) { System.out.print(f); }
void print(double d) { System.out.print(d); }
void print(char s[]) { System.out.print(s); }
void print(String s) { System.out.print(s); }
void print(Object obj) { System.out.print(obj); }
void println() { System.out.println(); }
void println(boolean b) { System.out.println(b); }
void println(char c) { System.out.println(c); }
void println(int i) { System.out.println(i); }
void println(long l) { System.out.println(l); }
void println(float f) { System.out.println(f); }
void println(double d) { System.out.println(d); }
void println(char s[]) { System.out.println(s); }
void println(String s) { System.out.println(s); }
void println(Object obj) { System.out.println(obj); }
void printf(java.util.Locale l, String format, Object... args) { System.out.printf(l, format, args); }
void printf(String format, Object... args) { System.out.printf(format, args); }
void write(boolean b) { System.out.print(b); }
void write(char c) { System.out.print(c); }
void write(int i) { System.out.print(i); }
void write(long l) { System.out.print(l); }
void write(float f) { System.out.print(f); }
void write(double d) { System.out.print(d); }
void write(char s[]) { System.out.print(s); }
void write(String s) { System.out.print(s); }
void write(Object obj) { System.out.print(obj); }
void writeln() { System.out.println(); }
void writeln(boolean b) { System.out.println(b); }
void writeln(char c) { System.out.println(c); }
void writeln(int i) { System.out.println(i); }
void writeln(long l) { System.out.println(l); }
void writeln(float f) { System.out.println(f); }
void writeln(double d) { System.out.println(d); }
void writeln(char s[]) { System.out.println(s); }
void writeln(String s) { System.out.println(s); }
void writeln(Object obj) { System.out.println(obj); }
void writef(java.util.Locale l, String format, Object... args) { System.out.printf(l, format, args); }
void writef(String format, Object... args) { System.out.printf(format, args); }

public class Simple2 extends JFrame{//inheriting JFrame  
    JFrame f;  
    Simple2(){  
        JButton b=new JButton("click");//create button  
        b.setBounds(130,100,100, 40);  
          
        add(b);//adding button on frame  
        setSize(400,500);  
        setLayout(null);  
        setVisible(true);  
    }  
}  

Simple2 simple2 = new Simple2()

