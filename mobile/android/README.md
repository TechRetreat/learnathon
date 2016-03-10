# Mobile/Android

## What you'll make
Gif of demo






## Table of Contents
- [Java](#java)
- [Setup Android Studio](#setup-android-studio)
- [Android Basics](#android-basics)
- [Geocaching App](#geocaching-app)
- [Extend it further](#extend-it-further)
- [Android Studio Tips](#android-studio-tips)
- [Vocabulary](#vocabulary)






##Java
To start todays lesson were going to take a quick look at the language Android apps are written in, Java. Open up the [Java Online Editor](https://www.compilejava.net/) to get started.

### Main
To start, replace the generated code with the following 
```java
public class JavaExamples {
  // The main function is the start of a normal Java program
  // You can ignore this function as Android starts running in a different spot
  public static void main(String[] args) {
    // This will print out "Hello world!" to the console
    System.out.println("Hello World!");
  }
}
```
If we run our code now, after compiling, it will run the `main` function and execute the command `System.out.print("Hello World!");` which prints "Hello World!" to the console. Note that anything after `//` does not run as code, this is called a comment.


### Data types
In Java, we store a few different types of values, the most common are `Integers` (numbers), `Strings` (text), and `Booleans` (true or false values). We store these values in things called variables by writing
```java
String myText = "Text to Print";
```
After we have stored our variable, a `String` (or text) named `myText`, we can access its value with just the variable name. Replace our old `System.out.println("Hello World!");` with
```java
String myText = "Text to Print";
System.out.println("Hello World!");
```
and rerun our program.

We can also store numbers or booleans like so
```java
int myNumber = 1337;
System.out.println("My favorite number is: " + myNumber);

boolean arewWeDoneYet = false;
System.out.println("Are we done yet: " + arewWeDoneYet);
```
As you can see in the above code, Strings (things in quotes) can be joined with numbers or booleans and they will display as their text values.

Variables can also be modified after they have been created (initialized).
```java
int countdown = 10;
System.out.println("Count: " + countdown);

countdown = 9; // Reassign countdown to 9
System.out.println("Count: " + countdown);

countdown = countdown - 1; // Lower countdowns value by 1
System.out.println("Count: " + countdown);

countdown -= 2; // Short form of writing countdown = countdown - 2
System.out.println("Count: " + countdown);

int countdownSpeed = 3;
countdown -= countdownSpeed; // Lower coundowns value by ammount in a variable we define
System.out.println("Count: " + countdown);
```
### Logic
Storing variables is nice, but you cant do very much with just variables. There are a few different ways to control what happens in our code. The first is `if` statements, which will check if a value is true, and if so run the code inside. Try running the following main function in the online editor.
```java
public static void main(String[] args) {
  boolean someValue = false; // Try setting this to true after 
  if (someValue) {
    System.out.println("The value chosen was true");
  }
}
```
Comparing numbers also results in boolean values (true or false), for example `4 == 4` or `5 > 3` are true, while `4 == 5` and `5 < 3` are false. `if` statements can also choose to run some other code if the value is false
```java
public static void main(String[] args) {
  int myNumber = 7; // Try setting this to different numbers
  if (myNumber < 8) {
    System.out.println(myNumber + " is smaller than 8");
  } else {
    System.out.println(myNumber + " is not smaller than 8");
  }
}
```
You can also check if multiple things are true using `&&` or if one of a list of things is true with `||`, try running the code below with different values for myNumber and shouldPrint.
```java
public static void main(String[] args) {
  int myNumber = 7;
  boolean shouldPrint = false;
  if (myNumber < 8 && shouldPrint) {
    System.out.println(myNumber + " is smaller than 8 and shouldPrint is true");
  }
  if (myNumber < 8 || shouldPrint) {
    System.out.println(myNumber + " is smaller than 8 or shouldPrint is true (or both)");
  }
}
```

### Loops
- for, while

### Data Structures
- array, map

### Functions

### Objects
- Object
- Extention
- Implementation






## Setup Android Studio
###Download/Install Android Studio
To build our Android app we'll be using an IDE called Android Studio. An IDE is a programming environment that allows you to edit files, compile and run your code, and has other features built into it like debugging. Lets start by [Downloading Android Studio](http://developer.android.com/training/basics/firstapp/index.html) To get it to work you'll also need to install [Java](TODO:link).

###Download SDK tools
TODO: SDK stuff here or after? test fresh install studio on windows.

###Setting up a New Project
Once Android studio is set up we'll need to start a [New Project](http://developer.android.com/training/basics/firstapp/creating-project.html). Open Android studio and select 'Start a new Android Studio Project', use a Minimum SDK of API 16 (Jelly Bean), and when prompted to choose starting Activity, choose 'Blank Activity with Fragment'
Minimum SDK is the minimum version of the Android Operating System required to run your app. We chose 16 because most phones run that or newer, and it allows us to use new Android features.
The starting Activity is the code that Android Studio will generate to start your project. Well explain what Activity and Fragment are in a bit.
Android studio should now generate your starting project, and drop you into the screen pictured below (TODO: Get screenshot from windows AS).

### Running project on Device/Emulator
To make sure it worked correctly, click the green 'Run' arrow at the top of the screen. This will compile the starting code and prompt you for a device to run it on. If you are using the emulator for testing, check Launch Emulator at the bottom and click OK, this will start a virtual device and run the app on it (may take while to start). If you are using a real device, plug in your phone and accept the prompt on the device, then it should appear under 'Choose a running device'. Select it and click OK to run the app on your phone. Your app should open with a white screen that says 'Hello World'.

### Problems?
Thats what mentors are here for, feel free to ask for help with any problems you have, if you feel up to the challenge you can also try searching your problem on [StackOverflow](http://stackoverflow.com/) or just Google.






## Building the GeoCaching App

### XML, Views and Fragments, oh my
- Strings, resources (http://developer.android.com/reference/android/content/res/Resources.html)
- XML
- Tools and XML view

### Add nested layout
- Relative layout
- Id's

### Add button and set text programatically
- Button
- View, Object
- Extention/Implementation (breifly)
- [Logging click] http://developer.android.com/reference/android/util/Log.html
- getViewById
- OnClickLostener
- Callbacks

### Build a RecyclesView with mock arraylist
- Simple arrayList of strings
- RecyclerView
- Adapter
- XML inflation
- Layout Manager
- ViewHolder pattern

### Get data from json file
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






## Extend it further
- [Camera](#)
- Caching data
- Other cool apis
- Device functionalities (Accelerometer etc)






## Android Studio Tips
### Android studio view (structure project preview)
- Project (left): Shows files in android structure
- Structure (right): Shows functions and inner classes of files
- Android (bottom right): Shows logs from device
- Run (bottom left): Options for running project
- Terminal (bottom left): Command line

### [Android studio shortcuts](https://teamtreehouse.com/library/android-tools/getting-started-with-android-studio/helpful-keyboard-shortcuts)






## Vocabulary
### User Interface (UI)
- **View:** An element on the screen, like a text block, a button, or a collection or other elements
- **XML:** The format android uses to store view data like text, color, and size
- **Resources:** All our data files defining our views, strings, or a bunch of other things
- **Layout:** Layouts define how views are organized and laid out 

### Top level classes
- **Activity:** Our Activity is the entry point into the app, and holds the UI not contained in fragments. Also used as a Context.
- **Fragment:** A Fragment is what holds the Views for our UI pages
- **Controller:** The Controller is what we will use to handle and logic and data for our fragments.
- **Utility:** Utilities consist of useful functions to share across our fragments, like fetching data, or getting preferences

### Data Structures
- **Data Class:** An class of Objects that holds data
- **HashMap:** 
- **Array:** 

### Functions
- **Function:** Reusable block of code, take in parameters and returns some value
- **Parameters:** Things 
- **Return:** 
- **Callback:** 
- **Asynchronous:** 

### Types
- **Class:** 
- **Type:** 
- **Variable:**
- **Casting:** 
- **Generic:** 
- **Primitive:** 
- **Null:** 

### Objects
- **Object:** A collection of values and things you can do with them
- **Instance:** One Object of a given class
- **Extends:** A class has the values and functions of its parent, as well as any more you define, can only extend one class. A class can be casted to the class it extends
- **Implements:** A class must define how the methods in an implemented interface work. A class can be casted to any interface it implements
- **Interface:** A collection of methods that can be implemented
- **Override:** Notation used to show that a function replaces the same named function in a class it extends or implements

### Primitive Data Types
- **Integer:** Whole numbers eg -1, 0, 1 up to 2147483647
- **Long:** Whole numbers larger in magnitude than ints
- **Double:** Decimal numbers
- **Boolean:** True of False
- **String:** Group of characters (Word, sentence, or longer)

### Variable/Function Access
- **Public:** Accessible by anyone
- **Private:** Accessible only in this class
- **Static:** Code that can be called without an object of that class
- **Final:** Variable that cannot be modified
- **Constant:** Variable that is hard coded to have some value (Never changes)

### Android
- **Context:** 
- **Gradle:** Android build system
- **SharedPreferences:** One place to store data on a device
