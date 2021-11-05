# Yeat Nai Jie - Project Portfolio Page

## Overview


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
  
---
## Developer Guide Extract

## Acknowledgements

Third party libraries:

- [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [Jackson Datatype-jsr310](https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310)
- [Jackson Annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations)


## Storage

**API** : `command.storage`

The Storage component saves data of NoCap into JSON format, and reads them back into corresponding objects when needed
using a 3rd party library Jackson Databind.

It consists of 2 utility classes StorageDecoder and StorageEncoder. StorageEncoder is used to encode the parent object
`SemesterList` into a JSON file. StorageDecoder decodes a JSON file into a `SemesterList `object

How the `StorageEncoder` class works:

![alt_text](../media/StorageEncoderSequenceDiagram.png "image_tooltip")

1. The static method `encodeAndSaveSemesterListToJson()` from `StorageEncoder` is called when NoCap data needs to be
   saved
2. If the save file directory has not been created yet, it is first created in order to store the save file
3. Similarly, an empty file is created to store the data if it has not been created yet
4. The parent object `SemesterList` is passed to the method to be converted into a JSON file with an `ObjectMapper`
   object from the  `jackson-databind` library
5. Finally, the data file is saved in a default data directory.

**How the `StorageDecoder` class works:**

![alt_text](../media/StorageDecoderSequenceDiagram.png "image_tooltip")

1. The static method `DecodeJsonToSemesterList()` from `StorageDecoder` is called when NoCap data needs to be loaded
   from the save file
2. If there is no save file available in the default data directory, a new `SemesterList `object is created and returned
   to the caller
3. Otherwise, an `ObjectMapper` object from the  `jackson-databind` library is used to deserialize the JSON save file
   into a `SemesterList` object to be returned to the caller
   <br/><br/>

## OverallTaskList

**API** : `task.OverallTasklist`

![alt_text](../media/OverallTaskClassDiagram.png)

_Class diagram for OverallTask and OverallTaskList_

**Note**: Some methods are omitted from the class diagram to improve clarity

The `OverallTaskList` class is instantiated from `ListParser` only when the end user needs to list available tasks in

a `Semester`.

How the `OverallTaskList` class works:

1. `OverallTask` objects (explained further under `OverallTask`) are stored in an ArrayList `overallTaskList.`
2. Both `Task` and `GradableTask` objects are converted to `OverallTask` objects first before being inserted into
   `OverallTaskList`.
3. When the `OverallTaskList` object is instantiated, a `ModuleList` object from a semester is passed to its
   constructor.
   ![alt_text](../media/OverallTaskListConstructorSequenceDiagram.png "image_tooltip")
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

## OverallTask

**API** : `task.OverallTask`

`OverallTask` objects are stored in a `OverallTaskList` object when the end user needs to list available tasks in
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

1. It inherits from `Task`, with additional attributes `isGradable`, `weightage` and `moduleName`.
2. The attributes `isGradable`, `weightage` are added to provide more information for gradable tasks, while `moduleName`
   is added to display module information.
3. It can be instantiated with 2 different constructors:
    * `OverallTask(task: Task, moduleName: String)` - Instantiates using a `Task` object <br/>
      ![alt_text](../media/OverallTaskConstructorTaskSequenceDiagram.png "image_tooltip")
    * `OverallTask(gradableTask:GradableTask, moduleName: String)` - Instantiates using a `GradableTask `object <br>
      ![alt_text](../media/OverallTaskConstructorGradableTaskSequenceDiagram.png "image_tooltip")

4. During instantiation, information from `Task/GradableTask` objects are added to the `OverallTask` object together
   with their `moduleName` .
5. Calling the `toString()` method generates a string containing task information together with its `moduleName`.
   <br/><br/>

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
<br/><br/>

# Appendix D: Glossary

* **Command Line Interface(CLI)** - A command-line interface (CLI) processes commands to a computer program in the form
  of lines of text(From [Wikipedia](https://en.wikipedia.org/wiki/Command-line_interface)).
* **Mainstream Operating Systems(OS)** - Windows, Linux, Unix, OS-X
  <br/><br/>

# Appendix E: Instructions for Manual Testing

Given below are instructions to test the app manually.

**Note:** These instructions only provide a starting point for testers to work on, testers are expected to do more
exploratory testing.

## Launch

1. Initial launch
    1. Download the JAR file and copy it into an empty folder
    2. Open up your terminal, and navigate to the folder containing the JAR file
    3. Type the following command:
   ```
   java -jar NoCap.jar
   ```
   **Note**: It is important that you navigate to the directory containing the JAR file first before running the
   application, as it may affect the location of the save file.

## Saving/Loading data

1. Automatic saving
    1. Carry out any command that adds/modifies data in the application, e.g `add CS2102`, or simply exit the
       application using the command `bye`.
    2. Expected: A JSON file is created/updated automatically in the data folder located in folder containing the JAR
       file, provided the instructions in `Launch` is followed correctly.
2. No save file exists
    1. Prerequisites: Make sure the data folder does not exist/is deleted from the folder containing the JAR file.
    2. Run the application as stated in `Launch`
    3. Expected: Application starts with an empty template and shows the following message
       ```
       No save file found, starting with an empty template
       Welcome to NoCap
       ```
3. Save file exists
    1. Prerequisites: Make sure that a save file already exists in the data folder that is located in the folder
       containing the JAR file. If not, simply carry out any command that adds/modifies data in the application,
       e.g `add CS2102`(see 1. Automatic Saving), and the save file will be created automatically.
    2. Run the application as stated in `Launch`
    3. Expected: Application loads the save file when starting the application and shows the following message
   ```
   Data loaded successfully
   Welcome to NoCap
   ```
4. Corrupted save file
    1. Prerequisites: Make sure that a save file already exists in the data folder that is located in the folder
       containing the JAR file If not, simply carry out any command that adds/modifies data in the application,
       e.g `add CS2102`(see 1. Automatic Saving), and the save file will be created automatically.
    2. To simulate data corruption, remove lines to cause syntax errors in the JSON file, such as lines containing `{` and `}`.
    3. Run the application
    4. Expected: An error message is shown and application starts with an empty template, showing the message below:
   ```
   Error reading save file, creating new template
   Welcome to NoCap
   ```
## List tasks in a semester

1. List tasks when there are no available tasks
    1. Prerequisites: There should be no tasks added to modules in the current semester yet.
    2. Run the command `list task`
    3. Expected: No tasks are shown and the following message is shown:
       ```
       All tasks: 
       You have no tasks
       ```
2. List tasks with optional arguments
    1. Prerequisites: There should be tasks added to modules in the semester beforehand. Can be checked by
       running `list task`. If there are no tasks in the semester, add in tasks first(including both gradable and
       non-gradable tasks)
    2. Run list task command with optional arguments, as specified in the user guide, e.g. `list task gradable`
    3. Expected: Tasks are shown accordingly, depending on the optional argument
--- 

## User Guide Extract

### Listing all tasks : `list task`

Shows a list of all tasks within the current semester

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

2. `[CS2132][ ][X] Quiz by: 16 Nov 2021 12:00 AM`

    * Belongs to the module CS2132
    * Non-Gradable
    * Done
    * Due on 16 Nov 2021 12:00 AM

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

* For optional arguments `w`, `m` and `y`, overdue tasks are listed together with the weekly/monthly/yearly tasks
  regardless of due date as a reminder that the user has forgotten to do the task.

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
