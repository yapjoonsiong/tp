# Yeo Zi Hao Edwin - Project Portfolio Page

## Overview

### Summary of Contributions

* **Code
  contributed:** [RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=f11&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=yzhedwin&tabRepo=AY2122S1-CS2113T-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false&until=2021-11-03FileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* **Enhancements Implemented** :
    * `Task` class
        * Class made to be used as a base class for `OverallTask` and `GradableTask`
        * Stores the information needed for each task added.
        * Implemented method to determine if task is overdue.
        * Implemented method to check if the task added is valid
        * Added a very readable toString() method.
        * Continued maintenance of the class for dependencies throughout the project.
    * `TaskList` class
        * Allowed class to be inheritable by `OverallTaskList` and `GradableTaskList`
        * Implemented custom comparators to sort the task list with special conditions.
        * Manipulated the user input to get desired data such as `deadline` and `task description`.
        * Added different ways to manipulate `LocalDateTime` data type such as filtering the list
        * for weekly, monthly and yearly tasks.
        * Added checks to see if there is duplication in the task list before adding or modifying tasks.
        * Made the code more defensive by catching errors from erroneous inputs.
        * Enhanced adding of task by automatically updating task deadline when user adds a duplicate task of different
          deadline.
        * Continued maintenance of the class for dependencies throughout the project.
* **Contributions to the UG** :
    * Features: Listing module tasks
    * Features: Adding tasks to module
* **Contributions to the DG** :
    * Design and implementation: Task
    * Design and implementation: TaskList
* **Contributions to team-based tasks** :
    * Managing gradle build file (`build.gradle`) in adding 3rd party libraries and assertions
    * Setting up milestone `v2.1`
    * Contacting teaching team to enquire on doubts about project
* **Review/Mentoring Contributions**
    * Help teammates when faced with technical issues by meeting up and helping to debug, such as debugging failing IO
      redirection tests and failing JUNIT tests.

### Developer Guide Extract

## [Storage](https://se-education.org/addressbook-level3/DeveloperGuide.html#logic-component)

**API** : `command.storage`

The Storage component saves data of NoCap into JSON format, and reads them back into corresponding objects when needed
using a 3rd party library Jackson Databind.

It consists of 2 utility classes StorageDecoder and StorageEncoder. StorageEncoder is used to encode the parent object
`SemesterList` into a JSON file. StorageDecoder decodes a JSON file into a `SemesterList `object

How StorageEncoder works:

![alt_text](../media/StorageEncoderSequenceDiagram.png "image_tooltip")

1. The static method `encodeAndSaveSemesterListToJson() `is called when NoCap data needs to be saved
2. If the save file directory has not been created, it is first created in order to store the save file
3. Similarly, an empty file is created to store the data if it has not been created yet
4. The parent object `SemesterList` is passed to the method to be converted into json format with an `ObjectMapper`
   object from the  `jackson-databind` library

How StorageDecoder works:

![alt_text](../media/StorageDecoderSequenceDiagram.png "image_tooltip")

1. The static method `DecodeJsonToSemesterList() `is called when NoCap data needs to be loaded from the save file
2. If there is no save file available, a new `SemesterList `object is created and returned to the caller
3. Otherwise, an `ObjectMapper` object from the  `jackson-databind` library is used to deserialize the json save file
   into a SemesterList object to be returned to the caller

## OverallTaskList

![alt_text](../media/OverallTaskClassDiagram.png)

_Class diagram for OverallTask and OverallTaskList_

**API** : `task.OverallTasklist`

The OverallTaskList class is instantiated from ListParser only when the end user needs to list available tasks in
a `Semester`.

How the Overall`TaskList` class works:

1. `OverallTask` objects (explained further under `OverallTask`) are stored in an ArrayList `overallTaskList.`
2. Both `Task` and `GradableTask` objects are converted to OverallTask objects first before being inserted into
   OverallTaskList.
3. When the `OverallTaskList` object is instantiated, a `ModuleList `object from a semester is passed to its
   constructor.

![alt_text](../media/OverallTaskListConstructorSequenceDiagram.png "image_tooltip")

4. The constructor calls the method `addAllModuleListTasks(module list)` which adds all the tasks in the module list
   into `OverallTaskList`.
5. Once the object is instantiated, the following methods can be called to sort and print the tasks in the
   ArrayList `overallTaskList`. All sorting and filtering is done via `Java Streams`, and method details are omitted.

* `sortByDateAndPrint() - Print all tasks sorted by deadline`
* `sortByStatusAndPrint() - Print all tasks sorted by status(done)`
* `printWeeklyTasks() - Print tasks due in a week`
* `printMonthlyTasks() - Print tasks due in a month`
* `printYearlyTasks() - Print tasks due in a year`
* `printAllTasks() - Print all tasks without sorting`

Notes about `OverallTaskList`

* Once `ListParser` is done using the object, it is deleted and the task list is not stored anywhere. The reason for
  this is to reduce coupling between objects and remove the need to update separate task lists whenever tasks are added
  to `Modules`.

## OverallTask

**API** : `task.OverallTask`

`OverallTask` objects are stored in a OverallTaskList object when the end user needs to list available tasks in
a `Semester`. It stores information from `GradableTask/Task `objects together with their module name.

`OverallTask` object stores the following for each task:

1. `description`
2. `Date`
3. `isDone`
4. `isLate`
5. `Deadline`
6. `isGradable`
7. `Weightage`
8. `moduleName`

How the `OverallTask` component works:

1. It inherits from `Task`, with additional attributes `isGradable`, `Weightage` and `moduleName`.
2. The attributes `isGradable`, `Weightage`are added to provide more information for gradable tasks, while `moduleName`
   is added to display module information.
3. It can be instantiated with 2 different constructors:

* `OverallTask(task: Task, moduleName: String)` - Instantiates using a `Task` object <br/>
  ![alt_text](../media/OverallTaskConstructorTaskSequenceDiagram.png "image_tooltip")
* `OverallTask(gradableTask:GradableTask, moduleName: String)` - Instantiates using a `GradableTask `object <br>
  ![alt_text](../media/OverallTaskConstructorGradableTaskSequenceDiagram.png "image_tooltip")

4. During instantiation, information from `Task/GradableTask` objects are added to the `OverallTask` object together
   with their `moduleName.`
5. Calling the  `toString()` method generates a string containing task information together with its `moduleName`.

# Appendix A: Product Scope

**Target User Profile:**

* NUS student
* is reasonably comfortable using CLI apps
* can type fast
* prefers typing to mouse interactions
* prefer desktop apps over other types

**Value Proposition:**

A centralized platform which allows NUS Students to carry out their learning management without needing to frequently
switch between multiple tools or applications such as NUSMods, Luminus, Sticky Notes etc.

## User Guide Extract

### Listing all tasks : `list task`

Shows a list of all tasks across modules

Additional format: `list task <optional argument>`

By default, all tasks in the current semester are listed, but this can be customised by adding optional arguments.

&lt;optional argument> includes:

* sortbydate - Sort tasks by due date.
* sortbystatus - Sort tasks by status, displaying unfinished tasks first.
* gradable - list gradable tasks only.
* normal - list non-gradable tasks only.
* w - list tasks due within the next week.
* m - list tasks due within the next month.
* y - list tasks due within the next year.

Tasks are listed in the format:

[Module Code][Gradable][Lateness][Done] &lt;description> by: &lt;deadline> [Weightage]

* [Module Name] - Name of the module
* [Gradable] - Shows ‘G’ if the task is gradable, and ‘ ‘ if the task is non-gradable.
* [Lateness] - Shows ‘LATE’ if the task is overdue. Only shows up for overdue tasks
* [Done] - Shows ‘X’ if the task is done, and ‘ ‘ if the task isn’t done yet.
* &lt;description> - Description of the task
* &lt;deadline> - Deadline of the task
* [Weightage] - Weightage of the task, if it is gradable. Only shows up for gradable tasks.

Example task:

`[CS2132][G][ ] Assignment by: 16 Dec 2021 12:00 AM [Weightage: 50%]`

This is a task belonging to the module CS2132 that is gradable and has not been done yet. It is due on 16 Dec 2021 12:00
AM, and has a weightage of 50%.

Example commands with expected output:

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

For optional arguments w, m and y, overdue tasks are listed together with the weekly/monthly/yearly tasks regardless of
due date as a reminder that the user has forgotten to do the task.

### Saving data

Program data is saved whenever data is added or modified. The process is done automatically, so no user input is needed
for this.

### Loading data

Program data is loaded from the data folder during startup of the program. If the program can detect the data file
successfully, the data is loaded and the following message should appear:

```
Data loaded successfully
Welcome to NoCap
```

On the other hand, if no data file can be found, the program starts with an empty template, and the following message
should appear:

```
No save file found, starting with an empty template
Welcome to NoCap
```