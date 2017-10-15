#!/bin/sh

#
# usage example:
#  echo '(5+3)*9' | ./test-rig.sh expression -gui
#

mvn compile exec:java -Dexec.mainClass="org.antlr.v4.gui.TestRig" -Dexec.args="de.mazdermind.playground.antlrtwig.grammar.Twig $*"
