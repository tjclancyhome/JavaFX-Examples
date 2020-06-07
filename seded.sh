#!/usr/bin/env bash

#sed s/<artifactId>JavaFX-12-Examples<\/artifactId>/<artifactId>JavaFX-Examples<\/artifactId>/ $1 $2
cp pom.xml pom.xml.copy
sed -l "s/<artifactId>JavaFX-12-Examples<\/artifactId>/<artifactId>JavaFX-Examples<\/artifactId>/" pom.xml > new.pom.xml 


