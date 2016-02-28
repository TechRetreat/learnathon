package techretreat.jgzuke.geocaching.MapPage;

import android.content.Context;
import android.support.v4.app.Fragment;

public class MapController implements MapDataInteractor.DataReceiver {

    private String userId;
    private Context context;
    private MapFragment mapFragment;
    private MapDataInteractor mapDataInteractor;

    public MapController(String userId, Context context) {
        this.userId = userId;

        mapFragment = MapFragment.newInstance(userId);
        mapDataInteractor = new MapDataInteractor(userId, context, this);
    }

    public Fragment getFragment() {
        return mapFragment;
    }
}
