# Yam Jin Ee Dmitri - Project Portfolio Page

## Overview
NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI).
If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect
app for NUS students!

### Summary of Contributions
* **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=Dmitri&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=DmitriYam&tabRepo=AY2122S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancement Implemented:** 
  
  1. module package.
  
     *Created base class for module. 
     *Implemented visualisation of module to command prompt.
  
  2. schedule class
    * Implemented visualisation of schedule and schedule list to command prompt.
  
  3. GradableTask GradableTaskList
    * Extended Task class to produce a class that stores gradable components.
    * Created constructors which checked for syntax errors or weightage overflow errors.
  
  4. VisualiseGradable
     * Created a class to better visualise all gradable components by breaking them down into their individual componnents
     and labelling the percentages.
     
* **Contributions to UG:**

    * Features: Add GradableTasks
    * Features: List GradableTasks
    * Features: Add class to module
    * Features: Delete class from module
    * Features: Add credit to module
    * Features: Add grade to module
    * Features: delete grade from module
    * 
* **Contributions to DG:** 

    * Design and implementation of ModuleList and ScheduleList classes.
    * Instruction for testing for features:
        * Add module
        * Add grade
        * Add class
        * Add credit
        * Add gradable
        * Visualise gradable
      
* **Contributions to team-based tasks:**
    * Helped keep track of weekly deadlines and deliverables.
    * Contributed ideas to the team such as extending Tasks for module breakdown.

* **Review/Mentoring Contributions**
  * Helped teammates debug their code when they faced difficulties.
  * Remind the group of internal deadlines.

###Developer Guide Extract

# Module

**API** : `module`

All data related to module is stored in the module class. An Arraylist of Module is used to store and manage the
modules. ModuleList is also responsible for constructing and printing out the Timetable.

![moduleListClassDiagram](../media/moduleListClassDiagram.png)

The modules are stored in an ArrayList and ModuleList uses the Module.get(int index) method to access the target Module.

- ModuleList is responsible for printing the Time Table.
- ModuleList contains the getter method find(String input) which returns a module by the same name as the input.
- Module contains getter and setter methods to change or access its contents.
- When Module is constructed, an empty gradableTaskList, taskList and scheduleList wll be instantiated and stored in
  Module.

Data stored in Module includes:

1. moduleName
2. letterGrade
3. credits
4. points
5. TaskList
6. GradableTaskList
7. ScheduleList

The modules are stored in an ArrayList and ModuleList uses the Module.get(int index) method to access the
target Module.

- ModuleList is responsible for printing the Time Table. It accesses different schedules of different mods before
  constructing a Time Table.
- ModuleList contains getter method find(String input) which returns a module by the same name as the input.

How printing a timetable works:

1. ModuleList first extracts day of week and timeslot information from different schedules.
2. It then prints out the Timetable one line at a time. At the same time it checks if the day of week and the timeslot
   corresponds to the schedule.

- If day of week and timeslot corresponds, venue and comments information is printed out
- If day of week and timeslot does not correspond, and blank character &quot; &quot; is printed instead. =======

# ![modulePrintTimetableSeq](../media/ModuleListseq.png)

How printing a timetable works:

1. When Timetable is called, ModuleList goes into a loop to print out the timetable. ModuleList iterates through the 207
   character long length and the 33 lines which makes up the entire timetable.
2. Each iteration of the loop can result in 1 of 3 cases:
1. It is at a border. When this happens a &quot;#&quot; character is printed to the console which denotes a border.
2. It is empty. When this happens a &quot; &quot;(blank) character is printed to the console.
3. It contains module information. When this happens, getMoudleName() , getModuleLocation() and getModuleComment() is
   called. The information is then printed onto the console.

# ScheduleList

**API** : `schedule`

ScheduleList consists of all data for the schedule for the module.

This includes:

1. `day`
2. `location`
3. `startTime`
4. `comments`

How ScheduleList works:

1. An empty `ScheduleList` is created when a module is constructed.
2. When `addClass` is called in `module` , `ScheduleList` parses the input from the user and splits the information into the
   relevant information. The information is then used to generate a new instance of `Schedule` which is then added to the
   list.
3. `toString()` prints out all relevant schedule information in a list format. This is done by going through the list and
   printing `Schedule` one after another.

Notes about ScheduleList

- ScheduleList checks that the input for the day of the week is only from the list of possible days: `MON`, `TUE`, `WED`, `THU`,
  `FRI`, `SAT` ,`SUN`. All other inputs will result in an exception being thrown.
- When a new `Schedule` class is called, `ScheduleList` ensures that the length of venue and comments are less than 16
  characters in length. This is to ensure that it fits within its time slot within the Timetable when printed.

![scheduleseq](../media/scheduleseq.png)

Adding Schedule to scheduleList

- When addclass() is called, schedule first checks if there is a duplicate schedule currently in the list. This is done
  by going through the whole list and checking if a schedule has the same time slot. If there exists a schedule in the
  same time slot, an error message is printed.
- If it is an empty timeslot, schedule list parses the input and checks for formatting errors within the input.
- A new instance of Schedule is generated and added to the schedule list.
