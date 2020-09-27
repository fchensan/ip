# User Guide

Duke is a **command-line chat bot app that helps you manage your todos, deadlines, and events**

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add a new Todo item : `todo`](#todo---add-a-new-todo-item)
    * [Add a new Deadline item : `deadline`](#deadline---add-a-new-deadline-item)
    * [Add a new Event item : `event`](#event---add-a-new-event-item)
    * [List all items : `list`](#list---list-all-items)
    * [Mark an item as done : `done`](#done---mark-an-item-as-done)
    * [Delete an item : `delete`](#delete---delete-an-item)
    * [Exit out of Duke : `bye`](#bye---exit-out-of-duke)
* [FAQ](#faq)
* [Command Summary](#commmand-summary)

## Quick start
1. Make sure you have Java 11 or above installed.
2. Download the latest `ip.jar`.
3. Copy the file to a folder where you want to run it from.
This folder will contain .
4. Using the terminal, navigate to the directory where the `ip.jar` file is.
5. Run `java -jar ip.jar`
6. You should see a welcome message. It is now ready to go.

## Features 

### `todo` - Add a new Todo item

Format: `todo DESCRIPTION`

Example of usage: 

`todo clean up room`

Expected outcome:

`Added: [T] ✘ clean up room`

### `deadline` - Add a new Deadline item

Adds a new Deadline with a date.

Format: `deadline DESCRIPTION /by yyyy-mm-dd`

Example of usage: 

`deadline submit report /by 2020-10-12`

Expected outcome:

`Added: [D] ✘ submit report (by: 2020-10-12)`

### `Event` - Add a new Event item

Adds a new Event with a date.

Format: `event DESCRIPTION /at yyyy-mm-dd`

Example of usage: 

`event appreciation dinner /at 2020-09-08`

Expected outcome:

`Added: [E] ✘ appreciation dinner (at: 2020-09-08)`

### `list` - List all items

Example outcome:

```
1. [T] ✘ clean up room
2. [D] ✘ submit report (by: 2020-10-12)
3. [E] ✘ appreciation dinner (at: 2020-09-08)
```

### `done` - Mark an item as done

Marks an item as done, given its number as shown in `list`.

Format: `done ITEM_NUMBER`

Example of usage: 

```
>> list
1. [T] ✘ clean up room
2. [D] ✘ submit report (by: 2020-10-12)
3. [E] ✘ party (at: 2020-09-11)
____________________________________________________________
>> done 2
Ok! "submit report" is marked as done!
____________________________________________________________
```

### `delete` - Delete an item

Delete an item with a given number as shown in `list`.

Format: `delete ITEM_NUMBER`

Example of usage: 

```
>> list
1. [T] ✘ clean up room
2. [D] ✓ submit report (by: 2020-10-12)
3. [E] ✘ party (at: 2020-09-11)
____________________________________________________________
>> delete 3
Ok! I have deleted this task:
[E] ✘ party (at: 2020-09-11)
You now have 2 task(s) left.
____________________________________________________________
```

### `bye` - Exit out of Duke

Exits Duke and saves all data into `duke-data.txt`.

Example of usage:
```
>> bye
Good bye!
```

## FAQ
**1. How do I move my data to another computer?**
    Simply copy `duke-data.txt` to the same directory as the `ip.jar` in the other coomputer.

## Commmand Summary

**Action** | **Format, Examples**
------------ | -------------
**todo**|`todo DESCRIPTION` <br>example: `todo CS1231 homework`
**deadline**|`deadline DESCRIPTION /by yyyy-mm-dd` <br>example: `deadline UTC1001 essay /by 2020-10-09`
**event**|`event DESCRIPTION /at yyyy-mm-dd`<br>example: `event beach party /at 2020-10-09`
**list**|`list`
**done**|`done <index>` <br>example: `done 3`
**delete**|`delete <index>` <br>example: `delete 2`
**find**|`find <keyword>`<br>example: `find assignment`
**exit**|`bye`
