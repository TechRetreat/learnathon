package techretreat.jgzuke.geocaching.FoundPage;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techretreat.jgzuke.geocaching.R;

public class FoundFragment extends Fragment {

    private static final String KEY_USER_ID = "user_id";

    private String userId;

    public static FoundFragment newInstance(String userId) {
        Bundle args = new Bundle();
        FoundFragment fragment = new FoundFragment();
        args.putString(KEY_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    public FoundFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userId = getArguments().getString(KEY_USER_ID);

        return inflater.inflate(R.layout.fragment_found, container, false);
    }

    public void setFoundCaches(Caches.Cache[] caches) {

    }
}
