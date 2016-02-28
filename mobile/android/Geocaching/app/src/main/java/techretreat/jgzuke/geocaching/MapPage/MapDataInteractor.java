package techretreat.jgzuke.geocaching.MapPage;


import android.content.Context;

import techretreat.jgzuke.geocaching.DataUtilities;

public class MapDataInteractor {

    public interface DataReciever {
    }

    private String userId;
    private Context context;
    private DataReciever reciever;

    public MapDataInteractor(String userId, Context context, DataReciever reciever) {
        this.userId = userId;
        this.context = context;
        this.reciever = reciever;
    }

    public void getCaches() {
        Caches response = new DataUtilities<Caches>().getResponse(context, Caches.class);
    }
}