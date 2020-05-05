#!/usr/bin/env bash


jpackager create-image -p /Users/tjclancy/Java/JavaFX/javafx-sdk-12.0.2/lib -i target/JFXSimpleGraph/ -o . -j target/JFXSimpleGraph.jar -n JFXSimpleGraph -m org.tjc.jfx.simplegraph -c org.tjc.jfx.simplegraph.JFXSimpleGraph

