## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is social media for musicians written. 
The project uses client-server architecture.
The backend is written in Java using MVVM and Design patterns.
The GUI is written in JavaFX.
## Technologies
Project is created with:
* Java
* JavaFx
* JUnit
* RMI
## Setup
To run this project

1) Download Intelij Idea
2) Install SDK 11 or higher
3) Install JavaFx for your operational system (the one which is provided in the project is for Windows machine)
4) Go to Settings > Build, Execution, Deployment > Compiler > Java Compiler and make sure target bytecode is the same for SEP2 and DreamTeam projects and it is equal or higher than 11th version.
5) Go to file > Project structure > Project Settings > Modules > Sources  and make sure that module sdk is the same as Java Compiler version
6) Go to file > Project structure > Project Settings > Modules > Dependencies and make sure that module sdk is the same as Java Compiler version
7) From  Project structure > Project Settings > Modules > Dependencies make sure that javafx, JUnit and sqlite exist and chosen. If not choose them and press the button apply
8) In case you cannot run the client, which is RunBondInBand.java got to file > Project structure > Platform Settings > Global Libraries , then press + sign, choose Java then find you JavaFx folder and choose the lib folder. The click apply.
9) You are ready to use our amazing app =)
