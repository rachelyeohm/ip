# Nyabot User Guide

![Alt text](https://rachelyeohm.github.io/ip/Ui.png "Product screenshot")

Nyabot is a basic task bot that also says nya.

## Adding todo: `todo`

Add todo task to the task list. Used for a task without start or end time.

Example: `todo IP task`

Expected outcome: confirmation message of adding todo.

```
I've added this task nya!
[T][] IP task
Nyow you have 1 task(s) in the list.
```

## Adding deadline: `deadline`

Add deadline task to the task list. Used for a task with an end time.

End time should be in the format dd/MM/yyyy HH:mm.

Example: `deadline IP task /by 20/09/2024 16:00`

Expected outcome: confirmation message of adding deadline.

```
I've added this task nya!
[D][] IP task (by: 20/09/2024 16:00)
Nyow you have 1 task(s) in the list.
```

## Adding event : `event`

Add event task to the task list. Used for a task with a start time and end time.

End time should be in the format dd/MM/yyyy HH:mm.

Example: `event CS2103T tutorial /from 20/09/2024 12:00 /to 20/09/2024 13:00`

`event CS2103T tutorial /to 20/09/2024 13:00 /from 20/09/2024 12:00`

Expected outcome: confirmation message of adding event.

```
I've added this task nya!
[E][] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
Nyow you have 1 task(s) in the list.
```

## Viewing event : `list`

View events in task list.

Example: `list`

Expected outcome: list of events.

```
1. [E][] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
2. [T][] IP task
```


## Deleting task: `delete`

Delete task from the task list. 

Example: `delete <task ID>`

Expected outcome: confirmation message of deleting event.

```
I've deleted this task nya!
[E][] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
Nyow you have 1 task(s) in the list.
```

## Mark event: `mark`

Marks task as done.

Example: `mark 1`

Expected outcome: confirmation message of marking event.

```
I've marked this task done for you nya!
[E][X] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
```


## Unmark event: `unmark`

Marks task as not done.

Example: `unmark 1`

Expected outcome: confirmation message of unmarking event.

```
I've marked this task undone for you nya!
[E][] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
```

## Find event: `find`

Finds tasks whose titles contains the search query.

Example: `find CS2103T tut`

Expected outcome: list of tasks that contain the title.

```
Here are the matching tyasks in your list nya!
1. [E][] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
```

## Viewing schedule : `schedule`

View schedule for a certain day. Day should be in the format dd/MM/yyyy. 

Alternatively, the day can be the word "today".

Example: `schedule 20/09/2024` or `schedule today`

Expected outcome: schedule for the day.

```
The following tasks are starting today:
1. [E][X] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)

The following tasks are ending today:
1. [D][] IP task (by: 20/09/2024 16:00)
2. [E][X] CS2103T tutorial (from : 20/09/2024 12:00 to: 20/09/2024 13:00)
```

## Save events: `save`

Saves events in a text file.

Example: `save`

Expected outcome: confirmation message of saving.

```
Saved successfully, nya!
```

## Exit: `bye`

Exits program.

Example: `bye`

Expected outcome: confirmation message of exiting, and closing of program. 

```
Byebye, nya!
```
