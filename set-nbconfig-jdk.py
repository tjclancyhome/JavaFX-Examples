#!/usr/bin/env python

import os

for folderName, subfolders, filenames in os.walk('./'):
    print('the current folder is ' + folderName)
    
    for subfolder in subfolders:
        print('SUBFOLDER OF ' + folderName + ': ' + subfolder)

    for filename in filenames:
        if filename == 'nb-configuration.xml':
            print('FILE INSIDE ' + folderName + ': ' + filename)
    print('')

