#!/usr/bin/env bash

cd JFXComponents && mvn -e clean install
cd ..
mvn -e "$@" clean package

