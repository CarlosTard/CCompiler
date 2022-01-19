#!/bin/bash
cd constructorast
java -cp ../../cup.jar java_cup.Main -parser ConstructorASTExp -symbols ClaseLexica -nopositions ConstructorAST.cup

#java -cp ../cup.jar java_cup.Main -parser Evaluador -symbols ClaseLexica -nopositions Tiny.cup
#mv Evaluador.java ./eval/Evaluador.java
#mv ClaseLexica.java ./eval/
