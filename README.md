
# Electronic GOT PTTK Booklet

Application project for Polish Tourist and Sightseeing Society made within Software Design Subject.

### Authors

- [A. Kłobus](https://github.com/AgnieszkaKlobus12)
- [A. Stecka](https://github.com/A-Stecka)

## Table of content

1. Introduction
2. Project
3. Interfaces
4. Installation
5. Technologies

## Introduction

The Mountain Tourist Badge of the Polish Tourist and Sightseeing Society is a badge established to promote mountain tourism. Obtaining this badge is therefore associated with practicing mountain tourism.

The most popular types of GOT PTTK are:
popular – that has only one grade
small – with three grades: bronze, silver and gold

For trips on which tourists go, they receive points, on the basis of which they can apply for the badge. To get the Popular Badge you need to score 60 points. For a small badge with a bronze grade, you need to score 120 points, a badge with a silver rank of 360 points, and a gold badge of 720 points. If the route of the trip is included in the list of points scored for GOT PTTK, the scoring provided in it should be used. If not, one point is awarded for each kilometer traveled and one point for each 100 meters climbed elevation difference.

The GOT PTTK booklet is the document that entitles you to purchase and wear GOT PTTK badges. The booklet should be filled in by yourself and then verified. An important element of the booklet is to attach a proof of completing the entered route - in the form of photos of stamps with the names of places, PTTK shelters or characteristic landscape elements. It is also available to attach the proof of the route in the form of a screenshot taken from any application that tracks the user's location. Proof of completing the route is not necessary if the mountain tourism leader of PTTK participated in a given trip and confirms this fact with a signature and the number of the leader ID card and, if possible, with a personal stamp.

The routes entered in the GOT PTTK booklet must be confirmed by the PTTK mountain tourism leader. The leader is authorized in a given mountain group and determines whether the tourist actually traveled the entered route on the basis of the attached evidence or the date of the trip. All routes entered in the GOT PTTK booklet must be confirmed by a leader.

Booklets of people applying for GOT PTTK popular and small, are verified by the GOT PTTK Field Verification Departments or the Central Verification Office of GOT PTTK.

The aim of the project is to offer an alternative to the GOT PTTK paper booklet in the form of an electronic system. The system will provide the possibility of entering, modifying and deleting data on sections of dotted routes to GOT PTTK by authorized users, as well as searching and presenting route sections by all users. In addition, the system will enable documenting the routes of trips made as part of acquiring subsequent types and degrees of PTTK GOT.

As part of the project, an application using a database was designed. The designed application was implemented as a native Android mobile application with a local SQLite database.

## Project

In the course of the semester we designed a project of application from scratch, starting with an introduction to a given topic, through writing out requirements and planning implementation part of work by creating diagrams and mock-ups. Code was created using design patterns such as MVVM, Builder, Observer, Repository.
Full documentation of the design process is available in Polish [here](https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/Documentation.pdf). Paradigm file with all diagrams available [here](https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/Diagrams.vpp).

Example diagrams created for projects:

Domain Model
<br><img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/Domain%20Model.png" alt="Domain Model" width="500"/>

Use Case Diagram
<br><img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/Use%20Case%20Diagram.png" alt="Use Case Diagram" width="500"/>

Activity Diagram
<br><img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/Activity%20Diagram.png" alt="Activity Diagram" width="500"/>

For better quality open [Visual Paradigm file](https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/Diagrams.vpp)

To complete the course only part of the final application was required. We implemented some of the functionalities available for three different types of users: PTTK employees, leaders and tourists. 
In the future we want to change the final look of the application and make it fully functional on a smaller scale: as a trip-planning tool for tourists.

## Interfaces

#### PTTK Employee view

<img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/employee1.jpg" alt="Employee1" width="200"/> <img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/employee2.jpg" alt="Employee2" width="200"/> <img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/employee3.jpg" alt="Employee3" width="200"/>

#### Leader view

<img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/leader1.jpg" alt="Leader1" width="200"/> <img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/leader2.jpg" alt="Leader2" width="200"/>

#### Tourist view

<img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/tourist1.jpg" alt="Tourist1" width="200"/> <img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/tourist2.jpg" alt="Tourist2" width="200"/> <img src="https://github.com/AgnieszkaKlobus12/POApp/blob/master/Documentation/ReadMe%20Photos/tourist3.jpg" alt="Tourist3" width="200"/>

## Installation

To install the app, import the project into Android Studio or any IDE of your choice, build Gradle project and install it on device or emulator.

## Technologies

- Kotlin
- SQLite
- Room Library
- JUnit 4
- Espresso
