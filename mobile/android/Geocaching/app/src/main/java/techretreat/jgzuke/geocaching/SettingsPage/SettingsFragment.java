package techretreat.jgzuke.geocaching.SettingsPage;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import techretreat.jgzuke.geocaching.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String KEY_USER_ID = "user_id";

    private String userId;

    public static SettingsFragment newInstance(String userId) {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        args.putString(KEY_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getArguments().getString(KEY_USER_ID);

        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }
}
