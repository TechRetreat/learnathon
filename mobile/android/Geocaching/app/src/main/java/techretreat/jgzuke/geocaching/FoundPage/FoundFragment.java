package techretreat.jgzuke.geocaching.FoundPage;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import techretreat.jgzuke.geocaching.R;

public class FoundFragment extends Fragment {

    public FoundFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_found, container, false);
    }
}
