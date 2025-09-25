@echo off
echo ================================
echo ===       PREPARE STAGE      ===
echo ================================
echo Checking for required JAR files...

set JARS=gs-core-2.0.jar;gs-ui-swing-2.0.jar;gs-algo-2.0.jar;junit-platform-console-standalone-1.8.2.jar

IF NOT EXIST gs-core-2.0.jar (
    echo Missing gs-core-2.0.jar
    exit /b 1
)
IF NOT EXIST gs-ui-swing-2.0.jar (
    echo Missing gs-ui-swing-2.0.jar
    exit /b 1
)
IF NOT EXIST gs-algo-2.0.jar (
    echo Missing gs-algo-2.0.jar
    exit /b 1
)
IF NOT EXIST junit-platform-console-standalone-1.8.2.jar (
    echo Missing junit-platform-console-standalone-1.8.2.jar
    exit /b 1
)

echo ================================
echo ===        BUILD STAGE       ===
echo ================================
javac -cp ".;%JARS%" *.java
IF %ERRORLEVEL% NEQ 0 (
    echo Compilation failed.
    exit /b 1
)
echo Compilation successful.

echo ================================
echo ===         TEST STAGE       ===
echo ================================
java -jar junit-platform-console-standalone-1.8.2.jar -cp ".;%JARS%" --select-class DataSetTest --select-class GraphVisualisationTest
IF %ERRORLEVEL% NEQ 0 (
    echo One or more tests failed.
    exit /b 1
)
echo All tests passed.

echo ================================
echo ===        RELEASE STAGE     ===
echo ================================
git add .
git commit -m "Auto Release: Build + Tests passed"
IF %ERRORLEVEL% EQU 1 (
    echo No changes to commit.
) ELSE (
    echo Release committed.
)

echo ================================================
echo ===        DEPLOY STAGE                   ======
echo ================================================
echo Launching application... 
java -cp ".;gs-core-2.0.jar;gs-ui-swing-2.0.jar;gs-algo-2.0.jar" Main
