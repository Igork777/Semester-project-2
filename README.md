## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Implemented functionality](#implemented-functionality)

## General info
This project is social media for musicians written. 
The project uses a client-server architecture.
The backend is written in Java using MVVM and Design patterns.
The GUI is written in JavaFX.
## Technologies
Project is created with:
* Java
* JavaFX
* JUnit
* RMI
* SQLite
## Setup
To run this project

1) Download Intelij Idea
2) Install SDK 11 or higher
3) Install JavaFX for your operational system (the one which is provided in the project is for Windows machines)
4) Go to Settings > Build, Execution, Deployment > Compiler > Java Compiler and make sure target bytecode is the same for SEP2 and DreamTeam projects and equal or higher than the 11th version.
5) Go to file > Project structure > Project Settings > Modules > Sources  and make sure that module SDK is the same as the Java Compiler version
6) Go to file > Project structure > Project Settings > Modules > Dependencies and make sure that module SDK is the same as the Java Compiler version
7) From  Project structure > Project Settings > Modules > Dependencies, make sure that JavaFX, JUnit, and SQLite exist and are chosen. If not, choose them and press the button, apply
8) If you cannot run the client, which is RunBondInBand.java got to file > Project structure > Platform Settings > Global Libraries, then press + sign, choose Java, then find your JavaFX folder and select the lib folder. Then click apply.
9) You are ready to use our fantastic app =)

## Implemented functionality
* As a user, I want to be able to register on
the platform, so that I can login and use the
features offered by the application.
* As a user, I want to be able to listen to
music ever since I open the application in
order to make the time spent using the
application more enjoyable.
* As a guest, I want to be able to take the role
of an organizer and/or musician, in order to
gain access to the functionality offered by the
roles
* As a guest, I want to be able to log in on the
platform, so that I can use the features offered
by the application.
* As a guest, I want to be able to see my
profile, so that I can view the information
presented on my profile
* As a guest, I want to be able to see other
musician, organizer and band profiles in order
to see the information presented on the
profiles.
* As a guest, I want to be able to search for
musicians, bands, and organizers by their
specific parameters, so that I can view their
profiles.
* As a musician, I want to be able to create
multiple bands, so that I can become a band
administrator and form a band of musicians to
play with.
* As a musician I want to be able to be a
member of and leave multiple bands so that I
can decide which bands I am involved in.
* As a band administrator, I want to be able
to invite and remove a musician from the
band, so that I can manage the members of the
band.


