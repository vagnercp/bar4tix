#!/usr/bin/env sh
##############################################################################
## Gradle start up script for UN*X
##
## NOTE: This is a minimal wrapper launcher that expects gradle/wrapper/gradle-wrapper.jar
## If the JAR is missing, see README.md for instructions to (re)generate it.
##############################################################################
APP_BASE_DIR=$(dirname "$0")
WRAPPER_JAR="$APP_BASE_DIR/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
  echo "ERROR: $WRAPPER_JAR not found."
  echo "See README.md for how to generate the wrapper JAR."
  exit 1
fi
exec "${JAVA_HOME:-}/bin/java" -Dorg.gradle.appname=gradlew \
  -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
