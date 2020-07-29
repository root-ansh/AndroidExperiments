
# todo (`paging2`)


Pagination, also known as paging, is the process of dividing a document into discrete pages. In 
terms of internet, it would mean to request only a small part of data from the server instead of 
the complete data, which could be  very huge, and network exhaustive. For eg, a typical user would
scroll upto 200-300 posts on a social media site before putting down their phone. So if there are 
million posts on the server, and our code tries to download them all, then firstly it would take a 
lot of user's internet, and secondly we might fill up the cache limit for our app, creating a crash. 
Thus pagination comes to the rescue.  we can simply load the first 100 items for user to see and then 
load next 100 when user is near to say 50  posts
 
1. under pagination, we have 3 approaches:
   - to paginate only the data coming from database,   <--- we did this in this project
   - to paginate data coming from network
   - to apply paging for both network and db
   
checkout the code changes in the latest commit(contains all the changes from the master branch in a 
single commit diff) and  following links for more information:
```
https://developer.android.com/topic/libraries/architecture/paging
https://github.com/anitaa1990/PagingLibrary-Sample
https://bloggie.io/@_junrong/part-1-understanding-the-paging-library-pagedlist

```


---
### Other Stuff:

- I jut saw in some other person's code that its common to pass inflator in Adapter. i would love 
  to discuss more on the why behind this,but from what i could guess, it is a more optimized approach.
  Recycler view is already creates 5-10 views and later recycles them by passing them as `parent`,
  but we are still creating a new inflator for every time a view is requested. so this approach will
  optimize garbage collection.
  So this is another small addition