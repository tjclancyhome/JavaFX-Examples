#!/usr/bin/env bash

find ./ -type f -name nb-configuration.xml -exec \
sed -i bak "s/<netbeans.hint.jdkPlatform>>.*<\/netbeans.hint.jdkPlatform>/<netbeans.hint.jdkPlatform>"$@"<\/netbeans.hint.jdkPlatform>/g" '{}' + 


