#!/bin/bash

echo "============================================"
echo "  LearnTrack - Compile and Run"
echo "============================================"

# Clean previous build
rm -rf out
mkdir -p out

echo "Compiling..."

# Compile all Java source files
javac -d out \
    src/com/airtribe/learntrack/enums/*.java \
    src/com/airtribe/learntrack/constants/*.java \
    src/com/airtribe/learntrack/exception/*.java \
    src/com/airtribe/learntrack/entity/*.java \
    src/com/airtribe/learntrack/util/*.java \
    src/com/airtribe/learntrack/repository/*.java \
    src/com/airtribe/learntrack/service/*.java \
    src/com/airtribe/learntrack/Main.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "============================================"
    echo ""
    java -cp out com.airtribe.learntrack.Main
else
    echo "Compilation failed. Please check for errors above."
    exit 1
fi
