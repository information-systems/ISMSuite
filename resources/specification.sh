#!/bin/sh

java -jar ../lib/antlr-4.7.1-complete.jar -visitor -o ../src/org/informationsystem/ismodeler/specification/parsing -package org.informationsystem.ismodeler.specification.parsing Specification.g4
# java -jar ../lib/antlr-4.7.1-complete.jar -visitor -o ../src/org/informationsystem/ismodeler/process/cpntools/tokenparsing/ -package org.informationsystem.ismodeler.process.cpntools.tokenparsing CPNToken.g4