# Yap Joon Siong - Project Portfolio Page

## Overview
NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI).
If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect
app for NUS students!

### Summary of Contributions
* **Code contributed:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=yapjoonsiong&tabRepo=AY2122S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)
* **Enhancements Implemented** :
    * `semester` package
        * Consists of utility classes `SemesterList` and `Semester`, where `SemesterList` computes and stores the 
          cumulative CAP of all semesters and also stores 10 `Semester` objects, and `Semester` stores and computes the 
          individual CAP for the semester, while also storing a ModuleList of the Module objects taken during the semester
        * Integrated and modified dependencies (`ModuleList` and `Module`) as the project went on
        * Implemented core functionalities such as `cap` and `allcap`
    * `schedule` package
        * Consists of utility classes `ScheduleList` and `Schedule`, where `ScheduleList` stores all `Schedule` objects,
          and `Schedule` stores all information for any schedule added to `Module`
        * Integrated and modified dependencies (namely `Module`) as the project went on
    * `ModuleList` and `Module` classes
        * Integrated classes and implemented core functionalities such as `timetable` and backend methods to calculate
          `cap` and `allcap`
        * Implemented core backend method to find modules by name
* **Contributions to the UG** :
    * Features: List Semesters
    * Features: Switching Semesters
    * Features: Adding a class
    * Features: Deleting a class
    * Features: Add credits to a module
    * Features: View CAP
    * Features: View all CAP
* **Contributions to the DG** :
    * Acknowledgements
    * Design and implementation: semester
    * Appendix C: Non Functional Requirements
* **Contributions to team-based tasks** :
    * Managing collaborative google drive folder
    * Setting up conference group to facilitate meetings
* **Review/Mentoring Contributions**
    * Help teammates when faced with technical issues by meeting up and helping to debug functional problems.
    * Integrated most core functional classes together at the start of the project to release a Minimum Viable Product
    