#!/usr/bin/env bash

mvn archetype:generate  \
    -DgroupId=org.tjc.jfx   \
    -DartifactId=JFXTestExamples    \
    -DarchetypeGroupId=org.tjc.jfx  \
    -DarchetypeArtifactId=jfxexamples-archetype-fxml
