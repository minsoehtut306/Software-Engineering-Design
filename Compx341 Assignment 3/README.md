Min Soe Htut : 1631938
# Encost Smart Graph Project (ESGP)

This is the ESGP (Encost Smart Graph Project), developed for COMPX341-25A Assignment 3.

# Project Structure
/sec/Main/ → Main application code (Java classes)
/sec/Test/ → JUnit test classes
/resources/ → Dataset files and users.txt
/lib/ → External libraries (GraphStream, JUnit)

## Features
- Role-based access (Community vs Encost user)
- Secure login system
- Default and custom dataset loading
- Device categorisation and visualisation
- Summary statistics output
- Graph visualisation using GraphStream

## How to Run
javac -cp ".;lib/*" -d out sec/Main/*.java sec/Test/*.java
java -cp "out;lib/*" -Dorg.graphstream.ui=swing sec.Main.Main

java -jar lib/junit-platform-console-standalone-1.9.3.jar -cp out --scan-classpath

# libraries
gs-core-2.0.jar
gs-ui-swing-2.0.jar
junit-platform-console-standalone-1.9.3.jar

# Resources
Encost Smart Homes Dataset (small).txt
Encost Smart Homes Dataset (bigger).txt
users.txt
