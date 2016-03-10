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