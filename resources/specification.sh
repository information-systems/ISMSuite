#!/bin/sh

java -jar ../lib/antlr-4.7.1-complete.jar -visitor -o ../src/org/informationsystem/ismodeler/specification/parsing -package org.informationsystem.ismodeler.specification.parsing Specification.g4
