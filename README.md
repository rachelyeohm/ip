# Nyabot project

Nyabot is a chatbot for the individual project component of CS2103T, by Rachel Yeo Hui Min.


Functions include add, delete and list tasks, marking tasks as done or undone, and loading and saving chatbot data.
Users can also use find to search for tasks with a query.
Users can also find out their schedule for a particular day.
There are three types of tasks, todo, deadline and event. Todo has a task name, deadline has a task name and end time,
event has a task name, start time and end time.

For deadlines and events, dates should be written in yyyy-MM-dd HH:mm.
For example, you can write 2019-12-27 18:00.
For schedules, dates should be written in yyyy-MM-dd (without the time).
If you wish to know the schedule for the current day, you can simply type "schedule today".

Here is the complete list of commands:
todo <todo name>
deadline <deadline name> /by <deadline>
event <event name> /from <start time> /to <end time>
delete <task index>
exit 
find <search query>
list
mark <task index>
unmark <task index>
schedule <date / "today">