package techretreat.jgzuke.geocaching.MapPage;


import android.content.Context;

import techretreat.jgzuke.geocaching.DataUtilities;

public class MapDataInteractor {

    public interface DataReceiver {
    }

    private String userId;
    private Context context;
    private DataReceiver reciever;

    public MapDataInteractor(String userId, Context context, DataReceiver reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }

    public void getCaches() {
        MapCaches response = new DataUtilities<MapCaches>().getResponse(context, MapCaches.class);
    }
}