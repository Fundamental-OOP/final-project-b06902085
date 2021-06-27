#!/bin/bash

rm -r bin/
javac -cp . -sourcepath src -d bin/ src/*.java