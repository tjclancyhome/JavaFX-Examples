/*
 * The MIT License
 *
 * Copyright 2019 tjclancy.
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
package org.tjc.jfx.jfxshapes;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author tjclancy
 */
public class JFXShapesSceneController {

    private static final Logger log = LoggerFactory.getLogger(JFXShapesSceneController.class);

    @FXML
    private VBox mainContainer;
    @FXML
    private MenuBar menuBar;
    @FXML
    private HBox shapesView;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        initializeShapesView();
    }

    private void initializeShapesView() {
        Color fill = Color.GRAY;

        Rectangle rect = new Rectangle(50, 50);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(fill);

        Circle circle = new Circle(50);
        circle.setFill(fill);

        Ellipse ellipse = new Ellipse();
        ellipse.setRadiusX(60);
        ellipse.setRadiusY(40);
        ellipse.setFill(fill);

        Polygon polygon = new Polygon();
        polygon.setFill(fill);
        polygon.getPoints().addAll(
                0.0, 0.0,
                50.0, 30.0,
                10.0, 60.0);

        Polygon crossEdgePolygon = new Polygon();
        crossEdgePolygon.setFill(fill);
        crossEdgePolygon.getPoints().addAll(
                0.0, 0.0,
                50.0, 0.0,
                0.0, 50.0,
                50.0, 50.0,
                30.0, -10.0,
                20.0, 70.0);

        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(
                0.0, 0.0,
                50.0, 30.0,
                10.0, 60.0);
        polyline.setFill(fill);

        shapesView.setSpacing(10);
        shapesView.setPadding(new Insets(20));
        shapesView.setAlignment(Pos.CENTER);
        shapesView.getChildren().addAll(
                wrap3(rect, "Rounded Rectangle"),
                wrap3(circle, "Circle"),
                wrap3(ellipse, "Ellipse"),
                wrap3(polygon, "Polygon"),
                wrap3(crossEdgePolygon, "Crossed Edge Polygon"),
                wrap3(polyline, "Polyline"));
    }

    private StackPane wrap(Node node, String description) {
        Label label = new Label(description);
        StackPane.setAlignment(node, Pos.TOP_CENTER);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(node, label);
        return stackPane;
    }

    private BorderPane wrap2(Node node, String description) {
        Label label = new Label(description);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(node);
        borderPane.setBottom(label);
        return borderPane;
    }

    private GridPane wrap3(Node node, String description) {
        GridPane gridPane = new GridPane();
        GridPane.setVgrow(node, Priority.ALWAYS);

        log.debug("### entered wrap3(): node: {}, description: {}", node.getClass(), description);

        /*
         * setup node.
         */
        GridPane.setConstraints(node, 0, 0);
        GridPane.setHalignment(node, HPos.CENTER);
        GridPane.setHgrow(node, Priority.ALWAYS);

        /*
         * setup label.
         */
        Text text = new Text(description);
        text.setBoundsType(TextBoundsType.VISUAL);

        TextFlow textFlow = new TextFlow(text);
        textFlow.setPadding(new Insets(10));
        textFlow.setTextAlignment(TextAlignment.CENTER);

        GridPane.setConstraints(textFlow, 0, 1);
        GridPane.setHalignment(textFlow, HPos.CENTER);
        GridPane.setValignment(textFlow, VPos.BOTTOM);
        GridPane.setVgrow(text, Priority.ALWAYS);

        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setGridLinesVisible(true);

        gridPane.getChildren().addAll(node, textFlow);

        return gridPane;
    }

}
