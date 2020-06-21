/*
 * The MIT License
 *
 * Copyright 2019 tjc.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package org.tjc.jfx.simplegraph;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.application.JFXFxmlExamplesApplication;

/**
 * <p>
 * JFXSimpleGraph class.</p>
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSimpleGraph extends JFXFxmlExamplesApplication {
    private static final Logger log = LoggerFactory.getLogger(JFXSimpleGraph.class);

    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 1024;
    private static final Duration DURATION = Duration.seconds(3.0);

    private final BooleanProperty dragModeActiveProperty;

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    private Stage stage;
    private Scene scene;

    public JFXSimpleGraph() {
        this.dragModeActiveProperty = new SimpleBooleanProperty(this, "dragModeActive", true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        build(this.stage);
    }

    /**
     * <p>
     * main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public BooleanProperty dragModeActiveProperty() {
        return dragModeActiveProperty;
    }

    public Boolean getDragModeActive() {
        return dragModeActiveProperty.get();
    }

    public void setDragModeActive(boolean isDragModeActive) {
        dragModeActiveProperty.set(isDragModeActive);
    }

    public void toggleDragMode() {
        dragModeActiveProperty.set(!dragModeActiveProperty.get());
    }

    private void build(Stage stage) throws Exception {
        log.debug("### entered build(): stage: {}", stage);
        var rootGroup = new Group();

        /*
         * If meta-d is pressed then set the drag mode active property to the NOT of itself. It's
         * essentially a toggle key.
         */
        rootGroup.setOnKeyPressed((var key) -> {
            if (key.getCode().equals(KeyCode.D) && key.isMetaDown()) {
                log.debug("meta-d pressed.");
                dragModeActiveProperty.set(!dragModeActiveProperty.get());
            }
        });

        scene = new Scene(rootGroup, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        var text = new Text(200, 50, "Press Meta-D to toggle drag mode.");
        text.textAlignmentProperty().set(TextAlignment.CENTER);
        text.setFont(new Font(20));
        rootGroup.getChildren().add(text);

        var contentBox = createContentBox();
        contentBox.relocate(200, 150);
        rootGroup.getChildren().add(contentBox);

        var circ = createCircle(40, 40, 30, Color.RED);
        rootGroup.getChildren().add(circ);

        /*
         * We make each rectangle flush-left with left edge of circle.
         */
        rootGroup.getChildren().addAll(
            createRectangle(40 - circ.getRadius(), 80, 60, 60, Color.BLUE),
            createRectangle(40 - circ.getRadius(), 160, 60, 60, Color.YELLOW));

        var triangle1 = createTriangle(200, 100, Color.SILVER, true);
        rootGroup.getChildren().add(triangle1);
        sleep(500);

        var triangle2 = createTriangle(250, 100, Color.SLATEGRAY, true);
        rootGroup.getChildren().add(triangle2);
        sleep(750);

        var triangle3 = createTriangle(300, 100, Color.SLATEBLUE, true);
        rootGroup.getChildren().add(triangle3);
        sleep(1000);

        rootGroup.getChildren().addAll(
            createPolyLine(),
            new Text("Hello!"));

        var simplePaneSceneNode = createNodeFromFXMLFile("SimplePaneScene.fxml");
        simplePaneSceneNode.relocate(300, 250);
        rootGroup.getChildren().add(simplePaneSceneNode);

        var complexPaneSceneNode = createNodeFromFXMLFile("ComplexPaneScene.fxml");
        complexPaneSceneNode.relocate(400, 350);
        rootGroup.getChildren().add(complexPaneSceneNode);

        var canvasAppSceneNode = createNodeFromFXMLFile("CanvasAppScene.fxml");
        canvasAppSceneNode.relocate(500, 450);
        rootGroup.getChildren().add(canvasAppSceneNode);

        stage.setTitle("Random Stuff - A JavaFX-12-Examples Application");
        stage.setScene(scene);
        stage.show();
        log.debug("### exited build()");
    }

    private Circle createCircle(double centerX, double centerY, double radius, Paint fill) {
        log.debug("### entered createCircle(): centerX: {}, centerY: {}, radius: {}, fill: {}",
            centerX, centerY, radius, fill);
        var circle = new Circle(centerX, centerY, radius, fill);
        circle.setOnMousePressed(nodeOnMousePressedEventHandler);
        circle.setOnMouseDragged(nodeOnMouseDraggedEventHandler);
        circle.setOnMouseReleased(nodeOnMouseReleasedEventHandler);
        circle.setOnMouseEntered((Event t) -> {
            log.debug("###     mouse entered circle: source: {}, target: {}", t.getSource(), t
                .getTarget());
        });
        circle.setOnMouseExited(event -> {

        });
        log.debug("### exited createCircle()");
        return circle;
    }

    private Rectangle createRectangle(double x, double y, double width, double height, Paint fill) {
        var rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(fill);
        rectangle.setOnMousePressed(nodeOnMousePressedEventHandler);
        rectangle.setOnMouseDragged(nodeOnMouseDraggedEventHandler);
        rectangle.setOnMouseEntered(nodeOnMousePressedEventHandler);
        rectangle.setOnMouseReleased(nodeOnMouseReleasedEventHandler);
        return rectangle;
    }

    private Polygon createPolygon(Double[] coords, Paint fill) {
        var polygon = new Polygon();
        polygon.getPoints().addAll(coords);
        polygon.setFill(fill);
        polygon.setOnMousePressed(nodeOnMousePressedEventHandler);
        polygon.setOnMouseDragged(nodeOnMouseDraggedEventHandler);
        polygon.setOnMouseReleased(nodeOnMouseReleasedEventHandler);
        return polygon;
    }

    private Polygon createTriangle(double x, double y, Paint fill, boolean rotate) {
        log.debug("### entered createTriangle(x: {}, y: {}, fill: {}, rotate: {})",
            x, y, fill, rotate);
        Double[] triangleCoords = new Double[] {50.0, 0.0, 40.0, 25.0, 60.0, 25.0};

        var triangle = createPolygon(triangleCoords, fill);

        triangle.setPickOnBounds(true);


        log.debug("###     before relocate triangle: {}", triangle);
        log.debug("###     triangle.getBoundsInParent().getCenterX(): {}", triangle
            .getBoundsInParent().getCenterX());
        log.debug("###     triangle.getBoundsInParent().getCenterY(): {}", triangle
            .getBoundsInParent()
            .getCenterY());
        log.debug("###     relocating to: x: {}, y:{}", x, y);
        triangle.relocate(x, y);
        log.debug("###     triangle.getBoundsInParent().getCenterX(): {}", triangle
            .getBoundsInParent()
            .getCenterX());
        log.debug("###     triangle.getBoundsInParent().getCenterY(): {}", triangle
            .getBoundsInParent()
            .getCenterY());
        log.debug("###     after relocate triangle: {}", triangle);

        if (rotate == true) {
            log.debug("###     rotate is true.");
            var triangleBounds = triangle.boundsInLocalProperty().get();
            log.debug("###     triangleBounds: {}", triangleBounds);
            double triangleCenterX = triangleBounds.getCenterX();
            double triangleCenterY = triangleBounds.getCenterY();
            log.debug("###     triangleCenterX: {}", triangleCenterX);
            log.debug("###     triangleCenterY: {}", triangleCenterY);

            final var rotationTransform = new Rotate(10, triangleCenterX, triangleCenterY);

            triangle.getTransforms().add(rotationTransform);

            final var rotationAnimation = new Timeline();
            rotationAnimation.getKeyFrames().add(
                new KeyFrame(
                    DURATION,
                    new KeyValue(
                        rotationTransform.angleProperty(), 360)));
            rotationAnimation.setCycleCount(Animation.INDEFINITE);
            rotationAnimation.play();
        }
        log.debug("### exited createTriangle(x, y, fill, rotate)");
        return triangle;
    }

    /**
     * Creates an arrowhead.
     *
     * @return A new Polyline instance.
     */
    private Node createPolyLine() {
        var polyline = new Polyline();
        polyline.getPoints().addAll(new Double[] {
            100.0, 100.0,
            120.0, 110.0,
            110.0, 120.0,
            100.0, 100.0});
        polyline.setFill(Color.CHARTREUSE);
        return makeDraggable(polyline);
    }

    private Node createNodeFromFXMLFile(String fxmlName) throws IOException {
        log.debug("### entered createNodeFromFXMLFile(fxmlName): fxmlName: {}", fxmlName);
        var fxmlLoader = createFxmlLoader(getClass().getResource(fxmlName));
        var parent = TitledPane.class.cast(fxmlLoader.load());
        parent.setPadding(Insets.EMPTY);

        if (log.isDebugEnabled()) {
            var controller = fxmlLoader.getController();
            log.debug("###     controller: {}", controller);
        }

        log.debug("### exited createNodeFromFXMLFile(fxmlName)");
        return makeDraggable(addDropShadow(parent));
    }

    private Node addDropShadow(Node node) {
        var dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        node.setEffect(dropShadow);
        return node;
    }

    /**
     * Gernerates a VBox panel that contains a Button control that when pressed will show the button
     * event and the 'Press Me!' message within it to stdout (the console).
     * <p>
     * The URL: https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm
     *
     * @return a {@link javafx.scene.layout.VBox} object.
     */
    private Node createContentBox() {
        log.debug("### entered createContentBox()");
        var contentBox = new VBox();
        contentBox.setPrefSize(300, 200);
        contentBox.setMouseTransparent(false);
        contentBox.setAlignment(Pos.TOP_LEFT);
        contentBox.setPickOnBounds(true);

        var fileMenu = new Menu("File");
        var editMenu = new Menu("Edit");
        var viewMenu = new Menu("View");
        var windowMenu = new Menu("Window");
        var helpMenu = new Menu("Help");

        var menuBar = new MenuBar(fileMenu, editMenu, viewMenu, windowMenu, helpMenu);

        var borderPane = new BorderPane();

        var scrollPane = new ScrollPane(borderPane);

        var textField = new TextField("Hello, World!");

        var button = new Button("Press Me!");
        button.setOnAction(event -> {
            log.debug("{}", textField.getCharacters());
            log.debug("Button pressed! event: {}", event);
        });
        borderPane.setTop(new Button("Don't Press Me"));
        borderPane.setLeft(new Button("Don't Press Me"));
        borderPane.setCenter(textField);
        borderPane.setBottom(button);
        contentBox.getChildren().addAll(menuBar, borderPane);
        contentBox.setStyle("-fx-background-color:lightblue;");
        contentBox.setLayoutX(175);
        contentBox.setLayoutY(75);
        contentBox.setSpacing(10);
        contentBox.setFillWidth(true);

        log.debug("### exited createContentBox()");
        return makeDraggable(addDropShadow(contentBox));
    }

    /**
     * I borrowed this method, DragContext and dragModeActiveProperty from an old JavaFX 2.2
     * tutorial.
     *
     * @param node
     *
     * @return
     */
    private Group makeDraggable(final Node node) {
        log.debug("### entered makeDraggable(): node: {}", node);

        var dragContext = new DragContext();
        var wrapGroup = new Group(node);

        wrapGroup.addEventFilter(MouseEvent.ANY, (final                                                                                                                                     var mouseEvent) -> {
            if (dragModeActiveProperty.get()) {
                mouseEvent.consume();
            }
        });

        wrapGroup.addEventFilter(MouseEvent.MOUSE_PRESSED, (final MouseEvent mouseEvent) -> {
            log.debug("### entered wrapGroup.addEventFilter MOUSE_PRESSED.");
            if (dragModeActiveProperty.get()) {
                scene.setCursor(Cursor.CLOSED_HAND);
                wrapGroup.toFront();
                wrapGroup.setOpacity(0.75);
                log.trace("###     node.opacity: {}", wrapGroup.getOpacity());
                dragContext.mouseAnchorX = mouseEvent.getX();
                dragContext.mouseAnchorY = mouseEvent.getY();
                dragContext.initialTranslateX = node.getTranslateX();
                dragContext.initialTranslateY = node.getTranslateY();
                mouseEvent.consume();
            }
            log.debug("### exited wrapGroup.addEventFilter MOUSE_PRESSED.");
        });

        wrapGroup.addEventFilter(MouseEvent.MOUSE_DRAGGED, (final MouseEvent mouseEvent) -> {
            if (dragModeActiveProperty.get()) {
                scene.setCursor(Cursor.HAND);
                node.setTranslateX(
                    dragContext.initialTranslateX
                    + mouseEvent.getX()
                    - dragContext.mouseAnchorX);
                node.setTranslateY(
                    dragContext.initialTranslateY
                    + mouseEvent.getY()
                    - dragContext.mouseAnchorY);
                mouseEvent.consume();
            }
        });

        wrapGroup.addEventFilter(MouseEvent.MOUSE_RELEASED, (final     var mouseEvent) -> {
            log.debug("### entered wrapGroup eventFilter MOUSE_RELEASED: event: {}", mouseEvent);
            if (dragModeActiveProperty.get()) {
                wrapGroup.setOpacity(1.0);
                log.trace("###     node.opacity: {}", wrapGroup.getOpacity());
                scene.setCursor(Cursor.DEFAULT);
            }
            mouseEvent.consume();
            log.debug("### exited wrapGroup eventFilter MOUSE_RELEASED: event: {}", mouseEvent);
        });
        log.debug("### exited makeDraggable(): node: {}", node);
        return wrapGroup;
    }

    private final EventHandler<MouseEvent> nodeOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug("### entered nodeOnMousePressedEventHandler handler: mouse event: {}",
                event);
            orgSceneX = event.getSceneX();
            orgSceneY = event.getSceneY();
            if (event.getSource() instanceof Node) {
                var node = Node.class.cast(event.getSource());
                node.toFront();
                node.setOpacity(0.75);
                log.trace("###     node.opacity: {}", node.getOpacity());
                orgTranslateX = node.getTranslateX();
                orgTranslateY = node.getTranslateY();
            }
            event.consume();
            log.debug("### exited nodeOnMousePressedEventHandler handler: mouse event: {}",
                event);
        }
    };

    private final EventHandler<MouseEvent> nodeOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug("### entered nodeOnMouseDraggedEventHandler handler: mouse event: {}",
                event);
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if (event.getSource() instanceof Node) {
                var node = Node.class.cast(event.getSource());
                log.debug("###     node: {}", node);
                node.setTranslateX(newTranslateX);
                node.setTranslateY(newTranslateY);
            }
            event.consume();
            log.debug("### exited nodeOnMouseDraggedEventHandler handler");
        }
    };

    private final EventHandler<MouseEvent> nodeOnMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            log.debug("### entered nodeOnMouseReleasedEventHandler handler: mouse event: {}",
                event);
            double offsetX = event.getSceneX() - orgSceneX;
            double offsetY = event.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if (event.getSource() instanceof Node) {
                var node = Node.class.cast(event.getSource());
                log.debug("###     node: {}", node);
                node.setOpacity(1.0);
                node.setTranslateX(newTranslateX);
                node.setTranslateY(newTranslateY);
                log.debug("###     node: {}", node);
            }
            event.consume();
            log.debug("### exited nodeOnMouseReleasedEventHandler handler: mouse event: {}",
                event);
        }
    };

    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
        }
    }
}
