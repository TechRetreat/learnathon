# Found Caches Page
## RecyclerView
### Add the RecyclerView to Layout
Lets start with building the found caches page, basically this page is a list of the name and difficulty of caches you have found. We will build this list with what is called a `RecyclerView`, lets add one to our found caches layout underneath our Button.
``` xml
<android.support.v7.widget.RecyclerView
    android:id="@+id/found_caches_recycler_view"
    android:layout_width="match_parent"
    android:layout_height="fill_parent"
    android:layout_below="@+id/my_button"/>
```

### Getting the Support Design Library
To use `RecyclerView` you need to grab some code that doesnt come packaged with android, we will do this through `gradle`. In the project tab on the left there is a section called 'Gradle Scripts', open up `build.gradle (Module: app)`. `Gradle` is Android's build system that you can also use to automate some build related tasks. Basically it takes our Java code and XML and packages them into an APK that you can run on your phone, we wont be working with it much today besides using it to fetch code like we are now. In the `dependencies` section add `compile 'com.android.support:design:23.2.0'`, this will allow us to use elements from the support design library (version 23.2.0) by including it when we build our app.

### What is RecyclerView
`RecyclerView`s are a bit tricky to work with, lets take a look at what they are before we dive in. To work a `RecyclerView` needs an `Adapter`, `LayoutManager`, `ViewHolder`, a list of data to display, and a layout to show it in. It will show this data as a list of `Views`

**Adapter:** When a `RecyclerView` is displayed it asks its `Adapter` to create `View`s from the data it has until it fills the screen completely. As the user scrolls the `Adapter` 'recycles' the `View`s that go off screen to make new ones by changing the data in the `View`'s `ViewHolder`, and gives the `View` back to the `RecyclerView` to show.

**ViewHolder:** The `ViewHolder`s are attached to each `View` and when given an entry from the data list 'bind' the data to their `View`, meaning it sets the information in the `View` based on the data it recieved.

**Data List:** The `RecyclerView` needs a data entry for every element is displays in its list, this data will be used to show the proper `Views` through the `ViewHolder`s.

**Layout:** The `RecyclerView` needs a layout to inflate for the items it will display. For our first example we will use a `TextView`, later we will made our own layout.

**LayoutManager:** The `LayoutManager` tells the `RecyclerView` how to lay out or display its list of items.

### LayoutManager
Back in FoundCachesFragment, get a reference to the `RecyclerView` the same way as we did the `Button` or `TextView`. Lets start by setting our `LayoutManager` like so `yourRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));`, you wont have to worry about what this is doing but basically LinearLayoutManager means the data will display in a straight vertical list.

### ViewHolder
Our RecyclerView will be really simple for now, we'll show a List of `TextView`s which means our `ViewHolder` will be pretty simple. Classes inside files, or 'inner' classes are defined the same place a method would go.
``` java
private class CacheHolder extends RecyclerView.ViewHolder {
    
    // We will modify the text in this TextView when we get bound to data
    private final TextView nameTextView;

    // itemView is the View this ViewHolder controls
    public CacheHolder(TextView itemView) {
        super(itemView);
        nameTextView = itemView;
    }

    // This is called when a View is recycled and given new data, or first created
    // cacheName will be from our data list
    public void bindCache(String cacheName) {
        nameTextView.setText(cacheName);
    }
}
```
Our `ViewHolder` will be created with a `View` that it will then modify when it is Bound to some data. In this case the data is just the name of the cache, later on we will bind a data object to this view that will have all the information we want to display about it.

### Adapter
Our adapter is responsible for inflating or recycling `View`s for the `RecyclerView` to display, as well as keeping track of the data definind the list. This class is a bit longer but still fairly simple.
``` java
private class CacheAdapter extends RecyclerView.Adapter<CacheHolder> {

    // This is our list of data
    private List<String> itemNames;

    // Our adapter is created with this data list to display
    public CacheAdapter(List<String> itemNames) {
        this.itemNames = itemNames;
    }

    // Called when a new view needs to be created, creates a View and ViewHolder
    @Override
    public CacheHolder onCreateViewHolder(ViewGroup parent, int pos) {
        TextView view = new TextView(getContext());
        return new CacheHolder(view);
    }

    // Binds a given ViewHolder to data at the same position in the list
    @Override
    public void onBindViewHolder(CacheHolder holder, int pos) {
        String name = itemNames.get(pos);
        holder.bindCache(name);
    }

    // If we have no value for itemNames we cant create any items,
    // otherwise we can create as many as we have data point
    @Override
    public int getItemCount() {
        if (itemNames == null) {
            return 0;
        }
        return itemNames.size();
    }
}
```

### Data List
The last thing we have to do to make our `RecyclerView` work is give it data, and set the `Adapter`. Becuase our adapter just has a list of names, lets make an `ArrayList` of names and add some.
``` java
ArrayList<String> itemNames = new ArrayList<>();
itemNames.add("Cache 1");
// Add more elements to data list
```
Then all have have to do to put everything together is create and set your `Adapter`.
``` java
yourRecyclerViewAdapter = new CacheAdapter(itemNames);
yourRecyclerView.setAdapter(yourRecyclerViewAdapter);
```
You should now have a working recycler view, make sure it compiles and runs correctly, you should see your names that you put in `itemNames` underneath your `Button`

## Get data from json file
- Model objects
- Json
- IO
- Generics
- Static functions
- Interface/Implementation/Composition
- Utility classes

## Put good data into recycler view
- Calling utility

## File organization
- Create Controller
- Fragment
- Utilities