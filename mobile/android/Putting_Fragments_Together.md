# Putting the Fragments Together
## ViewPager
### Create the ViewPager
We now have two `Fragment`s and we need a way to switch between them easily. A `ViewPager` is a class that allows you to swipe between different pages, we will be using this to hold our `Fragment`s. To start lets replace the current contents on `activity_main.xml` with
``` xml
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?attr/colorPrimary"
        android:elevation="6dp"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"/>

    <android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="fill_parent"
        android:layout_below="@id/tab_layout"/>
</RelativeLayout>
```
Basically we have two views, a `TabLayout` at the top which will have a list of `Fragment`s you will be able to click to navigate to and the `ViewPager` which will hold our `Fragment`s.

Over in `MainActivity` lets get references to these views like we did in our fragments. Create two variables `private ViewPager viewPager;` and `private TabLayout tabLayout;` and after calling `setContentView` set their values as we did before.
``` java
tabLayout = (TabLayout) findViewById(R.id.tab_layout);
viewPager = (ViewPager) findViewById(R.id.view_pager);
```
Now let's create a custom adapter for our `ViewPager` which will return the proper fragments for each position, similar to our `RecyclerView` adapter earlier. Create a new inner class in `MainActivity` called `GeocachingPagerAdapter` that `extends FragmentStatePagerAdapter`.
``` java
class GeocachingPagerAdapter extends FragmentStatePagerAdapter {

    // Constructor that mimics FragmentStatePagerAdapter's
    public GeocachingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // This will return our Fragments for each position
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    // This will return our Fragment titles for each position
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    // The number of Fragments in the ViewPager
    @Override
    public int getCount() {
        return 0;
    }
}
```
In `getItem` we want to return a `FoundCachesFragment` if we're viewing the first tab (`position == 0`) or a `MapFragment` for the second. We write that as follows
``` java
if (position == FOUND_TAB) {
    return new FoundCachesFragment();
} else {
    return new MapFragment();
}
```
In `getPageTitle` we want to return "Found" for the first and "Map" for the second. Define two new entries in `strings.xml` and return the correct one from this method using `getString(R.string.your_string);`.

Up in `onCreate` after `viewPager = (ViewPager) findViewById(R.id.view_pager);` add the following
``` java
// Create a new adapter for our ViewPager and set it
viewPagerAdapter = new GeocachingPagerAdapter(getSupportFragmentManager());
viewPager.setAdapter(viewPagerAdapter);

// Set up out TabLayout to control our ViewPager
tabLayout.setupWithViewPager(viewPager);

// This removes the shadow from the top bar, you can ignore this
if(getSupportActionBar() != null) {
    getSupportActionBar().setElevation(0);
}
```

## API
### Getting data from actual API
- Making the request

### Sending back to API from found
- Make the request, handle errors
