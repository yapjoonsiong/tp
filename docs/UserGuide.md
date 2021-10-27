# User Guide

## Introduction

NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI).
If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect 
app for NUS students!


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

### **Command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user. \
  e.g. `add MODULENAME` MODULENAME is the name of the module supplied by the user.
* Items in curved brackets describe the input format. \
  e.g.` MODULENAME addtask DESCRIPTION /by DATE(DDMMYY)` DDMMYY refers to the date-month-year of the description.
* Parameters must be in the exact order as seen in the user guide. \
  e.g. if the command specifies `CS2113 addclass tutorial /on Wednesday /at 23:00, CS2113 addclass /at 23:00 tutorial 
/on Wednesday` is not acceptable.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `timetable `and `exit`) is not 
acceptable. \
e.g. if the command specifies `help 123`, there will be an error.



###Viewing help : `help`

Shows a message explaining how to use NoCap

Format: `help`

###Listing semesters : `list semesters`

Lists all preloaded semesters and their corresponding indexes

Format: `list semesters`

Example output:
```
    1 : Y1S1
    2 : Y1S2
    3 : Y2S1
    4 : Y2S2
    5 : Y3S1
    6 : Y3S2
    7 : Y4S1
    8 : Y4S2
    9 : Y5S1
    10 : Y5S2
```

###Switching semesters : `switch <semester index>`

Switches the currently accessed semester to the corresponding input index (refer to `list semesters` for the index)

Format: `switch SEMESTERINDEX`

Examples:

* `Switch 1`
* `Switch 2`

###Adding module: `add <module>`
Adds a module  
Format: `add MODULENAME`  
Examples:
* `add CS2113T`
* `add MA1508`

###Delete module: `delete <module index> `
Deletes a module corresponding to the input index.(refer to `list module` for indexes )

Format: `delete MODULE INDEX`
Examples:
* `delete 1`
* `delete 2`

###Add class : `/m <module> addclass ***VALID RANGE`

Adds a class to a module

Format: `/m MODULENAME addclass DAY/PERIOD/LOCATION/COMMENTS`

Examples:

* `/m CG1111 addclass MON/0800/E1-03/tutorial`
* `/m MA1508 addclass WED/1000/zoom/lecture`


Note:
* DAY can only take on the following inputs in both uppercase and lowercase: {MON, TUE, WED, THU, FRI, SAT, SUN}
* PERIOD is a 1hr block in 24hr format
* Examples:
<table>
  <tr>
   <td>
Valid
   </td>
   <td>Invalid 
   </td>
  </tr>
  <tr>
   <td>0800
   </td>
   <td>0830
   </td>
  </tr>
  <tr>
   <td>1000
   </td>
   <td>2500
   </td>
  </tr>
  <tr>
   <td>0000
   </td>
   <td>11111
   </td>
  </tr>
</table>

* LOCATION and COMMENTS can only take on a maximum of 16 characters and cannot be empty
* Only one class can be added in any period

###Delete classes: `/m <module> deleteclass <class index>`
Deletes a module corresponding to the input index.(refer to `/m <module name> info` or `list module` for indexes )

Format: `/m <module> deleteclass <class index>`
Examples:
* `/m CS2040C deleteclass 1`
* `/m CG1112 deleteclass 2` 

###View Timetable : `timetable `

Shows the timetable for the currently accessed semester

Format: `timetable`

Example input:

* `timetable`

Example output:
![alt_text](media/timetableExampleOutput.png "image_tooltip")

Note:
* Timetable can only display classes from 0800 to 1700 periods

###Add task : `/m <module> addtask`

###Add gradable task:`/m <module> addgradable`

###Delete tasks: `/m <module> deletetask`

###Listing tasks : `list task`

Shows a list of all tasks

Format: `list task <OPTIONAL ARGUMENT>`

By default, all tasks in the current semester are listed, but this can be customised by adding optional arguments.

&lt;OPTIONAL ARGUMENT> includes:



* sortbydate - Sort tasks by due date.
* sortbystatus - Sort tasks by status, displaying finished tasks first.
* w - list tasks due within the next week.
* m -  list tasks due within the next month.
* y - list tasks due within the next year.

Examples with expected output:

Assuming tasks have been added to modules beforehand:



* `list task`

    ```
    All tasks: 
    1. [CS2132][G][ ] Assignment by: 16 Dec 2021 12:00 AM [Weightage: 50%]
    2. [CS2132][G][LATE][ ] asdf by: 10 Dec 2000 12:00 AM [Weightage: 50%]
    ```


* `list task sortbydate`

  ```
  Tasks sorted by date: 
  1. [CS2132][G][LATE][ ] asdf by: 10 Dec 2000 12:00 AM [Weightage: 50%]
  2. [CS2132][G][ ] Assignment by: 16 Dec 2021 12:00 AM [Weightage: 50%]
  ```


* `list task w`

  ```
  Weekly tasks: 
  1. [CS2132][G][LATE][ ] asdf by: 10 Dec 2000 12:00 AM [Weightage: 50%]
  ```


NOTE:

For optional arguments w, m and y,  overdue tasks are listed together with the weekly/monthly/yearly tasks regardless of due date as a reminder that the user has forgotten to do the task.
Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

* `todo n/Write the rest of the User Guide d/next week`
* `todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

###Add grade : `/m <module> addgrade `
Adds a grade to the module Name 

###Delete grade: `/m <module> deletegrade`

###Add credit : `/m <module> addcredit `

###View CAP : `cap `

Shows the CAP for the currently accessed semester

Format: `cap`

Example input:

* `cap`

Example output:

* `This semester's CAP: 4.25`

###View all CAP : `allcap `

Shows the CAP for all semesters and aggregated CAP

Format: `allcap`

Example input:

* `allcap`

Example output:

```
    Cumulative CAP: 4.25
    Y1S1: 4.25
    Y1S2: 0.0
    Y2S1: 0.0
    Y2S2: 0.0
    Y3S1: 0.0
    Y3S2: 0.0
    Y4S1: 0.0
    Y4S2: 0.0
    Y5S1: 0.0
    Y5S2: 0.0
```

###Exiting the program : `bye`

Exits the program.

Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
