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

- int, boolean
- If, else, case
- array, map
- for, while

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
- Android studio view (structure project preview)
 - Project (left): Shows files in android structure
 - Structure (right): Shows functions and inner classes of files
 - Android (bottom right): Shows logs from device
 - Run (bottom left): Options for running project
 - Terminal (bottom left): Command line
- [Android studio shortcuts](https://teamtreehouse.com/library/android-tools/getting-started-with-android-studio/helpful-keyboard-shortcuts)

## Vocabulary
### Views
- **View:** An element on the screen
- **XML:** The format android uses to store view data like text, color, and size
- **Fragment:** A Fragment
- **Resources:**

### Top level classes
- **Activity:** An Activity
- **Controller:** The Controller is what we use 
- **Utility:** 

### Objects
- **Object:** 
- **Extends:** 
- **Implements:** 
- **Interface:** 
- **Override:** 

### Data Class
### HashMap
### Array

### Function
### Return
### Callback
### Synchronous

### Class
### Type
### Casting
### Generic
### Primitive
### Null

### Integer
### Long
### Float
### Boolean
### String

### Public
### Private
### Static
### Final

### Gradle
### Preference
### Constant
