module org.tjc.jfx.jfxmlinteractivecanvas {
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
    requires org.apache.commons.lang3;

    opens org.tjc.jfx.jfxmlinteractivecanvas to javafx.fxml;
    exports org.tjc.jfx.jfxmlinteractivecanvas;
}
