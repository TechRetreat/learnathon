# Building the GeoCaching App
## Getting started
Now that we have an app compiling and running, lets start customizing it to make our Geocaching app. In our app we will have three screens shown below, a list of caches the user has found, a map showing caches at their locations, and a settings page.
TODO: add pics

## Whats here now
If you open up the project tab on the left you will be able to view all the files in the project, for the most part we will be worried about files in java folder which will be your code, and files in the res (resources) folder which will define how your views are laid out and a few other things like colors, images, and text. To start we will look at the Fragment and Activity that we start with, and the xml layouts that go with them.

### MainActivity.java
Activities in Android are entry points into the app that let you show something on the screen, and interact with the system. If you open up MainActivity under the java/yourApplicationName folder you will see three methods, `onCreate`, `onCreateOptionsMenu` and `onOptionsItemSelected`, you can ignore the last two as we wont be using an option menu in our app.
``` java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}
```
In `onCreate`, which you might notice Overrides the method from it parent class `AppCompatActivity`, we call `AppCompatActivity`s onCreate and the method `setContentView` that sets the screen to show `R.layout.activity_main`, our activity's view. `R` means resources, so we can find this file in `res/layout/activity_main`, lets go take a look.

### activity_main.xml
Welcome to XML, this is how we usually define our Views in Android. XML allows us to set values like width, height, text, and a bunch more depending what kind of View we are describing. In this case, our Activity's View is very simple, all it is displaying is one Fragment (screen), which is defined in XML as follows
``` xml
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/fragment"
    android:name="com.example.jgzuke.myapplication.MainActivityFragment"
    tools:layout="@layout/fragment_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
`<fragment` just says what type of element this is, in this case a fragment.
`xmlns:android="http://schemas.android.com/apk/res/android"` and `xmlns:tools="http://schemas.android.com/tools"` define namespaces, you won't need to know what these mean, just have them at the beginning of each file.
`android:id="@+id/fragment"` gives this view a unique identifier 'fragment', which will be used later to find it (like in a Map).
`android:name="com.example.jgzuke.myapplication.MainActivityFragment"` is telling this view where the code for our Fragment is. 
`tools:layout="@layout/fragment_main"` is telling Android Studio how to render this fragment in our design view (Click 'Design' at the bottom to see what the Activity looks like). This isn't important when we run our app, but is useful when we want to see what something will look like faster. Anything that starts with `tools:` instead of `android:` will be used to render stuff in Android Studio.
`android:layout_width="match_parent"` and `android:layout_height="match_parent"` set the width and height of the fragment to match the container or parents width and height, which will be the full screen.
Lets look at what this Fragment actually is by going to the class given on the 4th line `android:name="com.example.jgzuke.myapplication.MainActivityFragment"`, open up MainActivityFragment in the java/yourApplicationName folder.

### MainActivityFragment.java
MainActivityFragment is also very simple for now, notice that it extends `Fragment` and Overrides the method `onCreateView` from there.
``` java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
}
```
What this is doing is creating and returning a View from the XML resource `R.layout.fragment_main`, go open that file under res/layout/fragment_main.

### fragment_main.xml
``` xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context=".MainActivityFragment">
  
    <TextView android:text="@string/hello_world"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</RelativeLayout>
```

The first element is our `RelativeLayout` which again defines our namespaces and its width and height, as well as paddings on the sides (empty space), and the context, which again allows this fragment to render properly in Android Studio.

The second element is our `TextView` inside the `RelativeLayout`, we call the `TextView` a child of the `RelativeLayout`. All this means is that our `RelativeLayout` contains the `TextView` and can define its position and size (somewhat). The `TextView`, besides defining its width and height also gives a value for its text to display, `@string/hello_world`. Any time we see `@something` in XML it means the value is defined in another file, open up res/values/strings.xml.

### strings.xml
```xml
<resources>
    <string name="app_name">My Application</string>
    <string name="hello_world">Hello world!</string>
    <string name="action_settings">Settings</string>
</resources>
```
This is where we will define our strings, each one has a name which us used to access it, and a value. For example `<string name="hello_world">Hello world!</string>` has the name `hello_world` and value "Hello World!", so in fragment_main.xml when we say `android:text="@string/hello_world"`, "Hello World!" is what will be displayed.

## XML, Views and Fragments
Lets start with building the found caches page, if you open 
- Strings, resources (http://developer.android.com/reference/android/content/res/Resources.html)
- XML
- Tools and XML view

## Add nested layout
- Relative layout
- Id's

## Add button and set text programatically
- Button
- View, Object
- Extention/Implementation (breifly)
- [Logging click] http://developer.android.com/reference/android/util/Log.html
- getViewById
- OnClickLostener
- Callbacks

## Build a RecyclesView with mock arraylist
- Simple arrayList of strings
- RecyclerView
- Adapter
- XML inflation
- Layout Manager
- ViewHolder pattern

## Get data from json file
- Model objects
- Json
- IO
- Generics
- Static functions
- Interface/Implementation/Composition
- Utility classes

### Put good data into recycler view
- Calling utility

### File organization
- Create Controller
- Fragment
- Utilities

### Create viewPager
- ViewPager

### Tab layout
- Connect to viewPager

### Create Map fragment/Add to viewPager
- Extend SupportMapFragment
- Extension further

http://www.truiton.com/2013/05/google-maps-android-api-v2-introduction/
Getting api key
- https://code.google.com/apis/console/
- Create project
- Use Google APIs
- Google Maps Android API
- Enable
- Go to credentials
- Calling from: Android
- Click 'Find out what kind of credentials you need'
- Click 'Create an API key'
- Copy Key into Android Manifest meta-data 'com.google.android.maps.v2.API_KEY'

- Implement OnMapReadyCallback
- Implementation further
- Support Fragment vs Fragment
- Same stuff as before

### Using Map
- Getting map
- Asynchronous
- Map settings https://developers.google.com/maps/documentation/android-api/controls
- Zoom to location

### Permissions
- Manifest
- Check has permissions
- Request if not

### Make Markers
- Make Model and Call for map caches
- Click listener
- Info window
- Dealing with maps (found)

### Marker popup dialog
- Alert dialogs
- View inflation
- View dismiss

### Cache NFC?
- Read NFC of cache and check against server?
- Client/server validation

### Getting data from actual API
- Making the request

### Sending back to API from found
- Make the request, handle errors

### Open map from found fragment
- Callback to activity
- Passing in Location
- Talk about async with getting map

### Cache Data
- Store json to use off network
- Take in onError Callbacks
- Check connectivity

### Preferences
- Add fragment to viewPager
- Create Strings etc for preferences
- Create R.xml.preferences
- Extend PreferenceFragmentCompat http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html
- SharedPreferences http://developer.android.com/reference/android/content/SharedPreferences.html

### Preference screen to do actions
- Dump Cache
- Clear Cache
- Clear found caches (+server call)

### Beautify app
- Colors
- Styles
- Themes (put in settings?)
