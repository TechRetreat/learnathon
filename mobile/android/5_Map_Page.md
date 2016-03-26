# Map Page
## Empty Map Fragment
Now that we have our `FoundCachesFragment` working let's start work on the Map page. Create a new class called `MapFragment` that extends `SupportMapFragment`, well need to compile `'com.google.android.gms:play-services:8.4.0'` in our gradle file to use `SupportMapFragment`. `SupportMapFragment` is a special fragment that contains a `GoogleMap`. 

## Getting a Maps API Key
Before using the Google Maps in our app we have to get an 'API key' from Google that will let you use their data. Start by going to the [Google Developers Console](https://console.developers.google.com/start) and creating an account (TODO: test new user). Then were going to 'Create an empty project' at the bottom, the name doesn't matter. (TODO actually do this whole thing not on a work computer)

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
- Copy Key into Android Manifest meta-data 'com.google.android.maps.v2.API_KEY' also com.google.android.gms.version

Now that we have our API key open up `AndroidManifest.xml` under the `manifests` folder which is where we will put our API key. Under the `activity` tag (still inside `application`) add
``` xml
<meta-data
    android:name="com.google.android.maps.v2.API_KEY"
    android:value="YOUR_API_KEY" />
```
and copy in your API key we just generated.

## Displaying the Map
Now that we have our API Key set up we can start using the Maps API. In our `MainActivity` layout `activity_main.xml` replace `FoundCachesFragment` with `MapFragment` for the `android:name` property, this will make our `MainActivity` show the `MapFragment`. Try running the app, you should see a map when it opens.

## Getting the Map
Now that we have our map showing up we can start using it. To start we need to get the `GoogleMap` object being displayed, we do this by calling the `getMapAsync` method built into `SupportMapFragment` and then implementing `OnMapReadyCallback` which gets called when our `GoogleMap` is ready to be displayed and used. The full code looks like this
``` java
public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    // We will use this Object to control the map shown on the screen
    private GoogleMap googleMap;

    // onCreate is called when the Fragment is created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // This requests access to the GoogleMap, we will talk about what Async means later
        getMapAsync(this);
    }

    // Called when the map is ready to use
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Save this object to use later
        this.googleMap = googleMap;
    }
}
```

## Controlling the Map
After we get our `GoogleMap` let's try to zoom to our current location, create a new method called `zoomToUserLocation` or something similar. Getting the users location is a bit tricky, start by getting a `LocationManager` which will let us access location data
``` java
LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
```
Next we will get a `Location` from `locationManager.getLastKnownLocation` and animate our `googleMap` to that `Location`'s lattitude and longitude with a zoom factor of `13`, if we can't get a location (location == null) then use a default location in Waterloo.
``` java
Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
if (location != null) {
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
} else {
    // Default to a location in Waterloo
    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.38224, -80.32682), 13));
}
```
`getBestProvider` gets us a system service that can provide us the location and `newLatLngZoom` returns a `CameraUpdate` that will move our map. Don't worry much about this code as it's a bit complicated.

## Permissions
Notice that Android Studio is complaining about `getLastKnownLocation`, this is because we tried to access the users location without the proper 'Permission'. Go to `AndroidManifest.xml` and add the following permissions before the `application` tag.
``` xml
<!-- Permissions to use Google Maps -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />

<!-- Permissions to use location for Maps -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
These give us access to a bunch of the systems properties (If the user allows).

In Android M the complexity of permissions was increased quite a bit, since we want to avoid this for today open up your gradle file and change `targetSdkVersion` (in `defaultConfig`) to 22 which will build our app for a slightly older version of Android.

## Caches Data
We now have our map and access to the users location, lets get some cache data and display it on the map. Add another file called `caches_map.json` in res/raw with our `caches_found.json` file, copy the following inside.
``` json
{
  "caches": {
    "yrolad": {
      "name": "Cache 1",
      "description": "Hey a cache description 1",
      "difficulty": 3,
      "location": {
        "latitude": 43.38224,
        "longitude": -80.32682
      }
    },
    "mdifpq": {
      "name": "Cache 2",
      "description": "Another one",
      "difficulty": 3,
      "location": {
        "latitude": 43.38224,
        "longitude": -80.32582
      }
    },
    "cbdiyd": {
      "name": "Cache 3",
      "description": "Buy your momma a house",
      "difficulty": 3,
      "location": {
        "latitude": 43.38454,
        "longitude": -80.32382
      }
    },
    "qudotn": {
      "name": "Cache 4",
      "description": "Buy your whole family houses",
      "difficulty": 3,
      "location": {
        "latitude": 43.38024,
        "longitude": -80.32382
      }
    }
  }
}
```
This is our cache data, it tells us where the caches are located as well as giving the name, description, and difficulty. The structure is the same as in `caches_found.json`. Next up lets creates a java class called `MapCaches` that we will read our json file into (same use as `FoundCaches`). It has the same structure as `FoundCaches` but has some extra information.
``` java
public class MapCaches {
    public Map<String, Cache> caches;

    public static class Cache {
        public String name;
        public String description;
        public int difficulty;
        public Location location;
    }

    public static class Location {
        public double latitude;
        public double longitude;
    }
}
```
In `DataUtils` duplicate `FoundCachesReceiver` and `getFoundCaches` and replace the `FoundCache` stuff with `MapCache`, make sure to read `R.raw.caches_map` instead of `caches_found` and serialize our json into a `MapCaches` object `gson.fromJson(json, MapCaches.class)`. We are now ready to use this data in our `MapFragment`.

## Displaying Caches on the Map
Back in `MapFragment`, after calling `zoomToUserLocation` add a call to `DataUtilities.getMapCaches` that look similar to the one in `FoundCachesFragment`. At the top of `MapFragment` define a new variable `private Map<String, MapCaches.Cache> mapCaches;`, inside the callback set `mapCaches` to `results.caches` and call a method `makeMarkers()` that we'll write next.

Now create the `makeMarkers` method that will return `void` and take in no parameters. In this method we will draw the caches on our map. The body of this method should iterate over each `Map.Entry` in `mapCaches.entrySet()` and create a marker for each cache, an `Entry` is one Key/Value pair (`<String, MapCaches.Cache>` is the type of the Key and Value).
``` java
for (Map.Entry<String, MapCaches.Cache> entry : mapCaches.entrySet()) {
    MapCaches.Cache cache = entry.getValue();
    // Draw cache marker on map
}
```
To create a marker we first need a location. Create a `new LatLng(paramaters)` called `position` passing `cache.location.latitude` and `longitude` as parameters to the constructor. Next we'll define `float iconColor = BitmapDescriptorFactory.HUE_RED;` which will be the color of our markers. We can now call
``` java
googleMap.addMarker(new MarkerOptions()
        .position(position)
        .icon(BitmapDescriptorFactory.defaultMarker(iconColor))
        .title(cache.name));
```
This will add out marker to the map, try it out and make sure the markers got added around waterloo.

## Marking Found Caches
Some of these caches have already been found, let's mark these ones by changing the icon color to blue. In order to do this we first have to get the `FoundCaches` data. Create a variable `foundCaches` at the top that's a `Map` of `FoundCaches.Cache`s, before your call to `DataUtilities.getMapCaches` add one to `DataUtilities.getFoundCaches`. In the callback set `foundCaches = results.caches;`, this will let us use `foundCache`s in our `makeMarkers` method.

Replace `float iconColor = BitmapDescriptorFactory.HUE_RED;` with
``` java
// Check if this cache id is in the Map of found caches. If so it has already been found
boolean hasBeenFound = foundCaches.containsKey(entry.getKey());

// This pattern someBoolean? value1 : value2 is called a ternary
// It is equivalent to if (someBoolean) { value1 } else { value2 }
// If hasBeenFound is true we use the first value 'BitmapDescriptorFactory.HUE_AZURE', else we use 'BitmapDescriptorFactory.HUE_RED'
float iconColor = hasBeenFound? BitmapDescriptorFactory.HUE_AZURE : BitmapDescriptorFactory.HUE_RED;
```
The ternary is choosing between two values `BitmapDescriptorFactory.HUE_AZURE` and `BitmapDescriptorFactory.HUE_RED` based on whether hasBeenFound is true, then we set the color to red if unfound or azure if found.

## Cache Click Popup
To make things a bit more interesting and useful we're going to change the popup that appears when we click a marker on the map to display some cache information.

- Click listener
- Info window
- Dealing with maps (found)

## Marker popup dialog
- Alert dialogs
- View inflation
- View dismiss

## Using Map
- Map settings https://developers.google.com/maps/documentation/android-api/controls

## Create viewPager
- ViewPager

## Tab layout
- Connect to viewPager

## Cache NFC?
- Read NFC of cache and check against server?
- Client/server validation

## Getting data from actual API
- Making the request

## Sending back to API from found
- Make the request, handle errors
