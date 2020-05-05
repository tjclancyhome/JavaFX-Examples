package org.tjc.jfx.jfxstickynotes;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        JFXStickyNotesApp.setRoot("secondary");
    }
}
