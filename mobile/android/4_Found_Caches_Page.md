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
yourRecyclerView.setAdapter(new CacheAdapter(itemNames));
```
You should now have a working recycler view, make sure it compiles and runs correctly, you should see your names that you put in `itemNames` underneath your `Button`

## Getting Data
### JSON
Now that we have our `RecyclerView` its time to get some real data to put in it, eventually were going to be getting this data from our server online, but for now were going to read the data from a file locally in resources. The format were going to store our data in is called JSON, basically it's a way to write our data that is easy for us to read, as well as for a computer to use.
``` json
{
  "caches": {
    "yrolad": {
      "name": "Cache 1",
      "difficulty": 3,
      "found": 1456883400000
    },
    "mdifpq": {
      "name": "Cache 2",
      "difficulty": 3,
      "found": 1456531200000
    },
    "cbdiyd": {
      "name": "Cache 3",
      "difficulty": 3,
      "found": 1456531200000
    }
  }
}
```
This is what the JSON file looks like for our found caches, lets go through whats happening here. In JSON, everything is stored by some 'key' like in a `Map`. Our main element has the key `caches` and the value
``` json
"yrolad": {
  "name": "Cache 1",
  "difficulty": 3,
  "found": 1456883400000
},
"mdifpq": {
  "name": "Cache 2",
  "difficulty": 3,
  "found": 1456531200000
},
"cbdiyd": {
  "name": "Cache 3",
  "difficulty": 3,
  "found": 1456531200000
}
```
Each of these 'child' elements is again a key and value, the first has the key `yrolad` and the value 
``` json
"name": "Cache 1",
"difficulty": 3,
"found": 1456883400000
```
The key `yrolad`, as well as `mdifpq` and `cbdiyd` are our geocache Ids. Every cache has a unique random identifier we will use to refer to it, kind of like a name. Each of these geocache Ids maps to the definition for a cache, its name, difficulty, and the date it was found. Each of these values still maps a key, for example `name` to a value `Cache 1`.

## Create JSON Resource
Now that we know a bit about JSON lets start using it, create a new 'Android Resource Directory' in `res` called `raw` with resource type `raw`, then inside `raw` (you might have to refresh the menu to see it) create a file called `caches_found.json`. Copy the response from before into this file.
``` json
{
  "caches": {
    "yrolad": {
      "name": "Cache 1",
      "difficulty": 3,
      "found": 1456883400000
    },
    "mdifpq": {
      "name": "Cache 2",
      "difficulty": 3,
      "found": 1456531200000
    },
    "cbdiyd": {
      "name": "Cache 3",
      "difficulty": 3,
      "found": 1456531200000
    }
  }
}
```

## Reading from Resources
Since we dont want to make our `FoundCachesFragment` too large we'll write our code to load this JSON file ielsewhere create a new Java Class in your java folder with `MainActivity` and `FoundCachesFragment` called `DataUtilities`. This is where we will write all our code related to reading, and later fetching and sending data. Lets write a method called `getResponseTest` inside this class that takes in a `Context` like so `public static void getResponseTest(Context context) {`. This method is `static` meaning that it can be called without an instance of the class like so `DataUtilities.getResponseText(context)`. When reading from raw resources we use something called an `InputStream` to read the file, The next block of code seems scary and I wont go into how it works, but it reads the json file `caches_found` we wrote earlier into a `String` called `json`.
``` java
try {
    InputStream is = context.getResources().openRawResource(R.raw.caches_found);
    int size = is.available();
    byte[] buffer = new byte[size];
    is.read(buffer);
    is.close();
    String json = new String(buffer, "UTF-8");
} catch (IOException ex) {
    ex.printStackTrace();
}
```
The `try` and `catch` are keyword we use if something might go wrong. We try to run the code in the first block and if something goes wrong trying to read the file the system will 'throw' and `IOException` object that would normally crash the app. If this happens within the first block we instead 'catch' the exception, give up on what we were trying to do and run some other code instead. In this example we print out the exceptions 'stack trace' which is a log of what went wrong to cause the exception to get thrown.

## Serializing JSON Objects
Now we have a String of our JSON file, which is really hard to use as is. What were going to do now is convert this String to a more useable object, a Map of cache Ids to `Cache` objects, which will have `name`, `difficulty` and `found` as variables. What were going to use to do this is a tools called Gson, which will take our String and automatically convert it to an object we define. Lets start by creating our 'model' object that mirrors how our JSON data is set up. Create a new Java class called `FoundCaches` that looks like we just described.
``` java
public class FoundCaches {
    public Map<String, Cache> caches;

    public static class Cache {
        public String name;
        public int difficulty;
        public long found;
    }
}
```
When using Gson the variable names we use here have to match the keys in the JSON file. Notice that Gson will create a Map using our Ids as the keys and our Cache objects as the values.

To use Gson we have to include it with `gradle`, add `compile 'com.google.code.gson:gson:2.3'` to the gradle file the same place we did before. Now if we go back to `DataUtilities` we can add
``` java
Gson gson = new Gson();
FoundCaches caches =  gson.fromJson(json, FoundCaches.class);
```
underneath `String json = new String(buffer, "UTF-8");`, notice the method `fromJson` takes the class `FoundCaches` as a parameter. We now have a method that reads our JSON file and gets an object with the information we need to fill our `RecyclerView`.

## Callbacks
Now that we have a way to get this data let's send it to our `FoundCachesFragment`. The easiest way to do this would be to return the object from the function, but this causes problems later on when we get the information from online. When we call a method our phone waits till it finishes before moving on to do anything else, this causes issues if it takes a while for the method to finish as it would freeze your phone until it was done. We can get around this by running our code on a seperate 'thread' which we'll explain later, but that means we cant return from this method.

What we do instead is define a 'callback', an object that defines a function to run when something happens like our OnClickListener running code when our `Button` got clicked. To create a callback we define an `interface` with one method `onResults(FoundCaches results)` which we will call when we finish serializing the JSON file.
``` java
public interface Receiver {
    void onResults(FoundCaches results);
}
```
Add this to DataUtilities above the method `getResponseTest`. When we call `getResponseTest` we want to get a callback to send back the data with so add `Receiver receiver` as a parameter to the `getResponseTest` method and add `receiver.onResults(caches);` after serializing our JSON file. Now our method takes in a callback, and fires its function when it has the data ready.

## Using DataUtilities
In our `FoundCachesFragment` we can now get the list of found caches easily by calling
``` java
DataUtilities.getResponseTest(getContext(), new DataUtilities.Receiver() {
    @Override
    public void onResults(FoundCaches results) {
        // Do something with the results
    }
});
```
Since we want this list to get displayed





## Controller Class
Because we don't want our `FoundCachesFragment` file to get too large, were going to 

## Put Response into RecyclerView
- Calling utility

## File organization
- Create Controller
- Fragment
- Utilities
