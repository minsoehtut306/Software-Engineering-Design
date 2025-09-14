# Testing Guide

## Introduction
This document outlines the necessary steps to set up the testing environment. The test suite utilizes both the Mockito framework to create mocks for unit testing and the GraphStream library for graph manipulation and visualisation.

## Prerequisites
Ensure that the Java Development Kit (JDK) is installed on your system to compile and run the tests. Additionally, the tests are designed to be run with JUnit, and both Mockito and GraphStream are required for testing.

## Libraries Setup
To run the tests, you must include both Mockito and GraphStream in your project's classpath.

### Maven Users
Add the following dependencies in your `pom.xml` file within the `<dependencies>` section:

```xml
<!-- Mockito -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.11.2</version> 
    <scope>test</scope>
</dependency>

<!-- GraphStream -->
<dependency>
    <groupId>org.graphstream</groupId>
    <artifactId>gs-core</artifactId>
    <version>1.3</version> 
    <scope>test</scope>
</dependency>
```

Run the following command to download and install the dependency:
```shell 
mvn clean install
```

### Gradle Users
Include the following in your build.gradle file under dependencies:

```groovy
// Mockito
testImplementation 'org.mockito:mockito-core:3.11.2' 

// GraphStream
testImplementation 'org.graphstream:gs-core:1.3' 
```

Execute the following command to set up your dependencies:
```shell
./gradlew build
```

### Manual Setup
If you are not using a build tool like Maven or Gradle, you can manually download the JARs for Mockito and GraphStream from their respective GitHub releases pages and include them in your project's build path:

- [Mockito GitHub releases page](https://github.com/mockito/mockito/releases)
- [GraphStream GitHub releases page](https://github.com/graphstream/gs-core/releases)

## Running the Tests
With both Mockito and GraphStream set up, you can run the unit tests. In an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse, you can typically run tests by right-clicking on the test class or test method and selecting the "Run Test" option.

### For Maven:

```shell
mvn test
```

### For Gradle:
``` shell
./gradlew test
```