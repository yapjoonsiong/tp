# Developer Guide

## *Navigation

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}
<br>
<br>
Third party libraries:
- [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [Jackson Datatype-jsr310](https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310)
- [Jackson Annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations)

# Design & Implementation

## [Parser](https://se-education.org/addressbook-level3/DeveloperGuide.html#logic-component)

**API** : <code>command.parser </code>

The Parser classes is responsible for receiving user input and converting it into commands which are directly passed to respective classes.

The class diagram below is an overview of relationship between Parser classes and other classes.

![alt_text](media/ParserClassDiagram.JPG)

How the parsing works:
* `NoCap` passes the user input to `Parser` which separates the input into useful information such as taskType, taskDescription, Module, etc. 
* When commands include **listing tasks**, the taskDescription is passed to `ListParser` which determines the method of sorting and creates filtered `OverallTaskList` and `TaskList` accordingly.
* Otherwise, the taskDescription is passed to `Command` which calls the corresponding commands in `SemesterList`, `Semester` ,`ModuleList`, `Module` , `Task`, `Gradable Task`. For clarity purposes, associations are shown but dependencies are not.
* `ParserChecks` is a utility class that handles various error checking and string searching methods such as `ParserSearch#getTaskFromIndex()` and `ParserSearch#getTaskFromKeyword()`. `Command` utilizes these methods to verify the Strings before passing them to other classes. 
*In NoCap, Parser verifies the validity of input (Whether it exists in the right format). Input content is verified by individual classes for correctness.*
* `DateParser` handles parsing String into LocalDateTime format and displaying LocalDateTime as String. It is utilized by `Task`. Additional date formats can be added in `DateParser#inputFormatter()`

Below is a step by step example of how the parser receives and decipher a user input. In this example, the user enters `list task sortbydate`.   

The Sequence Diagram below illustrates the process
![alt_text](media/ParserSequenceDiagram.png)
**Note**: The alternate paths are omitted from the diagram for clarity.

Step 1: The User launches the application. `NoCap` creates a new `Parser` instance through the constructor and `Parser` creates `ListParser`.

Step 2: The application waits for User input. User enters `list task sortbydate`. `NoCap` passes the input to `Parser` through `Parser#chooseTask()`.

Step 3: `splitInput` is called for the first time and splits the user input into `list` and `task sortbydate`. 

> **TaskType** is set to `list`, and **TaskDescription** is set to `task sortbydate`. 

**TaskType** matches a possible command String.  

Step 4: `splitInput` is called for the second time and splits the user input into `task` and `sortbydate`.

> **TaskType** is set to `task`, and **TaskDescription** is set to `sortbydate`.

**TaskType** and **TaskDescription** are passed to `ListParser` through `ListParser#overallListParser`.

Step 5: `overallListParser` creates an `OverallTaskList`. Through nested switch cases, **TaskType** and **TaskDescription** are matched, and the corresponding method `OverallTaskList#sortByDateAndPrint()` is called. As the name implies, this method sorts all tasks by date and prints them.

> If **TaskType** does not match, then an error message is displayed. If **TaskDescription** does not match, all tasks are printed by default.

Step 6: The full command is carried out and the application returns to NoCap and waits for new User Input.

The diagram below illustrates the `splitString` process.  

![alt_text](media/splitStringDiagram.JPG)


## [Storage](https://se-education.org/addressbook-level3/DeveloperGuide.html#logic-component)

**API** : `command.storage`

The Storage component saves data of NoCap into JSON format, and reads them back into corresponding objects when needed using a 3rd party library Jackson Databind.

It consists of 2 utility classes StorageDecoder and StorageEncoder. StorageEncoder is used to encode the parent object `SemesterList` into a JSON file. StorageDecoder decodes a JSON file into a `SemesterList `object

How StorageEncoder works:


![alt_text](media/StorageEncoderSequenceDiagram.png "image_tooltip")


1. The static method `encodeAndSaveSemesterListToJson() `is called when NoCap data needs to be saved
2. If the save file directory has not been created, it is first created in order to store the save file
3. Similarly, an empty file is created to store the data if it has not been created yet
4. The parent object `SemesterList` is passed to the method to be converted into json format with an `ObjectMapper` object from the  `jackson-databind` library

How StorageDecoder works:



![alt_text](media/StorageDecoderSequenceDiagram.png "image_tooltip")




1. The static method `DecodeJsonToSemesterList() `is called when NoCap data needs to be loaded from the save file
2. If there is no save file available, a new `SemesterList `object is created and returned to the caller
3. Otherwise, an `ObjectMapper` object from the  `jackson-databind` library is used to deserialize the json save file into a SemesterList object to be returned to the caller
# [semester component](https://se-education.org/addressbook-level3/DeveloperGuide.html#model-component)
-
# [module component](https://se-education.org/addressbook-level3/DeveloperGuide.html#storage-component)
-
# [schedule component](https://se-education.org/addressbook-level3/DeveloperGuide.html#common-classes)
-
# [task component]
-
# [**Implementation**](https://se-education.org/addressbook-level3/DeveloperGuide.html#implementation)


## Semester

**API** : `semester`

The Semester component stores all NoCap data i.e., all Semester objects and cummula average point (CAP) (which are contained in a SemesterList object)



* It consists of 2 utility classes SemesterList and Semester
* SemesterList is used to compute and store the cumulative CAP of all semesters and also stores 10 fixed Semester objects
* Each Semester object stores and computes the individual CAP for the semester, while also storing a moduleList of the modules taken during the semester
* The computation of the CAP for both SemesterList and Semester is automatically done when a grade/credit as added to a module within any semester

## Module List

**API** : module

Data from all the modules are stored in the ModuleList class. This includes:

1. moduleName
2. letterGrade
3. credits
4. points
5. TaskList
6. GradableTaskList
7. ScheduleList

The modules are stored in an ArrayList and ModuleList uses the Module.get(int index) method to access the target Module.

- ModuleList is responsible for printing the Time Table. It accesses different schedules of different mods before constructing a Time Table.
- ModuleList contains getter method find(String input) which returns a module by the same name as the input.

How printing a timetable works:

1. ModuleList first extracts day of week and timeslot information from different schedules.
2. It then prints out the Timetable one line at a time. At the same time it checks if the day of week and the timeslot corresponds to the schedule.

- If day of week and timeslot corresponds, venue and comments information is printed out
- If day of week and timeslot does not correspond, and blank character &quot; &quot; is printed instead.

## ScheduleList

**API** : schedule

ScheduleList consists of all data for the schedule for the module.

This includes:

day of week

timeslot

venue

comments

How ScheduleList works:

1. An empty ScheduleList is created when a module is constructed.
2. When addClass is called in module , ScheduleList parses the input from the user and splits the information into the relevant information. The information is then used to generate a new instance of Schedule which is then added to the list.
3. toString() prints out all relevant schedule information in a list format. This is done by going through the list and printing Schedule one after another.

Notes about ScheduleList

- ScheduleList checks that the input for the day of the week is only from the list of possible days: MON, TUE, WED, THU, FRI, SAT ,SUN. All other inputs will result in an exception being thrown.
- When a new Schedule class is called, ScheduleList ensures that the length of venue and comments are less than 16 characters in length. This is to ensure that it fits within its time slot within the Timetable when printed.

## TaskList

**API** : `task.tasklist`

How the `TaskList` component works:



1. `TaskList` stores all tasks in an `ArrayList&lt;Task>`.
2. When the `addTask()` method is called, the `getDate()` and `removeDate()` return the `date` and `description` component of the user input respectively and store it as a local variable of a `String` type.
3. The `String` variables will then be passed to instantialize a new `Task` object.
4. This `Task` object will then be stored in the `ArrayList` in the `TaskList` object.
5. The methods `weeklyTaskList()`, `monthlyTaskList` and `yearlyTaskList()` returns an `ArrayList` which contains the `Task` objects of deadline within a week, a month and a year respectively.
6. The methods `sortTaskListByDate()`  and `sortTaskListByStatus()` will sort the current `TaskList` object by ascending order of `Deadline` and completion status.
7. The `ArrayList` returned by the above methods can then be passed to `printTasks()` which will call `toString()` in each `Task` object and print to the `Output Stream`.


## Task

**API** : `task.task`

`Task` object stores the following for each task:



1. `description`
2. `Date`
3. `isDone`
4. `isLate`
5. `deadline`

How the `Task` component works:



1. Whenever the `Task` object is instantiated, the `attributes` listed above will be initialized by the `setter` methods: `setDescription()`,  `setDate()`,  `setDone()`, `setLate()` and `setDeadline()`.
2. When calling `printAllTask()`, `printWeeklyTask()`, `printMonthlyTask()` in `OverallTaskList` the method  `updateOverdue()`will be called which checks for the truth value of the `boolean` attribute `isDone` and also whether the current date and time of the system clock is after  the `deadline` of the `Task` object.
3. If `isDone` is `FALSE` and the `deadline` is later than the current date and time, `updateOverdue()` will set the attribute `isLate` of the current `Task` object to `TRUE`.
4. Calling the `toString()` method of the` Task` object will call `createLateIcon()` ,` createStatusIcon()` , 



## OverallTaskList


![alt_text](media/OverallTaskClassDiagram.png)


_Class diagram for OverallTask and OverallTaskList_

**API** : `task.OverallTasklist`

The OverallTaskList class is instantiated from ListParser only when the end user needs to list available tasks in a `Semester`. 

How the Overall`TaskList` class works:
1. `OverallTask` objects (explained further under `OverallTask`) are stored in an ArrayList `overallTaskList.`
2. Both `Task` and `GradableTask` objects are converted to OverallTask objects first before being inserted into OverallTaskList.
3. When the `OverallTaskList` object is instantiated, a `ModuleList `object from a semester is passed to its constructor.

![alt_text](media/OverallTaskListConstructorSequenceDiagram.png "image_tooltip")

4. The constructor calls the method `addAllModuleListTasks(module list)` which adds all the tasks in the module list into `OverallTaskList`.
5. Once the object is instantiated, the following methods can be called to sort and print the tasks in the ArrayList `overallTaskList. `All sorting and filtering is done via `Java Streams`, and method details are omitted.
* `sortByDateAndPrint() - Print all tasks sorted by deadline`
* `sortByStatusAndPrint() - Print all tasks sorted by status(done)`
* `printWeeklyTasks() - Print tasks due in a week`
* `printMonthlyTasks() - Print tasks due in a month`
* `printYearlyTasks() - Print tasks due in a year`
* `printAllTasks() - Print all tasks without sorting`

Notes about `OverallTaskList`
* Once `ListParser` is done using the object, it is deleted and the task list is not stored anywhere. The reason for this is to reduce coupling between objects and remove the need to update separate task lists whenever tasks are added to `Modules`.
## OverallTask

**API** : `task.OverallTask`

`OverallTask` objects are stored in a OverallTaskList object when the end user needs to list available tasks in a `Semester`. It stores information from `GradableTask/Task `objects together with their module name.

`OverallTask` object stores the following for each task:

1. `description`
2. `Date`
3. `isDone`
4. `isLate`
5. `Deadline`
6. `isGradable`
7. `Weightage `
8. `moduleName`


How the `OverallTask` component works:

1. It inherits from `Task`, with additional attributes `isGradable, Weightage `and `moduleName. `
2. The attributes `isGradable, Weightage `are added to provide more information for gradable tasks, while `moduleName` is added to display module information.
3. It can be instantiated with 2 different constructors:
    * `OverallTask(task: Task, moduleName: String)` - Instantiates using a `Task` object <br/>
      ![alt_text](media/OverallTaskConstructorTaskSequenceDiagram.png "image_tooltip") 
    * `OverallTask(gradableTask:GradableTask, moduleName: String)` - Instantiates using a `GradableTask `object <br>
      ![alt_text](media/OverallTaskConstructorGradableTaskSequenceDiagram.png "image_tooltip") 
   
4. During instantiation, information from `Task/GradableTask` objects are added to the `OverallTask` object together with their `moduleName.`
5. Calling the  `toString()` method` `generates a string containing task information together with its `moduleName.`


{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


# Appendix A: Product Scope

# Appendix B: User Stories

# Appendix C: Non Functional Requirements

# Appendix D: Glossary

# Appendix E: Instructions for Manual Testing
