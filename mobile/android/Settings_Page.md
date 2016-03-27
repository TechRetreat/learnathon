# Settings Page
## Preferences.xml
Most Android applications have some sort of settings or preference page, for ours we'll allow the user to change some settings on the Map page as well as perform some actions like clearing all their found caches. Preferences in Android are defined in xml, create a Android resource directory in res called `xml` of type xml (right click on res -> new -> Android resource directory, you might have to close and reopen the res folder to see it) and create a new file inside called `preferences` with the following content.
``` xml
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <PreferenceCategory
        android:key="action_settings"
        android:title="@string/action_settings_title">

        <Preference
            android:key="action_settings_do_something"
            android:summary="@string/do_something_description"
            android:title="@string/do_something_title" />
    </PreferenceCategory>

    <PreferenceCategory
        android:key="map_settings"
        android:title="@string/map_settings_title">

        <CheckBoxPreference
            android:defaultValue="true"
            android:key="map_settings_zoom_buttons_enabled"
            android:summary="@string/zoom_enabled_description"
            android:title="@string/zoom_enabled_title" />

        <CheckBoxPreference
            android:defaultValue="false"
            android:key="map_settings_toolbar_enabled"
            android:summary="@string/toolbar_enabled_description"
            android:title="@string/toolbar_enabled_title" />
    </PreferenceCategory>
</PreferenceScreen>
```
This file has have a `PreferenceScreen` as its root tag and nested `PreferenceCategory`s inside. Each `PreferenceCategory` contains one or more `Preference` elements which can be of many types, we'll use `CheckBoxPreference` and normal `Preference`s. `PreferenceCategory`s can be nested further or `Preference` elements can go straight into `PreferenceScreen` if you want, this is just how we're organizing our screen for now. We also need our strings for this to work.
``` xml
<string name="settings">Settings</string>
    
<string name="action_settings_title">Actions</string>
<string name="do_something_description">Do Something</string>
<string name="do_something_title">Performs an action when clicked</string>

<string name="map_settings_title">Map settings</string>
<string name="zoom_enabled_title">Zoom Buttons Enabled</string>
<string name="zoom_enabled_description">Show on screen zoom control buttons</string>
<string name="toolbar_enabled_title">Toolbar Enabled</string>
<string name="toolbar_enabled_description">Show directions toolbar when you select a cache</string>
```
The last things we need to do for preferences to work is to define the preference theme by adding
``` xml
<item name="preferenceTheme">@style/PreferenceThemeOverlay.v14.Material</item>
```
to res/values/styles and adding
``` xml
<uses-sdk xmlns:tools="http://schemas.android.com/tools"
        tools:overrideLibrary="android.support.v14.preference" />
```
to `AndroidManifest.xml` in the same section we put permissions.

## SettingsFragment
Now that we have our preferences defined, compile `'com.android.support:preference-v14:23.2.0'` in gradle and create a new java class called `SettingsFragment` that extends `PreferenceFragmentCompat`.
``` java
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }
}
```
Now all we have to do to get our preferences to display is add `addPreferencesFromResource(R.xml.preferences);` to our `onCreate` method. To get our `action_settings_do_something` preference to perform an action we need to first get a reference to it by calling `Preference doSomething = findPreference("action_settings_do_something");` and then set its `OnPreferenceClickListener`.
``` java
doSomething.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    @Override
    public boolean onPreferenceClick(Preference preference) {
        // Perform the action
        return true;
    }
});
```

## Add SettingsFragment to ViewPager
We now have a third `Fragment` to show in our `ViewPager`. Go back to `MainActivity.GeocachingPagerAdapter` and add entries in `getItem` and `getPageTitle` for `SettingsFragment`, make sure to update `getCount` as well.

## Accessing Preferences
Let's create a new class called `PreferenceUtilities` where we will retreive the users preferences. First define two String constants 
``` java
private static final String ZOOM_ENABLED = "map_settings_zoom_buttons_enabled";
private static final String TOOLBAR_ENABLED = "map_settings_toolbar_enabled";
```
which are the preference keys defined in preferences.xml. To get the users preference we first get a `SharedPreference` object from `PreferenceManager`, `SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);` and then get the value from this object by its key, `boolean zoomEnabled = preferences.getBoolean(ZOOM_ENABLED, true);`. Write two static methods `getZoomEnabled` and `getToolbarEnabled` that take in a `Context` and return a boolean of whether the preference was set to true.

## Using PreferenceUtilities
If we now go to our `MapFragment` we can start implementing these preferences. Create a method `setMapSettings` that takes in no parameters and call it in `onMapReady`. Inside this method get a reference to our `googleMap`'s `UiSettings`, `UiSettings settings = googleMap.getUiSettings();` and then call `settings.setZoomControlsEnabled` and `setMapToolbarEnabled` passing in `PreferenceUtilities.getZoomEnabled(getContext())` and `getToolbarEnabled` as parameters. 

Because `onMapReady` only gets called when the Fragment is first created and the `ViewPager` saves it, we need to listen for preference changes if we want things to change immediately after returning to the map. Create a variable in `MapFragment`, `private SharedPreferences.OnSharedPreferenceChangeListener listener;` and set
``` java
listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        setMapSettings();
    }
};
```
in `onMapReady`. This will listen for changes in our preferences and set our map options when anything changes. Now we have to register our listener in `onMapReady` as follows
``` java
SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
preferences.registerOnSharedPreferenceChangeListener(listener);
```
This gets our `SharedPreferences` and registers our listener to fire when anything changes. We also have to unregister our listener so it stops listening when our app is closed or the `MapFragment` is killed. To do this we override `onDestroy` in `MapFragment` that gets called when the fragment is being destroyed.
``` java
@Override
public void onDestroy() {
    super.onDestroy();
    PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(listener);
}
```
Our Settings page should now modify the map as we change them, run the app and try it out.
