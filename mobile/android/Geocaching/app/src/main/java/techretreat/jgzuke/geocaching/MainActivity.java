package techretreat.jgzuke.geocaching;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import techretreat.jgzuke.geocaching.FoundPage.FoundController;
import techretreat.jgzuke.geocaching.MapPage.MapController;
import techretreat.jgzuke.geocaching.SettingsPage.SettingsController;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_TABS = 3;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private GeocachingPagerAdapter pagerAdapter;

    //TODO not this
    private String userId = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.found)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.map)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.settings)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new GeocachingPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new GeocachingTabSelectedListener());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private class GeocachingTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private class GeocachingPagerAdapter extends FragmentStatePagerAdapter {

        public GeocachingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FoundController(userId, MainActivity.this).getFragment();
                case 1:
                    return new MapController(userId, MainActivity.this).getFragment();
                case 2:
                    return new SettingsController(userId, MainActivity.this).getFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUMBER_OF_TABS;
        }
    }
}