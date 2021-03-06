# CustomBookList
An application developed in android studio which allows user to search for a certain book among a list that contains book items with name, author's name, publisher's name and the image of the cover page of the books.
The app uses a custom filterable adapter to setup the listview and allow searching in it. The search result contains all list items that have the search query matching wholly or partially with the name of the book or its author's name or its publisher's name. All filtered items are arranged in this order, i.e. first those with the query matching with title then with the author and at last the publisher.
Besides, the no. of found results is also shown. 

# User Interface 
When the app starts, a user can see the list of books (with their cover images). In this simple app there are four novels which are provided as list items. (There is no provision to add or remove items, to do so one has to edit the source code.) Above this list there's an edit textbox that has a hint "Search Here" to guide the user. Below this search box is a small textview that by default shows "Results". When the user types in some search query, the list continuously filters  with every character added. (thus the user does not need to press search all the time) The textview changes to show the number of found results, e.g. "Found 3 results". Considering that there are 4 items the maximum possible no. results can be 12 (i.e. when the query matches with all three fields of all the items). One additional feature of this app is that if the user decides to scroll through the list the keyboard automatically is hidden. However, the user won't be fully satisfied with this app because the characters that match the query are not highlighted (or are not shown in a different colour). 
On clicking a list item the corresponding amazon shopping page opens where user can see the price and description of the book.

# How the search algorithm is implemented?
To the search box which is an edittext, a textwatcher method has been attached. Whenever the text changes the filter is called via the custom adapter. In the filter we have two methods, one to perform filtering and other to publish results. The search query is first broken down into a set of words and then matching is done with the name, author and publisher (in this same respective order) with each of those words (that's how partial search is implemented, the user does not need to type the exact query!)

#Fundamental Concepts Involved
i)ListViews
ii) Custom Adapters
iii) Filterable Interface
iv) WebViews
