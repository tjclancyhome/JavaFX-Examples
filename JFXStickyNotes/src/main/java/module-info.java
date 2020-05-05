module org.tjc.jfx.jfxstickynotes {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.tjc.jfx.jfxstickynotes to javafx.fxml;
    exports org.tjc.jfx.jfxstickynotes;
}
