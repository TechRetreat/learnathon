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
Next we will get a `Location` from `locationManager.getLastKnownLocation` and animate our `googleMap` to that `Location`'s lattitude and longitude with a zoom factor of `13`.
``` java
Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
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

In Android M the complexity of permissions was increased quite a bit, since we want to avoid this for today open up your gradle file and change `targetSdkVersion` (in `defaultConfig`) to 22.

## Using Map
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
- 

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
