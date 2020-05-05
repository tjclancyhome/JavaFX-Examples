module org.tjc.jfx.jfxcontrollerexamples {
    requires java.base;
    requires java.naming;

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

    opens org.tjc.jfx.jfxcontrollerexamples to javafx.fxml;
    exports org.tjc.jfx.jfxcontrollerexamples;
}
