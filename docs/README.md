# ByteBrew User Guide

ByteBrew is a bot used for managing a task list, optimised for use via a Command Line Interface (CLI).

* [Quick Start](#quick-start)
* [Features](#features)
  - [Saving Data](#saving-data)
  - [Add Todo task: `todo`](#add-todo-task-todo)
  - [Add Deadline Task: `deadline`](#add-deadline-task-deadline)
  - [Add Event Task: `event`](#add-event-task-event)
  - [List Tasks: `list`](#list-tasks-list)
  - [Mark Task as Done: `mark`](#mark-task-as-done-mark)
  - [Mark Task as Undone: `unmark`](#mark-task-as-undone-unmark)
  - [Deleting Tasks: `delete`](#deleting-tasks-delete)
  - [Find a Task: `find`](#find-a-task-find)
  - [Print Help Message: `help`](#print-help-message-help)
  - [Exit and Write Data: `bye`](#exit-and-write-data-bye)
* [Frequently Asked Questions (FAQ)](#faq)

## Quick Start

1. Ensure that you have the latest Java 11.
2. Download the latest `ip.jar` file from https://github.com/rouvinerh/ip/releases.
3. Copy the file to the folder you want to use as the home folder for ByteBrew.
4. Open a command terminal (either cmd.exe or bash), `cd` to the folder with `ip.jar` in it, and use `java -jar ip.jar` to run the application.
5. The welcome message for ByteBrew should be printed to the screen.
6. Type commands in the command line and press Enter to execute it. Using help and pressing Enter will print the help message.

```
$ java -jar ip.jar
__________________________________________________
Hello! I'm ByteBrew!
What can I do for you?
__________________________________________________
__________________________________________________
Data file NOT present. Creating one now!
__________________________________________________
```

Some example commands:
* `help`: Prints the help message out.
* `list`: Lists out all tasks recorded.

## Features

### Notes about Command Format

* Words in `UPPER_CASE` are parameters supplied **by the user**.
  - E.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Finish DukeBot`.


### Saving Data

Each time the task list is modified, ByteBrew will automatically save the data to `bytebrew_data.txt`. No other actions are required from the user to save data.

If `bytebrew_data.txt` is found in the same directory, starting the bot will produce the following output:

```
$ java -jar ip.jar
__________________________________________________
Hello! I'm ByteBrew!
What can I do for you?
__________________________________________________
__________________________________________________
Data file present!
__________________________________________________
```

### Add Todo Task: `todo`

Adds a todo task. Does not have specific deadline nor start or end date.

Format: `todo DESCRIPTION`
* All fields must be provided.
* `DESCRIPTION` represents the task description.

Example:
* `todo homework`
* `todo run 5km`

Expected Output:

```
todo homework
__________________________________________________
Added Todo: homework
Total Number of Tasks: 1
__________________________________________________
```

### Add Deadline Task: `deadline`

Adds a deadline task, which has **one** specific deadline.

Format: `deadline DESCRIPTION /by DATETIME`
* `DESCRIPTION` represents the task description.
* `DATETIME` is in `yyyy-mm-dd HHmm` format.
* Note that deadline specified **must NOT be before current time**.

Example:
* `deadline return book /by 2024-08-05 1500`
* `deadline sleep /by 2024-05-05 0100`

Expected Output:

```
deadline sleep /by 2024-05-05 0100
__________________________________________________
Added Deadline: sleep
Total Number of Tasks: 1
__________________________________________________
```

### Add Event Task: `event`

Adds an event task, which has a start time and end time.

Format: `event DESCRIPTION /by STARTTIME /to ENDTIME`
* `DESCRIPTION` represents the task description.
* `STARTTIME` and `ENDTIME` are in `yyyy-mm-dd HHmm` format.
* Note that `STARTTIME` and `ENDTIME` specified **must NOT be before current time**.

Example:
* `event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700`

Expected Output:

```
event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700
__________________________________________________
Added Event: project meeting
Total Number of Tasks: 1
__________________________________________________
```

### List Tasks: `list`

Lists all tasks within the task list.

Format: `list`

Expected Output:

```
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
2. [D] [ ] sleep (by: 2024-05-05 0100)
3. [E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
__________________________________________________
```

### Mark Task as Done: `mark`

Marks a task as done. Task list will use an `X` to denote a task is marked as done.

Format: `mark INDEX`.
* `INDEX` represents the task number. Check `list` to see each number.

Example:
* `mark 1`

Expected Output:

```
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
__________________________________________________
mark 1
__________________________________________________
Nice! I've marked this task as done:
[T] [X] homework
__________________________________________________
list
__________________________________________________
Here's the task list:
1. [T] [X] homework
__________________________________________________
```

### Mark Task as Undone: `unmark`

Marks a task as undone. Task list will use a blank space character to denote a task is marked as undone.

Format: `mark INDEX`.
* `INDEX` represents the task number. Check `list` to see each number.

Example:
* `unmark 2`

Expected Output:

```
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
2. [E] [X] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
__________________________________________________
unmark 2
__________________________________________________
OK, I've marked this task as not done yet:
[E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
__________________________________________________
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
2. [E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
__________________________________________________
```

### Deleting Tasks: `delete`

Deletes a task from the list.

Format: `delete INDEX`
* `INDEX` represents the task number. Check `list` to see each number.

Example: 
* `delete 3`

Expected Output:

```
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
2. [E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
3. [D] [ ] return book (by: 2024-08-05 1500)
__________________________________________________
delete 3
__________________________________________________
Deleted task 3: [D] [ ] return book (by: 2024-08-05 1500)
Number of tasks left: 2
__________________________________________________
list
__________________________________________________
Here's the task list:
1. [T] [ ] homework
2. [E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
__________________________________________________
```

### Find a Task: `find`

Finds a task based on ONE keyword and prints it out.

Format: `find KEYWORD`
* `KEYWORD` can only be 1 word long.

Example:
* `find book`
* `find CS2113`

Expected Output:

```
list
__________________________________________________
Here's the task list:
1. [D] [ ] return book (by: 2024-08-05 1500)
2. [E] [ ] project meeting (from: 2024-08-24 1500 to: 2024-08-24 1700)
3. [T] [ ] borrow book
__________________________________________________
find book
__________________________________________________
Finding matching tasks in list...
1. [D] [ ] return book (by: 2024-08-05 1500)
3. [T] [ ] borrow book
__________________________________________________
find alife
__________________________________________________
Finding matching tasks in list...
Found no tasks with keyword of: 'alife'
__________________________________________________
```

### Print Help Message: `help`

Prints help message.

Format: `help`

Expected Output:

```
help
__________________________________________________
Use either 'todo', 'event' or 'deadline' to add an item to the task list!
Deadline Usage: deadline return book /by 2024-08-05 1500
Event Usage: event project meeting /from 2024-08-24 1500 /to 2024-08-24 1700
Todo Usage: todo borrow book
__________________________________________________
Use 'mark' OR 'unmark' to mark tasks as done or undone!
Mark usage: mark 2
Unmark usage: unmark 3
__________________________________________________
Use 'delete' to delete tasks from the list!
Delete usage: delete 1
__________________________________________________
Use 'find' to search for a task by ONE keyword
Find usage: find book
__________________________________________________
Use 'bye' to end the bot!
__________________________________________________
```

### Exit Bot: `bye`

Exits bot gracefully.

Format: `bye`

Expected Output:

```
bye
__________________________________________________
Bye! Hope to see you again soon!
__________________________________________________
```

## FAQ

1. If I exit the bot using anything other than `bye`, will I lose data?

No. Data will be saved each time the task list is edited.

2. How do I transfer data to other machines?

Copy the `ip.jar` file and `bytebrew_data.txt` to the other machine. Place both of the files in the same directory, and ByteBrew will read the data file.

If `bytebrew_data.txt` is found in the same directory, starting the bot will produce the following output:

```
$ java -jar ip.jar
__________________________________________________
Hello! I'm ByteBrew!
What can I do for you?
__________________________________________________
__________________________________________________
Data file present!
__________________________________________________
```