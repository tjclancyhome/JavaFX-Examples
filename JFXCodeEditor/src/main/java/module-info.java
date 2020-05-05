module org.tjc.jfx.jfxcodeeditor {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;

    requires java.base;
    requires org.slf4j;
    requires ch.qos.logback.classic;
    requires java.naming;
    requires jdk.jsobject;
    requires com.jfoenix;
    requires org.kordamp.iconli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.devicons;

    requires org.tjc.jfx.jfxcomponents;
    opens org.tjc.jfx.jfxcodeeditor to javafx.fxml, javafx.web;

    exports org.tjc.jfx.jfxcodeeditor;
}
