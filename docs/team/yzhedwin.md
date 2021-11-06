# Yeo Zi Hao Edwin - Project Portfolio Page

## Overview

NoCap (NC) is a **desktop app for managing modules taken in NUS, optimized for use via a Command Line Interface** (CLI).
If you can type fast, NC can get your module management tasks done faster than traditional GUI apps. It is the perfect
app for NUS students!

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
    * Testing
        * Added a way to test output from print functions by using `ByteArrayOutputStream` and `PrintStream` and
        * verifying the actual and expected outputs.
* **Contributions to the UG** :
    * Features: Listing module tasks
    * Features: Adding tasks to module
* **Contributions to the DG** :
    * Design and implementation: Task
    * Design and implementation: TaskList
* **Contributions to team-based tasks** :
    * Contributed ideas during brainstorming about enhancement and bug fixes.
    * Maintained clean github environment.
* **Review/Mentoring Contributions**
    * Helped teammates debug code in a collaborative manner.

### Developer Guide Extract

## TaskList

**API** : `task.tasklist`

![alt_text](../media/TaskClassDiagram.png)

How the `TaskList` component works:

![](../media/TaskListSequenceDiagram.png)

1. `TaskList` stores all tasks in an `ArrayList<Task>`.
2. When the `addTask()` method is called, the method `getDate()` will return the `date` string from the user input
3. The method `removeDate()` will return the `description` string from the user input by removing the date component in
   the user input.
4. Then store it as a local variable of a `String` type.
5. The `String` variables will then be passed to instantialize a new `Task` object.
6. This `Task` object will then be stored in the `ArrayList` in the `TaskList` object.
7. The methods `weeklyTaskList()`, `monthlyTaskList()` and `yearlyTaskList()` returns an `ArrayList` which contains
   the `Task` objects of deadline within a week, a month and a year respectively.
8. The methods `sortTaskListByDate()`  and `sortTaskListByStatus()` will sort the current `TaskList` object by ascending
   order of `Deadline` and completion status respectively
9. The `ArrayList` returned by the above methods can then be passed to `printTasks()` which will call `toString()` in
   each `Task` object and print to the `Output Stream`.
   <br/><br/>

## Task

**API** : `task.task`

`Task` object stores the following for each task:

1. `description`
2. `date`
3. `isDone`
4. `isLate`
5. `deadline`

How the `Task` component works:

![](../media/TaskSequenceDiagram.png)

1. Whenever the `Task` object is instantiated, the `attributes` listed above will be initialized by the `setter`
   methods: `setDescription()`,  `setDate()`,  `setDone()`, `setLate()` and `setDeadline()`.
2. When calling `printAllTask()`, `printWeeklyTask()`, `printMonthlyTask()` in `OverallTaskList` the
   method  `updateOverdue()`will be called which checks for the truth value of the `boolean` attribute `isDone` and also
   whether the current date and time of the system clock is after the `deadline` of the `Task` object.
3. If `isDone` is `FALSE` and the `deadline` is later than the current date and time, `updateOverdue()` will set the
   attribute `isLate` of the current `Task` object to `TRUE`.
4. Calling the toString converts the task information in the Task object to printable String.

Note:

* The printTask() call in the sequence diagram is a generalised method from: `OverallTaskList#addAllNormalTasks()`
  , `OverallTaskList#addAllGradableTasks()` and `TaskList#printTasks()`
* Any call from the methods above will result in the following sequence in the sequence diagram.
  <br/><br/>

### User Guide Extract

### Add task to module : `/m <module> addtask <description> /by <date> (time)`

* The `date` is in the format of dd/MM/yyyy, dd MM yyyy.
* The `time` is in the format of hhmm.
* The `description` can contain white spaces.

Note:

* If time is omitted, time will default to 0000 hrs.
* If duplicate task exist and has a different deadline, the existing task's deadline will be updated with the new
  deadline.
* If duplicate task has same deadline, new task will be rejected by the program.

Example of usage:

* `/m cs1010 addtask Remember to S/U /by 20/11/2020`

Example of expected output:

![alt_text](../media/AddTaskOutput.jpg)

**Warning** :

* For every month, the program will take in 01 - 31 as an input for the day of the month.
* In the case when the month does not have 31 days, the program will treat any input after the last day of the month
  until 31 as the last day.

Example:

* Date input as `31/02/2021` will be parsed as `28/02/2021` because there are only 28 days in the month of February.
* Date input as `31/04/2021` will be parsed as `30/04/2021` because there are only 30 days in the month of April

### Listing module tasks : `/m <module> list (optional argument)`

Shows a list of task of specified module.

By default, all tasks in the module specified in the current semester are listed, but this can be customised by adding
optional arguments.

(optional argument) includes:

* gradable - Shows a list of gradable tasks in the module.
* sortbydate - Sort tasks by due date, the closest deadline have the higher priority in the list. does not print the
  task list.
* sortbystatus - Sort tasks by status, finished tasks of lower priority. Does not print task list.
* w - list tasks due within the next week.
* m - list tasks due within the next month.
* y - list tasks due within the next year.

Task Prefixes:

* There are 2 prefixes in each Task defined as `[ ]`
* The first prefix is a `LATE` tag. If the task is overdue, the tag will show `[LATE]`
* The second prefix is a `DONE` tag. If the task is marked completed, the tag will show `[X]`
* Format will be as follows `[LATE][DONE] <task description> <date> <time>`

Examples with expected output:
* Removed to satisfy page requirement

NOTE:

* For optional arguments `w`, `m` and `y`, overdue tasks are listed together with the weekly/monthly/yearly tasks
  regardless of due date as a reminder that the user has forgotten to do the task.
* `/m <module> list` does not show gradable tasks.
* To show gradable task in module, have to input optional argument as shown above.