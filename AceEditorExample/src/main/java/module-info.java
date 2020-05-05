module org.tjc.jfx.aceeditorexample {
    requires java.base;
    requires java.naming;
    requires jdk.jsobject;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    requires org.slf4j;
    requires ch.qos.logback.classic;

    requires org.tjc.jfx.jfxcomponents;

    opens org.tjc.jfx.aceeditorexample to javafx.fxml, javafx.web;
    exports org.tjc.jfx.aceeditorexample;
    exports org.tjc.jfx.aceeditorexample.model;
}
