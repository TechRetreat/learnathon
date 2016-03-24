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

## Display Cache names in RecyclerView
Since we want this list to get displayed in the `RecyclerView` let's modify it a bit to take in FoundCache.Cache values instead of `String`s. First change the `CacheAdapter` constructor to take in a `List` of `FoundCache.Cache`s where `.Cache` refers to the nested class in `FoundCaches`, also change the type of the `items` variable. The last thing we have to do to in the `Adapter` is change `String item = items.get(pos);` to `FoundCaches.Cache item = items.get(pos).name;` in `onBindViewHolder`. Next in `CacheHolder` modify `bindCache` to take in a `FoundCaches.Cache cache` and display `cache.name`. The last thing we have to do is supply the right data. Let's replace
``` java
ArrayList<String> itemNames = new ArrayList<>();
itemNames.add("Cache 1");
itemNames.add("Cache 2");
itemNames.add("Cache 3");
cachesRecycerViewAdapter = new CacheAdapter(itemNames);
cachesRecycerView.setAdapter(cachesRecycerViewAdapter);
```
with the DataUtilities call from before, and where we have `// Do something with the results` we can create an adapter from our fetched data
``` java
cachesRecycerViewAdapter = new CacheAdapter(results.caches);
cachesRecycerView.setAdapter(cachesRecycerViewAdapter);
```
Try running the app and make sure it works, for now it should display the names of the caches (`Cache 1`, `Cache 2`, `Cache 3`)

## Cache info Layout
Let's continue by showing the rest of the data in our list. To do this we'll first need something to display the data in, create a layout called `list_item_found_cache` or something similar under res/layouts (Note that you can't use capital letters or spaces in resource names). Because creating layouts can be time consuming I've created this one for you to copy into your new file.
``` xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/card_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="12dp">

        <TextView
            android:id="@+id/cache_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="23sp"
            tools:text="Example cache name" />

        <TextView
            android:id="@+id/cache_difficulty"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/cache_name"
            android:layout_marginEnd="30dp"
            android:layout_marginRight="30dp"
            android:textSize="14sp"
            tools:text="Difficulty: 3" />

        <TextView
            android:id="@+id/cache_find_time"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/cache_name"
            android:layout_toEndOf="@+id/cache_difficulty"
            android:layout_toRightOf="@+id/cache_difficulty"
            android:textSize="14sp"
            tools:text="Found 3 days ago" />
    </RelativeLayout>
</android.support.v7.widget.CardView>
```
Theres a few things here you haven't seen before, lets list them quick.
- `CardView`: a special layout that shows its contents in 'cards', if you want to see what this looks like switch to the design view.
- `layout_margin`: similar to padding, but pushes other elements away from it rather than making itself bigger to fit its contents with space inside. Defined in `dp` or Density-independent Pixels which are a size unit that scales with pixel density on the screen (`dp` stays the same physical size).
- `textSize`: fairly self explanatory, this sets the text size in `sp` which is similar to `dp` but takes users font size into consideration.
- `layout_marginEnd` and `layout_toEndOf`: not all phones are layed out right to left, if it isn't then `End` refers to the end of the layout which would be right for us. `layout_marginStart` or `layout_toStartOf` would be the opposite.

## Using the Cache info Layout
Now that we have our layout let's put it to use, go back to our `CacheAdapter` and where we have `TextView view = new TextView(getContext());` we will inflate our new layout instead `View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_found_cache, parent, false);`. 

We should also modify our `CacheHolder` to give it two extra variables `difficultyTextView;` and `findTimeTextView;` both of which are `TextView`s, these will come from the layout we just defined. In the constructor, set `nameTextView = (TextView) itemView.findViewById(R.id.cache_name);` and the same for the other two variables, using `R.id.cache_difficulty` and `R.id.cache_find_time` as the element Ids. Now we have all the data we need to display, in `bindCache` add 
``` java
difficultyTextView.setText(Integer.toString(cache.difficulty));
findTimeTextView.setText(Long.toString(cache.found));
```

## Formatting and FormattingUtilities
If we run our app now it shows a list of all our data but not in a format that we can read, Lets creat another class called FormattingUtilities where we'll put any methods we use to format data to be readable. First lets add two methods to format difficulties and find times, each will take in the data we have and output a `String` that we can display.
``` java
public static String getDifficultyString(int difficulty, Context context) {
    return "";
}

public static String getTimeAgoString(long timestamp, Context context) {
    return "";
}
```
Notice that these methods are both `static` meaning that this class doesn't need to be instantiated to use these methods. Both methods also take in a `Context` which will be used to get `String`s from resources. 

Let's add two new entries in `strings.xml` that will be used in our cards with values `Found %s` and `Difficulty %d of 5`, you can pick names for them that make sense. Remember from before that `%d` is a number placeholder, `%s` is a placeholder but for a `String`.

Back in UiUtilities `getDifficultString` will work the same way as our button click counter did before, simply return `context.getString(R.string.difficulty_out_of_five, difficulty)` and it will fill in the placeholder with our difficulty. For `getTimeAgoString` we're going to use something bulid into Android called called DateUtils to format our time nicely. `DateUtils.getRelativeTimeSpanString(context, timestamp).toString();` returns a `String` which formats our time nicely eg. "42 minutes ago". Inject this value into our other `String` the same way we did the number placeholder by using `getString` and passing the result of `getRelativeTimeSpanString`.

If we go back to our `CacheHolder` we can replace our `Integer.toString` and `Long.toString` with `UiUtilities.getDifficultyString(cache.difficulty, getContext())` and `getTimeAgoString(cache.found, getContext())`.

## Cleaning up
We're finished work on the 'Found' page for now, if you feel like it go through and take out the stuff we added at the start, `helloWorldTextView` + `clickMeButton` in our `FoundCachesFragment` fragment and `my_text_view` + `my_button` in the `fragment_found_caches` layout.
