@ECHO OFF
REM Gradle start up script for Windows
REM NOTE: This is a minimal wrapper launcher that expects gradle\wrapper\gradle-wrapper.jar
SET APP_BASE_DIR=%~dp0
SET WRAPPER_JAR=%APP_BASE_DIR%gradle\wrapper\gradle-wrapper.jar
IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO ERROR: %WRAPPER_JAR% not found.
  ECHO See README.md for how to generate the wrapper JAR.
  EXIT /B 1
)
IF DEFINED JAVA_HOME (
  "%JAVA_HOME%\bin\java.exe" -Dorg.gradle.appname=gradlew -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
) ELSE (
  java -Dorg.gradle.appname=gradlew -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
)
