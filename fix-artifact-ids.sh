#!/usr/bin/env bash

find ./ -type f -name pom.xml -exec sed -i bak "s/<artifactId>JavaFX-12-Examples<\/artifactId>/<artifactId>JavaFX-Examples<\/artifactId>/g" '{}' + 


