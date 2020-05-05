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
package org.tjc.jfx.jfxspreadsheet;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author tjclancy
 * @version $Id: $Id
 */
public class JFXSheetsSceneController {

    private static final int NUMBER_OF_COLUMNS = 100;
    private static final int NUMBER_OF_ROWS = 1000;

    @FXML
    private Menu editMenuItem;
    @FXML
    private Menu fileMenuItem;
    @FXML
    private Menu helpMenuItem;
    @FXML
    private VBox mainWindow;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<String> sheetsView;
    @FXML
    private HBox statusBar;

    private Scene scene;

    private ObservableList<Person> teamMembers;
    private List<Person> members;
    private final Supplier<String> emptyStringSupplier = () -> "";
    private Supplier<String> randomStringSupplier;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        members = Person.generateExampleList();
        teamMembers = FXCollections.observableArrayList(members);

        Random r = new Random();
        randomStringSupplier = () -> {
            int next = r.nextInt(NUMBER_OF_COLUMNS);
            return Integer.toString(next);
        };

        initializeSpreadsheetView();
    }

    /**
     * <p>
     * Setter for the field <code>scene</code>.</p>
     *
     * @param scene a {@link javafx.scene.Scene} object.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void initializeSpreadsheetView() {
//        sheetsView.setItems(teamMembers);
//        sheetsView.setEditable(true);
//        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
//        firstNameCol.setCellValueFactory(p -> p.getValue().firstNameProperty());
//
//        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
//        lastNameCol.setCellValueFactory(p -> p.getValue().lastNameProperty());
//
//        sheetsView.getColumns().setAll(firstNameCol, lastNameCol);
//        sheetsView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//        sheetsView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        buildSheetsView();

//        autoResizeColumns(sheetsView);
//        sheetsView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
//        sheetsView.getColumns()
//                .forEach((column) -> {
//                    column.setMinWidth(150);
//                    column.setMaxWidth(150);
//                });
//        sheetsView.setColumnResizePolicy((param) -> true);
//        Platform.runLater(() -> customResize(sheetsView));
//        Platform.runLater(() -> autoFillColumn(sheetsView, 0));
        //sheetsView.getColumns().forEach(col -> col.setMinWidth(200));
//        sheetsView.getItems().addAll("a", "b", "c");
//        for (int i = 0; i < 100; i++) {
//            TableColumn<String, String> col = new TableColumn<>(Integer.toString(i));
//            sheetsView.getColumns().add(col);
//
//        }
    }

    private void buildSheetsView() {
        sheetsView.getColumns().clear();
        sheetsView.setEditable(true);
        sheetsView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            TableColumn<String, String> col = new TableColumn<>(Integer.toString(i));
            sheetsView.getColumns().add(col);
        }

        Stream<String> s = Stream.generate(randomStringSupplier);

        List<String> items = s.limit(NUMBER_OF_COLUMNS).collect(Collectors.toList());
        items.forEach(System.out::println);
        sheetsView.setItems(FXCollections.observableArrayList(items));

    }

    private static void autoResizeColumns(TableView<?> table) {
        //Set the right policy
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach((column) -> {
            //Minimal width = columnheader
            Text t = new Text(column.getText()) {
            };
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < table.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
            }
            //set the new max-widht with some extra space
            column.setPrefWidth(max + 10.0d);
        });
    }

    private static void autoFillColumn(TableView<?> table, int col) {
        double width = 0;
        for (int i = 0; i < table.getColumns().size(); i++) {
            if (i != col) {
                width += table.getColumns().get(i).getWidth();
            }
        }
        table.getColumns().get(col).setPrefWidth(table.getWidth() - width);
    }

    private static void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns()
                        .size()));
            });
        }
    }

}
