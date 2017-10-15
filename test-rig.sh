#!/bin/sh

#
# usage example:
#  echo '(5+3)*9' | ./test-rig.sh expression -gui
#

mvn compile
export CLASSPATH="./target/classes:$HOME/.m2/repository/org/antlr/antlr4-runtime/4.7/antlr4-runtime-4.7.jar:$CLASSPATH"
echo $CLASSPATH
java org.antlr.v4.gui.TestRig de.mazdermind.playground.antlrtwig.grammar.Twig "$@"
