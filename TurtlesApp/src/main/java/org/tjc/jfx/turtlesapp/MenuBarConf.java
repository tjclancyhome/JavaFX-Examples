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
package org.tjc.jfx.turtlesapp;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration of the MenuBar.
 *
 * @author tjclancy
 */
public class MenuBarConf {
    private static final Logger log = LoggerFactory.getLogger(MenuBarConf.class);

    private final MenuBar menuBar;
    private ObjectProperty<MenuBar> menuBarProperty;
    private ObjectProperty<Menu> fileMenuProperty;
    private ObjectProperty<Menu> editMenuProperty;
    private ObjectProperty<Menu> viewMenuProperty;
    private ObjectProperty<Menu> windowMenuProperty;
    private ObjectProperty<Menu> helpMenuProperty;
    private ObjectProperty<Menu> debugMenuProperty;

    private ObjectProperty<MenuItem> debugToggleShowBordersProperty;
    private ObjectProperty<MenuItem> debugTurtleHomeProperty;
    private ObjectProperty<MenuItem> debugTurtleForwardProperty;
    private ObjectProperty<MenuItem> debugTurtleTestProperty;

    public MenuBarConf() {
        log.debug("### entered MenuBarConf()");
        menuBar = new MenuBar();
        initialize(menuBar);
        log.debug("### exited MenuBarConf()");
    }

    public MenuBarConf(MenuBar menuBar) {
        log.debug("### entered MenuBarConf(menuBar)");
        this.menuBar = Objects.requireNonNull(menuBar, () -> {
            return "The 'menuBar' argument is null.";
        });
        initialize(menuBar);
        log.debug("### exited MenuBarConf(menuBar)");
    }

    public ObjectProperty<MenuBar> menuBarProperty() {
        return menuBarProperty;
    }

    public MenuBar getMenuBar() {
        return menuBarProperty.get();
    }

    public ObjectProperty<Menu> fileMenuProperty() {
        return fileMenuProperty;
    }

    public Menu getFileMenu() {
        return fileMenuProperty.get();
    }

    public ObjectProperty<Menu> editMenuProperty() {
        return editMenuProperty;
    }

    public Menu getEditMenu() {
        return editMenuProperty.get();
    }

    public ObjectProperty<Menu> viewMenuProperty() {
        return viewMenuProperty;
    }

    public Menu getViewMenu() {
        return viewMenuProperty.get();
    }

    public ObjectProperty<Menu> windowMenuProperty() {
        return windowMenuProperty;
    }

    public Menu getWindowMenu() {
        return windowMenuProperty.get();
    }

    public ObjectProperty<Menu> helpMenuProperty() {
        return helpMenuProperty;
    }

    public Menu getHelpMenu() {
        return helpMenuProperty.get();
    }

    public ObjectProperty<Menu> debugMenuProperty() {
        return debugMenuProperty;
    }

    public Menu getDebugMenu() {
        return debugMenuProperty.get();
    }

    public ObjectProperty<MenuItem> debugTurtleHomeProperty() {
        return debugTurtleHomeProperty;
    }

    public MenuItem getDebugTurtleHome() {
        return debugTurtleHomeProperty.get();
    }

    public ObjectProperty<MenuItem> debugTurtleForwardProperty() {
        return debugTurtleForwardProperty;
    }

    public MenuItem getDebugTurtleForward() {
        return debugTurtleForwardProperty.get();
    }

    public ObjectProperty<MenuItem> debugTurtleTestProperty() {
        return debugTurtleTestProperty;
    }

    public MenuItem getDebugTurtleTest() {
        return debugTurtleTestProperty.get();
    }

    @Override
    public String toString() {
        return "MenuBarConf{" + "menuBar=" + menuBar
            + ", menuBarProperty=" + menuBarProperty
            + ", fileMenuProperty=" + fileMenuProperty
            + ", editMenuProperty=" + editMenuProperty
            + ", viewMenuProperty=" + viewMenuProperty
            + ", windowMenuProperty=" + windowMenuProperty
            + ", helpMenuProperty=" + helpMenuProperty
            + ", debugMenuProperty=" + debugMenuProperty
            + ", debugToggleShowBordersProperty=" + debugToggleShowBordersProperty + '}';
    }

    private void initialize(MenuBar menuBar) {
        menuBarProperty = new SimpleObjectProperty<>(menuBar);
        fileMenuProperty = new SimpleObjectProperty<>(menu("File"));
        editMenuProperty = new SimpleObjectProperty<>(menu("Edit"));
        viewMenuProperty = new SimpleObjectProperty<>(menu("View"));
        windowMenuProperty = new SimpleObjectProperty<>(menu("Window"));
        helpMenuProperty = new SimpleObjectProperty<>(menu("Help"));
        debugMenuProperty = new SimpleObjectProperty<>(menu("Debug"));

        debugToggleShowBordersProperty = new SimpleObjectProperty<>(menuItem("Show Borders"));
        debugTurtleHomeProperty = new SimpleObjectProperty<>(menuItem("Turtle Go Home"));
        debugTurtleForwardProperty = new SimpleObjectProperty<>(menuItem("Turtle Forward"));
        debugTurtleTestProperty = new SimpleObjectProperty<>(menuItem("Turtle Test"));

        menuBar.getMenus().addAll(
            fileMenuProperty.get(),
            editMenuProperty.get(),
            viewMenuProperty.get(),
            windowMenuProperty.get(),
            helpMenuProperty.get(),
            debugMenuProperty.get()
        );

        debugMenuProperty.get().getItems().add(debugToggleShowBordersProperty.get());
        debugMenuProperty.get().getItems().add(debugTurtleHomeProperty.get());
        debugMenuProperty.get().getItems().add(debugTurtleForwardProperty.get());
        debugMenuProperty.get().getItems().add(debugTurtleTestProperty.get());
    }

    private static Menu menu(String menuName) {
        return new Menu(menuName);
    }

    private static MenuItem menuItem(String menuItemName) {
        return new MenuItem(menuItemName);
    }

    private static MenuItem menuItem(String menuItemName, Node node) {
        MenuItem menuItem = menuItem(menuItemName);
        menuItem.setGraphic(node);
        return menuItem;
    }

}
