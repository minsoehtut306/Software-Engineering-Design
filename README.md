# Software Engineering Design – Project Repository  

This repository contains all deliverables for a full **Software Engineering Design project**, structured into four major assignments. Each folder includes documentation, specifications, source code, and test suites.  

---

## Project Overview 
 
This repository documents the full lifecycle of the **Encost Smart Graph Project (ESGP)** — a Java-based console application that visualises smart home device connections and user interactions.  
The system enables **role-based access control**, allowing community users and verified Encost users to load, categorise, and analyse IoT datasets using dynamic graph visualisations powered by **GraphStream**.  

The app supports secure login with hashed authentication, dataset validation, automatic device categorisation, and visual graph rendering of interconnected devices.  
Each development phase — from requirements design to final testing — was executed following structured software engineering principles using **Agile sprint planning**.  

All project documentation, including reports and test plans, was prepared using **LaTeX** for professional formatting and consistent academic presentation.  

---
## Repository Structure  

### 1. **SOFTWARE DESIGN**  
- **Specs:** `COMPX341_25A_Assignment_One_Specifications.pdf`  
- **Report:** `SOFTWARE DESIGN.pdf`  
- Focus: System requirements analysis, high-level design, and architectural planning.  

### 2. **FUNCTIONAL SOFTWARE TEST PLAN**  
- **Specs:** `COMPX341_25A_Assignment_Two_Specifications.pdf`  
- **Report:** `FUNCTIONAL SOFTWARE TEST PLAN.pdf`  
- **Tests:** Unit test implementations (`GraphVisualisation_UnitTests.java`, `UserCategorisation_UnitTests.java`, etc.)  
- Focus: Designing and implementing functional test cases to validate software features.  

### 3. **DEVELOPMENT PLANNING DOCUMENT**  
- **Specs:** `COMPX341_25A_Assignment_Three_Specifications.pdf`  
- **Report:** `DEVELOPMENT PLANNING DOCUMENT.pdf`  
- **Source Code:** Java implementation (`sec/Main/*.java`, `sec/Test/*.java`) including:  
  - Device & category management  
  - Graph management & reporting  
  - User access control  
- **Resources:** Encost Smart Homes datasets, user data files  
- Focus: Detailed development plan and initial implementation of the system.  

### 4. **SOFTWARE MAINTENANCE DOCUMENT**  
- **Specs:** `COMPX341_25A_Assignment_Four_Specifications.pdf`  
- **Report:** `SOFTWARE MAINTENANCE DOCUMENT.pdf`  
- **Source Code:** Full project implementation in `Main/`  
- **Tests:** Comprehensive unit tests (`GitLab_Tests_2/`)  
- **Resources:** CSV datasets for validation and edge-case testing  
- Focus: Extending, maintaining, and testing the system for long-term reliability.  

---

## Languages & Tools  
- **Java** (core implementation & tests)  
- **JUnit** (testing framework)  
- **Batch scripts** (pipeline automation)  
- **Datasets & CSVs** (used for validation and test scenarios)  

---

## How to Run  
1. Open the project in **IntelliJ IDEA / Eclipse / VS Code** with Java 11+.  
2. Add `gs-core-2.0.jar`, `gs-ui-swing-2.0.jar`, and `junit-platform-console-standalone.jar` to the project libraries.  
3. Compile sources from the `Main/` folder.  
4. Run unit tests from `Test/` or `GitLab_Tests_2/`.  
5. Use the `pipeline.bat` script for build/test automation (Windows).  

---

This repo shows the complete lifecycle: **design → test planning → development → maintenance**, demonstrating best practices in software engineering design.  
  
---

### Note

This project was completed as part of the Bachelor of Computer Science degree at the University of Waikato.  
It is published here solely for educational and portfolio purposes, to showcase my skills in software development.  

All code presented is my own work. Course-specific materials such as assignment descriptions or test data are not included to respect university policies.  

## Academic Integrity
Portfolio-only; not intended for reuse in coursework. Removal on request.