module org.tjc.jfx.gameoflife {
    requires java.naming;
    requires java.base;
    requires java.xml;

    requires javafx.base;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.media;
    requires javafx.web;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires org.tjc.jfx.jfxcomponents;
    requires org.tjc.common.utils;

    exports org.tjc.jfx.gameoflife;
}
