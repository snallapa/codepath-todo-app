
#Pre-work - Todo App

Todo App is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Sahith Nallapareddy

Time spent: 4.5 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [x] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [x] Added a way to check the item on the todo list to mark item as completed
* [x] Added a completed section and a not completed section to listview to differentiate
## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/P09SeEX.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<img src='http://i.imgur.com/h6CNQeb.gifv' title='Video Walkthrough 2' width='' alt='Video Walkthrough 2'/>

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

The biggest challenges I faced were adding multiples views to a Listview. I had trouble dealing with the index correctly and it took a long time to debug to make it work correctly. I also had trouble using Active Android for the first time.It was relatively easy once I had it setup. However, adding the library to my project was harder than I thought it would be. Using it was relatively easy. 

## License

    Copyright 2016 Sahith Nallapareddy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
