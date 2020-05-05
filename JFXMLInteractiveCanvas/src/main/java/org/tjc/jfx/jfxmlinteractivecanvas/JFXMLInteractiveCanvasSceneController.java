/*
 * The MIT License
 *
 * Copyright 2020 tjclancy.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tjc.jfx.jfxmlinteractivecanvas;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXMLInteractiveCanvasSceneController {
    private static final Logger log = LoggerFactory.getLogger(
        JFXMLInteractiveCanvasSceneController.class);

    private static final double DEFAULT_CANVAS_WIDTH = 1024;
    private static final double DEFAULT_CANVAS_HEIGHT = 768;
    private static final Color DEFAULT_FILL_COLOR = Color.AZURE;
    private static final Color DEFAULT_PEN_COLOR = Color.BLACK;
    private static final double DEFAULT_PEN_SIZE = 5.0;

    @FXML
    private VBox mainView;
    @FXML
    private Menu viewMenu;
    @FXML
    private ScrollPane stackPaneScrollPane;
    @FXML
    private StackPane canvasStackPane;
    @FXML
    private Canvas canvas;
    @FXML
    private ToolBar toolBar;
    @FXML
    private ComboBox<Double> penSizeComboBox;
    @FXML
    private ColorPicker penColorPicker;
    @FXML
    private ColorPicker backgroundColorPicker;
    @FXML
    private Slider opacitySlider;
    @FXML
    private VBox penSizeVbox;
    @FXML
    private VBox penColorVbox;
    @FXML
    private VBox canvasColorVbox;
    @FXML
    private VBox opacitySliderVbox;

    private Scene scene;
    private GraphicsContext gc;
    private final DoubleProperty canvasWidthProperty;
    private final DoubleProperty canvasHeightProperty;
    private final DoubleProperty penSizeProperty;
    private final ObjectProperty<Color> canvasFillColorProperty;
    private final ObjectProperty<Color> penColorProperty;

    public JFXMLInteractiveCanvasSceneController() {
        canvasFillColorProperty = new SimpleObjectProperty<>(DEFAULT_FILL_COLOR);
        penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
        penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
        canvasWidthProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_WIDTH);
        canvasHeightProperty = new SimpleDoubleProperty(DEFAULT_CANVAS_HEIGHT);
    }

    public ObjectProperty<Color> canvasFillColorProperty() {
        return canvasFillColorProperty;
    }

    public Color getCanvasFillColor() {
        return canvasFillColorProperty.get();
    }

    public void setCanvasFillColor(Color color) {
        canvasFillColorProperty.set(color);
    }

    public ObjectProperty<Color> penColorProperty() {
        return penColorProperty;
    }

    public Color getPenColor() {
        return penColorProperty.get();
    }

    public void setPenColor(Color color) {
        penColorProperty.set(color);
    }

    public DoubleProperty penSizeProperty() {
        return penSizeProperty;
    }

    public double getPenSize() {
        return penSizeProperty.get();
    }

    public void setPenSize(double penSize) {
        penSizeProperty.set(penSize);
    }

    public DoubleProperty canvasWidthProperty() {
        return canvasWidthProperty;
    }

    public double getCanvasWidth() {
        return canvasWidthProperty.get();
    }

    public void setCanvasWidth(double canvasWidth) {
        canvasWidthProperty.set(canvasWidth);
    }

    public DoubleProperty canvasHeightProperty() {
        return canvasHeightProperty;
    }

    public double getCanvasHeight() {
        return canvasHeightProperty.get();
    }

    public void setCanvasHeght(double canvasHeight) {
        canvasHeightProperty.set(canvasHeight);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        log.debug("### entered initialize()");
        gc = canvas.getGraphicsContext2D();
        initializeToolBar();
        initializeCanvas();
        log.debug("### exited initialize()");
    }

    private void initializeToolBar() {
        penSizeComboBox.getItems().addAll(1.0, 2.0, 4.0, 5.0, 8.0, 10.0, 12.0, 16.0, 20.0);
        penSizeVbox.setPadding(new Insets(0.0, 5.0, 0.0, 0.0));
        penColorVbox.setPadding(new Insets(0.0, 5.0, 0.0, 0.0));
        canvasColorVbox.setPadding(new Insets(0.0, 5.0, 0.0, 0.0));
        opacitySliderVbox.setPadding(new Insets(0.0, 5.0, 0.0, 0.0));
//        opacitySlider.valueProperty().addListener((var obsv, var oldVal,
//            var newVal) -> {
//            log.debug("### opacitySlider Change Listener: oldVal: {}, newVal: {}, obsv: {}",
//                oldVal, newVal, obsv);
//            setCanvasOpacity(newVal.doubleValue());
//        });
    }

    private void initializeCanvas() {
        log.debug("### entered initializeController()");
        canvas.setWidth(canvasWidthProperty.get());
        canvas.setHeight(canvasHeightProperty.get());
        canvasStackPane.setAlignment(Pos.CENTER);
        penSizeComboBox.setValue(penSizeProperty.get());
        penColorPicker.setValue(penColorProperty.get());
        backgroundColorPicker.setValue(canvasFillColorProperty.get());
        stackPaneScrollPane.setFitToWidth(true);
        stackPaneScrollPane.setFitToHeight(true);
//        setCanvasOpacity(opacitySlider.getValue());
        gc.setLineWidth(penSizeProperty.get());
        gc.setFill(canvasFillColorProperty.get());
        clearCanvas();
        canvas.requestFocus();
        initializeMouseHandlers();
        log.debug("### exited initializeController()");
    }

    private void initializeMouseHandlers() {
        canvas.setOnMousePressed(e -> gc.beginPath());
        canvas.setOnMouseDragged((var e) -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });
        canvas.setOnMouseClicked((var e) -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                clearCanvas();
            }
        });
    }

    private void clearCanvas() {
        log.debug("### entered clearCanvas()");
        Bounds bounds = canvas.getBoundsInLocal();
        log.trace("### canvas.boundsInLocal : {}", bounds);
        log.trace("### canvas.boundsInParent: {}", canvas.getBoundsInParent());
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        double width = bounds.getWidth();
        double height = bounds.getHeight();
        Color canvasFill = getCanvasFillColor();
        log.debug("### canvasFillColor: {}", canvasFill);
        gc.setFill(canvasFill);
        gc.fillRect(x, y, width, height);
        gc.setStroke(getPenColor());
        log.debug("### exited clearCanvas()");

    }

//    private void setOpacity(double opacity) {
//        log.debug("### entered setOpacity(): opacity: {}", opacity);
//        gc.setGlobalAlpha(opacity);
//        log.debug("### exited setOpacity()");
//    }
    private void setCanvasOpacity(double opacity) {
        log.debug("### entered setCanvasOpacity(): opacity: {}", opacity);
        Color canvasFill = getCanvasFillColor();
//        Color newCanvasFill = Color.color(canvasFill.getRed(), canvasFill.getGreen(), canvasFill.getBlue(),
//            opacity);
        Color newCanvasFill = Color.color(canvasFill.getRed(), canvasFill.getGreen(), canvasFill.getBlue());
        setCanvasFillColor(newCanvasFill);
        clearCanvas();
        log.debug("### exited setCanvasOpacity()");
    }

    @FXML
    private void onClearCanvas(ActionEvent event) {
        clearCanvas();
        event.consume();
    }

    @FXML
    private void onChangePenSize(ActionEvent event) {
        Object obj = penSizeComboBox.getValue();
        Objects.requireNonNull(
            obj, () -> "The penSize combobox returned a null value.");

        log.debug("### entered onChangePenSize(): event: {}", event);

        log.trace("###     selected value of pen size combobox is: of type: {}, with value: '{}'",
            obj.getClass(), obj);

        Double penSize = null;
        if (obj instanceof Double) {
            penSize = (Double) obj;
        } else if (obj instanceof String) {
            try {
                penSize = Double.valueOf((String) obj);
            } catch (NumberFormatException ex) {
                log.error("The pen size as String: '{}' cannot be converted to Double value.",
                    obj.toString());
            }
        }
        log.debug("###     penSize: {}", penSize);
        if (penSize != null) {
            penSizeProperty.set(penSize);
            gc.setLineWidth(penSizeProperty.get());
        }
        event.consume();
        log.debug("### exited onChangePenSize()");
    }

    @FXML
    private void onChangePenColor(ActionEvent event) {
        log.trace("### entered onChangePenColor(): event: {}", event);
        penColorProperty.set(penColorPicker.getValue());
        gc.setStroke(penColorProperty.get());
        event.consume();
        log.trace("### exited onChangePenColor()");
    }

    @FXML
    private void onChangeBackgroundColor(ActionEvent event) {
        log.trace("### entered onChangeBackgroundColor(): event: {}", event);
        canvasFillColorProperty.set(backgroundColorPicker.getValue());
        gc.setFill(canvasFillColorProperty.get());
        clearCanvas();
        event.consume();
        log.trace("### exited onChangeBackgroundColor()");
    }

    @FXML
    private void onDrawRandomPixels(ActionEvent event) {
        log.debug("### entered onDrawRandomPixels(): event: {}", event);
        drawRandomPixels();
        event.consume();
        log.debug("### exited onDrawRandomPixels()");
    }

    @FXML
    private void onOpacityMouseClicked(MouseEvent event) {
        log.trace("### entered onOpacityMouseClicked(): event: {}", event);
        double opacity = opacitySlider.getValue();
        log.debug("###     opacity: {}", opacity);
//        setCanvasOpacity(opacity);
        event.consume();
        log.trace("### exited onOpacityMouseClicked()");
    }

    @FXML
    private void onOpacityMouseReleased(MouseEvent event) {
        log.trace("### entered onOpacityMouseReleased(): event: {}", event);
        double opacity = opacitySlider.getValue();
        log.debug("###     opacity: {}", opacity);
//        setCanvasOpacity(opacity);
        event.consume();
        log.trace("### exited onOpacityMouseReleased()");
    }

    @FXML
    private void onOpacityMouseDragged(MouseEvent event) {
        log.trace("### entered onOpacityMouseDragged(): event: {}", event);
        double opacity = opacitySlider.getValue();
        log.debug("###     opacity: {}", opacity);
//        setCanvasOpacity(opacity);
        event.consume();
        log.trace("### exited onOpacityMouseDragged()");
    }

    private void drawRandomPixels() {
        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                gc.getPixelWriter().setColor(i, j, Color.color(Math.random(),
                    Math.random(), Math.random()));
            }
        }
    }

}
