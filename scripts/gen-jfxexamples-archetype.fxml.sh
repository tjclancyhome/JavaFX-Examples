#!/usr/bin/env bash

mvn archetype:generate  \
    -DarchetypeGroupId=org.tjc.jfx  \
    -DarchetypeArtifactId=jfxexamples-archetype-fxml \
    -DarchetypeVersion=0.0.1-SNAPSHOT \
    -DgroupId=org.tjc.jfx   \
    -DartifactId=JFXTestingMyArchetype  \
    -Dversion=0.0.1-SNAPSHOT


