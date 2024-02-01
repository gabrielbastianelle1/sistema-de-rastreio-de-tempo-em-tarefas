#!/bin/bash

mvn compile
mvn exec:java -Dexec.mainClass="gabriel.App"