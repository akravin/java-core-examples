#!/bin/bash
BUILD_DIR=target
CLASSES_DIR=$BUILD_DIR/classes

if [ -d $BUILD_DIR ]; then
  rm -r $BUILD_DIR
fi
mkdir -p $CLASSES_DIR

echo "Build muthithreadconsumer..."
find src -name *.java | xargs $JAVA_HOME/bin/javac -d $CLASSES_DIR

cp -r resources $BUILD_DIR

cd $CLASSES_DIR

echo "Creating muthithreadconsumer.jar"

echo "Main-Class: com.akravin.multithreadconsumer.Demo" > ../manifest.txt
find com/akravin/multithreadconsumer -name *.class | xargs $JAVA_HOME/bin/jar -cfm ../muthithreadconsumer.jar ../manifest.txt

cd ../..

echo "Success!"