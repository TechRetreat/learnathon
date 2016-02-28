package techretreat.jgzuke.geocaching.MapPage;

import android.support.v4.app.Fragment;

public class MapController {

    private String userId;

    public MapController(String userId) {
        this.userId = userId;
    }

    public Fragment createFragment() {
        return MapFragment.newInstance(userId);
    }
}
