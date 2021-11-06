# Yeat Nai Jie - Project Portfolio Page

## Overview
NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI).
If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect
app for NUS students!

### Summary of Contributions
* **Code contributed:** [Reposense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=naijie2108&tabRepo=AY2122S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements Implemented** : 
  * `storage` package
    * Consists of utility classes `StorageEncoder` and `StorageDecoder`, which deals with encoding and decoding the application data
      into a JSON file.
    * Implemented using a 3rd party library `Jackson Databind` 
    * Spent time learning how to use and integrate the 3rd party library with the application, such as adding appropriate getters and constructors to classes 
      in order to bypass permission issues.
    * Integrated and modified the classes as the project went on, such as learning to deal with non-native
    data types like `LocalDateTime`
  * `OverallTask` and `OverallTaskList` classes
    * Classes used for listing all tasks in a semester
    * Includes methods which allows multiple ways of filtering and sorting of semester tasks.
    * Integrated and modified classes as project went on to fit with other classes
    * Added constructors which construct `OverallTask` from either `Task` or `GradableTask` objects.
    * Added custom comparators for better sorting of tasks
  * `exceptions` package
    * Created a framework for custom exceptions to be used by other team members.
    * Consist of two classes `NoCapExceptions` and `ExceptionMessages`.
* **Contributions to the UG** :
  * Features: Listing all tasks 
  * Features: Saving data section
  * Features: Loading data section
* **Contributions to the DG** :
  * Acknowledgements 
  * Design and implementation: Storage
  * Design and implementation: OverallTaskList and OverallTask
  * Appendix A: Product Scope
  * Appendix D: Glossary
  * Appendix E: Launch
  * Appendix E: Saving/Loading data
  * Appendix E: List tasks in a semester
* **Contributions to team-based tasks** :
  * Managing gradle build file (`build.gradle`) in adding 3rd party libraries and assertions
  * Setting up milestone `v2.1`
  * Contacting teaching team to enquire on doubts about project
  * Keep track of deadlines and milestones.
* **Review/Mentoring Contributions**
  * Help teammates when faced with technical issues by meeting up and helping to debug, 
    such as debugging failing IO redirection tests and failing JUNIT tests.
  
<div style="page-break-after: always;"></div>

## Developer Guide Extract

## OverallTaskList

**API** : `task.OverallTasklist`

![alt_text](../media/OverallTaskClassDiagram.png)

_Class diagram for OverallTask and OverallTaskList_

**Note**: Some methods are omitted from the class diagram to improve clarity

The `OverallTaskList` class is instantiated from `ListParser` only when the end user needs to list available tasks in
a `Semester`.

<div style="page-break-after: always;"></div>
How the `OverallTaskList` class works:

1. `OverallTask` objects (explained further under `OverallTask`) are stored in an ArrayList `overallTaskList.`
2. Both `Task` and `GradableTask` objects are converted to `OverallTask` objects first before being inserted into
   `OverallTaskList`.
3. When the `OverallTaskList` object is instantiated, a `ModuleList` object from a semester is passed to its
   constructor.


![alt_text](../media/OverallTaskListConstructorSequenceDiagram.png "image_tooltip")<br>
4. The constructor calls the method `addAllModuleListTasks(module list)` which converts and adds all the tasks in the
   module list into `OverallTaskList`.
5. Once the object is instantiated, the following methods can be called to sort and print the tasks in the
   ArrayList `overallTaskList`. All sorting and filtering is done via `Java Streams`, and method details are omitted.

    * `sortByDateAndPrint() - Print all tasks sorted by deadline`
    * `sortByStatusAndPrint() - Print all tasks sorted by status(done)`
    * `printWeeklyTasks() - Print tasks due in a week`
    * `printMonthlyTasks() - Print tasks due in a month`
    * `printYearlyTasks() - Print tasks due in a year`
    * `printAllTasks() - Print all tasks without sorting`
    * `printGradableTasks() - Print all gradable tasks`
    * `printNormalTasks() - Print all non-gradable tasks`

Notes about `OverallTaskList`

* Once `ListParser` is done using the object, it is deleted and the task list is not stored anywhere. The reason for
  this is to reduce coupling between objects and remove the need to update separate task lists whenever tasks are added
  to `Modules`.
  <br/><br/>

## User Guide Extract

### Listing all tasks : `list task (optional argument)`

Shows a list of all tasks within the current semester

By default, all tasks in the current semester are listed, but this can be customised by adding optional arguments.

(optional argument) includes:

* sortbydate - Sort tasks by due date.
* sortbystatus - Sort tasks by status, displaying unfinished tasks first.
* gradable - list gradable tasks only.
* normal - list non-gradable tasks only.
* w - list tasks due within the next week.
* m - list tasks due within the next month.
* y - list tasks due within the next year.

Tasks are listed in the format:

`[Module Code][Gradable][Lateness][Done] <description> by: <deadline> [Weightage]`

* [Module Name] - Name of the module
* [Gradable] - Shows `[G]` if the task is gradable, and `[ ]` if the task is non-gradable.
* [Lateness] - Shows `[LATE]` if the task is overdue. Only shows up for overdue tasks
* [Done] - Shows `[X]` if the task is done, and `[ ]` if the task isn’t done yet.
* &lt;description> - Description of the task
* &lt;deadline> - Deadline of the task
* [Weightage] - Weightage of the task, if it is gradable. Only shows up for gradable tasks.

Example tasks:

1. `[CS2132][G][ ] Assignment by: 16 Dec 2021 12:00 AM [Weightage: 50%]`

    * Belongs to the module CS2132
    * Gradable
    * Not done yet
    * Due on 16 Dec 2021 12:00 AM
    * Has a weightage of 50%.

Example commands with expected output:

Assuming tasks have been added to modules beforehand:

* `list task`

    ```
    All tasks: 
    1. [CS2132][G][ ] Assignment by: 16 Dec 2021 12:00 AM [Weightage: 50%]
    2. [CS2132][G][LATE][ ] asdf by: 10 Dec 2000 12:00 AM [Weightage: 50%]
    ```
NOTE:

* For optional arguments `w`, `m` and `y`, overdue tasks are listed together with the weekly/monthly/yearly tasks
  regardless of due date as a reminder that the user has forgotten to do the task.
