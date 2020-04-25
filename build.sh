#!/bin/bash

bash -c "rm -rf bin; mkdir bin"
bash -c "javac -classpath 'src' -d bin src/com/tyro/chessboard/*.java"
bash -c "ant -buildfile chessboard.xml"

bash -c "javac -classpath 'src' -d bin src/com/tyro/autotest/*.java"
bash -c "ant -buildfile chessboardtester.xml"

