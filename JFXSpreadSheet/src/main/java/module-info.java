module org.tjc.jfx.jfxspreadsheet {
    requires java.naming;
    requires java.base;

    requires javafx.base;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;

    requires org.tjc.jfx.jfxcomponents;

    opens org.tjc.jfx.jfxspreadsheet to javafx.fxml;
    exports org.tjc.jfx.jfxspreadsheet;
}
