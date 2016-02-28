package techretreat.jgzuke.geocaching.SettingsPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techretreat.jgzuke.geocaching.R;

public class SettingsFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userId = getArguments().getString(KEY_USER_ID);
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        return rootView;
    }
}
