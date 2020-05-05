package org.tjc.jfx.jfxsandboxfxml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.css.converter.PaintConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Borrowed this class from link below because I just needed a quick grid/graph for my example code.
 *
 * @see <a href="https://eckig.github.io/blog/2014/11/06/resizable-grid-using-canvas">Taken From</a>
 * @author Steffen Eckig (I think)
 * @version $Id: $Id
 */
public class ResizableGrid extends Pane {

    // This is to make the stroke be drawn 'on pixel'.
    private static final double HALF_PIXEL_OFFSET = -0.5;

    private final Canvas canvas = new Canvas();
    private boolean needsLayout = false;

    private final StyleableObjectProperty<Paint> gridColor = new StyleableObjectProperty<Paint>(
            Color.rgb(222, 248, 255)) {

        @Override
        public CssMetaData<? extends Styleable, Paint> getCssMetaData() {
            return StyleableProperties.GRID_COLOR;
        }

        @Override
        public Object getBean() {
            return ResizableGrid.this;
        }

        @Override
        public String getName() {
            return "gridColor";
        }

        @Override
        protected void invalidated() {
            needsLayout = true;
            requestLayout();
        }
    };

    private final StyleableObjectProperty<Number> gridSpacing = new StyleableObjectProperty<Number>(
            15) {

        @Override
        public CssMetaData<? extends Styleable, Number> getCssMetaData() {
            return StyleableProperties.GRID_SPACING;
        }

        @Override
        public Object getBean() {
            return ResizableGrid.this;
        }

        @Override
        public String getName() {
            return "gridSpacing";
        }

        @Override
        protected void invalidated() {
            needsLayout = true;
            requestLayout();
        }
    };

    @SuppressWarnings("OverridableMethodCallInConstructor")
    /**
     * <p>
     * Constructor for ResizableGrid.</p>
     */
    public ResizableGrid() {
        getStyleClass().add("graph-grid");
        getChildren().add(canvas);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren() {
        final int top = (int) snappedTopInset();
        final int right = (int) snappedRightInset();
        final int bottom = (int) snappedBottomInset();
        final int left = (int) snappedLeftInset();
        final int width = (int) getWidth() - left - right;
        final int height = (int) getHeight() - top - bottom;
        final double spacing = gridSpacing.get().doubleValue();

        canvas.setLayoutX(left);
        canvas.setLayoutY(top);

        if (width != canvas.getWidth() || height != canvas.getHeight() || needsLayout) {
            canvas.setWidth(width);
            canvas.setHeight(height);

            GraphicsContext g = canvas.getGraphicsContext2D();
            g.clearRect(0, 0, width, height);
            g.setFill(gridColor.get());

            final int hLineCount = (int) Math.floor((height + 1) / spacing);
            final int vLineCount = (int) Math.floor((width + 1) / spacing);

            for (int i = 0; i < hLineCount; i++) {
                g.strokeLine(0, snap((i + 1) * spacing), width, snap((i + 1) * spacing));
            }

            for (int i = 0; i < vLineCount; i++) {
                g.strokeLine(snap((i + 1) * spacing), 0, snap((i + 1) * spacing), height);
            }

            needsLayout = false;
        }
    }

    private static double snap(double y) {
        return ((int) y) + HALF_PIXEL_OFFSET;
    }

    /**
     * <p>
     * getClassCssMetaData.</p>
     *
     * @return The CssMetaData associated with this class, including the CssMetaData of its super
     *         classes.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    private static class StyleableProperties {

        private static final CssMetaData<ResizableGrid, Paint> GRID_COLOR = new CssMetaData<ResizableGrid, Paint>(
                "-graph-grid-color", PaintConverter.getInstance()) {

            @Override
            public boolean isSettable(ResizableGrid node) {
                return !node.gridColor.isBound();
            }

            @Override
            public StyleableProperty<Paint> getStyleableProperty(ResizableGrid node) {
                return node.gridColor;
            }
        };

        private static final CssMetaData<ResizableGrid, Number> GRID_SPACING = new CssMetaData<ResizableGrid, Number>(
                "-graph-grid-spacing", SizeConverter.getInstance()) {

            @Override
            public boolean isSettable(ResizableGrid node) {
                return !node.gridSpacing.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(ResizableGrid node) {
                return node.gridSpacing;
            }
        };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<CssMetaData<? extends Styleable, ?>>(
                    Pane.getClassCssMetaData());
            styleables.add(GRID_COLOR);
            styleables.add(GRID_SPACING);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }
}
