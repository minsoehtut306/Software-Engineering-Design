Name : Min Soe Htut 
ID : 1631938

##Files Modified and Created

All the file are in the Main Foler 
Note : I change the name of the foler to ESGP System 2 to Main

### Modified Files
- `GraphVisualisation.java` – Updated to support GraphStream 2.0 syntax.
- `FeatureAccessService.java` – Extended to trigger the updated Task 1 and Task 2 features.
- `DataSet.java` – Refactored to add `printSummaryStatistics()` method.
- `Main.java` – Minor update to route authenticated users to the updated feature access menu.
- `DataSetTest.java`– Added a test for `printSummaryStatistics()`.

### Newly Created Files
- `GraphVisualisationTest.java` – Unit test for graph visualization.
- `pipeline.bat` – CI/CD batch script to automate build, test, and deployment.

---

## Running Task 1 & 2 (Graph Visualization & Summary Statistics)

To compile:
```bash
javac -cp "gs-core-2.0.jar;gs-ui-swing-2.0.jar;gs-algo-2.0.jar" AuthenticationManager.java DataSet.java Device.java DeviceCategory.java FeatureAccessService.java GraphBuilder.java GraphVisualisation.java LoginService.java Main.java User.java UserManager.java UserType.java
```
To Run:
```bash 
java -cp ".;gs-core-2.0.jar;gs-ui-swing-2.0.jar;gs-algo-2.0.jar" Main
```
## Running the CI/CD Pipeline
To Run:
```bash
pipeline.bat
```
### What It Does

Prepare: Checks for required JAR files.

Build: Compiles all .java files using the required GraphStream 2.0 JARs.

Test: Runs DataSetTest and GraphVisualisationTest using the JUnit platform.

Release: Commits changes with an automated message if all tests pass.

Deploy: Launches the application.
