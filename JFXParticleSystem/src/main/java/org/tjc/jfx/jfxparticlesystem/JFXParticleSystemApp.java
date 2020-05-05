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
package org.tjc.jfx.jfxparticlesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tjc.jfx.jfxcomponents.canvaspane.AnimatedCanvasApp;

/**
 * JavaFX JFXParticleSystemApp
 */
public class JFXParticleSystemApp extends AnimatedCanvasApp {
    private static final Logger log = LoggerFactory.getLogger(JFXParticleSystemApp.class);

    private List<Emitter> emitters;
    private final Random random;
    private GraphicsContext gc;
    private ParticleSystemConf globalConf;

    public static void main(String[] args) {
        launch(args);
    }

    public JFXParticleSystemApp() {
        emitters = new ArrayList<>();
        random = new Random();
    }

    @Override
    public void setup() {
        setFrames(50);
        setWidth(1200);
        setHeight(800);
        gc = getGraphicsContext();

        /*
         * Setup configuration pane at the bottom of the simulation.
         */
        GridPane gpConfRoot = buildConfigurationRoot();
        TitledPane tpConf = new TitledPane("Configuration", gpConfRoot);
        setBottom(tpConf);
        gc.getCanvas().setOnMouseClicked((var e) -> {
            Emitter newEmitter;
            if (globalConf.cloneConfProperty().get()) {
                newEmitter = new Emitter(e.getSceneX(), e.getSceneX(), globalConf.clone());
            } else {
                newEmitter = new Emitter(e.getSceneX(), e.getSceneY(), globalConf);
            }
            emitters.add(newEmitter);
        });

        setTitle("Simple Particle System");
    }

    @Override
    public void draw() {
        emitters.forEach((emitter) -> {
            emitter.emit(gc);
        });
    }

    public class Emitter {
        private List<Particle> particles = new ArrayList<>();
        private final double x;
        private final double y;
        private final ParticleSystemConf conf;

        public Emitter(double x, double y, ParticleSystemConf conf) {
            this.x = x;
            this.y = y;
            this.conf = conf;
        }

        public void emit(GraphicsContext gc) {
            for (int i = 0; i < conf.numberOfParticlesProperty().get(); i++) {
                Particle p = new Particle(x, y, conf);
                particles.add(p);
            }
            particles.stream()
                .map((particle) -> {
                    particle.step();
                    return particle;
                })
                .forEachOrdered((particle) -> {
                    particle.show(gc);
                });
            particles = particles.stream().filter(p -> p.getDuration() > 0).collect(Collectors
                .toList());
        }

        public List<Particle> getParticles() {
            return particles;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public ParticleSystemConf getConf() {
            return conf;
        }
    }

    public class Particle {
        private int duration;
        private final int initialDuration;
        private double x;
        private double y;
        private final double yDir;
        private final double xDir;
        private final double size;
        private final double opacity;
        private double currentOpactity;
        private final boolean oscilate;
        private final boolean fadeOut;
        Color color = Color.YELLOW;

        public Particle(double x, double y, ParticleSystemConf conf) {
            this.x = x;
            this.y = y;
            this.oscilate = conf.oscilateProperty().get();
            this.size = conf.sizeProperty().get();
            this.initialDuration = conf.durationProperty().get() + 1;
            this.yDir = random.nextGaussian() * 2.0 - 1.0;
            this.xDir = random.nextGaussian() * 2.0 + -1.0;
            this.opacity = conf.opacityProperty().get();
            this.fadeOut = conf.fadeOutProperty().get();
            this.duration = initialDuration;
            this.currentOpactity = opacity;
            this.color = conf.colorProperty().get();
        }

        public void step() {
            x += xDir;
            y += yDir;
            if (oscilate) {
                x += Math.sin(duration) * 10;
                y += Math.cos(duration) * 10;
            }
            if (fadeOut) {
                currentOpactity = map(duration, 0, initialDuration, 0, opacity);
            }
            duration--;
        }

        public void show(GraphicsContext gc) {
            Color c1 = Color
                .color(color.getRed(), color.getGreen(), color.getBlue(), currentOpactity);
            gc.setFill(c1);
            gc.fillOval(x, y, size, size);
        }

        public int getDuration() {
            return duration;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getyDir() {
            return yDir;
        }

        public double getxDir() {
            return xDir;
        }
    }

    private GridPane buildConfigurationRoot() {
        globalConf = new ParticleSystemConf();
        Button btnClear = new Button("Clear");
        Button btnUndo = new Button("Remove Last");
        ToggleButton tbClone = new ToggleButton("Static Configuration");
        Spinner<Integer> spFrames = new Spinner<>(1, 40, 20);
        Slider sldNumberOfParticles = new Slider(1, 1000, 500);
        Slider sldDuration = new Slider(1, 60, 30);
        Slider sldOpacity = new Slider(0, 1.0, 0.5);
        Slider sldPParticleSize = new Slider(1, 50, 25);
        ColorPicker cbColor = new ColorPicker(Color.WHITE);
        ColorPicker cbBackgrounColor = new ColorPicker(Color.BLACK);
        CheckBox cbOscillate = new CheckBox("Oscillate");
        CheckBox cbFadeOut = new CheckBox("Fade Out");
        spFrames.setPrefWidth(100);
        btnClear.setOnAction(e -> emitters = new ArrayList<>());
        spFrames.valueProperty().addListener((a, b, c) -> frames(c));
        btnUndo.setOnAction(e -> removeLast());
        cbBackgrounColor.valueProperty().addListener((a, b, c) -> background(c));
        globalConf.numberOfParticlesProperty().bind(sldNumberOfParticles.valueProperty());
        globalConf.durationProperty().bind(sldDuration.valueProperty());
        globalConf.oscilateProperty().bind(cbOscillate.selectedProperty());
        globalConf.sizeProperty().bind(sldPParticleSize.valueProperty());
        globalConf.opacityProperty().bind(sldOpacity.valueProperty());
        globalConf.fadeOutProperty().bind(cbFadeOut.selectedProperty());
        globalConf.colorProperty().bind(cbColor.valueProperty());
        globalConf.cloneConfProperty().bind(tbClone.selectedProperty());
        GridPane gp = new GridPane();
        gp.add(new Label("Number of Particles"), 0, 0);
        gp.add(sldNumberOfParticles, 1, 0);
        gp.add(new Label("Particle Duration"), 0, 1);
        gp.add(sldDuration, 1, 1);
        gp.add(new Label("Particle Size"), 2, 0);
        gp.add(sldPParticleSize, 3, 0);
        gp.add(new Label("Initial Opacity"), 2, 1);
        gp.add(sldOpacity, 3, 1);
        gp.add(new Label("Color"), 4, 0);
        gp.add(cbColor, 5, 0);
        gp.add(new Label("Background"), 4, 1);
        gp.add(cbBackgrounColor, 5, 1);
        gp.add(cbOscillate, 6, 0);
        gp.add(cbFadeOut, 6, 1);
        gp.add(new Label("Frames"), 7, 0);
        gp.add(spFrames, 8, 0);
        gp.add(btnClear, 9, 0);
        gp.add(tbClone, 8, 1);
        gp.add(btnUndo, 9, 1);
        gp.setHgap(5);
        gp.setVgap(15);
        return gp;
    }

    private void removeLast() {
        if (!emitters.isEmpty()) {
            emitters.remove(emitters.size() - 1);
        }
    }

}
