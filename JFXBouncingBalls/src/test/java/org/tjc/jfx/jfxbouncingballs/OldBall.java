/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tjc.jfx.jfxbouncingballs;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author tjclancy
 */
public class OldBall {
    private static final Logger log = LoggerFactory.getLogger(OldBall.class);

//    private int x;
//    private int y;
//    private int xDir;
//    private int yDir;
//    private int circ;
//    private Color color;
//    private double width;
//    private double height;
    private DoubleProperty xProp;
    private DoubleProperty yProp;
    private DoubleProperty xDirProp;
    private DoubleProperty yDirProp;
    private DoubleProperty circProp;
    private ObjectProperty colorProp;
    private DoubleProperty widthProp;
    private DoubleProperty heightProp;

    public OldBall(double width, double height) {
        xProp = new SimpleDoubleProperty(0);
        yProp = new SimpleDoubleProperty(0);
        xDirProp = new SimpleDoubleProperty(0);
        yDirProp = new SimpleDoubleProperty(0);
        circProp = new SimpleDoubleProperty(0);
        colorProp = new SimpleObjectProperty<Paint>(Color.BLACK);
        widthProp = new SimpleDoubleProperty(width);
        heightProp = new SimpleDoubleProperty(height);

//        this.width = width;
//        this.height = height;
    }

    public DoubleProperty xProp() {
        return xProp;
    }

    public DoubleProperty yProp() {
        return yProp;
    }

    public DoubleProperty xDirProp() {
        return xDirProp;
    }

    public DoubleProperty yDirProp() {
        return yDirProp;
    }

    public DoubleProperty circProp() {
        return circProp;
    }

    public ObjectProperty<Paint> colorProp() {
        return colorProp;
    }

    public DoubleProperty widthProp() {
        return widthProp;
    }

    public DoubleProperty heightProp() {
        return heightProp;
    }

    public void update() {
        log.debug("### entered update()");

        log.debug("###     xDirProp before: {}", xDirProp);
        if (xProp.get() + circProp.get() > widthProp.get() || xProp.get() < 0) {
            xDirProp.multiply(-1);
        }
        log.debug("###     xDirProp after : {}", xDirProp);


        log.debug("###     yDirProp before: {}", yDirProp);
        if (yProp.get() + circProp.get() > heightProp.get() || yProp.get() < 0) {
            yDirProp.multiply(-1);
        }
        log.debug("###     yDirProp after : {}", yDirProp);

        log.debug("###     xProp before: {}", xProp);
        xProp.add(5 * xDirProp.get());
        log.debug("###     xProp after : {}", xProp);
        log.debug("###     yProp before: {}", yProp);
        yProp.add(5 * yDirProp.get());
        log.debug("###     yProp after : {}", yProp);
        log.debug("### exited update()");
    }

    public void draw(GraphicsContext gc) {
        log.debug("### entered draw()");
        gc.setLineWidth(10);
        gc.setFill(Paint.class.cast(colorProp.get()));
        gc.fillOval(xProp.get(), yProp.get(), circProp.get(), circProp.get());
        gc.setStroke(Color.BLACK);
        gc.strokeOval((double) xProp.get(), (double) yProp.get(), (double) circProp.get(), (double) circProp
            .get());
        log.debug("### exited draw()");
    }

//    public void update() {
//        if (x + circ > width || x < 0) {
//            xDir *= -1;
//        }
//        if (y + circ > height || y < 0) {
//            yDir *= -1;
//        }
//        x += 5 * xDir;
//        y += 5 * yDir;
//    }
//
//    public void draw(GraphicsContext gc) {
//        gc.setLineWidth(10);
//        gc.setFill(color);
//        gc.fillOval(x, y, circ, circ);
//        gc.setStroke(Color.BLACK);
//        gc.strokeOval(x, y, circ, circ);
//    }
    @Override
    public String toString() {
        return "Ball{" + "xProp=" + xProp + ", yProp=" + yProp + ", xDirProp=" + xDirProp + ", yDirProp=" + yDirProp + ", circProp=" + circProp + ", colorProp=" + colorProp + ", widthProp=" + widthProp + ", heightProp=" + heightProp + '}';
    }

}
