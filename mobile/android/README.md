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
- [Online Editor](https://www.compilejava.net/)
- int, boolean
- If, else, case
- array, map
- for, while

(look at https://teamtreehouse.com/library/topic:android later)
## Setup Android Studio
- [Download/Install Android Studio + SDK tools](http://developer.android.com/training/basics/firstapp/index.html) Make sure you have Java installed as well
- [Set up Android Studio project](http://developer.android.com/training/basics/firstapp/creating-project.html)
 - Start a new Android Studio Project
 - Minimum SDK: API 16 (Jelly Bean)
 - Add an activity to Mobile: Blank Activity with fragment
- [Run on device/emulator](http://developer.android.com/training/basics/firstapp/running-app.html)

# Building the app

## Add text to fragment
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

## Put good data into recycler view
- Calling utility

## File organization
- Create Controller
- Fragment
- Utilities

## Cteate viewPager
- ViewPager

## Tab layout
- Connect to viewPager

## Create Map fragment/Add to viewPager
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

## Using Map
- Getting map
- Asynchronous
- Map settings https://developers.google.com/maps/documentation/android-api/controls
- Zoom to location

## Permissions
- Manifest
- Check has permissions
- Request if not

## Make Markers
- Make Model and Call for map caches
- Click listener
- Info window
- Dealing with maps (found)

## Marker popup dialog
- Alert dialogs
- View inflation
- View dismiss

## Cache NFC?
- Read NFC of cache and check against server?
- Client/server validation

## Getting data from actual API
- Making the request

## Sending back to API from found
- Make the request, handle errors

## Open map from found fragment
- Callback to activity
- Passing in Location
- Talk about async with getting map

## Cache Data
- Store json to use off network
- Take in onError Callbacks
- Check connectivity

## Preferences
- Add fragment to viewPager
- Create Strings etc for preferences
- Create R.xml.preferences
- Extend PreferenceFragmentCompat http://developer.android.com/reference/android/support/v7/preference/PreferenceFragmentCompat.html
- SharedPreferences http://developer.android.com/reference/android/content/SharedPreferences.html

## Preference screen to do actions
- Dump Cache
- Clear Cache
- Clear found caches (+server call)

## Beautify app
- Colors
- Styles
- Themes (put in settings?)

## Geocaching App
- [Gradle, importing libraries (android arsenal)](#)

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
- xml
- view
- fragment
- activity
- service
- synchronous