This is a sample application that compares the performance of a RecyclerView using a traditional 
Cursor list adapter and a PagedListAdapter backed by a TiledDataSource.

The database is loaded with 1000 User entries with fake data and as you scroll through the list the 
amount of time onBindViewHolder takes is logged for comparison.
 
Change the fragment class in _activity_main.xml_ if you want to switch between the list adapter 
implementations.