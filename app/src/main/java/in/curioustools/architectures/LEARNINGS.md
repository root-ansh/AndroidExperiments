# Learnings

Android UI is mainly about working with views and view manupulation. We want to perform a lot of 
actions on the views via  code: hide/show a ui, handle its clicks, set data on it,... lots of tasks,
and all these tasks are  performed via java/kotlin code in the following 
way: 1) we access/ create a view and 2) we perform some action on it.   

for task (1) we again have the java way of using `findviewbyid(...)` or the kotlin way of synthetic 
binding (i.e accessing the views directly via their entries). I like the kotlin way a lot , but 
turns out there is another way to think about view manupulations, its called data binding!  


Data binding is  like an advanced version of synthetic binding, you can access the views  directly
(well almost)  , but that's not it. Its uses a complete different approach to view manupulations.
Under view binding, the xml and views gets the powers to define the manupulations. This is called a
**declarative UI** . Under declarative ui, we can define all the manupulations in the xml only :  

- we can pass data to ui and it would be set automatically.
- we can pass functions(via binding adapters) and onclick listeners to xml and they will automatically be run when buttons
  are clicked
- we can attach live data to ui and it would automatically update the ui
- even holders and adapters of recycler views could use data binding extensively

***Note:** This example is going to be a very basic introduction to one way and 2 way binding because this app 
does not have much  of custom ui. Data binding, on its own can is a very advanced library, almost giving
the powers to execute complete programming logics via xml only .*

*However i plan to create another ui based branch which would have more focus on ui libs like 
navigation, constraint/motion layout/co-ordinator  animations, reycler view, 
material libs and databinding*


***Note2:** There is a much simpler library called view binding by google that does almost the same 
thing as synthetic binding. i guess i would say its a small part of data binding sliced out from the 
main data binding library and deployed a separate library*

---
## Cliff notes

- The best way to understand the working of this library would be to checkout all the files in the latest 
commit of this repo via github ui and trying to understand what was changed to incorporate this new lib,
before reading these cliff notes. 

- **Details Activity**
This activity is relatively simple to explain and shows a few  examples of one way binding. We are 
simply passing a model to ui, and ui gets set accordingly(while handling null checks). 
Also this activity shows  how a function gets binded to  a view. as binding adapter

- **Dashboard Activity**
Dashboard activity follows an even simpler code based databinding. here we are doing everything 
similar to kotlin's synthetic binding: accessing the views directly and attaching ui in them.

- **Dashboard Activity**
Dashboard adapter is super similar to how we usually bind stuff in recycler view instead of usual 
layout inflater, we use the databindingutil inflator. instead of usual inflator. 
we simply passed the item and click listener instead of binding ourselves manually in the viewholder(
also that was a cool thing to do , binding a listener and that too generic!). we also have to call
binding. executepending transaction on every holder binding. rtfc( read the freaking code)
for more info


**Misc**

(everything in xml)
- integer must be binded using `@obj.intParam.toString()`
- concatination = ```  @{  obj.a + `xyz` +obj.b    }  ```
- check for null : ```@{obj.item??`alternative`}``` i.e null collase operator
- lists are binded using `@{obj.list.toString()}` . don't use null collase operator
- any other checks : ```       @{obj.item==condition?`true case`:`false case`}     ```
- prefer to keep everything as much null free as possible

- there is a long list of templating  the biding can do, checkout this:

- if you have handled the null cases for `@{obj.item}` as  @{obj.item==condition?`true case`:`false case`} ```
  or some other ways, you don't have to worry if `obj` itself is null. compiler will automatically
   show values that wold have shown if `obj.item==null`
   
- turns out, there are ways to bind view model to ui, and pass adapter/layout manager to recycler view as binding,
  and binding ui to livedata, and they all require a lot of customizations. cool   
  
- note that if you once used an adapter binding with some xmlns schema `xyz` , for eg `xyz:bindUrl = "@{something}"`
  you can only create other adapter bindings using the smame xyz schema and none other. this brings another archetectural question:
  if all the binding adapters follow a common style, shouldn't we place them in a single file?
  and that's what i did
  
---


### Resources :
- https://developer.android.com/topic/libraries/data-binding/expressions#kotlin
- https://android.jlelse.eu/how-to-bind-a-list-of-items-to-a-recyclerview-with-android-data-binding-1bd08b4796b4

- https://spin.atomicobject.com/2019/06/08/kotlin-recyclerview-data-binding/
- https://proandroiddev.com/advanced-data-binding-binding-to-livedata-one-and-two-way-binding-dae1cd68530f


### Other stuff
- changed annotation processor of glide to kapt because a warning says that annotation processor
  won't be recognized in a kapt project
  
- always implement the complete lifecycles! i have recently got to know that observers should be 
  attached inside the oncreate only. this has changed my complete perception towards both the 
  observers and listeners which i thought should be attached in on start. so i maybe wrong about this,
  i have to check more on this. but even if i am right, i didn't removed them in on stop making  my 
  code prone to null pointers. so i must remove observers and listeners in onstop, nullify the 
  instances on destroy and whatnot. following the lifecycle, along with on saved states is must. i 
  would be making a branch on that too

