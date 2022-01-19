#!/bin/bash

#habria que preguntar lo del unchecked
javac -cp "../cup.jar" -Xlint:-unchecked */*.java 
if [ $# -eq 0 ]
  then
    echo "El primer argumento del script indica el fichero a compilar: ./compTry nombreModulo.cc"
  else
    java -cp ".:../cup.jar" constructorast.Main $1
fi

find . -name "*.class" -type f -delete

# Windows weird
# dir /s /B *.java > src.txt
# javac -cp "../cup.jar" -Xlint:unchecked @src.txt
# java -cp ".:../cup.jar" "constructorast/Main" ./input.txt
