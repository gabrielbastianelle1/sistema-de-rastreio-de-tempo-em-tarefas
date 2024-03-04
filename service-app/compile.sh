#!/bin/bash

echo "#################################################################"
mvn compile
mvn exec:java -Dexec.mainClass="gabriel.App"