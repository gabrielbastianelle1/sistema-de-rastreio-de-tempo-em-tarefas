#!/bin/bash

#watchexec -e java 'mvn compile &&  mvn exec:java -Dexec.mainClass="gabriel.App"'

#nodemon -e java -w src -x "mvn compile && java -cp target/classes gabriel.App"

# echo "app container starting"
# tail -f /dev/null

#nodemon -e java -w src -x "javac -d target/classes src/main/java/gabriel/*.java && java -cp target/classes gabriel.App"

#nodemon -e java -w src -x "./compile.sh"

#nodemon -e java -w src -x "/opt/openjdk-19/bin/java -Xmx512m -classpath /usr/share/java/maven-3/boot/plexus-classworlds-2.6.0.jar -Dclassworlds.conf=/usr/share/java/maven-3/bin/m2.conf -Dmaven.home=/usr/share/java/maven-3 -Dlibrary.jansi.path=/usr/share/java/maven-3/lib/jansi-native -Dmaven.multiModuleProjectDirectory=/app org.codehaus.plexus.classworlds.launcher.Launcher exec:java -Dexec.mainClass=gabriel.App"

./clean-log-files.sh &
nodemon -e java -w src -x "./compile.sh"
