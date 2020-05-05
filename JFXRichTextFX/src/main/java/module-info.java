module org.tjc.jfx.jfxrichtextfx {
    requires java.base;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    requires org.slf4j;
    requires ch.qos.logback.classic;

    requires richtextfx;
    requires flowless;
    requires reactfx;

    requires org.tjc.jfx.jfxcomponents;

    opens org.tjc.jfx.jfxrichtextfx to javafx.fxml;
    exports org.tjc.jfx.jfxrichtextfx;
}
